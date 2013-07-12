/**
 * 异步请求用户数据
 */
var rolestore = Ext.create('Ext.data.Store', {
	fields:['id','rolename'],
	proxy: {
		type: 'ajax',
		url : ctx+'/role/role/role!findAll.action',
		reader: {
			type: 'json',
			root: 'list'
		}
	}
})
rolestore.load();	
/**
 * 异步请求用户数据
 */
var userStore = Ext.create('Ext.data.Store', {
	pageSize : thePublicPageSize,   //页面容量,
	fields:['userName','accouont','tbRole.rolename','email','phoneNumber','tbRole.id','psssword'],
	proxy: {
		type: 'ajax',
		url : ctx+'/user/user/user!list.action',
		reader: {
			type: 'json',
			totalProperty : 'pageList.totalCount',
			root: 'pageList.result'
		}
	}
})

