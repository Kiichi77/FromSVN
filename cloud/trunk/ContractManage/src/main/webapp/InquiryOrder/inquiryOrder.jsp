<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/inquiryOrder_controller.js"></script>
<script type="text/javascript" src="view/inquiryOrder_view.js"></script>
<script type="text/javascript">  
var ctx = '${ctx}';
</script>

<body class="easyui-layout">
<!-- 
		<div id="datagridsearch">
		开始时间:<input id="starttime" name="starttime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		截止时间:<input id="endtime" name="endtime" class="easyui-datebox" style="width:150px;padding: 2px;"></input>
		<a href="javascript:void(0);" onclick="querySaleOrder();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div> -->
	<div data-options="region:'center'" title="询价单" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
		<!-- 询价 -->
		<!-- class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板审核" style="padding: 10px;width: 400px;height:300px;" iconCls="icon-add" -->
	<div id="executeDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="询价" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 260px;">
			<input type="hidden" id="contractId"/>
			<input type="hidden" id="projectId"/>
			<table id="execute"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		<a href="#" onclick="updateRelationship();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交审核</a>
		    <a href="#" onclick="hideDiv1();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a>
		</div>
	</div>
	<div id="inquiryDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="询价单" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 260px;">
			<table id="ttt1"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideDiv2();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
	<!-- 机型配置的详情 -->
	<div id="numberDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="机型配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a>
		</div>
	</div>
	
	 <!-- 分配任务给下一个处理人 -->
	<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="提交询价审核" style="padding: 10px;width: 350px;height:250px;overflow:hidden" iconCls="icon-add">
   	  <form id="addVmform1" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="processdiv" style="float: center;">
    			<input type="hidden" id="checkedVo_id" name="checkedVo.id"/>
    			<input type="hidden" id="checkedVo_projectId" name="checkedVo.projectId"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					 <select id="tbUser" name="checkedVo.user_id" style="width:180px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备   注：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <textarea id="describe"  class="easyui-validatebox" rows="4" cols="30" name="checkedVo.describe"></textarea></div>
	    	    </div><br>
	    	    <div align="right" >
			    	<a href="javascript:void(0);" onclick="submitCheck();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	<a href="#" onclick="closeSubmitCheckedWin();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 		</div>
      	</form>
	</div>
	
	</body>
</html>