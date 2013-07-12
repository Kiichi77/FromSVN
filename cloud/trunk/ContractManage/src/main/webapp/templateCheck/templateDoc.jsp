<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
              <%
  String id=request.getParameter("id");
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
   //合同模板下载地址
   Parameters param = Parameters.getInstance();
   String exportPath = param.templatesWordPath;
   request.setAttribute("exportPath", exportPath);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/templateDoc_view.js"></script>
<script type="text/javascript" src="controller/templateDoc_controller.js"></script>
<script>
var ctx = '${ctx}';	
var exportPath='${exportPath}';
var contractTemplatesId = '<%=request.getParameter("id")%>'; //合同模板总表id
var status = '<%=request.getParameter("status")%>'; //合同模板状态
var templatesId; //合同模板id，用于修改
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="合同模版" style="overflow:hidden;">
	      <table id="tt"></table>
	      <div id="dd" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板" style="padding: 10px;width: 500px;height:400px;" iconCls="icon-add">
	       <table id="ta"></table>
	      </div>
	</div>
	
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板审核" style="padding: 10px;width: 400px;height:300px;" iconCls="icon-add">
	    <form id="addVmform" method="post" novalidate >
	    	<div id="thevmleft" style="float: left;">
	    		<!-- <input type="hidden" id="processId" name="checkedVo.process_id" > -->
	    		<input type="hidden" id="templateId" name="checkedVo.contractTemplateId">
	    		<input type="hidden" id="status" name="checkedVo.status">
	    	 	<div style="margin-left: 20px">审核结果：&nbsp;&nbsp;
	    	 		<input type="radio" id="pass"  checked="checked" onclick="onSelect()" name="checkedVo.result" value="0"/>同意&nbsp;&nbsp;
	    	 		<input type="radio" id="unpass" onclick="onSelect()" name="checkedVo.result" value="1"/>不同意</div>
	    	 	<br>
	       		 <div style="margin-left: 20px"><font style="font-size: 13px;">批示：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br>
	       		 <textarea id="describe"  class="easyui-validatebox" data-options="required:false" rows="4" cols="40" name="checkedVo.describe"></textarea></div>
	    	  	<br>
	    	  	 <div id="checker" style="margin-left: 20px"><font style="font-size: 13px;">下一个处理人：</font>&nbsp;&nbsp;&nbsp;<br>
				 	<select id="tbUser" name="checkedVo.user_id" style="width:200px;" data-options="required:true"></select>
    	   		 </div>
    	   		 <br>
	    	</div>
	    	<br>
	    	  <div style="width: 200px;float: right;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    </form>
	</div>
	
</body>