$(function () {
    $.ajax({
        url: '/stock/hkall',
        type: 'POST',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        data: {
            type: 1,
            page: 1
        },
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
        console.log(stock.response.result.data);
        var stocks = stock.response.result.data;
        $.each(stocks, function() {
            /*console.log(this.name);
            console.log(this.eps);*/
            $('#hsi-stock').append("<tr><td><a id='stock_id' onclick='oncli(this)'>"+this.symbol+"</a></td><td>"+this.name+"</td><td class='up'>"+this.buy+"</td><td class='up'>"+this.changepercent+"%</td><td class='up'>"+this.pricechange+"</td><td>"+this.stocks_sum+"</td><td>"+this.amount+"</td><td class='text-center'><a>模拟交易</a></td></tr>");
            $('#hsi-stock1').append("<tr><td>"+this.symbol+"</td><td>"+this.name+"</td><td>"+this.dividend+"</td><td>"+this.stocks_sum+"</td><td>"+this.eps+"</td><td>"+this.volume+"</td><td>"+this.stocks_sum+"</td><td><a>模拟交易</a><a><span></span>自选</a></td></tr>");
            $('#hsi-stock2').append("<tr><td>"+this.symbol+"</td><td>"+this.name+"</td><td>"+this.dividend+"</td><td>"+this.stocks_sum+"</td><td>"+this.eps+"</td><td>"+this.volume+"</td><td>"+this.stocks_sum+"</td><td><a>模拟交易</a><a><span></span>自选</a></td></tr>");
        });
    }
});

/*定义oncli函数，请求个股详细信息*/
function oncli(stockId) {
    $(function () {
        $.ajax({
            url: '/stock/hk',
            type: 'POST',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            data: {
                num: stockId.innerHTML
            },
            error: function () {
            alert("！！！");
            },
            success: function (data) {
            console.log(data);
            }
        });
    });
}