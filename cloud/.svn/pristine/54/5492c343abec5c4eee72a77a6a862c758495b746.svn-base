<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/images_controller.js"></script>
<script type="text/javascript" src="view/images_view.js"></script>
<script>
//用户信息列表
$(function(){
	$('#tt').datagrid({
		url:ctx+'/server/server/service!listServices.action',
		idField:'id',
		width: 'auto',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[
			{field:'id',title:'ID',width:60,align:'center',hidden:true},
			{field:'name',title:'名称',width:60,align:'center'},
			{field:'type',title:'服务',width:30,align:'center'},
			{field:'description',title:'描述',width:30,align:'center'}/*,
		]],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
});
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="服务管理" style="overflow: hidden; padding: 10px;">
		<table id="tt"></table>
	</div>
</body>
</html>