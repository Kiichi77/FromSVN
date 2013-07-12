<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/logManager_controller.js"></script>
<script type="text/javascript" src="view/logManager_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';	
</script>

<body class="easyui-layout">
<div id="datagridsearch">
 		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input> 
		操作人:<input id="operaterName" name="operaterName" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="queryByTiaojian();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div> 
	<div id="logdic" data-options="region:'center'" title="日志总览" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
</body>
</html>