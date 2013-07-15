//清空表单fun
function clearMyForm() {
	$('#contractnumber').val("");//合同编号
	$('#contractids').val("");//合同ID
	$('#proname').val("");//合同名称
	$('#title').val("");//选择的报价表ID
	$('#baojianame').val("");//选择的报价表name
	$('#createManName').val(nowUser);//创建者
}

//根据ID删除数据源
function deleteContract(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
		if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
			$.post(ctx+'/contractTemplates/contractTemplates/contract!deleteContract.action',
			{id:id},
			function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					if(data.msg=="ok"){
						$.messager.alert("提示","删除成功！");
						$("#tt").datagrid("reload");
						hideDiv();
						query();
					}else{
						$.messager.alert("提示","删除失败！");
					}
				}
			});
		}
	});
}
//批量删除
function deleteSomeContract() {
	// 获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的数据!', 'info');
		return;
	}
	var parm = "";
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		parm += n.id + ",";
	});
	parm = parm.substring(0, parm.length - 1);
	deleteContract(parm);
}

//查看配置单
function querytable(paramsId,paramsName) {
	window.location = "file.jsp?contractIds="+ paramsId + "&projecksName="+ paramsName;
}		   

//重新加载列表
function query() {
	$('#tt').datagrid('reload');
//	var kwords = $("#starttime").datebox("getValue").trim();
//	var filetype = $("#endtime").datebox("getValue").trim();
//	$('#tt').datagrid('load',{"contractVo.createTime":kwords,"contractVo.endTime":filetype});
}

