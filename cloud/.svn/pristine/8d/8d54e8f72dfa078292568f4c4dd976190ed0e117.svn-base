Ext.onReady(function() {
	/*********************************************先定义好下拉框*******************************************************/
	//策略类型下拉框
	var policyTypecombo=Ext.create('Ext.form.ComboBox', {
		store: policyTypeStore,	
		queryMode: 'local',
		width:180,
		allowBlank: false,
		id:'policyType',
		displayField: 'names',
		valueField: 'ids',
		editable:false
	});

	// 策略类型下拉框2弹出窗口用
	var policyTypecombo2=Ext.create('Ext.form.ComboBox', {
		labelAlign :"left	",
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
			select:function(aa){
				checkTypeShowText(aa.getValue());
			}
		}
	});
	/*********************************************策略信息设置窗口start************************************************/
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

	/**
	 * 新增窗口开始时间panel
	 */		
	var timePanel = Ext.create('Ext.panel.Panel', {
		labelAlign :"left",
		width: 220,
		frame:true,
		layout: {
			type: 'table',
			columns: 5
		},
		items: [{
			xtype: 'label',
			html: '<font size="2">开始时间:</font>'
		},
		{
			xtype: 'combobox',
			fieldLabel: '',
			store:hours,
			width:50,
			queryMode: 'local',
			displayField: 'ids',
			allowBlank: false,
			editable:false,
			valueField: 'names',
			id:'shour',
			listeners:{
				'select':function(aa){
					shour=aa.getValue();
				}
			}
		}, {
			xtype: 'label',
			html: '<font size="2">时-</font>'
		},{
			xtype: 'combobox',
			fieldLabel: '',
			store:minins,
			width:50,
			queryMode: 'local',
			displayField: 'ids',
			valueField: 'names',
			allowBlank: false,editable:false,
			id:'sminute',
			listeners:{
				'select':function(aa){
					sminute=aa.getValue();
				}
			}
		}, {
			xtype: 'label',
			html: '<font size="2">分</font>'
		},{
			xtype: 'label',
			html: '<font size="2">结束时间:	'
		},
		{
			xtype: 'combobox',
			fieldLabel: '',
			store:hours,
			width:50,
			queryMode: 'local',
			displayField: 'ids',
			allowBlank: false,
			editable:false,
			valueField: 'names',
			id:'ehour',
			listeners:{
				'select':function(aa){
					ehour=aa.getValue();
				}
			}
		}, {
			xtype: 'label',
			html: '<font size="2">时-</font>'
		},{
			xtype: 'combobox',
			fieldLabel: '',
			store:minins,
			width:50,	
			queryMode: 'local',
			displayField: 'ids',
			valueField: 'names',
			allowBlank: false,	        	editable:false,
			id:'eminute',
			listeners:{
				'select':function(aa){
					eminute=aa.getValue();
				}
			}
		}, {
			xtype: 'label',
			html: '<font size="2">分</font>'
		}
		]
	});

	var serviceInfoPanel = Ext.create('Ext.form.FormPanel', {
		id:'form1',
		width: 280,
		height: 260,
		bodyPadding: 1,
		url:'',
		frame:true,	
		buttonAlign :'center',
		layout: {
			type: 'table',
			columns: 1
		},
		items: [ 
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"id",id:'addid',hidden:true},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略名称",id:'name',width:210,allowBlank: false,maxLength:20},
		        policyTypecombo2,
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略功耗阀值(W)",id:'limitadd',width:210,allowBlank: false,vtype:'num',maxLength:5},  
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略期望数值(W)",id:'numberaadd',width:210,allowBlank: true,vtype:'num',maxLength:5},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略温度阀值(℃)",id:'downlimit',width:210,allowBlank: true,vtype:'num',maxLength:2},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"功耗下降(%)",id:'down',width:210,vtype:'num',maxLength:2},
		     
		        timePanel
		        ],
		        buttons:[submitButton,resetButton]
	});
	/**
	 * 下面是东神的窗口相关
	 */
	var addUser=Ext.create('Ext.Window', {
		closeAction:'hide',
		id:'addwin',
		constrain:true,	//弹出窗不能超过父窗口的范围
		modal:true,
		title: '设置策略',
		height: 280,
		width: 240,
		layout: 'fit',
		border: false,
		items:[serviceInfoPanel],
		listeners:{
			beforehide:function(obj,opt){
				clearFormAll();
			}
		}
	});
	/*********************************************策略信息设置窗口end************************************************/
	/*********************************************策略绑定主机窗口start************************************************/
//	提交按钮
	var submitButton2 = new Ext.Button({
		text:"提交",
		listeners:{
			click:function(){
				savePolicyServer();
			}
		}
	});
