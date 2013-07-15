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
	$.messager.confirm('提示','确定要删除选中的数据吗？',
		function(r) {
			if (r) {
				//设置为同步
				$.ajaxSettings.async = false;
				$.post(ctx+'/configModel/configModel/configModel!deleteConfigModels.action',
				{id : id},				
				function(data) {
					var data = eval('(' + data + ')');
					if (null != data && "" != data) {
						if (data.msg == "ok") {
							$.messager.alert("提示","删除成功！");
							$("#tt").datagrid("reload");
							hideDiv();
							query();
						} else {
							$.messager.alert("提示","删除失败！");
						}
					}
				});				
			}
		});			
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
	$('#addVmDiv').window('open');
}

//隐藏存储操信息操作
function hideDiv() {
	clearFormFun();
	$('#addVmDiv').window('close');
}

//弹出层生成合同***************************************
//添加存储操信息操作
function showWindow1() {
	var dataSourceId = new Array();
	// 获取表格选择行
	var rows = $('#tt').datagrid('getSelections');
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		dataSourceId.push(n.dataSourceId);
	});
	//判断选择标配是否属于同一个数据源
	$('#addVmDiv1').window('open');
}

//隐藏存储操信息操作
function hideDiv1() {
	clearFormFun();
	$('#addVmDiv1').window('close');
}

//跳转入配置详情制定页面
function updateModelsInfos(paramscsId,dataIds,dataNames) {
	window.location = ctx+"/quotation/config/optionaltype/optional.jsp?tocheckIds="
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
	var datasou = $("#dateNames").val();
	var confiname = $("#dateNames option:selected").html();
	$.post(ctx+ '/configModel/configModel/configModel!saveConfiguration.action',{
				id : $("#modelId").val(),
				dataSouceId : $("#dateNames").val(),
				configurationName : $("#pezhiname").val()
			}, function(data) {
				if (null != data && "" != data) {
					var datas = eval("("+data+")");
					   hideDiv();
					   updateModelsInfos(datas.msg,datasou,confiname);
//					   query();
				} else {
					$.messager.alert('提示', "系统异常，请稍后操作", 'info');
				}
			});
}

//保存加个总表内容信息
function saveOrUpdataInfo() {
	var rows = $('#t11').datagrid('getRows');
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		var totalzs = ""+n.totalPrice;
		var totz = totalzs.split(".");
		json += "{'quantity':'"+n.quantity+"','projectName':'"+n.projectName+"','configModelId':'"+n.configModelId+"','quotationName':'"+n.quotationName+"','totalPrice':'"+totz[0]+"'}";//
		if(i!=rows.length-1){
			json += ',';
		}
	});
	json += "]";
	return json;
}

//跳转入下载报价表页面
function toRapaport() {
	var rows = $('#tt').datagrid('getSelections');
	var caudit=$("#caudit").val();
	var projectId=$("#projectName").val();
	var parm = "";
   	// 循环给提交删除参数赋值(音乐风格编码)
   	$.each(rows, function(i, n) {
   		parm += n.id;
   		if(i!=rows.length-1){
   			parm+= ",";
   		}
   	});
   	$('#t11').datagrid('endEdit',lastIndex1);
	var bjzb = saveOrUpdataInfo();		//获得报价总表的json数组
	$("#alltable").val(bjzb);
	// 调用 form 插件的 'submit' 方法来提交 form
	$('#addVmform1').form('submit', {
		url:ctx+'/quotations/quotations/quotations!saveAuotions.action', //id='+parm+'&projectId='+projectId+'&baoJiaTable='+bjzb
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
//				$.messager.alert('提示', "数据生成成功，即将为您跳转到报价表下载页面。");
				window.location = ctx+"/rapaport/rapaport.jsp?caudit="+caudit+'&projectId='+projectId;
			} else {
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		}
	});
}

//根据数据源类型查询数据源
function findedatas(paramsname) {
	var sourceType=$("#sourceType").val();
	var sel = $("#dateNames");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/dataSource/dataSource/dataSource!queryAllDataSourceByType.action?typeId='+sourceType,
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
				sel.append(opt);  
			}
			var sel1=document.getElementById("dateNames").options;
	        for(var i=0;i<sel1.length;i++){
	            if(sel1[i].text==paramsname){
	                sel1[i].selected=true;
	            }
	        }
			showWindow();
		}
	});	
}

//查询并填充配置模板
function updateModels(modelsId,parasmtype) {
	$('#sourceType').attr("disabled",true);
	$('#dateNames').attr("disabled",true);
	$.post(ctx+ '/configModel/configModel/configModel!queryModelsById.action', {
		id : modelsId
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			$("#modelId").val(datas.configmodel.id);
			var sel=document.getElementById("sourceType").options;
	        for(var i=0;i<sel.length;i++){
	            if(sel[i].text==parasmtype){
	                sel[i].selected=true;
	            }
	        }
	        $("#pezhiname").val(datas.configmodel.name);
	        findedatas(datas.configmodel.dataSourceName);
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}