<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
   //合同打包文件下载地址
    Parameters param = Parameters.getInstance();
   String exportPath = param.supplierQuotationsPath;
   request.setAttribute("exportPath", exportPath);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/orderCheckedDetail_controller.js"></script>
<script type="text/javascript" src="view/orderCheckedDetail_view.js"></script>
<script type="text/javascript">  
var exportPath = '${exportPath}';
var isCanAnble = '<%=request.getParameter("statusid")%>';
var contractOrderId = '<%=request.getParameter("contractOrderId")%>';
<%-- var processId = '<%=request.getParameter("processid")%>';
var taskPkId = '<%=request.getParameter("taskPkId")%>'; --%>
var iscanOpen = false;
//是否启用审核按钮功能
var muluhejia = 0;
var zhekouhoujia = 0;
var zhekouhouxianchang = 0;
var hejijia = 0;
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="询价单详情" style="overflow:hidden;">
		   <div>
			 <table id="tt" >
		     </table>
		</div>
	</div>
	<!-- 弹出层 -->
	<!-- style="background: #fafafa; padding: 20px; width: 440px; height: 260px; display: none; z-index: 9999; border: solid gray 1px;" -->
     <div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="询价" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<form id="addVmform" method="post" novalidate>
			<%-- <input type="hidden" id="processId" name="checkedVo.process_id" value="<%=request.getParameter("processId")%>"> --%>
			<input type="hidden" id="contractOrderId" name="checkedVo.id">
			<div id="thevmleft" style="float: left;">
				<div>
					<font style="font-size: 13px;">审核结果：</font> 
					<input type="radio" id="pass" checked="checked" name="checkedVo.status" value="1" />同意&nbsp;&nbsp;
					<input type="radio" id="unpass" name="checkedVo.status"  value="0" />不同意
				</div><br />
				<div>
					<font style="font-size: 13px;">批示：</font>
					<textarea id="describe" rows="4" cols="40" name="describe.describe"></textarea>
				</div><br />
				<div align="right" style="width: 440px;">
					<a href="#" onclick="chekcForm();" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'">确 定</a> <a href="#"
						onclick="hideDiv();" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">取 消</a>
				</div>
			</div>
		</form>
	</div>
	<!-- 询价单审核配置详情弹出层 -->
	<div id="configDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
		    <table id="t2"></table>
		    <table id="t3"></table>
		    <table id="t4"></table>
			</div>
		</div>
		<br>
		<div align="right" style="width: 1200px;">
		    <a href="#" onclick="hideConfigDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
</body>
</html>