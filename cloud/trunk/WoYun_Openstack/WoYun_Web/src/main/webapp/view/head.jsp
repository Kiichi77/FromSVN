<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<head>
<%
	String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/"; 
	session.setAttribute("realPath",realPath);
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>沃云管理系统</title>
	<link rel="shortcut icon" href="<%= realPath%>images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%= realPath%>jquery-easyui-1.3.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%= realPath%>jquery-easyui-1.3.1/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%= realPath%>jquery-easyui-1.3.1/themes/icon.css">
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/src/jquery.parser.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
	#user_info {
	color: white;
	margin: auto 0;
	position: absolute;
	top: 22px;
	right: 20px;
	z-index: 2;
	}
	#user_info > a {
	margin-left: 25px;
	font-size: 12px !important;
	color: white;
	}
	a {
	color: #08C;
	text-decoration: none;
	}
	h1.brand a {
	background: url(<%=session.getAttribute("realPath").toString()%>images/logo.png) top left no-repeat;
	display: block;
	float: left;
	width: 312px;
	height: 27px;
	text-indent: -9999px;
	margin-left: 30px;
	margin-bottom: 25px;
	}
	</style>
	<script type="text/javascript">
	var ctx = '${realPath}';
	</script>
</head>