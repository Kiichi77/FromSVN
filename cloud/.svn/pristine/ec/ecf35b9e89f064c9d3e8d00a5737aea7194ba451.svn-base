<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/invoiceApplication_controller.js"></script>
<script type="text/javascript" src="view/invoiceApplication_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';	
var contractParamId = <%=request.getParameter("contractId")%>;  //销售合同id
var customerParamId = <%=request.getParameter("customerId")%>; //客户公司抬头id
var processParamId = <%=request.getParameter("processid")%>; //进程id
var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id
</script>

<body class="easyui-layout">
<!-- 	<div id="datagridsearch">
		客户公司名称:<select id="customer1" name="invoice.customer.customerId" style="width:200px;" data-options="required:true"></select>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div> -->
	<div data-options="region:'center'" title="销项发票" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	<div id="addVmDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="申请销项发票" style="background:#fafafa;padding:20px;width:350px;height:540px;" iconCls="icon-add">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 190px;height:450px;">
	    	<div id="thevmleft" style="width: 210px;height:450px;float: left;">
	    	<input id="process_id" name="checkedVo.process_id" type="hidden" style="width: 200px;" data-options="required:true" />
	    	<input id="id" name="invoice.id" type="hidden" style="width: 200px;" data-options="required:true" />
	    	    <div style="margin-left: 20px">
	    	    <font style="font-size: 13px;">合同名称：</font>
	    	     <select id="contractTitle" name="invoice.contract.id" style="width:200px;" data-options="required:true" onchange="change()"></select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">客户公司名称抬头：</font>
	    	   	 <select id="customer" name="invoice.customer.customerId" style="width:200px;" data-options="required:true"></select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">增值税类型：</font>
	    	    <select id="taxType" name="invoice.taxType" style="width:200px;" data-options="required:true">
	    	      <option value="0">增值税普通发票</option>
	    	      <option value="1">增值税专用发票</option>
	    	    </select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">合同总价：</font><input id="amount" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">金额百分比：</font><input id="percent" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" onblur="onblurpercent();" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">发票金额：</font><input id="invoicemoney" name="invoiceVo.amount" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">公司名称：</font>
	    	    <select id="companyName" name="invoice.purchaseCompany.id" style="width:200px;" data-options="required:true"></select>
	    	    </div>
   	   			<br/>
	    	    <div align="right" style="width: 260px;">
		    	<a href="javascript:void(0);" onclick="saveOrUpdateInvoice();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</body>
</html>