<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("path",path);
	request.setAttribute("ctx",path);
	request.setAttribute("basePath",basePath);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IPMI后台管理登陆</title>
<meta name="author" content="HSX" />
<style>
.colTd1{ width:60px; height:32px; line-height:32px;}
.colTd3{ width:60px; height:32px; line-height:32px;}
.colTd2 input{ height:25px; line-height:22px; padding:2px 2px 2px 20px; border:1px solid #DEDEDC; width:180px;color:#666;}
.colTd2 input.ip1{ background:url(${ctx}/jsp/common/images/hysign_ic1.png) no-repeat 5px 6px;}
.colTd2 input.ip2{ background:url(${ctx}/jsp/common/images/hysign_ic2.png) no-repeat 5px 6px;}

.colTd4 input{ height:25px; line-height:22px; padding:2px; border:1px solid #DEDEDC; width:34px; margin-right:5px; color:#666;}
.colTd4 .notsee{ color:#666;}
.colTd4 a{ color:#0000FF; text-decoration:underline;}
.colTd4 a:hover{ color:#FF6600;}

.colTd6{ padding-top:10px; height:24px; line-height:24px;}
.colTd6 .submitBut{ color:#FFF; text-align:center; height:24px; line-height:24px; cursor:pointer; background:url(${ctx}/jsp/common/images/hysign_but.png) no-repeat; width:62px; border:none; margin-right:6px;}
.colTd6 span a{ color:#223B73;}
.colTd6 span a:hover{ color:#FF6600;}
</style>
<!--[if IE 6]><script>document.execCommand("BackgroundImageCache", false, true)</script><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/jsp/common/js/extjs4/resources/css/ext-all.css" /> 
<script type="text/javascript" src="${ctx}/jsp/common/js/extjs4/bootstrap.js"> </script> 
<script type="text/javascript" src="${ctx}/jsp/common/js/extjs4/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/jsp/common/js/charts/jquery.min.js"> </script>
<script type='text/javascript' language='JavaScript' src="${ctx}/jsp/user_manager/js/userLogin.js"></script>
</head>
<body style="background-image: url('${ctx}/jsp/common/images/back.jpg');">
	<div id="forms1" align="center" style="margin-top: 200px;">
		<table cellspacing="0" cellpadding="0" border="0" align="center" style="background-image: url('${ctx}/jsp/common/images/login.jpg');width: 500px;height: 230px;">
			<tr>
				<td><div><img src="${ctx}/jsp/common/images/noname.jpg" style="margin-top: 50px;"/></div></td>
				<td>
					<div>
						<table cellspacing="0" cellpadding="0" border="0" style="margin-top: 50px;">
								<tr>
								<td class="colTd1">帐&nbsp;&nbsp;号：&nbsp;</td>
								<td class="colTd2"><input type="text" maxlength="32" value="" class="ip1" name="username" id="useraccount" /></td>
								</tr>
								<tr>
								<td class="colTd1">密&nbsp;&nbsp;码：&nbsp;</td>
								<td class="colTd2"><input type="password" maxlength="18" class="ip2" name="password" id="userpasswd" /></td>
								</tr>
								<tr>
								<td class="colTd3">验证码：&nbsp;</td>
								<td class="colTd4"> <input type="text" name="rand" id="userVerificationCode" maxlength="4" /><img src="${ctx}/jsp/user_manager/verificationCode.jsp" id="verificationCode" /> <span class="notsee">看不清?</span><a onclick="changeVerificationCode()" href="#">换一张</a></td>
								</tr>
								<tr>
								<td class="colTd5">&nbsp;</td>
								<td class="colTd6"><input type="submit" value="登&nbsp;录" id="userLogin" name="button" class="submitBut" onclick="login()" /><span><a href="#">忘记密码？</a></span></td><!-- <span><a href="#">忘记密码？</a></span> -->
								</tr>
						</table>	
					</div>		
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
