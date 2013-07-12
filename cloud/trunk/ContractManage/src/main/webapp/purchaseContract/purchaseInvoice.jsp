<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
    request.setAttribute("ctx", path);
    
  //合同文件下载地址
    Parameters param = Parameters.getInstance();
    String exportPath = param.purchaseWordPath;
    request.setAttribute("exportPath", exportPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/purchaseInvoice_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var exportPath='${exportPath}';
var purchaseContractId = <%=request.getParameter("purchaseContractId")%>;
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="进项发票" style="overflow:hidden;">
	    <table id="tt"></table>
	</div>
</body>

</html>