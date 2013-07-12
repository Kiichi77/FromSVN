<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
String download = request.getSession().getServletContext().getRealPath("/download/quotations/");
request.setAttribute("download", download);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/rapaport_view.js"></script>
<script type="text/javascript" src="controller/rapaport_controller.js"></script>
	   <script type="text/javascript">
	   var ctx = '${ctx}';
   </script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="报价表" style="overflow:hidden;">      
	    <table id="tt"></table>
    </div>
</body>
</html>