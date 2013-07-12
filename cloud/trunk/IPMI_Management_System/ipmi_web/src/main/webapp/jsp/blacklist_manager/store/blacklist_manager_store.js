var Store = Ext.create('Ext.data.Store', {
	storeId : 'simpsonsStore',
	pageSize : thePublicPageSize, // 页面容量
	fields : [ 'id','serviceId', 'serverEntity.serverIpIn', 'serverEntity.serverIpOut',
			'serverEntity.serverName' ],
	proxy : {
		type : 'ajax',
		url : ctx + '/blackName/blackName/blackName!list.action',
		reader : {
			type : 'json',
			totalProperty : 'blackNamePageList.totalCount',
			root : 'blackNamePageList.result'
		}
	}
});

// 位置类型
var placeType = Ext.create('Ext.data.Store', {
	storeId : 'placeType',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'ajax',
		url : ctx + '/belong/belong/belong!findAll.action',
		reader : {
			type : 'json',
			root : 'list'
		}
	}
});