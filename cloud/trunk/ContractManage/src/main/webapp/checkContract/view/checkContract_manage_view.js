$(function(){
	var linshiAction = '/contractTemplates/contractTemplates/contractTemporary!checkListAll.action';
	if(in_contractType!='' && in_contractType==2){
		linshiAction +='?temporaryVo.id='+in_contractId;
		$("#demo").tabs("select",0);
	}
	var zhengshiAction = '/contractTemplates/contractTemplates/contract!listContract.action';
	if(in_contractType!='' && in_contractType==1){
		zhengshiAction +='?id='+in_contractId;
		$("#demo").tabs("select",1);
	}
	//临时合同
	$('#linshi').datagrid({
		url: ctx+linshiAction,
		width: '95%',
		height: 'auto',
//		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		pagination:true,
		columns:[[  		   
                  {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'contractNo',title:'临时合同编号',width:50,align:'center'},
					{field:'title',title:'临时合同名称',width:50,align:'center'},
					{field:'price',title:'合同总价(元)',width:50,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},
					{field:'creatorId',title:'creatorId',width:60,align:'center',hidden:true},
					{field:'status',title:'状态',width:50,align:'center',
						formatter:function(value,rec,index){
							  var edit;
							  if(0==value){
									return '进行中';
								}else if(1==value){
									return '已完成';
								} else if(2==value){
								return '待审核';
							} else if(3==value){
								return '审核同意';
							}  else if(4==value){
								return '审核不同意';
							}  else if(5==value){
								return '审核中';
							}  else if(6==value){
								return '到货';
							  } else if(7==value){
									return '初验';
								}
							  return edit;
						 }	
					},
					{field:'fileForPDF',title:'文件',width:50,align:'center'},
					{field:'creatorName',title:'创建者',width:50,align:'center'},
					{field:'createTime',title:'创建时间',width:50,align:'center'},
					{field:'operate',title:'操作',width:120,align:'center',
						formatter:function(value,rec,index){
							var id=rec.id;
							var status=rec.status;
							var existFileName = rec.title;
							var fileName = rec.fileForPDF;
							if(fileName!=null && ""!=fileName){
								existFileName += fileName.substring(fileName.indexOf("."),fileName.length);
							}
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+in_contractTemporaryPath; 
							
							var edit;
							edit='<a href="#" onclick="tolookContactInfo(\''+id+'\')">查看合同</a>&nbsp;|&nbsp;';
							edit+='<a href="'+downPath+'">下载</a>&nbsp;|&nbsp;';
							if(status==5){
								edit+='<a href="#" onclick="showWindow(\''+id+'\',\'2\')">审核</a>&nbsp;|&nbsp;';
							}
							
							return edit;
						}
					}
					]],
					//toolbar:[],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
			
		}
	});
	//分页工具条
	 $('#linshi').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	
	
	//正式合同
			$('#tt').datagrid({
				url: ctx+zhengshiAction,
				width: '95%',
				height: 'auto',
//				pagination:true,
				rownumbers:true,
				remoteSort: false,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				columns:[[ 
							{field:'id',title:'ID',width:60,align:'center',hidden:true},
							{field:'projectId',title:'项目id',width:60,align:'center',hidden:true},
							{field:'projectName',title:'项目名称',width:60,align:'center'},
						//	{field:'contractTemplatesName',title:'合同模版',width:60,align:'center'},
							{field:'contractNumber',title:'合同编号',width:50,align:'left'},
							{field:'title',title:'合同名称',width:50,align:'left'},
							{field:'contractTotalPrice',title:'合同总价(元)',width:50,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'contractGoodsPercent',title:'货款比例(%)',width:35,align:'center',
								formatter:function(value,rec,index){
									return value+'';
								}	
							},
							{field:'contractGoodsPrice',title:'货款(元)',width:45,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'contractBeginPercent',title:'初验比例(%)',width:35,align:'center',
								formatter:function(value,rec,index){
									return value+'';
								}		
							},
							{field:'contractBeginPrice',title:'初验(元)',width:45,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'contractFinalPercent',title:'终验比例(%)',width:35,align:'center',
								formatter:function(value,rec,index){
									return value+'';
								}			
							},
							{field:'contractFinalPrice',title:'终验(元)',width:45,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'status',title:'状态',width:30,height: 30,align:'center',
								 formatter:function(value,rec,index){
										if(0==value){
											return '进行中';
										}else if(1==value){
											return '下单完成';
										} else if(2==value){
										return '待审核';
									  } else if(3==value){
										return '审核同意';
									  }  else if(4==value){
										return '审核不同意';
									  }  else if(5==value){
										return '审核中';
									  } else if(6==value){
										return '到货';
									  } else if(7==value){
											return '到货款';
										}else if(8==value){
											return '初验';
										} else if(9==value){
											return '初验款';
										 } else if(10==value){
											return '终验';
										 } else if(11==value){
											return '终验款';
										}else if(12==value){
											return '合同完成';
										}
								 }},
							{field:'createTime',title:'创建时间',width:50,align:'center',hidden:true},
							{field:'downPath',title:'下载地址',width:80,align:'center',hidden:true},
							{field:'opt',title:'操作',width:100,align:'left',
								formatter:function(value,rec,index){
									var existFileName = rec.title;
									var fileName = rec.downPath;
									if(fileName!=null && ""!=fileName){
										existFileName += fileName.substring(fileName.indexOf("."),fileName.length);
									}
									var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+in_exportExcelPath; 
									var status = rec.status; //状态
									var id=rec.id;
									var edit='';
									if(status==5){
										edit='<a href="#" onclick="showWindow(\''+id+'\',\'1\')">审核</a>&nbsp;|&nbsp;';
									}
									edit+='<a href="#" onclick="querytable(\''+id+'\')">查看合同</a>&nbsp;|&nbsp;';
									edit+='<a href="#" onclick="contractPaymentBycontractId(\''+id+'\');"><font style="color: red;">查看金额</font></a>&nbsp;|&nbsp;';
									edit+='<a href="#" onclick="shengChengcon(\''+id+'\')">重新打包</a>&nbsp;|&nbsp;';
									edit+='<a href="'+downPath+'">下载合同</a>';
									return edit;
								}
								
							}
							]],
							//toolbar:[],
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
					
				}
			});
			//分页工具条
			 $('#tt').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				     $(this).pagination('loaded');
				    }
			 });
			 
				var sel = $("#tbUser");
				sel.empty();//清除select中的 option
				var radioValue = $("input[type='radio']:checked").val();
				$.post(ctx+'/user/user/user!queryUserCheck.action',
						{
						 'status':status,
						 'checkResult':radioValue
						}
						,function(data){
					var datas = eval("("+data+")");
					if(null!=datas && ""!=datas){
						if("ok"==datas.msg){
							for(var i = 0;i < datas.rows.length; i++) {
								var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
								sel.append(opt);  
							}
						}else if("no"==datas.msg){  //已经是最后一个活动，不需要显示审核人
							$("#reject").hide(); 
							$("#checker").hide();
							var opt = $("<option>").text("1").val("last");  
							sel.append(opt);  
							wetherLastActive=true;
						}
					}
				});	
		});	


//生成合同
function shengChengcon(paramsId) {
	$.post(ctx+'/quotations/quotations/contractQuotations!contractFileToPackage.action',{
		conid:paramsId
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
	});		
}

//查看临时合同信息
function tolookContactInfo(paramsid) {
	window.location = ctx+"/contractTemporary/contractTemporaryInfo.jsp?contractIds="+paramsid+'&backUrl=/checkContract/checkContract_manage.jsp';
}

//查看正式合同配置单
function querytable(paramsId,paramsName) {
	window.location = ctx+"/contract/createcontract/file.jsp?contractIds="+ paramsId + "&projecksName="+ paramsName+'&backUrl=/checkContract/checkContract_manage.jsp';
}		