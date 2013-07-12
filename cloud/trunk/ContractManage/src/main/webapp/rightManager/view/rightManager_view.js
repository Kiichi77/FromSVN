//修改角色信息
function editRoleInfo(paramsid,paramsname,paramsnote) {
	$('#jsids').val(paramsid);
	$('#jsname').val(paramsname);
	$('#jsnode').val(paramsnote);
	$.post(ctx+ 'role/role/role!selectRole.action',{'model.id':paramsid},function(data) {
		$('#tt2').tree({ data: data.root });
	});
	showWindow();
}

//单元数据总览列布局
var dyDataLayOut = [[  		  
	{field:'id',title:'权限序号',width:20,align:'center'},
	{field:'rightName',title:'权限',width:50,align:'center'},
	{field:'rightLevel',title:'级别',width:20,align:'center'},
	{field:'url',title:'地址',width:150,align:'center'},
	{field:'parid',title:'父级序号',width:20,align:'center'},
	{field:'parName',title:'父级菜单',width:50,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="#" onclick="deleteDataSource(\''+id+'\')">删除</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'right/right/right!allList.action',
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				showWindow();
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
	//分页工具条
	 $('#tt').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	 
	 $('#tt2').tree({
		checkbox: true,
		onClick:function(node){
			$(this).tree('toggle', node.target);
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			$('#tt2').tree('select', node.target);
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
	});
});
