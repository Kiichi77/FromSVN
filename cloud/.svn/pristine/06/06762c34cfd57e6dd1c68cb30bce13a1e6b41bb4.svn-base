//虚机信息列表
$(function(){
	$('#virtualMachineInfo').datagrid({
		url: ctx+'/server/server/server!listServers.action',
//		title: '所有服务',
		id:'serverGrid',
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
		    {field:'serverId',title:'实例id',width:60,align:'center',hidden:true},
			{field:'name',title:'实例名称',width:60,align:'center'},
			{field:'privateIP',title:'IP地址',width:60,align:'center'},
			{field:'progress',title:'大小和设备',width:90,align:'center'},
			{field:'state',title:'状态',width:30,align:'center'},
			{field:'workState',title:'工作',width:30,align:'center'},
			{field:'powerState',title:'电源状态',width:30,align:'center'},
			{field:'updatedTime',title:'运行时间',width:40,align:'center'},
			{field:'dosometihe',title:'操作',width:50,align:'center',
				formatter:function(value,rec,index){  
					var state = rec.state;
					var powerState = rec.powerState;
					var serverId = rec.serverId;
					var edit;
					if("PAUSED" == state && "Paused" == powerState){
						edit = '<select id="edit" class="easyui-combobox" name="state" style="width:150px;"'+
										   'data-options="required:true" onchange="changeForm(this,\''+serverId+'\')" >'+
										   '<option value="0"  selected="selected">请选择操作</option>'+
										   '<option value="1">取消暂停 运行实例</option>'+
								     	   '<option value="2">终止 运行实例</option>'+
								     	   '</select>';
					}else{
						edit = '<select id="edit" class="easyui-combobox" name="state" style="width:150px;"'+
										   'data-options="required:true" onchange="changeForm(this,\''+serverId+'\')" >'+
										   '<option value="0" selected="selected">请选择操作</option>'+
										   '<option value="3">编辑运行实例</option>'+
								     	   '<option value="4">VNC界面</option>'+
								     	   '<option value="5">查看日志</option>'+
								     	   '<option value="6">暂停 运行实例</option>'+
								     	   '<option value="7">启动 运行实例</option>'+
								     	   '<option value="8">重启运行实例</option>'+
								     	  '<option value="2">终止 运行实例</option>'+
								     	'</select>';
					}
                    return edit;
                }  
			}
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
				deleteServer();
			}
		},{
			id:'btnflush',
			text:'刷新',
			iconCls:'icon-reload',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$("#virtualMachineInfo").datagrid("reload");
			}
		}],onDblClickRow: function(rowIndex,rowData) {
			updateInstanceshebei(1,rowData.serverId)
		}, 
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
	
	getFlavors();
	getImage();
	quotaDefault();
});	

//获取模板的下拉框的值
function getFlavors(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+"/server/server/flavors!listFlavors.action",
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var flavors = $("#flavors");  
				flavors.empty();//清除select中的 option
				$.each(obj,function(i,n){
					var opt = $("<option>").text(n.name).val(n.id);  
					flavors.append(opt);  
				});
			}
		}
	);
}

//获得镜像的下拉框的值
function getImage(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+"/server/server/images!listImages.action",
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var flavors = $("#images");  
				flavors.empty();//清除select中的 option
				$.each(obj,function(i,n){
					var opt = $("<option>").text(n.name).val(n.id);  
					flavors.append(opt);  
				});
			}
		}
	);
}


//获取配额信息
function quotaDefault(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+"/server/server/quotaSet!listQuotaSet.action",
		function(data){
			if(null!=data && ""!=data){
				var obj = data.volist;
				$.each(obj,function(i,n){
					$('#instance').progressbar('setValue', n.instance);
					$('#cpu').progressbar('setValue', n.cores);
					$('#hardiskValue').progressbar('setValue', Math.floor(n.ram%100));
					$('#memoryValue').progressbar('setValue', Math.floor(n.gigabytes%100));
				});
			}
		}
	);
}

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
/***********************************************安全性群组表单start************************************************/
//虚拟机操作界面的安全性群组表单
function createSaftyTable(){
	$('#saftygroup').datagrid({
		title: '安全性群组',
		url: ctx+'/server/server/securityGroup!listSecurityGroup.action',
		width: 450,
		id:'securitygrid',
		height: 'auto',
		fitColumns: true,
		columns:[[
				    {field:'ck',checkbox:true,align:'center'},
					{field:'number',title:'安全性群组序号',width:60,align:'center'},
					{field:'name',title:'安全性群组名称',width:60,align:'center'}
				]],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#ttmenu').length){
				createColumnMenu();
			}
			$('#ttmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
}

function createColumnMenu(){
	var tmenu = $('<div id="ttmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#saftygroup').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#saftygroup').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#saftygroup').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}
