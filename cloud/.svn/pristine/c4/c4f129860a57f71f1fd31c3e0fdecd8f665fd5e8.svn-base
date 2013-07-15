var nodeIDs = "";

//清空所有填写内容
function clearAllWirte() {
	Ext.getCmp("theServiceInfoPanels").form.reset();
	Ext.getCmp("idRacip").enable();
	var tpanel = Ext.getCmp("serviceTreePanel");
	var selNodes = tpanel.getChecked();
	Ext.each(selNodes, function(node){
		node.set('checked', false);
	});
	//取消保存选择的服务器位置，并隐藏窗口
	//Ext.getCmp("serviceInfoSetWin").hide();
}

//检测idrac信息是否正确
function checkIdracInfo() {
	if("" != Ext.getCmp("idRacip").getValue() && "" != Ext.getCmp("idRacipName").getValue() && "" != Ext.getCmp("idRacipPwd").getValue()) {
		Ext.Ajax.request({
			url: ctx+'/serviceinfo/serviceinfo/serviceinfo!checkIdracInfo.action',
			params: {
				'model.idracIp':Ext.getCmp("idRacip").getValue(),
				'model.idracUser':Ext.getCmp("idRacipName").getValue(),
				'model.idracPassword':Ext.getCmp("idRacipPwd").getValue()
			},
			method: 'post',
			success: function (response, action) {
				var pp  =  Ext.JSON.decode(response.responseText);
				if("操作成功" != pp.msg) {
					Ext.getCmp("idRacip").markInvalid(pp.msg);
					Ext.getCmp("idRacipName").markInvalid(pp.msg);
					Ext.getCmp("idRacipPwd").markInvalid(pp.msg);
				} else {
					Ext.getCmp("idRacip").clearInvalid();
					Ext.getCmp("idRacipName").clearInvalid();
					Ext.getCmp("idRacipPwd").clearInvalid();
				}
			}	
		});
	}
}

//保存目标服务器信息
function saveServiceInfo(params) {
	if(params.form.isValid()) {
		var urls = "";
		if(null == Ext.getCmp("nowServicePlaceIds").getValue() || "" == Ext.getCmp("nowServicePlaceIds").getValue()) {//如果Id为空，则使用新增url    
			urls = ctx+'/serviceinfo/serviceinfo/serviceinfo!save.action';
		} else {
			urls = ctx+'/serviceinfo/serviceinfo/serviceinfo!update.action';
		}
		Ext.Ajax.request({
			url: urls,
			params: {  
				"model.id":Ext.getCmp("nowServicePlaceIds").getValue(),
				"model.groupId":Ext.getCmp("palceNodeId").getValue(),
				'model.serverName':Ext.getCmp("serviceName").getValue(),
				'model.operateSystem':Ext.getCmp("operateSystem").getValue(),
				'model.idracIp':Ext.getCmp("idRacip").getValue(),
				'model.idracUser':Ext.getCmp("idRacipName").getValue(),
				'model.idracPassword':Ext.getCmp("idRacipPwd").getValue(),
				'model.serverIpIn':Ext.getCmp("inServiceIp").getValue(),
				'model.serverIpOut':Ext.getCmp("outServiceIp").getValue(),
				'model.serverDescription':Ext.getCmp("remark").getValue(),
				'model.quickServerCode':Ext.getCmp("queikCode").getValue(),
				'model.searchNote1':Ext.getCmp("nowWhere").getValue(),
				'belongId':Ext.getCmp("palceNodeId").getValue()
			},
			method: 'post',
			success: function (response, options) {
				var obj = Ext.JSON.decode(response.responseText);
				if(null != obj.msg || "" != obj.msg) {
					Ext.MessageBox.alert('提示', obj.msg);
					Ext.getCmp("serviceInfoSetWin").hide();
					queryServiceByNameOrIp();
				} else {
					Ext.MessageBox.alert('提示', '操作成功');
					Ext.getCmp("serviceInfoSetWin").hide();
					queryServiceByNameOrIp();
				}
			},
			failure: function (response, options) {
				Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
				Ext.getCmp("serviceInfoSetWin").hide();
			}
		});
	}
}

