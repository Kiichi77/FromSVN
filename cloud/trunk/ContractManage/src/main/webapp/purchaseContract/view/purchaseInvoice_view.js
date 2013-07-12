$(function(){
	$('#tt').datagrid({
		url: ctx+'/invoice/invoice/purchaseinvoice!findByPurchaseContractId.action?purchaseContractId='+purchaseContractId,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		pagination:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		   
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
			//		{field:'contract',title:'所属合同',width:60,align:'center'},
					{field:'purchaseOrderContractFile',title:'采购合同名称',width:60,align:'center'},
					{field:'purchaseCompany',title:'采购公司',width:60,align:'center'},
					{field:'suppliers',title:'供应商',width:60,align:'center'},
					{field:'purchaseAccount',title:'供应商帐号',width:60,align:'center'},
					{field:'purchaseBankOpen',title:'供应商开户行',width:60,align:'center'},
					{field:'taxpayerNo',title:'纳税人识别号',width:60,align:'center'},
					{field:'dateInvoice',title:'开票日期',width:60,align:'center'},
					{field:'invoiceNo',title:'发票编号',width:60,align:'center'},
					{field:'amount',title:'金额(元)',width:60,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},
					{field:'pathName',title:'发票文件',width:60,align:'center',
						formatter:function(value,rec,index){
							var name = rec.pathName;
							var docname = rec.title;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+name+"&existFileName="+docname+"&exportPath="+exportPath;
							return '<a href="'+downPath+'">下载</a>';
						}	
					}
				]],
					toolbar:[{
						id:'btnadd',
						text:'返回',
						iconCls:'icon-back',
						handler:function(){
							 history.go(-1);
						}
					}
					         ],
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

