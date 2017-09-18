$().ready(function(){
    warnMain=$warnMain.detach();
    content=$('#window').detach();
});
/***** 声明变量 *****/
var $userId=$("#userId"),
    $phoneNumber=$("#phoneNumber"),
    $phoneCode=$("#phoneCode"),
    $userPWD=$("#userPWD"),
    $getPhoneCode=$("#getPhoneCode");
var $warnMain=$(".warnMain"),
    $warmContent=$(".warnContent");
var warnMain;
var phoneNumber=0;
/***** 函数事件 *****/

//用户昵称
$userId.on('focus',function(){
    if($("#inputUser")){
        warnMain=$warnMain.detach();
    }
});
$userId.on('blur',function(){
    var thisLeft=this.getBoundingClientRect().left+this.offsetWidth;
    var thisTop=this.getBoundingClientRect().top;
    console.log("正在检验用户ID规范性...");
    if(this.value==""||this.value==null){
        console.log(thisLeft+"/////"+thisTop);
        $(".main").prepend(warnMain);
        $warnMain.attr("id","inputUser");
        $warmContent.text("用户名不符合规范");
        $warnMain.css({
            "left":thisLeft,
            "top":thisTop,
            "display":"block"
        });
    }
});

//填写手机号码
$phoneNumber.on('keyup',function(){
    console.log("手机号码在更改");
    phoneNumber=this.value;
    console.log(phoneNumber);
    if (getStringLen(phoneNumber)===11) {
        $getPhoneCode.removeClass("disable");
    }else{
        $getPhoneCode.addClass("disable");
    }
});

//发送验证码
$getPhoneCode.on('click',function(){
    var timer=60;
    var $this=$(this);
    var phoneNumber = $("#phoneNumber").val();
    $.ajax({
        type: 'POST',
        url: '/web/account/getVerifyCode',
        dataType: 'json',
        data:{
            phone: phoneNumber
        },
        error: function (data) {
            console.log(data.message);
            alert("请求失败，网络异常" + data.message);
        },
        success: function (data) {
            $this.text("发送成功 ( "+timer+" )");
            $this.addClass("disable");
            $phoneNumber.attr("disabled",true);
            var time=setInterval(function(){
                timer=timer-1;
                $this.text("发送成功 ( "+timer+" )");
                if(timer==0){
                    $this.text("发送验证码").removeClass("disable");
                    $phoneNumber.attr("disabled",false);
                    clearInterval(time);
                }
            },900);
            bool=true;
        }
    });
});

//填写密码
$userPWD.on('focus',function(){
    if($("#inputPWDr")){
        warnMain=$warnMain.detach();
    }
});

//开始注册
$("#sign_up").on('click',function(e){
    console.log("正在进行注册...");
    e.preventDefault();
    var userId=$userId.val(),
        phoneNumber=$phoneNumber.val(),
        phoneCode=$phoneCode.val(),
        userPWD=$userPWD.val();
    if(($("#inputUser")==true)||userId==""||userId==null){
        var userWarn=warnMain.clone();
        userWarn.attr('id',"userWarn");
        userWarn.css({
            "left":$userId[0].getBoundingClientRect().left+$userId[0].offsetWidth,
            "top":$userId[0].getBoundingClientRect().top,
            "display":"block"
        });
        userWarn.find("em").text("昵称未填写");
        $(".main").prepend(userWarn);
    };
    if(phoneNumber==""||phoneNumber==null){
        var pnWarn=warnMain.clone();
        pnWarn.attr('id',"pnWarn");
        pnWarn.css({
            "left":$phoneNumber[0].getBoundingClientRect().left+$phoneNumber[0].offsetWidth,
            "top":$phoneNumber[0].getBoundingClientRect().top,
            "display":"block"
        });
        pnWarn.find("em").text("手机号未填写");
        $(".main").prepend(pnWarn);
    };
    if(phoneCode==""||phoneCode==null){
        var pcWarn=warnMain.clone();
        pcWarn.attr('id',"pcWarn");
        pcWarn.css({
            "left":$phoneCode[0].getBoundingClientRect().left+$phoneCode[0].offsetWidth,
            "top":$phoneCode[0].getBoundingClientRect().top,
            "display":"block"
        });
        pcWarn.find("em").text("验证码未填写");
        $(".main").prepend(pcWarn);
    };
    if(userPWD==""||userPWD==null){
        var pwdWarn=warnMain.clone();
        pwdWarn.attr('id',"pwdWarn");
        pwdWarn.css({
            "left":$userPWD[0].getBoundingClientRect().left+$userPWD[0].offsetWidth,
            "top":$userPWD[0].getBoundingClientRect().top,
            "display":"block"
        });
        pwdWarn.find("em").text("密码未填写");
        $(".main").prepend(pwdWarn);
        return false;
    };
    $.ajax({
        async:true,
        type:"post",
        url:"/web/account/register",
        data:{
            username: phoneNumber,
            password: userPWD,
            code: phoneCode
        },
        dataType:"json",
        success:function(data){
            alert(data.message);
        },
        error:function(data){
            alert("ajax请求失败，网络异常"+data);
        }
    })
});


/***** 模式窗口 *****/
var modal=(function(){
    var $window=$(window);
    var $modal=$('<div class="modal"/>');
    var $content=$('<div class="modal-content"/>');
    var $close=$("#closeWindow");
    $modal.append($content);
    $close=$("#closeWindow");
    $close.on('click',function(e){
        console.log("点击触发");
        e.preventDefault();
        modal.close();
    });
    return {
        center:function(){
            var top=Math.max($window.height()-$modal.outerHeight())/2;
            var left=Math.max($window.width()-$modal.outerWidth())/2;
            $modal.css({
                'position':"absolute",
                'top':top,
                'left':left,
                'text-align':'center',
                'background-color':'#E5E5E5',
                'z-index':"111"
            });
            content.css("display","block");
            console.log(content);
        },
        open:function(settings){
            var $div=$('<div class="moda"></div>');
            $div.css({
                "position":"absolute",
                "width":"100%",
                "height":"100%",
                "top":0,
                'background-color':'rgba(255,255,255,0.2)',
                'z-index':"100"
            });
            $div.appendTo("body");
            $content.empty().append(settings.content);
            $modal.css({
                width:settings.width||'auto',
                height:settings.height||'auto'
            }).appendTo('body');
            modal.center();
            $(window).on('resize',modal.center);
        },
        close:function(){
            $(".moda").remove();
            $content.empty();
            $modal.detach();
            window.close();
            $(window).off('resize',modal.center);
        }
    }
})();

function getStringLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
            len += 2;
        }
        else {
            len += 1;
        }
    }
    return len;
}