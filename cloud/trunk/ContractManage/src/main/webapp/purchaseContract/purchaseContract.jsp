<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
    request.setAttribute("ctx", path);
    
  //合同文件下载地址
    Parameters param = Parameters.getInstance();
    String exportPath = param.purchaseWordPath;
    request.setAttribute("exportPath", exportPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/purchaseContract_controller.js"></script>
<script type="text/javascript" src="view/purchaseContract_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var exportPath='${exportPath}';
</script>

<body class="easyui-layout">
<!-- 
	<div id="datagridsearch">
		文件名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
	        文件类型:<select id="filesType" name="filesType" style="width:200px;" data-options="required:true"></select>
		<a href="javascript:void(0);" onclick="queryByTiaojian();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div> -->
	<div data-options="region:'center'" title="采购合同管理" style="overflow:hidden;">
	    <table id="tt"></table>
	    <div id="dd" data-options="iconCls:'icon-save'" title='采购合同' style="padding:5px;width:500px;height:400px;display: none;modal: true;">
	        <table id="ta"></table>
		</div>
		<!-- 弹出上传log -->
		<div id="add"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="新增采购合同" style="background:#fafafa;padding:20px;width:430px;height:470px;">
			<form id="addVmform" method="post" enctype="multipart/form-data"  style="width: 350px;height:370px;">
	             <div style="margin-top: 10px">请选择所属合同 ：
	                   <select id="contract" name="purchaseOrderContractFile.contract.id" style="width:200px;" data-options="required:true"></select>
	            </div>
	            <br>
	             <div style="margin-top: 10px">&nbsp;&nbsp;请选择供应商 ：
	                   <select id="suppliers" name="purchaseOrderContractFile.suppliers.id" style="width:200px;" data-options="required:true"></select>
	            </div>
	            <br>
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入名称：
	            	<input id="contractName" type="text" class="easyui-validatebox" style="width:200px;" name="purchaseOrderContractFile.title" data-options="required:true" />
	            </div>
	            <br>
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采购金额：
	            	<input id="contractMoney" type="text" class="easyui-validatebox" style="width:200px;" name="purchaseOrderContractFile.contractMoney" data-options="required:true" />
	            </div>
	             <div style="margin-top: 10px">&nbsp;&nbsp;&nbsp;&nbsp;请付款方式 ：
	                   <select id="paymentType" name="purchaseOrderContractFile.paymentType" style="width:200px;" data-options="required:true">
	                   <option value="支票">支票</option>
	                   <option value="延期支票">延期支票</option>
	                   <option value="电汇">电汇</option>
	                   <option value="账期">账期</option>
	                   </select>
	            </div>
	            <br>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采购公司：
		 	    <select id="purchaseCompany" name="purchaseCompanyId" style="width:200px;" data-options="required:true"></select>
		 	    <br>
		 	    <br>
	            <div id="filediv"  >
	            	 &nbsp;&nbsp;&nbsp;选择附件：<br>
	           		&nbsp;&nbsp;&nbsp; <input id="upFile" type="file" name="purchaseContract" style="width: 180px"/>
	            </div>
	            <br><br>
	            <div align="right" style="width: 350px;">
				    <a href="javascript:void(0)" onclick="uploadFileFun();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
				    <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			   	</div>    
	   		 </form>
		</div>
	</div>
</body>

</html>