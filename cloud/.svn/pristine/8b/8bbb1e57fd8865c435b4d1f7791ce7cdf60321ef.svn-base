//弹出层***************************************
//添加存储操信息操作
function showWindow() {
	$("#addVmDiv").window('open');
}

//隐藏存储操信息操作
function hideDiv() {   
	$("#addVmDiv").window('close');
} 

function upload() {
	alert("操作成功!");
	hideDiv();
}

//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}

//根据ID删除发票
function deleteInvoice(id){
		$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
			if (r){  
		    	//设置为同步
		    	$.ajaxSettings.async = false;
		    	$.post(
		    		ctx+'/invoice/invoice/invoice!deleteInvoice.action',
		    		{id:id},
		    		function(data){
		    			var data = eval('(' + data + ')');
		    			if(null!=data && ""!=data){
		    				if(data.msg=="ok"){
		    					$.messager.alert("提示","删除成功！");
		    					$("#tt").datagrid("reload");
		    					//   hideDiv();
		    					    query();
		    				}else{
		    					$.messager.alert("提示","删除失败！");
		    				}
		    			}
		    		}
		    	);
			}
		})
}
//批量删除
function deleteSomeInvoice() {
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
	deleteInvoice(parm);
}


//saveOrUpdate
function saveOrUpdateInvoice() 
{
	var id = $("#id").val();
	if(null!=id && ""!=id && -1!=id){  //修改
		$('#addVmform').form('submit', {
			url:ctx+'/invoice/invoice/invoice!updateInvoice.action',
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
						window.location.href="../view/main.jsp";
//						hideDiv();
//						query();
					}else {
						$.messager.alert("提示","修改失败!");
					}
				}
			}
		}); 
	}else{ //添加
	var invoicemoney=$("#invoicemoney").val();
		if(null == invoicemoney || "" == invoicemoney)
			{
			$.messager.alert("提示","发票金额不能为空！");
			}else{
		$('#addVmform').form('submit', {
			url:ctx+'/invoice/invoice/invoice!addInvoice.action',
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
						hideDiv();
						query();
					}else {
						$.messager.alert("提示","添加失败!");
					}
				}
			}
		}); 
			}
	}
}

//查询并填充发票信息
function updateInvoice(id) {
	$.messager.confirm('提示','确定收到发票？',function(r){  
		if (r){  
	$.post(ctx+ '/invoice/invoice/invoice!updateStauts.action', {
		"id" : id
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			if(datas.msg=="ok"){
				$.messager.alert('提示', "确定收到！", 'info');
				query();
			}
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
		}
	})
}