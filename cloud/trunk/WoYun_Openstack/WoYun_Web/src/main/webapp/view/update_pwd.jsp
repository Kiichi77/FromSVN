<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	$('#menu').accordion('select',0);
	$('#menu').accordion({
		onSelect:function(){
			var pp = $('#menu').accordion('getSelected');
			if (pp){
				var index = $('#menu').accordion('getPanelIndex',pp);
			//	alert(index);
			}
		}
	});
});
</script>
</head>
<body class="easyui-layout" >
<div id="themains" data-options="region:'center'" title="虚拟机"  style="overflow:hidden;">
	<div id="dd" data-options="iconCls:'icon-save'" style="padding:5px;width:200px;height:195px;" title="修改密码" >
		<div style="background:#fafafa;padding:10px;">
		    <form id="ff" method="post" novalidate>
		        <div>
		            <label for="passwd" >新密码:</label><br/>
		            <input class="easyui-validatebox" type="password" name="name" data-options="required:true"></input>
		        </div>
		         <div>
		            <label for="passwd">确认密码:</label><br/>
		            <input class="easyui-validatebox" type="password" name="name" data-options="required:true"></input>
		        </div>
		    </form>
		    <div>
		    	<a href="#" onclick="javascript:alert('修改成功');" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修 改</a>
		    	<a href="#" onclick="javascript:window.close();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    </div>
		</div>
	</div>
</div>
</body>
</html>