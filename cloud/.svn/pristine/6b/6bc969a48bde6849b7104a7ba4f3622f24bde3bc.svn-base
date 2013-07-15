<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
    request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/roleManager_controller.js"></script>
<script type="text/javascript" src="view/roleManager_view.js"></script>
<script type="text/javascript">
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="角色信息总览" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	<div id="addDataInfoDiv" style="background:#fafafa;padding:20px;width:350px;height:480px;display: none;border: solid gray 1px;">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 640px;height:460px;">
	    	<div id="addthevmleft" style="width: 350px;height:450px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">角   色：</font>
		    	    <input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px;height:400px;overflow:scroll;"><font style="font-size: 13px;">权   限：</font>
	    	    	<ul id="tt2"></ul>
	    	    </div>
	      	</div>
    	    <div align="right" style="width: 350px;height: 100px">
		    	<a href="javascript:void(0);" onclick="addSave();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 	</div>
	   </form>
	</div>
</body>
</html>