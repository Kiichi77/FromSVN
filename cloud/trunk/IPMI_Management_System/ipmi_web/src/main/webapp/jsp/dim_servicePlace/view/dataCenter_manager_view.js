Ext.onReady(function () {
//	提交按钮
	var submitButtontree = new Ext.Button({
		text:"确认",
		listeners:{
			click:function(){
				Ext.getCmp("placeInfoTreePanel").hide();
			}
		}
	});
	//位置树的详情结构图
	var serviceWhereTreePanel = Ext.create('Ext.tree.Panel', {
		width: 300,
		defaults: { // defaults are applied to items, not the container
			expanded: true
		},
		height: 400,
		store: treeStore,
		id : "servertree",
		rootVisible: false,
		useArrows : true,
		autoScroll:true,
		frame:true,
		listeners: {
			'checkchange':function(node,checked,optes) {
//				checkChangeCkeck(node,checked);
			}
		}
	});
	 //位置详情窗口
	var addUser=Ext.create('Ext.window.Window', {
		closeAction:'hide',
		id:'placeInfoTreePanel',
		hidden:true,
		constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
		title: '服务器位置层级结构',
		height: 500,
		buttonAlign :'center',
		width: 300,
		layout: 'fit',
		border: false,
		items: [serviceWhereTreePanel],
        buttons: [submitButtontree]
	});
	/*********************************************************************************************************************/
	createNewWin();
	/************************************************************************************************************************************/
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				{
					xtype:'button',
					text:"新增",
					iconCls:'add',
					listeners:{
						"click":function(){
							if(null == Ext.getCmp("servicePlaceWins")) {
								createNewWin();
							}
							Ext.getCmp("servicePlaceWins").show();
						}
					}
				},
				"-",
				{
					xtype:'button',
					text:"删除",
					iconCls:'delete',
					listeners:{
						"click":function(){
							deleteServiceByIp(null);
						}
					}
				},
				"-",
		        "位置名称:",
				{xtype:"textfield",fieldLabel:"",id:'pName',width:200,maxLength:40},
				"-",
				"位置类型",
				{
	                   xtype: 'combobox',
	                   fieldLabel: '',
	                   store:placeType,
	                   editable:false,
	                   queryMode: 'local',
	                   displayField: 'ids',
	                   valueField: 'names',
	                   name:'pwerId',
	                   id:'pwrStatas',
	                   width:120
	            },
				"-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							queryPlaceByNameOrIp();
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
							Ext.getCmp("pName").setValue("");
							Ext.getCmp("pwrStatas").setValue("");
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
				queryServicePlaceInfo(sel[0].get("id"));
				//根据服务器IP查询并跳转至详情界面
				//参数:IP
			}
		}, 
		bbar : new Ext.PagingToolbar({   //添加翻页工具条
		    pageSize : thePublicPageSize,   //页面容量
		    store : Ext.data.StoreManager.lookup('simpsonsStore'),   //将数据源添加到分页的工具条中
		    displayInfo : true         //显示展示信息
		})
	});
	var placeName = Ext.getCmp("pName").getValue();
	var placeLevel = Ext.getCmp("pwrStatas").getValue();
	Ext.getCmp("pwrStatas").setValue("-1");
	queryPlaceByNameOrIp(placeName,placeLevel);
//	Ext.getCmp("pwrStatas").setValue("-1");
});