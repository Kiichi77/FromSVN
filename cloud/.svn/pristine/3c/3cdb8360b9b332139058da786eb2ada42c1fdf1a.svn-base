			$(function(){
				$('#tt').datagrid({
					url:  ctx+'/contractTemplates/contractTemplates/contractTemplates!listContractTemplates.action',
					width: '95%',
					height: 'auto',
					rownumbers:true,
					remoteSort: false,
					nowrap: false,
					autoRowHeight: false,
					striped: true,
					collapsible:true,
					fitColumns: true,
					pagination:true,
					showGroup:true,
					columns:[[  		   
			                    {field:'ck',checkbox:true,align:'center'},
								{field:'id',title:'ID',width:60,align:'center',hidden:true},
								{field:'name',title:'模版名称',width:100,align:'center'},
								{field:'status',title:'状态',width:80,align:'center',
									 formatter:function(value,rec,index){
										  var status=rec.status;
										  var edit;
										  if(0==status){
											 edit='审核中';
										  }else if(1==status){
											 edit='未审核';
										  }else if(2==status){
											 edit='销售审核通过';
										  }else if(3==status){
											  edit='销售审核未通过';
										  }else if(4==status){
											  edit='财务审核通过';
										  }else if(5==status){
											  edit='财务审核未通过';
										  }else if(6==status){
											  edit='经理审核通过';
										  }else if(7==status){
											  edit='经理审核未通过';
										  }
										  return edit;
									 }
								},
								{field:'createtime',title:'创建时间',width:100,align:'center'},
								{field:'operate',title:'操作',width:80,align:'center',
									formatter:function(value,rec,index){
										var id=rec.id;
										var status=rec.status;
										var edit;
										if(1 == status || 3 == status ||5 == status ||7 == status) {
											edit='<a href="#" onclick="deleteContractTemplates(\''+id+'\')">删除</a>&nbsp;|&nbsp;'
											+'<a href="#" onclick="updateTemplates(\''+id+'\',\''+rec.status+'\')">修改</a>&nbsp;|&nbsp;'
											+'<a href="addcontract.jsp?id='+id+'&iscontext='+rec.status+'">查看详情</a>&nbsp;|&nbsp;'
											+'<a href="#" onclick="showDataInfoDiv(\''+id+'\',\''+status+'\')">提交审核</a>';
										} else {
											edit='<a href="addcontract.jsp?id='+id+'&iscontext='+rec.status+'">查看详情</a>';
										}
										return edit;
									}
								}]],
								toolbar:[{
									id:'btnadd',
									text:'添加模版',
									iconCls:'icon-add',
									handler:function(){
										$('#btnsave').linkbutton('enable');
										showWindow();
									}
								}/*{
									id:'btndelete',
									text:'删除',
									iconCls:'icon-remove',
									handler:function(){
										$('#btnsave').linkbutton('enable');
										deleteSomecontract();
									}
								}*/],
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
//					     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
					     $(this).pagination('loaded');
					    }
				 });
				 //影藏流程任务分配弹出框
				// $('#processForm').hide();
				 onSelect();
			});	
			
			//加载用户下拉框
			function onSelect(){
				var sel = $("#tbUser");
				sel.empty();//清除select中的 option
				$.post(ctx+'/user/user/user!queryAllSalesIP.action',function(data){
					var datas = eval("("+data+")");
					if(null!=datas && ""!=datas){
						for(var i = 0;i < datas.rows.length; i++) {
							var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
							sel.append(opt);  
						}
					}
				});	
			};
			
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

			//弹出层***************************************
			//添加存储操信息操作
			function showWindow() {
			  $('#addVmDiv').window('open');
			}

			//隐藏存储操信息操作
			function hideDiv() {   
			  $('#addVmDiv').window('close');
			}  

			//弹出层***************************************
			//修改存储操信息操作
			function showWindow1() {
			   $('#addVmDiv1').window('open');  
			}

			//隐藏存储操信息操作
			function hideDiv1() {   
				$('#addVmDiv1').window('close');   
			}  