<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/checkDetails_view.js"></script>
<script type="text/javascript">
</script>
<body class="easyui-layout">
<div data-options="region:'center'" title="报价表审核" style="overflow:hidden;">
	   	<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;height:700px;">
		<div title="典配1" data-options="tools:'#p-tools'">
		  <table id="tt1"></table>
		</div>
		<div title="典配2" data-options="tools:'#p-tools'">
		<table id="tt2"></table>
		</div>
	    <div title="典配3" data-options="tools:'#p-tools'" >
	    <table id="tt3"></table>
		</div>
</div>
		<div id="tab-tools">
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="showWindow()">审核</a>
		<a href="checkRapaport_manage.jsp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'" >返回</a>
		</div>
	</div>
	

		<!-- 弹出层 -->
     <div id="addVmDiv" style="background:#fafafa;padding:20px;width:360px;height:180px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 350px;height:180px;">
	    	<div id="thevmleft" style="width: 210px;height:180px;float: left;">
	    	 <div><input type="radio"/>通过&nbsp;&nbsp;<input type="radio"/>不通过</div>
	    	 <br>
	        <div><font style="font-size: 11px;">批示：</font><textarea id="describe" rows="4" cols="40" name="describe"></textarea></div>
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