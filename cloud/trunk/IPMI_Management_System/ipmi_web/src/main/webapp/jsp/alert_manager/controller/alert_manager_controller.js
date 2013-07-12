var usersIDs = null;
//根据传入参数查询目标服务器
this.queryPlaceByNameOrIp = function (filterType,isEnable,serverIpIn,accouont) {
	Store.currentPage = 1;
	Store.pageSize = thePublicPageSize;
	Store.load({			
			params:{
			start:0,
		    limit:thePublicPageSize,
		    'alertVo.filterType' : filterType,//筛选类型
			'alertVo.enable' : isEnable,//严重性
			'alertVo.tbServer.serverIpIn':serverIpIn, //内网ip
			'alertVo.tbUsers.accouont':accouont //用户名
		}
	});
	Store.on('beforeload', function (store, options) {
	    Ext.apply(store.proxy.extraParams, {
	    	'alertVo.filterType' : filterType,//筛选类型
			'alertVo.enable' : isEnable,//严重性
			'alertVo.tbServer.serverIpIn':serverIpIn, //内网ip
			'alertVo.tbUsers.accouont':accouont //用户名
	    });
	});
}

//有子节点选中，则父节点选中；若子节点都不选中，则父节点也不能被选中
function checkChangeCkeck(node,checked) {	
	if (checked == true) {
		node.checked = checked;
		//获得父节点
		pNode = node.parentNode;
		//当checked == true通过循环将所有父节点选中
		for (; pNode != null; pNode = pNode.parentNode) {
			pNode.set("checked", true);
		}
	}
	//当该节点有子节点时，将所有子节点选中删除
	if (!node.get("leaf") && !checked){
		node.cascade(function(node){
			node.set('checked', false);
		}); 
	}
}

//保存选择的内容
function checkTypeShowText(params) {
	usersIDs = params;
}

//保存警告信息
function saveOrUpdateAlert(){
	var paramId = Ext.getCmp("alertId").getValue();
	var filterType = Ext.getCmp("filter_type").getValue();  //筛选类型
	var seriousNum = Ext.getCmp("serious_trigger_limit").getValue();  //严重阀值
	var warningNum = Ext.getCmp("alert_trigger_limit").getValue();  //警告阀值
	var seriousEnable = 1;  //开启严重
	var alertEnable = 1;    //开启警告
	var accouontId = usersIDs;
	var selModel = Ext.getCmp("servertree");
	var selected = selModel.getChecked();
	var Ids = new Array();
	Ext.each(selected, function (item) {
		if(item.data.leaf==true){
			Ids.push(item.data.id.substring(0,item.data.id.length-1));
		}
	})
	var formPanel = Ext.getCmp("operAlertForm");
	if(formPanel.form.isValid()){
		if(paramId==null||paramId==0){
			if(Ids.length<=0){
				Ext.MessageBox.alert('提示','至少选择一台服务器.');
				return;
			}
			Ext.Ajax.request({
				url: ctx+'/alert/alert/alert!saveAlert.action',
				params: {'alertVo.filterType':filterType,
					'alertVo.seriousNum':seriousNum,
					'alertVo.warningNum':warningNum,
					'alertVo.warningEnable':alertEnable,
					'alertVo.seriousEnable':seriousEnable,
					'alertVo.tbUsers.id':accouontId,
					'ids':Ids},
					method: 'post',
					success: function (response, options) {
						var pp  =  Ext.JSON.decode(response.responseText);
						Ext.MessageBox.alert('提示',pp.msg);
						var addwin = Ext.getCmp('rePlayWin');addwin.hide();
						var roonodes = Ext.getCmp("servertree").getRootNode();
						eachTreeAllNode(roonodes);
						Store.load();
					},
					failure: function (response, options) {
					}
			});
		}else{
			if(Ids.length<=0){
				Ext.MessageBox.alert('提示','至少选择一台服务器.');
				return;
			}
			Ext.Ajax.request({
				url: ctx+'/alert/alert/alert!updateAlert.action',
				params: {
					'alertVo.alertId':paramId,
					'alertVo.filterType':filterType,
					'alertVo.seriousNum':seriousNum,
					'alertVo.warningNum':warningNum,
					'alertVo.warningEnable':alertEnable,
					'alertVo.seriousEnable':seriousEnable,
					'alertVo.tbUsers.id':accouontId,
					'ids':Ids},
					method: 'post',
					success: function (response, options) {
						var pp  =  Ext.JSON.decode(response.responseText);
						Ext.MessageBox.alert('提示',pp.msg);
						var addwin = Ext.getCmp('rePlayWin');addwin.hide();
						var roonodes = Ext.getCmp("servertree").getRootNode();
						eachTreeAllNode(roonodes);
						Store.load();
					},
					failure: function (response, options) {
					}
			});
		}
	}
}

