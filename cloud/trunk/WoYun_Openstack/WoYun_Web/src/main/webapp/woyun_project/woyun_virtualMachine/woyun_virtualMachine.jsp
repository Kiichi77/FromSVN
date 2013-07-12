<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String ctxPath = session.getAttribute("realPath").toString();
/* String path = request.getContextPath();
request.setAttribute("ctx",path); */
// System.out.println(ctxPath+"*********WWW*********");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>虚拟机</title>
<script type="text/javascript">
//var ctx = '${ctx}';
</script>
<script type="text/javascript" src="controller/woyun_virtualMachine_controller.js"></script>
<script type="text/javascript" src="view/woyun_virtualMachine_view.js"></script>
</head>
<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	
	<div id="themains" data-options="region:'center'" title="虚拟机"  style="overflow:hidden;padding:10px;margin-top: 40px;">
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:auto;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 450px;height:auto;">
	    	<div id="thevmleft" style="width: 210px;height:300px;float: left;">
		        <div>
		            <label for="vmNames">服务器名称:</label><br>
		            <input id="todovmNames" class="easyui-validatebox" type="text" name="serverForCreate.name" style="width: 200px;" data-options="required:true"></input>
		        </div><br>
		        <div>
		            <label for="models">模板:</label><br>
		            <select id="flavors" name="serverForCreate.flavorRef" style="width:200px;" data-options="required:true">
				     </select>
		        </div><br>
		        <div>
		            <label for="images">镜像:</label><br>
		            <select id="images" name="serverForCreate.imageRef" style="width:200px;" data-options="required:true">
				    </select>
		        </div><br>
		        <div>
		            <label for="subject">运行实例数量:</label><br>
		            <input  id="instanceNum" class="easyui-numberspinner" name="serverForCreate.max" type="text" style="width: 200px;" data-options="onChange: function(value){  
                    $('#vv').text(value);  
                } "></input>
		        </div>
	        </div>
	    </form>
	        <div id="thevmright" style="width: 210px;height:300px;float: right;">
	        	<h4>详 述</h4>
				<h5>添加运行实例所指定的详细资料。以下图表显示的是这个项目配额可以使用以及已使用的资源</h5>
				<div>
		            <label for="skeys">运行实例数量已用3个,剩余数量:</label><br>
		            <div id="instance" class="easyui-progressbar" style="width:200px;"></div>  
		        </div>
				<div>
		            <label for="skeys">虚拟处理器已用4个,剩余数量:</label><br> 
		             <div id="cpu" class="easyui-progressbar" style="width:200px;"></div>  
		        </div>
		        <div>
		            <label for="skeys">硬盘已用160GB,剩余大小:</label><br>
		             <div id="harddisk" class="easyui-progressbar" style="width:200px;"></div>  
		        </div>
		        <div>
		            <label for="skeys">内存已用5120MB,剩余数量:</label><br>
		             <div id="memory" class="easyui-progressbar" style="width:200px;"></div>  
		        </div>
	        </div>
	        <div style="float:left;">
				<table id="saftygroup"></table>					
		    </div>
	    <div align="right" style="width: 450px;">
	    	 <a href="javascript:void(0)" onclick="createServer()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
	    	 <a href="javascript:void(0)" onclick="cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	    </div>
	</div>
	<table id="virtualMachineInfo"></table>
	</div>
</body>
</html>