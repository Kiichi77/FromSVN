//重新加载列表
function query() {
	$('#tt').datagrid('reload');
}

//弹出层***************************************
function showWindow() {
	 $('#addVmDiv').window('open');
	 $('#addVmform').form('clear');
	 $("#nextline").css('display','');
	 $("#upfilediv").css('display','');
}
//隐藏存储操信息操作
function hideDiv() {
	$('#addVmDiv').window('close');
}

//根据ID删除数据源
   function deleteSupplier(id){
		$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
		if (r){  
	    	//设置为同步
	    	$.ajaxSettings.async = false;
	    	$.post(
	    		ctx+'/suppliers/suppliers/suppliersQuotations!deleteSupplierQuotations.action',
	    		{id:id},
	    		function(data){
	    			var data = eval('(' + data + ')');
	    			if(null!=data && ""!=data){
	    				if(data.msg=="ok"){
	    					$.messager.alert("提示","删除成功！");
	    					$("#tt").datagrid("reload");
	    					//   hideDiv();
	    					    query();
	    				}else{
	    					$.messager.alert("提示","删除失败！");
		    				}
		    			}
		    		}
		    	);
			}
		})
   }
	//批量删除
   function deleteSomeSupplier() {
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
	   		parm +=  n.superliersQuotationsId+","; 
	   	}); 
	   	parm = parm.substring(0,parm.length-1);
	   	deleteSupplier(parm);
   }
			   
			   
	 //添加供应商
   function saveOrUpdateSuppliers() {
	   var id=$("#id").val();
	 
	   if(null!=id && ""!=id && -1!=id){      //修改
		   $('#addVmform').form('submit', {
				url:ctx+'/suppliers/suppliers/suppliersQuotations!updateSupplierQuotations.action',
				onSubmit: function(){
					// 做某些检查
					// 返回 false 来阻止提交
					return $(this).form('validate'); 
				},
				success:function(data){
					var data = eval('(' + data + ')');
					if(null!=data && ""!=data){
						if(data.msg=="ok"){
							$.messager.alert("提示","修改成功!");
							hideDiv();
							query();
						}else {
							$.messager.alert("提示","修改失败!");
						}
					}
				}
			}); 
	   }
	   else{                                 //添加
		$('#addVmform').form('submit', {
			url:ctx+'/suppliers/suppliers/suppliersQuotations!saveSuppliersQuotations.action',
			onSubmit: function(){
				// 做某些检查
				// 返回 false 来阻止提交
				return $(this).form('validate'); 
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(null!=data && data!=""){
					if(data.msg == "ok"){
						 $.messager.alert("提示","操作成功！");
						    query();
							hideDiv();
					} else {
						$.messager.alert("提示","操作失败！");
						hideDiv();
					}
				} else {
					alert("系统异常，请稍后操作");
				}
			}
		});
	   }
  }
 //查询并填充客户信息
   function updateSuppliers(id) {
   	$.post(ctx+ '/suppliers/suppliers/suppliersQuotations!querySuppliersById.action', {
   		"id" : id
   	}, function(data) {
   		if (null != data && "" != data) {
   			var datas = eval("("+data+")");
   			//给文本框赋值
   			$("#id").val(datas.vo.superliersQuotationsId);
   			$("#suppQuotaName").val(datas.vo.suppliersQuotationsName);
   			$("#suppliersId").val(datas.vo.suppliersID);
   			$("#description").val(datas.vo.description);
   			$("#upfilediv").css('display','none');
   			$("#nextline").css('display','none');
   			//弹出修改对话框
   			$('#addVmDiv').window('open');
   		} else {
   			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
   		}
   	});
   }
			   
 //修改客户信息
//   function chekcForm() {
//   	$('#addVmform1').form('submit', {
//	url:ctx+ '/customer111/customer111/customer111!updateCustomer.action',
//	onSubmit: function(){
//		// 做某些检查
//		// 返回 false 来阻止提交
//		return $(this).form('validate'); 
//	},
//	success:function(data){
//		var data = eval('(' + data + ')');
//		if(null!=data && ""!=data){
//			if(data.msg=="ok"){
//				$.messager.alert("提示","修改成功!");
//				query();
//				hideDiv1();
//			}else {
//				$.messager.alert("提示","修改失败!");
//				}
//			}
//		}
//	}); 
//   	
//   }
			   		   