<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/salesReceivables_controller.js"></script>
<script type="text/javascript" src="view/salesReceivables_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';	
var processParamId = <%=request.getParameter("processid")%>; //进程id
var taskPkId = <%=request.getParameter("id")%>; //任务url跳转表的id
var contractId = <%=request.getParameter("contractId")%>;
</script>

<body class="easyui-layout">
	<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		项目名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div>

	<div data-options="region:'center'" title="合同收款" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	 
	  <div id="addVmDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="执行收款" style="background:#fafafa;padding:20px;width:300px;height:440px;" iconCls="icon-add">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 190px;height:350px;">
	    	<div id="thevmleft" style="width: 210px;height:350px;float: left;">
	    	<div style="margin-left: 20px"><font style="font-size: 13px;">收款金额：</font>
	    	<input id="payDatamoney" name="payData.money" class="easyui-validatebox"  style="width: 200px;" data-options="required:true" readonly="readonly"/></div>
	    	<input id="payDataContractReceivePayment" name="payData.contractReceivePayment" style="width: 200px;display: none"  />
	    	<input id="processParamId" name="checkedVo.process_id" type="hidden" >
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">预计到账时间：</font>
	    	    <input id="payDatareceiveTime" name="payData.receiveTime" class="easyui-datebox" style="width: 200px;" data-options="required:true"  /></div>
	    	    <br><!-- payData.receiveTime -->
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备注：</font>
	    	    <input id="payDataNotes" name="payData.Notes" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">收款类型：</font>
	    	    <select id="payDatatype" name="payData.payType" style="width:200px;" data-options="required:true">
	    	    </select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">财务人员：</font>
	    	    <select id="payDatafinancialName" name="checkedVo.user_id" style="width:200px;" data-options="required:true"></select></div>
	    	    <br>
	    	    <br>
	    	    <div align="right" style="width: 210px;">
		    	<a href="javascript:void(0);" onclick="upload();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
	
	 <div id="w" class="easyui-window"  title="合同收款明细"  data-options="modal:false,closed:true,iconCls:'icon-save'" style="width:850px;height:500px;padding:2px;" >  
        <div data-options="region:'center'"  style="overflow:hidden;">
	      <table id="moneylist"></table>
	</div>
    </div> 
    
</body>
</html>