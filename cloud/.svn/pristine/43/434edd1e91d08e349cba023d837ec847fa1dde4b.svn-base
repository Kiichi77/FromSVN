//根据传入参数查询目标服务器
this.queryPlaceByNameOrIp = function (accouont,operationType,startTime,endTime) {
	Store.currentPage = 1;
	Store.pageSize = thePublicPageSize;
	Store.load({			
			params:{
			start:0,
		    limit:thePublicPageSize,
		    'entity.tbUser.accouont' : accouont,//操作者
			'entity.operationType' : operationType,//操作类型
			'entity.startTime':startTime, //开始时间
			'entity.endTime':endTime //结束时间
		}
	});
	Store.on('beforeload', function (store, options) {
	    Ext.apply(store.proxy.extraParams, {
	    	limit:thePublicPageSize,
	    	'entity.tbUser.accouont' : accouont,//操作者
			'entity.operationType' : operationType,//操作类型
			'entity.startTime':startTime, //开始时间
			'entity.endTime':endTime //结束时间
	    });
	});
}
