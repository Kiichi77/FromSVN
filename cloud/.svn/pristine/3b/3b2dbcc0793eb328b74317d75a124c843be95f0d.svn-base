function changeOverpay()
{
	 var overpay=$("#useOverPay").val();
	 if(overpay==0)
		 {
		  $("#OverPayDIV").hide();
		 }else
			 {
			 $("#OverPayDIV").show();
			 }
}
$(function(){
	$('#tt').datagrid({
		url: ctx+'/purchasecontract/purchasecontract/purchasecontract!queryAll.action?contratFileId='+contratFileId,
		width: '100%',
		height: 'auto',
//		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: true,
		striped: true,
		collapsible:true,
		fitColumns: true,
		pagination:true,
		columns:[[  		   
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'projectName',title:'项目名称',width:120,align:'center'},	
					{field:'contract',title:'所属合同',width:120,align:'center'},	
					{field:'contractMoney',title:'总价(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}			
					},
					{field:'create',title:'采购人',width:80,align:'center'},
					{field:'title',title:'采购合同',width:80,align:'center'},	
					{field:'suppliers',title:'供应商',width:80,align:'center'},
					{field:'createtime',title:'创建时间',width:80,align:'center'},
					{field:'isPay',title:'状态',width:80,align:'center',
						 formatter:function(value,rec,index){
							 var status=rec.isPay;
							 var edit;
							 if(0==status){
								  edit='待付款';
							 } else if(1==status) {
									   edit='已付款';
							 }
							  return edit;
							 }	
					},
					{field:'opt',title:'操作',width:100,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var status=rec.isPay;
								var edit;
								if(status==0){
								edit='<a href="#" onclick="payment(\''+id+'\')">确认付款</a>&nbsp;|&nbsp;<a href="payment_detail.jsp?purchaseContractId='+id+'">查看付款明细</a>';
								}else{
								edit='<a href="payment_detail.jsp?purchaseContractId='+id+'">查看付款明细</a>';
								}
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
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	$(".datagrid-toolbar").append($("#datagridsearch"));
	
	//下拉选择框数据加载  采购方公司
	var sel5 = $("#purchaseCompany");  
	sel5.empty();//清除select中的 option
	$.post(ctx+'/customer111/customer111/purchaseCompany!queryAllPurchaseCompany.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
				sel5.append(opt);  
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

//弹出层***************************************
function showWindow() {
	changePaymetType();
	$('#addVmDiv').window('open');
}