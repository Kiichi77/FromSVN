/**
 * 异步请求角色数据
 */
var rolestore = Ext.create('Ext.data.Store', {
	fields:['id','rolename','note'],
	pageSize : thePublicPageSize,   //页面容量
	proxy: {
		type: 'ajax',
		url : ctx+'/role/role/role!list.action',
		reader: {
			type: 'json',
			totalProperty : 'pageList.totalCount',
			root: 'pageList.result'
		}
	}
})	
rolestore.load({			
	params:{
		start:0,
		limit:10
	}
});	

Ext.define('treeModel', {   
	extend: 'Ext.data.Model',
	fields: [
			{name: 'text',  type: 'string'},
			{name: 'id',  type: 'int'},
			{name: 'expanded',  type: 'boolean'},   
			{name: 'checked',  type: 'boolean'},
			{name: 'leaf',  type: 'boolean'}
	]       
});

//Ext.Ajax.request({   
//	url: ctx+'/role/role/role!selectRole.action',
//	method: 'post',   
//	loadMask:true,
//	success:function(form,action){   
//		
//		//var obj = Ext.util.JSON.decode(form.responseText);
//		alert(form.responseText);
//			menu = obj.rootz;
//	}
//});
//
//var treeStore = Ext.create('Ext.data.TreeStore', {
//	model: 'treeModel',
//	proxy: {
//		type: 'ajax',	http://127.0.0.1:8080/ipmi_web_local/jsp/role_manager/role_manager.jsp//		url : ctx+'/role/role/role!selectRole.action',
//	},
//	reader: {
//		type: 'json',
//		root: 'root'
//	}
//});
