/**
 *采购订购单 
 */
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'purchaseOrderId',title:'purchaseOrderId',width:60,align:'center',hidden:true},
	{field:'configModelsId',title:'ConfigModelId',width:60,align:'center',hidden:true},
	{field:'configModels',title:'机型配置单名称',width:100,align:'center'},
	{field:'companyName',title:'生产厂家',width:100,align:'center'},
	{field:'purchaseQuantity',title:'采购数',width:50,align:'center',editor:'text'},
	{field:'opt',title:'查看通用单元',width:80,align:'center',
		formatter:function(value,rec,index){
			var id = rec.purchaseOrderId;
			var edit = '<a href="#" onclick="wantTongyongInfo(\''+id+'\')">查看通用配置</a>';//
			return edit;
		}
	}			
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'purchaseorder/purchaseorder/relationship!findDetailbyOrderPkid.action?purchaseOrderId='+purchaseOrderId,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				window.location.href='orders.jsp';
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
});

//隐藏通用单元数量设置框
function hideNumberDIV() {   
	$("#numberDIV").animate({left: 0, top: 0, opacity: "hide" }, "slow");
} 

//通用单元表单
function wantTongyongInfo(purchaseOrderDetailId) {
	var div_obj = $("#numberDIV");
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/2, opacity: "show" }, "slow");
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'purchaseorder/purchaseorder/relationship!findUnitDetailByOrderDetailPkid.action?purchaseOrderDetailId='+purchaseOrderDetailId,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
				{field:'ck',checkbox:true,align:'center'},
				{field:'id',title:'id',width:60,align:'center',hidden:true},
				{field:'productDesc',title:'单元名称描述',width:100,align:'center'},
				{field:'ordered',title:'采购数',width:100,align:'center'}
				]],  
		toolbar:[],
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
}
