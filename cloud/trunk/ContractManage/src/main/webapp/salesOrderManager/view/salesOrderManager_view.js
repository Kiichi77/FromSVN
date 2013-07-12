//我的销售订单
   $(function(){
		$('#tt').datagrid({
		url: ctx+'/order/order/order!myContractOrder.action',
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
					{field:'contractOrderId',title:'ID',width:40,align:'center',hidden:true},
					{field:'orderNo',title:'订单编号',width:150,align:'center'},
					{field:'projectName',title:'项目名称',width:200,align:'center'},
					{field:'contractNo',title:'合同编号',width:200,align:'center'},
					{field:'contractName',title:'合同名称',width:200,align:'center'},
					{field:'contractType',title:'合同类型',width:120,align:'center',formatter:function(value,rec,index){
						 var contractType=rec.contractType;
						 var edit;
						 if(1==contractType){
							 edit='正式合同';
						 }else if(2==contractType){
							 edit='临时合同';
						 }
						  return edit;
					 }
				},
				{field:'receiveInfo',title:'收货地址',width:400,align:'center'},
				{field:'note',title:'备注',width:200,align:'center'},
				{field:'status',title:'订单状态',width:120,align:'center',formatter:function(value,rec,index){
						 var status=rec.status;
						 var edit;
							if(0==status){
								return '询价中';
							 }else if(1==status){
								 return '审核中';
							 }else if(2==status){
								 return '审核通过';
							 }else if(3==status){
								 return '审核不通过';
							 }else if(4==status){
								 return '采购中';
							 }else if(5==status){
								 return '到货';
							 }else if(6==status){
								 return '完成';
							 }else if(7==status){
								 return '采购完成';
							 }
					 }
				},
				{field:'opt',title:'操作',width:300,align:'center',formatter:function(value,rec,index){
						var contractOrderId=rec.contractOrderId;
						var edit;
						edit='<a href="#" onclick="showExecuteDIV(\''+contractOrderId+'\');">详情</a>&nbsp;|&nbsp;'
						+'<a href="#" onclick="showPurchaseOrder(\''+contractOrderId+'\');">采购订单</a>';
						return edit;
					}
				}
				]],
				toolbar:[],
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
			$("#numberDIV").animate({left: 0, top: 0, opacity: "hide" }, "slow");
		} 