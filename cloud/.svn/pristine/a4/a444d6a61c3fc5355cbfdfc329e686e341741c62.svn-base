var Store = Ext.create('Ext.data.Store', {
    storeId:'simpsonsStore',
    fields:['id', 'text','parName','level'],
    pageSize : thePublicPageSize,   //页面容量
    proxy: {
	    type: 'ajax',
	    url : ctx+'/datacenter/datacenter/datacenter!findListByLevel.action',
	    reader: {
	        type: 'json',
	        totalProperty:'pageBelongList.totalCount',
	        root: 'pageBelongList.result'
	    }
    }
});

//位置类型
var placeType = Ext.create('Ext.data.Store', {
    storeId:'placeType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
                {'ids':'全部','names':'-1'},
               	{'ids':'数据中心','names':'1'},
            	{'ids':'机房','names':'2'},
            	{'ids':'机架系','names':'3'},
            	{'ids':'机架','names':'4'},
            	{'ids':'机箱','names':'5'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

//位置类型
var placeTypeSave = Ext.create('Ext.data.Store', {
    storeId:'placeType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
               	{'ids':'数据中心','names':'1'},
            	{'ids':'机房','names':'2'},
            	{'ids':'机架系','names':'3'},
            	{'ids':'机架','names':'4'},
            	{'ids':'机箱','names':'5'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

//所属父级位置
var parentsPlace = Ext.create('Ext.data.Store', {
    storeId:'parentsPlace',
    fields:['id','name'],
    proxy: {
	    type: 'ajax',
	    url : ctx+'/datacenter/datacenter/datacenter!findParentBylevel.action',
	    reader: {
	        type: 'json',
	        root: 'list'
	    }
    }
});

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
	          {name : 'parName',type : 'string'},
	          {name : 'expanded ',type : 'boolean'}
	          ],
	          // 数据代理
	          proxy : {
	        	  type : 'ajax',// 请求方式
	        	  url : ctx+"/datacenter/datacenter/datacenter!getBelongMenu.action",// 请求网址
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