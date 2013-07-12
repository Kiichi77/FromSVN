		
function query() {
				$('#tt').datagrid('reload');
			}
//根据ID删除数据源
			   function deleteContractDocType(id){
					$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
						if (r){  
						//	var rows = $('#tt').datagrid('getSelections');
						
					    	//设置为同步
					    	$.ajaxSettings.async = false;
					    	$.post(
					    		ctx+'/contractTemplates/contractTemplates/contractDocType!deleteContractDocType.action',
					    		{id:id},
					    		function(data){
					    			var data = eval('(' + data + ')');
					    			if(null!=data && ""!=data){
					    				if(data.msg=="ok"){
					    					$.messager.alert("提示","删除成功！");
					    					$("#tt").datagrid("reload");
					    					   hideDiv();
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
			  
			 //添加分类  
			   function addContractDocType() {
	        	   if($('#name').val()=="")
	        		   {
	        		  alert("请输入一个类型名称！");
	        		   }else{
	        		$('#addVmform').form('submit', {
	        			url:ctx+'/contractTemplates/contractTemplates/contractDocType!addContractDocType.action',
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