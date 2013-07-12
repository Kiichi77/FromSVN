/**
 * 登陆界面的相应功能
 * wirterMan：lizl
 * time:2012-08-01
 * */

//刷新验证码方法
function chgUrl(url) {   
     var timestamp = (new Date()).valueOf();   
         urlurl = url.substring(0, 17);   
        if ((url.indexOf("?") >= 0)) {   
            urlurl = url + "&t=" + timestamp;   
        } else {   
            urlurl = url + "?t=" + timestamp;   
        }   
        return urlurl;   
}

//重新获得验证码的方法
function changeVerificationCode() {
	 var imgSrc = $("#verificationCode");   
     var src = imgSrc.attr("src");   
     imgSrc.attr("src", chgUrl(src));   
}

//用户登陆的方法
function login() {
	var uname = $("#useraccount").val();
	var pwd = $("#userpasswd").val();
	var verificationCode = $("#userVerificationCode").val();
	if (null == uname || "" == uname) {
		alert("用户账号不能为空");
		return;
	} else if(null == pwd || "" == pwd) {
		alert("用户密码不能为空");
		return;
	} else if(null == verificationCode || "" == verificationCode) {
		alert("验证码不能为空");
		return;
	}
	$.ajax({
		  type: "post",
		  url: 'user/user/user!login.action',
		  dataType : "json",
		  data: "model.accouont="+uname+"&admin&model.password="+pwd+"&verificationCode="+verificationCode+"",
		  success: function(data){
				if("verificationCodeError"==data.msg) {
					Ext.MessageBox.alert("信息","验证码输入错误，登录失败");
					$("#userpasswd").val("");
					changeVerificationCode();
					return;
				} else if("error"==data.msg){
					Ext.MessageBox.alert("信息","账号或密码输入错误，登录失败");
					changeVerificationCode();
					$("#userpasswd").val("");
					return;
				}else if("usererror"==data.msg){
					Ext.MessageBox.alert("信息","账号输入错误，登录失败");
					changeVerificationCode();
					$("#userpasswd").val("");
					return;
				}else if("passworderror"==data.msg){
					Ext.MessageBox.alert("信息","密码输入错误，登录失败");
					changeVerificationCode();
					$("#userpasswd").val("");
					return;
				}
				userLoginStats = false;
				window.location.href = "jsp/menu/main.jsp";
		  }
	}); 
}