//循环数的所有节点
function eachTreeAllNode(node) {
	 var childnodes = node.childNodes;
     for(var i=0;i<childnodes.length;i++){  //从节点中取出子节点依次遍历
         var rootnode = childnodes[i];
         rootnode.set("expanded", true);
         rootnode.set("checked", false);
         if(rootnode.childNodes.length>0){  //判断子节点下是否存在子节点，个人觉得判断是否leaf不太合理，因为有时候不是leaf的节点也可能没有子节点
         	eachTreeAllNode(rootnode);    //如果存在子节点  递归
         }    
     }  
}

//选择和请取消选择树节点
function checkTreeNode(pp) {
	var roonodes = Ext.getCmp("servertree").getRootNode();
	eachTreeAllNode(roonodes);
	Ext.getCmp("servertree").collapseAll();
	Ext.getCmp("servertree").expandAll();
	for(var s = 0; s < pp.alertServerEntitys.length; s++) {
		var selNodes = Ext.getCmp("servertree").getChecked();
		if(0!=selNodes.length) {
			Ext.each(selNodes, function(nodes){
				nodes.set("checked", false);
				nodes.set("expanded", true);
		    });
		} else {
			Ext.each(selNodes, function(nodes){
				nodes.set("expanded", true);
		    });
		}
		var node = Ext.getCmp("servertree").getStore().getNodeById(pp.alertServerEntitys[s].server.id+"i");
		if(null != node) {
			node.set("checked", true);
			pNode = node.parentNode;
		    //当checked == true通过循环将所有父节点选中
		    for (; pNode != null; pNode = pNode.parentNode) {
			     pNode.set("expanded", true);
			     pNode.set("checked", true);
			}
		}
	}
}

//修改
function update(alertId,filterType,warningNum,seriousNum,userNames,userId) {
	//根据警告ID查询该策略所关联的服务器
	Ext.Ajax.request({
		url: ctx+'/alert/alert/alert!queryServersByAlertId.action',
		params: { 'alertVo.alertId': alertId},
			method: 'post',
			success: function (response, options) {
				var pp =  Ext.JSON.decode(response.responseText); 
				checkTreeNode(pp);
				Ext.getCmp("alertId").setValue(alertId);
				Ext.getCmp("serious_trigger_limit").setValue(seriousNum);
				Ext.getCmp("filter_type").setValue(filterType);
				if("1" == filterType) {
					Ext.getCmp("alert_trigger_limit").setFieldLabel("触发告警阀值(W)");
				} else if ("2" == filterType) {
					Ext.getCmp("alert_trigger_limit").setFieldLabel("触发告警阀值(℃)");
				}
				Ext.getCmp("alert_trigger_limit").setValue(warningNum);
				Ext.getCmp("accouont").setRawValue(userNames);
				usersIDs = userId;
				Ext.getCmp("servertree").disable();
				Ext.getCmp("rePlayWin").show();
			}
	});
}

//批量删除
function deleteById(params) {
	var selModel = params.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
			if (button == "yes") {
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.alertId);
				})
				deletes(Ids);
			}
			else {
				Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
			}
		})
	}if(selModel.getSelection()==0){
		Ext.Msg.alert("温馨提示","请选择需要删除的用户!");
		return;
	}
}
//单个删除
function deleteByOne(params){
	Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
			var Ids = new Array(); //要删除的id
			Ids.push(params);
			deletes(Ids);
		}
		else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		}
	})
}

function deletes(Ids){
	Ext.Ajax.request({
		url: ctx+"/alert/alert/alert!delete.action",
		params: { "ids": Ids},
		method: 'post',
		success: function (response, options) {
			Ext.MessageBox.alert('提示','操作成功');
			Store.load();
		},
		failure: function (response, options) {
			Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
		}
	});
}