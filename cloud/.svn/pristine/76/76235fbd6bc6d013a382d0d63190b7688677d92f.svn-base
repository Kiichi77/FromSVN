<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils" %>
    <%
String srealPath = session.getAttribute("realPath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript">
function ok() {
	 var id=<%=request.getParameter("id")%>;
		//设置为同步
		$.ajaxSettings.async = true;
		$.post(
			ctx+'user/user/user!findById.action',
			{'model.id':id},
			function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					 if(data.msg=="ok"){
						 var userEntity = data.userEntity;
						 $("#userId").val(userEntity.id);
						 $("#accouont").val(userEntity.accouont);
						 $("#email").val(userEntity.email);
						 $("#note").val(userEntity.note);
						 $("#tbRole").val(userEntity.tbRole.id);
						 $("#userName").val(userEntity.userName);
						 $("#phoneNumber").val(userEntity.phoneNumber);
						 $("#tbRole").val(userEntity.tbRole.id);
					 }else{
						 $.messager.alert("警告","数据加载失败!");
					 }
				}
			});
}
ok();

function closewin()
{
window.close();	
}

function updateUser()
{
	var pwd1=$("#password3").val();
	var pwd2=$("#password2").val();
	var accouont=$("#accouont").val();
	if (null == accouont.trim() || "" == accouont.trim()) {
		$.messager.alert("提示","用户帐号不能为空!");
		return;
	}
	if (null == pwd1.trim() || "" == pwd1.trim()) {
		$.messager.alert("提示","原密码不能为空!");
		return;
	}
	if (null == pwd2.trim() || "" == pwd2.trim()) {
		$.messager.alert("提示","新密码不能为空!");
		return;
	}
	var userId=<%=request.getParameter("id")%>;
	$('#addDataInfoform').form('submit', {
		url:ctx+'user/user/user!updateInfo.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","修改成功!");
				//	closewin();
				}else {
					$.messager.alert("提示","原密码输入错误!");
				}
			}
		}
	}); 
	
}
</script>
</head>
<body class="easyui-layout">
<div id="addDataInfoDiv" style="background:#fafafa;padding:20px;width:300px;height:400px;border: solid gray 1px;">
	    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: 300px;height:360px;">
		<div id="addthevmleft" style="width: 300px;height:240px;float: left;">
				<div style="margin-left: 20px">
				<input id="userId" name="model.id" type="hidden">
				<input id="tbRole" name="roleid" type="hidden">
	    	    <font style="font-size: 13px;">用户名：</font>&nbsp;&nbsp;
		    	    <input id="accouont" name="model.accouont" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br>
	    	    <div id="pwd1" style="margin-left: 20px"><font style="font-size: 13px;">原密码：</font>&nbsp;&nbsp;
	    	    	<input  id="password3" name="oldPasswd" class="easyui-validatebox" type="password" style="width: 160px;" data-options="required:true" value=""/>
	    	    </div>
	    	    <br>
	    	    <div id="pwd2" style="margin-left: 20px"><font style="font-size: 13px;">新密码：</font>&nbsp;&nbsp;
	    	    	<input  id="password2" name="model.password" class="easyui-validatebox" type="password" style="width: 160px;" data-options="required:true" />
	    	    </div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">邮&nbsp;&nbsp;箱：</font>&nbsp;&nbsp;
	    	    	<input id="email" name="model.email" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">姓&nbsp;&nbsp;名：</font>&nbsp;&nbsp;
	    	    	<input id="userName" name="model.userName" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">联系方式：&nbsp;</font>
	    	    	<input id="phoneNumber" name="model.phoneNumber" class="easyui-validatebox" type="text" style="width: 160px;"  data-options="required:true" /></div>
	    	    <br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备&nbsp;&nbsp;注：</font>&nbsp;&nbsp;
	    	    	<textarea id="note" rows="2" cols="20" name="model.note" style="font-size: 13px;"></textarea>  </div>
	     </div> 
	    <br>
	   	 </form>
	  	 <div style="float: right;">
	  	 <input type="submit" onclick="updateUser();" value="提交"/>
		    	<button onclick="closewin();">取消</button>
	   	 </div>	
</div> 
</body>
</html>