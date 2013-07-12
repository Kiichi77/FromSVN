//根据传入参数查询目标服务器
function queryServiceByNameOrIp() {
	Store.currentPage = 1;
	Store.pageSize = thePublicPageSize;
	Store.load({			
		params:{
			start:0,
			limit:thePublicPageSize,
			'model.serverName' : Ext.getCmp("serviceNames").getValue(),
			'model.serverIpIn' : Ext.getCmp("serviceIPs").getValue()
		}
	});
	Store.on('beforeload', function (store, options) {
		Ext.apply(store.proxy.extraParams, {
			'model.serverName' : Ext.getCmp("serviceNames").getValue(),
			'model.serverIpIn' : Ext.getCmp("serviceIPs").getValue()
		});
	});
}

//根据节点ID，找到并选择该节点以及其父节点
function quertNodeByIdAndChecked(paramsId) {
	var selNodes = Ext.getCmp("servertree").getChecked();
	Ext.each(selNodes, function(nodes){
		nodes.set("checked", false);
	});
	var node = Ext.getCmp("servertree").getStore().getNodeById(paramsId);
	node.set("checked", true);
	//获得父节点
	pNode = node.parentNode;
	//当checked == true通过循环将所有父节点选中
	for (; pNode != null; pNode = pNode.parentNode) {
		pNode.set("checked", true);
	}
}

//根据serviceid查询并填充服务器信息
function carryServiceIpToQuery(paramsId) {
	if(null != paramsId) {
		Ext.getCmp("nowServicePlaceIds").setValue(paramsId);
		wietinLoad.show();
		Ext.Ajax.request({
			url: ctx+'/serviceinfo/serviceinfo/serviceinfo!findById.action',
			params: {"model.id": paramsId},
			method: 'post',
			success: function (response, options) {
				wietinLoad.hide();
				var obj = Ext.JSON.decode(response.responseText);
				Ext.getCmp("palceNodeId").setValue(obj.model.groupId);
				Ext.getCmp("operateSystem").setValue(obj.model.operateSystem);
				Ext.getCmp("serviceName").setValue(obj.model.serverName);
				Ext.getCmp("idRacip").setValue(obj.model.idracIp);
				Ext.getCmp("idRacip").disable();
				Ext.getCmp("idRacipName").setValue(obj.model.idracUser);
				Ext.getCmp("idRacipPwd").setValue(obj.model.idracPassword);
				Ext.getCmp("inServiceIp").setValue(obj.model.serverIpIn);
				Ext.getCmp("outServiceIp").setValue(obj.model.serverIpOut);
				Ext.getCmp("remark").setValue(obj.model.serverDescription);
				Ext.getCmp("queikCode").setValue(obj.model.quickServerCode);
				Ext.getCmp("nowWhere").setValue(obj.model.searchNote1);
				//quertNodeByIdAndChecked(obj.model.);
				Ext.getCmp("serviceInfoSetWin").show();
			},
			failure: function (response, options) {
				wietinLoad.hide();
				Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
			}
		});
	} else {
		wietinLoad.hide();
		Ext.getCmp("serviceInfoSetWin").show();
	}
}
//根据IP删除目标服务器
function deleteServiceByIp(params) {
	params = Ext.getCmp("firstGrid");
	var sel = params.getSelectionModel().getSelection();
	var ids = new Array();
	for(var i = 0; i < sel.length;i ++) {
		ids.push(sel[i].get("id"));
	}
	Ext.Msg.confirm("警告", "请确定主机没绑定策略,否则无法删除？", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
			      url: ctx+'/serviceinfo/serviceinfo/serviceinfo!delete.action',
			      params: { "ids": ids},
			      method: 'post',
			      success: function (response, options) {
					  var obj = Ext.JSON.decode(response.responseText); 
			    	  if(obj.msg=="ok"){
			    		  Ext.MessageBox.alert('提示', '操作成功');
			    		  queryServiceByNameOrIp();
			    	  }else if(obj.msg=="havePolicy"){
			    		  Ext.Msg.alert("提示","有服务器关联了策略，请先删除策略。");
			    	  }else{
			    		  Ext.Msg.alert("提示","删除失败，请联系管理员。");
			    	  }
			      },
			      failure: function (response, options) {
			          Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
			      }
			  });
		}
	}		
	)
}

