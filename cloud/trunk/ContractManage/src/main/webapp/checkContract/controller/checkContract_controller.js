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

//隐藏存储操信息操作
function hideDiv2() {   
	clearMyForm();
	$('#PaymentDIV').window('close');
}  

//清空表单fun
function clearMyForm() {
	$('#contractnumber').val("");//合同编号
	$('#contractids').val("");//合同ID
	$('#proname').val("");//合同名称
	$('#title').val("");//选择的报价表ID
	$('#baojianame').val("");//选择的报价表name
	$('#createManName').val(nowUser);//创建者
}

//审核弹出框
function showWindow( id,type) {
  $('#addVmDiv').window('open');
  $('#addVmform').form('clear');
  $("#checkedVo_id").val(id);
 // $('#checkedVo_status').val(status);
  $('#checkedVo_type').val(type);
}

//关闭审核弹出框
function hideDataInfoDiv(){
	 $('#addVmDiv').window('close');
}


//审核提交功能
function chekcForm(){
	if($("#pass").attr("checked")=="checked"){
	}else if($("#unpass").attr("checked")=="checked"){
	}else{
		$.messager.alert("提示","您还没有选择审核结果!");
		return;
	}
	$('#addVmform').form('submit', {
		url:ctx+'/task/task/task!checkContract.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","审核成功!");
					hideDataInfoDiv();
					window.location.href = ctx+"/view/main.jsp";
				}else {
					$.messager.alert("提示","审核失败!");
				}
			}
		}
	});
}