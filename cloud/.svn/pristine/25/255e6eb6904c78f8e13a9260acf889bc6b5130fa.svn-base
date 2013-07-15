<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
   //合同打包文件下载地址
    Parameters param = Parameters.getInstance();
   String exportPath = param.exportExcelPath;
   request.setAttribute("exportPath", exportPath);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/createcontract_view.js"></script>
<script type="text/javascript" src="controller/createcontract_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
var exportPath = '${exportPath}';
var nowUser = '<%=session.getAttribute("username") %>';
var process_id ='<%=request.getParameter("processid")%>';  //进程id
var contract_id = '<%=request.getParameter("contractId")%>'; //合同id
var userTaskPkid = '<%=request.getParameter("id")%>'; //任务url跳转表的id
</script>
<body class="easyui-layout">
	<!-- 报价表明细tab -->
	<div data-options="region:'center'" title="我的合同" style="overflow:hidden;">
		<div id="myTabs" class="easyui-tabs" style="width:1800;height:700px;">
			<div title="临时合同" data-options="closable:false,cache:false">
				<iframe scrolling="no" frameborder="0"  src="../../contractTemporary/contract_temporary.jsp" style="width:100%;height:100%;"></iframe>
			</div>
			<div title="正式合同">
				<table id="tt"></table>
			</div>
		</div>
	</div>
	<!-- 生成合同弹出层 -->
     <div id="addVmDiv" style="background:#fafafa;padding:20px;width:360px;height:460px;display: none;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 350px;height:460px;">
	    	<div id="thevmleft" style="width: 210px;height:455px;float: left;">
	    		 <div>
	    	     	  <font style="font-size: 13px;">合同编号：</font>
	                  <input id="contractnumber" type="text" style="width:194px" data-options="required:true"/>
	             </div><br>
	    		 <div>
	    		 	<font style="font-size: 13px;">合同名称：</font>
	    		 	<input id="proname" type="text" style="width:194px" />
	    		 	<input id="contractids" type="text" style="width:194px;display: none;" />
	    		 </div><br>
	    		 <div>
	    		 	<font style="font-size: 13px;">合同类型：</font>
	    		 	<select id="contractType" name="contractType" style="width:200px;">
	    		 		<option value="1">临时合同</option>
	    		 		<option value="2">正式合同</option>
	    		 	</select>
	    		 </div><br>
	    	     <div>
	    	     	  <font style="font-size: 13px;">所属项目 ：</font>
	                  <select id="projectname" class="easyui-combobox" name="projectname" style="width:200px;" data-options="required:true"></select>
	             </div><br>
	    	     <div><font style="font-size: 13px;">报价表 ：&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showCheckBaojia();" style="font-size: 13px;">修改已选择的报价表</a></font>
	                  <input id="title" type="text" style="width:198px;display: none;"/>
	                  <textarea id="baojianame" rows="3" cols="21" name="model.description"></textarea>
	             </div>
	             <div ><font style="font-size: 13px;">合同模版 ：</font>
	                  <select id="name" name="name" style="width:200px;"></select>
	             </div><br>
	             <div>
	             	  <font style="font-size: 13px;">创建人：</font>
	             	  <input id="createManName" type="text" style="width:194px;" disabled="disabled"/>
	             </div><br>
	    	     <div align="right" style="width: 350px;">
			    	 <a href="#" onclick="addContract();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	    	</div>
	    </form>
	 </div>
	 <!-- 报价表选择弹出层 -->
	<div id="baojiaDiv" style="background:#fafafa;padding:20px;width:500px;height:300px;display: none;border: solid gray 1px;margin-top: 100px; overflow-y:auto;">
		<table id="baojiabiao"></table><br>
		<div align="right" style="width: 480px;">
		    <a href="#" onclick="addbaojia();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    <a href="#" onclick="hicacel();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		</div>
	</div>
	
		 <!-- 合同执行弹出层 -->
		<div id="executeDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同执行" style="background:#fafafa;padding:20px;width:700px;height:600px;">
		<div>
		  <input id="corids" type="text" style="width:200px;display: none;"/>
		  <input id="projectId" type="text" style="width:200px;display: none;">
		  <font style="font-size: 13px;">订&nbsp;单&nbsp;号&nbsp;：&nbsp;</font><input id="ordernum" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		</div>
		<div style="margin-top: 10px">
		  <font style="font-size: 13px;">收货人&nbsp;&nbsp;：&nbsp;</font>
		  <input id="consignee" class="easyui-validatebox" type="text" style="width:200px" data-options="required:true"/>
		</div>
	    <div style="margin-top: 10px">
		  <font style="font-size: 13px;">联系方式&nbsp;：</font>
		  <input id="consigneePhone"  class="easyui-validatebox" type="text" style="width:200px"  data-options="required:true"/>
		</div><br>
		<div>
		<font style="font-size: 13px;">收货地址&nbsp;：&nbsp;</font><input class="easyui-combobox" id="address" name="customer.jobPlace.id" style="width: 400px"  data-options="required:true"> 
		</div>
		<div style="margin-top: 10px">
		  <font style="font-size: 13px;">备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;：</font>
		  <input id="description" type="text" style="width:200px" name="project.description"/>
		</div><br>
		<div style="overflow-y: scroll;height: 260px;">
			<table id="execute"></table>
		</div><br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="createAndSaveOrder();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		    <a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		</div>
	</div>
	
	<!-- 合同执行通用单元和数量配置弹出层 -->
	<div id="numberDIV" style="background:#fafafa;padding:20px;width:650px;height:360px;display: none;border: solid gray 1px;margin-top: 100px; overflow-y:auto;">
		<div>
