Ext.onReady(function() {
	//位置类型
	var stateStore = Ext.create('Ext.data.Store', {
		storeId:'placeType',
		autoLoad: true,
		fields:['ids','names'],
		data:{'items':[{'ids':'','names':'全部'},
		               {'ids':'1','names':'启用'},
		               {'ids':'2','names':'禁用'}
		               ]},
		               proxy: {
		            	   type: 'memory',
		            	   reader: {
		            		   type: 'json',
		            		   root: 'items'
		            	   }
		               }	
	}); 
	var policyTypeStore2 = Ext.create('Ext.data.Store', {
		storeId:'placeType',
		autoLoad: true,
		fields:['ids','names'],
		data:{'items':[
		               {'ids':'1','names':'电源功耗策略'},
		               {'ids':'2','names':'CPU功耗策略'},
		               {'ids':'3','names':'内存功耗策略'},
		               {'ids':'4','names':'进风口温度策略'}
		               ]},
		               proxy: {
		            	   type: 'memory',
		            	   reader: {
		            		   type: 'json',
		            		   root: 'items'
		            	   }
		               }
	}); 

	/**
	 * 策略下拉框
	 */
	var policycombo=Ext.create('Ext.form.ComboBox', {
		store: policyListStore,	
		queryMode: 'remote',
		width:180,
		allowBlank: false,
		id:'policyid',
		displayField: 'policyName',
		valueField: 'id',
		editable:false
	});
	/**
	 * 策略下拉框
	 */
	var policycombo2=Ext.create('Ext.form.ComboBox', {
		store: policyListStore,	
		queryMode: 'local',
		width:180,
		allowBlank: false,
		id:'policyid2',
		displayField: 'policyName',
		valueField: 'id',
		editable:false,
		listeners:{
			'select':function(aa){
				modifyid=aa.getValue();
			}
		}
	});

	/**
	 * 策略状态下拉框
	 */
	var statecombo=Ext.create('Ext.form.ComboBox', {
		labelAlign :"left",
		store: stateStore,	
		queryMode: 'local',
		width:180,
		allowBlank: false,
		id:'statecombox',
		displayField: 'names',
		valueField: 'ids',
		editable:false
	});

	/**
	 * 策略类型下拉框2弹出窗口用
	 */
	var policyTypecombo2=Ext.create('Ext.form.ComboBox', {
		labelAlign :"left",
		fieldLabel: '策略类型',
		store: policyTypeStore2,	
		queryMode: 'local',
		width:210,
		allowBlank: false,
		id:'policyType2',
		displayField: 'names',
		valueField: 'ids',
		editable:false,
		listeners:{
			select:function(){
				// alert(Ext.getCmp('policyType2').getValue());
				modifyid=Ext.getCmp('policyType2').getValue();
			} 
		}
	});

	/**
	 * 工具条
	 */
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[{
			xtype:'button',
			text:"绑定策略",
			iconCls:'add',
			listeners:{
				"click":function(){
					//window.location.href = '/jsp/policy_manager/policy_manager';
					Ext.getCmp('policyid').setValue('');
					Ext.getCmp('addwin').show();
				}
			}
		},"-",
		{
			xtype:'button',
			text:"删除",
			iconCls:'delete',
			listeners:{
				"click":function(){
					deleteUser(griduser);
				}
			}
		},
		"策略名称：",
		{xtype:"textfield",fieldLabel:"",id:'policynameforsearch',width:200,maxLength:20},
		"-",
		"内网IP:",
		{xtype:"textfield",fieldLabel:"",id:'ipin',width:200,maxLength:20},
		"-",'状态',
		statecombo.setValue(''),
		"-",
		{
			xtype:'button',
			text:"查询",
			iconCls:'search_16x16',
			listeners:{
				"click":function(){
					policyStore.load({			
						params:{
							start:0,
							limit:thePublicPageSize,
							'model.tbPolicy.policyName':Ext.getCmp("policynameforsearch").getValue(),
							'model.tbServer.serverIpIn':Ext.getCmp("ipin").getValue(),
							'model.biningState':Ext.getCmp("statecombox").getValue()
						}
					});	
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
						Ext.getCmp("policynameforsearch").setValue("");
						Ext.getCmp("ipin").setValue("");
						Ext.getCmp("statecombox").setValue("");
					}
				}
			}
		]
	});
	queryUserList();
	/**
	 * 大神东的列表
	 */
	var griduser=Ext.create('Ext.grid.Panel', {
		autoWidth:true,
		autoHeight:true,
		tbar:topTool,
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		tbar:topTool,
		renderTo: Ext.getBody(),
		store: policyStore,
		selModel : {
			selType : 'checkboxmodel'
		},
		multiSelect : true,
		columns: [
		          { header: 'ID',  dataIndex: 'id' ,hidden:true},
		          { header: '服务器内网IP',  dataIndex: 'tbServer.serverIpIn' ,flex:1},
		          { header: '服务器名称',  dataIndex: 'tbServer.serverName' ,flex:1},
		          { header: '策略状态',  dataIndex: 'biningState' ,flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  var s=rec.get('biningState');
		        	  if(s==1)return '激活';
		        	  else return '待激活';
		          }},
		          { header: '策略名称',  dataIndex: 'tbPolicy.policyName' ,flex:1},
		          { header: '策略开始时间', dataIndex: 'policyStartTime', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  var returnstr='';
		        	  var s=rec.get('tbPolicy.policyStartHour');
		        	  var e=rec.get('tbPolicy.policyStartMinute');
		        	  if(s<10)s="0"+s;
		        	  if(e<10)e="0"+e;
		        	  returnstr= s+":"+e;
		        	  return returnstr;
		          }},
		          { header: '策略结束时间', dataIndex: 'policyStartTime', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  var returnstr='';
		        	  var s=rec.get('tbPolicy.policyEndHour');
		        	  var e=rec.get('tbPolicy.policyEndMinute');
		        	  if(s<10)s="0"+s;
		        	  if(e<10)e="0"+e;
		        	  returnstr= s+":"+e;
		        	  return returnstr;
		          }},
		          { header: '服务器策略编号', dataIndex: 'policyidInServer', flex:1},
		          { header: '策略类型', dataIndex: 'tbPolicy.policyType', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  if(rec.get('tbPolicy.policyType')==1)return '电源功耗策略';
		        	  if(rec.get('tbPolicy.policyType')==2)return 'CPU功耗策略';
		        	  if(rec.get('tbPolicy.policyType')==3)return '内存功耗策略';
		        	  if(rec.get('tbPolicy.policyType')==4)return '进风口温度策略';
		          }},
		          { header: '策略预期数值', dataIndex: 'tbPolicy.policyLimit' ,flex:1},
