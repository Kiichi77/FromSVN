//打开分配浮动ip给实例的窗口
function allocationIp(floatIP) {
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");   
    $('#floatIP').val(floatIP);
}
//隐藏虚拟机操信息操作
function hideDiv() {   
    $("#mask").remove();   
    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 

//配给ip
function addServerFloat(){
	// 调用 form 插件的 'submit' 方法来提交 form
	$('#addVmform').form('submit', {
		url:ctx+'/server/server/floatIp!setFloatIpServer.action',
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

//释放ip(删除实例与ip的关联)
function releaseIp(ip,serverId){
	$.messager.confirm('提示','确定要释放选中的IP与实例的关系吗？',function(r){  
	    if (r){ 
			//设置为同步
			$.ajaxSettings.async = false;
			$.getJSON(
				ctx+'/server/server/floatIp!disassociateFloatingIp.action',
				{
					serverId:serverId,
					ip:ip
				},
				function(data){
					if(null!=data && ""!=data){
						var obj = data.msg;
						if("ok"==obj){
							$.messager.alert("提示","释放成功！");
							$("#virtualMachineInfo").datagrid("reload");
						}else{
							$.messager.alert("提示","释放失败！");	
						}
					}
				}
			);
	    }
	});
}

//释放ip(删除浮动ip)
function deallocateFloatingIp(id){
	$.messager.confirm('提示','确定要释放选中的IP吗？',function(r){  
	    if (r){ 
			//设置为同步
			$.ajaxSettings.async = false;
			$.getJSON(
				ctx+'/server/server/floatIp!deallocateFloatingIp.action',
				{
					id:id
				},
				function(data){
					if(null!=data && ""!=data){
						var obj = data.msg;
						if("ok"==obj){
							$.messager.alert("提示","释放成功！");
							$("#virtualMachineInfo").datagrid("reload");
						}else{
							$.messager.alert("提示","释放失败！");	
						}
					}
				}
			);
	    }
	});
}

//打开申请ip窗口
function applyForIp(){
	var div_obj = $("#Allocation");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/4, opacity: "show" }, "slow");   
}

//分配ip,申请ip
function createIp(){
	var pools = $("#Allocation_pools").val();
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/floatIp!allocateFloatingIp.action',
		{
			pools:pools
		},
		function(data){
			if(null!=data && ""!=data){
				var obj = data.msg;
				if("ok"==obj){
					$.messager.alert("提示","申请成功！");
					$('#Allocation').css('display','none');
					$("#virtualMachineInfo").datagrid("reload");
				}else{
					$.messager.alert("提示","申请失败！");	
				}
			}
		}
	);
}

//释放浮动ip（批量操作）
function liberateIp(){
	 //获取表格选择行
	var rows = $('#virtualMachineInfo').datagrid('getSelections');
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		json += "{'id':'"+n.id+"','ip':'"+n.ip+"','serverId':'"+n.instanceId+"'}";
		if(i!=rows.length-1){
			json += ',';
		}
	});
	json += "]";
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/floatIp!batchLiberateIp.action',
		{
			json:json,
		},
		function(data){
			if(null!=data && ""!=data){
				var obj = data.msg;
				if("ok"==obj){
					$.messager.alert("提示","批量释放成功！");
					$("#virtualMachineInfo").datagrid("reload");
				}else{
					$.messager.alert("提示","批量释放失败！");	
				}
			}
		}
	);
}

//取消
function cancel(){
	$('#addVmDiv').css('display','none');
	$('#Allocation').css('display','none');
}