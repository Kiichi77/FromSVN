<%@page import="cn.com.kxcomm.base.constants.BusinessConstants"%>
<%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<head>
<%
	String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/"; 
	String ctx = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	session.setAttribute("realPath",realPath);
	session.setAttribute("ctx",ctx);
	
	//获取当前用户的角色
   Long nowUserRoleId = ((Long) session.getAttribute(SessionUtils.USER_ROLE_ID));
   request.setAttribute("nowUserRole", nowUserRoleId); //当前用户角色
   request.setAttribute("roleSeller", BusinessConstants.role_seller);//销售
   request.setAttribute("roleFinancial", BusinessConstants.role_financial);//财务
   request.setAttribute("roleManager", BusinessConstants.role_manager);//经理
   request.setAttribute("rolePurchase", BusinessConstants.role_purchase);//采购
   request.setAttribute("roleAftermarket", BusinessConstants.role_aftermarket);//售后
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>报价与合同管理平台</title>
	<link rel="shortcut icon" href="<%= realPath%>images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%= realPath%>jquery-easyui-1.3.1/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%= realPath%>jquery-easyui-1.3.1/themes/icon.css">
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/src/jquery.parser.js"></script>
	<script type="text/javascript" src="<%= realPath%>jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>

	
<style type="text/css">
a:hover {
	text-decoration: none;
}

	label{
			width:120px;
			display:block;
			padding:5px;
	}
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
	var realCtx = '${ctx}';
	var nowUserRole = '${nowUserRole}';//当前用户角色
	var roleSeller='${roleSeller}';//销售
	var roleFinancial='${roleFinancial}';//财务
	var roleManager='${roleManager}';//经理
	var rolePurchase='${rolePurchase}';//采购
	var roleAftermarket='${roleAftermarket}';//售后
	</script>
</head>