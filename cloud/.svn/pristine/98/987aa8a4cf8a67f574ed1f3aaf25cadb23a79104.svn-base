//根据ID删除数据源
function deleteDataSource(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(ctx+'role/role/role!delete.action',{roleIds:id},
			function(data){
				if(null!=data && ""!=data){
//					 if(data.msg=="ok"){
//					    	$.messager.alert("提示","删除成功！");
//					    	$("#tt").datagrid("reload");
//					    	reloadDataGrid();
//					 }else{
//					    	$.messager.alert("提示","删除失败！");
//					 }
					reloadDataGrid();
					$.messager.alert("提示",data.msg);
				}
			});
		}
	});
}

//批量删除
function deleteSomeRole() {
	//获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	//判断是否选择行
	if (!rows || rows.length == 0) { 
	$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
		return; 
	} 
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
		if (r){  
			//设置为同步
			$.ajaxSettings.async = false;
			// 循环给提交删除参数赋值(音乐风格编码)
			var parm = "";
			// 循环给提交删除参数赋值(音乐风格编码)
			$.each(rows, function(i, n) {
				parm += n.id + ",";
			});
			parm = parm.substring(0, parm.length - 1);
			$.post(ctx+'role/role/role!delete.action',{roleIds:parm},
				function(data){
					var data = eval('(' + data + ')');
					if(null!=data && ""!=data){
//						 if(data.msg=="ok"){
//						    	$.messager.alert("提示","删除成功！");
//						    	$("#tt").datagrid("reload");
//						    	reloadDataGrid();
//						 }else{
//						    	$.messager.alert("提示","删除失败！");
//						 }
						reloadDataGrid();
						$.messager.alert("提示",data.msg);
					}
				});
			}
		});
}

//清空上传、数据详情表单
function clearVMFormFun() {
	$('#jsids').val("");
	$('#jsname').val("");
	$('#jsnode').val("");
}

//弹出层***************************************
//添加存储操信息操作
function showWindow(){
	$('#addDataInfoDiv').window('open');
}
//隐藏数据详情窗口操作
function hideDataInfoDiv() {
	 clearVMFormFun();
	 $("#addDataInfoDiv").window('close'); 
}

//重新加载列表
function reloadDataGrid() {
	$('#tt').datagrid('reload');
}

//保存详细数据信息的方法
function addSave() {
	var nodes = $('#tt2').tree('getChecked');
	var neednodeid = '';
	for(var i=0; i < nodes.length; i++){
		neednodeid += nodes[i].id + ',';
	}
	neednodeid = neednodeid.substring(0,neednodeid.length-1);
	var roleid = $('#jsids').val();
	var rolename = $('#jsname').val();
	var rolenote = $('#jsnode').val();
	var url = "";
	if(null != roleid && "" != roleid) {
		url = ctx+ "role/role/role!updateRoleRight.action";
	} else {
		url = ctx+ "role/role/role!saveRoleRight.action";
	}
	$.post(url,{
		'model.rolename':rolename,
		'model.note':rolenote,
		'model.id':roleid,
		'roleIds':neednodeid
	},function(data) {
		reloadDataGrid();
		hideDataInfoDiv();
		$.messager.alert('提示', "操作成功", 'info');
	});
}
