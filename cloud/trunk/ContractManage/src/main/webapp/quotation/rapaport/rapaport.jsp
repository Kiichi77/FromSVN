<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
String download = request.getSession().getServletContext().getRealPath("/download/quotations/");
request.setAttribute("download", download);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/rapaport_view.js"></script>
<script type="text/javascript" src="controller/rapaport_controller.js"></script>
	   <script type="text/javascript">
	   var ctx = '${ctx}';
   </script>
<body class="easyui-layout">
<%-- 	<jsp:include page="../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../view/left.jsp"></jsp:include> --%>
	<div data-options="region:'center'" title="报价表" style="overflow:hidden;">      
	    <table id="tt"></table>
    </div>
    <!-- 弹出层生成合同 -->
     <div id="addVmDiv1" style="background:#fafafa;padding:20px;width:360px;height:100px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform1" method="post" novalidate style="width: 350px;height:100px;">
	    	<div id="thevmleft1" style="width: 210px;height:100px;float: left;">
	             <br>
	                <div><font style="font-weight: bold;font-size: 11px;">请输入一个项目名称：</font>
	                <br>
	                <input id="projectNames" type="text" style="width:200px" name="model.title"/></div>
	              	<br>
	    	     <div align="right" style="width: 350px;">
			    	 <a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   		</div>
	    	</div>
	    </form>
	</div>
</body>
</html>