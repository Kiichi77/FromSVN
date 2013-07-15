//查询并填充订单信息
//弹出合同执行选择层
var lastnumIndext;//表单的行索引
var lastnumIndext1;
var suppliers;
var suppliersQuotations; //供应商报价表
function productFormatter(value){
	for(var i=0; i<suppliers.length; i++){
		if (suppliers[i].id == value) return suppliers[i].companyName;
	}
	return value;
}

//根据供应商id查找供应下的报价表
function retrunSsuppliersQuotations(value){
	if(suppliersQuotations!="" && null!=suppliersQuotations){
		for(var i=0; i<suppliersQuotations.length; i++){
			if (suppliersQuotations[i].superliersQuotationsId == value) {
				//alert("suppliersQuotations[i].suppliersQuotationsName:"+suppliersQuotations[i].suppliersQuotationsName);
				return suppliersQuotations[i].suppliersQuotationsName;
			}
		}
		return value;
	}
	return "";
}

//查询供应商报价表
function querySuppliersQuotations(){
	$.post(ctx+ '/suppliers/suppliers/suppliersQuotations!querySuppliersQuotationsBySuppId.action', function(data) {
		var datas = eval("("+data+")");
		if (null != datas && "" != datas) {
			suppliersQuotations = datas;
		}
	});
}

//异步加载供应商报价表
function synchCategory(editRow,index,paramSuppliersId){
	$(editRow.target).combobox('clear','');
	$(editRow.target).combobox('reload',ctx+ '/suppliers/suppliers/suppliersQuotations!querySuppliersQuotationsBySuppId.action?id='+paramSuppliersId);
}

//获取需要编辑的控件
function getEditRow(lastIndex,field){
	return jQuery('#execute').datagrid('getEditor', {index :lastIndex,field :field});
};

function showExecuteDIV(contractOrderId,projectId) {
	var lastIndex;
	$("#contractId").val(contractOrderId);
	$("#projectId").val(projectId);
	$.post(ctx+'/suppliers/suppliers/suppliers!listSuppliers.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					suppliers = datas.rows;
				}
				//获取销售订单
				var div_obj = $("#executeDIV");
				div_obj.window('open');
				 
				//查询供应商报价表
				querySuppliersQuotations();
				//初始化表单
				$('#execute').datagrid({
					url: ctx+'/order/order/order!findOrderDetailByContractId.action?contractOrderId='+contractOrderId,
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
							{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
							{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
							{field:'unitPrice',title:'采购价(元)',width:80,align:'center',editor:'text'}, //unitPrice
							{field:'quantity',title:'采购数',width:50,align:'center'},
							{field:'supplierId',title:'供应商',width:80,align:'center',formatter:productFormatter,
								editor:{
									type:'combobox',
									options:{
										textField:'companyName',
										valueField:'id',
										data:suppliers,
										onSelect:function(value){
											 lastIndex = $('#execute').datagrid('getRows').length-1;
						                     $('#execute').datagrid('selectRow', lastnumIndext);
						                     $('#execute').datagrid('beginEdit', lastnumIndext);
						                     var editor = jQuery('#execute').datagrid('getEditor', {index :lastnumIndext,field :'suppliersQuotationsId'});
						                     synchCategory(editor,lastnumIndext,value.id);
										},
										required:true
									}
								}
							},{field:'suppliersQuotationsId',title:'供应商报价单',width:80,align:'center',
								formatter:retrunSsuppliersQuotations,
							 	editor:{
									type:'combobox',
									options:{
										textField:'suppliersQuotationsName',
										valueField:'superliersQuotationsId',
										required:true
									}
								}
							},
							{field:'opt',title:'详情',width:80,align:'center',
								formatter:function(value,rec,index){
									var id = rec.configModelId;
									var edit = '<a href="#" onclick="wantTongyongInfo(\''+id+'\')">配置详情</a>';//
									return edit;
								}
							}
							]],  
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
					onDblClickRow:function(rowIndex,rowData){
					if (lastnumIndext != rowIndex){
						$('#execute').datagrid('endEdit', lastnumIndext);
						$('#execute').datagrid('beginEdit', rowIndex);
						lastnumIndext = rowIndex;
						if("" != rowData.supplierId || "" != rowData.suppliersQuotationsId){
							var editor = jQuery('#execute').datagrid('getEditor', {index :lastnumIndext,field :'suppliersQuotationsId'});
							synchCategory(editor,lastnumIndext,rowData.supplierId);
							$(editor.target).combobox('setValue',rowData.suppliersQuotationsId);
						}
					}
				}
				});
			});
	
}

