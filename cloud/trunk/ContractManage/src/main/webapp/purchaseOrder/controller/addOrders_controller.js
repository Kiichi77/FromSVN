//根据选择的厂商刷新销售订单数据
function queryChangjia() {
	var supps = $("#changjia").val();
//	$('#execute').datagrid('load',{"id":contractOrderId,"suppliersId":supps});
	$.post(ctx+'order/order/order!querySaleOrder.action',{
		'id':contractOrderId,
		'suppliersId':supps
	},function(data) {
		var datas = eval('(' + data + ')');
		if (null != datas && "" != datas) {
			$('#execute').datagrid('loadData',datas.rows);
		} else {
		   
		}
	});
}

//获得机型配置的通用单元
var lastnumIndext;//表单的行索引
var cancreate = true;		//是否可以提交表单
function wantTongyongInfo(contractOrderIdd) {
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'order/order/order!queryContractOrderRelationshipData.action?id='+contractOrderIdd,
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
				{field:'configModelId',title:'configModelId',width:60,align:'center',hidden:true},
				{field:'configModelDataId',title:'configModelDataId',width:60,align:'center',hidden:true},
				{field:'productDesc',title:'单元名称',width:100,align:'center'},
				{field:'quantity',title:'总数',width:50,align:'center'},
				{field:'ordered',title:'已采购数',width:50,align:'center'},
				{field:'buyNumber',title:'采购数',width:50,align:'center',editor:'text'}
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
		},
		onClickRow:function(rowIndex){
			if (lastnumIndext != rowIndex){
				$('#tynumbers').datagrid('endEdit', lastnumIndext);
				$('#tynumbers').datagrid('beginEdit', rowIndex);
			}
			lastnumIndext = rowIndex;
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			var end = (rowData.ordered*1.0)+(rowData.buyNumber*1.0);
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
}

//保存修改的通用单元数量
function saveAndUpdatetyNumbers() {
	var zongshu = $("#yicaigou").val();
	var caigoushu = $("#jxcgsl").val();
	if(null == caigoushu || "" == caigoushu) {
		$.messager.alert('提示', "请重新输入数量", 'info');
		return;
	}
	if((zongshu*1) < (caigoushu*1)) {
		$.messager.alert('提示', "机型配置超过了可采购的总数，请重新输入数量", 'info');
		return;
	}
	var editrows = lastnumIndext;
	if(null != editrows) {
		$('#tynumbers').datagrid('endEdit',editrows);
	}
	var json = "";
	// 获取表格行
	var rows = $('#tynumbers').datagrid('getRows');
	// 循环给提交删除参数赋值(音乐风格编码)
	if(1 > rows.length) {
		json = "0=0";
	}
	$.each(rows, function(i, n) {
		if(null != n.buyNumber && "" != n.buyNumber && "0" != n.buyNumber && 0 != n.buyNumber) {
			json += n.configModelDataId+"="+n.buyNumber;
			if(i!=rows.length-1){
				json += '+';
			} 
		} else {
			json += n.configModelDataId+"=0";
			if(i!=rows.length-1){
				json += '+';
			}
		}
	});
	if(!cancreate) {
		$('#tynumbers').datagrid('beginEdit', lastnumIndext);
		return;
	} else {
		$('#execute').datagrid('updateRow',{index:$("#praindex").val(),row:{remainingQuantity:caigoushu,yincanglie:json}});
		hideNumberDIV();
	}
}

//创建并保存采购订单
function createAndSaveOrder() {
	$('#execute').datagrid('endEdit', lastIndext);
	// 获取表格选择行
	var rows = $('#execute').datagrid('getSelections');
		// 判断是否选择行
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要执行的机型配置!', 'info');
			return;
		}
	var json = "[";//PurchaseOrderVo
	var porelation = "[";	//PurchaseOrderRelationshipVo
	var endbool = true;
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		if("0" == n.remainingQuantity || 0 == n.remainingQuantity) {
			endbool = false;
		}
		porelation += "{'purchaseQuantity':'"+n.remainingQuantity+"','contractOrderRelationshipId':'"+n.id+"'}";
		if(i!=rows.length-1){
			porelation += ',';
		}
	});
	porelation += "]";
	var processId = $("#processId").val();//任务ID
	var quoteNo = $("#baojiadanhao").val();//报价单号
	var contractId = $("#caigouhetong").val();//采购合同
	var suppliersId = $("#changjia").val();//厂家
	var orderNo = $("#dinghuohao").val();//订单编号
	var remark = $("#remark").val();//备注
	var afterSalesId = $("#shenqingren").val();//审核人
	var contractOrderIds = contractOrderId;//销售订单ID
	json += "{'processId':'"+processId+"','orderNo':'"+orderNo+"','suppliersId':'"+suppliersId+"','quoteNo':'"
	+quoteNo+"','contractId':'"+contractId+"','contractOrderId':'"+contractOrderIds+"','afterSalesId':'"
	+afterSalesId+"','remark':'"+remark+"','shipVos':"+porelation+"}]";
	if(!endbool) {
		$.messager.alert('提示', '采购数量为“0”的机型不可采购!', 'info');
		return;
	}
	$.post(ctx+'purchaseorder/purchaseorder/purchaseorder!savePurchaseOrder.action',{
		'id':json,
		'processid':$("#processId").val(),
		'userid':afterSalesId
	},function(data) {
		var datas = eval('(' + data + ')');
		if (null != datas && "" != datas) {
			if("ok" == datas.msg) {
				window.location = ctx+"view/main.jsp";
			} else {
				$.messager.alert('提示', "订单生成失败", 'info');
			}
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

function queryAlldscee(deis) {
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
			var supps = $("#changjia").val();
			$.post(ctx+'order/order/order!querySaleOrder.action',{
				'id':contractOrderId,
				'suppliersId':supps
			},function(data) {
				var datas = eval('(' + data + ')');
				if (null != datas && "" != datas) {
					$('#execute').datagrid('loadData',datas.rows);
				} else {
				   
				}
			});
		}
	});
}

function queryCaigouhet() {
	var supps = $("#caigouhetong").val();
	queryAlldscee(supps);
}