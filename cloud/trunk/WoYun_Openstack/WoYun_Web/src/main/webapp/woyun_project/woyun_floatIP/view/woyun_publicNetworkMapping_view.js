//虚机信息列表
$(function(){
	$('#virtualMachineInfo').datagrid({
		url: ctx+'/server/server/floatIp!listFloatingIp.action',
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
		    {field:'id',title:'id',width:60,align:'center'},
			{field:'instanceId',title:'运行实例',width:60,align:'center'},
			{field:'fixedIp',title:'内网IP',width:60,align:'center'},
			{field:'ip',title:'外网IP',width:60,align:'center'},
			{field:'pool',title:'浮动IP集',width:60,align:'center'},
			{field:'dosometihe',title:'操作',width:100,align:'center',
				formatter:function(value,rec,index){
					var id = rec.id;
					var ip = rec.ip;
					var release = rec.instanceId;
					var edit="";
					if(null==release || ""==release){
						edit += '<a href="#" onclick="deallocateFloatingIp(\''+id+'\');">释放浮动IP</a>| <a href="#" onclick="allocationIp(\''+ip+'\');">配给IP</a>';
					}else{
						edit += '<a href="#" onclick="releaseIp(\''+ip+'\',\''+release+'\');">释放浮动IP</a>';
					}
                    return edit;
                } 
			}
		]],
		toolbar:[{
			id:'btnadd',
			text:'申请浮动IP',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				applyForIp();
			}
		},{
			id:'btndelete',
			text:'释放浮动IP',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				liberateIp();
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
	
	getInstance();
	getPools();
	
});	

/**
 * 获取所有实例
 */
function getInstance(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/server!listServers.action',
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var instance = $("#instance");  
				instance.empty();//清除select中的 option
				$.each(obj,function(i,n){
					var opt = $("<option>").text(n.name).val(n.serverId);  
					instance.append(opt);  
				});
			}
		}
	);
}

/**
* 获取所所有的浮动ip池的集合
*/
function getPools(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/floatIp!listFloatingIpPools.action',
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var allocation_pools = $("#Allocation_pools");  
				allocation_pools.empty();//清除select中的 option
				$.each(obj,function(i,n){
					var opt = $("<option>").text(n.name).val(n.name);  
					allocation_pools.append(opt);  
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