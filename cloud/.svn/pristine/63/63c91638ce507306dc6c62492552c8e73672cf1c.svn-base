<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/uploaddata_controller.js"></script>
<script type="text/javascript" src="view/uploaddata_view.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	   
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="单元数据总览" style="overflow:hidden;">
	      <table id="tt"></table>
	</div>
	<div id="addDataInfoDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="单元数据" 
	style="background:#fafafa;padding:20px;width:700px;height:440px;">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 640px;height:360px;">
	    	<div id="addthevmleft" style="width: 310px;height:240px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">所属数据源：</font><select id="shujuyuan" name="model.dataSource.id" style="width:160px;"></select></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">产品类型：</font>&nbsp;&nbsp;<select id="chanpinlei" name="model.productType.id" style="width:160px;"></select></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">产品订货号：</font><input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">产品数量：</font>&nbsp;&nbsp;<input id="chanpinshu" name="model.quantity" id="modelsName" class="easyui-numberbox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">单价（￥）：</font><input id="danjia" name="model.unitPrice" class="easyui-numberbox" type="text" style="width: 164px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">产品备注：</font>&nbsp;&nbsp;<input id="chanpinbei" name="model.remark" class="easyui-validatebox" type="text" style="width: 160px;"/></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">产品描述：</font>&nbsp;&nbsp;<textarea id="chanpinmiaoshu" rows="3" cols="20" name="model.productDesc" style="font-size: 13px;"></textarea>  </div>
	    	    <br>
	    	    <div align="right" style="width: 640px;">
		    	<a href="javascript:void(0);" onclick="saveNewDataInfos();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	      	<div id="addthevmright" style="width: 310px;height:240px;float: right;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">折扣率（%）：</font>&nbsp;&nbsp;&nbsp;&nbsp;<input id="zhekoulv" name="model.discountRate" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">第一年保修期费用：</font><input id="diyibao" name="model.firstYear" class="easyui-numberbox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">第二年保修期费用：</font><input id="dierbao" name="model.secondYear" class="easyui-numberbox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">第三年保修期费用：</font><input id="disanbao" name="model.thirdYear" class="easyui-numberbox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">运保及其他费率（%）：</font><input id="qitafei" name="model.otherRates" class="easyui-validatebox" type="text" style="width: 140px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">安装服务费（￥）：</font>&nbsp;&nbsp;<input id="anzhuangfei" name="model.InstallServiceCharge" class="easyui-numberbox" type="text" style="width: 145px;" data-options="required:true" /></div>
	    	    <br>
	      	</div>
	   </form>
	</div>
	<div id="addVmDiv" class="easyui-window" closed="true" minimizable="false" collapsible="false" title="数据源上传" style="background:#fafafa;padding:20px;width:520px;height:410px;">
	    <form id="addVmform" method="post" enctype="multipart/form-data" novalidate style="width: 450px;height:300px;">
	    	<div id="thevmleft" style="width: 210px;height:280px;float: left;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">数据源名称：</font><input id="modelsName" name="model.name" class="easyui-validatebox" type="text" style="width: 200px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">分类：</font>
	    	    	<select id="sourceType" name="model.dataSourceType.id" style="width:200px;" data-options="required:true"></select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">描述：</font><textarea id="modelsmiaoshu" rows="4" cols="40" name="model.description"></textarea>  </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">上传：</font><input id="modelshangchuan" type="file" name="dataSource"/>  </div>
	    	    <br>
	    	    <div align="right" style="width: 450px;">
		    	<a href="javascript:void(0);" onclick="upload()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   	 	</div>
	      	</div>
	   </form>
	</div>
</body>
</html>