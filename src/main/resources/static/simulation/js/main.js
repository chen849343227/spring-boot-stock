$().ready(function () {
    var code = window.sessionStorage.getItem('code');
    init(code);
    var userName=window.sessionStorage.getItem('userName');
    if(userName==undefined){
        $("#userId").text('未登录');
    }else{
        $("#userId").text(userName);
    }
});

/**** 页面初始化 ***/
function init(code) {
    $.ajax({
        type: 'POST',
        url: '/web/account/getVerifyCode',
        dataType: 'json',
        data: {
            code: code
        },
        error: function (data) {
            console.log(data.message);
            alert("请求失败，网络异常" + data.message);
        },
        success: function (data) {

        }
    })
};

//初始化代码


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
