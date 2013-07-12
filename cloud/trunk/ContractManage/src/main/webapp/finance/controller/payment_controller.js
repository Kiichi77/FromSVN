//根据采购合同文件Id查询采购付款明细
function payment(purchaseContractId)
{
 	$.post(ctx+ '/purchasecontract/purchasecontract/purchasecontract!queryByPurchaseContractId.action', {
   		"purchaseContractId" : purchaseContractId
   	}, function(data) {
   		if (null != data && "" != data) {
   			var datas = eval("("+data+")");
   			$("#id").val(datas.purchaseOrderContractFile.id);
   			$("#projectName").val(datas.purchaseOrderContractFile.projectName);
   			$("#contract").val(datas.purchaseOrderContractFile.contract);
   			$("#title").val(datas.purchaseOrderContractFile.title);
   			if(datas.purchaseOrderContractFile.isPay==0)
   				{
   				$("#isPay").val("待付款");
   				}else{
   					$("#isPay").val("已付款");
   				}
   			$("#contractMoney").val(datas.purchaseOrderContractFile.contractMoney);
   			$("#suppliers").val(datas.purchaseOrderContractFile.suppliers);
   			$("#payment").val(datas.purchaseOrderContractFile.totalpayment);
   			$("#totalOverPayMoney").val(datas.purchaseOrderContractFile.totalOverPayMoney);
   			$("#overPayId").val(datas.purchaseOrderContractFile.overPayId);
   			$("#purchaseCompany").val(datas.purchaseOrderContractFile.purchaseCompany);
   			$("#purchaseCompanyId").val(datas.purchaseOrderContractFile.purchaseCompanyId);
   			$("#paymetType").val(datas.purchaseOrderContractFile.paymentType);
   			$("#checkNo").val(datas.purchaseOrderContractFile.checkNo);
   			$("#paymentTime").val(datas.purchaseOrderContractFile.paymentTime);
   			showWindow();
   		} else {
   			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
   		}
   	});	
}

function saveContractBill()
{
	$("#btnSave").hide();
	var contractMoney=$("#contractMoney").val()*1; //合同金额
	var payment=($("#payment").val())*1;  //实际付款
	var currentPayment=$("#currentPayment").val()*1;  //当前付款
	var balance=(currentPayment+payment)-contractMoney;
	
	var totalOverPayMoney=$("#totalOverPayMoney").val()*1;   //可对冲金额
	var userOverPayMoney=$("#userOverPayMoney").val()*1;  //对冲金额
	var payMoney=userOverPayMoney-totalOverPayMoney;
	if(balance>0)
		{
		$.messager.alert("提示","您多付款了!");
		}
	else if(payMoney>0)
		{
		$.messager.alert("提示","可对冲金额不足!");
		}else
			{
	$('#addVmform').form('submit', {
		url:ctx+'/purchasecontract/purchasecontract/purchaseOrderContractBill!saveContractBill.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","添加成功!");
					hideDiv();
					query();
				}else {
					$.messager.alert("提示","添加失败!");
				}
			}
		}
	}); 
			}
}

//隐藏存储操信息操作
function hideDiv() {   
	$("#suppliers").val("");
	$("#payment").val("");
	$("#currentPayment").val("");
//	$("#checkNo").val("");
//	$("#paymentTime").val("");
	$("#addVmDiv").window('close');  
} 
//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}

function changePaymetType()
{
	var paymetType=$("#paymetType").val();
	if(paymetType=="支票")
		{
		  $("#no").html("支票号&nbsp;&nbsp;&nbsp;：&nbsp;");
		  $("#time").html("开票时间&nbsp;&nbsp;&nbsp;：&nbsp;");
		}else if(paymetType=="延期支票"){
			 $("#no").html("支票号&nbsp;&nbsp;&nbsp;：&nbsp;");
			  $("#time").html("开票时间&nbsp;&nbsp;&nbsp;：&nbsp;");
		}else if(paymetType=="电汇"){
			 $("#no").html("流水号&nbsp;&nbsp;&nbsp;：&nbsp;");
			  $("#time").html("付款时间&nbsp;&nbsp;&nbsp;：&nbsp;");
		}else if(paymetType=="账期"){
			 $("#no").html("流水号&nbsp;&nbsp;&nbsp;：&nbsp;");
			  $("#time").html("付款时间&nbsp;&nbsp;&nbsp;：&nbsp;");
		}
}