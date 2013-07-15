<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
   //合同打包文件下载地址
   Parameters param = Parameters.getInstance();
  String exportPath = param.contractTemporaryPath;
  request.setAttribute("exportPath", exportPath);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/contract_temporary_view.js"></script>
<script type="text/javascript" src="controller/contract_temporay_controller.js"></script>
<script type="text/javascript">
var contractModeId = null;
var iscontext = null;//是否审核
var ctx = '${ctx}';	
var exportPath = '${exportPath}';
<%-- var process_id = <%=request.getParameter("processid")%>;  //进程id --%>
<%-- var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id --%>
</script>

<body  class="easyui-layout">
    <div data-options="region:'center'" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
	</div>
	<!-- 合同执行通用单元和数量配置弹出层 -->
	<div id="executeDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同执行" style="background:#fafafa;padding:20px;width:700px;height:600px;">
		<div>
		  <input id="corids" type="text" style="width:200px;display: none;"/>
		  <input id="projectId" type="text" style="width:200px;display: none;">
		  <font style="font-size: 13px;">订&nbsp;单&nbsp;号&nbsp;：&nbsp;</font><input id="ordernum" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		</div>
		<div style="margin-top: 10px">
		  <font style="font-size: 13px;">收货人&nbsp;&nbsp;：&nbsp;</font>
		  <input id="consignee" class="easyui-validatebox" type="text" style="width:200px" data-options="required:true"/>
		</div>
	    <div style="margin-top: 10px">
		  <font style="font-size: 13px;">联系方式&nbsp;：</font>
		  <input id="consigneePhone"  class="easyui-validatebox" type="text" style="width:200px"  data-options="required:true"/>
		</div><br>
		<div>
		<font style="font-size: 13px;">收货地址&nbsp;：&nbsp;</font><input class="easyui-combobox" id="address" name="customer.jobPlace.id" style="width: 400px" data-options="required:true"> 
		</div>
		<div style="margin-top: 10px">
		  <font style="font-size: 13px;">备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;：</font>
		  <input id="description" type="text" style="width:200px" name="project.description"/>
		</div><br>
		<div style="overflow-y: scroll;height: 260px;">
			<table id="execute"></table>
		</div><br><br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="createAndSaveOrder();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    <a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		</div>
	</div>
	
	 <!-- 分配任务给下一个处理人 -->
	<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="提交合同审核" style="padding: 10px;width: 350px;height:250px;overflow:hidden" iconCls="icon-add">
   	  <form id="addVmform1" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="processdiv" style="float: center;">
    			<input type="hidden" id="checkedVo_id" name="checkedVo.id"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					 <select id="tbUser" name="checkedVo.user_id" style="width:180px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备   注：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <textarea id="describe"  class="easyui-validatebox" data-options="required:false" rows="4" cols="30" name="checkedVo.describe"></textarea></div>
	    	    </div><br>
	    	    <div align="right" >
			    	<a href="javascript:void(0);" onclick="submitCheck();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 		</div>
      	</form>
	</div>
</body>
</html>