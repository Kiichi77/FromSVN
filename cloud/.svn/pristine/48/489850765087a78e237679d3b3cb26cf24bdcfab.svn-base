Ext.onReady(function () {
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
		 		{
		 			xtype:'button',
	 				iconCls:'delete',
	 				text:"清空系统日志",
	 				listeners:{
	 					"click":function(){
	 						Ext.Ajax.request({
	 							url: ctx+'/serviceinfo/serviceinfo/serviceinfo!clearsel.action',
	 							params:{'model.id':serverId},
	 							method: 'post',
	 							success: function (response, options) {
	 								Ext.MessageBox.alert('提示','操作成功');
	 								policyStore.load();
	 							},
	 							failure: function (response, options) {
	 								Ext.MessageBox.alert('提示','操作失败，请求超时或网络故障。');
	 							}
	 						});
	 					}
	 				}
	 			},
		 		{
		 			xtype:'button',
	 				iconCls:'edit',
	 				text:"返回",
	 				listeners:{
	 					"click":function(){
	 						forback();
	 					}
	 				}
	 			}	
          ]
	});
var serviceTable = Ext.create('Ext.grid.Panel',{
	    title:'系统日志',
		id:'firstGrid',
    	autoHeight : true, 
    	tbar:topTool,
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		viewConfig: {forceFit:true}, 		//列自适应宽
		columns :servicePowerConsumeCm,
		stripeRows: true,
		trackMouseOver : true ,		
		frame:true,
		store:selStore,
		boder:true,
    	renderTo:Ext.getBody()
	});

});