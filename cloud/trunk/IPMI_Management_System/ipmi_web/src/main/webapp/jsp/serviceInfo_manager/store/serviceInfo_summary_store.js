var store = Ext.create('Ext.data.Store', {
    storeId:'simpsonsStore',
    pageSize : thePublicPageSize,   //页面容量
    fields:['id','serverIpIn','serverIpOut','serverName','cangetPowerConsumption','quickServerCode','operateSystem'],
    proxy: {
	    type: 'ajax',
	    url : ctx+'/serviceinfo/serviceinfo/serviceinfo!list.action',
	    reader: {
	        type: 'json',
	        totalProperty:'pageList.totalCount',
	        root: 'pageList.result'
	    }
    }
});

