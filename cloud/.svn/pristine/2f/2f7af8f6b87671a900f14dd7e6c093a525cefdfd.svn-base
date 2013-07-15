//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: ctx+'/suppliers/suppliers/suppliersQuotations!listSuppliersQuotations.action',
		width: '100%',
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
                    {field:'ck',checkbox:true,align:'center'},
					{field:'superliersQuotationsId',title:'ID',width:60,align:'center',hidden:true},
					{field:'suppliersQuotationsName',title:'供应商报价单名称',width:80,align:'center'},	
					{field:'suppliers',title:'供应商',width:80,align:'center'},
					{field:'url',title:'文件',width:80,align:'center',
						formatter:function(value,rec,index){
							return '<a href="#" onclick="">'+rec.url+'</a>';
						}
					},
					{field:'creator',title:'创建者',width:80,align:'center'},
					{field:'createtime',title:'创建时间',width:80,align:'center'},
					{field:'description',title:'描述',width:80,align:'center'},	
					{field:'operate',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.superliersQuotationsId;
								var edit;
								edit='<a href="#" onclick="deleteSupplier(\''+id+'\');">删除</a>&nbsp;|&nbsp;<a href="#" onclick="updateSuppliers(\''+id+'\');">修改</a>';
								return edit;
							}
						}
					]],
					
					toolbar:[ {
						id:'btnadd',
						text:'上传供应商报价单',
						iconCls:'icon-add',
						handler:function(){
							showWindow();
						}
					},{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							deleteSomeSupplier();
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

	//获取供应商
	getSuppliers();
});	

function getSuppliers(){
	//加载所属项目
	var suppliers = $("#suppliersId");  
	suppliers.empty();//清除select中的 option
	$.post(ctx+'/suppliers/suppliers/suppliers!findAll.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
				suppliers.append(opt);  
			}
		}
	});
}

