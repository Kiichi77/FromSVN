var Store = Ext.create('Ext.data.Store', {
	storeId : 'simpsonsStore',
	pageSize : thePublicPageSize, // 页面容量
	fields : [ 'id', 'serverId', 'policyId','serverIp','policyIdInServer','domainInServer',
	           'operat','failReason','modifyEnterLimit','modifyEnterHope','datetime' ],
	proxy : {
		type : 'ajax',
		url : ctx + '/failRecode/failRecode/failRecode!list.action',
		reader : {
			type : 'json',
			totalProperty : 'failPageList.totalCount',
			root : 'failPageList.result'
		}
	}
});

//位置类型
var failReason = Ext.create('Ext.data.Store', {
    storeId:'failReason',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                {'ids':'全部','names':'-1'},
               	{'ids':'连接超时','names':'1'},
            	{'ids':'policy Id被占用','names':'2'},
            	{'ids':'其他原因','names':'3'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

