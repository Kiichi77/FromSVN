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
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/aftersale_detail_manage_view.js"></script>
<script type="text/javascript">  
</script>

<body class="easyui-layout">
	<div id="datagridsearch">
		产品名称：<input id="purchaseOrderNumber" name="purchaseOrderNumber" class="easyui-validatebox" style="width:150px"></input>
		<a href="javascript:void(0);" onclick="search();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a href="javascript:void(0);" onclick="alert('确认收货')" class="easyui-linkbutton"  style="float: right;">确认收货</a>
	</div>
	
	<div data-options="region:'center'" title="采购订单详情" style="overflow:hidden;">
			<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;height:700px;">
	           <div title="采购订单1" data-options="tools:'#p-tools'">
	            <table id="tt1"></table>
	           </div>
	           <div title="采购订单2" data-options="tools:'#p-tools'">
	            <table id="tt2"></table>
	           </div>
	           <div title="采购订单3" data-options="tools:'#p-tools'">
	            <table id="tt3"></table>
	           </div>
	        </div>
	        <div id="tab-tools">
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="alert('确认收货')">确认收货</a>
		<a href="aftersale_manage.jsp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'" >返回</a>
		</div>
	</div>
	</div>
</body>
</html>