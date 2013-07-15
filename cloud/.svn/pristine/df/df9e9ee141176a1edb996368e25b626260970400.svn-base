<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>采购订购单详情</title>
</head>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/aftersale_detail_manage_view.js"></script>
<script type="text/javascript" src="controller/aftersale_detail_manage_controller.js"></script>
<script type="text/javascript">  
var contractOrderId = '<%=request.getParameter("contractOrderId")%>';
var ctx = '${ctx}';	
</script>

<body class="easyui-layout">
<!-- 
	<div id="datagridsearch">
		产品名称：<input id="purchaseOrderNumber" name="purchaseOrderNumber" class="easyui-validatebox" style="width:150px"></input>
		<a href="javascript:void(0);" onclick="search();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	 -->
	<div data-options="region:'center'" title="采购订单详情" style="overflow:hidden;">
		<div>
		  <table id="tt"></table>
		</div>
	</div>
		 <!-- 采购订单详情 -->
	<div id="purchaseDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="采购订单详情" style="background:#fafafa;padding:20px;width:780px;height:380px;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 260px;">
			<table id="purchaseOrder"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideDiv();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
	<!-- 配置机型详情 -->
	<div id="configDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="采购订单详情" style="background:#fafafa;padding:20px;width:900px;height:500px;" iconCls="icon-add">
	  <div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;">
		<div title="基本配置单元" data-options="tools:'#p-tools'">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
			</div>
		</div>
		<div title="同系列通用单元" data-options="tools:'#p-tools'">
			<div id="menu2" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
			<table id="t2"></table>
			</div>
		</div>
	    <div title="全通用单元" data-options="tools:'#p-tools'" >
			<div id="menu3" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		<table id="t3"></table>
			</div>
		</div>
	</div>
	</div>
</body>
</html>