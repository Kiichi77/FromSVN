$(function(){
	
	var flavorId = "";
	/**
	 * 查询服务器详细信息
	 */
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+"/server/server/server!showServerDetails.action",
		{serverId:serverId},
		function(data){
			if(null!=data && ""!=data){
				var obj = data.model;
				$("#name").text(obj.name);
				$("#id").text(obj.serverId);
				$("#state").text(obj.state);
				$("#address").text(obj.addresses[0].addr);
				$("#imagename").text(obj.imageName);
				$("#keyName").text(obj.keyName);
				flavorId = obj.flavor.id; 
			}
		}
	);
	
	/**
	 *查询模板信息 
	 */
	if(null!=flavorId && ""!=flavorId){
		//设置为同步
		$.ajaxSettings.async = false;
		$.getJSON(
			ctx+"/server/server/flavors!showFlavorsDetail.action",
			{flavorId:flavorId},
			function(data){
				if(null!=data && ""!=data){
					var obj = data.rows;
					$("#memory").text(obj.ram+"M");
					$("#cpu").text(obj.vcpus+"个处理器");
					$("#disk").text(obj.disk+"G");
				}
			}
		);
	}
	
	/**
	 * 查询安全组信息
	 */
	//设置为同步
	$.ajaxSettings.async = false;
	$.getJSON(
		ctx+'/server/server/securityGroup!showSecurityGroupByServerId.action',
		{serverId:serverId},
		function(data){
			if(null!=data && ""!=data){
				var obj = data.rows;
				var securityGroup = $("#securityGroup");  
				$.each(obj,function(i,sg){
					var dt = $("<dt>").text(sg.name);
					$.each(sg.ruleList,function(j,rule){
						var dd = $("<dd>").text("允许"+rule.formPort+":"+rule.toPort+"从网段"+rule.iprangvo.cidr+"的连线.");
						dt.append(dd);
					})
					securityGroup.append(dt);  
				});
			}
		}
	);
});