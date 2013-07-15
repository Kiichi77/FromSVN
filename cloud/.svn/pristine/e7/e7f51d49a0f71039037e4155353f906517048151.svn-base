<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/projectManager_controller.js"></script>
<script type="text/javascript" src="view/projectManager_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var taskPkId = <%=request.getParameter("id")%>;
var processid = <%=request.getParameter("processid")%>;
</script>
<body class="easyui-layout">
	<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		操作人编号或名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div>
	<div data-options="region:'center'" title="项目总览" style="overflow:hidden;">
	 <div>
	      <table id="tt"></table>
	 </div>
	</div>
	<!-- 创建项目 -->
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="新增/修改项目" style="background:#fafafa;padding:20px;width:350px;height:auto;overflow:hidden;">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate ><!-- style="width: 220px;height:220px;" -->
	    	<input type="hidden" id="projectId" name="project.projectId">
	    	<input type="hidden" id="process_id" name="process_id" value="<%=request.getParameter("processid")%>">
	    	<div id="thevmleft" style="float: left;"><!-- width: 210px;height:200px; -->
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">项目名称：</font><br>
	    	    <input id="projectName" name="project.projectName" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">客户：</font><br>                
	    	     <select id="customer" name="project.customer.customerId" style="width:200px;" data-options="required:true"></select>
				</div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><br>
	    	    <textarea id="description" rows="3" cols="26" name="project.description"></textarea>  </div>
	    	    <br>
	    	   <!--  <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					 <select id="tbUser" name="user_id" style="width:160px;" data-options="required:true"></select>
	    	    </div> -->
	    	    <br>
	    	    <div align="right" style="width: 260px;">
		    	<a href="javascript:void(0);" onclick="addProject();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</body>
</html>