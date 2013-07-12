
//弹出层***************************************
function showWindow() {
  $('#addVmDiv').window('open');
}
//隐藏存储操信息操作
function hideDiv() {   
	$("projectName").val('');
	$("description").val('');
	$("#addVmDiv").window('close');
} 

//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}
function upload() {
	alert("操作成功!");
	hideDiv();
}

//新建项目
function addProject() 
{
	var projectId = $("#projectId").val();
	if(null!=projectId && ""!=projectId && -1!=projectId){  //修改
		$('#addVmform').form('submit', {
			url:ctx+'/projectManage1/projectManage1/project1!updateProject.action',
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
						hideDiv();
						query();
					}else {
						$.messager.alert("提示","添加失败!");
					}
				}
			}
		}); 
	}else{ //添加
		$('#addVmform').form('submit', {
			url:ctx+'/projectManage1/projectManage1/project1!addProject.action',
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

//查询并填充客户信息
function updateProject(id) {
	$.post(ctx+ '/projectManage1/projectManage1/project1!queryProjectById.action', {
		"id" : id
	}, function(data) {
		if (null != data && "" != data) {
			var datas = eval("("+data+")");
			$("#projectId").val(datas.project.id);
			$("#customer").val(datas.project.customerName);
			$("#projectName").val(datas.project.projectName);
			$("#description").val(datas.project.description);
			showWindow();
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}
