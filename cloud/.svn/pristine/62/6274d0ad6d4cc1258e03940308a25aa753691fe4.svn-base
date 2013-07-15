Ext.onReady(function () {
	// 提交按钮
	var saveButton = new Ext.Button({
		text:"保存",
		listeners:{
			click:function(){
				saveOrUpdateAlert();
			}
		}
	});
	// 取消按钮
	var cancleButton = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){
				servicePlaceWin.hide();
			}			
		}
	});
	//服务器所在位置的树形控件
	var serviceWhereTreePanel = Ext.create('Ext.tree.Panel', {
		width: 300,
		height: 300,
		store: treeStore,
		id : "servertree",
		rootVisible: false,
		useArrows : true,
		autoScroll:true,
		frame:true,
		listeners: {
			'checkchange':function(node,checked,optes) {
				checkChangeCkeck(node,checked);
			}
		}
	});
	
		
	//显示重新查询检测服务器信息
	var serviceInfoPanel = Ext.create('Ext.form.Panel', {
	    width: 680,
	    height: 400,
	    bodyPadding: 10,
	    frame:true,	
	    id:'operAlertForm',
	    buttonAlign :'center',
	    layout: {
	        type: 'column',
	        columns: 1
	    },
	    defaults: {frame: true},
	    items: [{
	    	columnWidth: 1/2,  
	    	baseCls:'x-plain',  
	    	bodyStyle:'padding:21px 0 5px 5px',  
	    	items:[{
	    		xtype:'panel',
	    		height:300,
	    		frame:true,	
	    		items:[{xtype:"textfield",fieldLabel:"id",id:'alertId',width:270,allowBlank: true,value:'0',hidden:true},//hidden:true,
	    		       Ext.create('Ext.form.ComboBox',{
				       xtype: 'combobox',
				       fieldLabel: '筛选类型',
				       store:filterTypeAdd,
				       queryMode: 'local',
				       displayField: 'ids',
				       editable:false,
				       valueField: 'names',
				       allowBlank: false,
				       name:'name_filter_type',
				       id:'filter_type',
				       listeners:{
							'select':function(params){
								if("1" == params.getValue()) {
									Ext.getCmp("serious_trigger_limit").setFieldLabel("严重阀值(W)");
									Ext.getCmp("alert_trigger_limit").setFieldLabel("告警阀值(W)");
								} else if ("2" == params.getValue()) {
									Ext.getCmp("serious_trigger_limit").setFieldLabel("严重阀值(℃)");
									Ext.getCmp("alert_trigger_limit").setFieldLabel("告警阀值(℃)");
								}
							}
						}
					}).setValue("1"),
					{		
		    		 	xtype:"textfield",
	    	    		fieldLabel:"严重阀值(W)",
	    	    		id:'serious_trigger_limit',
	    	    		width:253,
	    	    		allowBlank: false,
	    	    		regex : /^\d+$/,
	    	    		maxLength:5
					}, {
	    	    		xtype:"textfield",
	    	    		fieldLabel:"警告阀值(W)",
	    	    		id:'alert_trigger_limit',
	    	    		width:253,
	    	    		allowBlank: false,
	    	    		regex : /^\d+$/,
	    	    		maxLength:5
	    	    	  }/*,{
	    	    		 width:260,
	    	    		 xtype: 'fieldset',
	    	    		 title: '',
	    	    		 height:56,
 	                	 frame:true,
 	                	 layout: {
	 	           	        type: 'column',
	 	           	        columns: 2
	 	           	     },
 	                     items : [{
 	                    	columnWidth: 1/2,  
	 	           	    	baseCls:'x-plain',  
	 	           	    	bodyStyle:'padding:0px 0 0px 0px',  
	 	           	    	items:[ new Ext.form.field.Checkbox({
	    	                     boxLabel : '开启严重',
	    	                     name : 'startSerious',
	    	                     inputValue : '0',
	    	                     checked : true
	    	                 })]
 	                     },{
 	                    	columnWidth: 1/2,  
	 	           	    	baseCls:'x-plain',  
	 	           	    	bodyStyle:'padding:0px 0 0px 0px',  
	 	           	    	items:[new Ext.form.field.Checkbox({
	    	                     boxLabel : '开启警告',
	    	                     name : 'endAlert',
	    	                     inputValue : '1',
	    	                     checked : false})]
 	                     }]
	    	         }*/,{
					       xtype: 'combobox',
					       fieldLabel: '告警邮件联系人',
					       store:userStore,
					       editable:false,
					       queryMode: 'local',
					       displayField: 'accouont',
					       valueField: 'id',
					       allowBlank: false,
					       name:'name_accouont',
					       id:'accouont',
					       listeners:{
								select:function(aa){
									checkTypeShowText(aa.getValue());
								}
							}
					}]
	    	}]

	    },{
	    	columnWidth: 1/2,  
	    	baseCls:'x-plain',  
	    	bodyStyle:'padding:5px 0 5px 5px',  
	    	items:[{
	        	xtype: 'label',
	        	id:'labeltext',
	        	html: '请选择服务器:'
	        },serviceWhereTreePanel]
	    }]	
	});
	//弹出的设置服务器位置的窗口
	var servicePlaceWin = Ext.create('Ext.Window', {
         title: '设置报警信息',
         width: 680,
         id:'rePlayWin',
         height: 410,
         hidden:true,
         constrain:true,	//弹出窗不能超过父窗口的范围
         modal:true,
         closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
         x: 320,
         y: 220,
         //constrainHeader: true,
         layout: 'fit',
         items: [serviceInfoPanel],
         buttons: [saveButton,cancleButton]
     })
     
	/************************************************************************************************************************************/
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				{
					xtype:'button',
					text:"新增",
					iconCls:'add',
					listeners:{
						"click":function(){
							Ext.getCmp("servertree").enable();
							serviceInfoPanel.form.reset();
							var selNodes = Ext.getCmp("servertree").getChecked()
							if(0!=selNodes.length) {
								Ext.each(selNodes, function(nodes){
									nodes.set("checked", false);
							    });
							}
							servicePlaceWin.show();
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
							deleteById(serviceTable);
						}
					}
				},
				"筛选器类型",
				{
			       xtype: 'combobox',
			       fieldLabel: '',
			       store:filterType,
			       queryMode: 'local',
			       displayField: 'ids',
			       editable:false,
			       valueField: 'names',
			       name:'type',
			       id:'filterType',
			       width:120
				},
				"是否启用",
				{
			       xtype: 'combobox',
			       fieldLabel: '',
			       store:serious,
			       queryMode: 'local',
			       editable:false,
			       displayField: 'ids',
			       valueField: 'names',
			       name:'enable',
			       id:'isEnable',
			       width:120
				},
				"内网IP:",
				{xtype:"textfield",fieldLabel:"",id:'tbServer.serverIpIn',width:200},
				"-",
		        "用户名:",
				{xtype:"textfield",fieldLabel:"",id:'tbUsers.accouont',width:200},
				"-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							var filterType = Ext.getCmp("filterType").getValue();
							var isEnable = Ext.getCmp("isEnable").getValue();
							var serverIpIn = Ext.getCmp("tbServer.serverIpIn").getValue();
							var accouont = Ext.getCmp("tbUsers.accouont").getValue();
							queryPlaceByNameOrIp(filterType,isEnable,serverIpIn,accouont);
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
							Ext.getCmp("filterType").setValue("");
							Ext.getCmp("serious").setValue("");
							Ext.getCmp("tbServer.serverIpIn").setValue("");
							Ext.getCmp("tbUsers.accouont").setValue("");
						}
					}
		 		}
          ]
	});
	Ext.getCmp("filterType").setValue('-1');
	Ext.getCmp("isEnable").setValue('-1');
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
	queryPlaceByNameOrIp(Ext.getCmp("filterType").getValue(),Ext.getCmp("isEnable").getValue(),Ext.getCmp("tbServer.serverIpIn").getValue(),Ext.getCmp("tbUsers.accouont").getValue());
});