//删除用户
function deleteImage(paramsId) {
	//提示是否删除
	$.messager.confirm('提示', '是否删除选中数据?', function (r) { 
		if (!r) { 
			return; 
		} else {
			$.post(ctx+'/server/server/images!deleteImageById.action',{imageId:paramsId},function(data){
				if(data!=null && data!=""){
					if("error" == data.msg){
						alert("删除失败，请从新尝试");
					} else {
						queryImages();
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
function deleteSomeImage() {
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
	deleteImage(parm);
}

//查询用户信息
function queryImages() {
	$('#tt').datagrid('reload');
}