// 添加合同
function addContract() {
	if (null == $("#proname").val() || "" == $("#proname").val()) {
	    $.messager.alert('提示', '合同名称不能为空!', 'info');
	    return;
	}
	$.post(ctx+ '/contractTemplates/contractTemplates/contract!addOrUpdateContract.action',{
		'contractVo.type':$("#contractType").val(),
		'contractVo.title':$("#proname").val(),
		'contractVo.quotationsTitle':$("#title").val(),
		'contractVo.contractNo':$("#contractnumber").val(),
		'contractVo.projectId':$('#projectname').combobox('getValue'),
		'contractVo.id':$("#contractids").val(),
		'contractVo.contractTemplatesId':$("#name").val()
	},function(data) {
		if (null != data && "" != data) {
		   $.messager.alert('提示', "操作成功", 'info');
		   query();
		   hideDiv();
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

//生成合同
function shengChengcon(paramsId) {
	$.post(ctx+'/quotations/quotations/contractQuotations!contractFileToPackage.action',{
		conid:paramsId
	},function(data) {
		var data = eval('(' + data + ')');
		if (null != data.msg && "ok" == data.msg) {
		   $.messager.alert('提示', "操作成功", 'info');
		   query();
		} else if("uncheck" == data.msg) {
		   $.messager.alert('提示', "文件内容不符合", 'info');
		} else if("docSB" == data.msg) {
		   $.messager.alert('提示', "合同文件生成失败", 'info');
		} else {
		   $.messager.alert('提示', "打包文件失败", 'info');
		}
	});		
}

//弹出报价表选择层
function showCheckBaojia() {
	var div_obj = $("#baojiaDiv");
	var contracids = $("#contractids").val();
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/2-popupWidth/2,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");
		//报价表数据加载
		$('#baojiabiao').datagrid({
			url: ctx+'/quotations/quotations/quotations!queryByContractId.action?contractId='+contracids,
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'title',title:'项目名称',width:300,align:'center'},
					{field:'createtime',title:'创建时间',width:200,align:'center'},
					{field:'status',title:'状态',width:200,align:'center',
						 formatter:function(value,rec,index){
							 var status=rec.status;
							 var edit;
							  if(0==status) {
								   edit='已生成';
								  }else if(1==status){
									   edit='生成中';
									  }
							  return edit;
						 }
					}]],  
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
			onLoadSuccess: function(data){
				//获取表格选择行
				var rows = $('#baojiabiao').datagrid('getRows');
				//获得模板与合同模板文件之间的关系，并勾选相应文件
				// 循环给提交删除参数赋值(合同文件ID)
				$.each(rows, function(i, n) {
					var id=n.contractQuotationId;
					if(null != id && 0 != id && "0" != id){
						$('#baojiabiao').datagrid("selectRow",i);
					}
				});
			}
		});
}

//根据合同ID查询修改合同信息
function queryContarctByid(parmsid,contractStatue) {
	window.location = ctx+"/contract/createcontract/realcreatecontract.jsp?contractId="+parmsid+"&contractStatue="+contractStatue;
}

function officialOrderNo(contracid){
	$.post(ctx+ '/order/order/order!generateSaleOrder.action?contractId='+contracid+'&typeId=1', function(data) {
		var data = eval('(' + data + ')');
		if (null != data && "" != data && "ok" == data.msg) {
			$("#ordernum").val(data.orderNO);
		} else {
			$.messager.alert('提示', "出现异常");
		}
	});
}
var lastIndext;//表单的行索引
var cancreate = true;		//是否可以提交表单
//弹出合同执行选择层
function showExecuteDIV(contracid,projectId) {
	$("#corids").val(contracid);
	$("#ordernum").val("");
	$("#description").val("");
	$("#projectId").val(projectId);
//	var div_obj = $("#executeDIV");
//	var windowWidth = document.body.clientWidth;       
//	var windowHeight = document.body.clientHeight;  
//	var popupHeight = div_obj.height();       
//	var popupWidth = div_obj.width(); 
//	div_obj.css({"position": "absolute"})
//	.animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/2, opacity: "show" }, "slow");
	officialOrderNo(contracid);
	$("#executeDIV").window('open');
	//报价表数据加载
		$('#execute').datagrid({///quotations/quotations/contractQuotations!queryAllConfigModels.action?conid=
			url: ctx+'/quotations/quotations/contractQuotations!queryAllConfigModels.action?conid='+contracid,
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
					{field:'contractId',title:'ContractId',width:60,align:'center',hidden:true},
					{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
					{field:'quotationsId',title:'QuotationsId',width:60,align:'center',hidden:true},
					{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
					{field:'quantity',title:'总数',width:50,align:'center'},
					{field:'totalPrice',title:'隐藏总价',width:50,align:'center',hidden:true},//,hidden:true
		//			{field:'yincanglie',title:'隐藏通用单元',width:50,align:'center',hidden:true},//,hidden:true
					{field:'danjia',title:'单价/元',width:80,align:'center'},
					{field:'ordered',title:'已下单总数',width:50,align:'center'},
					{field:'remainingQuantity',title:'下单数',width:50,align:'center',editor:'text'},//,editor:'text'
					{field:'soutotal',title:'小计/元',width:80,align:'center'}
		//			{field:'opt',title:'操作',width:120,align:'center',
		//				formatter:function(value,rec,index){
		//				var id = rec.configModelId;
		//				var tomoney = (rec.totalPrice*1.0)/(rec.quantity*1.0);
		//				tomoney = Math.round(tomoney*100)/100;
		//				var yicaigou = (rec.quantity*1)-(rec.ordered*1);
		//				var edit = '<a href="#" onclick="showNumberDIV(\''+id+'\',\''+yicaigou+'\',\''+index+'\',\''+tomoney+'\')">设置配置数量</a>';
		//				return edit;
		//			}}
					]],  
			toolbar:[],
			onLoadSuccess: function(data){
				// 获取表格行
				var rows = $('#execute').datagrid('getRows');
				// 循环给提交删除参数赋值
				$.each(rows, function(i, n) {
					var danjia = (n.totalPrice*1.0)/(n.quantity*1.0);
					danjia = Math.round(danjia*100)/100;
					var totalprices = Math.round(n.totalPrice*100)/100;
					$('#execute').datagrid('updateRow',{index:i,row:{soutotal:totalprices,danjia:danjia}});
				});
			},
			onClickRow:function(rowIndex, rowData){
				if (lastIndext != rowIndex){
					$('#execute').datagrid('endEdit', lastIndext);
					$('#execute').datagrid('beginEdit', rowIndex);
				}
				lastIndext = rowIndex;
			},
			onAfterEdit: function(rowIndex, rowData, changes) {
				var cha=rowData.quantity*1.0-rowData.ordered*1.0-rowData.remainingQuantity*1.0;
				if(cha<0)
					{
					$.messager.alert('提示', "下单数超过了总数", 'info');
					}else{
				var total=(rowData.danjia*1.0)*(rowData.remainingQuantity*1.0);
				total=Math.round(total*100)/100;
				$('#execute').datagrid('updateRow',{index:rowIndex,row:{soutotal:total}});
					}
			}
		});
		 $('#address').combobox({ 
			 url:ctx+'/customer111/customer111/customer111!listAddressInfo.action',
			 valueField:'id', 
			 textField:'receiveInfo'
			 })
}

//创建并保存销售订单
function createAndSaveOrder() {
	var editrows = lastIndext;
	if(null != editrows) {
		$('#execute').datagrid('endEdit',editrows);
	}
	// 获取表格选择行
	var rows = $('#execute').datagrid('getSelections');
		// 判断是否选择行
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要执行的机型配置!', 'info');
			return;
		}
	var parm = "";		//机型配置ID
	var conIds = "";	//报价表ID
	var num = "";		//采购个数
//   var ordernums = "";  //已下单总数
//   var quantity="";  //总数
//	var tyjson = "";
	var endbool = true;
	var check=true;
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		conIds += n.quotationsId + ",";
		parm += n.configModelId + ",";
		if("0" == n.remainingQuantity || 0 == n.remainingQuantity) {
			endbool = false;
		}
		var remainingQuantity =n.remainingQuantity*1.0;
		var ordered =n.ordered*1.0;
		var tt = remainingQuantity + ordered;
		if(tt>n.quantity)
			{
			  check = false;
			}
		num += n.remainingQuantity + ",";
    //	ordernums += n.ordered + ",";
	//	tyjson += n.yincanglie + ",";
	});
	if(!endbool) {
		$.messager.alert('提示', '采购数量为“0”的机型不可采购!', 'info');
		return;
	}
	if(!check)
		{
		$.messager.alert('提示', '下单数超过了总数!', 'info');
		return;
		}
	conIds = conIds.substring(0, conIds.length - 1);
	parm = parm.substring(0, parm.length - 1);
	num = num.substring(0, num.length - 1);
