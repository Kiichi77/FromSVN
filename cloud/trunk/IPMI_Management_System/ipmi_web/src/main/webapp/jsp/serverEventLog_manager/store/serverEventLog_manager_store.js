/**
 * 异步请求策略数据
 */
var selStore = Ext.create('Ext.data.Store', {
	pageSize : thePublicPageSize,   //页面容量,
	fields:['createTime','id','serverIp','selMsg','state'],
	proxy: {
		type: 'ajax',
		url : ctx+'/systemeventlog/systemeventlog/systemeventlog!list.action',		
		reader: {
			type: 'json',
		    totalProperty:'pageList.totalCount',
			root: 'pageList.result'
		}
	}
})
