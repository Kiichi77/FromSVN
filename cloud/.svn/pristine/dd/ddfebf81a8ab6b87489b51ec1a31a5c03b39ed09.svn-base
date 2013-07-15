<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
    request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/rightManager_controller.js"></script>
<script type="text/javascript" src="view/rightManager_view.js"></script>
<script type="text/javascript">
//弹出层***************************************
//添加存储操信息操作
function showWindow() {
	  $('#addDataInfoDiv').window('open');  
}
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="权限信息总览" >
	      <table id="tt"></table>
	</div>
	<div id="addDataInfoDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="新增权限" style="background:#fafafa;padding:20px;width:400px;height:400px;overflow:hidden">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 640px;height:260px;">
	    	<div id="addthevmleft" style="width: 350px;height:250px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">权限序号：</font>
	    	    	<input id="modelId" name="model.id"  type="text" style="width: 160px;" data-options="required:false" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">权限名称：</font>
		    	    <input id="modelRghtName" name="model.rightName"  type="text" style="width: 160px;" data-options="required:false" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">权限地址：</font>
		    	    <input id="modelUrl" name="model.url"  type="text" style="width: 160px;" data-options="required:false" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">权限级别：</font>
		    	    <input id="modelRghtLevel" name="model.rightLevel"  type="text" style="width: 160px;" data-options="required:false" />
	    	    </div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">父级ID：&nbsp;&nbsp;</font>
		    	    <input id="modelParid" name="model.parid"  type="text" style="width: 160px;" data-options="required:false" />
	    	    </div>
	    	    <br>
	      	</div>
	      	<br>
    	    <div align="right" style="width: 350px;height: 100px">
		    	<a href="javascript:void(0);" onclick="addSave();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 	</div>
	   </form>
	</div>
</body>
</html>