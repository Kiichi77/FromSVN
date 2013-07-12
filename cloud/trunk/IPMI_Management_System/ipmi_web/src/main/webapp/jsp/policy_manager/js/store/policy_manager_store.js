/**
 * 异步请求策略数据
 */
var policyStore = Ext.create('Ext.data.Store', {
	pageSize : thePublicPageSize,   //页面容量,
	fields:['command','domain','id','policyLimit','policyName','policyStartHour','policyStartMinute','policyEndHour','policyEndMinute','policyTrigger','policyType','policyBumber'],
	proxy: {
		type: 'ajax',
		url : ctx+'/policy/policy/policy!list.action',		
		reader: {
			type: 'json',
		    totalProperty:'pageList.totalCount',
			root: 'pageList.result'
		}
	}
})

var hours = Ext.create('Ext.data.JsonStore', {
    fields: ['ids', 'names'],
    data: backHourAndMinnsFun(1)
});
var minins = Ext.create('Ext.data.JsonStore', {
    fields: ['ids', 'names'],
    data: backHourAndMinnsFun(2)
});

//位置类型
var policyTypeStore = Ext.create('Ext.data.Store', {
	storeId:'placeType',
	autoLoad: true,
	fields:['ids','names'],
	data:{'items':[{'ids':'0','names':'全部'},
	               {'ids':'1','names':'电源功耗策略'},
	               {'ids':'2','names':'CPU功耗策略'},
	               {'ids':'3','names':'内存功耗策略'},
	               {'ids':'4','names':'进风口温度策略'}
	               ]},
	               proxy: {
	            	   type: 'memory',
	            	   reader: {
	            		   type: 'json',
	            		   root: 'items'
	            	   }
	               }
}); 
var policyTypeStore2 = Ext.create('Ext.data.Store', {
	storeId:'placeType',
	autoLoad: true,
	fields:['ids','names'],
	data:{'items':[
	               {'ids':'1','names':'电源功耗策略'},
	               {'ids':'2','names':'CPU功耗策略'},
	               {'ids':'3','names':'内存功耗策略'},
	               {'ids':'4','names':'进风口温度策略'}
	               ]},
	               proxy: {
	            	   type: 'memory',
	            	   reader: {
	            		   type: 'json',
	            		   root: 'items'
	            	   }
	               }
}); 