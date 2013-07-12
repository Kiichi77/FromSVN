<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/templateCheck_view.js"></script>
<script type="text/javascript" src="controller/templateCheck_controller.js"></script>
<script type="text/javascript">

var ctx = '${ctx}';	
var process_id = <%=request.getParameter("processid")%>; //进程id
var taskPkId = <%=request.getParameter("id")%>;   //任务url跳转表的id
</script>


<body class="easyui-layout">
	<div data-options="region:'center'" title="合同模版审核" style="overflow:hidden;">
              <table id="tt"></table>
	</div>
</body>
</html>