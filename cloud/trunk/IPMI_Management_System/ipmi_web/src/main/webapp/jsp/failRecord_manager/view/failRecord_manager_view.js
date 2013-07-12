Ext.onReady(function () {
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				"失败原因:",
				{
	                   xtype: 'combobox',
	                   fieldLabel: '',
	                   store:failReason,
	                   editable:false,
	                   queryMode: 'local',
	                   displayField: 'ids',
	                   valueField: 'names',
	                   name:'failreason',
	                   id:'model.failReason',
	                   width:120
	            },
	            "-",
	    		{
	    			xtype:'button',
	    			text:"删除",
	    			iconCls:'delete',
	    			listeners:{
	    				"click":function(){deleteUser(serviceTable);}
	    			}
	    		},
				"-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							var failReason = Ext.getCmp("model.failReason").getValue();
							queryPlaceByNameOrIp(failReason);
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
							Ext.getCmp("model.failReason").setValue("");
						}
					}
		 		}
          ]
	});
	Ext.getCmp("model.failReason").setValue("-1");
	var serviceTable = Ext.create('Ext.grid.Panel',{
		id:'firstGrid',
    	autoHeight : true, 
    	renderTo:Ext.getBody(),
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		tbar:topTool,
		selModel : {
			 selType : 'checkboxmodel'
		},multiSelect : true,
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
				var sel = serviceTable.getSelectionModel().getSelection();
//				queryNumById(sel[0].get("serviceIp"));
				//根据服务器IP查询并跳转至详情界面
				//参数:IP
				carryServiceIpToQuery(sel[0].get("id"),servicePlaceWin);
			}
		}, 
		bbar : new Ext.PagingToolbar({   //添加翻页工具条
		    pageSize : thePublicPageSize,   //页面容量
		    store : Ext.data.StoreManager.lookup('simpsonsStore'),   //将数据源添加到分页的工具条中
		    displayInfo : true         //显示展示信息
		})
	});
	queryPlaceByNameOrIp();
});