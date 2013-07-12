//弹出层***************************************
//添加存储操信息操作
function showWindow(id) {
	  $('#addVmDiv').window('open');
	  $('#addVmform').form('clear');
	  $("#processId").val(processParamId);
	  $("#id").val(id);
}

////隐藏存储操信息操作
function hideDataInfoDiv() {   
	 $('#addVmDiv').window('close');
} 

//function upload() {
//	alert("操作成功!");
//	hideDiv();
//}


//下拉框切换
function changeSelect(){
	var radionValue = $("input[type='radio']:checked").val();
	if(radionValue==1){
		$("#checker").show();
		$("#reject").hide(); 
	}else if(radionValue==2){
		$("#checker").hide();
		$("#reject").show();
	}
}


/**
 * 审核发票
 */
function chekcForm(){
	var tbUser = $("#tbUser").val();
	var process_Step = $("#process_Step").val();
	if($("#pass").attr("checked")=="checked"){
		if(null==tbUser || ""==tbUser){
			if(!wetherLastActive){
				$.messager.alert("提示","请选择审核人!");
				return;
			}
		}
	}else if($("#unpass").attr("checked")=="checked"){
		if(null==process_Step || ""==process_Step){
			$.messager.alert("提示","请选择驳回步骤!");
			return;
		}
	}else{
		$.messager.alert("提示","您还没有选择审核结果!");
		return;
	}
	$('#addVmform').form('submit', {
		url:ctx+'invoice/invoice/invoice!checkedAuditInvoice.action?',
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

//查询审核发票
function queryCheckedInvoice() {
	var starttime = $("#starttime").datebox("getValue").trim();
	var endtime = $("#endtime").datebox("getValue").trim();
	var kwords = $("#kwords").val();
	$('#tt').datagrid('load',{"invoiceVo.createtime":starttime,"invoiceVo.endtime":endtime,"invoiceVo.companyName":kwords});
}