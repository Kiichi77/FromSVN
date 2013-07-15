//显示项目信息操作界面
function showWindow(whichParams) {
	if("toproject" == whichParams) {
		var div_obj = $("#addVmDiv");  
	    var windowWidth = document.body.clientWidth;       
	    var windowHeight = document.body.clientHeight;  
	    var popupHeight = div_obj.height();       
	    var popupWidth = div_obj.width(); 
	    div_obj.css({"position": "absolute"})
	    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow"); 
	} else if("toQuota" == whichParams) {
		var div_obj = $("#updateQuota");  
	    var windowWidth = document.body.clientWidth;       
	    var windowHeight = document.body.clientHeight;  
	    var popupHeight = div_obj.height();       
	    var popupWidth = div_obj.width(); 
	    div_obj.css({"position": "absolute"})
	    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");
	} else {
		
	}
}
//隐藏项目信息操作界面
function hideDiv(whichParams) {
	if("toproject" == whichParams) {
		  $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");
		  clearProjectInfoForm("toproject")
	} else if("toQuota" == whichParams) {
		  $("#updateQuota").animate({left: 0, top: 0, opacity: "hide" }, "slow");
		  clearProjectInfoForm("toQuota")
	} else {
		
	}
}  

//清空项目信息表单方法
function clearProjectInfoForm(whichParams) {
	if("toproject" == whichParams) {
		$("#projectIds").val("");
		$("#projectNames").val("");
		$("#projectDescription").val("");
		$("#theAbeld").attr("checked",false);
	} else if("toQuota" == whichParams) {
		
	} else {
		
	}
}

//删除用户
function deleteProject(paramsId) {
	//提示是否删除
	$.messager.confirm('提示', '是否删除选中数据?', function (r) { 
		if (!r) { 
			return; 
		} else {
			$.post(ctx+'/user/user/user!deleteUserById.action',{usersid:paramsId},function(data){
				if(data!=null && data!=""){
					if("error" == data.msg){
						alert("删除失败，请从新尝试");
					} else {
						queryUsers();
						alert("删除成功");
					}
				} else {
					alert("系统异常，请稍后操作");
				}
			});
		}
	});
}

//批量删除策略
function deleteSomeProject() {
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
	deleteProject(parm);
}

//查询用户信息
function queryUsers() {
	$('#tt').datagrid('reload');
}

function changeForm(selects,paramsId) {
	var sel = selects.value;
	var url = "";
	if("1" == sel) {
		url = ctx+'/user/user/project!queryProjectById.action';
	} else if ("2" == sel) {
		
	}
	$.post(url,{projectId:paramsId},function(data){
		if(data!=null && data!=""){
			if("1" == sel) {
				$("#projectIds").val(data.projectVo.id);
				$("#projectNames").val(data.projectVo.name);
				$("#projectDescription").val(data.projectVo.description);
				$("#theAbeld").attr("checked",data.projectVo.enabled);
				showWindow("toproject");
			}
		} else {
			alert("系统异常，请稍后操作");
		}
	});
}

//查询用户信息
function queryProjects() {
	$('#tt').datagrid('reload');
}

//提交项目信息
function submitProjectInfo(whichParams) {
	if("toproject" == whichParams) {
		var end = "true";
		if(!$("#theAbeld").attr("checked")){ 
			end = "false";   			
		}
		$('#addVmform').form('submit', {
			url:ctx+'/user/user/project!updateProject.action?projectId='+end,
			onSubmit: function(){
				// 做输入验证检查
				// 返回 false 来阻止提交
				return $(this).form('validate'); 
			},
			success:function(data){
				if(data!=null && data!=""){
					if("error" == data.msg){
						alert("操作失败，请从新尝试");
					} else {
						queryProjects();
						alert("操作成功");
						hideDiv("toproject");
					}
				} else {
					alert("系统异常，请稍后操作");
				}
			}
		});
	}
}