var Store = Ext.create('Ext.data.Store', {
	storeId : 'simpsonsStore',
	pageSize : thePublicPageSize, // 页面容量
	fields : [ 'alertId','filterType','warningNum','seriousNum', 'tbServer.serverIpIn','tbUsers.id',
			'tbUsers.accouont','tbUsers.email','tbUsers.phoneNumber' ],
	proxy : {
		type : 'ajax',
		url : ctx + '/alert/alert/alert!pageList.action',
		reader : {
			type : 'json',
			totalProperty : 'alertPageInfo.totalCount',
			root : 'alertPageInfo.result'
		}
	}
});

//查询时筛选器类型
var filterType = Ext.create('Ext.data.Store', {
    storeId:'filterType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                {'ids':'全部','names':'-1'},   
               	{'ids':'系统功耗','names':'1'},
            	{'ids':'出风口温度','names':'2'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});
//添加时筛选器类型
var filterTypeAdd = Ext.create('Ext.data.Store', {
    storeId:'filterTypeAdd',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                   {'ids':'系统功耗','names':'1'},
                   {'ids':'出风口温度','names':'2'},
	               {'ids':'入风口温度','names':'3'},
	               {'ids':'CPU功耗','names':'4'},
	               {'ids':'内存功耗','names':'5'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

//查询时严重性
var serious = Ext.create('Ext.data.Store', {
    storeId:'serious',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                {'ids':'全部','names':'-1'},   
               	{'ids':'开启','names':'1'},
            	{'ids':'关闭','names':'0'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

//添加时严重性
//var seriousAdd = Ext.create('Ext.data.Store', {
//    storeId:'seriousAdd',
//    autoLoad: true,
//    fields:['ids','names'],
//    data:{'items':[
//               	{'ids':'启用','names':'1'},
//            	{'ids':'禁用','names':'2'}
//            	]},
//    proxy: {
//        type: 'memory',
//        reader: {
//            type: 'json',
//            root: 'items'
//        }
//    }
//});

//用户信息
var userStore = Ext.create('Ext.data.Store', {
	fields:['id','accouont'],
	proxy: {
		type: 'ajax',
		url : ctx+'/user/user/user!findAll.action',
		reader: {
			type: 'json',
			root: 'list'
		}
	}
});
userStore.load();
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
	          {name : 'level',type : 'short'}//,
	         // {name : 'parName',type : 'string'},
	         // {name : 'expanded ',type : 'boolean'}
	          ],
	          // 数据代理
	          proxy : {
	        	  type : 'ajax',// 请求方式
	        	  url : ctx+"/datacenter/datacenter/datacenter!findMenuWithServer.action",// 请求网址 findMenuWithServer
	        	  reader: {
	        		  type: 'json',
	        		  root: 'belongEntityList'
	        	  }
	          }
});

var timer = null;
function loadData() {
	if(null != Ext.getCmp("servertree")) {
		clearInterval(timer);
		treeStore.load({
			callback:function(){
				Ext.getCmp("servertree").expandAll();
			}
		});
	}
}
timer = setInterval("loadData()",2000);
