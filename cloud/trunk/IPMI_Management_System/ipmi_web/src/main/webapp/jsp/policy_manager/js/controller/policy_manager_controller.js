var timeLoad = doloadingFunc(Ext.getBody());
//清空表单的方法
function clearFormAll() {
	Ext.getCmp("form1").form.reset();
	shour=null;
	ehour=null;
	sminute=null;
	eminute=null;
	Ext.getCmp("policyType2").setReadOnly(false);
}

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
			'tbpolicy.policyName':Ext.getCmp("policynameforsearch").getValue(),
			'tbpolicy.policyType':Ext.getCmp("policyType").getValue()
		}
	});
	policyStore.on("beforeload",function(store, options){	
		Ext.apply(store.proxy.extraParams,{
			'tbpolicy.policyName':Ext.getCmp("policynameforsearch").getValue(),
			'tbpolicy.policyType':Ext.getCmp("policyType").getValue()
		});
	});	
}

var shour;//月份下拉框传值
var sminute;//日下拉框的值
var ehour;//月份下拉框传值
var eminute;//日下拉框的值
function saveServiceInfo(){
	timeLoad.show();
	var name=Ext.getCmp('name').getValue();
	var limitadd=Ext.getCmp('limitadd').getValue();
	var id=Ext.getCmp('addid').getValue();
	var number=Ext.getCmp('numberaadd').getValue();
	var down=Ext.getCmp('down').getValue();
	var dowmlimit=Ext.getCmp('downlimit').getValue();
//	var starthouradd=Ext.getCmp('shour').getValue();
//	var startminuteadd=Ext.getCmp('sminute').getValue();
	var starthouradd=shour;
	var startminuteadd=sminute;
	var endthouradd=ehour;
	var endtminuteadd=eminute;
	var policyType2=Ext.getCmp('policyType2').getValue();
	var params=Ext.getCmp('form1');
	if(policyType2==4){
		Ext.getCmp('limitadd').setValue('1');//给个值好提交
		Ext.getCmp('numberaadd').setValue('1');//给个值好提交
		limitadd=dowmlimit;
		if(down==null){
			timeLoad.hide();
			Ext.MessageBox.alert('提示','进风口温度功耗下降比例不能为空,可以输入0-15');
			return;
		}
	}else{
		Ext.getCmp('down').setValue('1');//给个值好提交
		Ext.getCmp('downlimit').setValue('1');//给个值好提交
		if(number==null){
			return;
		}
	}
	if(params.form.isValid()) {
		if(id==null||id==''){
			Ext.Ajax.request({
				url: ctx+'/policy/policy/policy!save.action',
				params: { "tbpolicy.policyName": name,'tbpolicy.policyType' : policyType2,'tbpolicy.policyLimit' : limitadd
					,'tbpolicy.policyStartHour' : starthouradd,'tbpolicy.policyEndHour' : endthouradd,'tbpolicy.policyEndMinute' : endtminuteadd,'tbpolicy.policyBumber' : number,
					'tbpolicy.policyStartMinute' : startminuteadd,'down':down},
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
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
			});
		}else{
			if(policyType2==4){
				if(down==null){
					timeLoad.hide();
					Ext.MessageBox.alert('提示','进风口温度功耗下降比例不能为空,可以输入0-15');
					return;
				}
			}else{
				if(number==null){
					timeLoad.hide();
					return;
				}
			}
			Ext.Ajax.request({
				url: ctx+'/policy/policy/policy!update.action',
				params: {"tbpolicy.id":id, "tbpolicy.policyName": name,'tbpolicy.policyType' : policyType2,'tbpolicy.policyLimit' : limitadd
					,'tbpolicy.policyStartHour' : starthouradd,'tbpolicy.policyEndHour' : endthouradd,'tbpolicy.policyEndMinute' : endtminuteadd,'tbpolicy.policyBumber' : number,
					'tbpolicy.policyStartMinute' : startminuteadd,'down':down},
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
						Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
					}
			});
		}

	}	else{
		timeLoad.hide();
		return;
	}
}

