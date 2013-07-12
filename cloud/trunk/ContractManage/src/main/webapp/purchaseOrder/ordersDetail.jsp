<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>采购订购单详情</title>
</head>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/ordersDetail_view.js"></script>
<script type="text/javascript">
var purchaseOrderId = 5;
<%-- '<%=request.getParameter("paramsid")%>'; --%>
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="采购订单详情总览" style="overflow:hidden;">
		 <table id="tt">
	     </table>
	     <!-- 合同执行通用单元和数量配置弹出层 -->
	<div id="numberDIV" style="background:#fafafa;padding:20px;width:650px;height:280px;display: none;border: solid gray 1px;margin-top: 100px; overflow-y:auto;">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		</div>
	</div>
	</div>
</body>
</html>