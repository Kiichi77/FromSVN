<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/salesOrderInfo_controller.js"></script>
<script type="text/javascript" src="view/salesOrderInfo_view.js"></script>
<script type="text/javascript">  
</script>

<body class="easyui-layout">
	<div id="datagridsearch">
		<a href="javascript:void(0);" onclick="javascript:window.location='../salesOrderManager.jsp';" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返 回</a>
	</div>
	<div data-options="region:'center'" title="销售订单管理" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
</body>
</html>