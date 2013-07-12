//付款详情
$(function(){
	$('#tt').datagrid({
		url: ctx+'/purchasecontract/purchasecontract/purchaseOrderContractBill!queryAll.action?purchaseContractId='+purchaseContractId,
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
		columns:[[  		   
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'projectName',title:'项目名称',width:100,align:'center'},
					{field:'contract',title:'所属合同',width:100,align:'center'},
					{field:'title',title:'采购合同',width:80,align:'center'},
					{field:'contractMoney',title:'合同金额(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},
					{field:'payment',title:'当前付款(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},	
					{field:'createTime',title:'付款时间',width:80,align:'center'},	
					{field:'checkNo',title:'支票号/流水号',width:80,align:'center'},	
					{field:'paymentTime',title:'开票时间/付款时间',width:80,align:'center'},
					{field:'suppliers',title:'供应商',width:80,align:'center'},
					{field:'purchaseCompany',title:'采购公司',width:80,align:'center'},	
					{field:'useOverPay',title:'是否使用对冲',width:80,align:'center',
						 formatter:function(value,rec,index){
							 var useOverPay=rec.useOverPay;
							 var edit;
							 if(0==useOverPay){
								  edit='不对冲';
							 } else if(1==useOverPay) {
									   edit='对冲';
							 }
							  return edit;
							 }		
					},	
					{field:'userOverPayMoney',title:'对冲金额(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},
					{field:'payer',title:'付款人',width:80,align:'center'}
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