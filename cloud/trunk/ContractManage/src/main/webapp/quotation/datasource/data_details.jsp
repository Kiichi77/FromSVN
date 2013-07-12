<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript">
var tocheckIds = '<%=request.getParameter("tocheckId")%>';
/***************DIV*****************/
//基本配置单元divID序列号
var accordionDivId1 = 0;
//同系列通用单元divID序列号
var accordionDivId2 = 0;
//全通用单元divID序列号
var accordionDivId3 = 0;
/***************column*****************/
//基本配置单元列模型序列号
var columnId1 = 0;
//基本配置单元列模型序列号
var columnId2 = 0;
//基本配置单元列模型序列号
var columnId3 = 0;
</script>
<script type="text/javascript" src="controller/details_controller.js"></script>
<script type="text/javascript" src="view/details_view.js"></script>
<body class="easyui-layout">
<div data-options="region:'center'" title="数据详情" style="overflow:hidden;">	
	<div class="easyui-tabs" data-options="tools:'#tab-tools'"  style="width:1800;height:500px;">
		<div title="基本配置单元" data-options="tools:'#p-tools'">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:hidden;">
			</div>
		</div>
		<div title="同系列通用单元" data-options="tools:'#p-tools'">
			<div id="menu2" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:hidden;">
			</div>
		</div>
	    <div title="全通用单元" data-options="tools:'#p-tools'" >
			<div id="menu3" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:hidden;">
			</div>
		</div>
	</div>
</div>
</body>
</html>