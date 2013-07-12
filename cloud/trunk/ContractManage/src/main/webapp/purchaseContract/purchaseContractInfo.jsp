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
<script type="text/javascript" src="controller/purchaseContractInfo_controller.js"></script>
<script type="text/javascript" src="view/purchaseContractInfo_view.js"></script>
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
	<div data-options="region:'center'" title="进项发票管理" style="overflow:hidden;">
	    <table id="tt"></table>
	    <div id="dd" data-options="iconCls:'icon-save'" title='进项发票' style="padding:5px;width:500px;height:400px;display: none;modal: true;">
	        <table id="ta"></table>
		</div>
		<!-- 弹出上传log -->
		<div id="addVmDiv"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="上传进项发票" style="background:#fafafa;padding:20px;width:450px;height:430px;">
			<form id="addVmform" method="post" enctype="multipart/form-data" novalidate style="width: 350px;height:350px;">
	          <div>
	          <input id="id" type="text" class="easyui-validatebox" style="width:200px;display: none;" name="purchaseContractId" data-options="required:true" />
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订购合同名称：
	            	<input id="contractName" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly"/>
	            </div>
	            <br>
	             <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商：
	            	<input id="supplier" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	            <br>
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同金额：
	            	<input id="contractMoney" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	            <br>
	              <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;付款方式：
	            	<input id="paymentType" type="text" class="easyui-validatebox" style="width:200px;" data-options="required:true" readonly="readonly" />
	            </div>
	            <br>
	            <div>
	               <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;支票号/流水号：
	            	<input id="checkNo" type="text" class="easyui-validatebox" style="width:200px;" name="checkNo" data-options="required:true" />
	              </div>
	              <br>
	              <div>
	            	开票时间/付款时间：
	              <input id="paymentTime" name="paymentTime" class="easyui-datebox" style="width:200px;padding: 2px;"></input>
	              </div>
	            </div>  
		 	    <br>
	            <br><br>
	            <div align="right" style="width: 350px;">
				    <a href="javascript:void(0)" onclick="updatePurchaseContract();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
				    <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			   	</div>    
	   		 </form>
		</div>
		<!-- 上传进项发票 -->
		<div id="addInvoiceDiv"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="进项发票" style="background:#fafafa;padding:20px;width:450px;height:480px;">
			<form id="addInvoiceform" method="post" enctype="multipart/form-data" novalidate style="width: 350px;height:350px;">
			   <div style="margin-top: 10px"><font style="font-size: 11px;">请选择采购合同 ：</font>
	                   <select id="contract" name="purchaseInvoice.purchaseOrderContractFile.id" style="width:200px;" data-options="required:true" onchange="changeById()"></select>
	            </div>
	            <div style="margin-top: 10px"><font style="font-size: 11px;">请选择采购公司 ：</font>
	                   <select id="purchaseCompany" name="purchaseInvoice.purchaseCompany.id" style="width:200px;" data-options="required:true"></select>
	            </div>
	             <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商 ：</font>
	                   <input id="supplierName" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	                   <input id="supplierId" name="purchaseInvoice.suppliers.id" type="hidden" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	              <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;纳税人识别号 ：</font>
	                   <input id="taxpayerNumber" name="purchaseInvoice.taxpayerNo" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	              <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开户银行 ：</font>
	                   <input id="openBank" name="purchaseInvoice.PurchaseBankOpen" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	              <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;银行帐号 ：</font>
	                   <input id="accounts" name="purchaseInvoice.PurchaseAccount" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true" readonly="readonly" />
	            </div>
	             <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开票日期 ：</font>
	                   <input id="dateInvoice" name="purchaseInvoice.dateInvoice" type="text" class="easyui-datebox" style="width:200px;"  data-options="required:true"  />
	            </div>
	             <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发票编号 ：</font>
	                   <input id="invoiceNo" name="purchaseInvoice.invoiceNo" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true"  />
	            </div>
	             <div style="margin-top: 10px"><font style="font-size: 11px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发票金额 ：</font>
	                   <input id="amount" name="purchaseInvoice.amount" type="text" class="easyui-validatebox" style="width:200px;"  data-options="required:true"  />
	            </div>
	            <br>
	             <div id="filediv"  >
	            	 &nbsp;&nbsp;&nbsp;选择您要上传的文件：<br>
	           		&nbsp;&nbsp;&nbsp; <input id="upFile" type="file" name="upFile" style="width: 180px"/>
	            </div>
	            <br><br>
	            <div align="right" style="width: 400px;">
				    <a href="javascript:void(0)" onclick="uploadFileFun();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
				    <a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			   	</div>  
			</form>
		</div>
	</div>
</body>

</html>