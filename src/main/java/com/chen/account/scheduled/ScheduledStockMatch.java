package com.chen.account.scheduled;

import com.chen.account.dao.StockDataMapperExtends;
import com.chen.account.dao.StockOrderMapperExtends;
import com.chen.account.dao.UserMapperExtends;
import com.chen.account.entity.StockData;
import com.chen.account.entity.StockOrder;
import com.chen.account.entity.User;
import com.chen.common.utils.FormatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author long
 * <p>
 * date 17-9-22
 * <p>
 * desc 撮合股票
 */
@Component
@EnableScheduling
public class ScheduledStockMatch {

    public final static long TEN_Second = 10 * 1000;

    public final static boolean DEBUG = true;
    private static Logger logger = Logger.getLogger(ScheduledStockMatch.class);

    @Autowired
    private StockOrderMapperExtends stockOrderMapperExtends;

    @Autowired
    private StockDataMapperExtends stockDataMapperExtends;

    @Autowired
    private UserMapperExtends userMapperExtends;

    //存储撮合后的订单
    private StockOrder matchOrder;

    //10秒钟执行一次   前面任务的执行完成才会再次执行
    @Scheduled(fixedDelay = TEN_Second)
    public void executeStockMatch() {
        List[] lists;
        Thread current = Thread.currentThread();
        logger.info(current.getName() + ":" + current.getId());
        //从数据库取数据(除掉相同的股票ID)
        List<StockOrder> stockIds = stockOrderMapperExtends.getStockId();
       // logger.info(stockIds == null);
        /**这里将股票分类 */
        if (stockIds.size() != 0) {
            lists = new ArrayList[stockIds.size()];
            for (int i = 0; i < stockIds.size(); i++) {
                String stockId = stockIds.get(i).getStockId();
                //这里拿到股票Id,再去查询数据库里面当前股票的相关信息()
                lists[i] = stockOrderMapperExtends.selectByStockId(stockId);
                logger.info(stockId);
            }
            StockMatch stockMatch = new StockMatch(lists);
            if (DEBUG) {
                logger.info("*****开始执行*****");
            }
            while (stockMatch.match()) {
                if (DEBUG) {
                    logger.info("撮合成功");
                }
            }
        }

        /** 这里打印了股票的信息*/
        /*for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                StockOrder stockOrder = (StockOrder) lists[i].get(j);
                logger.info("股票的id:" + stockOrder.getStockId() + ",股票的用户名:" + stockOrder.getUser() + ",订单类型:" + stockOrder.getOrderType() + ",股票的价格" + stockOrder.getOrderPrice());
            }
            logger.info("**************************");
        }*/
    }


    public class StockMatch {
        //定义一个阻塞队列 (买单队列)
        BlockingQueue<StockOrder> bigQueue = new LinkedBlockingQueue<>();
        //卖单队列
        BlockingQueue<StockOrder> askQueue = new LinkedBlockingQueue<>();
        //股票订单
        private List<StockOrder> stockOrders = new ArrayList<>();
        //买家订单
        private List<StockOrder> bigOrders = new ArrayList<>();
        //卖家订单
        private List<StockOrder> askOrders = new ArrayList<>();
        //存储买单信息
        StockOrder order1;
        //存储卖单信息
        StockOrder order2;

        public StockMatch(List[] lists) {
            for (List<StockOrder> stockOrders : lists) {
                splitOrder(stockOrders);
                if (DEBUG) {
                    logger.info("*****分割以及放入队列完成*****");
                }
            }
        }

