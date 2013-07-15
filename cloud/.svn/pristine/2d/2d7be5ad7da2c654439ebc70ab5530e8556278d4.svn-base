<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cn.com.woyun.keystone.model.Tenant" %>
<%@ include file="../view/head.jsp"%>
<%
String srealPath = session.getAttribute("realPath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>top</title>
<script type="text/javascript">
$(function(){
	var s = $("#cc");  
	s.empty();//清除select中的 option
	var tenantss = null;
	$('#menu').accordion('select',0);
	$('#menu').accordion({
		onSelect:function(){
			var pp = $('#menu').accordion('getSelected');
			if (pp){
				var index = $('#menu').accordion('getPanelIndex',pp);
			}
		}
	});
	<% 
		List<Tenant> tens = (List<Tenant>) session.getAttribute("userTenants");
		for(int i = 0;i < tens.size(); i++) {
	%>
		var opt = $("<option>").text('<%=tens.get(i).getName()%>').val('<%=tens.get(i).getId()%>');  
		s.append(opt);  
	<%}%>
});
</script>
</head>
<body class="easyui-layout" >
	<div data-options="region:'west',split:true" title="菜单列表" style="width:200px;padding1:1px;overflow:hidden;">
		<div title="所属项目" style="padding:10px;">所属项目:
				<select id="cc" name="state" style="width:170px;" data-options="required:true">
<!-- 				     	<option value="1" selected="selected">admin</option> -->
<!-- 				     	<option value="2">client</option> -->
				</select>
		</div>
		<div id="menu" class="easyui-accordion" data-options="fit:true,border:false">
			<div title="项目" style="padding:10px;">
				<p><a href="<%=realPath%>woyun_project/woyun_virtualMachine/woyun_virtualMachine.jsp" target="main">虚拟机</a></p>
				<p><a href="<%=realPath%>woyun_project/woyun_storage/woyun_storage.jsp" target="main">存储</a></p>
				<p><a href="<%=realPath%>woyun_project/woyun_floatIP/woyun_floatIP.jsp" target="main">浮动IP</a></p>
				<p><a href="<%=realPath%>woyun_project/woyun_safetyGroup/woyun_safetyGroup.jsp" target="main">安全组</a></p>
				<p><a href="<%=realPath%>woyun_project/woyun_publicNetworkMapping/woyun_publicNetworkMapping.jsp" target="main">公网映射</a></p>
			</div>
			<div title="监控" style="padding:10px">
				<p><a href="<%=realPath%>monitor/project/pro_monitor.jsp" target="main">项目总览</a></p>
				<p><a href="<%=realPath%>monitor/vm/vm_monitor.jsp" target="main">虚拟机总览</a></p>
				<p><a href="<%=realPath%>monitor/alarm/alarm.jsp" target="main">报警</a></p>
			</div>
			<div title="管理" style="padding:10px;overflow:auto;">
				<p><a href="<%=realPath%>syspanel/services/service.jsp" target="main">服务</a></p>
				<p><a href="<%=realPath%>syspanel/flavors/flavor.jsp" target="main">模板</a></p>
				<p><a href="<%=realPath%>syspanel/images/image.jsp" target="main">镜像</a></p>
				<p><a href="<%=realPath%>syspanel/projects/project.jsp" target="main">项目</a></p>
				<p><a href="<%=realPath%>syspanel/users/users.jsp" target="main">用户</a></p>
				<p><a href="<%=realPath%>syspanel/quotas/quota.jsp" target="main">配额</a></p>
			</div>
		</div>
	</div>
	<div id="themains" data-options="region:'center'" title=""  style="overflow:hidden;padding:10px;margin-top: 40px;">
	</div>
</body>
</html>