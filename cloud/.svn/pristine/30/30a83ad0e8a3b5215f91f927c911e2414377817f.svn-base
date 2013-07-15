var rolIds = null; 

/**
 * 加载用户信息的store
 */
function queryUserList(){
	userStore.currentPage = 1;
	userStore.pageSize = thePublicPageSize;
	userStore.load({			
			params:{
				start:0,
				limit:thePublicPageSize,
				'model.userName' :  Ext.getCmp("usernameforsearch").getValue(),
				'model.accouont' :  Ext.getCmp("accountforsearch").getValue(),
				'model.tbRole.id'  :  Ext.getCmp("role2").getValue()
			}
	});
	userStore.on("beforeload",function(store, options){	
		Ext.apply(store.proxy.extraParams,{
			'model.userName' :  Ext.getCmp("usernameforsearch").getValue(),
			'model.accouont' :  Ext.getCmp("accountforsearch").getValue(),
			'model.tbRole.id'  :  Ext.getCmp("role2").getValue()
		});			
	});
}

function saveServiceInfo(){
	var id = Ext.getCmp('addid').getValue();
	var name=Ext.getCmp('name').getValue();
	var accounaddt=Ext.getCmp('accountadd').getValue();
	var emailadd=Ext.getCmp('emailadd').getValue();
	var passwordadd=Ext.getCmp('passwordadd').getValue();
	var phoneadd=Ext.getCmp('phoneadd').getValue();
	var role1=rolIds;
	var params=Ext.getCmp('form1');
	if(params.form.isValid()) {
		if(id==null||id==''){
			Ext.Ajax.request({
				url: ctx+'/user/user/user!saveUser.action',
				params: { "model.userName": name,'model.accouont' : accounaddt,'model.email' : emailadd
					,'model.password' : passwordadd,'model.phoneNumber' : phoneadd,'roleid' : role1},
					method: 'post',
					success: function (response, options) {
						Ext.MessageBox.alert('提示','操作成功');
						userStore.load();
						Ext.getCmp('addwin').hide();
					},
					failure: function (response, options) {
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
			});
		}else{
			Ext.Ajax.request({
				url: ctx+'/user/user/user!updateUser.action',
				params: {"model.id": id, "model.userName": name,'model.accouont' : accounaddt,'model.email' : emailadd
					,'model.phoneNumber' : phoneadd,'roleid' : role1},
					method: 'post',
					success: function (response, options) {
						Ext.MessageBox.alert('提示','操作成功');
						userStore.load();
						Ext.getCmp('addwin').hide();
					},
					failure: function (response, options) {
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
			});
		}
	}else{
		return;
	}

}

function modify(id){
	var zu=id.split(',');//
	Ext.getCmp('addid').setValue(zu[0]);
	Ext.getCmp('name').setValue(zu[1]);
//	Ext.getCmp('role1').setValue(zu[2]);
	rolIds = zu[2];
	Ext.getCmp('role1').setRawValue(zu[6]);
	Ext.getCmp('emailadd').setValue(zu[3]);
	Ext.getCmp('phoneadd').setValue(zu[4]);
	Ext.getCmp('accountadd').setValue(zu[5]);
	Ext.getCmp('accountadd').setDisabled(true);
	Ext.getCmp('passwordadd').setDisabled(true);
	var addwin = Ext.getCmp('addwin');
	addwin.show();
}		


function deleteUser(parqams){
	var selModel = parqams.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "确定要删除这些用户吗？", function (button) {
			if (button == "yes") {
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.id);
				})
				Ext.Ajax.request({
					url: ctx+'/user/user/user!delete.action',
					params: { "ids": Ids},
					method: 'post',
					success: function (response, options) {
						var pp  =  Ext.JSON.decode(response.responseText);
						Ext.MessageBox.alert('提示',pp.msg);
						userStore.load();
					},
					failure: function (response, options) {
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
				});
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
function deleteOneUser(id){
	var Ids = new Array(); //要删除的id
	Ids.push(id);
	Ext.Msg.confirm("警告", "确定要删除该用户吗？", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
				url: ctx+'/user/user/user!delete.action',
				params: { "ids": Ids},
				method: 'post',
				success: function (response, options) {
					var pp  =  Ext.JSON.decode(response.responseText);
					Ext.MessageBox.alert('提示',pp.msg);
					userStore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}
	})
}

//检验用户账号是否存在
function checkTheAccounts() {
	Ext.Ajax.request({
		url: ctx+'/user/user/user!checkAccount.action',
		params: {'model.accouont' : Ext.getCmp('accountadd').getValue()},
			method: 'post',
			success: function (response, action) {
				var obj = Ext.JSON.decode(response.responseText);
				if('ok'==obj.msg) {
				} else {
					Ext.MessageBox.alert('提示','该账号已经存在，请选择其他的账号');
					Ext.getCmp('accountadd').setValue("");
				}
			}
	});
}