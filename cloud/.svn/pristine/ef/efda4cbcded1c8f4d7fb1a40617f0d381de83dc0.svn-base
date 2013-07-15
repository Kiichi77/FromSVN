//根据ID删除数据源
function deleteDataSource(tid){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(ctx+'right/right/right!delById.action',{id:tid},
			function(data){
				if(null!=data && ""!=data){
					 if(data.msg=="ok"){
				    	$.messager.alert("提示","删除成功！");
					    	reloadDataGrid();
				 }else{
				    	$.messager.alert("提示","删除失败！");
				    	}
				}
			});
		}
	});
}



//隐藏数据详情窗口操作
function hideDataInfoDiv() {
	 $("#addDataInfoDiv").window('close');
}

//重新加载列表
function reloadDataGrid() {
	$('#tt').datagrid('reload');
}

//保存详细数据信息的方法
function addSave() {
	$('#addDataInfoform').form('submit', {
		url:ctx+'right/right/right!save.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","添加成功!");
					hideDataInfoDiv();
					reloadDataGrid();
				}else {
					$.messager.alert("提示","添加失败!");
				}
			}
		}
	}); 
}
