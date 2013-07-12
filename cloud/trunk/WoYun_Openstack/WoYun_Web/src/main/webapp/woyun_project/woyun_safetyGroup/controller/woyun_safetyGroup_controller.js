//添加存储操信息操作
function showWindow() {
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
}
//隐藏存储操信息操作
function hideDiv() {   
    $("#mask").remove();   
    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}  

var parentGroupId="";

//显示规则操作界面
function showpoliceWindow(id) {
	createSaftyTable(id); //加载规则列表
	getSecurityGroup(id); //加载安全组下拉框
	parentGroupId = id;
	var div_obj = $("#addpoliceWin");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
}

//隐藏规则操作界面
function hidepoliceDiv() {   
    $("#mask").remove();   
    $("#addpoliceWin").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}  

//删除策略
function deletePolice(paramsId) {
	alert("删除成功");
}

//创建安全组规则
function createRuleSubmit(){
	$('#addpoliceWinform').form('submit', {
		url:ctx+'/server/server/securityGroup!createSecurityGroupRule.action?securityGroupRuleForCreate.parentGroupId='+parentGroupId,
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					alert('添加成功！');
					$('#from_port').val('');
					$('#to_port').val('');
					$("#policeTable").datagrid("reload");
				}else {
					alert("添加失败!");
					$('#from_port').val('');
					$('#to_port').val('');
				}
			}
		}
	}); 
}

//判断ＣＩＤＲ是否显示
function isHiddenCidr(obj){
	if(0!=obj.value){
		$("#cidr").hide();
		$("#isCidrs").val('0.0.0.0/0');
	}else{
		$("#cidr").show();
		$("#isCidrs").val('');
	}
}

//删除规则
//该功能失败
function deleteRule(ruleid){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	    if (r){  
			//设置为同步
			$.ajaxSettings.async = false;
			$.getJSON(
				ctx+'/server/server/securityGroup!deleteSecurityGroupRule.action',
				{id:ruleid},
				function(data){
					if(null!=data && ""!=data){
						var msg = data.msg;
						if(msg="ok"){
							$("#policeTable").datagrid("reload");
							$.messager.alert("提示","删除成功！");
						}else{
							$.messager.alert("提示","删除失败！");
						}
					}
				}
			);
	    }
	});
}

//创建安全组
function createSecurityGroup(){
	$('#addVmform').form('submit', {
		url:ctx+'/server/server/securityGroup!createSecurityGroup.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					alert('添加成功！');
					$("#addVmform").form("clear");
					$("#safetyGroupInfo").datagrid("reload");
					$("#addVmDiv").css('display','none');
				}else {
					$('#"addVmform"').form("clear");
					alert("添加失败!");
				}
			}
		}
	}); 
}

//删除安全组
function deleteGroup(groupId){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	    if (r){  
			//设置为同步
			$.ajaxSettings.async = false;
			$.getJSON(
				ctx+'/server/server/securityGroup!deleteSecurityGroup.action',
				{id:groupId},
				function(data){
					if(null!=data && ""!=data){
						var msg = data.msg;
						if(msg="ok"){
							$("#safetyGroupInfo").datagrid("reload");
							$.messager.alert("提示","删除成功！");
						}else{
							$.messager.alert("提示","删除失败！");
						}
					}
				}
			);
	    }
	});
}