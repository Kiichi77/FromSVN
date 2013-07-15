var wietinLoad = null;
Ext.onReady(function () {
	
	// 提交按钮
	var saveButton = new Ext.Button({
		text:"确定",
		listeners:{
			click:function(){
				comfirmTreeNode();
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
	    height: 400,
	    store: treeStore,
	    id : "serviceTreePanel",
	    rootVisible: false,
	    useArrows : true,
	    autoScroll:true,
	    frame:true,
	    listeners: {
	    	'checkchange':function(node,checked,optes) {
	    		checkChangeCkeckOnlyOne(node,checked);
	        }
	    }
	});
	//弹出的设置服务器位置的窗口
	var servicePlaceWin = Ext.create('Ext.Window', {
        title: '服务器位置',
        width: 340,
        height: 400,
        hidden:true,
        constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
        id:"placeWin",
        closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
        x: 20,
        y: 10,
        //constrainHeader: true,
        layout: 'fit',
        items: [serviceWhereTreePanel],
        buttons: [saveButton,cancleButton]
    });
/******************************************************************************************************/
	// 提交按钮
	var submitButton = new Ext.Button({
		text:"提交",
		listeners:{
			click:function(){
				saveServiceInfo(serviceInfoPanel);
			}
		}
	});
	// 取消按钮
	var resetButton = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){
				//取消保存选择的服务器位置，并隐藏窗口
				Ext.getCmp("serviceInfoSetWin").hide();
			}			
		}
	});
	// 设置服务器信息的容器以及相应控件
	var serviceInfoPanel = Ext.create('Ext.form.Panel', {
		id:"theServiceInfoPanels",
	    width: 330,
	    height: 420,
//	    bodyPadding: 10,
	    frame:true,	
	    buttonAlign :'center',
	    layout: {
	        type: 'column'
	    },
	    items: [ {
	    			frame:true,
	    			columnWidth: 10 / 10,        //设置列的宽度
	    			items:[{xtype:"textfield",fieldLabel:"",id:'palceNodeId',width:170,hidden:true},
					{xtype:"textfield",fieldLabel:"",id:'nowServicePlaceIds',width:170,hidden:true},  
		            {xtype:"textfield",fieldLabel:"服务器名称",id:'serviceName',width:270,labelWidth:80,allowBlank: false,maxLength:20},
		            {xtype:"textfield",fieldLabel:"系统型号",id:'operateSystem',width:270,labelWidth:80,labelWidth:80,allowBlank: false,maxLength:20},  
		            {xtype:"textfield",fieldLabel:"服务器外网IP",id:'outServiceIp',width:270,labelWidth:80,maxLength:20}, 
		            {xtype:"textfield",fieldLabel:"服务器内网IP",id:'inServiceIp',width:270,labelWidth:80,allowBlank: false,maxLength:20}, 
		            {xtype:"textfield",fieldLabel:"IDRACIP",id:'idRacip',width:270,labelWidth:80,allowBlank: false,maxLength:20,
		            	listeners:{
		            		blur:function(){
								checkIdracInfo();
							}
		            	}
		            }, 
		            {xtype:"textfield",fieldLabel:"IDRAC用户",id:'idRacipName',width:270,labelWidth:80,allowBlank: false,maxLength:20,
		            	listeners:{
		            		blur:function(){
								checkIdracInfo();
							}
			            }
		            }, 	
		            {xtype:"textfield",fieldLabel:"IDRAC密码",id:'idRacipPwd',width:270,labelWidth:80,allowBlank: false,inputType:'password',maxLength:20,
		            	listeners:{
		            		blur:function(){
								checkIdracInfo();
							}
		            	}
		            },
		            {xtype:"textfield",fieldLabel:"快速服务代码",id:'queikCode',width:270,labelWidth:80,allowBlank: false,vtype:'num',maxLength:40},
		            {
						xtype:'button',
						text:"设置服务器位置",
						width:160,
						labelWidth:90,
						iconCls:'search_16x16',
						listeners:{
							"click":function(){
								showAndQueryServicePlace(servicePlaceWin,serviceWhereTreePanel);
							}
						}
		            },{
		                xtype     : 'textareafield',
		                grow      : true,
		                id:'remark',
		                width:270,
		                labelWidth:80,
		                height:50,
		                name      : 'remark',
		                maxLength : 100,   //设置多行文本框的最大长度为100
		                fieldLabel: '备注',
		                preventScrollbars : true,   //设置多行文本框没有滚动条显示 
		                anchor    : '100%'
		            },{
		                xtype     : 'textareafield',
		                grow      : true,
		                width:270,
		                labelWidth:80,
		                height:80,
		                autoScroll :true,
		                id:'nowWhere',
		                readOnly : true,
		                allowBlank: false,
		                maxLength : 300,   //设置多行文本框的最大长度为100
		                name      : 'nowWhere',
		                preventScrollbars : true,   //设置多行文本框没有滚动条显示
		                fieldLabel: '服务器当前位置',
		                anchor    : '100%'
		            }]
	    	}],
	    buttons:[submitButton,resetButton]
	});
	
	//弹出的设置服务器位置的窗口
	var serviceInfoSetWin = Ext.create('Ext.Window', {
        title: '服务器位置',
        width: 320,
        height: 460,
        hidden:true,
        constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
        id:"serviceInfoSetWin",
        closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
        x: 200,
        y: 100,
        //constrainHeader: true,
        layout: 'fit',
        items: [serviceInfoPanel],
        listeners:{
        	beforehide:function(obj,opt){
        		clearAllWirte();
        	}
        }
    });
//	提交按钮
	var submitButton2 = new Ext.Button({
		text:"提交",
		listeners:{
			click:function(){
				iprange();
			}
		}
	});
//	取消按钮
	var resetButton2 = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){Ext.getCmp('dongwin').hide();}			
		}
	});
//	后台执行按钮
	var baktaiButton = new Ext.Button({
		text:"后台执行",
		listeners:{
			click:function(){Ext.getCmp('dongwin').hide();}			
		}
	});

	//策略绑定主机PANEL
	var tiePanel = Ext.create('Ext.form.FormPanel', {
		id:'form2',
		width: 280,
		height: 170,
		bodyPadding: 1,
		frame:true,	
		buttonAlign :'center',
		layout: {
			type: 'table',
			columns: 1
		},
		items: [
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"通用user",id:'iuser',width:210,allowBlank: false},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"通用password",id:'ipassword',width:210,allowBlank: false},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"IP网段",id:'ipduan',width:210,allowBlank: false},
		        {xtype: 'label',html: '<font size="2" color="red">如果网段内的主机数过多,会花费比较长的时间,如果不多可以等待返回结果,多的话可以点击后台执行,过一段时间再刷新列表即可</font>'}
		        ],
		        buttons:[submitButton2,resetButton2]
	});
	
	//弹出IPRANGE的窗口
	var dongwin = Ext.create('Ext.Window', {
        title: '检索网段添加拥有IPMI的服务器',
        width: 320,
        height: 220,
        hidden:true,
        constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
        id:"dongwin",
        closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
        x: 200,
        y: 100,
        //constrainHeader: true,
        layout: 'fit',
        items: [tiePanel],
        listeners:{
        	beforehide:function(obj,opt){
        		
        	}
        }
    });
	

});