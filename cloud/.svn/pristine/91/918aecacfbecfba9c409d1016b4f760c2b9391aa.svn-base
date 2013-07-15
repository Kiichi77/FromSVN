<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/config_controller.js"></script>
<script type="text/javascript" src="view/config_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="机型配置总览" style="overflow:hidden;">
	       <div>
	      <table id="tt"></table>
		</div>
	  </div>
<!-- 弹出层 -->
     <div id="addVmDiv" style="background:#fafafa;padding:20px;width:360px;height:280px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 350px;height:280px;">
	    	<div id="thevmleft" style="width: 210px;height:280px;float: left;">
	        <div><font style="font-size: 11px;">请选择一个项目：</font><select id="type" name="type" style="width:200px;" data-options="required:true">
	                   <option>联通沃云XXX</option>
	                   <option>移动XXX</option>
	                   <option>电信XXX</option>
	                   </select></div>
	    	<br>
	    	<div style="margin-top: 10px"><font style="font-size: 11px;">请选择分类 ：</font>
	                 <select id="sourceType" name="sourceType" style="width:200px;" data-options="required:true" onchange="changeSource()"></select>
	            </div><br>
	    	    <div style="margin-top: 10px"><font style="font-size: 11px;">请选择单元数据 ：</font>
	                   <select id="dateNames" name="dateName" style="width:200px;" data-options="required:true"></select>
	            </div><br>
	            <input id="modelId" type="text" style="width:200px;display: none;"/>
	            <div><font style="font-size: 11px;">请输入配置单名称：</font><input id="pezhiname" type="text" style="width:200px" /></div><br>
	    	    <div align="right" style="width: 350px;">
		    	   <a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    	   <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    	</div>
	    	</div>
	    </form>
	</div>
	    	
	<!-- 弹出层生成合同 -->
     <div id="addVmDiv1" style="background:#fafafa;padding:20px;width:360px;height:200px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform1" method="post" novalidate style="width: 350px;height:200px;">
	    	<div id="thevmleft1" style="width: 210px;height:200px;float: left;">
	             <br>
	                <div><font style="font-weight: bold;font-size: 11px;">请输入一个项目名称：</font>
	                <br>
	                <input id="projectNames" type="text" style="width:200px" name="model.title"/></div>
	                 <br>
	                <div><font style="font-weight: bold;font-size: 11px;">请选择审核人：</font>
	                <br>
	                <select id="name" name="name" style="width:200px;" data-options="required:true">
	                <option>BOSS ONE</option>
	                <option>BOSS TWO</option>
	                <option>BOSS THREE</option>
	                </select>
	                </div>
	              	<br><br>
	    	     <div align="right" style="width: 350px;">
			    	 <a href="#" onclick="toRapaport();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   		</div>
	    	</div>
	    </form>
	</div>
</body>
</html>