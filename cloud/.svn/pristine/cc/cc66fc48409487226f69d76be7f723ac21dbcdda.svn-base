<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>采购申请单</title>
</head>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/purchaseOrder_controller.js"></script>
<script type="text/javascript" src="view/purchaseOrder_view.js"></script>
<script type="text/javascript">  
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="采购申请单总览" style="overflow:hidden;">
		 <table id="tt"></table>
	</div>
	<div id="addDataInfoDiv" style="background:#fafafa;padding:20px;width:650px;height:280px;display: none;border: solid gray 1px;">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 650px;height:auto;">
	    	<div id="addthevmleft" style="width: 310px;height:240px;float: left;">
	    	    <div style="margin-left: 20px;margin-top:20px;"><font style="font-size: 13px;">采购单名称：</font>
		    	    <input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">开始时间：</font>
	    	    	<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">结束时间：</font>
	    	    	<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div align="right" style="width: 350px;">
		    	<a href="javascript:void(0);" onclick="addSave();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
   	 		</div>
   	 		<div id="rightDIV" style="width: 310px;height:240px;float: right;">
   	 			<div style="margin-left: 20px;margin-top:20px;">
	    	    	<font style="font-size: 13px;">销售订单：</font>
	    	    	<select id="shujuyuan" name="model.dataSource.id" style="width:160px;">
		    	    	<option value="1">1000100</option>
		    	    	<option value="2">1000101</option>
		    	    	<option value="2">1000102</option>
		    	    	<option value="3">1000103</option>
		    	    	<option value="4">1000104</option>
		    	    </select></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备注：</font>
	    	    	<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:false" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px">
	    	    	<font style="font-size: 13px;">采购单申请人：</font>
	    	    	<select id="shujuyuan" name="model.dataSource.id" style="width:160px;">
		    	    	<option value="1">垃圾</option>
		    	    	<option value="2">垃圾</option>
		    	    	<option value="2">了色</option>
		    	    </select>
		    	</div>
	    	    <br>
	      	<br/>
	    	    <br>
   	 		</div>
	   </form>
	</div>
</body>
</html>