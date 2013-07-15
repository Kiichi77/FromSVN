
$(function(){
	$('#tt').datagrid({
		url: ctx+'/order/order/order!listContractOrderByStauts.action?contractOrderId='+contractOrderId,
		width: '100%',
		height: 'auto',
		pagination:true,
		remoteSort: false,
		nowrap: false,
		singleSelect: true,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
		fitColumns: true,
		showGroup:true,
		columns:[[  		   
					{field:'contractOrderId',title:'ID',width:60,align:'center',hidden:true},
					{field:'contractName',title:'合同名称',width:120,align:'center'},	
					{field:'orderNo',title:'销售订单号',width:80,align:'center'},	
					//{field:'quoteNo',title:'报价单号',width:80,align:'center'},	
					{field:'creator',title:'销售人',width:80,align:'center'},
				//	{field:'suppliers',title:'供应商',width:80,align:'center'},
					{field:'createtime',title:'创建时间',width:80,align:'center'},
				//	{field:'purchaseCompany',title:'采购公司',width:80,align:'center'},
					{field:'status',title:'状态',width:80,align:'center',
						formatter:function(value,rec,index){
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
								 }else if(7==value){
									 return '采购完成';
								 }
						}
					},
					{field:'opt',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.contractOrderId;
								var stauts=rec.status;
								var edit;
//								if(7==stauts)
//									{
//								   edit='<a href="aftersale_detail_manage.jsp?contractOrderId='+id+'">查看订单</a>&nbsp;|&nbsp;<a href="#" onclick="updateOrderStauts(\''+id+'\')">全部到货</a>';
//									}else if(7==stauts)
//										{
								   edit='<a href="aftersale_detail_manage.jsp?contractOrderId='+id+'">查看订单</a>';
//										}
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
	$(".datagrid-toolbar").append($("#datagridsearch"));

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

//更改销售订单状态
function updateOrderStauts(id){
	$.messager.confirm('提示','确定全部到货？',function(r){  
		if (r){  
	    	//设置为同步
	    	$.ajaxSettings.async = false;
	    	$.post(
	    		ctx+'/order/order/order!updateOrderStauts.action',
	    		{contractOrderId:id},
	    		function(data){
	    			var data = eval('(' + data + ')');
	    			if(null!=data && ""!=data){
	    				if(data.msg=="ok"){
	    					$.messager.alert("提示","已到货！");
	    					$("#tt").datagrid("reload");
	    					    query();
	    				}else{
	    					$.messager.alert("提示","系统异常！");
	    				}
	    			}
	    		}
	    	);
		}
	})
}
