<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
//合同打包文件下载地址
Parameters param = Parameters.getInstance();
String exportExcelPath = param.exportExcelPath;
request.setAttribute("exportExcelPath", exportExcelPath);
String contractTemporaryPath = param.contractTemporaryPath;
request.setAttribute("contractTemporaryPath", contractTemporaryPath);
String contractId = request.getParameter("id");
request.setAttribute("contractId", contractId);
String contractType = request.getParameter("type");
request.setAttribute("contractType", contractType);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/checkContract_manage_view.js"></script>
<script type="text/javascript" src="controller/checkContract_controller.js"></script>
<script type="text/javascript">
<%-- var processid = <%=request.getParameter("processid")%>; --%>
var ctx = '${ctx}';
var exportPath = '${exportPath}';
var in_contractId = '${contractId}';
var in_contractType = '${contractType}';
var in_contractTemporaryPath = '${contractTemporaryPath}';
var in_exportExcelPath = '${exportExcelPath}';
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="审核合同" style="overflow:hidden;">
<div class="easyui-tabs" style="width:1800;height:700px;" id="demo">  
        <div title="临时合同"  >  
	     <table id="linshi"></table>
        </div>  
        <div title="正式合同" >  
             <table id="tt"></table>
        </div>   
    </div>  
</div>
<!-- 付款项弹出层 -->
	<div id="PaymentDIV"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="查看金额" style="background:#fafafa;padding:20px;width:1100px;height:400px;" iconCls="icon-add">
	    <form id="addVmform1111" method="post"  enctype="multipart/form-data" novalidate style="width: 1000px;height:320px;">
	       <div>
	       <input id="id" type="hidden" name="contractReceivePayment.id" style="width:200px" readonly="readonly"/>
	       <input id="contractId" type="hidden" name="contractReceivePayment.contract.id" style="width:200px" readonly="readonly"/>
	       <input id="purchaseCompanyId" type="hidden"  name="contractReceivePayment.purchaseCompany.id" style="width:200px" readonly="readonly"/>
	       	 <font style="font-size: 13px;">合&nbsp;同&nbsp;名&nbsp;称&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;</font><input id="contract" type="text" style="width:200px" readonly="readonly"/>
	         <font style="font-size: 13px;">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;：&nbsp;</font><input id="customer" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	        <font style="font-size: 13px;">合&nbsp;同&nbsp;总&nbsp;价&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;</font><input id="contractTotalPrice" name="contractReceivePayment.contractTotalPrice" readonly="readonly"  type="text" style="width:200px"/> 
	       	 <font style="font-size: 13px;">合同总价大写&nbsp;：&nbsp;</font><input id="contractTotalPriceText" name="contractReceivePayment.contractTotalPriceText" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
	       </div>
	       <br>
	        <div>
	          <font style="font-size: 13px;">货款的百分比&nbsp;&nbsp;：&nbsp;&nbsp;</font><input id="contractGoodsPercent" name="contractReceivePayment.contractGoodsPercent" readonly="readonly"  type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款&nbsp;&nbsp;：&nbsp;</font><input id="contractGoodsPrice" name="contractReceivePayment.contractGoodsPrice" readonly="readonly" type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp;
	          <font style="font-size: 13px;">货&nbsp;款&nbsp;大&nbsp;写&nbsp;：&nbsp;</font><input id="contractGoodsPriceText" name="contractReceivePayment.contractGoodsPriceText" type="text" style="width:200px" readonly="readonly" />
	       </div>
	       <br>
	         <div>
	          <font style="font-size: 13px;">初验款项百分比&nbsp;：&nbsp;</font><input id="contractBeginPercent" name="contractReceivePayment.contractBeginPercent"  readonly="readonly" type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">初&nbsp;验&nbsp;款&nbsp;项&nbsp;&nbsp;：&nbsp;</font><input id="contractBeginPrice" name="contractReceivePayment.contractBeginPrice"  readonly="readonly"  type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp;  
	          <font style="font-size: 13px;">初验款项大写&nbsp;：&nbsp;</font><input id="contractBeginPriceText" name="contractReceivePayment.contractBeginPriceText" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	          <font style="font-size: 13px;">终验款项百分比&nbsp;：&nbsp;</font><input id="contractFinalPercent" name="contractReceivePayment.contractFinalPercent"  readonly="readonly" type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">终&nbsp;验&nbsp;款&nbsp;项&nbsp;&nbsp;：&nbsp;</font><input id="contractFinalPrice" name="contractReceivePayment.contractFinalPrice"  readonly="readonly" type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp; 
	          <font style="font-size: 13px;">终验款项大写&nbsp;：&nbsp;</font><input id="contractFinalPriceText" name="contractReceivePayment.contractFinalPriceText" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	        <input id="statusId" type="hidden" name="contractReceivePayment.status" style="width:200px" readonly="readonly"/>
	          <font style="font-size: 13px;">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态&nbsp;：&nbsp;</font><input id="status" type="text" name="status" style="width:200px" readonly="readonly"/>
	       	  <font style="font-size: 13px;">公&nbsp;司&nbsp;名&nbsp;称&nbsp;&nbsp;：&nbsp;</font><input id="purchaseCompany" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
	       </div>
	       <br>
		<br>
		
		</form>
	</div>

		<!-- 弹出层 -->
	 <div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同审核" style="padding: 10px;width: 400px;height:300px;" iconCls="icon-add">
	    <form id="addVmform" method="post" novalidate style="width: 360px;height:180px;">
	    	<div id="thevmleft" style="width: 230px;height:180px;float: left;">
	    	<input type="hidden" id="checkedVo_id" name="checkedVo.id">
	    	<input type="hidden" id="checkedVo_status" name="checkedVo.status">
	    	<input type="hidden" id="checkedVo_type" name="checkedVo.type">
	    		 <div style="margin-left: 20px">审核结果：&nbsp;&nbsp;
	    	 		<input type="radio" id="pass"  checked="checked" onclick="onSelect()" name="checkedVo.result" value="0"/>同意&nbsp;&nbsp;
	    	 		<input type="radio" id="unpass" onclick="onSelect()" name="checkedVo.result" value="1"/>不同意</div>
	    	 		<br>
	        <div style="margin-left: 20px"><font style="font-size: 11px;">批示：</font><textarea id="checkedVo_describe" rows="4" cols="40" name="checkedVo.describe"></textarea></div>
	    	  <br>
	    	    <div align="right" style="width: 350px;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
</body>
</html>