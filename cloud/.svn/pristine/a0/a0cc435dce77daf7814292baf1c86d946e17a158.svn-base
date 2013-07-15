//弹出层***************************************
//添加存储操信息操作
function showWindow(paramsId,orderid) {
	window.location.href = ctx+"InquiryOrder/orderCheckedDetail.jsp?statusid="+paramsId+"&contractOrderId="+orderid;
}

//隐藏存储操信息操作
function hideDiv() {   
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 

//隐藏数据详情窗口操作
function hideDataInfoDiv() {
	 $("#addDataInfoDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}

//重新加载列表
function reloadDataGrid() {
	$('#tt').datagrid('reload');
}

//保存详细数据信息的方法
function addSave() {
	alert("添加成功！");
	hideDiv();
}

