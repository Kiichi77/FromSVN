<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/sourceData_manage_view.js"></script>
<script type="text/javascript" src="controller/sourceData_manage_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	   
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="源数据" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
	<!--  弹出 层-->	
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:320px;display: none;border: solid gray 1px;">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 450px;height:260px;">
	    	<div id="thevmleft" style="width: 210px;height:320px;float: left;">
	    	   <div style="margin-left: 20px"><font style="font-size: 13px;">数据源名称：</font><input id="modelsName" name="model.name" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	   <div style="margin-left: 20px"><font style="font-size: 13px;">分类：</font>
	    	    <select id="sourceType" name="sourceType" style="width:200px;" data-options="required:true"></select>
	    	  </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><textarea id="modelsmiaoshu" rows="4" cols="40" name="model.description"></textarea>  </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">上传：</font><input id="modelshangchuan" type="file" name="dataSource"/>  </div>
	    	    <br>
	    	    <div align="right" style="width: 450px;">
		    	<a href="javascript:void(0);" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
	
</body>
</html>