<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils" %>
<%@ include file="../view/head.jsp"%>
<%
String srealPath = session.getAttribute("realPath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>top</title>
<script type="text/javascript">
function open1(){
	var userId=<%=session.getAttribute(SessionUtils.USER_ID) %>;
	var theNewWin = window.open('update_user.jsp?id='+userId, 'newwindow', 'height=480px, width=400px, top=160, left=410, toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no');
}	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:90px; border:2px solid #C44012;background: #DD4814 url(../images/header_stripes.png) repeat-x top left;">
		<h1 class="brand clearfix"><a href="#">OpenStack控制台</a></h1>
		<div id="user_info">
		    <a href ="main.jsp" target="main">我的工作区 </a>
			<a>当前用户: <%=session.getAttribute("username") %></a> 
		    <a href="#"  onclick="open1();">修改信息</a>
			<a href="<%=realPath%>login.jsp" target="_top">退出</a>
		</div>
	</div>
	<div id="themains" data-options="region:'center'" title=""  style="overflow:hidden;padding:10px;margin-top: 40px;">
	</div>
</body>
</html>