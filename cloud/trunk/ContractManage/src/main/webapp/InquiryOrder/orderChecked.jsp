<%@page import="cn.com.kxcomm.common.util.BlankUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/orderChecked_controller.js"></script>
<script type="text/javascript" src="view/orderChecked_view.js"></script>
<%
String projectname=request.getParameter("projectname");
if(!BlankUtil.isBlank(projectname) && !"".equals(projectname)){
	projectname=new String(projectname.getBytes("ISO-8859-1"),"UTF8");
}
%>
<script type="text/javascript">  
var contractOrderId=<%=request.getParameter("quotationsId")%>
var projectNAMENAME='<%=projectname%>';
var process_id = '<%=request.getParameter("processid")%>';  //进程id
var taskPkId = <%=request.getParameter("id")%>;  //任务url跳转表的id
</script>

<body class="easyui-layout">
<!-- 	<div id="datagridsearch"> -->
<!-- 		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input> -->
<!-- 		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input> -->
<!-- 		询价单号：<input id="purchaseOrderNumber" name="purchaseOrderNumber" class="easyui-validatebox" style="width:150px"></input> -->
<!-- 		<a href="javascript:void(0);" onclick="search();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> -->
<!-- 	</div> -->
	<div data-options="region:'center'" title="询价单总览" style="overflow:hidden;">
		 <table id="tt"></table>
	</div>
</body>
</html>