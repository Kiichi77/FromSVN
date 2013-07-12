<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);

Parameters param = Parameters.getInstance();
String exportPath = param.purchaseWordPath;
request.setAttribute("exportPath", exportPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/accept_view.js"></script>
<script type="text/javascript" src="controller/accept_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var exportPath='${exportPath}';
var processParamId = <%=request.getParameter("processid")%>; //进程id
var taskUserPkid = <%=request.getParameter("id")%>; //任务url跳转表的id
var contractParamId = <%=request.getParameter("contractId")%>;  //从采购订单流程传入的合同id


</script>
<body class="easyui-layout">
<!-- 
<div id="datagridsearch">
 合同名称:<input id="starttime" name="starttime"  style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</div> -->
<div data-options="region:'center'" title="验收管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
<!-- 弹出层 -->
	 <div id="addVmDiv"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="上传文件" style="background:#fafafa;padding:20px;width:400px;height:240px;" iconCls="icon-add">
	    <form id="addVmform" method="post" novalidate enctype="multipart/form-data" style="width: 350px;height:100px;">
	    	<div id="thevmleft" style="float: left;">
	    	<input type="hidden" id="provedId" name="id"/>
	    	<input type="hidden" id="contractId" name="contractId"/>
	    	<input type="hidden" id="customerId" name="customerId"/>
	    	<input type="hidden" id="processId" name="checkedVo.process_id">
	        <input type="hidden" id="fileType" name="fileType"/>
	        <div><font style="font-size: 13px;">请选择您要上传的文件：</font><input type="file" id="proved" name="proved"/>
	        </div>
   	   		 <br/>
    	   <div id="checker"><font style="font-size: 13px;">下一个处理人：</font><br/>
			 	<select id="tbUser" name="checkedVo.user_id" style="width:160px;" data-options="required:true"></select>
   	   		 </div>
   	   		 <br/>
	    	    <div align="right" style="width: 290px;">
		    	   <a href="#" onclick="uploadFileFun();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
		
</body>
</html>