//	ordernums = ordernums.substring(0, ordernums.length - 1);
//	tyjson = tyjson.substring(0, tyjson.length - 1);
	$.post(ctx+'/order/order/order!saveSalOrder.action',{
		'contractOrderVo.orderNo':$("#ordernum").val(),
		'contractOrderId':$("#corids").val(),
	//	'contractOrderVo.purchaseCompany':$('#purchasepeople').val(),
	//	'contractOrderVo.purchase':$('#purchase').val(),
	//	'contractOrderVo.caudit':$('#caudit').val(),
		'contractOrderVo.note':$("#description").val(),
		'contractOrderVo.consignee':$("#consignee").val(),
		'contractOrderVo.consigneePhone':$("#consigneePhone").val(),
		'contractOrderVo.roderNums':num,
		'contractOrderVo.quotionIds':conIds,
		'contractOrderVo.configModelId':parm,
	//	'contractOrderVo.orderednum':ordernums,
	//	'contractOrderVo.processId':process_id,
		'contractOrderVo.projectId':$("#projectId").val(),
		'contractOrderVo.receiveInfo':$("#address").combobox('getText')
	//	'contractOrderVo.currentUnitData':tyjson,
	},function(data) {
		var datas = eval('(' + data + ')');
		if (null != datas && "" != datas) {
			if("ok" == datas.msg) {
				window.location = ctx+"/salesOrderManager/salesOrderManager.jsp";
			} else {
				$.messager.alert('提示', "订单生成失败", 'info');
			}
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

var lastnumIndext;//表单的行索引
function wantTongyongInfo(configId,contracid) {
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'/order/order/order!findCurrentUnit.action?contractId='+contracid+'&configModelId='+configId,
//		url:ctx+'/order/order/order!findCurrentUnit.action?contractId=19&configModelId=7',
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
				{field:'unitPrice',title:'单价',width:80,align:'center',
					formatter:function(value,rec,index){
						var totalprices = Math.round(value*100)/100;
						return totalprices;
					}
				},
				{field:'ordered',title:'已采购数',width:50,align:'center'},
				{field:'buyNumber',title:'采购数',width:50,align:'center',editor:'text'},//
				{field:'endMoney',title:'小计',width:80,align:'center'}
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
				$('#tynumbers').datagrid('updateRow',{index:rowIndex,row:{remainingQuantity:0,endMoney:0}});
				$('#tynumbers').datagrid('beginEdit', rowIndex);
			} else {
				var totalprices = (rowData.unitPrice*1.0)*(rowData.buyNumber*1);
				totalprices = Math.round(totalprices*100)/100;
				cancreate = true;
				$('#tynumbers').datagrid('updateRow',{index:rowIndex,row:{endMoney:totalprices}});
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
	var zongjia = 0;
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
				json += '|~';
			} 
			zongjia += zongjia+n.endMoney;
		} else {
			json += n.configModelDataId+"=0";
			if(i!=rows.length-1){
				json += '|~';
			}
		}
	});
	zongjia = zongjia+($("#totalmoney").val()*1.0*caigoushu);
	if(!cancreate) {
		$('#tynumbers').datagrid('beginEdit', lastnumIndext);
		return;
	} else {
		$('#execute').datagrid('updateRow',{index:$("#praindex").val(),row:{remainingQuantity:caigoushu,soutotal:zongjia,yincanglie:json}});
		hideNumberDIV();
	}
}

