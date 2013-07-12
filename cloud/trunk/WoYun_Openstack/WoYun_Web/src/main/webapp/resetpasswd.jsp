<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/"; 
	session.setAttribute("realPath",realPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Login – 沃云管理系统</title>

<link href='<%=realPath %>css/bootstrap.min.css'
	media='screen' rel='stylesheet' />
<link href='<%=realPath %>css/ubuntu.css'
	media='screen' rel='stylesheet' />


<link rel="shortcut icon" href="images/favicon.ico" />

</head>
<body id="splash">
	<div class="container">
		<div class="row large-rounded">
			<div id="" class="login">
				<div class="modal-header">

					<h3>重置密码</h3>
					<h5>请输入您的用户名和邮箱地址，系统会生成随机密码发送到您的邮箱。重置成功后，请登录系统及时修改您的密码。</h5>
				</div>
				<form id="" class="" action="login.jsp" method="POST">
					<div class="modal-body clearfix">

						<fieldset>

							<div class="control-group form-field clearfix">
								<label for="id_username">用户名</label> 
								<div class="input">
									<input type="text" name="username" id="id_username" />
								</div>
							</div>

							<div class="control-group form-field clearfix">
								<label for="id_password">邮箱地址</label> 
								<div class="input">
									<input type="text" name="mail" id="mail" />
								</div>
							</div>
						</fieldset>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary pull-right">确认重置</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
</html>