//		          { header: '策略触发器', dataIndex: 'policyTrigger', flex:1},
//		          { header: '策略命令', dataIndex: 'command' ,flex:1},
		          //<a href="#" onclick="modify(\''+rec.get('id')+','+rec.get('tbPolicy.policyName')+','+rec.get('tbPolicy.id')+'\')">'+"修改"
		          { header: '操作', dataIndex: '',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  return '<a href="#" onclick="deleteOneUser(\''+rec.get('id')+'\')">'+"删除"
		        	  +'</a>';
		          }}	
		          ],
		          bbar : new Ext.PagingToolbar({   //添加翻页工具条
		        	  store : policyStore,   //将数据源添加到分页的工具条中
		        	  displayInfo : true         //显示展示信息
		          })
	});




//	提交按钮
	var submitButton = new Ext.Button({
		text:"提交",
		listeners:{
			click:function(){
				saveServiceInfo();
			}
		}
	});
//	取消按钮
	var resetButton = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){Ext.getCmp('addwin').hide();}			
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


	/**
	 * 新增窗口属性选择服务器panel
	 */		
	var treePanel = Ext.create('Ext.panel.Panel', {
		labelAlign :"left",
		width: 320,
		frame:true,
		layout: {
			type: 'column',
			columns: 1
		},
		items: [	
		        {
		        	xtype: 'label',
		        	html: '策略:'
		        },
		        policycombo,
		        serviceWhereTreePanel,

		        ],buttons:[submitButton,resetButton]
	});




	/**
	 * 下面是东神的窗口相关
	 */
	var addUser=Ext.create('Ext.window.Window', {
		closeAction:'hide',
		id:'addwin',
		title: '设置服务器策略',
		height: 400,
		width: 320,
		constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
		layout: 'fit',
		border: false,
		listeners:{
			beforehide:function(obj,opt){
				treeStore.load();
			}
		}
	}).add(treePanel);

//	提交按钮
	var submitButton1 = new Ext.Button({
		text:"提交",
		listeners:{
			click:function(){
				update();
			}
		}
	});
//	取消按钮
	var resetButton1 = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){Ext.getCmp('modifywin').hide();}			
		}
	});




	/**
	 * 新增窗口属性选择服务器panel
	 */		
	var modifyPanel = Ext.create('Ext.panel.Panel', {
		labelAlign :"left",
		width: 400,
		frame:true,
		layout: {
			type: 'column',
			columns: 1
		},
		items: [
		        {xtype:"textfield",fieldLabel:"id",id:'addid',width:100,allowBlank: false,hidden:true},
		        {
		        	xtype: 'label',
		        	html: '策略:'
		        },
		        policycombo2
		        ],buttons:[submitButton1,resetButton1]
	});




	/**
	 * 下面是东神的窗口相关
	 */
	var modifyUser=Ext.create('Ext.window.Window', {
		closeAction:'hide',
		id:'modifywin',
		constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
		title: '修改服务器策略',
		height: 100,
		width: 250,
		layout: 'fit',
		border: false
	}).add(modifyPanel);
})