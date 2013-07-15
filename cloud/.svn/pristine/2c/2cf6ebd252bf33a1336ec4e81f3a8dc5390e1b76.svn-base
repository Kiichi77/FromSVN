<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公网映射</title>
<script type="text/javascript" src="controller/woyun_publicNetworkMapping_controller.js"></script>
<script type="text/javascript" src="view/woyun_publicNetworkMapping_view.js"></script>
</head>
<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	
	<div id="themains" data-options="region:'center'" title="公网映射管理"  style="overflow:hidden;padding:10px;margin-top: 40px;">
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:270px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 450px;height:270px;">
	    	<div id="thevmleft" style="width: 210px;height:250px;float: left;">
		        <div>
		            <label for="vmNames">内网IP:</label><br>
		            <input class="easyui-validatebox" type="text" name="nipname" style="width: 200px;" data-options="required:true"></input>
		        </div><br>
		         <div>
		            <label for="userInfo">内网端口:</label><br>
		            <input class="easyui-validatebox" type="text" name="ndkname" style="width: 200px;" data-options="required:true"></input>
		        </div><br>
		        <div>
		            <label for="models">外网IP:</label><br>
		            <input class="easyui-validatebox" type="text" name="wipname" style="width: 200px;" data-options="required:true"></input>
		        </div><br>
		        <div>
		            <label for="models">外网IP:</label><br>
		            <input class="easyui-validatebox" type="text" name="wdkname" style="width: 200px;" data-options="required:true"></input>
		        </div>
	        </div>
	        <div id="thevmright" style="width: 210px;height:250px;float: right;">
	        	<h4>详 述</h4>
				<h5>公网的IP映射</h5>
	        </div>
		    <div align="right" style="width: 450px;">
		    	 <a href="#" onclick="javascript:alert('添加成功');" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	 <a href="#" onclick="javascript:$('#addVmDiv').css('display','none');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    </div>
	    </form>
	</div>
	<span style="margin-top: 20px;">请选择一下查询条件查询使用量:</span>
		<div style="margin-top: 20px;">
			开始时间：<input id="starttime" class="easyui-datebox" required="required"></input>
			结束时间：<input id="endtime" class="easyui-datebox" required="required"></input>
			所属项目：<select id="cc" class="easyui-combobox" name="state" style="width:200px;" data-options="required:true">
				     	<option value="1" selected="selected">admin</option>
				     	<option value="2">client</option>
				     </select>
				     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查 询</a>
		</div><br>
		<table id="virtualMachineInfo"></table>
	</div>
</body>
</html>