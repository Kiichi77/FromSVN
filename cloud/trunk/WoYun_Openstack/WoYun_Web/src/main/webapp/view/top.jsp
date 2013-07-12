<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		var theNewWin = window.open('update_pwd.jsp?carryCustomerId=1', 'newwindow', 'height=180, width=205, top=160, left=410, toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no');
// 		$('#dd').dialog({
// 			buttons:[{
// 				text:'保存',
// 				iconCls:'icon-ok',
// 				handler:function(){
// 					alert('修改密码成功');
// 					$('#dd').dialog('close');
// 				}
// 			},{
// 				text:'取消',
// 				handler:function(){
// 					$('#dd').dialog('close');
// 				}
// 			}]
// 		});
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:90px; border:2px solid #C44012;background: #DD4814 url(../images/header_stripes.png) repeat-x top left;">
		<h1 class="brand clearfix"><a href="#">OpenStack控制台</a></h1>
		<div id="user_info">
			<a>当前用户: Admin</a> 
			<a href="#"  onclick="open1();">修改密码</a>
			<a href="<%=realPath%>/login.jsp" target="_top">退出</a>
		</div>
	</div>
	<div id="themains" data-options="region:'center'" title=""  style="overflow:hidden;padding:10px;margin-top: 40px;">
	</div>
</body>
</html>