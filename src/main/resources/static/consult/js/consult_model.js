$(function () {
    $.ajax({
        url: '/stock/all',
        type: 'POST',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        data: {},
        beforeSend: LoadFunction, //加载执行方法
        error: erryFunction,  //错误执行方法
        success: succFunction //成功执行方法
    });
    function LoadFunction() {
        /*$("#hsi-stock").html('加载中...');*/
    }
    function erryFunction() {
        alert("error");
    }
    function succFunction(stock) {
        // $("#list").html('');
        console.log(stock);
        console.log(stock.response);
        var stocks = stock.response;
        $.each(stocks, function() {
            /*console.log(this.name);
            console.log(this.eps);*/
            $('#hsi-stock').append("<tr><td>"+this.symbol+"</td><td>"+this.name+"</td><td class='up'>"+this.buy+"</td><td class='up'>"+this.changePercent+"</td><td class='up'>"+this.priceChange+"</td><td>"+this.stocksSum+"</td><td>"+this.amount+"</td><td class='text-center'><a id='stock_id' onclick='oncli(this)'>模拟交易</a></td></tr>");
            $('#hsi-stock1').append("<tr><td>"+this.symbol+"</td><td>"+this.name+"</td><td>"+this.dividend+"</td><td>"+this.stocks_sum+"</td><td>"+this.eps+"</td><td>"+this.volume+"</td><td>"+this.stocks_sum+"</td><td><a>模拟交易</a><a><span></span>自选</a></td></tr>");
            $('#hsi-stock2').append("<tr><td>"+this.symbol+"</td><td>"+this.name+"</td><td>"+this.dividend+"</td><td>"+this.stocks_sum+"</td><td>"+this.eps+"</td><td>"+this.volume+"</td><td>"+this.stocks_sum+"</td><td><a>模拟交易</a><a><span></span>自选</a></td></tr>");
        });
    }
    var userData=window.localStorage.getItem('userData');
    if(userData==undefined){
        $("#userId").text('未登录');
    }else{
        var newUD=JSON.parse(userData);
        var userID=newUD.response.username;
        $("#userName").text(userID);
    }

});

/*定义oncli函数，请求个股详细信息*/
function oncli(stockId) {
    var code=$(stockId).parent().parent().children().first().text();
    window.sessionStorage.setItem('code',code);
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
            error: function () {
            alert("！！！");
            },
            success: function (data) {
                window.location.href = 'simulation';
            }
        });
    });
}

//请求用户的信息
function getStorang(number){

}