<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/supplier_quotations_manage_view.js"></script>
<script type="text/javascript" src="controller/supplier_quotations_manage_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="供应商报价单管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
		<!-- 弹出层 -->	
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="供应商报价单审核" style="padding: 10px;width: auto;height:auto;" iconCls="icon-add">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate >
	    	<div id="thevmleft" style="float: left;">
	    	    <input id="id" name="model.superliersQuotationsId" type="hidden" style="width:200px"/>
	    	    	<br/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">供应商报价单名称：</font>
	    	    	<br/>
	    	    <input id="suppQuotaName" name="model.suppliersQuotationsName" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	   	 	<br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">供应商：</font>
	    	     	<br/>
	    	     	<select id="suppliersId" name="model.suppliers.id" style="width:200px;" data-options="required:true">
                     </select>
                 </div>
	    	     	<br>
	    	     <div style="margin-left: 20px" id="upfilediv"><font style="font-size: 13px;">供应商报价单文件：</font>
	    	    	 <br/>
	    	     <input id="upFile" type="file" name="suppliersQuotations" style="width: 180px"/></div>
	    	     	<br id="nextline" />
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font>
	    	     	<br/>
	    	     <textarea id="description" rows="4" cols="40" name="model.description"></textarea></div>
	    	     	<br>
	    	    <div align="right" >
		    	<a href="javascript:void(0);" onclick="saveOrUpdateSuppliers();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</div>
		
</body>
</html>