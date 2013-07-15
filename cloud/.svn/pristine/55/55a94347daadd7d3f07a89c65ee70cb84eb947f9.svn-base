<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript"
	src="controller/salesOrderManager_controller.js"></script>
<script type="text/javascript" src="view/salesOrderManager_view.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
	var muluhejia = 0;
	var zhekouhoujia = 0;
	var zhekouhouxianchang = 0;
	var hejijia = 0;
</script>
<body class="easyui-layout">
	<div id="datagridsearch">
	        内部合同编号：<input id="kwords" name="kword" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="querySaleOrder();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查 询</a>
	</div>
	<div data-options="region:'center'" title="销售订单管理" style="overflow: hidden;">
		<table id="tt"></table>
	</div>
	<!-- 详情弹出层 -->
	<div id="executeDIV"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="订单详情" style="background:#fafafa;padding:20px;width:705px;height:370px;">
		<div style="overflow-y: scroll; height: 260px;">
			<table id="execute"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
			<a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
	<!-- 合同执行通用单元和数量配置弹出层 -->
	<div id="numberDIV" style="background: #fafafa; padding: 20px; width: 650px; height: 280px; display: none; border: solid gray 1px; margin-top: 100px; overflow-y: auto;">
		<div style="overflow-y: scroll; height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
			<a href="#" onclick="hideNumberDIV();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		</div>
	</div>
	
	<!-- 采购订单弹出层 -->
	<div id="executeDIVVV"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="采购订单" style="background:#fafafa;padding:20px;width:805px;height:350px;">
		<table id="caigoudan" style="height:310px;overflow-y: auto;"></table><br>
		<div align="right" style="width: 640px;">
	    	 <a href="#" onclick="hideexecuteDIVVV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
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

<script type="text/javascript">
	//下拉选择框数据加载  采购人员,我在这里加的原因是我不想每次弹都发一个请求.
	var sel3 = $("#purchase");
	sel3.empty();//清除select中的 option
	$.post(ctx + '/user/user/user!queryAllPurchase.action', function(data) {
		var datas = eval("(" + data + ")");
		if (null != datas && "" != datas) {
			for ( var i = 0; i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].userName).val(
						datas.rows[i].id);
				sel3.append(opt);
			}
		}
	});
</script>
</html>