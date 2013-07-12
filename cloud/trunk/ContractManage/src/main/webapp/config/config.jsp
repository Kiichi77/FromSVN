<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/config_controller.js"></script>
<script type="text/javascript" src="view/config_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
var lastIndex1;		//报价总表的datagrid行序列号
var taskPkId = <%=request.getParameter("id")%>;
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="机型配置列表" style="overflow:hidden;">
		<div>
	      <table id="tt"></table>
		</div>
	</div>
<!-- 弹出层 -->
     <div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="机型配置" style="background:#fafafa;padding:20px;width:300px;height:300px;">
	    <form id="addVmform" method="post" novalidate style="width: 240px;height:220px;">
	    	<div id="thevmleft" style="width: 210px;height:220px;float: left;">
	    	<br>
	    	<div style="margin-top: 10px"><font style="font-size: 13px;">分类 ：</font>
	                 <select id="sourceType" name="sourceType" style="width:200px;" data-options="required:true" onchange="changeSource()"></select>
	            </div><br>
	    	    <div style="margin-top: 10px"><font style="font-size: 13px;">单元数据 ：</font>
	                   <select id="dateNames" name="dateName" style="width:200px;" data-options="required:true"></select>
	            </div><br>
	            <input id="modelId" type="text" style="width:200px;display: none;"/>
	            <div><font style="font-size: 13px;">配置单名称：</font><input id="pezhiname" type="text" style="width:200px" /></div><br>
	    	    <div align="right" style="width: 240px;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">下一步</a>
		    	   <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
	    	
	<!-- 弹出层生成报价表 -->
    <div id="addVmDiv1" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="制作报价表" style="background:#fafafa;padding:20px;width:590px;height:420px;">
	    <form id="addVmform1" method="post" novalidate style="width: 520px;height: 80px;">
		    <input type="hidden" id="alltable" name="baoJiaTable">
		    <div id="addthevmleft" style="width: 280px;height:80px;float: left;">
		    	<!-- <div><font style="font-size: 13px;">请选择项目：</font>
		            <select id="projectName" name="model.project.projectId" style="width:165px;" data-options="required:true" onchange="changeProject()"></select>
		        </div> -->
		        <div><font style="font-size: 13px;">报价表名称：</font>
		        	<input id="projectNames" type="text" style="width:160px" name="model.title"/>
		        </div>
		    </div>
		    <br>
	    </form>
	    <!--  价格总表table  -->
	    <div style="width: 550px;height:220px;overflow-y:scroll;"><table id="t11"></table></div><br>
	    <div align="right" style="width: 520px;">
			<a href="#" onclick="toRapaport();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a>
			<a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		</div>
	</div>
</body>
</html>