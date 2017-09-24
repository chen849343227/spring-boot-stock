$().ready(function () {
    var code = window.sessionStorage.getItem('code');
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
});
/*** 声明 ***/
var newData;
var buy;
var money;
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
        url: 'http://192.168.43.76:8080/stock/data',
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
            var newdata = data.response;
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
            if(profit>Number(0)){
                console.log("赚了！");
            }else{
                console.log("亏大了！");
            }
        }
    })
}
/*** 初始化界面 ***/
function initStorang(e){
    var content=`<li class="lio1" style="cursor:pointer;background-color: #f6f8fa;" data-hash='sh/${e.stockId}'>
    <div class="div01">
        <p class="p01">
            <a>${e.name}</a>
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
        //回调函数
        message="交易成功！";
        message02="";
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