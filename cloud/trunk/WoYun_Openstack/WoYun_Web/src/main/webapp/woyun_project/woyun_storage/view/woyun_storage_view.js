//存储信息列表
$(function(){
	$('#storageInfo').datagrid({
		url: 'datagrid_data2.json',
//		title: '所有服务',
		width: 'auto',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[
		    {field:'ck',checkbox:true,align:'center'},
			{field:'instanName',title:'名称',width:60,align:'center'},
			{field:'instanAdress',title:'叙述',width:60,align:'center'},
			{field:'instanSize',title:'大小',width:90,align:'center'},
			{field:'status',title:'状态',width:30,align:'center'},
			{field:'work',title:'挂载',width:30,align:'center'},
			{field:'dosometihe',title:'操作',width:50,align:'center'}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('删除成功');
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
	var p = $('#storageInfo').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});	
//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#storageInfo').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#storageInfo').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#storageInfo').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}