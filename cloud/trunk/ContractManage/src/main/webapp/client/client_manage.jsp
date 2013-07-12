<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/client_manage_view.js"></script>
<script type="text/javascript" src="controller/client_manage_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="客户管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
	<!--  弹出 层 添加-->	
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="新增客户"  style="background:#fafafa;padding:20px;width:500px;height:auto;overflow:hidden;">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate >
	    	<div id="thevmleft" style="float: left;">
	    	   <input id="id" type="text" style="width:200px;display: none;"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">公司名称：</font><br/><input id="name" name="customer.companyName"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">注册地址：</font><br/><input id="address" name="customer.address" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">负责人：</font><br/><input id="principal"  name="customer.contact" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">电话：</font><br/><input id="phone"  name="customer.phone" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">邮箱：</font><br/><input id="email"  name="customer.email" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">所在地：</font><br>
	    	     <input class="easyui-combobox" id="jobPlace" name="customer.jobPlace.id" style="width: 200px"> 
				</div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><br/><textarea id="describe"  name="customer.description" rows="4" cols="40" name="model.description"></textarea></div>
	    	     <br>
	    	    <div align="right" style="width: 450px;">
		    	<a href="javascript:void(0);" onclick="addCustomer();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
	
	
		<!--  弹出 层 修改-->	
	<div id="addVmDiv1" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="修改客户"  style="background:#fafafa;padding:20px;width:500px;height:auto;overflow:hidden;">
	    <form id="addVmform1" method="post"  enctype="multipart/form-data" novalidate style="width: 450px;height:420px;">
	    	<div id="thevmleft1" style="float: left;">
	    	   <input id="customerId" name="customer.customerId"  type="text" style="width:200px;display: none;"/><!-- display: none; -->
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">公司名称：</font><br/><input id="companyName" name="customer.companyName"  class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">注册地址：</font><br/><input id="address1" name="customer.address" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">负责人：</font><br/><input id="contact"  name="customer.contact" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">电话：</font><br/><input id="phone1"  name="customer.phone" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">邮箱：</font><br/><input id="email1"  name="customer.email" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">所在地：</font><br>
	    	     <input class="easyui-combobox" id="jobPlace1" name="customer.jobPlace.id" style="width: 200px"> 
				</div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><br/><textarea id="description"  name="customer.description" rows="4" cols="40" name="model.description"></textarea></div>
	    	     <br>
	    	    <div align="right" style="width: 450px;">
		    	<a href="javascript:void(0);" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a>
		    	<a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</body>
</html>