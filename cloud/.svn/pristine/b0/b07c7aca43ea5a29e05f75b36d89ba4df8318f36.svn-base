//添加虚拟机操信息操作
function showWindow() {
	createSaftyTable();
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");   
}
//隐藏虚拟机操信息操作
function hideDiv() {   
    $("#mask").remove();   
    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}  
//修改实例信息的 方法
function updateInstanceInfo(paramsID) {
	createSaftyTable();
	$('#addVmform').form('load',{
		name:'FI-SW-01'
	});
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");
}

//修改实例设备
function updateInstanceshebei(paramsID,serverId) {
	window.location = "woyun_virtualMachineInfo.jsp?param="+paramsID+"&serverId="+serverId;
}

var interval;
var isClose = false;

//实例操作:取消暂停实例 1，终止运行实例2，编辑运行实例3，VNC界面4，查看日志5，暂停运行实例6，启动运行实例7，重启运行实例8
function changeForm(obj,serverId){
	var val = obj.value;
	var url="";
	if(val==1){
		url = ctx+"/server/server/server!unPausesServer.action";
	}else if(val==2){
		url = ctx+"/server/server/server!stopServer.action";
	}else if(val==3){
		url = ctx+"/server/server/server!showServerDetails.action";
	}else if(val==4){
		updateInstanceshebei(1,serverId);
	}else if(val==5){
		updateInstanceshebei(2,serverId);
	}else if(val==6){
		url = ctx+"/server/server/server!pausesServer.action";
	}else if(val==7){
		url = ctx+"/server/server/server!startServer.action";
	}else if(val==8){
		url = ctx+"/server/server/server!reBootServer.action";
	}
	//设置为同步
	$.ajaxSettings.async = false;
	//请求后台操作
	$.getJSON(
		url,
		{serverId:serverId},
		function(data){
			if(val!=3){
				$("#virtualMachineInfo").datagrid("reload");
				alert("操作成功！");
			}else{
				//设置文本框的值
				if(null!=data && ""!=data){
					$("#todovmNames").val(data.model.name);
					$("#images").val(data.model.imageRef);
					$("#flavors").val(data.model.flavorRef);
					$("#instanceNum").val(data.model.max);
				}
			}
		}
	);
	if(val==3){
		showWindow();
	}
}

//添加实例
function createServer(){
	 //获取表格选择行
	var rows = $('#saftygroup').datagrid('getSelections');
	var parm=new Array();
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		parm.push(n.name);
	});
	// 调用 form 插件的 'submit' 方法来提交 form
	$('#addVmform').form('submit', {
		url:ctx+'/server/server/server!createServer.action?ids='+parm,
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
					$('#addVmDiv').css('display','none');
					$('#addVmform').form("clear");
					$("#virtualMachineInfo").datagrid("reload");
				}else {
					alert("添加失败!");
					$('#addVmform').form("clear");
				}
			}
		}
	}); 
}

//删除实例
function deleteServer(){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	    if (r){  
	    	var rows = $('#virtualMachineInfo').datagrid('getSelections');
	    	var serverId='';
	    	//循环给提交删除参数赋值(音乐风格编码)
	    	$.each(rows, function (i, n) {
	    		serverId += n.serverId;
	    		if(i!=rows.length-1){
	    			serverId+=",";
	    		}
	    	});
	    	//设置为同步
	    	$.ajaxSettings.async = false;
	    	$.getJSON(
	    		ctx+'/server/server/server!deleteServer.action',
	    		{serverId:serverId},
	    		function(data){
	    			if(null!=data && ""!=data){
	    				if(data.msg=="ok"){
	    					$.messager.alert("提示","删除成功！");
	    					$("#virtualMachineInfo").datagrid("reload");
	    				}else{
	    					$.messager.alert("提示","删除失败！");
	    				}
	    			}
	    		}
	    	);
	    }  
	});
}

//取消
function cancel(){
	$('#addVmDiv').css('display','none');
	$('#addVmform').form("clear");
}
