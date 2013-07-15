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
		url:ctx+'/server/server/images!listImages.action',
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
		    {field:'ck',checkbox:true,align:'center'},
			{field:'id',title:'镜像ID',width:60,align:'center',hidden:true},
			{field:'name',title:'镜像名称',width:60,align:'center'},
			{field:'createdAt',title:'创建时间',width:30,align:'center'},
			{field:'status',title:'状态',width:30,align:'center'},
			{field:'protected',title:'公开',width:30,align:'center'},
			{field:'containerFormat',title:'容器格式',width:30,align:'center'},
			{field:'todos',title:'操作',width:50,align:'center',
				//添加超级链 
                formatter:function(value,rowData,rowIndex){ 
                	var userids = ""+rowData.id;
                    return '<a href="#" onclick="deleteImage(\''+userids+'\')">删除</a>';  
               }  
			}
		]],
		toolbar:[{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeImage();
			}
		}],
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
	<div data-options="region:'center'" title="镜像管理" style="overflow: hidden; padding: 10px;">
		<table id="tt"></table>
	</div>
</body>
</html>