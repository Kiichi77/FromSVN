/**
 * 异步请求策略数据
 */
var policyStore = Ext.create('Ext.data.Store', {
	pageSize : thePublicPageSize,   //页面容量,
	fields:['id','biningState','biningType','tbPolicy.policyName','tbPolicy.policyLimit','tbPolicy.policyStartHour',
	        'tbPolicy.policyStartMinute','tbPolicy.policyType','tbServer.serverIpIn','tbServer.serverName','tbPolicy.id'
	        ,'policyidInServer','tbPolicy.policyEndMinute','tbPolicy.policyEndHour'],
	        proxy: {
	        	type: 'ajax',
	        	url : ctx+'/policy/policy/policyserver!list.action',
	        	reader: {
	        		type: 'json',
	        		totalProperty:'pageList.totalCount',
	        		root: 'pageList.result'
	        	}
	        }
})

var policyListStore = Ext.create('Ext.data.Store', {
	root: 'root',
	fields:['id','policyName'],
	proxy: {
		type: 'ajax',
		url : ctx+'/policy/policy/policy!findAll.action',
		reader: {
			type: 'json',
			root: 'list'
		}
	}
//autoLoad : true
})
policyListStore.load();

//服务器位置的树形控件数据源
var treeStore = Ext.create('Ext.data.TreeStore', {
	// 根节点的参数是parentId
	nodeParam : 'model.parId',
	// 根节点的参数值是0
	defaultRootId : 0,
	// 属性域	
	fields : [{name : 'id',type : 'long'}, 
	          {name : 'text',type : 'string'},
	          {name : 'iconCls', type : 'string'},
	          {name : 'checked',type : 'boolean'},
	          {name : 'level',type : 'short'},
	          {name : 'parName',type : 'string'}
	          ],
	          // 数据代理
	          proxy : {
	        	  type : 'ajax',// 请求方式
	        	  url : ctx+"/datacenter/datacenter/datacenter!findMenuWithServer.action",// 请求网址
	        	  reader: {
	        		  type: 'json',
	        		  root: 'belongEntityList'
	        	  }
	          }
});
