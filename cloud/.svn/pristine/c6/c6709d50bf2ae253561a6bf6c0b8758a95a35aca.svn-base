<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String ctxPath = session.getAttribute("realPath").toString();
/* String opt = request.getParameter("param");
if(null!=opt && ""!=opt){
	if("1".equals(opt)){
		request.setAttribute("vnc", "selected:true");
		request.setAttribute("opt", "selected:false");
	}else if("2".equals(opt)){
		request.setAttribute("vnc", "selected:false");
		request.setAttribute("opt", "selected:true");
	}
}
 */
 String serverId = request.getParameter("serverId");
 request.setAttribute("serverId", serverId);
 // System.out.println(ctxPath+"*********WWW*********");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<head>
<style type="text/css">
dt {
	margin-left: 22px;
	font-size: 16px;
}
dd {
	font-size: 13px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>虚拟机详情和VNC操作界面</title>
<script type="text/javascript">
	var serverId = '${serverId}';
</script>
<script type="text/javascript" src="view/woyun_virtualMachineInfo_view.js"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'" title="虚拟机详情"  style="overflow:hidden;padding:10px;margin-top: 20px;" align="center">
		<div id="infop" class="easyui-panel" style="width:99;height:550px;padding:10px;"
				data-options="collapsible:false,minimizable:false,maximizable:false,closable:false">
			<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:99;">
				<div title="虚拟机信息总览" style="padding:10px;" align="left">
					<h2>资讯</h2>
					<hr>
					<dl>
						<dt><b>名称</b></dt>
						<dd id="name"></dd>
						<dt><b>ID</b></dt>
						<dd id="id"></dd>
						<dt><b>状态</b></dt>
						<dd id="state"></dd>
					</dl><br>
					<h2>模板</h2>
					<hr>
					<dl>
						<dt><b>内存</b></dt>
						<dd id="memory"></dd>
						<dt><b>虚拟处理器</b></dt>
						<dd id="cpu"></dd>
						<dt><b>硬盘</b></dt>
						<dd id="disk"></dd>
					</dl><br>
					<h2>IP地址</h2>
					<hr>
					<dl>
						<dt><b>Br100</b></dt>
						<dd id="address"></dd>
					</dl><br>
					<h2>安全性群组</h2>
					<hr>
					<dl id="securityGroup">
					</dl><br>
					<h2>相关资料</h2>
					<hr>
					<dl>
						<dt><b>Key Name</b></dt>
						<dd id="keyName"></dd>
						<dt><b>镜像名称</b></dt>
						<dd id="imagename"></dd>
					</dl><br>
					<h2>存储</h2>
					<hr>
					没有挂载的存储
				</div>
				<div title="VNC操作" id="cc" data-options="closable:false,cache:false" style="padding:20px;">
					<div id="vncp" class="easyui-panel" style="width:99;height:550px;padding:10px;"
				data-options="collapsible:false,minimizable:false,maximizable:false,closable:false"></div>
				</div>
				<div title="硬件设备设置" data-options="closable:false" style="padding:20px;background:#fafafa;" align="left">
					<h2>虚拟机硬件设备配置</h2>
					<hr>
					<div style="float: left;">
						<h2>当前设备配置参数</h2>
						<dl>
							<dt><b>内存</b></dt>
							<dd>2GB</dd>
							<dt><b>虚拟处理器</b></dt>
							<dd>1 虚拟处理器</dd>
							<dt><b>硬盘</b></dt>
							<dd>60GB</dd>
							<dt><b>网卡</b></dt>
							<dd>1 网卡	</dd>
						</dl><br>
					</div>
					<div style="float: left;margin-left: 200px; margin-top: 60px;">
						<label for="vmCupNum">选择要添加设别的类型:</label><br>
						<select id="cc" class="easyui-combobox" name="state" style="width:200px;" data-options="required:true">
					     	<option value="1" selected="selected">内存(MB)</option>
					     	<option value="2">硬盘(GB)</option>
					     	<option value="3">网卡(个)</option>
					     	<option value="4">CPU(个)</option>
					     </select><br><br>
						<div>
							<label for="vmMemory">添加大小:</label><br>
							<input class="easyui-validatebox" type="text" name="vmMemory"></input>
						</div><br>
						<div>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="tab-tools">
			<a href="woyun_virtualMachine.jsp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">返回</a>
		</div>
	</div>
</body>
</html>