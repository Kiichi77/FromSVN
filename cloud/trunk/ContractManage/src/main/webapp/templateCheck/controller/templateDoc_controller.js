//审核弹出框
function showWindow() {
  $('#addVmDiv').window('open');
  $('#addVmform').form('clear');
  $("#templateId").val(contractTemplatesId);
  $('#status').val(status);
}

//关闭审核弹出框
function hideDataInfoDiv(){
	 $('#addVmDiv').window('close');
}


//审核提交功能
function chekcForm(){
	if($("#pass").attr("checked")=="checked"){
	}else if($("#unpass").attr("checked")=="checked"){
	}else{
		$.messager.alert("提示","您还没有选择审核结果!");
		return;
	}
	$('#addVmform').form('submit', {
		url:ctx+'/contractTemplates/contractTemplates/contractTemplates!checkContractTemplate.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","审核成功!");
					hideDataInfoDiv();
					window.location.href = ctx+"/view/main.jsp";
				}else {
					$.messager.alert("提示","审核失败!");
				}
			}
		}
	});
}

function open1(templatesDocId){
	variable(templatesDocId);
	$('#dd').window('open');
}
function variable(templatesDocId){
	$('#ta').datagrid({
		url: ctx+'/variable/variable/templatesDocVariable!listContractTemplatesDocVariable.action?templatesDocId='+templatesDocId,
		width: '95%',
		height: 'auto',
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  	
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'variableName',title:'变量名',width:60,align:'center',editor:'text'},
					{field:'description',title:'描述',width:60,align:'center',editor:'text'},
					{field:'templatesDocId',title:'模版文件Id',width:60,align:'center',hidden:true}
					]],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},
	});
}