<!-- 		  选择行的可采购数 -->
		  <input id="yicaigou" type="text" style="width:200px;display: none;" />
<!-- 		  选择行的索引 -->
		  <input id="praindex" type="text" style="width:200px;display: none;" />
<!-- 		  选择行的单价 -->
		  <input id="totalmoney" type="text" style="width:200px;display: none;" />
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
	<!-- 付款项弹出层 -->
	<div id="PaymentDIV"  class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="查看金额" style="background:#fafafa;padding:20px;width:1100px;height:400px;" iconCls="icon-add">
	    <form id="addVmform1111" method="post"  enctype="multipart/form-data" novalidate style="width: 1000px;height:320px;">
	       <div>
	       <input id="id" type="hidden" name="contractReceivePayment.id" style="width:200px" readonly="readonly"/>
	       <input id="contractId" type="hidden" name="contractReceivePayment.contract.id" style="width:200px" readonly="readonly"/>
	       <input id="purchaseCompanyId" type="hidden"  name="contractReceivePayment.purchaseCompany.id" style="width:200px" readonly="readonly"/>
	       	 <font style="font-size: 13px;">合&nbsp;同&nbsp;名&nbsp;称&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;</font><input id="contract" type="text" style="width:200px" readonly="readonly"/>
	         <font style="font-size: 13px;">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;：&nbsp;</font><input id="customer" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	        <font style="font-size: 13px;">合&nbsp;同&nbsp;总&nbsp;价&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;</font><input id="contractTotalPrice" name="contractReceivePayment.contractTotalPrice" readonly="readonly"  type="text" style="width:200px"/> 
	       	 <font style="font-size: 13px;">合同总价大写&nbsp;：&nbsp;</font><input id="contractTotalPriceText" name="contractReceivePayment.contractTotalPriceText" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
	       </div>
	       <br>
	        <div>
	          <font style="font-size: 13px;">货款的百分比&nbsp;&nbsp;：&nbsp;&nbsp;</font><input id="contractGoodsPercent" name="contractReceivePayment.contractGoodsPercent" readonly="readonly"  type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款&nbsp;&nbsp;：&nbsp;</font><input id="contractGoodsPrice" name="contractReceivePayment.contractGoodsPrice" readonly="readonly" type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp;
	          <font style="font-size: 13px;">货&nbsp;款&nbsp;大&nbsp;写&nbsp;：&nbsp;</font><input id="contractGoodsPriceText" name="contractReceivePayment.contractGoodsPriceText" type="text" style="width:200px" readonly="readonly" />
	       </div>
	       <br>
	         <div>
	          <font style="font-size: 13px;">初验款项百分比&nbsp;：&nbsp;</font><input id="contractBeginPercent" name="contractReceivePayment.contractBeginPercent"  readonly="readonly" type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">初&nbsp;验&nbsp;款&nbsp;项&nbsp;&nbsp;：&nbsp;</font><input id="contractBeginPrice" name="contractReceivePayment.contractBeginPrice"  readonly="readonly"  type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp;  
	          <font style="font-size: 13px;">初验款项大写&nbsp;：&nbsp;</font><input id="contractBeginPriceText" name="contractReceivePayment.contractBeginPriceText" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	          <font style="font-size: 13px;">终验款项百分比&nbsp;：&nbsp;</font><input id="contractFinalPercent" name="contractReceivePayment.contractFinalPercent"  readonly="readonly" type="text" style="width:200px" />
	       	  <font style="font-size: 13px;">终&nbsp;验&nbsp;款&nbsp;项&nbsp;&nbsp;：&nbsp;</font><input id="contractFinalPrice" name="contractReceivePayment.contractFinalPrice"  readonly="readonly" type="text" style="width:200px"/>&nbsp;&nbsp;&nbsp; 
	          <font style="font-size: 13px;">终验款项大写&nbsp;：&nbsp;</font><input id="contractFinalPriceText" name="contractReceivePayment.contractFinalPriceText" type="text" style="width:200px" readonly="readonly"/>
	       </div>
	       <br>
	        <div>
	        <input id="statusId" type="hidden" name="contractReceivePayment.status" style="width:200px" readonly="readonly"/>
	          <font style="font-size: 13px;">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态&nbsp;：&nbsp;</font><input id="status" type="text" name="status" style="width:200px" readonly="readonly"/>
	       	  <font style="font-size: 13px;">公&nbsp;司&nbsp;名&nbsp;称&nbsp;&nbsp;：&nbsp;</font><input id="purchaseCompany" type="text" style="width:200px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
	       </div>
	       <br>
		<br>
		<div align="right" style="width: 1000px;">
		    <a href="#" onclick="hideDiv2();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
		</div>
		</form>
	</div>
	
	    <!-- 分配任务给下一个处理人 -->
	<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="提交合同审核" style="padding: 10px;width: 350px;height:250px;overflow:hidden" iconCls="icon-add">
   	  <form id="addVmform1" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="processdiv" style="float: center;">
    			<input type="hidden" id="checkedVo_id" name="checkedVo.id"/>
    			<input type="hidden" id="checkedVo_projectId" name="checkedVo.projectId"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					 <select id="tbUser" name="checkedVo.user_id" style="width:180px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备   注：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <textarea id="describe"  class="easyui-validatebox" data-options="required:false" rows="4" cols="30" name="checkedVo.describe"></textarea></div>
	    	    </div><br>
	    	    <div align="right" >
			    	<a href="javascript:void(0);" onclick="submitCheck();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 		</div>
      	</form>
	</div>
</body>
	