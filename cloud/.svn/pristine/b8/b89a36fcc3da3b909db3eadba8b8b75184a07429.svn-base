//根据传入参数查询目标服务器
this.queryPlaceByNameOrIp = function (failReason) {
	Store.pageSize = thePublicPageSize;
	Store.load({			
			params:{
			start:0,
		    limit:thePublicPageSize,
		    'model.failReason' : failReason//失败原因
		}
	});
	Store.on('beforeload', function (store, options) {
	    Ext.apply(store.proxy.extraParams, {
	    	'model.failReason' : failReason//失败原因
	    });
	});
}

function deleteUser(parqams){
	var selModel = parqams.getSelectionModel();
	if (selModel.hasSelection()) {
		Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
			if (button == "yes") {
				var selected = selModel.getSelection();
				var Ids = new Array(); //要删除的id
				Ext.each(selected, function (item) {
					Ids.push(item.data.id);
				})
				Ext.Ajax.request({
					url: ctx+'/failRecode/failRecode/failRecode!delete.action',
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
			else {
				Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
			}
		})
	}if(selModel.getSelection()==0){
		Ext.Msg.alert("温馨提示","请选择需要删除的错误!");
		return;
	}
}
function deleteOneUser(id){
	var Ids = new Array(); //要删除的id
	Ids.push(id);
	Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
				url: ctx+'/failRecode/failRecode/failRecode!delete.action',
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
	})
}

function carryServiceIpToQuery(id){
	Ext.Ajax.request({
		url: ctx+'/failRecode/failRecode/failRecode!check.action',
		params: { "model.id": id},
		method: 'post',
		success: function (response, options) {
			var pp  =  Ext.JSON.decode(response.responseText);
			Ext.MessageBox.alert('提示',pp.msg);
			Store.load();
		},
		failure: function (response, options) {
			Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
		}
	});
}