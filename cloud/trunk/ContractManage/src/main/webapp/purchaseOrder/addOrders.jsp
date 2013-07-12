<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/addOrders_view.js"></script>
<script type="text/javascript" src="controller/addOrders_controller.js"></script>
<script type="text/javascript">  
var contractOrderId = <%=request.getParameter("paramsId")%>;
var lastIndext;//表单的行索引
</script>
<%
String projectname=request.getParameter("projectname");
if(projectname!=null&&!"".equals(projectname)){
	projectname =new String(projectname.getBytes("ISO-8859-1"),"UTF8");
}
%>
<body class="easyui-layout">
	<div data-options="region:'center'" title="添加采购订单" style="overflow:hidden;background-color: #F7F7F7;">
		<div style="margin-left: 15px;margin-top: 10px;">
		    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: auto;height:60px;">
		      <input type="hidden" id="processId" name="processid" value="<%=request.getParameter("processid")%>">	
		    	<table style="margin-top:10px;width: auto;">
		    		<tr style="float: center;">
		    			<td style="width: 35%;">
		    				<font style="font-size: 13px;">报价单号：</font>
			    	    	<input id="baojiadanhao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:false" />
		    			</td>
		    			<td style="width: 40%;">
							<font style="font-size: 13px;">采购合同：</font>
			    	    	<select id="caigouhetong" name="model.dataSource.id" style="width:160px;" onchange="queryCaigouhet();">
				    	       <option value="<%=request.getParameter("projcetid")%>"><%=projectname%></option>
				    	    </select>
		    			</td>
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">厂&nbsp;&nbsp;家：</font>
			    	    	<select id="changjia" name="model.dataSource.id" style="width:160px;" onchange="queryChangjia();"></select>
				    	   <!-- 选择行的可采购数 -->
						   <input id="yicaigou" type="text" style="width:200px;display: none;" />
						   <!-- 选择行的索引 -->
						   <input id="praindex" type="text" style="width:200px;display: none;" />
						   <!-- 选择行的单价 -->
						   <input id="totalmoney" type="text" style="width:200px;display: none;" />
		    			</td>
		    		</tr>
		    		<tr style="float: center;">
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">订单编号：</font>
			    	   		<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:false" />
		    			</td>
		    			<td style="width: 40%;">
		    				<font style="font-size: 13px;">备&nbsp;&nbsp;&nbsp;注：</font>
		    	    		<input id="remark" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:false" />
		    			</td>
		    			<td style="width: 30%;">
							<font style="font-size: 13px;">售&nbsp;&nbsp;后：</font>
			    	    	<select id="shenqingren" name="userid" style="width:160px;"></select>
		    			</td>
		    		</tr>
		    	</table>
		   </form>
		   </div>
		   
		   <div style="width: 880px;margin-left: 15px;">
			 <table id="saleOrderTable"></table>
		   </div>
		   
		   <div style="margin-top: 10px;width: 880px;margin-left: 15px;">
			 <table id="execute" ></table>
		   </div>
		<div>
			 <div align="right" style="width: 880px;margin-top: 15px;">
		    	<a href="javascript:void(0);" onclick="createAndSaveOrder();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="javascript:window.location.href='orders.jsp';" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 	</div>
		</div>
	</div>
	   <!-- 合同执行通用单元和数量配置弹出层 -->
		<div id="numberDIV" style="background:#fafafa;padding:20px;width:650px;height:360px;display: none;border: solid gray 1px;margin-top: 100px; overflow-y:auto;">
			<div>
			  <!-- 选择行的可采购数 -->
			  <input id="yicaigou" type="text" style="width:200px;display: none;" />
			  <!-- 选择行的索引 -->
			  <input id="praindex" type="text" style="width:200px;display: none;" />
			  <font style="font-size: 13px;">机型采购数量&nbsp;：&nbsp;</font><input id="jxcgsl" type="text" style="width:200px" />&nbsp;&nbsp;&nbsp;
			</div><br>
			<font style="font-size: 13px;">通用单元数量&nbsp;：&nbsp;</font>
			<br>
			<div style="overflow-y: scroll;height: 250px;">
				<table id="tynumbers"></table>
			</div>
			<br>
			<div align="right" style="width: 650px;">
			    <a href="#" onclick="saveAndUpdatetyNumbers();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    <a href="#" onclick="hideNumberDIV();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			</div>
		</div>
				<!-- 机型配置的详情 -->
	<div id="configDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="机型配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="ttt"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a>
		</div>
	</div>
		
</body>
</html>