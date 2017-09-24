package com.chen.account.scheduled;

import com.chen.account.dao.StockDataMapperExtends;
import com.chen.account.dao.StockOrderMapperExtends;
import com.chen.account.entity.StockOrder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private static Logger logger = Logger.getLogger(ScheduledStockMatch.class);

    @Autowired
    private StockOrderMapperExtends stockOrderMapperExtends;

    @Autowired
    private StockDataMapperExtends stockDataMapperExtends;

    //存储撮合后的订单
    private StockOrder matchOrder;

    //10秒钟执行一次   前面任务的执行完成才会再次执行
    @Scheduled(fixedDelay = TEN_Second)
    public void executeStockMatch() {
        List[] lists = null;
        Thread current = Thread.currentThread();
        logger.info(current.getName() + ":" + current.getId());
        //从数据库取数据(除掉相同的股票ID)
        List<StockOrder> stockIds = stockOrderMapperExtends.getStockId();
        /**这里将股票分类 */
        if (stockIds != null) {
            lists = new ArrayList[stockIds.size()];
            for (int i = 0; i < stockIds.size(); i++) {
                String stockId = stockIds.get(i).getStockId();
                //这里拿到股票Id,再去查询数据库里面当前股票的相关信息()
                lists[i] = stockOrderMapperExtends.selectByStockId(stockId);
                logger.info(stockId);
            }
            StockMatch stockMatch = new StockMatch(lists);
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
            //买单长度大于卖单长度
            if (bigOrders.size() > askOrders.size()) {
                for (int i = 0; i < bigOrders.size(); i++) {
                    try {
                        //先取数据
                        order1 = bigQueue.take();
                        order2 = askQueue.take();
                        //首先买单价格必须大于卖单价格才能撮合
                        if (order1.getOrderPrice() > order2.getOrderPrice()) {
                            //买单数量大于卖单数量
                            if (order1.getAmount() > order2.getAmount()) {
                                //成交的数量
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(null, order2, amount, data, match_price);
                            } else if (Objects.equals(order1.getAmount(), order2.getAmount())) {
                                //买卖数量相等
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, order2, amount, data, match_price);
                            } else {   //买单数量小于卖单数量
                                int amount = order1.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, null, amount, data, match_price);
                            }
                            //对部分成交的订单生成一个新订单.
                            // addOrder();
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
                            //买单数量大于卖单数量
                            if (order1.getAmount() > order2.getAmount()) {
                                //成交的数量
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(null, order2, amount, data, match_price);
                            }else if (Objects.equals(order1.getAmount(), order2.getAmount())) {
                                //买卖数量相等
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, order2, amount, data, match_price);
                            } else {   //买单数量小于卖单数量
                                int amount = order1.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, null, amount, data, match_price);
                            }
                            //对部分成交的订单生成一个新订单.
                            // addOrder();
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
                            //买单数量大于卖单数量
                            if (order1.getAmount() > order2.getAmount()) {
                                //成交的数量
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(null, order2, amount, data, match_price);
                            }else if (Objects.equals(order1.getAmount(), order2.getAmount())) {
                                //买卖数量相等
                                int amount = order2.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, order2, amount, data, match_price);
                            } else {   //买单数量小于卖单数量
                                int amount = order1.getAmount();
                                long match_time = System.currentTimeMillis();
                                Date data = new Date(match_time);
                                double match_price = (order1.getOrderPrice() + order2.getOrderPrice()) / 2;
                                return updateOrder(order1, null, amount, data, match_price);
                            }
                            //对部分成交的订单生成一个新订单.
                            // addOrder();
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


       /* public boolean matchOrder(StockOrder order1, StockOrder order2) {

        }*/


        /**
         * @param order1
         * @param order2
         * @param amount
         * @param data
         * @param match_price
         * @return
         */
        private boolean updateOrder(StockOrder order1, StockOrder order2, int amount, Date data, double match_price) {
            if (order1 != null && order2 != null) {
                order1.setMatchTime(data);
                order1.setOrderPrice(match_price);
                order1.setOrderState(1);
                stockOrderMapperExtends.updateByPrimaryKey(order1);
                order2.setMatchTime(data);
                order2.setOrderPrice(match_price);
                order2.setOrderState(1);
                stockOrderMapperExtends.updateByPrimaryKey(order2);
                return true;
            }
            //数量被消耗完的订单  更改其状态
            if (order1 != null) {
                order1.setMatchTime(data);
                order1.setOrderPrice(match_price);
                order1.setOrderState(1);
                stockOrderMapperExtends.updateByPrimaryKey(order1);
                return true;
            }
            if (order2 != null) {
                order2.setMatchTime(data);
                order2.setOrderPrice(match_price);
                order2.setOrderState(1);
                stockOrderMapperExtends.updateByPrimaryKey(order2);
                return true;
            }
            return false;
        }


        /**
         * 添加新订单
         *
         * @param order
         */
        private boolean addOrder(StockOrder order) {

            return true;
        }


    }


}
