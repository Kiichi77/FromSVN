var Store = Ext.create('Ext.data.Store', {
	storeId:'simpsonsStore',
    fields:['id','serverIpIn','serverIpOut','serverName','cangetPowerConsumption','quickServerCode','operateSystem'
            ,'idracIp'],
    pageSize : thePublicPageSize,   //页面容量
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

//电池状态
var pwrStats = Ext.create('Ext.data.Store', {
    storeId:'pwrStats',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
               	{'ids':'全部','names':'-1'},
            	{'ids':'开启','names':'1'},
            	{'ids':'关闭','names':'2'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});