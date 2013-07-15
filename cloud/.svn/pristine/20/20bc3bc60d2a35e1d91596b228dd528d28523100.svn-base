<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/payment_view.js"></script>
<script type="text/javascript">
</script>
<body class="easyui-layout">
<div id="datagridsearch">
 开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		付款情况：<select id="dateNames" name="dateName" style="width:150px;padding: 2px;" data-options="required:true">
		       <option>已付款</option>
		       <option>未付款</option>
			 </select>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</div>
<div data-options="region:'center'" title="采购付款管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
		
</body>
</html>