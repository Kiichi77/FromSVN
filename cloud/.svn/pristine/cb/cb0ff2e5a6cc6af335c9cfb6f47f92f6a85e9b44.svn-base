/**
 * 加载用户信息的store
 */
function queryUserList(){
	rolestore.currentPage = 1;
	rolestore.pageSize = thePublicPageSize;
	rolestore.load({			
		params:{
		start:0,
	    limit:thePublicPageSize,
	    'model.rolename' : Ext.getCmp("serviceName").getValue()
		}
	});
	rolestore.on('beforeload', function (store, options) {
	    Ext.apply(store.proxy.extraParams, {
	    	'model.rolename' : Ext.getCmp("serviceName").getValue()
	    });
	});
}

function saveServiceInfo(){
	var name=Ext.getCmp('name').getValue();
	var note=Ext.getCmp('note').getValue();
	var id=Ext.getCmp('id').getValue();
	var rightids =  new Array();
	//读取树的每一个ID
	var treeer = Ext.getCmp('tree').getChecked();
	Ext.each(treeer, function (item) {
		if(item.data.id!='root')
			rightids.push(item.data.id);
	})
	if(name!=null&&name!=""&&rightids!=null) {
		if(id==null||id==''){
			Ext.Ajax.request({
				url: ctx+'/role/role/role!saveRoleRight.action',
				params: { "model.rolename": name,'model.note' : note,'rightIds':rightids},
				method: 'post',
				success: function (response, options) {
					Ext.MessageBox.alert('提示','操作成功');
					var addwin = Ext.getCmp('addwin');addwin.hide();
					rolestore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}else{
			Ext.Ajax.request({
				url: ctx+'/role/role/role!updateRoleRight.action',
				params: {"model.id":id, "model.rolename": name,'model.note' : note,'rightIds':rightids},
				method: 'post',
				success: function (response, options) {
					Ext.MessageBox.alert('提示','操作成功');
					var addwin = Ext.getCmp('addwin');addwin.hide();
					rolestore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}
	}else{
		Ext.MessageBox.alert('提示','角色和菜单不能留空');
		return;
	}

}
function deleteRole(parqams){
	var selModel = parqams.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "你确定要是删除该这些角色?", function (button) {
			if (button == "yes") {
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.id);
				})
				Ext.Ajax.request({
					url: ctx+'/role/role/role!delete.action',
					params: { "ids": Ids},
					method: 'post',
					success: function (response, options) {
						var pp  =  Ext.JSON.decode(response.responseText);
						Ext.MessageBox.alert('提示',pp.msg);
						rolestore.load();
					},
					failure: function (response, options) {
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
				});
			}
			else {

			}
		})
	}if(selModel.getSelection()==0){
		Ext.Msg.alert("温馨提示","请选择需要删除的角色!");
		return;
	}
}
function deleteOneRole(id){
	var Ids = new Array(); //要删除的id
	Ids.push(id);
	Ext.Msg.confirm("警告", "你确定要是删除该角色?", function (button) {
		if(button=="yes"){
			Ext.Ajax.request({
				url: ctx+'/role/role/role!delete.action',
				params: { "ids": Ids},
				method: 'post',
				success: function (response, options) {
					var pp  =  Ext.JSON.decode(response.responseText);
					Ext.MessageBox.alert('提示',pp.msg);
					rolestore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}else {

		}
	})
}

function getTreeStore(menu){
	var treeStores = Ext.create('Ext.data.TreeStore', {
		root: {
			expanded: true,
			children: menu
		}
	});	
	var TreerPanel = Ext.create('Ext.tree.Panel', {
		id:'tree',
		width: 240,
		height: 150,
		store: treeStores,
		rootVisible: false,
		frame:true,
		listeners: {
			checkchange:function(node,checked,optes) {
				checkChangeCkeck(node,checked);
			}
		}
	});
	return TreerPanel;
}

function modify(id,win){
	Ext.getCmp("tree").destroy();
	var zu=id.split(',');//
	Ext.getCmp('id').setValue(zu[0]);
	Ext.getCmp('name').setValue(zu[1]);
	Ext.getCmp('note').setValue(zu[2]);
	var addwin = Ext.getCmp('addwin');
	Ext.Ajax.request({
		url: ctx+'/role/role/role!selectRole.action',
		params: {"model.id":(zu[0]*1)},
		method: 'post',
		success: function (response, options) {
			var obj = Ext.decode(response.responseText);
			var menu = obj.root;
			panel = getTreeStore(menu);
			Ext.getCmp("form1").add(panel);
			addwin.show();
		},
		failure: function (response, options) {
			Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
		}
	});
}

/**
 * 有子节点选中，则父节点选中；若子节点都不选中，则父节点也不能被选中
 */
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
			pNode = node.parentNode;
	    	for (; pNode != null; pNode = pNode.parentNode) {
	 		   pNode.set("checked", false);
	 		}
		}); 
	} else if(!node.get("leaf") && checked) {
		node.cascade(function(node){
			node.set('checked', true);
			pNode = node.parentNode;
	    	for (; pNode != null; pNode = pNode.parentNode) {
	 		   pNode.set("checked", true);
	 		}
		});
	}
}
