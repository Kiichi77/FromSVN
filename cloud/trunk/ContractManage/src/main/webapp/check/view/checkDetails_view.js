 //关闭审核弹出框
function hideDiv() {
	 $('#addVmDiv').window('close');
}
////审核弹出框-弹出层***************************************
function showWindow() {
	 $('#addVmDiv').window('open');
	 $('#addVmform').form('clear');
	 $('#projectId').val(projectId);
}

//审核提交功能
function chekcForm(){
	if($("#pass").attr("checked")=="checked"){
	}else if($("#unpass").attr("checked")=="checked"){
	}else{
		$.messager.alert("提示","您还没有选择审核结果!");
	}
	$('#addVmform').form('submit', {
		url:ctx+'quotations/quotations/quotations!checkRapaport.action?quotationsId='+qoutaionId,
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
					window.location.href="../view/main.jsp";
				}else {
					$.messager.alert("提示","审核失败!");
				}
			}
		}
	});
}


//根据状态判断审核按钮是否开放
function checkButton(){
	//0:无效，1：未审核，2：待审核，3：审核通过，4：审核不通过
	if(nowUserRole==roleManager){ //只有经理才能审核
		if(2==status){
			$('#btnadd').linkbutton('enable'); //启用
		}else{
			$('#btnadd').linkbutton('disable'); //禁用
		}
	}else{
		$('#btnadd').linkbutton('disable'); //禁用
	}
}