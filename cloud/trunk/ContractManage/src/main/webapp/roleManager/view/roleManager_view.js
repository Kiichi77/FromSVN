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
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'角色id',width:60,align:'center',hidden:true},
	{field:'rolename',title:'角色',width:100,align:'center'},
	{field:'note',title:'描述',width:80,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="#" onclick="deleteDataSource(\''+id+'\')">删除</a>&nbsp;|&nbsp;<a href="#" onclick="editRoleInfo(\''+id+'\',\''+rec.rolename+'\',\''+rec.note+'\')">修改</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'role/role/role!queryAllRole.action',
		width: '95%',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
	    pagination:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.post(ctx+ 'right/right/right!queryMenu.action',function(data) {
					$('#tt2').tree({ data: data.list });
				});
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeRole();
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
