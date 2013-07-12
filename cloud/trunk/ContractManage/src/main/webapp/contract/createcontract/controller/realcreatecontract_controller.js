//修改
function updateContract(contractId){
	$('#updateform').form('submit', {
   		url:ctx+"contractTemplates/contractTemplates/contractDoc!updateContractDoc.action",
   		onSubmit: function(){
   			// 做某些检查
   			// 返回 false 来阻止提交
   			return $(this).form('validate'); 
   		},
   		success:function(data){
   			if(data!=null && data!=""){
   				if("error" == data.msg){
   					alert("操作失败，请从新尝试");
   				} else {
   					alert("操作成功");
   					$("#tt").datagrid("reload");
   					$('#updateform').form("clear");
   					$('#updateDIV').css('display','none');
   				}
   			} else {
   				alert("系统异常，请稍后操作");
   			}
   		}
   	});
}

//修改合同
//根据合同ID查询修改合同信息
function queryContarctByid() {
	if(pkContractId!=null && pkContractId!=""){
		$("#contractids").val(pkContractId);//合同ID
		$.post(ctx+'/contractTemplates/contractTemplates/contract!getContractById.action',{
			id:pkContractId
		},function(data) {
			var datas = eval('(' + data + ')');
			if (null != datas && "" != datas) {
				$("#contractids").val(datas.id);
				$("#contractno").val(datas.contractNumber);
				$('#contractnumber').val(datas.contractNo);//合同编号
				$('#proname').val(datas.title);//合同名称
				$('#name').val(datas.contractTemplatesId);//合同模板ID
				$('#project').val(datas.projectId);//项目ID
				$("#name").attr('disabled','disabled');
				//合同模板
				$("#contractTemplate").hide();
				$("#baojiaDiv").hide();
		//		showContractTemplate(datas.contractTemplatesId);
				
//				$('#title').val(datas.quotationsId);//选择的报价表ID
//				$('#baojianame').val(datas.quotationsTitle);//选择的报价表name
//				$('#contractType').val(datas.type);//合同模板ID
//				$('#createManName').val(nowUser);//创建者
//			   showWindow(pkContractId);
			} else {
			   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		});
		//选中的合同文件
		showContract();
		$("#contract").show();
		
		//根据合同状态判断哪些可以修改哪些不能修改
		if(contractStatue==2 || contractStatue==4){ //当合同状态为待审核和审核不同意的时候才能修改
		}else{
			//只能修改外部合同编号，合同名称
			$("#contractnumber").attr('disabled','disabled');  //内部合同编号
			$("#name").attr('disabled','disabled');  //合同模板
			$("#project").attr('disabled','disabled');   //项目
		}
		
	}
}