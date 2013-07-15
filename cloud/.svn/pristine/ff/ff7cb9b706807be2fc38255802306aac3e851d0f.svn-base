//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: ctx+'/configModel/configModel/configModel!listConfigModels.action',
		width: '95%',
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
					{field:'dataSourceId',title:'所属数据源id',width:100,align:'center',hidden:true},
					{field:'dataSourceName',title:'所属数据源',width:100,align:'center'},
					{field:'name',title:'配置单名称',width:80,align:'center'},	
					{field:'createtime',title:'创建时间',width:80,align:'center'},	
					{field:'status',title:'状态',width:80,align:'center',
						 formatter:function(value,rec,index){
						 var status=rec.status;
						 var edit;
						 if(0==status){
							  edit='无效';
						 } else if(1==status) {
								   edit='有效';
						 }
						  return edit;
						 }
					},	
					{field:'operate',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								edit='<a href="#" onclick="deleteConfigModels(\''+id+'\')">删除</a>&nbsp;|&nbsp;<a href="#" onclick="updateModels(\''+id+'\')">修改</a>&nbsp;|&nbsp;<a href="#" onclick="ll(\''+id+'\',\''+rec.dataSourceId+'\',\''+rec.dataSourceName+'\')">ll</a>';//
								return edit;
							}
						}
					]],
					
					toolbar:[ {
						id:'btnadd',
						text:'新增配置',
						iconCls:'icon-add',
						handler:function(){
							showWindow();
						}
					},{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							deleteSomeConfig();
						}
					},{
						id:'btnsave',
						text:'生成报价单',					
						iconCls:'icon-save',
						handler:function(){
							showWindow1();
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
	//下拉选择框数据加载
	var sel = $("#dateNames");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/dataSource/dataSource/dataSource!queryAllDataSource.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});		
});	
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