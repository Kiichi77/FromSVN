<!-- struts2 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Locale,cn.com.kxcomm.common.util.LocalUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	//path=/sshdemo
	//basePath=http://localhost:8080/sshdemo/
	request.setAttribute("path",path);
	request.setAttribute("ctx",path);
	request.setAttribute("basePath",basePath);
	
	String countryCode = LocalUtil.getCountryCode();
	request.setAttribute("countryCode",countryCode);
	
	String fileName = LocalUtil.getResourceFileName(request.getRequestURL().toString());
	request.setAttribute("fileName",fileName);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/jsp/common/js/extjs4/resources/css/ext-all.css" /> 
<script type="text/javascript" src="${ctx}/jsp/common/js/extjs4/bootstrap.js"> </script>
<script type="text/javascript" src="${ctx}/jsp/common/js/extjs4/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/jsp/common/js/utilTool.js"> </script>
<script type="text/javascript">
var ctx = '${ctx}';
</script>
</head>
<body>

</body>
</html>

