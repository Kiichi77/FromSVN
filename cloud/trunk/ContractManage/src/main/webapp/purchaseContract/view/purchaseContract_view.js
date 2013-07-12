$(function(){
	$('#tt').datagrid({
		url: ctx+'/purchasecontract/purchasecontract/purchasecontract!queryAll.action',
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
                  //  {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'contract',title:'所属合同',width:60,align:'center'},
					{field:'title',title:'采购合同名称',width:60,align:'center'},
					{field:'paymentType',title:'付款方式',width:60,align:'center'},
					{field:'suppliers',title:'供应商',width:60,align:'center'},
					{field:'create',title:'创建者',width:60,align:'center'},
					{field:'createtime',title:'创建时间',width:60,align:'center'},
					{field:'pathName',title:'采购合同文件',width:60,align:'center',
						formatter:function(value,rec,index){
							var name = rec.pathName;
							var docname = rec.title;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+name+"&existFileName="+docname+"&exportPath="+exportPath;
							return '<a href="'+downPath+'">下载</a>';
						}
					},
					{field:'operate',title:'操作',width:100,align:'center',
						formatter:function(value,rec,index){
							var id=rec.id;
							return '<a href="purchaseInvoice.jsp?purchaseContractId=\''+id+'\'">进项发票</a>';
						}
					}
				]],
					toolbar:[{
						id:'btnadd',
						text:'新增',
						iconCls:'icon-add',
						handler:function(){
							optType=1;
					//		$('#btnsave').linkbutton('enable');
					//		$("#filediv").css("display","");
					//		tempDocsId = "";
							showWindow();
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
	//隐藏、显示
	$("#dd").toggle();
//	$("#add").toggle();
	$(".datagrid-toolbar").append($("#datagridsearch"));
	
	//下拉选择框数据加载  合同
	var sel = $("#contract");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/contractTemplates/contractTemplates/contract!listContract.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].title).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});
	//下拉选择框数据加载  供应商
	var sel1 = $("#suppliers");  
	sel1.empty();//清除select中的 option
	$.post(ctx+'/suppliers/suppliers/suppliers!listSuppliers.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
				sel1.append(opt);  
			}
		}
	});
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

//弹出添加的弹出框
function showWindow(){
	 $("#add").window('open');
}
//关闭弹出框
function hideDiv(){
	$('#contractName').val("");
	 $("#add").window('close');
}