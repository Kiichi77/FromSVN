//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: ctx+'/suppliers/suppliers/suppliers!pageQuerySuppliers.action',
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'companyName',title:'公司名称',width:80,align:'center'},	
					{field:'address',title:'公司地址',width:80,align:'center'},	
					{field:'contact',title:'联系人',width:80,align:'center'},
					{field:'phone',title:'电话',width:80,align:'center'},
					{field:'email',title:'邮箱',width:80,align:'center'},
					{field:'taxpayerNumber',title:'纳税人识别号',width:80,align:'center'},
					{field:'openBank',title:'开户行',width:80,align:'center'},
					{field:'accountsName',title:'帐号名称',width:80,align:'center'},
					{field:'accounts',title:'帐号',width:80,align:'center'},
					{field:'state',title:'状态',width:80,align:'center',formatter:function(value,rec,index){
						 var status=rec.state;
						 var edit;
						 if(0==status){
							 edit='待审核';
						 }else if(1==status){
							 edit='审核中';
						 }else if(2==status){
							 edit='审核通过';
						 }else if(3==status){
							 edit='审核不通过';
						 }
						 return edit;
					 }
					},	
					{field:'description',title:'描述',width:80,align:'center'},	
					{field:'operate',title:'操作',width:120,align:'center',
							formatter:function(value,rec,index){
								var status=rec.state;
								var id=rec.id;
								var edit;
								if(status==0 || status==3){
									edit='<a href="#" onclick="deleteSupplier(\''+id+'\');">删除</a>&nbsp;|&nbsp;<a href="#" onclick="updateSuppliers(\''+id+'\');">修改</a>&nbsp;|&nbsp;<a href="#" onclick="showSubmitWin(\''+id+'\');">提交</a>';
								}else{
									edit='无';
								}
								return edit;
							}
						}
					]],
					
					toolbar:[ {
						id:'btnadd',
						text:'新增供应商',
						iconCls:'icon-add',
						handler:function(){
							showWindow();
						}
					}/*,{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							deleteSomeSupplier();
						}
					}*/
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
//弹出层***************************************
function showWindow() {
	$('#addVmDiv').window('open');
}
//隐藏存储操信息操作
function hideDiv() {
	$('#addVmDiv').window('close');
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

//显示提交审核弹出层***********************************************
function showSubmitWin(id){
	$("#checkedVo_id").val(id); //设置供应商id
	$('#addprocessdiv').window('open');
}

//隐藏提交审核弹出层***********************************************
function closeSubmitCheckedWin(){
	$('#addprocessdiv').window('close');
}