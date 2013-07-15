


//报价表
	   $(function(){
			$('#tt').datagrid({
				url: ctx+'/quotations/quotations/quotations!listQuotations.action',
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
                            {field:'ck',checkbox:true,align:'center'},
							{field:'id',title:'ID',width:60,align:'center',hidden:true},
							{field:'dataSourceName',title:'所属数据源',width:300,align:'center'},
							{field:'title',title:'项目名称',width:300,align:'center'},
							{field:'createtime',title:'创建时间',width:200,align:'center'},
							{field:'createname',title:'创建者',width:100,align:'center',
								formatter:function(value,rec,index){
									return 'admin';
							}
							},
							{field:'status',title:'状态',width:100,height: 30,align:'center',
								 formatter:function(value,rec,index)
								 {
									 var status=rec.status;
									 var edit;
									  if(0==status)
										  {
										   edit='待审核';
										  }else if(1==status)
											  {
											   edit='审核通过';
											  }
										  else if(2==status)
										  {
										   edit='审核不通过';
										  }
									  return edit;
								 }
							},
							{field:'opt',title:'操作',width:200,align:'center',
								formatter:function(value,rec,index){
									var id=rec.id;
									//转码
									var existFileName = rec.title+".xlsx";
									var fileName = id+".xlsx";
									var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName;
									var edit;
									edit='<a href="#" onclick="redio(\''+id+'\')">重做</a>&nbsp;|&nbsp;<a href="#" onclick="deleteQuotations(\''+id+'\')">删除</a>&nbsp;|&nbsp;<a href="'+downPath+'">下载</a>';
									return edit;
								}
								
							}
							]],
							toolbar:[{
								id:'btndelete',
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									$('#btnsave').linkbutton('enable');		
									deleteSomeRapaport();
								}
							}],
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
