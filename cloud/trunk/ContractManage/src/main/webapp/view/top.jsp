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
	<script type="text/javascript" src="<%= realPath%>view/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= realPath%>view/css/bootstrap.css">
<script type="text/javascript">
function open1(){
	var userId=<%=session.getAttribute(SessionUtils.USER_ID) %>;
	var theNewWin = window.open('update_user.jsp?id='+userId, 'newwindow', 'height=442px, width=342px, top=160, left=410, toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no');
}	
</script>
</head>
<body >
	<div class="navbar navbar-inverse navbar-fixed-top"  style="z-index: 9999;">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">KangXun OA Project</a>
          <div class="nav-collapse collapse" >
            <ul class="nav">
              <li class="active"><a href ="main.jsp" target="main">我的工作区 </a></li>
              <li><a href="<%=realPath%>flowChart/flowChart.jsp"  target="main">工作流程</a></li>
              <li><a href="#"  onclick="open1();"><%=session.getAttribute("username") %></a></li>
              <li><a href="<%=realPath%>login.jsp" target="_top">退出</a></li>
            </ul>
            
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
</body>
</html>