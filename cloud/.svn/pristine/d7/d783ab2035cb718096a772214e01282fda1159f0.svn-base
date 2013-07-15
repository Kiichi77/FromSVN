var selStore = Ext.create('Ext.data.Store', {
	pageSize : 1000,   //页面容量,
	fields:['id','eventResult','event','ssert','time'],
	        proxy: {
	        	type: 'ajax',
	        	url : ctx+'/serviceinfo/serviceinfo/serviceinfo!SELList.action',
	        	reader: {
	        		type: 'json',
	        		root: 'sellist'
	        	}
	        }
})
selStore.load({			
	params:{'model.id':serverId}
})