$(function(){
	$('#tt').datagrid({
		url: ctx+'/purchaseorder/purchaseorder/purchaseorder!listByContractOrderId.action?contractOrderId='+contractOrderId,
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'contractOrderNo',title:'销售订单号',width:80,align:'center'},	
					{field:'orderNo',title:'订单编号',width:80,align:'center'},	
					{field:'quoteNo',title:'报价单号',width:80,align:'center'},
				//	{field:'processId',title:'任务编号',width:80,align:'center'},//,hidden:true
					{field:'create',title:'采购人',width:80,align:'center'},
					{field:'suppliers',title:'供应商',width:80,align:'center'},
					{field:'createTime',title:'下单时间',width:80,align:'center'},
					{field:'status',title:'状态',width:80,align:'center',
						formatter:function(value,rec,index){
							if(7==value) {
								return "已收货";
							} else {
								return "待收货";
							}
						}
					},
					{field:'opt',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var processId=rec.processId
								var edit;
								var status = rec.status;
								if(7==status){
									edit='<a href="#" onclick="showfindOrderDetailByPurchaseId(\''+id+'\')">查看配置详情</a>';
								} else {
									edit='<a href="#" onclick="showfindOrderDetailByPurchaseId(\''+id+'\')">查看配置详情</a>&nbsp;|&nbsp;<a href="#" onclick="updateStauts(\''+id+'\',\''+processId+'\')">确认收货</a>';
								}
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

//可选配置
var optiona = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'typeId',title:'typeId',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center'},
	{field:'unitPrice',title:'目录单价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'totalprice',title:'目录合价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'discountRate',title:'折扣率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit=value*100;
			return edit;
		}
	},
	{field:'rebateprice',title:'折扣后价格(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'otherRates',title:'运保及其他费率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit=value*100;
			return edit;
		}
	},
	{field:'rebateafter',title:'折扣后现场价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'installServiceCharge',title:'安装服务费(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计(元)',width:30,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	}
]];

function configDIV(configId){
	$("#configDiv").window('open');
	$('#t1').datagrid({
		url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=1",
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		pagination:true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:optiona
	});
	//分页工具条
	 $('#t1').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
		$('#t2').datagrid({
			url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=2",
			width: '95%',
			height: 'auto',
			rownumbers:true,
			remoteSort: false,
			nowrap: false,
			pagination:true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			fitColumns: true,
			showGroup:true,
			columns:optiona
		});
		//分页工具条
		 $('#t2').datagrid('getPager').pagination({
			    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
			    onBeforeRefresh:function(pageNumber, pageSize){
			     $(this).pagination('loading');
			     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
			     $(this).pagination('loaded');
			    }
		 });
			$('#t3').datagrid({
				url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=3",
				width: '95%',
				height: 'auto',
				rownumbers:true,
				remoteSort: false,
				nowrap: false,
				pagination:true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				showGroup:true,
				columns:optiona
			});
			//分页工具条
			 $('#t3').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				     $(this).pagination('loaded');
				    }
			 });
}