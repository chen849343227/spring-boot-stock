window.onload=function(){
    console.log("页面加载完毕，onload启动;");
    //updateImg();
    content=$('#window').detach();
    console.log("onload执行完毕;");
}
/***** 获取字符串长度函数 *****/
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
/***** 删除部分元素 *****/
/***** 声明全局变量 *****/
var bool=false;
var phone;
var userName;
/***** 缓存要用到的DOM对象 *****/
/*** 输入框 ***/
var userId=$("#userId");
var verifyImg=$("#verify-img");
var verifyPhone=$("#verify-phone");
var newPassWord=$("#newPassWord");
var repeatPassWord=$("#repeatPassWord");
/*** 按钮 ***/
var submitOne=$("#submit-next_1");
var submitTwo=$("#submit-next_2");
var submitThree=$("#submit-next_3");
var vcodeImgChange=$(".vcode-img-change");
var getVerifyCode=$("#getVerifyCode");
var $close=$("#closeWindow");
/*** 提示信息 ***/
var userIdError=userId.next();
var vcodImgError=verifyImg.nextAll().last();
var getVerifyCodeError=getVerifyCode.nextAll().last();
var newPWError=newPassWord.next();
var repeatNPWError=repeatPassWord.next();
/*** 反馈信息 ***/
var phoneNumber=$("#phoneNumber");
var findId=$("#findId");
/** 图片验证码 **/
var vcodImg=verifyImg.next();
/* 模块 */
var step_1=$("#step_1");
var step_2=$("#step_2");
var step_3=$("#step_3");

/***** focus\blur事件 *****/
var inputText=$(":text");
var inputTextLen=inputText.length;
console.log(inputTextLen);
for(var i=0;i<inputTextLen;i++){
    inputText[i].addEventListener('focus',function(){
        this.style.borderColor="#ddd";
        $(this).nextAll().last().css({
            "display":"none"
        })
    });
    inputText[i].addEventListener('blur',function(){
        if(this.value==""||this.value==null){
            this.style.borderColor="red";
            $(this).nextAll().last().css({
                "display":"inline"
            })
        }
    });
};
/* 重置密码 */
newPassWord[0].addEventListener('focus',function(){
    this.style.borderColor="#ddd";
    $(this).nextAll().last().css({
        "display":"none"
    })
});
newPassWord[0].addEventListener('blur',function(){
    var string=this.value;
    var stringLen=getStringLen(string);
    console.log("字符数："+stringLen);
    if(this.value==""||this.value==null||stringLen<6){
        this.style.borderColor="red";
        $(this).nextAll().last().css({
            "display":"inline"
        })
    }
});
repeatPassWord[0].addEventListener('focus',function(){
    this.style.borderColor="#ddd";
    this.value="";
    $(this).nextAll().last().css({
        "display":"none"
    })
});
repeatPassWord[0].addEventListener('blur',function(){
    var thisValue=this.value;
    var newPW=newPassWord[0].value;
    var bool=false;
    if(this.value==""||this.value==null){
        this.style.borderColor="red";
        $(this).nextAll().last().css({
            "display":"inline"
        })
    }
    if(newPW===thisValue){
        bool=true;
    }else{
        this.style.borderColor="red";
        $(this).nextAll().last().text("与上一次输入不相符")
            .css({"display":"inline"});
    }
});

/***** 点击事件函数 *****/
//更新图片
vcodeImgChange.on('click',function(){
    console.log("验证码图片切换触发;");
    //更新图片
    updateImg();
});

//发送手机验证码
getVerifyCode.on('click',function(){
    console.log("正在发送手机验证码...")
    var timer=60;
    getVerifyCode.text("发送成功 ( "+timer+" )").css("opacity","0.6");
    getVerifyCode.attr('disabled',"true");

    getPhoneVerifyCode();

    var time=setInterval(function(){
        timer=timer-1;
        getVerifyCode.text("发送成功 ( "+timer+" )");
        if(timer==0){
            clearInterval(time);
            getVerifyCode.text("获取验证码").css("opacity","1");
            getVerifyCode.removeAttr("disabled");
        }
    },900)
    bool=true;
});

/***** 第一步 *****/
$(submitOne).on('click',function(e){
    console.log("第一步正在执行...");
    e.preventDefault();
    var userIdval=userId.val();
    var string=verifyImg.val();
    if (userIdval==""||userIdval==null) {
        userId.css({"borderColor":"red"});
        userIdError.css({"display":"inline"});
    }else if(string==""||string==null){
        verifyImg.css({"borderColor":"red"});
        vcodImgError.css({"display":"inline"});
    }else if(string.toLowerCase()!='5dxc'){
        verifyImg.css({"borderColor":"red"});
        vcodImgError.css({"display":"inline"});
    }
    else{
        console.log("正在进行账号验证...");
        ajaxOne(userIdval);
    }

});

