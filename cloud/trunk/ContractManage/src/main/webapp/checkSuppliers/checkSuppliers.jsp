<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/checkSuppliers_view.js"></script>
<script type="text/javascript">
var suppliersId = <%=request.getParameter("suppliersId")%>;
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="供应商审核" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
</div>
		<!-- 弹出层 -->
     <!-- 弹出层 -->
	 <div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同审核" style="padding: 10px;width: auto;height:auto;" iconCls="icon-add">
	    <form id="addVmform" method="post" novalidate >
	    	<div id="thevmleft" style="float: left;">
	    	<input type="hidden" id="checkedVo_id" name="checkedVo.id">
	    		 <div style="margin-left: 20px">审核结果：&nbsp;&nbsp;
	    	 		<input type="radio" id="pass"  checked="checked" onclick="onSelect()" name="checkedVo.result" value="0"/>同意&nbsp;&nbsp;
	    	 		<input type="radio" id="unpass" onclick="onSelect()" name="checkedVo.result" value="1"/>不同意</div>
	    	 		<br>
	        <div style="margin-left: 20px;margin-right: 20px;">
	        	批示：<br/>
	        	<textarea id="checkedVo_describe" rows="4" cols="40" name="checkedVo.describe"></textarea></div>
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