        /**
         * 分割订单并且放入队列
         * 将总订单分为买单和卖单
         *
         * @param orders 总的订单
         */
        private void splitOrder(List<StockOrder> orders) {
            for (int i = 0; i < orders.size(); i++) {
                //代表买单
                if (orders.get(i).getOrderType() == 0) {
                    bigOrders.add(orders.get(i));
                    try {
                        //将买单添加到买单队列里面
                        bigQueue.put(orders.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {   //卖单
                    askOrders.add(orders.get(i));
                }
            }
            for (int i = askOrders.size() - 1; i >= 0; i--) {
                //  System.out.println(askOrders.get(i).toString());
                try {
                    //将卖单添加到卖单队列里面
                    askQueue.put(askOrders.get(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 撮合股票
         * (待重构)
         *
         * @return 能撮合就返回true  不能撮合就返回false
         */
        public boolean match() {
            if (DEBUG) {
                logger.info("*****开始撮合*****");
            }
            //买单长度大于卖单长度
            if (bigOrders.size() > askOrders.size()) {
                for (int i = 0; i < bigOrders.size(); i++) {
                    try {
                        //先取数据
                        order1 = bigQueue.take();
                        order2 = askQueue.take();
                        //首先买单价格必须大于卖单价格才能撮合
                        if (order1.getOrderPrice() > order2.getOrderPrice()) {
                            return updateOrder(order1, order2);
                        } else {
                            return false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (bigOrders.size() == askOrders.size()) {
                for (int i = 0; i < bigOrders.size(); i++) {
                    try {
                        //先取数据
                        order1 = bigQueue.take();
                        order2 = askQueue.take();
                        //首先买单价格必须大于卖单价格才能撮合
                        if (order1.getOrderPrice() > order2.getOrderPrice()) {
                            return updateOrder(order1, order2);
                        } else {
                            return false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (int i = 0; i < askOrders.size(); i++) {
                    try {
                        //先取数据
                        order1 = bigQueue.take();
                        order2 = askQueue.take();
                        //首先买单价格必须大于卖单价格才能撮合
                        if (order1.getOrderPrice() > order2.getOrderPrice()) {
                            return updateOrder(order1, order2);
                        } else {
                            return false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }

        /**
         * @param order1 买单
         * @param order2 卖单
         * @return
         */
        private boolean updateOrder(StockOrder order1, StockOrder order2) {
            if (DEBUG) {
                logger.info("*****开始更新订单*****");
            }
            //成交时间
            Date date = null;
            //成交剩余的数量
            int amount;
            //成交均价
            double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
            try {
                //格式化时间
                date = FormatUtil.formatDate(new Date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //买单数量大于等于卖单数量
            if (order1.getAmount() >= order2.getAmount()) {
                int a, b;
                amount = order1.getAmount() - order2.getAmount();
                //买单数量部分成交
                order1.setMatchTime(date);
                order1.setMatchPrice(match_price);
                order1.setOrderState(2);
                order1.setAmount(amount);
                order1.setMatchAmount(order2.getAmount());
                a = stockOrderMapperExtends.updateByPrimaryKey(order1);
                //卖单完全成交
                order2.setMatchTime(date);
                order2.setMatchPrice(match_price);
                order2.setOrderState(1);
                order2.setMatchAmount(order2.getAmount());
                b = stockOrderMapperExtends.updateByPrimaryKey(order2);
                //订单状态都修改成功了,
                if (a == 1 && b == 1) {
                    //对用户的持有股票和金额进行修改
                    //设置买单 买入之后的状态
                    StockData stockData = new StockData();
                    User user;
                    //设置手机号
                    stockData.setPhone(order1.getUser());
                    //设置价格
                    stockData.setBuyMoney(order1.getOrderPrice());
                    //持有数量
                    stockData.setHaveAmount(order2.getAmount());
                    stockData.setStockId(order1.getStockId());
                    stockData.setStockName(order1.getStockName());
                    stockData.setStockMoney(order1.getMatchPrice());
                    //更新用户账户
                    user = userMapperExtends.selectByPhone(order1.getUser());
                    user.setMoney(user.getMoney() - (order2.getAmount() * match_price));
                    userMapperExtends.updateByPrimaryKey(user);
                    if (DEBUG) {
                        logger.info("*****买家用户账户更新完成*****");
                    }
                    stockDataMapperExtends.updateByPrimaryKey(stockData);
                    if (DEBUG) {
                        logger.info("*****买家用户持股数据更新完成*****");
                    }
                    //设置卖单 卖出之后的状态
                    stockData = stockDataMapperExtends.selectByPhoneAndStockId(order2.getUser(), order2.getStockId());
                    //设置卖家持有的剩余股票
                    stockData.setHaveAmount(stockData.getHaveAmount() - order2.getAmount());
                    //设置卖家可卖的股票
                    stockData.setSellAmount(stockData.getHaveAmount() - order2.getAmount());
                    //设置股票金额
                    stockData.setStockMoney(match_price);
                    //设置盈亏金额
                    stockData.setProMoney((stockData.getBuyMoney() - match_price) * (stockData.getHaveAmount() - order2.getAmount()));
                    user = userMapperExtends.selectByPhone(order2.getUser());
                    user.setMoney(user.getMoney() + (order2.getAmount() * match_price));
                    userMapperExtends.updateByPrimaryKey(user);
                    if (DEBUG) {
                        logger.info("*****卖家用户账户更新完成*****");
                    }
                    stockDataMapperExtends.updateByPrimaryKey(stockData);
                    if (DEBUG) {
                        logger.info("*****卖家用户持股数据更新完成*****");
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                //卖单数量大于买单数量
                int a, b;
                amount = order2.getAmount() - order1.getAmount();
                //买单完全成交
                order1.setMatchTime(date);
                order1.setMatchPrice(match_price);
                order1.setOrderState(1);
                order1.setMatchAmount(order2.getAmount());
                a = stockOrderMapperExtends.updateByPrimaryKey(order1);
                //卖单数量部分成交
                order2.setMatchTime(date);
                order2.setMatchPrice(match_price);
                order2.setOrderState(2);
                order2.setAmount(amount);
                order2.setMatchAmount(order2.getAmount());
                b = stockOrderMapperExtends.updateByPrimaryKey(order2);
                //订单状态都修改成功了,
                if (a == 1 && b == 1) {
                    //对用户的持有股票和金额进行修改
                    //设置买单 买入之后的状态
                    StockData stockData = new StockData();
                    User user;
                    //设置手机号
                    stockData.setPhone(order1.getUser());
                    //设置价格
                    stockData.setBuyMoney(order1.getOrderPrice());
                    //持有数量
                    stockData.setHaveAmount(order1.getAmount());
                    stockData.setStockId(order1.getStockId());
                    stockData.setStockName(order1.getStockName());
                    stockData.setStockMoney(order1.getMatchPrice());
                    //更新用户账户
                    user = userMapperExtends.selectByPhone(order1.getUser());
                    if (user != null) {
                        user.setMoney(user.getMoney() - (order1.getAmount() * match_price));
                        if (DEBUG) {
                            logger.info("*****买家用户账户更新完成*****");
                        }
                        stockDataMapperExtends.updateByPrimaryKey(stockData);
                    }
                    if (DEBUG) {
                        logger.info("*****买家用户持股数据更新完成*****");
                    }
                    //设置卖单 卖出之后的状态
                    stockData = stockDataMapperExtends.selectByPhoneAndStockId(order2.getUser(), order2.getStockId());
                    if (stockData != null) {
                        logger.info(stockData);
                        //设置卖家持有的剩余股票
                        stockData.setHaveAmount(stockData.getHaveAmount() - order1.getAmount());
                        //设置卖家可卖的股票
                        stockData.setSellAmount(stockData.getHaveAmount() - order1.getAmount());
                        //设置股票金额
                        stockData.setStockMoney(match_price);
                        //设置盈亏金额
                        stockData.setProMoney((stockData.getBuyMoney() - match_price) * (stockData.getHaveAmount() - order1.getAmount()));
                        user = userMapperExtends.selectByPhone(order2.getUser());
                        if (user != null) {
                            user.setMoney(user.getMoney() - (order1.getAmount() * match_price));
                            if (DEBUG) {
                                logger.info("*****卖家用户账户更新完成*****");
                            }
                        }
                        stockDataMapperExtends.updateByPrimaryKey(stockData);
                        if (DEBUG) {
                            logger.info("*****卖家用户持股数据更新完成*****");
                        }
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

    }

}


