function queryUserList(){
	selStore.currentPage = 1;
	selStore.pageSize = thePublicPageSize;
	selStore.load({			
		params:{
			start:0,
			limit:thePublicPageSize,
			'model.serverIp':Ext.getCmp("idracIp").getValue(),
			'time':Ext.util.Format.date(Ext.getCmp("dateTime").getValue(),'Y-m-d'),
			'endtime':Ext.util.Format.date(Ext.getCmp("endTime").getValue(),'Y-m-d')
		}
	});
	selStore.on("beforeload",function(store, options){	
		Ext.apply(store.proxy.extraParams,{
			'model.serverIp':Ext.getCmp("idracIp").getValue(),
			'time':Ext.util.Format.date(Ext.getCmp("dateTime").getValue(),'Y-m-d'),
			'endtime':Ext.util.Format.date(Ext.getCmp("endTime").getValue(),'Y-m-d')
		});
	});	
}