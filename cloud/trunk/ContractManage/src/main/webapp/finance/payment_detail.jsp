<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/payment_detail_view.js"></script>
<script type="text/javascript">
var purchaseContractId = '<%=request.getParameter("purchaseContractId")%>';
var ctx = '${ctx}';
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="采购付款明细" style="overflow:hidden;">
 <div>
	      <table id="tt"></table>
		</div>
</div>
		
</body>
</html>