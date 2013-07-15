//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: 'Json/datagrid_data.json',
		width: '100%',
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
					{field:'id',title:'ID',width:60,align:'center'},
					{field:'name',title:'公司名称',width:80,align:'center'},	
					{field:'address',title:'注册地址',width:80,align:'center'},	
					{field:'principal',title:'负责人',width:80,align:'center'},
					{field:'phone',title:'电话',width:80,align:'center'},
					{field:'describe',title:'描述',width:80,align:'center'},	
					{field:'operate',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								edit='<a href="#" >删除</a>&nbsp;|&nbsp;<a href="#" onclick="showWindow();">修改</a>';
								return edit;
							}
						}
					]],
					
					toolbar:[ {
						id:'btnadd',
						text:'新增客户',
						iconCls:'icon-add',
						handler:function(){
							showWindow();
						}
					},{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							var row = $('#tt').datagrid('getSelected');
							if (row){
								var index = $('#tt').datagrid('getRowIndex', row);
								$('#tt').datagrid('deleteRow', index);
							}
						}
					}
					],
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
//隐藏存储操信息操作
function hideDiv() {
	$("#mask").remove();
	$("#addVmDiv").animate({
		left : 0,
		top : 0,
		opacity : "hide"
	}, "slow");
}
//弹出层***************************************
function showWindow() {
	var div_obj = $("#addVmDiv");
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	var popupHeight = div_obj.height();
	var popupWidth = div_obj.width();
	div_obj.css({
		"position" : "absolute"
	}).animate({
		left : windowWidth / 2 - popupWidth / 2,
		top : windowHeight / 2 - popupHeight / 2,
		opacity : "show"
	}, "slow");
}
//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#tt').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#tt').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#tt').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
	
	
	
}