<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<%
String backUrl = request.getParameter("backUrl");
request.setAttribute("backUrl", backUrl);
Parameters param = Parameters.getInstance();
String templatesWordPath = param.templatesWordPath;
request.setAttribute("templatesWordPath", templatesWordPath);
%>
<script type="text/javascript">
 $.extend($.fn.datagrid.methods, {
	  addEditor : function(jq, param) {
	      if (param instanceof Array) {
	          $.each(param, function(index, item) {
	              var e = $(jq).datagrid('getColumnOption', item.field);
	              e.editor = item.editor;
	          });
	      } else {
	          var e = $(jq).datagrid('getColumnOption', param.field);
	          e.editor = param.editor;
	      }
	  },
	  removeEditor : function(jq, param) {
	      if (param instanceof Array) {
	          $.each(param, function(index, item) {
	              var e = $(jq).datagrid('getColumnOption', item.field);
	              e.editor = {};
	          });
	      } else {
	          var e = $(jq).datagrid('getColumnOption', param.field);
	          e.editor = {};
	      }
	  }
	});


</script>
<script type="text/javascript" src="view/file_view.js"></script>
<script type="text/javascript" src="controller/file_controller.js"></script>
<script>
var backUrl = '${backUrl}';
var templatesWordPath = '${templatesWordPath}';
var contractIds = '<%=request.getParameter("contractIds")%>';
var projecksName = '<%=request.getParameter("projecksName")%>';
var cotrostId = 0;   	//合同文件ID
var lastIndexa;
var lastIndext;
var lastIndex1;
var lastIndex;
var muluhejia = 0;
var zhekouhoujia = 0;
var zhekouhouxianchang = 0;
var hejijia = 0;
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="正式合同详情" style="overflow:hidden;">
		<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;height:630px;overflow-y:scroll;">
			<div title="报价表" data-options="tools:'#p-tools'">
			   <table id="tt1" title="配置单"></table>
		       <table id="t11" title="价格总表"></table>
			</div>
			<div title="合同文件" data-options="tools:'#p-tools'">
			    <table id="tt" title="合同文件"></table>
			</div>
		</div>	
		<!-- 弹出log1：变量设置-->
	  	<div id="dd" data-options="iconCls:'icon-save'" title='合同文件变量' style="padding:5px;width:800px;height:500px;display: none;overflow-y:scroll;">
	    	<table id="ta"></table>
		</div>
		<!-- 弹出log2：查看金额-->
	  	<div id="mone" data-options="iconCls:'icon-save'" title='合同中金额详情' style="padding:5px;width:800px;height:500px;display: none;overflow-y:scroll;">
	    	<table id="tat"></table>
		</div>
	</div>
	<!-- 弹出层 -->
	<div id="updateDIV"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="修改文件名" style="background:#fafafal;padding:20px;width:auto;height:auto;" iconCls="icon-add">
	    <form id="updateform" method="post" novalidate style="width: 350px;height:50px;">
	    	<div id="thevmleft" style="width: 350px;height:50px;float: left;">
		    	<div align="left" style="width: 350px;margin-bottom: 10px;">
		    		<input id="contractId" type="hidden" name="model.id">
		    		<input id="ppath" type="hidden" name="model.exportPath">
		    		<font style="font-size: 13px;">文件名：</font>
		    		<input id="fileName" type="text" class="easyui-validatebox" style="width:180px;" name="model.fileName" data-options="required:true" />
		    	</div>
		    	<div align="right" style="width: 350px;margin-top: 15px;">
			    	 <a href="#" onclick="updateContract();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="javascript:hide();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			   	</div>
	    	</div>
	    </form>
	 </div>
	 	<!-- 配置详情弹出层 -->
	<div id="configDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
		    <table id="t2"></table>
		    <table id="t3"></table>
		    <table id="t4"></table>
			</div>
		</div>
		<br>
		<div align="right" style="width: 1200px;">
		    <a href="#" onclick="hideConfigDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
</body>