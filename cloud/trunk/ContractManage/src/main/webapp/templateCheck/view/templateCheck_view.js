			$(function(){
				$('#tt').datagrid({
					url:  ctx+'/contractTemplates/contractTemplates/contractTemplates!listContractTemplates.action',
					width: '95%',
					height: 'auto',
					pagination:true,
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
			              //      {field:'ck',checkbox:true,align:'center'},
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
									  }else{
										  edit='未知';
									  }
									  return edit;
								 }
								},
								{field:'createtime',title:'创建时间',width:100,align:'center'},
								{field:'operate',title:'操作',width:80,align:'center',
									formatter:function(value,rec,index){
										var status=rec.status;
										var id=rec.id;
										var edit;
										edit='<a href="templateDoc.jsp?id='+id+'&status='+status+'">查看模板文件</a>';
										return edit;
									}
								}
								]],
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
				// 分页工具条
				 $('#tt').datagrid('getPager').pagination({
					    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
					    onBeforeRefresh:function(pageNumber, pageSize){
					     $(this).pagination('loading');
					     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
					     $(this).pagination('loaded');
					    }
				 });
				 
			});	
			
			// 创建列菜单
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
