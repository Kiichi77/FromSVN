//虚机信息列表
$(function(){
	$('#virtualMachineInfo').datagrid({
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
			{field:'instanName',title:'项目名称',width:60,align:'center'},
			{field:'winstanAdress',title:'外网IP地址',width:60,align:'center'},
			{field:'winstanSize',title:'外网端口',width:90,align:'center'},
			{field:'ninstanAdress',title:'内网IP地址',width:60,align:'center'},
			{field:'ninstanSize',title:'内网端口',width:90,align:'center'},
			{field:'dosometihe',title:'操作',width:50,align:'center'}
		]],
		toolbar:[{
			id:'btnadd',
			text:'申请公网端口',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				showWindow();
			}
		},{
			id:'btndelete',
			text:'释放公网端口',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('释放成功');
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
	var p = $('#virtualMachineInfo').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});	
//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#virtualMachineInfo').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#virtualMachineInfo').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#virtualMachineInfo').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}