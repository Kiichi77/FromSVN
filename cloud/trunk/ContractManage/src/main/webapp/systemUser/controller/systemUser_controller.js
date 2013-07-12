//根据ID删除数据源
function deleteDataSource(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(
			ctx+'user/user/user!delete.action',
			{uid:id},
			function(data){
				var data = eval('(' + data + ')');
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
			
//批量删除
function deleteSomeData(id) {
	//获取表格选择行
	if(id!=-1){
		$('#tt').datagrid('deleteRow',id);
	}else{
		var rows = $('#tt').datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length == 0) { 
			$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
			return; 
		} 
		var parm = "";
		// 循环给提交删除参数赋值(音乐风格编码)
		$.each(rows, function(i, n) {
			parm += n.id + ",";
		});
		parm = parm.substring(0, parm.length - 1);
		deleteDataSource(parm);
	}
	
}			 
//弹出层***************************************
//添加存储操信息操作
function showWindow() {
	  $("#addDataInfoDiv").window('open');
}

//清空上传、数据详情表单
function clearVMFormFun() {
	$("#id").val("");
	$("#accouont").val("");
	$("#password").val("");
	$("#email").val("");
	$("#note").val("");
	$("#tbRole").val("1");
	$("#userName").val("");
	$("#phoneNumber").val("");
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

//添加用户
function addSave() {
	var id  = $("#userId").val();
	if(null!=id && ""!=id && -1!=id){//修改
		$('#addDataInfoform').form('submit', {
			url:ctx+'user/user/user!updateUser.action',
			onSubmit: function(){
				// 做某些检查
				// 返回 false 来阻止提交
				return $(this).form('validate'); 
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					if(data.msg=="ok"){
						$.messager.alert("提示","修改成功!");
						hideDataInfoDiv();
						reloadDataGrid();
					}else {
						$.messager.alert("提示","修改失败!");
					}
				}
			}
		}); 
	}else{ //添加
		$('#addDataInfoform').form('submit', {
			url:ctx+'user/user/user!saveUser.action',
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
	
}

//修改用户信息
function updateUser(id){
	//设置为同步
	$.ajaxSettings.async = false;
	$.post(
		ctx+'user/user/user!findById.action',
		{'model.id':id},
		function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				 if(data.msg=="ok"){
					 var userEntity = data.userEntity;
					 $("#userId").val(userEntity.id);
					 $("#accouont").val(userEntity.accouont);
					 $("#password").val(userEntity.password);
					 $("#email").val(userEntity.email);
					 $("#note").val(userEntity.note);
					 $("#tbRole").val(userEntity.tbRole.id);
					 $("#jobDuties").val(userEntity.jobDuties.id);
					 $("#jobPlace").val(userEntity.jobPlace.id);
					 $("#userName").val(userEntity.userName);
					 $("#phoneNumber").val(userEntity.phoneNumber);
					 $("#pwd").hide();
					 $("#aa").hide();
					 showWindow();
				 }else{
					 $.messager.alert("警告","数据加载失败!");
				 }
			}
		});
}

//初始化密码
function initialize(id){
	$.messager.confirm('提示','确定要初始化密码吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(
			ctx+'user/user/user!initPasswd.action',
			{'model.id':id},
			function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					 if(data.msg=="ok"){
					    	$.messager.alert("提示","密码初始化成功！");
					 }else{
					    	$.messager.alert("提示","密码初始化失败！");
					 }
				}
			});
		}
	});
}
	