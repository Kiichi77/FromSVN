<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/contract_view.js"></script>
<script type="text/javascript" src="controller/contract_controller.js"></script>
<script type="text/javascript">
var contractModeId = null;
var iscontext = null;//是否审核
var ctx = '${ctx}';	
<%-- var process_id = <%=request.getParameter("processid")%>;  //进程id --%>
<%-- var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id --%>
</script>


<body class="easyui-layout">
	<div data-options="region:'center'" title="合同模版" style="overflow:hidden;">
         <table id="tt"></table>
	</div>
	 <!-- 弹出层  添加用户输入名称 -->
     <div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板新增" style="background:#fafafa;padding:20px;width:360px;height:170px;overflow:hidden">
	    <form id="addVmform" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="thevmleft" style="width: 210px;height:100px;float: left;">
	             <br>
	                <div><font style="font-weight: bold;font-size: 11px;">请输入一个模版名称：</font>
	                <br>
	                <input id="name" name="contractTemplates.name" type="text" style="width:200px" /></div>
	              	<br>
	    	     <div align="right" style="width: 300px;">
			    	 <a href="#" onclick="uploadFileFun();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   		</div>
	    	</div>
	    </form>
	</div>
	
		<!-- 弹出层  修改用户输入名称 -->
     <div id="addVmDiv1" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板修改" style="background:#fafafa;padding:20px;width:360px;height:170px;overflow:hidden">
	    <form id="addVmform1" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="thevmleft1" style="width: 210px;height:100px;float: left;">
	             <br>
	                <div><font style="font-weight: bold;font-size: 11px;">请输入一个模版名称：</font>
	                <br>
	                <input id="modelId" type="text" style="width:200px;display: none;"/>
	                <input id="templateName" name="contractTemplates.name" type="text" style="width:200px" /></div>
	              	<br>
	    	     <div align="right" style="width: 300px;">
			    	 <a href="#" onclick="chekcForm();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   		</div>
	    	</div>
	    </form>
	</div>
	
		<!-- 分配任务给下一个处理人 -->
		<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板审核" style="padding: 10px;width: 350px;height:230px;overflow:hidden" iconCls="icon-add">
	    	<div id="processdiv" style="width: 300px;height:180px;float: center;">
	    	<input type="hidden" id="templatesId"/>
		    	    <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
						 <select id="tbUser" name="user_id" style="width:180px;" data-options="required:true"></select>
		    	    </div><br>
		    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备   注：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <textarea id="remark" rows="3" cols="18" name="describe"></textarea>
		    	    </div><br>
	    	    <div align="right" style="width: 300px;">
			    	<a href="javascript:void(0);" onclick="checkedTemplate();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 		</div>
	      	</div>
		</div>
</body>
</html>