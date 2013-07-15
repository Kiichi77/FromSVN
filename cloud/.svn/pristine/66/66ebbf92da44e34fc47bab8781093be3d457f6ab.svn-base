<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/invoiceManager_controller.js"></script>
<script type="text/javascript" src="view/invoiceManager_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';	
</script>

<body class="easyui-layout">
<!-- 	<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		公司抬头名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div>  -->
	<div data-options="region:'center'" title="发票管理" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
</body>
</html>