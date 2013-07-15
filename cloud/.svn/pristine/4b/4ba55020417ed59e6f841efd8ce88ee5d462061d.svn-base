//弹出层***************************************
//添加存储操信息操作
function showWindow(id,contractStatus,contractId) {
	$("#payDataContractReceivePayment").val(id);
	$("#processParamId").val(processParamId);
	var sel = $("#payDatatype");
	sel.empty();//清除select中的 option
	var opt;
	if(contractStatus==1){ //待收货款
		findPayDatamoney(contractId,1);
		opt = $("<option>").text("到货").val(11);  
	}else if(contractStatus==2){//待初验款
		findPayDatamoney(contractId,2);
		opt = $("<option>").text("初验").val(12);
	}else if(contractStatus==3){ //待终验款
		findPayDatamoney(contractId,3);
		opt = $("<option>").text("终验").val(13);
	}
	sel.append(opt);  
	$('#addVmDiv').window('open');
}

function findPayDatamoney(contractId,contractStatus)
{
	$.post(
    		ctx+'/contractTemplates/contractTemplates/contractPayment!findPayDatamoney.action',
    		{contractId:contractId,contractStatus:contractStatus},
    		function(data){
    			var data = eval('(' + data + ')');
    			var num = new Number(data.msg);
    			$("#payDatamoney").val(num.toFixed(0));
    			}
    		
    	);
}
//隐藏存储操信息操作
function hideDiv() {   
//	clearVMFormFun(1);
	$("#addVmDiv").window('close'); 
} 

function upload() {
	var select = $("#payDatatype");
	if(null==select || ""==select){
		$.messager.alert("提示","请选择付款类型!");
		return;
	}
	$('#addVmform').form('submit', {
		url:ctx+'/contractTemplates/contractTemplates/contractPayment!addPaymentData.action',
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
//					$('#tt').datagrid('reload');
					window.location.href="../view/main.jsp";
				}else {
					$.messager.alert("提示","添加失败!");
				}
				hideDiv();
			}
		}
	}); 
//	alert($("#payDatamoney").val()+"   "+$("#payDatareceiveTime").datebox('getValue')+"   "+$("#payDataNotes").val()+"   "+$("#payDatatype").val()+"   "+$("#payDatafinancialName").val());
	/*$.post(ctx+'/contractTemplates/contractTemplates/contractPayment!addPaymentData.action',{
		'payData.money':$("#payDatamoney").val(),
		'payData.receiveTime':$("#payDatareceiveTime").datebox('getValue'),//payData.receiveTime
		'payData.Notes':$("#payDataNotes").val(),
		'payData.payType':$("#payDatatype").val(),
		'payData.financialId':$("#payDatafinancialName").val()
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
	});		*/
}