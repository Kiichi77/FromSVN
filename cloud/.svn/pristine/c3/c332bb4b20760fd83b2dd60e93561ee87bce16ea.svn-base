
	   $(function(){
			$('#tt').datagrid({
				url: ctx+'/order/order/order!listByStauts.action',
				width: '95%',
				height: 'auto',
				pagination:true,
				rownumbers:true,
				remoteSort: false,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				columns:[[ 
                          //  {field:'ck',checkbox:true,align:'center'},
							{field:'contractOrderId',title:'ID',width:60,align:'center',hidden:true},
							{field:'orderNo',title:'订单编号',width:200,align:'center'},
							{field:'projectName',title:'项目名称',width:200,align:'center'},
							{field:'projectId',title:'项目id',width:200,align:'center',hidden:true},
							{field:'contractName',title:'合同名称',width:100,align:'center'},
							{field:'creator',title:'创建者',width:100,align:'center'},
							{field:'createtime',title:'创建时间',width:200,align:'center'},
						//	{field:'caudit',title:'审核人',width:200,align:'center'},
						//	{field:'purchase',title:'采购人',width:200,align:'center'},
							{field:'note',title:'备注',width:200,align:'center'},
							{field:'status',title:'订单状态',width:120,align:'center',formatter:function(value,rec,index){
									 if(0==value){
											return '询价中';
										 }else if(1==value){
											 return '审核中';
										 }else if(2==value){
											 return '审核通过';
										 }else if(3==value){
											 return '审核不通过';
										 }else if(4==value){
											 return '采购中';
										 }else if(5==value){
											 return '到货';
										 }else if(6==value){
											 return '完成';
										 }
								 }
							},
							{field:'opt',title:'操作',width:300,align:'center',formatter:function(value,rec,index){
									var contractOrderId=rec.contractOrderId;
									var projectId = rec.projectId;
									var edit;
									var status=rec.status;
									if(status==0){
									edit='<a href="#" onclick="showExecuteDIV(\''+contractOrderId+'\',\''+projectId+'\');">采购询价</a>';
									}else
										{
										edit='<a href="#" onclick="showInquiryDIV(\''+contractOrderId+'\');">查看</a>';
										}
									return edit;
								}
							}
							]],
					//		toolbar:[],
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
				     $(this).pagination('loaded');
				    }
			 });
			 $(".datagrid-toolbar").append($("#datagridsearch"));
			 
			 
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

		//隐藏通用单元数量设置框
		function hideNumberDIV() {   
			$("#numberDIV").window('close');
		} 