//添加存储操信息操作
function showWindow() {
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");   
}
//隐藏存储操信息操作
function hideDiv() {
    $("#mask").remove();   
    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");
    clearFlavorinfoForm();
}  

//清空用户信息表单方法
function clearFlavorinfoForm() {
	$("#flavorId").val("");
	$("#flavorNames").val("");
	$("#vmcpus").val("");
	$("#vmRam").val("");
	$("#vmDisks").val("");
	$("#vmEphemeral").val("");
}

//删除用户
function deleteFlavor(paramsId) {
	//提示是否删除
	$.messager.confirm('提示', '是否删除选中数据?', function (r) { 
		if (!r) { 
			return; 
		} else {
			$.post(ctx+'/server/server/flavors!deleteFlavorsById.action',{flavorId:paramsId},function(data){
				if(data!=null && data!=""){
					if("error" == data.msg){
						alert("删除失败，请从新尝试");
					} else {
						queryFlavors();
						alert("删除成功");
						hideDiv();
					}
				} else {
					alert("系统异常，请稍后操作");
				}
			});
		}
	});
}

//批量删除策略
function deleteSomeFlavor() {
	//获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	//判断是否选择行
	if (!rows || rows.length == 0) { 
	$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
		return; 
	} 
	var parm = ""; 
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		parm +=  n.id+","; 
	}); 
	parm = parm.substring(0,parm.length-1);
	deleteFlavor(parm);
}

//查询用户信息
function queryFlavors() {
	$('#tt').datagrid('reload');
}

//提交修改用户信息
function submitFlavorInfo() {
	$('#addVmform').form('submit', {
		url:ctx+'/server/server/flavors!saveFlavorInfo.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			if(data!=null && data!=""){
				if("error" == data.msg){
					alert("操作失败，请从新尝试");
				} else {
					queryFlavors();
					alert("操作成功");
					hideDiv();
				}
			} else {
				alert("系统异常，请稍后操作");
			}
		}
	});
}