/***** 第二步 *****/
$(submitTwo).on('click',function(e){
    console.log("第二步正在执行...");

    e.preventDefault();
    var string=verifyPhone.val();
    if(bool===false){
        getVerifyCodeError.text("请先获取验证码")
            .css({"display":"inline"});
        return false;
    }else{
        getVerifyCodeError.text("验证码有误");
    }
    if (string==""||string==null) {
        verifyPhone.css({"borderColor":"red"});
        getVerifyCodeError.css({"display":"inline"});
    }else{
        console.log("正在进行手机验证...");
        ajaxTwo(string);
    }

});

/***** 第三步 *****/
$(submitThree).on('click',function(e){
    console.log("第三步正在执行...");

    e.preventDefault();
    var newPW=newPassWord.val();
    var repeatPW=repeatPassWord.val();
    var string=getStringLen(newPW);
    if (string<6) {
        newPassWord.css({"borderColor":"red"});
        newPWError.css({"display":"inline"});
        return false;
    }
    if(newPW!=repeatPW){
        repeatPassWord.css({"borderColor":"red"});
        repeatNPWError.css({"display":"inline"});
        return false;
    }else{
        console.log("正在上传新密码...");
        ajaxThree(repeatPW);
    }
});
/***** ajax请求 *****/
function ajaxOne(user){
    $.ajax({
        async:true,
        type:"post",
        url:"",
        data:"userid="+user,
        dataType:"json",
        success:function(data){
            phone=data.phone;
            console.log("账号验证通过："+data);
            callBackOne();
        },
        error:function(data){
            alert("ajax请求失败，网络异常"+data);
        }

    })
};
function ajaxTwo(phoneCode){
    $.ajax({
        async:true,
        type:"post",
        url:"",
        data:"code="+phoneCode,
        dataType:"json",
        success:function(data){
            var code = data.status;
            if(code==200){
                userName=data.name;
                console.log("账号验证通过："+data);
                callBackTwo();
            }else{
                console.log("账号验证未通过："+data);
                getVerifyCodeError.css('display','inline');
            }
        },
        error:function(data){
            alert("ajax请求失败，网络异常"+data);
        }

    })
};
function ajaxThree(newpassword){
    $.ajax({
        async:true,
        type:"post",
        url:"",
        data:"newpwd="+newpassword,
        dataType:"json",
        success:function(data){
            var code = data.status;
            if(code==200){
                console.log("密码修改成功："+data);
                modal.open({content:content,width:440,height:200});
            }else{
                console.log("密码修改失败："+data);
            }
        },
        error:function(data){
            alert("ajax请求失败，网络异常"+data);
        }

    })
};

function updateImg(){
    var img = document.getElementById("img");
    img.src =getRootPath()+"user/refurbishimg.do?date=" + new Date();
};
function getPhoneVerifyCode(){
    console.log("手机号："+phone);
    $.ajax({
        async:true,
        type:"post",
        url:"",
        data:"phone="+phone,
        dataType:"json",
        success:function(data){
            var code = data.status;
            if(code==200){
                console.log("已发送手机验证码："+data);

            }else{
                console.log("发送手机验证码异常："+data);
            }
        },
        error:function(data){
            alert("ajax请求失败，网络异常"+data);
            console.log(data);
        }

    })
};
/***** 请求完成后的回调函数 *****/
function callBackOne(){
    step_1.stop(true,true).animate({
        "marginLeft":"-300px",
        "opacity":"0"
    },300,function(){
        $(this).css("display","none");
    })
    step_2.css({
        "display":"block",
        "opacity":0
    }).stop(true,true).animate({
        "marginLeft":"0",
        "opacity":1
    });
    var dataPhone=phone;
    var newPhone=dataPhone.substring(3,7);
    phoneNumber.text(dataPhone.replace(newPhone,'****'));
    $(".mod-sub-list1").removeClass("list1-active");
    $(".mod-sub-list2").addClass("list2-active");
};
function callBackTwo(){
    step_2.stop(true,true).animate({
        "marginLeft":"-300px",
        "opacity":"0"
    },300,function(){
        $(this).css("display","none");
    })
    step_3.css({
        "display":"block",
        "opacity":0
    }).stop(true,true).animate({
        "marginLeft":"0",
        "opacity":1
    });
    findId.text(userName);
    $(".mod-sub-list2").removeClass("list2-active");
    $(".mod-sub-list3").addClass("list3-active");
};

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

/***** 获取绝对路径 *****/
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';
}