<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>浮动IP管理</title>
<script type="text/javascript" src="controller/woyun_publicNetworkMapping_controller.js"></script>
<script type="text/javascript" src="view/woyun_publicNetworkMapping_view.js"></script>
</head>
<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	
	<div id="themains" data-options="region:'center'" title="浮动IP管理"  style="overflow:hidden;padding:10px;margin-top: 40px;">
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:250px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 450px;height:250px;">
	    	<div id="title" style="width: 450px;height:80px;float: left;">
	    		<h2>配给浮动IP</h2>
				<hr>
	    	</div>
	    	<div id="thevmleft" style="width: 210px;height:100px;float: left;">
		        <div>
		            <label for="vmNames">浮动IP:</label><br>
		           	<input id="floatIP" class="easyui-validatebox" type="text" style="width:150px;"  name="ip" readonly="readonly" data-options="required:true" />  
		        </div><br>
		         <div>
		            <label for="userInfo">运行实例:</label><br>
		            <select  id="instance" name="serverId" style="width:150px;" data-options="required:true">
				     </select>
		        </div>
	        </div>l
	        <div id="thevmright" style="width: 210px;height:100px;float: right;">
	        	<h4>详 述</h4>
				<h5>从浮动IP集分配一个浮动IP</h5>
	        </div>
	    </form>
	    <div align="right" style="width: 450px;">
	    	 <a href="javascript:void(0);" onclick="addServerFloat()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
	    	 <a href="javascript:void(0);" onclick="cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	    </div>
	</div>
	
	<div id="Allocation" style="background:#fafafa;padding:20px;width:460px;height:250px;display: none;z-index: 9999;border: solid gray 1px;">
    	<div id="Allocation_title" style="width: 450px;height:80px;float: left;">
    		<h2>分配浮动IP</h2>
			<hr>
    	</div>
    	<div id="Allocation_thevmleft" style="width: 210px;height:150px;float: left;">
	        <div>
	            <label for="vmNames">储备池:</label><br>
	           	<select  id="Allocation_pools" name="serverId" style="width:150px;" data-options="required:true">
			     </select>  
	        </div><br>
        </div>
        <div id="Allocation_thevmright" style="width: 210px;height:150px;float: right;">
        	<h4>详 述</h4>
			<h5>从浮动IP集分配一个浮动IP</h5>
        </div>
	    <div align="Allocation_right" style="width: 450px;">
	    	 <a href="javascript:void(0);" onclick="createIp()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">分配ip</a>
	    	 <a href="javascript:void(0);" onclick="cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	    </div>
	</div>
	<table id="virtualMachineInfo"></table>
	</div>
</body>
</html>