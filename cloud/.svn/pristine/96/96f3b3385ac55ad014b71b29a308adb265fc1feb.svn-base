//文件上传，保存
function uploadFileFun(){
	var docType = "";
		var fileName = $('input[name="proved"]').val();
		if(fileName=="" || null==fileName){
			$.messager.alert('提示', "请选择需要上传的文件。", 'info');
			return;
		}
		var index = fileName.lastIndexOf(".");
		fileName = fileName.substring(index+1,fileName.length);
		if('docx'!=fileName && 'jpg'!=fileName){
			$.messager.alert('提示', "您选择的文件类型不对，只能上传docx和jpg文件。", 'info');
			return;
		}
	$('#addVmform').form('submit', {
   		url:ctx+'/contractTemplates/contractTemplates/arrivalProved!uploadProved.action',
   		onSubmit: function(){
   			// 做某些检查
   			// 返回 false 来阻止提交
   			return $(this).form('validate'); 
   		},
   		success:function(data){
   			if(data!=null && data!=""){
   				var data = eval('(' + data + ')');
   				if("ok" == data.msg){
   					$.messager.alert('提示', "操作成功", 'info');
   					hideDiv();
   					$("#tt").datagrid("reload");
   					$('#addVmform').form("clear");
   					window.location = ctx+"/view/main.jsp";
   				} else {
   					$.messager.alert('提示', "操作失败，请从新尝试", 'info');
   					hideDiv();
   					$('#addVmform').form("clear");
   				}
   			} else {
   				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
   			}
   		}
   	});
}
