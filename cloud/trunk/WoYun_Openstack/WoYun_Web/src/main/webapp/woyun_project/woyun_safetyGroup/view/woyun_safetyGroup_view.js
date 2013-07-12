//虚拟机操作界面的安全性群组表单
function createSaftyTable(id){
	$('#policeTable').datagrid({
		title: '安全性群组',
		url:ctx+'/server/server/securityGroup!listSecurityGroupRule.action?id='+id,
		width: 550,
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
		          	{field:'id',title:'id',width:60,align:'center',hidden:true},
					{field:'ipProtocol',title:'IP协定',width:60,align:'center'},
					{field:'formPort',title:'从端口',width:60,align:'center'},
					{field:'toPort',title:'到端口',width:60,align:'center'},
					{field:'iprangvo',title:'来源',width:60,align:'center',
						formatter:function(value,rec,index){
							return value.cidr+"(CIDR)";
						}	
					},
					{field:'dosometihe',title:'动作',width:60,align:'center',
						formatter:function(value,rec,index){
							var id = rec.id;
							var edit = '<a href="#" onclick="deleteRule(\''+id+'\');">删除 规则</a>';
		                    return edit;
		                } 
					}
				]],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#ttmenu').length){
				createSaftyColumnMenu();
			}
			$('#ttmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
}

function createSaftyColumnMenu(){
	var tmenu = $('<div id="ttmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#policeTable').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#policeTable').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#policeTable').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}

//获取安全组列表
function getSecurityGroup(id){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/securityGroup!listSecurityGroup.action',
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var securityGroup = $("#securipty_group");
				securityGroup.empty();//清除select中的 option
				var opt = $("<option>").text('CIDR').val(0); 
				securityGroup.append(opt);
				$.each(obj,function(i,n){
					if(id!=n.number){
						opt = $("<option>").text(n.name).val(n.number);  
					}
					securityGroup.append(opt);  
				});
			}
		}
	);
}

// 安全组信息列表
$(function(){
	$('#safetyGroupInfo').datagrid({
		url: ctx+'/server/server/securityGroup!listSecurityGroup.action',
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
		    {field:'number',title:'id',width:60,align:'center',hidden:true},
			{field:'name',title:'名称',width:60,align:'center'},
			{field:'description',title:'叙述',width:60,align:'center'},
			{field:'dosometihe',title:'操作',width:50,align:'center',
				formatter:function(value,rec,index){
					var id = rec.number;
					var edit = '<a href="#" onclick="showpoliceWindow(\''+id+'\');">编辑规则</a>| <a href="#" onclick="deleteGroup(\''+id+'\');">删除安全性群组</a>';
                    return edit;
                } 
			}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加安全性群组',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除安全性群组',
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
	
	var p = $('#safetyGroupInfo').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});	
//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#safetyGroupInfo').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#safetyGroupInfo').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#safetyGroupInfo').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}