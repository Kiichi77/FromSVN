//根据ID删除数据源
function deleteDataSource(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(
			ctx+'/dataSource/dataSource/dataSource!deleteDataSource.action',
			{id:id},
			function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					 if(data.msg=="ok"){
					    	$.messager.alert("提示","删除成功！");
					    	$("#tt").datagrid("reload");
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
		$.each(rows, function (i, n) {
			$('#tt').datagrid('deleteRow',i);
		});
	}
	
}			 
//弹出层***************************************
//添加存储操信息操作
function showWindow() {
  var div_obj = $("#addDataInfoDiv");  
  var windowWidth = document.body.clientWidth;       
  var windowHeight = document.body.clientHeight;  
  var popupHeight = div_obj.height();       
  var popupWidth = div_obj.width(); 
  div_obj.css({"position": "absolute"})
  .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");
}


/**
 * 查看采购单下的订单
 */
function showOrderDetail(){
	window.location.href = ctx+"/purchaseOrder/orders.jsp";
}
//清空上传、数据详情表单
function clearVMFormFun(params) {
	if(1 == params) {		//清空数据源上传层
		$("#modelsName").val("");
		$("#modelsmiaoshu").val("");
		$("#modelshangchuan").val("");
	} else {
		$("#dinghuohao").val("");
		$("#chanpinshu").val("");
		$("#danjia").val("");
		$("#zhekoulv").val("");
		$("#chanpinbei").val("");
		$("#chanpinmiaoshu").val("");
		$("#dierbao").val("");
		$("#diyibao").val("");
		$("#disanbao").val("");
		$("#qitafei").val("");
		$("#anzhuangfei").val("");
	}
}

//隐藏存储操信息操作
function hideDiv() {   
	clearVMFormFun(1);
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 

//隐藏数据详情窗口操作
function hideDataInfoDiv() {
	 clearVMFormFun(2);
	 $("#addDataInfoDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}

//重新加载列表
function reloadDataGrid() {
	$('#tt').datagrid('reload');
}

//保存详细数据信息的方法
function addSave() {
	alert("添加成功！");
}
