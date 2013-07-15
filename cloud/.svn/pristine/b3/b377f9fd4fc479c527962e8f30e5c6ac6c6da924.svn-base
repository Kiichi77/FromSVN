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
        url : ctx+"/datacenter/datacenter/datacenter!findMenu.action",// 请求网址
        reader: {
	        type: 'json',
	        root: 'belongEntityList'
	    }
    }
});
