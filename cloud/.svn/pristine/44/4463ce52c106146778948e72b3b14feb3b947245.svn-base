
//重新加载列表
function query() {
	var kwords = $("#starttime").datebox("getValue").trim();
	var filetype = $("#endtime").datebox("getValue").trim();
	$('#tt').datagrid('load',{"contractVo.createTime":kwords,"contractVo.endTime":filetype});
}


//隐藏存储操信息操作
function hideDiv() {
	$("#purchaseDiv").window('close');
}


//查看采购订单详情
function showfindOrderDetailByPurchaseId(purchaseOrderId) {
	var div_obj = $("#purchaseDiv");
	var contracids = $("#contractids").val();
	$("#purchaseDiv").window('open');
		$('#purchaseOrder').datagrid({
			url: ctx+'/purchaseorder/purchaseorder/relationship!findOrderDetailByPurchaseId.action?purchaseOrderId='+purchaseOrderId,
			width: '95%',
			height: 'auto',
			pagination:false,
			rownumbers:true,
			remoteSort: false,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			fitColumns: true,
			columns:[[ 
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'purchaseOrderNo',title:'订单编号',width:300,align:'center'},
					{field:'configModelsId',title:'配置单ID',width:300,align:'center',hidden:true},
					{field:'configModels',title:'配置',width:200,align:'center',
						formatter:function(value,rec,index)
                        {
							var configModelsId=rec.configModelsId;
							var configModels=rec.configModels;
							var edit;
							edit='<a href="#" onclick="configDIV(\''+configModelsId+'\')">'+configModels+'</a>';
							return edit;
                        }				
						},
					{field:'purchaseQuantity',title:'采购数量',width:200,align:'center'}
					]],  
		//	toolbar:[],
			onHeaderContextMenu: function(e, field){
				e.preventDefault();
				if (!$('#tmenu').length){
					createColumnMenu();
				}
				$('#tmenu').menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
		});
}

//更改采购订单状态
function updateStauts(id,processId){
	$.messager.confirm('提示','确定已到货？',function(r){  
		if (r){  
	    	//设置为同步
	    	$.ajaxSettings.async = false;
	    	$.post(
	    		ctx+'/purchaseorder/purchaseorder/purchaseorder!updateStatus.action',
	    		{id:id,processid:processId},
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
