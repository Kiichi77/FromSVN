Ext.onReady(function () {
//	// 提交按钮
//	var saveButton = new Ext.Button({
//		text:"移除黑名单",
//		listeners:{
//			click:function(){
//				carryServiceIpToQuery(servicePlaceWin,serviceInfoPanel);
//			}
//		}
//	});
//	// 取消按钮
//	var cancleButton = new Ext.Button({
//		text:"取消",
//		listeners:{
//			click:function(){
//				servicePlaceWin.hide();
//			}			
//		}
//	});
//	//显示重新查询检测服务器信息
//	var serviceInfoPanel = Ext.create('Ext.form.Panel', {
//	    width: 380,
//	    height: 280,
//	    bodyPadding: 10,
//	    url:'',
//	    frame:true,	
//	    buttonAlign :'center',
//	    layout: {
//	        type: 'table',
//	        columns: 1
//	    },
//	    defaults: {frame: true, width: 270, height: 28},
//	    items: [
//				{xtype:"textfield",fieldLabel:"服务器内网IP",id:'inServiceIp',width:270},
//				{xtype:"textfield",fieldLabel:"服务器名称",id:'serviceNames',width:270},
//				{xtype:"textfield",fieldLabel:"服务器及时功耗",id:'serviceNowPwr',width:270},
//				{xtype:"textfield",fieldLabel:"服务器及时风口温度",id:'serviceWendu',width:270},
//				{xtype:"textfield",fieldLabel:"电源状态",id:'servicePwerStats',width:270},
//				{xtype:"textfield",fieldLabel:"风扇冗余",id:'serviceFengshan',width:270}
//			]	
//	});
//	//弹出的设置服务器位置的窗口
//	var servicePlaceWin = Ext.create('Ext.Window', {
//         title: '设置位置信息',
//         width: 380,
//         id:'rePlayWin',
//         height: 280,
//         hidden:true,
//         constrain:true,	//弹出窗不能超过父窗口的范围
//         modal:true,
//         closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
//         x: 320,
//         y: 220,
//         //constrainHeader: true,
//         layout: 'fit',
//         items: [serviceInfoPanel],
//         buttons: [saveButton,cancleButton]
//     })
	/************************************************************************************************************************************/
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				"内网IP:",
				{xtype:"textfield",fieldLabel:"",id:'serverEntity.serverIpIn',width:200,maxLength:20},
				"-",
		        "服务器名称:",
				{xtype:"textfield",fieldLabel:"",id:'serverEntity.serverIpOut',width:200,maxLength:20},
				"-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							var placeName = Ext.getCmp("serverEntity.serverIpOut").getValue();
							var placeLevel = Ext.getCmp("serverEntity.serverIpIn").getValue();
							queryPlaceByNameOrIp(placeName,placeLevel);
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
							Ext.getCmp("serverEntity.serverIpOut").setValue("");
							Ext.getCmp("serverEntity.serverIpIn").setValue("");
						}
					}
		 		}
          ]
	});
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
//				carryServiceIpToQuery(sel[0].get("id"),servicePlaceWin);
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