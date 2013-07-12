Ext.onReady(function () {
	
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				"操作者:",
				{xtype:"textfield",fieldLabel:"",id:'entity.tbUser.accouont',width:200,maxLength:20},
				"-",
		        "操作:",
				{
	                   xtype: 'combobox',
	                   fieldLabel: '',
	                   store:todoType,
	                   queryMode: 'local',
	                   displayField: 'ids',
	                   editable:false,
	                   valueField: 'names',
	                   name:'pwerId',
	                   id:'entity.operationType',
	                   width:120
	            },
				"-",
				"开始时间:",
				{xtype:"datefield",fieldLabel:"",id:'entity.startTime',width:200,format: "Y-m-d",value:getStartTime()},
				"结束时间:",
				{xtype:"datefield",fieldLabel:"",id:'entity.endTime',width:200,format: "Y-m-d",value:getStartTime()},
				"-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							var accouont = Ext.getCmp("entity.tbUser.accouont").getValue();
							var operationType = Ext.getCmp("entity.operationType").getValue();
							var startTime = Ext.util.Format.date(Ext.getCmp("entity.startTime").getValue(),'Y-m-d');
							var endTime = Ext.util.Format.date(Ext.getCmp("entity.endTime").getValue(),'Y-m-d');
							queryPlaceByNameOrIp(accouont,operationType,startTime,endTime);
						}
					}
		 		},
		 		"-",
		 		{
		 			xtype:'button',
						iconCls:'edit',
						text:"重置查询条件",
						listeners:{
							"click":function(){
								Ext.getCmp("entity.tbUser.accouont").setValue("");
								Ext.getCmp("entity.operationType").setValue("");
								Ext.getCmp("entity.startTime").setValue("");
								Ext.getCmp("entity.endTime").setValue("");
							}
						}
					}
          ]
	});
	
	Ext.getCmp("entity.operationType").setValue("-1");
	
	var serviceTable = Ext.create('Ext.grid.Panel',{
		id:'firstGrid',
    	autoHeight : true, 
    	renderTo:Ext.getBody(),
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		tbar:topTool,
		viewConfig: {forceFit:true}, 		//列自适应宽
		columns :cm,
		stripeRows: true,
		trackMouseOver : true ,		
		frame:true,
		store:Ext.data.StoreManager.lookup('simpsonsStore'),  //将数据源添加到grid中
		boder:true,
		autoHeight:true,
		listeners : {
			'itemdblclick' : function(ben,e,eopte) {
//				var sel = serviceTable.getSelectionModel().getSelection();
//				carryServiceIpToQuery(sel[0].get("id"),servicePlaceWin);
			}
		}, 
		bbar : new Ext.PagingToolbar({   //添加翻页工具条
			//pageSize : 10,   //页面容量
			id:"bottmPage",
		    store : Store,//Ext.data.StoreManager.lookup('simpsonsStore'),   //将数据源添加到分页的工具条中
		    displayInfo : true         //显示展示信息
		})
	});
	queryPlaceByNameOrIp(Ext.getCmp("entity.tbUser.accouont").getValue(),Ext.getCmp("entity.operationType").getValue(),Ext.util.Format.date(Ext.getCmp("entity.startTime").getValue(),'Y-m-d'),Ext.util.Format.date(Ext.getCmp("entity.endTime").getValue(),'Y-m-d'));
});