		//根据ID删除数据源
			   function deleteQuotations(id){
					$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
						if (r){  
						//	var rows = $('#tt').datagrid('getSelections');
						
					    	//设置为同步
					    	$.ajaxSettings.async = false;
					    	$.post(
					    		ctx+'/quotations/quotations/quotations!deleteQuotations.action',
					    		{id:id},
					    		function(data){
					    			var data = eval('(' + data + ')');
					    			if(null!=data && ""!=data){
					    				if(data.msg=="ok"){
					    					$.messager.alert("提示","删除成功！");
					    					$("#tt").datagrid("reload");
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
			   function deleteSomeRapaport() {
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
			   	deleteQuotations(parm);
			   }
			 
			   //重新生成报价表
			   function redio(id)
			   {
				 	$.post(
				    		ctx+'/quotations/quotations/quotations!redoAuotions.action',
				    		{id:id},
				    		function(data){
				    			var data = eval('(' + data + ')');
				    			if(null!=data && ""!=data && "ok"==data.msg){
				    					$.messager.alert("提示","操作成功！");
				    					$("#tt").datagrid("reload");
				    			}
				    				else{
				    					$.messager.alert("提示","操作失败！");
				    				}
				    			}
				    		
				    	);
			   }
			   
	