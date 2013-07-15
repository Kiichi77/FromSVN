//清空上传表单
function clearFormFun() {
	clearFormFun.clear();
}
//重新加载列表
function query() {
	$('#tt').datagrid('reload');
}
//根据ID删除数据源
function deleteContractTemplates(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
		if (r){  
		//	var rows = $('#tt').datagrid('getSelections');
	    	//设置为同步
	    	$.ajaxSettings.async = false;
	    	$.post(
	    		ctx+'/contractTemplates/contractTemplates/contractTemplates!deleteContractTemplates.action',
	    		{id:id},
	    		function(data){
	    			var data = eval('(' + data + ')');
	    			if(null!=data && ""!=data){
	    				if(data.msg=="ok"){
	    					$.messager.alert("提示","删除成功！");
	    					$("#tt").datagrid("reload");
	    					    query();
	    				}else{
	    					$.messager.alert("提示","删除失败！");
	    				}
	    			}
	    		}
	    	);
		}
	});
}		   
			   
//批量删除
function deleteSomecontract() {
   	//获取表格选择行
   	var rows = $('#tt').datagrid('getSelections');
   	//判断是否选择行
   	if (!rows || rows.length == 0) { 
   	$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
   		return; 
   	} 
   	var parm = ""; 
   	// 循环给提交删除参数赋值(音乐风格编码)
   	$.each(rows, function (i, n) {
   		parm +=  n.id+","; 
   	}); 
   	parm = parm.substring(0,parm.length-1);
   	deleteContractTemplates(parm);
}			   
			   
//添加合同模版的方法
function uploadFileFun() {
	   if($("#name").val()==null || $("#name").val()==""){
		   $.messager.alert('提示', '模版名称不能为空!', 'info');
	   }else{
		   	$('#addVmform').form('submit', {
		   		url:ctx+"/contractTemplates/contractTemplates/contractTemplates!addContractTemplates.action",
		   		onSubmit: function(){
		   			// 做某些检查
		   			// 返回 false 来阻止提交
		   			return $(this).form('validate'); 
		   		},
		   		success:function(data){
		   			var datas = eval("("+data+")");
		   			if(data!=null && data!=""){
		   				if("error" == data.msg){
		   					$.messager.alert('提示', '操作失败，请从新尝试!', 'info');
		   				} else {
		   					query();
		   					hideDiv();
		   					window.location.href="addcontract.jsp?id="+datas.msg+"&iscontext=1";
		   				}
		   			} else {
		   				$.messager.alert('提示', '系统异常，请稍后操作!', 'info');
		   			}
		   		}
		   	});
	   }
}			   
//查询并填充配置模板
function updateTemplates(id,iscon) {
	iscontext = iscon;
	$.post(ctx+ '/contractTemplates/contractTemplates/contractTemplates!queryTemplatesById.action', {
		id : id
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			$("#modelId").val(datas.contractTemplates.id);
			$("#templateName").val(datas.contractTemplates.name);
			showWindow1();
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}			 
			   
//修改合同模版
function chekcForm() {
	if (null == $("#templateName").val() || "" == $("#templateName").val()) {
		$.messager.alert('提示', '配置单名称不能为空!', 'info');
		return;
	}
	$.post(ctx+ '/contractTemplates/contractTemplates/contractTemplates!updateContractTemplates.action',
	{
        id: $("#modelId").val(),
		name : $("#templateName").val()
	}, function(data) {
		if (null != data && "" != data) {
			var conid = $("#modelId").val();
			query();
			hideDiv1();
			window.location.href="addcontract.jsp?id="+conid+"&iscontext="+iscontext;
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});	
}			 
			   
//审核弹出框
function showDataInfoDiv(id,status) {
//	alert(status);
//  if(status !='1' && status !='3' && status !='5' && status !='7'){
//	  $.messager.alert('提示', "该合同模板已经审核过!", 'info');
//	  return;
//  }
  contractModeId=id;
  $("#templatesId").val(id);
  $('#addprocessdiv').window('open');
  $('#processForm').form('clear');
}

//关闭审核弹出框
function hideDataInfoDiv(){
	 $('#addprocessdiv').window('close');
}				
			   
/**
 * 模板审核 
 */
function checkedTemplate(){
	var userId = $('#tbUser').val();
	var rem = $("#remark").val();
	var templatesId=$("#templatesId").val();
	if(userId == null){
		   $.messager.alert('提示', "请选择下一个审核人！");
		   return;
	   }
	   $.post(ctx+ '/contractTemplates/contractTemplates/contractTemplates!startMoBanShenHeProcess.action',
		{
		    templatesId:templatesId,
		   	id: userId,
		   	name:rem,
		}, function(data) {
			if (null != data && "" != data) {
				var data = eval('(' + data + ')');
				if(data.msg=="ok"){
					$.messager.alert('提示', "操作成功"); 
					hideDiv();
					reloadDataGrid();
					window.location.href = ctx+"/view/main.jsp";
				}else if(data.msg=="yijingshenheguole"){
					$.messager.alert('提示', "模板已审核或在审核中"); 
				}
			} else {
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		});      
}	
//重新加载列表
function reloadDataGrid() {
	$('#tt').datagrid('reload');
}