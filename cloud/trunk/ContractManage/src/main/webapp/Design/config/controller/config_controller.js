//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}
//清空数据表单
function clearFormFun() {
	$('#addVmform').form('clear');
	$('#addVmform1').form('clear');
}

//根据ID删除数据源
function deleteConfigModels(id) {
	$.messager
			.confirm(
					'提示',
					'确定要删除选中的数据吗？',
					function(r) {
						if (r) {
							//设置为同步
							$.ajaxSettings.async = false;
							$.post(ctx+'/configModel/configModel/configModel!deleteConfigModels.action',
											{
												id : id
											},
											function(data) {
												var data = eval('(' + data
														+ ')');
												if (null != data && "" != data) {
													if (data.msg == "ok") {
														$.messager.alert("提示",
																"删除成功！");
														$("#tt").datagrid(
																"reload");
														hideDiv();
														query();

													} else {
														$.messager.alert("提示",
																"删除失败！");
													}
												}
											});
						}
					})
}
//批量删除
function deleteSomeConfig() {
	//获取表格选择行
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
	deleteConfigModels(parm);
}
//弹出层***************************************
//添加存储操信息操作
function showWindow() {
	var div_obj = $("#addVmDiv");
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	var popupHeight = div_obj.height();
	var popupWidth = div_obj.width();
	div_obj.css({
		"position" : "absolute"
	}).animate({
		left : windowWidth / 2 - popupWidth / 2,
		top : windowHeight / 2 - popupHeight / 2,
		opacity : "show"
	}, "slow");
}

//隐藏存储操信息操作
function hideDiv() {
	$("#mask").remove();
	clearFormFun();
	$("#addVmDiv").animate({
		left : 0,
		top : 0,
		opacity : "hide"
	}, "slow");
}

//弹出层生成合同***************************************
//添加存储操信息操作
function showWindow1() {
	var dataSourceId = new Array();
	// 获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要生成报价表的数据!', 'info');
		return;
	}
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		dataSourceId.push(n.dataSourceId);
	});
	//判断选择标配是否属于同一个数据源
//	for ( var int = 0; int < dataSourceId.length; int++) {
//		for ( var int2 = 1; int2 < dataSourceId.length; int2++) {
//			if(dataSourceId[int]!=dataSourceId[int2]){
//				alert("选择的数据源不一致,请重新选择！");
//				return;
//			}
//		}
//	}
	var div_obj = $("#addVmDiv1");
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	var popupHeight = div_obj.height();
	var popupWidth = div_obj.width();
	div_obj.css({
		"position" : "absolute"
	}).animate({
		left : windowWidth / 2 - popupWidth / 2,
		top : windowHeight / 2 - popupHeight / 2,
		opacity : "show"
	}, "slow");
}

//隐藏存储操信息操作
function hideDiv1() {
	$("#mask").remove();
	clearFormFun();
	$("#addVmDiv1").animate({
		left : 0,
		top : 0,
		opacity : "hide"
	}, "slow");
}

//跳转入配置详情制定页面
function updateModelsInfos(paramscsId,dataIds,dataNames) {
	window.location = "../../quotation/config/optionaltype/optional.jsp?tocheckIds="
		+ dataIds + "&tocheckName="
		+ dataNames + "&configsIds="
		+ paramscsId;
}

//修改模板主表信息
function chekcForm() {
	if (null == $("#pezhiname").val() || "" == $("#pezhiname").val()) {
		$.messager.alert('提示', '配置单名称不能为空!', 'info');
		return;
	}
	$.post(ctx+ '/configModel/configModel/configModel!saveConfiguration.action',
			{
				id : $("#modelId").val(),
				dataSouceId : $("#dateNames").val(),
				configurationName : $("#pezhiname").val()
			}, function(data) {
				if (null != data && "" != data) {
					$.messager.alert('提示', "操作成功", 'info');
					   hideDiv();
					   query();
				} else {
					$.messager.alert('提示', "系统异常，请稍后操作", 'info');
				}
			});
}

//跳转入下载报价表页面
function toRapaport() {
	var rows = $('#tt').datagrid('getSelections');
	var parm = "";
   	// 循环给提交删除参数赋值(音乐风格编码)
   	$.each(rows, function(i, n) {
   		parm += n.id;
   		if(i!=rows.length-1){
   			parm+= ",";
   		}
   	});
	// 调用 form 插件的 'submit' 方法来提交 form
	$('#addVmform1').form('submit', {
		url:ctx+'/quotations/quotations/quotations!saveAuotions.action?id='+parm,
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				$.messager.alert('提示', "数据生成成功，即将为您跳转到报价表下载页面。");
				window.location = ctx+"/quotation/rapaport/rapaport.jsp";
			} else {
				alert("系统异常，请稍后操作");
			}
		}
	});
}

//查询并填充配置模板
function updateModels(modelsId) {
	$.post(ctx+ '/configModel/configModel/configModel!queryModelsById.action', {
		id : modelsId
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			$("#modelId").val(datas.configmodel.id);
			$("#dateNames").val(datas.configmodel.dataSourceId);
			$("#pezhiname").val(datas.configmodel.name);
			showWindow();
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}