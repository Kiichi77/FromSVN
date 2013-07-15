//重新加载列表数据
function query() {
	var kwords = $("#kwords").val().trim();//文件名字
	var filetype = $("#filesType").val();//文件类型"contractTemplatesDocVo.contractTemplatesId":contractTemplatesId,
	$('#tt').datagrid('reload',{"contractTemplatesDocVo.name":kwords,"contractTemplatesDocVo.typeName":filetype});
}
//根据ID删除合同文件
			   function deleteContractTemplatesDoc(id){
					$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
						if (r){  
					    	//设置为同步
					    	$.ajaxSettings.async = false;
					    	$.post(
					    		ctx+'/contractTemplates/contractTemplates/contractTemplatesDoc!deleteContractTemplatesDoc.action',
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
					});
			   }
			   
				 //批量删除
			   function deleteSomeContractTemplatesDoc() {
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
				   	deleteContractTemplatesDoc(parm);
			   }

			   function query1() {
			   		$('#ta').datagrid('reload');
			   }
			   //根据ID删除变量
			   			   function deleteContractTemplatesDocVariable(id){
			   					$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
			   						if (r){  
			   					    	//设置为同步
			   					    	$.ajaxSettings.async = false;
			   					    	$.post(
			   					    		ctx+'/variable/variable/templatesDocVariable!deleteContractTemplatesDocVariable.action',
			   					    		{id:id},
			   					    		function(data){
			   					    			var data = eval('(' + data + ')');
			   					    			if(null!=data && ""!=data){
			   					    				if(data.msg=="ok"){
			   					    					$.messager.alert("提示","删除成功！");
			   					    					$("#ta").datagrid("reload");
			   					    					query1();
			   					    				}else{
			   					    					$.messager.alert("提示","删除失败！");
			   					    				}
			   					    			}
			   					    		}
			   					    	);
			   						}
			   					});
			   			   }
			   			   
			   				 //批量删除
			   			   function deleteSomeVariable() {
			   			   	//获取表格选择行
			   			   	var rows = $('#ta').datagrid('getSelections');
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
			   			  deleteContractTemplatesDocVariable(parm);
			   			   }
			   			   
var optType = 1;
//弹出添加的弹出框
function open2(){
	var div_obj = $("#add");
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
}

//关闭弹出框
function hideDiv(){
	 $("#add").animate({left: 0, top: 0, opacity: "hide" }, "slow"); 
}

//文件上传，保存
function uploadFileFun(){
	var docType = "";
	var inputChecked = $('input[name="typeName"]:checked');
	if(inputChecked.length<=0){
		$.messager.alert('提示', "请选择模板类型!", 'info');
		return;
	}
	inputChecked.each(function(i){
	     var type=$(this).val();
	     docType+=type;
	     if(i!=inputChecked.length-1){
	    	 docType += ","
	     }
	 });
	var url = ctx+"/contractTemplates/contractTemplates/contractTemplatesDoc" ;
	if(optType==1){ //添加
		var fileName = $('input[name="templatesDoc"]').val();
		if(fileName=="" || null==fileName){
			$.messager.alert('提示', "请选择需要上传的文件!", 'info');
			return;
		}
		var index = fileName.lastIndexOf(".");
		fileName = fileName.substring(index+1,fileName.length);
		if('docx'!=fileName && 'jpg'!=fileName){
			alert("您选择的文件类型不对，只能上传docx和jpg文件!");
			return;
		}
		url+="!saveTemplatesDoc.action?contractTemplatesDocVo.typeName="+docType+"&contractTemplatesDocVo.contractTemplatesId="+contractTemplatesId;
	}else if(optType==2){ //修改
		url+="!updateTemplatesDoc.action?contractTemplatesDocVo.typeName="+docType+"&contractTemplatesDocVo.id="+templatesId;
	}
	$('#addVmform').form('submit', {
   		url:url,
   		onSubmit: function(){
   			// 做某些检查
   			// 返回 false 来阻止提交
   			return $(this).form('validate'); 
   		},
   		success:function(data){
   			if(data!=null && data!=""){
   				if("error" == data.msg){
   					$.messager.alert('提示', "操作失败，请从新尝试", 'info');
   				} else {
   					$.messager.alert('提示', "操作成功", 'info');
   					hideDiv();
   					$("#tt").datagrid("reload");
   					$('#addVmform').form("clear");
   				}
   			} else {
   				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
   			}
   		}
   	});
}


