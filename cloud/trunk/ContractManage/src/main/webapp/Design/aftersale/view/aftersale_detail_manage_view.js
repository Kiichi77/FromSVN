/**
 *采购订购单 
 */
//单元数据总览列布局
var dyDataLayOut = [[  	
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'产品id',width:60,align:'center',hidden:true},
	{field:'buyPrice',title:'产品名称',width:80,align:'center'},
	{field:'grossProfit',title:'厂家',width:80,align:'center'},
	{field:'netProfit',title:'数量',width:80,align:'center'},
	{field:'startTime',title:'采购时间',width:80,align:'center'}
]];

$(function(){
	$('#tt1').datagrid({
		url:  'Json/ordersDetaillist.json',
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
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				window.location.href='aftersale_manage.jsp';
			}
		}],		
		toolbar:[ {
			id:'btnadd',
			text:'确认到货',
			iconCls:'icon-save',
			handler:function(){
				alert("确认到货");
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
	// $(".datagrid-toolbar").append($("#datagridsearch"));
	$('#tt2').datagrid({
		url:  'Json/ordersDetaillist.json',
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
			text:'确认到货',
			iconCls:'icon-save',
			handler:function(){
				alert("确认到货");
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
	$('#tt3').datagrid({
		url:  'Json/ordersDetaillist.json',
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
			text:'确认到货',
			iconCls:'icon-save',
			handler:function(){
				alert("确认到货");
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
