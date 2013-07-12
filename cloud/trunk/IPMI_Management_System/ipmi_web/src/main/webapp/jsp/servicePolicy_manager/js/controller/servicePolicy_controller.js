var timeLoad = doloadingFunc(Ext.getBody());
/**
 * 加载用户信息的store
 */
function queryUserList(){
	policyStore.currentPage = 1;
	policyStore.pageSize = thePublicPageSize;
	policyStore.load({			
		params:{
			start:0,
			limit:thePublicPageSize,
			'model.tbPolicy.policyName':Ext.getCmp("policynameforsearch").getValue(),
			'model.tbServer.serverIpIn':Ext.getCmp("ipin").getValue(),
			'model.biningState':Ext.getCmp("statecombox").getValue()
		}
	});
	policyStore.on("beforeload",function(store, options){	
		Ext.apply(store.proxy.extraParams,{
			'model.tbPolicy.policyName':Ext.getCmp("policynameforsearch").getValue(),
			'model.tbServer.serverIpIn':Ext.getCmp("ipin").getValue(),
			'model.biningState':Ext.getCmp("statecombox").getValue()
		});
	});	
}
function check(){
	var zhengze=''
}
var modifyid;

function saveServiceInfo(){
	timeLoad.show();
	var policyid=Ext.getCmp('policyid').getValue();
	var policyid2=Ext.getCmp('policyid2').getValue();
	var selModel=Ext.getCmp('servertree');
	var selected = selModel.getChecked();
	var id=Ext.getCmp('addid').getValue();
	var Ids = new Array();
	Ext.each(selected, function (item) {
		if(item.data.leaf==true){
			Ids.push(item.data.id.substring(0,item.data.id.length-1));
		}
	})
	if(Ids.length<=0){
		if(id==null||id==''){
			timeLoad.hide();
			Ext.MessageBox.alert('提示','请选择一台主机');
			return;
		}
	}
	if(policyid==null||policyid==''){timeLoad.hide();Ext.MessageBox.alert('提示','请选择策略');return;}
	
		if(id==null||id==''){
			Ext.Ajax.request({
				url: ctx+'/policy/policy/policyserver!saveByTree.action',
				params: {'treeid':Ids,'policyid2':policyid},
				method: 'post',
				success: function (response, options) {
					timeLoad.hide();
					var pp  =  Ext.JSON.decode(response.responseText);
					Ext.MessageBox.alert('提示',pp.msg);
					var addwin = Ext.getCmp('addwin');addwin.hide();
					policyStore.load();
				},
				failure: function (response, options) {
					timeLoad.hide();
				}
			});
	}	else{

		return;
	}
}

function update(){
	var id=Ext.getCmp('addid').getValue();
	Ext.Ajax.request({
		url: ctx+'/policy/policy/policyserver!update.action',
		params: {'treeid':id,'policyid2':modifyid},
		method: 'post',
		success: function (response, options) {
			var pp  =  Ext.JSON.decode(response.responseText);
			Ext.MessageBox.alert('提示',pp.msg);
			var modifywin = Ext.getCmp('modifywin');modifywin.hide();
			policyStore.load();
		},
		failure: function (response, options) {
			Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
		}
	});
}



function modify(id){
	var zu=id.split(',');
	Ext.getCmp('addid').setValue(zu[0]);
	Ext.getCmp('policyid2').setValue(zu[2]);
	modifyid=zu[2];
	Ext.getCmp('policyid2').setRawValue(zu[1]);
	var modifywin = Ext.getCmp('modifywin');
	modifywin.show();
}		


function deleteUser(parqams){
	var selModel = parqams.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
			if (button == "yes") {
				timeLoad.show();
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.id);
				})
				Ext.Ajax.request({
					url: ctx+'/policy/policy/policyserver!delete.action',
					params: { "ids": Ids},
					method: 'post',
					success: function (response, options) {
						timeLoad.hide();
						Ext.MessageBox.alert('提示','操作成功');
						policyStore.load();
					},
					failure: function (response, options) {
						timeLoad.hide();
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
				});
			}
			else {
				timeLoad.hide();
				Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
			}
		})
	}if(selModel.getSelection()==0){
		timeLoad.hide();
		Ext.Msg.alert("温馨提示","请选择需要删除的用户!");
		return;
	}
}
function deleteOneUser(id){
	var Ids = new Array(); //要删除的id
	Ids.push(id);
	Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
				url: ctx+'/policy/policy/policyserver!delete.action',
				params: { "ids": Ids},
				method: 'post',
				success: function (response, options) {
					Ext.MessageBox.alert('提示','操作成功');
					policyStore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}
	})
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