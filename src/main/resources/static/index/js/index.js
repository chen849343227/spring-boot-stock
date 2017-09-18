$(document).ready(function () {
    $('#login').click(function () {
        $(".login-modal").toggle();
    })
});

$("#loginBtn").on('click', function (e) {
    var phoneNumber = $("#exampleInputPhone").val();
    var password = $("#exampleInputPassword").val();
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/web/account/login",
        dataType: "json",
        data: {
            phoneNumber: phoneNumber,
            password: password
        },
        error: function (data) {
            console.log(data);
            alert("请求失败，网络异常" + data.message);
        },
        success: function (data) {
            console.log(data);
            /**
             * {
                 *   "isSuccess": false,
                 *   "code": 1,
                 *   "message": "登录失败，请稍后重试",
                 *   "errorResponse": {
                 *      "code": 10005,
                 *      "message": "用户不存在"
                 *      },
                 *   "response": null
                 *  }
             */
            if (data.isSuccess) {
                window.location.href = 'consult';
                window.sessionStorage.setItem('user', data.response.phone);
            } else {
                console.log(data.errorResponse.message);
                alert(data.errorResponse.message);
            }
            console.log(data);
        }
    });
});

// $("#signUp").on('click', function () {
//     window.location.href = 'signUp';
// });

$("#forget-password").on('click', function () {
    window.location.href = 'forget';
});