//弹出付款项
function showPaymentDIV(contracid,projectId) {
	 $('#PaymentDIV').window('open');
}

//查询并填充付款项
function contractPaymentBycontractId(id) {
	$.post(ctx+ '/contractTemplates/contractTemplates/contractPayment!contractPaymentBycontractId.action', {
		"id" : id
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			$("#contract").val(datas.contractReceivePayment.contract);
			var num = new Number(datas.contractReceivePayment.contractTotalPrice);
			$("#contractTotalPrice").val(num.toFixed(0));
			$("#contractTotalPriceText").val(datas.contractReceivePayment.contractTotalPriceText);
			$("#contractGoodsPercent").val(datas.contractReceivePayment.contractGoodsPercent);
			var num1 = new Number(datas.contractReceivePayment.contractGoodsPrice);
			$("#contractGoodsPrice").val(num1.toFixed(0));
			$("#contractGoodsPriceText").val(datas.contractReceivePayment.contractGoodsPriceText);
			$("#contractBeginPercent").val(datas.contractReceivePayment.contractBeginPercent);
			var num2 = new Number(datas.contractReceivePayment.contractBeginPrice);
			$("#contractBeginPrice").val(num2.toFixed(0));
			$("#contractBeginPriceText").val(datas.contractReceivePayment.contractBeginPriceText);
			$("#contractFinalPercent").val(datas.contractReceivePayment.contractFinalPercent);
			var num3 = new Number(datas.contractReceivePayment.contractFinalPrice);
			$("#contractFinalPrice").val(num3.toFixed(0));
			$("#contractFinalPriceText").val(datas.contractReceivePayment.contractFinalPriceText);
			$("#purchaseCompany").val(datas.contractReceivePayment.purchaseCompany);
			$("#customer").val(datas.contractReceivePayment.customer);
			$("#contractId").val(datas.contractReceivePayment.contractId);
			$("#purchaseCompanyId").val(datas.contractReceivePayment.purchaseCompanyId);
			$("#id").val(datas.contractReceivePayment.id);
			$("#statusId").val(datas.contractReceivePayment.status);
			var status=datas.contractReceivePayment.status
			if(status=0)
				{
				$("#status").val("完成收款");
				}
			else if(status=1)
				{
				$("#status").val("待收货款");
				}
			else if(status=2)
			{
			$("#status").val("待收初验款");
			}
			else if(status=3)
			{
			$("#status").val("待收终验款");
			}
			showPaymentDIV();
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

//关闭审核弹出框
function hideDataInfoDiv(){
    $('#addprocessdiv').window('close');
}	

//审核弹出框
function showDataInfoDiv(id,projectId) {
     $('#addprocessdiv').window('open');
     $('#addVmform1').form('clear');
     $('#checkedVo_id').val(id);
     $('#checkedVo_projectId').val(projectId);
}
  
//报价表提交审核
function submitCheck(){
	var tbUser = $('#tbUser').val();
	if(tbUser==null || tbUser==""){
		$.messager.alert("提示","请选择审核接口人!");
		return;
	}
	$('#addVmform1').form('submit', {
		url:ctx+'/contractTemplates/contractTemplates/contract!submitCheck.action',
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
					hideDataInfoDiv();
					window.location.href = ctx+"/view/main.jsp";
				}else {
					$.messager.alert("提示","提交失败!");
				}
			}
		}
	});
}
//合同金额 小写数字转成大写数字
//function TotalPrice()
//{
//	var money1=$("#contractTotalPrice").val();
//	$.post(ctx+'/contractTemplates/contractTemplates/contract!toChineseCharacter.action',
//			{money:money1},
//			function(data){
//				var data = eval('(' + data + ')');
//				if(null!=data && ""!=data){
//					$("#contractTotalPriceText").val(data.msg);
//				}
//			});
//}
////货款 小写数字转成大写数字
//function GoodsPercent()
//{
//	var money1=$("#contractGoodsPrice").val();
//	$.post(ctx+'/contractTemplates/contractTemplates/contract!toChineseCharacter.action',
//			{money:money1},
//			function(data){
//				var data = eval('(' + data + ')');
//				if(null!=data && ""!=data){
//					$("#contractGoodsPriceText").val(data.msg);
//				}
//			});
//}
////初验 小写数字转成大写数字
//function BeginPercent()
//{
//	var money1=$("#contractBeginPrice").val();
//	$.post(ctx+'/contractTemplates/contractTemplates/contract!toChineseCharacter.action',
//			{money:money1},
//			function(data){
//				var data = eval('(' + data + ')');
//				if(null!=data && ""!=data){
//					$("#contractBeginPriceText").val(data.msg);
//				}
//			});
//}
////终验 小写数字转成大写数字
//function FinalPercent()
//{
//	var money1=$("#contractFinalPrice").val();
//	$.post(ctx+'/contractTemplates/contractTemplates/contract!toChineseCharacter.action',
//			{money:money1},
//			function(data){
//				var data = eval('(' + data + ')');
//				if(null!=data && ""!=data){
//					$("#contractFinalPriceText").val(data.msg);
//				}
//			});
//}

//修改收款项
//function updatePayment() {
//	$('#addVmform1111').form('submit', {
//		url:ctx+ '/contractTemplates/contractTemplates/contractPayment!updatePayment.action',
//		onSubmit: function(){
//			// 做某些检查
//			// 返回 false 来阻止提交
//			return $(this).form('validate'); 
//		},
//		success:function(data){
//			var data = eval('(' + data + ')');
//			if(null!=data && ""!=data){
//				if(data.msg=="ok"){
//					$.messager.alert("提示","修改成功!");
//					hideDiv2();
//				}else {
//					$.messager.alert("提示","修改失败!");
//				}
//			}
//		}
//	}); 
//	
//}