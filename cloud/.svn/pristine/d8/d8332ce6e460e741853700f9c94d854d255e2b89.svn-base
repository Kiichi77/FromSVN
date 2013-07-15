<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/projectProcess_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var projectid = <%=request.getParameter("projectid")%>;
var process_id = <%=request.getParameter("id")%>;
function processPicJump(process_id){
	$.post(ctx+ '/entity/single/query!processPicJump.action',
			{process_id:process_id}
	,function(data) {
		var aaa=eval("("+data+")");
	//	alert(data);alert(aaa.taskUrl.url);
		if (aaa.taskUrl!=null) {
			window.location.href=ctx+'/view/view.jsp?taskId='+aaa.taskUrl.id;
		}
	});
}
</script>
<head>
<title>项目进度列表</title>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" title="项目进度列表" style="overflow:hidden;">
	 <div>
	      <table id="tt"></table>
	 </div>
	</div>
</body>
</html>