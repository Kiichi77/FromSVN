<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/configDetails_view.js"></script>
<script type="text/javascript">
var configId = '<%=request.getParameter("configId")%>';
var qoutaionId = '<%=request.getParameter("qoutaionId")%>';
var status = '<%=request.getParameter("status")%>';
var muluhejia = 0;
var zhekouhoujia = 0;
var zhekouhouxianchang = 0;
var hejijia = 0;
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="" style="overflow:hidden;">	
	<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;">
		<div title="机型配置详情" data-options="tools:'#p-tools'">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
		    <table id="t2"></table>
		    <table id="t3"></table>
		    <table id="t4"></table>
			</div>
		</div>
<!-- 		<div title="同系列通用单元" data-options="tools:'#p-tools'"> -->
<!-- 			<div id="menu2" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;"> -->
<!-- 			<table id="t2"></table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	    <div title="全通用单元" data-options="tools:'#p-tools'" > -->
<!-- 			<div id="menu3" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;"> -->
<!-- 		<table id="t3"></table> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
	<div id="tab-tools" style="margin-right: 20px;">
		<a href="checkDetails.jsp?qoutaionId=<%=request.getParameter("qoutaionId")%>&status=<%=request.getParameter("status")%>" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'" >返回</a>
	</div>
</div>
</body>
</html>