<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>采购订购单</title>
</head>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/orders_controller.js"></script>
<script type="text/javascript" src="view/orders_view.js"></script>
<script type="text/javascript">  
</script>
<script type="text/javascript">
	var muluhejia = 0;
	var zhekouhoujia = 0;
	var zhekouhouxianchang = 0;
	var hejijia = 0;
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="采购订单总览" style="overflow:hidden;">
		 <table id="tt">
	     </table>
	</div>
	<!-- 采购订单弹出层 -->
	<div id="executeDIVVV"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="采购订单" style="background:#fafafa;padding:20px;width:805px;height:350px;">
		<table id="caigoudan" style="height:310px;overflow-y: auto;"></table><br>
		<div align="right" style="width: 700px;">
	    	 <a href="#" onclick="hideexecuteDIVVV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
   		</div>
	</div>
	<!-- 采购订单弹出层 -->
	<div id="executeDIVInfos"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="机型配置" style="background:#fafafa;padding:20px;width:405px;height:350px;">
		<table id="jixingpeizhi" style="height:310px;overflow-y: auto;"></table><br>
		<div align="right" style="width: 350px;">
	    	 <a href="#" onclick="hideexecuteDIVInfos();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
   		</div>
	</div>
		<!-- 机型配置的详情 -->
<!-- 	<div id="numberDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="机型配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a>
		</div>
	</div> -->
		<!-- 机型配置的详情 -->
	<div id="numberDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="机型配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
		</div>
	</div>
			<!-- 配置详情弹出层 -->
	<div id="configDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
		    <table id="t2"></table>
		    <table id="t3"></table>
		    <table id="t4"></table>
			</div>
		</div>
		<br>
		<div align="right" style="width: 1200px;">
		    <a href="#" onclick="hideConfigDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
</body>
</html>