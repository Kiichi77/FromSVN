<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
    request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/systemUser_controller.js"></script>
<script type="text/javascript" src="view/systemUser_view.js"></script>
<script type="text/javascript">
</script>

<body class="easyui-layout">
<!-- 
	<div id="datagridsearch">
		用户名：<input id="kword" name="kword" class="easyui-validatebox" style="width:150px"></input>
		<a href="javascript:void(0);" onclick="search();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查 询</a>
	</div> -->
	<div data-options="region:'center'" title="账户信息总览" style="overflow:hidden;">
		 <table id="tt">
	     </table>
	</div>
	<div id="addDataInfoDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="账户信息" style="background:#fafafa;padding:20px;width:700px;height:400px;overflow:hidden">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 640px;height:400px;">
	    	<div id="addthevmleft" style="width: 310px;height:240px;float: left;">
				<input id="userId" name="model.id" type="hidden">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">用户名：</font>&nbsp;&nbsp;
		    	    <input id="accouont" name="model.accouont" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br>
	    	    <div id="pwd" style="margin-left: 20px"><font style="font-size: 13px;">密&nbsp;&nbsp码：</font>&nbsp;&nbsp;
	    	    	<input  id="password" name="model.password" class="easyui-validatebox" type="password" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br id="aa">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">邮&nbsp;&nbsp箱：</font>&nbsp;&nbsp;
	    	    	<input id="email" name="model.email" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">职&nbsp;&nbsp;务：</font>&nbsp;&nbsp;
					<select id="jobDuties" name="model.jobDuties.id" style="width:160px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备&nbsp;&nbsp注：</font>&nbsp;&nbsp;
	    	    	<textarea id="note" rows="2" cols="20" name="model.note" style="font-size: 13px;"></textarea>  </div>
	    	    <br>
	      	</div>
	      	<div id="addthevmright" style="width: 310px;height:260px;float: right;">
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">角&nbsp;&nbsp&nbsp;&nbsp色：</font>&nbsp;&nbsp;
					<select id="tbRole" name="roleid" style="width:160px;" data-options="required:true"></select>
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">姓&nbsp;&nbsp&nbsp;&nbsp名：</font>&nbsp;&nbsp;
	    	    	<input id="userName" name="model.userName" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">联系方式：</font>&nbsp;&nbsp;
	    	    	<input id="phoneNumber" name="model.phoneNumber" class="easyui-validatebox" type="text" style="width: 160px;"  data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">常驻地：&nbsp;&nbsp;</font>&nbsp;&nbsp;
					<select id="jobPlace" name="model.jobPlace.id" style="width:160px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">是否为接口人：</font>&nbsp;&nbsp;
					<input type="radio" id="isYes"  checked="checked" name="model.isInterface" value="1"/>是&nbsp;&nbsp;
	    	 		<input type="radio" id="isNo"  name="model.isInterface" value="0"/>否
	    	 	</div>
	    	 	<br>
	    	 </div>
	        <div style="width:200px;height:260px;float: right;">
		    	<a href="javascript:void(0);" onclick="addSave();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 	</div>
	   </form>
	</div>
</body>
</html>