//加载服务器位置树形
function showAndQueryServicePlace(paramsWin,paramsTree) {
	paramsWin.show();
}

//保存确认选择的服务器位置
function comfirmTreeNode(){
	var tpanel = Ext.getCmp("serviceTreePanel");
	var comNode = null;
	var msg = "";
	var selNodes = tpanel.getChecked();
	var end = false;
	Ext.each(selNodes, function(node){
		if(node.get("leaf")){
			comNode = node;
			end = true;
		}
		msg += node.get("text")+"→";
	});
	if(!end) {
		Ext.MessageBox.alert('提示', '请选择一个机箱。');
		return;
	}
	msg = msg.substring(5,msg.length-1);
	Ext.getCmp("nowWhere").setValue(msg);
	Ext.getCmp("palceNodeId").setValue(comNode.get("id"));
	Ext.getCmp("placeWin").hide();
}

/**
 * 有子节点选中，则父节点选中；若子节点都不选中，则父节点也不能被选中
 * 只能选择一项
 */
function checkChangeCkeckOnlyOne(node,checked) {
	var tpanel = Ext.getCmp("serviceTreePanel");
	var selNodes = tpanel.getChecked();
	Ext.each(selNodes, function(nodes){
		nodes.set("checked", false);
	});
	if (checked == true) {
		node.set("checked", true);
		node.checked = checked;
		//获得父节点
		pNode = node.parentNode;
		//当checked == true通过循环将所有父节点选中
		for (; pNode != null; pNode = pNode.parentNode) {
			pNode.set("checked", true);
		}    
	} else {
		node.checked = checked;
		//获得父节点
		pNode = node.parentNode;
		//当checked == true通过循环将所有父节点选中
		for (; pNode != null; pNode = pNode.parentNode) {
			pNode.set("checked", false);
		}   
	}
	//当该节点有子节点时，将所有子节点选中删除
	if (!node.get("leaf") && !checked){
		node.cascade(function(node){
			node.set('checked', false);
			pNode = node.parentNode;
			for (; pNode != null; pNode = pNode.parentNode) {
				pNode.set("checked", false);
			}
		}); 
	}   
}
//IPRANGe窗口打开
function openWin(){
	Ext.getCmp("ipduan").setValue("");
	Ext.getCmp("iuser").setValue("");
	Ext.getCmp("ipassword").setValue("");
	Ext.getCmp("dongwin").show();
}
//IPRANGE提交方法
function iprange(){
	var ipcheck=Ext.getCmp("ipduan").getValue();
	if(findHowManyParam(ipcheck,".")<2){
		Ext.MessageBox.alert('提示', '1请输入类似***.***.**的网段');
		return ;
	}
	if(ipcheck.substring(ipcheck.length-1,ipcheck.length)=="."){
		Ext.MessageBox.alert('提示', '2请输入类似***.***.**的网段');
		return ;
	}
	if(findHowManyParam(ipcheck,"..")>0){
		Ext.MessageBox.alert('提示', '3请输入类似***.***.**的网段');
		return ;
	}


	Ext.MessageBox.alert("服务器检测需要一定的时间,您可以先进行别的操作,等一下再回来刷新页面即可");
	Ext.getCmp("dongwin").hide();
	Ext.Ajax.request({
		url: ctx+'/serviceinfo/serviceinfo/serviceinfo!iprange.action',
		params: {
			'model.idracIp':ipcheck,
			'model.idracUser':Ext.getCmp("iuser").getValue(),
			'model.idracPassword':Ext.getCmp("ipassword").getValue()
		},
		method: 'post',
		success: function (response, action) {
			Store.load();
		}	
	});
}
//a字符串中包含多少个b
function findHowManyParam(a,b){
	var i=0;
	while(true){
		var start=a.indexOf(b);
		if(start>=0){
			i++;
			a=a.substring(start+1,a.length);
		}
		else {
			break;
		}
	}
	return i;
}