<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realPath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
	session.setAttribute("realPath", realPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href='<%=realPath %>css/bootstrap.min.css'
	media='screen' rel='stylesheet' />
<link href='<%=realPath %>css/ubuntu.css'
	media='screen' rel='stylesheet' />
<jsp:include page="view/head.jsp"></jsp:include>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="view/resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="view/resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="view/resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="view/resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="view/resources/scripts/jquery.wysiwyg.js"></script>

<script type="text/javascript"> 
$(function() {
	<%String loginEnd = (String) session.getAttribute("msg");
				if ("pwdError".equals(loginEnd)) {%>
		alert("登录失败,账号或密码错误，请重新登陆");
	<%} else if ("noProject".equals(loginEnd)) {%>
		alert("登录失败,该账号不属于任何项目下，请重新登陆");
	<%} else if ("longinEcxp".equals(loginEnd)) {%>
		alert("登录出现异常,请稍后登陆或与管理员联系");
	<%}%>
});

$(document).ready(function(){
	$('#id_password').bind('keyup', function(event){
	   if (event.keyCode=="13"){
		   userloginfun();
	   }
	});
});

//登陆验证的方法
function userloginfun() {
	var usernames = jQuery("#id_username")[0].value;
	var userpwds = jQuery("#id_password")[0].value;
	if (null == usernames.trim() || "" == usernames.trim()) {
		alert("用户登陆账号不能为空!");
		return;
	}
	if (null == userpwds.trim() || "" == userpwds.trim()) {
		alert("用户登陆密码不能为空!");
		return;
	}

	$('#firstForm').form('submit', {
		url : ctx + 'user/user/user!login.action',
		success : function(data) {
			data = eval('(' + data + ')');
			if(data!=null){
				if (data.msg == 'passworderror') {
					alert("请确认帐号密码.登录失败!");
					window.location = ctx + "login.jsp";
				} else if (data.msg == 'ok') {
					window.location = ctx + "index.jsp";//"index.jsp";
				}
			}else{
				alert("登陆错误，请联系管理员！");
			}
		}
	});
}		
</script>

<%
session.removeAttribute("login");
%>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>登录</h1>
    <!-- Logo (221px width) -->
    <a href="http://www.kxcomm.com.cn/"><img id="logo" src="view/resources/images/logo.png" alt="Simpla Admin logo"  width="220px"/></a> </div>
  <!-- End #logn-top -->
  <div id="login-content">
  
   <form id="firstForm" action="user/user/user!login.action" method="POST">
      <p>
        <label for="id_username">用户名：</label>
        <input class="text-input" type="text"  name="model.accouont" id="id_username"/>
      </p>
      <div class="clear"></div>
      <p>
        <label for="id_password">密码：</label>
        <input class="text-input" type="password"  name="model.password" id="id_password" />
      </p>
      <div class="clear"></div>
      <p id="remember-password">
       
      <div class="clear"></div>
      <p>
        <input class="button" type="button" value="登录"  onclick="userloginfun();"/>
      </p>
    </form>
  </div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->

	
</body>

</html>
