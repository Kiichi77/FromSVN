var Store = Ext.create('Ext.data.Store', {
	storeId : 'simpsonsStore',
	pageSize : thePublicPageSize, // 页面容量
	fields : [ 'id', 'tbUser.accouont','operationResult','operationType','dateTime','param','note','ip' ],
	proxy : {
		type : 'ajax',
		url : ctx + '/systemLog/systemLog/systemLog!findpage.action',
		reader : {
			type : 'json',
			totalProperty : 'pageEntityList.totalCount',
			root : 'pageEntityList.result'
		}
	}
});

//操作类型
var todoType = Ext.create('Ext.data.Store', {
    storeId:'todoType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                {'ids':'全部','names':'-1'},
               	{'ids':'登陆','names':'1'},
            	{'ids':'添加','names':'2'},
            	{'ids':'修改','names':'3'},
            	{'ids':'删除','names':'4'},
            	{'ids':'批量删除','names':'5'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});