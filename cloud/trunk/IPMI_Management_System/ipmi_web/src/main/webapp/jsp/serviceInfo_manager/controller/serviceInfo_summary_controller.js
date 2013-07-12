//根据服务器名称或者服务器IP查询目标服务器
function queryServiceByNameOrIp() {
	store.currentPage = 1;
	store.pageSize = thePublicPageSize;
	store.load({			
		params:{
			start:0,
			limit:thePublicPageSize,
			'model.serverName' : Ext.getCmp("model.serviceName").getValue(),
			'model.serverIpIn' : Ext.getCmp("model.serviceIP").getValue()
		}
	});
	store.on('beforeload', function (store, options) {
		Ext.apply(store.proxy.extraParams, {
			'model.serverName' : Ext.getCmp("model.serviceName").getValue(),
			'model.serverIpIn' : Ext.getCmp("model.serviceIP").getValue()
		});
	});
}

//根据服务器IP打开或关闭该服务器电源
//参数：IP，当前服务器电源状态
function openOrclosePwrByIp(paramIp,stats) {
	var str="";
	if(1==stats){
		str="开机";
	}else{
		str ="关机";
	}
	Ext.Msg.confirm("警告", "确定要"+str+"?", function (button) {
		if (button == "yes") {
			Ext.Ajax.request({
				url: ctx+'/serviceinfo/serviceinfo/serviceinfo!openOrShutdown.action',
				params: { "model.id": paramIp,"stateType":stats},
				method: 'post',
				success: function (response, options) {
					Ext.MessageBox.alert('提示', '操作成功');
					queryServiceByNameOrIp();
				},
				failure: function (response, options) {
					Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
				}
			});
		}})
}


//跳入详情页面
function openDetail(paramIp){
	var width=document.body.clientWidth;
	var height=document.body.clientHeight;
	window.open('serviceInfo_details.jsp?serverId='+paramIp+'', 'newwindow', 'height='+height+', width='+width+', top=80, left=100, toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no');
//	window.location.href = "serviceInfo_details.jsp?serverId="+paramIp;
}
//跳入系统日志页面
function openSEL(id){
	window.location.href = "serviceInfo_sel.jsp?serverId="+id;
}
