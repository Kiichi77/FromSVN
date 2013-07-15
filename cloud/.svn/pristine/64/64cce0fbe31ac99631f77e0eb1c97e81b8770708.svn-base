<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/"; 
	session.setAttribute("realPath",realPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="view/head.jsp"></jsp:include>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Login – 沃云管理系统</title>

<link href='<%=realPath %>css/bootstrap.min.css'
	media='screen' rel='stylesheet' />
<link href='<%=realPath %>css/ubuntu.css'
	media='screen' rel='stylesheet' />


<link rel="shortcut icon" href="images/favicon.ico" />
<script type="text/javascript">
	$(function(){
		<% 
		String loginEnd = (String) session.getAttribute("msg");
		if("pwdError".equals(loginEnd)) {
		%>
				alert("登录失败,账号或密码错误，请重新登陆");
		<%
			}else if("noProject".equals(loginEnd)) {
		%>	
				alert("登录失败,该账号不属于任何项目下，请重新登陆");
		<%
			}else if("longinEcxp".equals(loginEnd)) {
		%>	
				alert("登录出现异常,请稍后登陆或与管理员联系");
		<%}%>
	});

	//登陆验证的方法
	function userloginfun() {
		var usernames = jQuery("#id_username")[0].value;
		var userpwds = jQuery("#id_password")[0].value;
		if(null == usernames.trim() || "" == usernames.trim()) {
			alert("用户登陆账号不能为空!");
			return;
		}
		if(null == userpwds.trim() || "" == userpwds.trim()) {
			alert("用户登陆密码不能为空!");
			return;
		}
	}
</script>
</head>
<body id="splash">
	<div class="container">
		<div class="row large-rounded">
			<div id="" class="login">
				<div class="modal-header">
					<h3>登录</h3>
				</div>
				<form id="firstForm" class="" action="user/user/user!login.action" method="POST">
					<div class="modal-body clearfix">
						<fieldset>
							<div class="control-group form-field clearfix">
								<label for="id_username">用户名称</label> 
								<div class="input">
									<input type="text" name="model.username" id="id_username" data-options="required:true"/>
								</div>
							</div>

							<div class="control-group form-field clearfix">
								<label for="id_password">密码</label> 
								<div class="input">
									<input type="password" name="model.password" id="id_password" />
								</div>
							</div>
						</fieldset>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary pull-right" onclick="userloginfun();">登录</button>
						<a href="resetpasswd.jsp">忘记密码</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
