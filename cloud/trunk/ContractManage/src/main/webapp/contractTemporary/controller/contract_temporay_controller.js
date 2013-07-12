var lastIndext;//表单的行索引
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
	var endbool = true;
	var check=true;
	// 循环给提交删除参数赋值
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
	});
	if(!endbool) {
		$.messager.alert('提示', '采购数量为“0”的机型不可采购!', 'info');
		return;
	}
	if(!check){
		$.messager.alert('提示', '下单数超过了总数!', 'info');
		return;
	}
	conIds = conIds.substring(0, conIds.length - 1);
	parm = parm.substring(0, parm.length - 1);
	num = num.substring(0, num.length - 1);
	$.post(ctx+'/order/order/order!saveSalTemporaryOrder.action',{
		'contractOrderVo.orderNo':$("#ordernum").val(),
		'contractOrderId':$("#corids").val(),
		'contractOrderVo.note':$("#description").val(),
		'contractOrderVo.consignee':$("#consignee").val(),
		'contractOrderVo.consigneePhone':$("#consigneePhone").val(),
		'contractOrderVo.roderNums':num,
		'contractOrderVo.quotionIds':conIds,
		'contractOrderVo.configModelId':parm,
		'contractOrderVo.receiveInfo':$("#address").combobox('getText')
	},function(data) {
		var datas = eval('(' + data + ')');
		if (null != datas && "" != datas) {
			if("ok" == datas.msg) {
				window.location = "contract_temporary.jsp";
			} else {
				$.messager.alert('提示', "订单生成失败", 'info');
			}
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

//合并临时合同
function togetherContact() {
	// 获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要合并的临时合同!', 'info');
		return;
	}
	var parm = "";		//临时合同的ID
	// 循环给提交删除参数赋值
	$.each(rows, function(i, n) {
		parm += n.id + ",";
	});
	parm = parm.substring(0, parm.length-1);
	window.location = "togetherContract.jsp?contractIds="+parm;
}

//关闭审核弹出框
function hideDataInfoDiv(){
    $('#addprocessdiv').window('close');
}	

//审核弹出框
function showDataInfoDiv(id) {
     $('#addprocessdiv').window('open');
     $('#addVmform1').form('clear');
     $('#checkedVo_id').val(id);
}
  
//报价表提交审核
function submitCheck(){
	var tbUser = $('#tbUser').val();
	if(tbUser==null || tbUser==""){
		$.messager.alert("提示","请选择审核接口人!");
		return;
	}
	$('#addVmform1').form('submit', {
		url:ctx+'/contractTemplates/contractTemplates/contractTemporary!submitCheck.action',
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