//	取消按钮
	var resetButton2 = new Ext.Button({
		text:"取消",
		listeners:{
			click:function(){Ext.getCmp('setwin').hide();}			
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
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略id",id:'idset',hidden:true},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"策略名称",id:'nameset',width:210,allowBlank: false},
		        {xtype:"textfield",labelAlign :"left",fieldLabel:"IP网段或IP",id:'ipset',width:210,allowBlank: false},
		        {xtype: 'label',html: '<font size="2" color="red">IP文本框可以填写具体IP或者IP段,只要与数据库服务器匹配就行</font>'}
		        ],
		        buttons:[submitButton2,resetButton2]
	});

	// 绑定主机窗口
	var addUser=Ext.create('Ext.window.Window', {
		closeAction:'hide',
		id:'setwin',
		constrain:true,	//弹出窗不能超过父窗口的范围
		modal:true,
		title: '策略绑定服务器',
		height: 170,
		width: 260,
		layout: 'fit',
		border: false,
		listeners:{
			beforehide:function(obj,opt){
				Ext.getCmp('ipset').setValue('');
			}
		},
		items:[tiePanel]
	});
	/*********************************************策略绑定主机窗口end************************************************/
	/*********************************************策略列表表单start************************************************/


	// 工具条
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[{
			xtype:'button',
			text:"新增策略",
			iconCls:'add',
			listeners:{
				"click":function(){Ext.getCmp('addwin').show();}
			}
		},"-",
		{
			xtype:'button',
			text:"删除",
			iconCls:'delete',
			listeners:{
				"click":function(){deleteUser(griduser);}
			}
		},
		"策略名称：",
		{xtype:"textfield",fieldLabel:"",id:'policynameforsearch',width:200},
		"-",
		"策略类型:",
		policyTypecombo,
		"-",
		{
			xtype:'button',
			text:"查询",
			iconCls:'search_16x16',
			listeners:{
				"click":function(){queryUserList();}
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
					Ext.getCmp("policyType").setValue("");
				}
			}
		}]
	});
	queryUserList();

	//大神东的列表
	var griduser=Ext.create('Ext.grid.Panel', {
		autoWidth:true,
		autoHeight:true,
		tbar:topTool,
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		renderTo: Ext.getBody(),
		store: policyStore,
		selModel : {
			selType : 'checkboxmodel'
		},
		multiSelect : true,
		columns: [
		          { header: 'ID',  dataIndex: 'id',hidden:true},
		          { header: '策略名称',  dataIndex: 'policyName' ,flex:1},
		          { header: '策略开始时间', dataIndex: 'policyStartTime', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  var returnstr='';
		        	  var s=rec.get('policyStartHour');
		        	  var e=rec.get('policyStartMinute');
		        	  if(s<10)s="0"+s;
		        	  if(e<10)e="0"+e;
		        	  returnstr= s+":"+e;
		        	  return returnstr;
		          }},
		          { header: '策略结束时间', dataIndex: 'policyStartTime', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  var returnstr='';
		        	  var s=rec.get('policyEndHour');
		        	  var e=rec.get('policyEndMinute');
		        	  if(s<10)s="0"+s;
		        	  if(e<10)e="0"+e;
		        	  returnstr= s+":"+e;
		        	  return returnstr;
		          }},
		          { header: '策略类型', dataIndex: 'policyType', flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  if(rec.get('policyType')==0)return '全部';
		        	  if(rec.get('policyType')==1)return '电源功耗控制';
		        	  if(rec.get('policyType')==2)return 'CPU功耗控制';
		        	  if(rec.get('policyType')==3)return '内存功耗控制';
		        	  if(rec.get('policyType')==4)return '进风口温度控制';
		          }},
		          { header: '策略阀值', dataIndex: 'policyLimit' ,flex:1},
		          { header: '策略预期数值', dataIndex: 'policyBumber' ,flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  if(rec.get('policyType')==4)
		        		  {return '下降'+rec.get('policyBumber')+'%';}
		        	  else return rec.get('policyBumber');
		          }},
		          { header: '操作', dataIndex: '',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  return '<a href="#" onclick="openSetIpWin(\''+rec.get('id')+','+rec.get('policyName')+'\')">'+"绑定主机"+'</a>|'
		        	  +'<a href="#" onclick="modify(\''+rec.get('id')+','+rec.get('policyName')+','+rec.get('policyStartHour')
		        	  +','+rec.get('policyStartMinute')+','+rec.get('policyType')+','+rec.get('policyLimit')+','+rec.get('policyEndMinute')+
		        	  ','+rec.get('policyEndHour')+','+rec.get('policyBumber')+'\')">'+"修改"
		        	  +'</a>|<a href="#" onclick="deleteOneUser(\''+rec.get('id')+'\')">'+"删除"
		        	  +'</a>';
		          }}	
		          ],
		          bbar : new Ext.PagingToolbar({   //添加翻页工具条
		        	  store : policyStore,   //将数据源添加到分页的工具条中
		        	  displayInfo : true         //显示展示信息
		          })
	});
	/*********************************************策略列表表单end************************************************/
	if(Ext.getCmp('policyType2').getValue()==null){
		Ext.getCmp('downlimit').setVisible(false);
		Ext.getCmp('down').setVisible(false);
	}
});