//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: ctx+'/customer111/customer111/customer111!listCustomer.action',
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'companyName',title:'公司名称',width:80,align:'center'},	
					{field:'jobPlaceName',title:'省份',width:80,align:'center'},	
					{field:'contact',title:'负责人',width:80,align:'center'},
					{field:'phone',title:'电话',width:80,align:'center'},
					{field:'email',title:'邮箱',width:80,align:'center'},
					{field:'address',title:'注册地址',width:80,align:'center'},	
					{field:'description',title:'描述',width:80,align:'center'},	
					{field:'operate',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								edit='<a href="#" onclick="deleteCustomer(\''+id+'\')">删除</a>&nbsp;|&nbsp;<a href="#" onclick="updateCustomer(\''+id+'\')">修改</a>';
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
							$('#btnsave').linkbutton('enable');
							deleteSomeCustomer();
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
	//分页工具条
	 $('#tt').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });

	 $('#jobPlace').combobox({ 
		 url:ctx+'/role/role/role!selectJobPlace.action', 
		 valueField:'id', 
		 textField:'jobPlaceName'
		 }); 
	 
	 $('#jobPlace1').combobox({ 
		 url:ctx+'/role/role/role!selectJobPlace.action', 
		 valueField:'id', 
		 textField:'jobPlaceName'
		 }); 

});	
//弹出层***************************************
//添加
function showWindow() {
	  $('#addVmDiv').window('open');
}
//修改
function showWindow1() {
	 $('#addVmDiv1').window('open');
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