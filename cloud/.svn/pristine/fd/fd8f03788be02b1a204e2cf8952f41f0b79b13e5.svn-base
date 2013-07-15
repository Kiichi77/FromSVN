<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/payment_view.js"></script>
<script type="text/javascript" src="controller/payment_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
var contratFileId = '<%=request.getParameter("quotationsId")%>'; //合同模板总表id
</script>
<body class="easyui-layout">
<!-- 
<div id="datagridsearch">
 		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		付款情况：<select id="dateNames" name="dateName" style="width:150px;padding: 2px;" data-options="required:true">
		       <option>已付款</option>
		       <option>未付款</option>
			 </select>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</div> -->
<div data-options="region:'center'" title="采购付款管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
		
			<!--  弹出 层 确认付款-->	
	<div id="addVmDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="确认付款" style="background:#fafafa;padding:20px;width:780px;height:500px;" iconCls="icon-add">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 680px;height:310px;">
	    	<div id="thevmleft" style="width: 680px;height:310px;float: left;">
	    	<div>
	       		<p align="center"><font style="font-size: 18px;font-weight: bold;">采购付款单</font></p>
			</div>
			<div>
			   <input type="hidden" id="processId" name="processid" value="<%=request.getParameter("processid")%>">
			   <input id="id" type="hidden" name="purchaseContractId" style="width:200px; " />
			   <input id="overPayId" type="hidden" name="overPayId" style="width:200px; " />
			   <font style="font-size: 13px;">项目名称&nbsp;：&nbsp;</font><input id="projectName" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	   <font style="font-size: 13px;">所属合同&nbsp;：&nbsp;&nbsp;&nbsp;</font><input id="contract" type="text" style="width:200px" readonly="readonly" />  
			</div>
			<br>
			<div>
			   <font style="font-size: 13px;">合同名称&nbsp;：&nbsp;</font><input id="title" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	   <font style="font-size: 13px;">合同状态&nbsp;：&nbsp;&nbsp;</font>	<input id="isPay" type="text" style="width:200px"readonly="readonly"/>
			</div>
			<br>
			<div>
			   <font style="font-size: 13px;">合同金额&nbsp;：&nbsp;</font><input id="contractMoney" type="text" name="contractMoney" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	     <font style="font-size: 13px;">采购公司&nbsp;：&nbsp;&nbsp;</font>
		 	   <input id="purchaseCompany" name="purchaseCompany"  type="text" style="width:200px" readonly="readonly"/>
		 	   <input id="purchaseCompanyId" name="purchaseOrderContractBill.purchaseCompany.id"  type="hidden" style="width:200px" readonly="readonly"/>
			</div>
			<br>
			<div>
			   <font style="font-size: 13px;">付款方式&nbsp;：&nbsp;</font><input id="paymetType" type="text" name="paymetType" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	   <font style="font-size: 13px;">供应商&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;</font>	<input id="suppliers" type="text" style="width:200px" readonly="readonly"/>
			</div>
			<br>
			<div>
			   <font style="font-size: 13px;">累计付款&nbsp;：&nbsp;</font><input id="payment" name="paymentDouble"  type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	 	<font style="font-size: 13px;">本次付款&nbsp;：&nbsp;&nbsp;</font>	<input id="currentPayment" name="purchaseOrderContractBill.payment" type="text" style="width:200px" />
		 	</div>
		 	<br>
			<div>
			   <font style="font-size: 13px;" id="no">支票号/流水号:</font><input id="checkNo" type="text" name="checkNo" style="width:200px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	   <font style="font-size: 13px;" id="time">开票时间/付款时间:</font><input id="paymentTime" name="paymentTime" class="easyui-datebox" style="width:200px;padding: 2px;"></input>
			</div>
		 	 <br>
		 	<div>
		 	   <font style="font-size: 13px;">是否对冲&nbsp;：&nbsp;</font>
		 	   <select id="useOverPay" name="purchaseOrderContractBill.useOverPay" style="width:200px;" data-options="required:true"  onchange="changeOverpay();">
		 	   <option value="0">不对冲</option>
		 	   <option value="1">对冲</option>
		 	   </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	</div>
		 	<br>
		 
		 	<div  id="OverPayDIV" style="display: none;">
		 	   <font style="font-size: 13px;">对冲金额&nbsp;：&nbsp;</font><input id=userOverPayMoney name="purchaseOrderContractBill.userOverPayMoney" type="text" style="width:200px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	   <font style="font-size: 13px;">可对冲金额&nbsp;：&nbsp;</font><input id="totalOverPayMoney" name="totalOverPayMoney" type="text" style="width:200px" readonly="readonly"/>
			</div>
			 <br>
	    	    <div style="width: 630px;" align="right">
		    	<a href="javascript:void(0);" id="btnSave" onclick="saveContractBill();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</body>
</html>