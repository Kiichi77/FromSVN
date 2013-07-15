<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/financialProceedsReceived_controller.js"></script>
<script type="text/javascript" src="view/financialProceedsReceived_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';
var processParamId = <%=request.getParameter("processid")%>; //进程id
var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id
var contractId = <%=request.getParameter("contractId")%>; //合同id
</script>

<body class="easyui-layout">
<!-- 
	<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		项目名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div> -->
	<div data-options="region:'center'" title="销售收款项" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	  <div id="addVmDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="确认收款" style="background:#fafafa;padding:20px;width:300px;height:280px;" iconCls="icon-add">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 190px;height:220px;">
	    	<div id="thevmleft" style="width: 210px;height:170px;float: left;">
	    		<input type="hidden" id="processId" name="checkedVo.process_id" >
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">买家银行帐号：</font>
	    	     <input id="payDataDataId" name="payData.dataId"  style="width: 200px;display: none" />
	    	      <input id="payType" name="payData.payType"  style="width: 200px;display: none" />
	    	       <input id="contractId" name="payData.contractId"  style="width: 200px;display: none" />
	    	    <input id="payDataBuyerAccount" name="payData.buyerAccount"  style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">到账时间：</font>
	    	    <input id="payDataAccpetTime" name="payData.accpetTime" class="easyui-datebox" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div id="checker" style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;
				 	<select id="tbUser" name="checkedVo.user_id" style="width:160px;" data-options="required:true"></select>
    	   		 </div>
	    	    <br>
	    	    <div align="right" style="width: 210px;">
		    	<a href="javascript:void(0);" onclick="upload();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
	
</body>
</html>