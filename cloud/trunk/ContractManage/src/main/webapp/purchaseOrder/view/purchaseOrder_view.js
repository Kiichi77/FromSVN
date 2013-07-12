/**
 * 采购申请单
 */
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'id',title:'采购申请单id',width:60,align:'center',hidden:true},
	{field:'purchaseOrderName',title:'采购申请单名称',width:80,align:'center'},
	{field:'purchaseOrderNumber',title:'采购申请单单号',width:80,align:'center'},
	{field:'sellOrderNumber',title:'销售订单号',width:80,align:'center'},
	{field:'purchaser',title:'采购人员',width:80,align:'center'},
	{field:'startTime',title:'采购开始时间',width:80,align:'center'},
	{field:'endTime',title:'采购结束时间',width:100,align:'center'},
	{field:'state',title:'状态',width:100,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="#" onclick="deleteSomeData('+index+')">删除</a>&nbsp;|&nbsp;<a href="#" onclick="showWindow()">修改</a>&nbsp;|&nbsp;<a href="#" onclick="showOrderDetail()">查看订单</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/purchaseOrder/view/purchaseOrderlist.json',
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
//		toolbar:[{
//			id:'btnadd',
//			text:'制作采购申请单',
//			iconCls:'icon-add',
//			handler:function(){
//				$('#btnsave').linkbutton('enable');
//				showWindow();
//			}
//		},{
//			id:'btndelete',
//			text:'删除采购申请单',
//			iconCls:'icon-remove',
//			handler:function(){
//				$('#btnsave').linkbutton('enable');
//				deleteSomeData(-1);
//			}
//		}],			
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
	 
	 $(".datagrid-toolbar").append($("#datagridsearch"));
});