function showInquiryDIV(contractOrderId)
{
	$("#inquiryDIV").window('open');
	$('#ttt1').datagrid({
		url: ctx+'/order/order/order!findOrderByContractId.action?contractOrderId='+contractOrderId,
		width: '100%',
		height: 'auto',
	//	pagination:true,
		remoteSort: false,
		nowrap: false,
		singleSelect: true,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
		fitColumns: true,
		showGroup:true,
		columns:[[ 
					{field:'id',title:'id',width:60,align:'center',hidden:true},
					{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
					{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
					{field:'purchasePrice',title:'采购价(元)',width:80,align:'center'}, //unitPrice
					{field:'quantity',title:'采购数',width:50,align:'center'},
					{field:'supplierId',title:'供应商',width:50,align:'center'},
					{field:'suppliersQuotationsId',title:'供应商报价单',width:50,align:'center'},
					{field:'opt',title:'查看详情',width:80,align:'center',
						formatter:function(value,rec,index){
							var id = rec.configModelId;
							var edit = '<a href="#" onclick="wantTongyongInfo(\''+id+'\')">查看配置详情</a>';//
							return edit;
						}
					}
					]],
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

//隐藏存储操信息操作
function hideDiv1() {   
	$("#executeDIV").window('close');
//	window.location.reload(true);
}

//隐藏存储操信息操作
function hideDiv2() {   
	$("#inquiryDIV").window('close');
//	window.location.reload(true);
}
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

//更新询价单的价格和供应商
function updateRelationship() {
	var contractId=$("#contractId").val();
	var projectId=$("#projectId").val();
	$('#execute').datagrid('acceptChanges');
	var rows = $('#execute').datagrid('getRows');
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		json += "{'id':'"+n.id+"','purchasePrice':'"+n.unitPrice+"','suppliers':'"+n.supplierId+"','suppliersQuotations':'"+n.suppliersQuotationsId+"'}";
		if(i!=rows.length-1){
			json += ',';
		}
	});
	json += "]";
	$.post(ctx+ '/order/order/order!updateRelationship.action', {
		id : json
	}, function(data) {
		var datas = eval("("+data+")");
		if (null != datas && "" != datas) {
			if("ok" == datas.msg){
				$.messager.alert('提示', "操作成功", 'info');
				showSubmitCheckedWin(contractId,projectId);
				hideDiv1();
			//	window.location.reload(true);
			} else {
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		}
	});
}

/**
 * 提交审核
 */
function submitCheck(){
	var tbUser = $('#tbUser').val();
	if(tbUser==null || tbUser==""){
		$.messager.alert("提示","请选择审核接口人!");
		return;
	}
	$('#addVmform1').form('submit', {
		url:ctx+ '/order/order/order!submitState.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","提交审核成功");
					closeSubmitCheckedWin();
				}else {
					$.messager.alert("提示","提交失败!");
				}
			}
		}
	});
}
/**
 * 弹出提价审核
 * @param orderId 订单id
 * @param projectId 项目id
 */
function showSubmitCheckedWin(orderId,projectId){
	$("#checkedVo_id").val(orderId);
	$("#checkedVo_projectId").val(projectId);
	$("#addprocessdiv").window('open');
}

/**
 * 弹出提价审核
 * @param orderId 订单id
 * @param projectId 项目id
 */
function closeSubmitCheckedWin(orderId,projectId){
	$("#addprocessdiv").window('close');
	window.location.reload(true);
}
