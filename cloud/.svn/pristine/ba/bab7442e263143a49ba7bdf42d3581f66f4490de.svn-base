//报价表
	   $(function(){
			$('#tt').datagrid({
				url: ctx+'/quotations/quotations/quotations!myQuotations.action',
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
                         //   {field:'ck',checkbox:true,align:'center'},
							{field:'id',title:'ID',width:60,align:'center',hidden:true},
					//		{field:'dataSourceName',title:'所属数据源',width:300,align:'center'},
						//	{field:'projectId',title:'所属项目id',width:10,align:'center',hidden:true},
						//	{field:'projectName',title:'所属项目',width:300,align:'center'},
						//	{field:'downUrl',title:'下载地址',width:300,align:'center'},
							{field:'title',title:'报价表名称',width:300,align:'center',
								formatter:function(value,rec,index)
                                 {
									var title=rec.title;
									var projectName=rec.projectName;
									var title=rec.title;
									var id=rec.id;
									var edit;
									edit='<a href="#" onclick="toRapaport(\''+id+'\',\''+projectName+'\',\''+title+'\')">'+title+'</a>';
									return edit;
                                 }								
								},
							{field:'catalogTotalPrice',title:'目录合价(元)',width:250,align:'center',
									formatter:function(value,rec,index){
										value = value.replace(/,/g,"");
										var num = new Number(value);
										return num.toFixed(0);
									}
								},
							{field:'afterDiscountPrice',title:'折扣后价格(元)',width:250,align:'center',
									formatter:function(value,rec,index){
										value = value.replace(/,/g,"");
										var num = new Number(value);
										return num.toFixed(0);
									}
								},
							{field:'afterDiscountNowPrice',title:'折扣后现场价(元)',width:250,align:'center',
									formatter:function(value,rec,index){
										value = value.replace(/,/g,"");
										var num = new Number(value);
										return num.toFixed(0);
									}
								},
							{field:'totalPrice',title:'合计(元)',width:200,align:'center',
									formatter:function(value,rec,index){
										value = value.replace(/,/g,"");
										var num = new Number(value);
										return num.toFixed(0);
									}
								},
							{field:'createtime',title:'创建时间',width:150,align:'center',hidden:true},
							{field:'creatorName',title:'创建者',width:100,align:'center',hidden:true},
							{field:'status',title:'状态',width:150,height: 30,align:'center',
								 formatter:function(value,rec,index)
								 {
									 var status=rec.status;
									 var edit;
									  if(0==status){
										   edit='无效';
									  }else if(1==status){
										   edit='未审核';
									  }else if(2==status){
										  edit='待审核';
									  }else if(3==status){
										  edit='审核通过';
									  }else if(4==status){
										  edit='审核不通过';
									  }
									  return edit;
								 }
							},
							{field:'opt',title:'操作',width:300,align:'center',
								formatter:function(value,rec,index){
									var status=rec.status;
									var projectid = rec.projectId;
									var id=rec.id;
									var fileName = rec.downUrl;
									var projectName=rec.projectName;
									var title=rec.title;
									//转码
									var existFileName = rec.title+".xlsx";
									var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath;
									var edit='';
									//&nbsp;<a href="#" onclick="deleteQuotations(\''+id+'\')">删除</a>&nbsp;|
									if(1==status || 4==status){
										edit='<a href="#" onclick="showDataInfoDiv(\''+id+'\',\''+projectid+'\')">提交</a>&nbsp;|&nbsp;';
									}
									edit+='<a href="'+downPath+'">下载</a>&nbsp;|&nbsp;<a href="#" onclick="redio(\''+id+'\')">打包</a>';
									return edit;
								}
								
							}
							]],
							/*toolbar:[{
								id:'btndelete',
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									$('#btnsave').linkbutton('enable');		
									deleteSomeRapaport();
								}
							}],*/
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
			 
			//下拉选择框数据加载  审核人
			var sel4 = $("#tbUser");  
			sel4.empty();//清除select中的 option
			$.post(ctx+'/user/user/user!queryAllCaudit.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
						sel4.append(opt);  
					}
				}
			});
		});	
	   //隐藏存储操信息操作
	   function hideDiv() {
	   	$("#mask").remove();
	   	$("#addVmDiv").animate({
	   		left : 0,
	   		top : 0,
	   		opacity : "hide"
	   	}, "slow");
	   }
		//创建列菜单
		function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
			var fields = $('#tt').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						$('#tt').datagrid('hideColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						$('#tt').datagrid('showColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}

		var threeCluomn = [[  		
		                	{field:'id',title:'ID',width:60,align:'center',hidden:true},
		                	{field:'projectName',title:'项目名称',width:140,align:'center'},
		                	{field:'title',title:'配置单名称',width:140,align:'center',
		                		formatter:function(value,rec,index)
                                {
									var title=rec.title;
									var id=rec.id;
									var edit;
									edit='<a href="#" onclick="showConfigDIV(\''+id+'\')">'+title+'</a>';
									return edit;
                                }			
		                	},
		                	{field:'dataSourceType',title:'数据源类型',width:100,align:'center'},
		                	{field:'quantity',title:'数量',width:80,align:'center'},
		                	{field:'afterDiscountNowPrice',title:'折扣后目录现场价',width:100,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}
							},
		                	{field:'afterDiscountPrice',title:'折扣后目录价',width:100,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}
							},
		                	{field:'catalogTotalPrice',title:'目录合价',width:100,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}
							},
		                	{field:'totalPrice',title:'总价',width:100,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}
							}
		                ]];
		
	function toRapaport(id,projectName,title)
	{
	//	$("#projectNames").val(projectName);
		$("#rapaportNames").val(title);
		$('#t11').datagrid({
			url: ctx+'/quotations/quotations/quotations!queryByQuotationId.action?quotationsId='+id,
			width: '95%',
			height: 'auto',
			rownumbers:true,
			remoteSort: false,
			nowrap: false,
			pagination:false,
			autoRowHeight: false,
			singleSelect:true,
			striped: true,
			fitColumns: true,
			showGroup:true,
			columns:threeCluomn,
		});
		$("#addVmDiv1").window('open');
	}

	function hideDiv1()
	{
		$("#addVmDiv1").window('close');
	}