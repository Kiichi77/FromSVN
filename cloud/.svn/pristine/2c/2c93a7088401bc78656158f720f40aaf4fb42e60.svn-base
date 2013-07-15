<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/accept_view.js"></script>
<script type="text/javascript">
</script>
<body class="easyui-layout">
<div id="datagridsearch">
 合同名称:<input id="starttime" name="starttime"  style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="javascript:alert('ca');" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</div>
<div data-options="region:'center'" title="验收管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
<!-- 弹出层 -->
 <div id="addVmDiv" style="background:#fafafa;padding:20px;width:360px;height:180px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 350px;height:180px;">
	    	<div id="thevmleft" style="width: 210px;height:180px;float: left;">
	        <div><font style="font-size: 11px;">请选择您要上传的文件：</font><input type="file"/></div>
	    	  <br>
	    	    <div align="right" style="width: 350px;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
		
</body>
</html>