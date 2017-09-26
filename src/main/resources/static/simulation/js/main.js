$().ready(function () {
    code = window.sessionStorage.getItem('code');
    init(code);
    var userData=window.localStorage.getItem('userData');
    if(userData==undefined){
        $("#userId").text('未登录');
        return;
    }else{
        newUD=JSON.parse(userData);
        newData=newUD.response;
        var username=newData.username;
        console.log(newData);
        $("#userId").text(username);
        getStockData(newData.phone);
    };
    initLocal();
    printRecord();
});
/*** 声明 ***/
var newData;
var buy;
var money;
var code;
var $account=$("#account"),
    $content=$account.find("p");
var $watchList=$("#watchList");
var $quote=$("#quote"),
    $container=$quote.find("span");
var $li02=$("#li02").find("span");
var $li022=$("#li022").find("font");
var $li0222=$("#li0222").find("font");
/**** 页面初始请求 ***/
function init(code) {
    $(function () {
        $.ajax({
            url: '/stock/detail',
            type: 'POST',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            data: {
                stockId: code
            },
            error: function (data) {
                alert("ajax请求失败"+data);
            },
            success: function (data) {
                console.log(data);
                initStorang(data.response);
            }
        });
    });
};
/*** 返回登录 ***/
$("#userId").on('click',function(){
    var $val = $(this).text();
    if($val=='未登录'){
        window.location.href = 'index';
    }else{
        return;
    }
});
//获取个人持股信息
function getStockData(phone){
    $.ajax({
        url: '/stock/data',
        type: 'POST',
        dataType: 'json',
        timeout: 2000,
        cache: false,
        data: {
            phone: phone
        },
        error: function (data) {
            alert("ajax请求失败"+data);
        },
        success: function (data) {
            var newDataa = data.response;
            var newdata;
            for(x in newDataa){
                if(newDataa[x].stockId==code){
                    console.log(newDataa[x]);
                    newdata=newDataa[x];
                }
            };
            var proportion;
            var marketValue=(Number(newdata.stockMoney)*Number(newdata.haveAmount)).toFixed(2);
            var costing=Number(newdata.buyMoney).toFixed(2);
            var totalCosting=costing*Number(newdata.haveAmount);
            var profit=(marketValue-totalCosting).toFixed(2);
            var profitRatio=(profit/totalCosting*100).toFixed(2)+'%';
            console.log(data);
            var content = `<tr>
                <td class="ttd">${newdata.stockId}</td>
                <td class="ttd">${newdata.stockName}</td>
                <td class="ttd" align="left">${newdata.haveAmount}@${newdata.stockMoney}</td>
                <td class="ttd" align="right">${newdata.sellAmount}</td>
                <td class="ttd" align="right">${marketValue}</td>
                <td class="ttd" align="right">${costing}</td>
                <td class="ttd" align="right">${profit}</td>
                <td class="ttd" align="right">${profitRatio}</td>
                <td align="center"><a>交易</a></td>
            </tr>`
            $("#positions").append(content);
        }
    })
}
/*** 初始化界面 ***/
function initStorang(e){
    var content=`<li class="lio1" style="cursor:pointer;background-color: #f6f8fa;" data-hash='sh/${e.stockId}'>
    <div class="div01">
        <p class="p01">
            <a id="stockName">${e.name}</a>
        </p>
        <p class="p02">${e.stockId}.sh</p>
    </div>
    <div class="div02 down">
        <p class="p01">${e.lastestPri}</p>
    </div>
</li>`;
    var buy=Math.floor(newData.money/e.inPic);
    var newbuy=buy;
    if(newbuy>10000){
        newbuy=Math.floor(newbuy/1000)+"k";
    }
    $watchList.append(content);
    $container.eq(0).text(e.stockId+" "+e.name).attr("title",e.stockId+" "+e.name);
    $container.eq(1).text(e.lastestPri);
    $container.eq(7).text(e.maxPri);
    $container.eq(8).text(e.minPri);
    $container.eq(9).text(e.openPri);
    $container.eq(10).text(e.formPri);
    $container.eq(11).text((e.traAmount/10000).toFixed(2)+"万");
    $container.eq(12).text(e.traNumber);
    $container.eq(13).text(e.priearn);
    $container.eq(14).text(e.formPri);
    $("#stockCodeInput").val(e.stockId);
    $("#price").val(e.inPic);
    $("[name='qty_str']").val(100);
    $li02.eq(0).text(e.name);
    $li02.eq(2).text(e.maxPri);
    $li022.eq(0).text(e.inPic);
    $li022.eq(1).text(e.outPic);
    $li0222.eq(0).text(newbuy);
    $li0222.eq(1).text(0);
};
function initLocal(){
    if(newData.money>10000){
        var allMoney=Math.floor(newData.money/10000)+"万";
    }else{
        var allMoney=newData.money.toFixed(2);
    }
    $content.eq(0).text(newData.username).css({
        "line-height":"32px","height":"32px","font-size":"14px"
    });
    $content.eq(2).text(allMoney);
    $content.eq(4).text((0).toFixed(2));
    money = Number(newData.money)-Number($content.eq(4).text());
    $content.eq(6).text(money.toFixed(2));
    $content.eq(8).text(0);
};

