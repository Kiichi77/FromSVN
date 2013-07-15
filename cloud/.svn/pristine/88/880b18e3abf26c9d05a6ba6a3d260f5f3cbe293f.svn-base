<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
   
 //合同打包文件下载地址
   Parameters param = Parameters.getInstance();
  String exportPath = param.exportExcelPath;
  request.setAttribute("exportPath", exportPath);
   
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/auditInvoices_controller.js"></script>
<script type="text/javascript" src="view/auditInvoices_view.js"></script>
<script type="text/javascript">
var processParamId = <%=request.getParameter("processid")%>; //进程id
var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id
var exportPath = '${exportPath}';
</script>

<body class="easyui-layout">
<!-- 
	<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		公司抬头名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="queryCheckedInvoice();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div> -->
	<div data-options="region:'center'" title="发票审核" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板审核" style="padding: 10px;width: 500px;height:300px;" iconCls="icon-add">
	    <form id="addVmform" method="post" novalidate >
	    	<div id="thevmleft" style="float: left;">
	    		<input type="hidden" id="id" name="checkedVo.id" >
	    		<input type="hidden" id="processId" name="checkedVo.process_id" >
	    	 	<div style="margin-left: 20px">审核结果：&nbsp;&nbsp;
	    	 		<input type="radio" id="pass"  checked="checked" name="checkedVo.status" onclick="changeSelect();" value="1"/>通过&nbsp;&nbsp;
	    	 		<input type="radio" id="unpass"  name="checkedVo.status" onclick="changeSelect();" value="2"/>不通过</div>
	    	 	<br>
	       		 <div style="margin-left: 20px"><font style="font-size: 13px;">批示：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><textarea id="describe"  class="easyui-validatebox" data-options="required:false" rows="4" cols="40" name="checkedVo.describe"></textarea></div>
	    	  	<br>
	    	  	 <div id="checker" style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;
				 	<select id="tbUser" name="checkedVo.user_id" style="width:160px;" data-options="required:true"></select>
    	   		 </div>
				 <div id="reject" style="margin-left: 20px"><font style="font-size: 13px;">驳回：</font>&nbsp;&nbsp;&nbsp;&nbsp;
				 	<select id="process_Step" name="checkedVo.reject_process_id" style="width:160px;" data-options="required:true"></select>
    	   		 </div>
    	   		 <br>
	    	    <div align="right" style="width: 200px;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
</body>
</html>