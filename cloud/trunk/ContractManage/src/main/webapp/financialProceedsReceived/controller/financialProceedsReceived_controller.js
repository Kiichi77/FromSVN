//弹出层***************************************
//添加存储操信息操作
function showWindow(id,status,contractId) {
  $("#payDataDataId").val(id);
  $("#payType").val(status);
  $("#contractId").val(contractId);
  $("#addVmDiv").window('open'); 
}

//隐藏存储操信息操作
function hideDiv() {   
	 $("#addVmDiv").window('close'); 
} 

//重新加载列表
function query() {
				$('#tt').datagrid('reload');
			}

function upload() {
	$('#addVmform').form('submit', {
		url:ctx+'/contractTemplates/contractTemplates/contractPayment!confirMoney.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","操作成功!");
//					query();
					window.location.href="../view/main.jsp";
					}else {
					$.messager.alert("提示","操作失败!");
				}
				hideDiv();
			}
		}
	}); 	
}