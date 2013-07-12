//查询并填充客户信息
			   function findById(id) {
			   	$.post(ctx+ '/purchasecontract/purchasecontract/purchasecontract!findById.action', {
			   		"purchaseContractId" : id
			   	}, function(data) {
			   		if (null != data && "" != data) {
			   			var datas = eval("("+data+")");
			   			$("#id").val(datas.purchaseOrderContractFile.id);
			   			$("#contractName").val(datas.purchaseOrderContractFile.title);
			   			$("#supplier").val(datas.purchaseOrderContractFile.suppliers);
			   			$("#contractMoney").val(datas.purchaseOrderContractFile.contractMoney);
			   			$("#paymentType").val(datas.purchaseOrderContractFile.paymentType);
			   			showWindow();
			   		} else {
			   			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			   		}
			   	});
			   }
			   
			   function updatePurchaseContract()
			   {
					$('#addVmform').form('submit', {
						url:ctx+'/purchasecontract/purchasecontract/purchasecontract!updatePurchaseContract.action',
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
									$.messager.alert("提示","添加失败!");
								}
							}
						}
					});                                                                                                                                                                                                                                                                                                                                                                                                                      
			   }
			 //文件上传，保存
			   function uploadFileFun(){
			   	var docType = "";
			   		var fileName = $('input[name="upFile"]').val();
			   		if(fileName=="" || null==fileName){
			   			$.messager.alert('提示', "请选择需要上传的文件。", 'info');
			   			return;
			   		}
			   		var index = fileName.lastIndexOf(".");
			   		fileName = fileName.substring(index+1,fileName.length);
			   		if('docx'!=fileName && 'jpg'!=fileName){
			   			$.messager.alert('提示', "您选择的文件类型不对，只能上传docx和jpg文件。", 'info');
			   			return;
			   		}
			   	$('#addInvoiceform').form('submit', {
			      		url:ctx+"/invoice/invoice/purchaseinvoice!savePurchaseInvoice.action",
			      		onSubmit: function(){
			      			// 做某些检查
			      			// 返回 false 来阻止提交
			      			return $(this).form('validate'); 
			      		},
			      		success:function(data){
			      			if(data!=null && data!=""){
			      				var data = eval('(' + data + ')');
			      				if("ok" == data.msg){
			      					$.messager.alert('提示', "操作成功", 'info');
			      					hideDiv1();
			      			//		$("#tt").datagrid("reload");
			      			//		$('#addInvoiceform').form("clear");
			      				} else {
			      					$.messager.alert('提示', "操作失败，请从新尝试", 'info');
			      					hideDiv1();
			      				//	$('#addInvoiceform').form("clear");
			      				}
			      			} else {
			      				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			      			}
			      		}
			      	});
			   }
	
			 //重新加载列表
			   function query() {
			   				$('#tt').datagrid('reload');
			   			}