/*** 加点计数 ***/
var $iconfont = $("span").find(".iconfont");
$iconfont.eq(0).on("click", function () {
    var $value = $(this).parent().parent().children().first();
    var value = Number($value.val());
    var newVal = value + Number(0.01);
    $value.val(newVal.toFixed(2));
});
$iconfont.eq(1).on("click", function () {
    var $value = $(this).parent().parent().children().first();
    var value = Number($value.val());
    if (value == 0) {
        return false;
    }
    var newVal = value - Number(0.01);
    $value.val(newVal.toFixed(2));
});
$iconfont.eq(2).on("click", function () {
    var $value = $(this).parent().parent().children().first();
    var value = Number($value.val());
    var newVal = value + Number(100);
    $value.val(newVal);
});
$iconfont.eq(3).on("click", function () {
    var $value = $(this).parent().parent().children().first();
    var value = Number($value.val());
    if (value == 0) {
        return false;
    }
    var newVal = value - Number(100);
    $value.val(newVal);
});
/**** 买入卖出 ***/
$("#buy").on("click",function(){
    var initPrice=Number($li022.eq(0).text());
    var message,message02,price,number;
    price=Number($("#price").val());
    number=Number($("[name='qty_str']").val());
    var buy_money=Math.floor(price*number);
    var upPrice=Number(initPrice*1.1).toFixed(2);
    var downPrice=Number(initPrice*0.9).toFixed(2);
    if(buy_money==0){
        return false;
    }
    if(buy_money>money){
        message="交易失败！";
        message02="可用现金不足以完成本次交易";
    }
    if(price>upPrice||price<downPrice){
        message="交易失败！";
        message02="交易价格不在涨幅区间内"+"("+downPrice+"~"+upPrice+")";
    }else{
        var $stockName = $("#stockName").text();
        $.ajax({
            async:false,
            url: '/stock/commitOrder',
            type: 'POST',
            dataType: 'json',
            timeout: 2000,
            cache: false,
            data: {
                orderType:'0',
                user:newData.phone,
                stockId: code,
                orderPrice:price,
                amount:number,
                stockName:$stockName
            },
            error: function (data) {
                message="交易失败！";
                message02="Ajax数据提交出错！";
            },
            success: function (data){
                console.log(data);
                message="交易成功！";
                message02="";
            }
        });
    }
    var container = `<div class="cover">
    <div class="buyMessage">
        <h2>${message}</h2>
        <h6>${message02}</h6>
        <a class="buyEnter">确认</a>
    </div></div>`;
    $("body").append(container);
    var $cover=$(".cover");
    var $buyMessage=$(".buyMessage");
    $cover.on('click',function(){
        $buyMessage.stop(true,true).animate({
            "margin-left":"-30px"
        },50).animate({
            "margin-left":"30px"
        },50).animate({
            "margin-left":"0"
        },50)
    });
    $buyMessage.on('click',function(e){
        e.stopPropagation();
    });
    $buyMessage.find(".buyEnter").on('click',function(){
        $cover.remove();
    });

});

/* 获取订单信息 */
function printRecord(){
    console.log(code);
    console.log(newData.phone);
    var orderType;
    var orderState;
    $.ajax({
        async:true,
        url: 'http://192.168.43.76:8080/stock/allOrder',
        type: 'POST',
        dataType: 'json',
        timeout: 2000,
        cache: false,
        data: {
            stockId:code,
            phone:newData.phone
        },
        error: function (data) {
            alert("数据请求失败！");
        },
        success: function (data){
            console.log(data);
            // var newdata = data.reponse;
            // if(newdata=="null"){
            //     console.log("没有订单信息！");
            // }else{
            //     if(newdata.orderType==0){
            //         orderType="买入";
            //     }else if(newdata.orderType==1){
            //         orderType="卖出";
            //     };
            //     if(newdata.orderState==0){
            //         orderState="已成交";
            //     }else if(newdata.orderState==1){
            //         orderState="等待成交";
            //     }
            //     var content = `<tr>
            //     <td class="ttd">${orderType}</td>
            //     <td class="ttd">${newdata.stockId}</td>
            //     <td class="ttd" align="left">${newdata.stockName}</td>
            //     <td class="ttd" align="right">${newdata.orderPrice}</td>
            //     <td class="ttd" align="right">${newdata.amount}</td>
            //     <td class="ttd" align="right">${orderState}</td>
            //     <td class="ttd" align="right">0@0.00</td>
            //     <td class="ttd" align="right">${newdata.stockTime}</td>
            //     <td align="center"><a>交易</a></td>
            // </tr>`
            //     $("#orders").append(content);
        }


    })

}
/* 折线图 */
chart = new Highcharts.Chart('container', {
    plotOptions : {
        series : {

        }
    },
    credits : {
        enabled : false
    },
    chart : {
        // type:"column"
        type : ""
        // 指定图标类型，默认是折线图
    },
    title : {
        text : "" // 图标标题
    },
    xAxis : {
        type: 'time',

    },
    yAxis : {
        title : {
            text : '' // 指定y轴的标题
        }
    },
    plotOptions : {
        line : {
            events : {
                legendItemClick : function(event) {
                    return false;
                }
            }
        },
        column:{
            events : {
                legendItemClick : function(event) {
                    return false;
                }
            }
        }
    },
    series : [ { // 指定数据列
        name : "", // 列名
        data : [100,104,101]
        // 数据
    }]
});