//根据主键查询
function updateFindbyId(id){
	//设置为同步
	$.ajaxSettings.async = false;
	$.post(
		ctx+'/contractTemplates/contractTemplates/contractTemplatesDoc!findById.action',
		{id:id},
		function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
			 	$.each(data.rows, function (i, n) {
			 		templatesId=n.id;
			   		$("#tempName").val(n.docname);
			   		$("#isVariable").children("option").each(function(){
			              var temp_value = $(this).val();
			              if(temp_value == n.bVariable){
			                  $(this).attr("selected",true);
			              }
			         });
			   		var typeIds = n.typeName;
			   		var ss;
			   		if(typeIds!=null){
			   			ss = typeIds.split(",");
			   			$("[name='typeName']").each(function(){
			   				for ( var int = 0; int < ss.length; int++) {
			   					if($(this).val()==ss[int]){
			   						$(this).attr("checked",'true');
			   					}
							}
			   			})
			   		}
			   		$("#filediv").css("display","none");
			   		optType=2;//表识为修改
			   		open2(2);
			   	}); 
			}
		}
	);
}

//获取文档类型
function getDocType(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.post(
		ctx+'/contractTemplates/contractTemplates/contractDocType!findAll.action',
		function(data){
			//下拉选择框数据加载
			var sel = $("#filesType");  
			sel.empty();//清除select中的 option
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				var add = $("#typeDIV");
				var opt1 = $("<option>").text("全部").val("0");  
				sel.append(opt1);
				$.each(data.rows,function(i,sg){ 
					var opt = $("<option>").text(sg.name).val(sg.id);  
					sel.append(opt);  
				});
			}
		}
	);
}

//保存变量信息,修改变量信息
function saveAndUpdateVariable() {
	var rows = $('#ta').datagrid('getRows');
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		json += "{'id':'"+n.id+"','description':'"+n.description+"','variableName':'"+n.variableName+"','templatesDocId':'"+n.templatesDocId+"'}";
		if(i!=rows.length-1){
			json += ',';
		}
	});
	json += "]";
	$.post(ctx+ '/variable/variable/templatesDocVariable!saveAndUpdateVariable.action', {
		id : json
	}, function(data) {
		data = eval('(' + data + ')');
		if (null != data && "" != data ) {
			if("ok" == data.msg){
				$.messager.alert('提示', "操作成功", 'info');
				$("#ta").datagrid("reload");
			}else if( "no" == data.msg){
				$.messager.alert('提示', "变量名与上传文件变量名不一致，请仔细核对。", 'info');
			}else{
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		} else{
			$.messager.alert('提示', "数据异常。", 'info');
		}
	});
}

//保存模板文件和模板之间的关系
function saveTempleAndFile() {
	//获取表格选择行
   	var rows = $('#tt').datagrid('getSelections');
   	//判断是否选择行
   	if (!rows || rows.length == 0) { 
   	$.messager.alert('提示', '请选择合同模板所用的文件!', 'info'); 
   		return; 
   	}
   	var parm = ""; 
   	// 循环给提交删除参数赋值(音乐风格编码)
   	$.each(rows, function (i, n) {
   		parm +=  n.id+","; 
   	}); 
   	parm = parm.substring(0,parm.length-1);
	$.post(ctx+ '/contractTemplates/contractTemplates/contractTemplatesDoc!saveRelationShip.action',{
		contractTemplatesId : contractTemplatesId,
		contractTemplatesDocIds : parm
	}, function(data) {
		data = eval('(' + data + ')');
		if (null != data && "" != data ) {
			if("ok" == data.msg){
				$.messager.alert('提示', "操作成功", 'info');
				$("#tt").datagrid("reload");
			}else{
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		} else{
			$.messager.alert('提示', "数据异常。", 'info');
		}
	});
}