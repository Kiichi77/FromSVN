/**
 *采购订购单 
 */
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'产品id',width:60,align:'center',hidden:true},
	{field:'protectName',title:'产品名称',width:60,align:'center'},
	{field:'OrdersNumber',title:'采购价',width:80,align:'center'},
	{field:'OrdersName',title:'厂商',width:80,align:'center'},
	{field:'Orderser',title:'数量',width:80,align:'center'}                    
]];

var saleOrderLayOut = [[
	{field:'contractOrderId',title:'ID',width:60,align:'center',hidden:true},
	{field:'orderNo',title:'订单编号',width:200,align:'center'},
	{field:'projectName',title:'项目名称',width:200,align:'center'},
	{field:'contractName',title:'合同名称',width:200,align:'center'},
	{field:'creator',title:'创建者',width:100,align:'center'},
	{field:'createtime',title:'创建时间',width:200,align:'center'},
	{field:'caudit',title:'审核人',width:100,align:'center',hidden:true},
	{field:'purchase',title:'采购人',width:200,align:'center',hidden:true}
]];

//显示通用单元数量设置框
function showNumberDIV(params,yicaigou,paramindex) {
	$("#yicaigou").val(yicaigou);
	$("#praindex").val(paramindex);
	var div_obj = $("#numberDIV");  
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/2-popupWidth/4,top: windowHeight/5-popupHeight/5, opacity: "show" }, "slow");   
	wantTongyongInfo(params);
}

//隐藏通用单元数量设置框
function hideNumberDIV() {   
	$("#yicaigou").val("");
	$("#praindex").val("");
	$("#jxcgsl").val("");
	$("#numberDIV").animate({left: 0, top: 0, opacity: "hide" }, "slow");
} 

$(function(){
	//销售订单信息列表
	$('#saleOrderTable').datagrid({
		url:  ctx+'order/order/order!findById.action?contractOrderId='+contractOrderId,
		title:"选择的销售订单",
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
		fitColumns: true,
		showGroup:true,
		columns:saleOrderLayOut,
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
	
	//下拉选择框数据加载         采购合同
	var sel2 = $("#caigouhetong");  
	sel2.empty();//清除select中的 option
	$.post(ctx+'purchasecontract/purchasecontract/purchasecontract!findAll.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].title).val(datas.rows[i].id);  
				sel2.append(opt);  
			}
		}
	var deis = $("#caigouhetong").val();
	//下拉选择框数据加载         厂家
	var sel1 = $("#changjia");  
	sel1.empty();//清除select中的 option
	$.post(ctx+'suppliers/suppliers/suppliers!findAll.action?id='+deis,
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
				sel1.append(opt);  
			}
		}
		var supp = $("#changjia").val();//生产厂家ID
		//机型配置列表
		$('#execute').datagrid({
			url: ctx+'order/order/order!querySaleOrder.action?id='+contractOrderId+'&suppliersId='+supp,
			width: '95%',
			title:"机型配置信息",
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'contractId',title:'ContractId',width:60,align:'center',hidden:true},
					{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
					{field:'quotationsId',title:'QuotationsId',width:60,align:'center',hidden:true},
					{field:'configModelName',title:'机型配置单名称',width:100,align:'center',
						formatter:function(value,rec,index)
                        {
							var title=rec.configModelName;
							var id=rec.configModelId;
							var edit;
							edit='<a href="#" onclick="showConfigDIV(\''+id+'\')">'+title+'</a>';
							return edit;
                        }			
					},
					{field:'quantity',title:'总数',width:50,align:'center'},
					{field:'yincanglie',title:'隐藏通用单元',width:50,align:'center',hidden:true},//,hidden:true
					{field:'ordered',title:'已采购数',width:50,align:'center'},
					{field:'remainingQuantity',title:'采购数',width:50,align:'center',editor:'text'}
					]],  
			onClickRow:function(rowIndex, rowData){
				if (lastIndext != rowIndex){
					$('#execute').datagrid('endEdit', lastIndext);
					$('#execute').datagrid('beginEdit', rowIndex);
				}
				lastIndext = rowIndex;
			},
			onAfterEdit: function(rowIndex, rowData, changes) {
				var end = (rowData.ordered*1.0)+(rowData.remainingQuantity*1.0);
				if(rowData.quantity < end) {
					$.messager.alert('提示', "超过了可采购的总数，请重新输入数量", 'info');
					cancreate = false;
					$('#tynumbers').datagrid('updateRow',{index:rowIndex,row:{remainingQuantity:0}});
					$('#tynumbers').datagrid('beginEdit', rowIndex);
				} else {
					cancreate = true;
				}
			}
		});
	});		
	});
	//下拉选择框数据加载         售后人
	var sel3 = $("#shenqingren");  
	sel3.empty();//清除select中的 option
	$.post(ctx+'user/user/user!queryAllAftersale.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
				sel3.append(opt);  
			}
		}
	});
});

//配置详情
function showConfigDIV(id) {
	var div_obj = $("#configDIV");
	div_obj.window('open');
	
	//报价表数据加载
	$('#ttt').datagrid({
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

function hideNumberDIV()
{
	$("#configDIV").window('close');
}