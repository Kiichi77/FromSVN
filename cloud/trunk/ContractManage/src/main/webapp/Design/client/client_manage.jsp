<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/client_manage_view.js"></script>
<script type="text/javascript">
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="客户管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
	<!--  弹出 层-->	
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:360px;display: none;border: solid gray 1px;">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 450px;height:360px;">
	    	<div id="thevmleft" style="width: 210px;height:360px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">公司名称：</font><input id="name"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">注册地址：</font><input id="address"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">负责人：</font><input id="principal"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">电话：</font><input id="phone"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><textarea id="describe" rows="4" cols="40" name="model.description"></textarea></div>
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