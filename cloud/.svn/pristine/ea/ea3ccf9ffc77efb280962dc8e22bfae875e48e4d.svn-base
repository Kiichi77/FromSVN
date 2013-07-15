/**
 *采购订购单 
 */
//跳转至采购订单新增界面
function showExecuteDIV(paramsId) {
	window.location.href = ctx+"purchaseOrder/addOrders.jsp?paramsId="+paramsId;
}

//单元数据总览列布局
var dyDataLayOut = [[ 
		{field:'contractOrderId',title:'ID',width:40,align:'center',hidden:true},
		{field:'orderNo',title:'订单编号',width:150,align:'center'},
		{field:'projectName',title:'项目名称',width:200,align:'center'},
		{field:'contractNo',title:'合同编号',width:200,align:'center'},
		{field:'contractName',title:'合同名称',width:200,align:'center'},
		{field:'contractType',title:'合同类型',width:120,align:'center',formatter:function(value,rec,index){
			 var contractType=rec.contractType;
			 var edit;
			 if(1==contractType){
				 edit='正式合同';
			 }else if(2==contractType){
				 edit='临时合同';
			 }
			  return edit;
		 }
	},
	{field:'receiveInfo',title:'收货地址',width:400,align:'center'},
	{field:'note',title:'备注',width:200,align:'center'},
	{field:'status',title:'订单状态',width:120,align:'center',formatter:function(value,rec,index){
			 var status=rec.status;
			 var edit;
			 if(0==status){
					return '询价中';
				 }else if(1==status){
					 return '审核中';
				 }else if(2==status){
					 return '审核通过';
				 }else if(3==status){
					 return '审核不通过';
				 }else if(4==status){
					 return '采购中';
				 }else if(5==status){
					 return '到货';
				 }else if(6==status){
					 return '完成';
				 }else if(7==status){
					 return '采购完成';
				 }
		 }
	},
	{field:'opt',title:'操作',width:300,align:'center',formatter:function(value,rec,index){
			var contractOrderId=rec.contractOrderId;
			var status = rec.status;
			var edit = '';
			if(2==status||4==status){
				edit='<a href="#" onclick="showExecuteDIV(\''+contractOrderId+'\');">执行</a>&nbsp;|&nbsp;';
			}
			if(4==status||5==status||6==status||7==status){
				edit +='<a href="#" onclick="showPurchaseOrder(\''+contractOrderId+'\');">查看</a>';
			}
			return edit;
		}
	}
]];

$(function(){
	$('#tt').datagrid({
		url: ctx+'order/order/order!myContractOrder.action',
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
//	 $(".datagrid-toolbar").append($("#datagridsearch"));
});


//配置详情
function wantTongyongInfo(id) {
	var div_obj = $("#numberDIV");
	div_obj.window('open');
	
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+id,
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
				{field:'id',title:'id',width:60,align:'center',hidden:true},
				{field:'productOrderNo',title:'产品订货号',width:100,align:'center'},
				{field:'name',title:'产品名称',width:100,align:'center'},
				{field:'quantity',title:'数量',width:100,align:'center'},
				{field:'productDesc',title:'产品描述',width:120,align:'center'},
				{field:'categoryName',title:'产品类型',width:120,align:'center'}
				]],  
	//	toolbar:[],
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