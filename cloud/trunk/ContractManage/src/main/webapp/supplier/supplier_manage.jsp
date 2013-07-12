<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/supplier_manage_view.js"></script>
<script type="text/javascript" src="controller/supplier_manage_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="供应商管理" style="overflow:hidden;">
	    <div>
	      <table id="tt"></table>
		</div>
	</div>
	<!-- 弹出层 -->	
	<div id="addVmDiv"  class="easyui-window" closed="true" minimizable="false" collapsible="false" title="供应商" style="background:#fafafa;padding:20px;width:495px;height:500px;">
	    <form id="addVmform" method="post"  enctype="multipart/form-data" novalidate style="width: 435px;height:420px;">
	    	<div id="thevmleft" style="width: 210px;height:380px;float: left;">
	    	    <input id="id" name="suppliers.id" type="hidden" style="width:200px"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">公司名称：</font><input id="companyName" name="suppliers.companyName" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">公司地址：</font><input id="address" name="suppliers.address"  class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">联系人：</font><input id="contact" name="suppliers.contact" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">电话：</font><input id="phone" name="suppliers.phone" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">邮箱：</font><input id="email" name="suppliers.email" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><textarea id="description" rows="4" cols="40" name="suppliers.description"></textarea></div>
	    	     <br>
	      	</div>
	      	<div id="thevmRight" style="width: 210px;height:380px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">纳税人识别号：</font><input id="taxpayerNumber" name="suppliers.taxpayerNumber" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">开户银行：</font><input id="openBank" name="suppliers.openBank" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	    <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">账户名称：</font><input id="accountsName" name="suppliers.accountsName"  class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	    	     <div style="margin-left: 20px"><font style="font-size: 13px;">账户号码：</font><input id="accounts" name="suppliers.accounts" class="easyui-validatebox" type="text" style="width: 180px;" data-options="required:true" /></div>
	    	     <br>
	      	</div>
	      	<div align="right" style="width: 430px;">
		    	<a href="javascript:void(0);" onclick="saveOrUpdateSuppliers();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="javascript:void(0);" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	</div>
	   </form>
	</div>	
	
	 <!-- 分配任务给下一个处理人 -->
	<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="提交供应商审核" style="padding: 10px;width: 350px;height:250px;overflow:hidden" iconCls="icon-add">
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