//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}
//隐藏存储操信息操作
function hideDiv() {   
	$("#name").val("");
	$("#address").val("");
	$("#principal").val("");
	$("#phone").val("");
	$("#email").val("");
	$("#describe").val("");
  $("#mask").remove();  
  $("#addVmDiv").window('close');
}  

//隐藏存储操信息操作
function hideDiv1() {  
	  $("#mask1").remove();  
	  $("#addVmDiv1").window('close');
}
//清空上传表单
			   function clearFormFun() {
			   	clearFormFun.clear();
			   }
//根据ID删除数据源
			   function deleteCustomer(id){
					$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
						if (r){  
					    	//设置为同步
					    	$.ajaxSettings.async = false;
					    	$.post(
					    		ctx+'/customer111/customer111/customer111!deleteCustomer.action',
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
			   function deleteSomeCustomer() {
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
			   	deleteCustomer(parm);
			   }
			   
			   
	 //添加客户
			   function addCustomer() {
	        	   if($('#name').val()=="")
	        		   {
	        		  alert("请输入一个类型名称！");
	        		   }else{
	        		$('#addVmform').form('submit', {
	        			url:ctx+'/customer111/customer111/customer111!addCustomer.action',
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
			   function updateCustomer(id) {
			   	$.post(ctx+ '/customer111/customer111/customer111!queryCustomerById.action', {
			   		"id" : id
			   	}, function(data) {
			   		if (null != data && "" != data) {
			   			var datas = eval("("+data+")");
			   			$("#customerId").val(datas.customer.id);
			   			$("#companyName").val(datas.customer.companyName);
			   			$("#address1").val(datas.customer.address);
			   			$("#contact").val(datas.customer.contact);
			   			$("#phone1").val(datas.customer.phone);
			   			$("#email1").val(datas.customer.email);
			   			$("#description").val(datas.customer.description);
			   			$('#jobPlace1').combobox('setValue',datas.customer.jobPlaceId);  
			   			showWindow1();
			   		} else {
			   			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			   		}
			   	});
			   }
			   
				 //修改客户信息
			   function chekcForm() {
			   	$('#addVmform1').form('submit', {
					url:ctx+ '/customer111/customer111/customer111!updateCustomer.action',
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
								query();
								hideDiv1();
							}else {
								$.messager.alert("提示","修改失败!");
							}
						}
					}
				}); 
			   	
			   }
		