function savePolicyServer(){
	timeLoad.show();
	var name=Ext.getCmp('nameset').getValue();
	var ip=Ext.getCmp('ipset').getValue();
	var id=Ext.getCmp('idset').getValue();
	var params=Ext.getCmp('form2');
	if((ip!=null||ip!='')&&(name!=null||name!='')) {
		Ext.Ajax.request({
			url: ctx+'/policy/policy/policyserver!searchandsave.action',
			params: { "ip": ip,'policyid' : id},
			method: 'post',
			success: function (response, options) {
				timeLoad.hide();
				var pp  =  Ext.JSON.decode(response.responseText);
				Ext.MessageBox.alert('提示',pp.msg);
				var setwin=Ext.getCmp('setwin');
				setwin.hide();
				//	window.location.href='../servicePolicy_manager/servicePolicy_manager.jsp';
			},
			failure: function (response, options) {
				timeLoad.hide();
				Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
			}
		});
	}	else{
		return;
	}
}

//根据选择的策略类型展现不同的控件
function checkTypeShowText(params) {
	if(4 == params){
		Ext.getCmp('down').setVisible(true);
		Ext.getCmp('downlimit').setVisible(true);
		Ext.getCmp('numberaadd').setVisible(false);
		Ext.getCmp('limitadd').setVisible(false);



	}else{
		Ext.getCmp('down').setVisible(false);
		Ext.getCmp('downlimit').setVisible(false);
		Ext.getCmp('limitadd').setVisible(true);
		Ext.getCmp('numberaadd').setVisible(true);

	}
}

function modify(id){
	var zu=id.split(',');	//根据“，”分隔，策略信息
	Ext.getCmp('addid').setValue(zu[0]);
	Ext.getCmp('ehour').setRawValue(zu[7]);
	Ext.getCmp('eminute').setRawValue(zu[6]);
	Ext.getCmp('shour').setRawValue(zu[2]);
	Ext.getCmp('sminute').setRawValue(zu[3]);
	Ext.getCmp('name').setValue(zu[1]);
	Ext.getCmp('policyType2').setValue(zu[4]);
	checkTypeShowText(zu[4]);
	Ext.getCmp('limitadd').setValue(zu[5]);
	Ext.getCmp('numberaadd').setValue(zu[8]);
	if(zu[4]==4){
		Ext.getCmp('down').setValue(zu[8]);
		Ext.getCmp('downlimit').setValue(zu[5]);
	}
	shour=zu[2];
	sminute=zu[3];
	ehour=zu[7];
	eminute=zu[6];
	Ext.getCmp('addwin').show();
}		

function openSetIpWin(array){
	var zu=array.split(',');
	Ext.getCmp('idset').setValue(zu[0]);
	Ext.getCmp('nameset').setValue(zu[1]);
	var addwin = Ext.getCmp('setwin');
	addwin.show();
}


function deleteUser(parqams){
	var selModel = parqams.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "如果选择的策略中有绑定了主机的存在,将都不会执行删除", function (button) {
			if (button == "yes") {
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.id);
				})
				Ext.Ajax.request({
					url: ctx+'/policy/policy/policy!delete.action',
					params: { "ids": Ids},
					method: 'post',
					success: function (response, options) {
						var pp  =  Ext.JSON.decode(response.responseText);
						Ext.MessageBox.alert('提示',pp.msg);
						policyStore.load();
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
		Ext.Msg.alert("温馨提示","请选择需要删除的策略!");
		return;
	}
}
function deleteOneUser(id){
	var Ids = new Array(); //要删除的id
	Ids.push(id);
	Ext.Msg.confirm("警告", "如果策略已经绑定主机,不能删除", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
				url: ctx+'/policy/policy/policy!delete.action',
				params: { "ids": Ids},
				method: 'post',
				success: function (response, options) {
					var pp  =  Ext.JSON.decode(response.responseText);
					Ext.MessageBox.alert('提示',pp.msg);
					policyStore.load();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
				}
			});
		}
	})
}