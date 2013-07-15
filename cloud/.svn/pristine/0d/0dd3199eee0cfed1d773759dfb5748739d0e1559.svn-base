//可选配置
var firsts = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center',editor:'text'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center'},
	{field:'totalprice',title:'目录合价',width:60,align:'center'},
	{field:'discountRate',title:'折扣率',width:50,align:'center'},
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center'},
	{field:'otherRates',title:'运保及其他费率（%）',width:50,align:'center'},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center'},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:50,align:'center'}
]];

//动态添加tab元素
function wantNewTabFun(paramName,tdata) {
	var paramsIds = "tabl"+addtabIds;
	var cid = "col"+addtabIds;
	$('#myTabs').tabs('add',{
		title:paramName,
		content:"<div><table id='"+paramsIds+"'></table></div>",
		iconCls:"icon-save",
		closable:false
	});
	loadDataforGrideTable(tdata,paramsIds,cid)
	addtabIds++;
}

//为列表加载数据的方法
function loadDataforGrideTable(tdata,paramsIds,cid) {
	$('#'+paramsIds+'').datagrid({
		width: '95%',
//		url: 'Json/datagrid_data.json',
		height: 'auto',
		pagination:false,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
		fitColumns: true,
		columns:firsts,
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu(paramsIds,cid);
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
			
		}
	});
	$('#'+paramsIds+'').datagrid('loadData',tdata);
}

//创建列菜单
function createColumnMenu(paramsId,cid){
	var tmenu = $('<div id="'+cid+'" style="width:100px;"></div>').appendTo('body');
	var fields = $('#'+paramsId+'').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#'+paramsId+'').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#'+paramsId+'').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}