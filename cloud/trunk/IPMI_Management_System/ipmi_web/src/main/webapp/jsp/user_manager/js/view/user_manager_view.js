Ext.onReady(function() {
	/**
	 * 角色下拉框
	 */
	var rolecombo=Ext.create('Ext.form.ComboBox', {
		fieldLabel: '角色',
		store: rolestore,	
		queryMode: 'local',
		editable:false,
		width:270,
		displayField: 'rolename',
		allowBlank: false,
		id:'role1',
		valueField: 'id',
		listeners:{
			 select:function(){
				 rolIds=Ext.getCmp('role1').getValue();
			 } 
		}
	});
	/**
	 * 角色下拉框2
	 */
	var rolecombo2=Ext.create('Ext.form.ComboBox', {
		store: rolestore,	
		queryMode: 'local',
		width:200,
		editable:false,
		displayField: 'rolename',
		id:'role2',
		valueField: 'id'
	});

	/**
	 * 工具条
	 */
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[{
			xtype:'button',
			text:"新增用户",
			iconCls:'add',
			listeners:{
				"click":function(){
					Ext.getCmp('addid').setValue('');
					var name=Ext.getCmp('name').setValue('');
					var accounaddt=Ext.getCmp('accountadd').setValue('');
					accounaddt.enable();
					var emailadd=Ext.getCmp('emailadd').setValue('');
					var passwordadd=Ext.getCmp('passwordadd').setValue('');
					passwordadd.enable();
					var phoneadd=Ext.getCmp('phoneadd').setValue('');
					var role1=Ext.getCmp('role1').setValue('');
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
		"用户名称：",
		{xtype:"textfield",fieldLabel:"",id:'usernameforsearch',width:200,maxLength:20},
		"-",
		"帐号：",
		{xtype:"textfield",fieldLabel:"",id:'accountforsearch',width:200,maxLength:20},
		"-",
		"角色",
		,rolecombo2,
		"-",
		{
			xtype:'button',
			text:"查询",
			iconCls:'search_16x16',
			listeners:{
				"click":function(){
					queryUserList();
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
						Ext.getCmp("usernameforsearch").setValue("");
						Ext.getCmp("accountforsearch").setValue("");
						rolecombo2.setValue("");
					}
				}
			}
		]
	});
	queryUserList();
	/**
	 * 东的列表
	 */
	var griduser=Ext.create('Ext.grid.Panel', {
		autoWidth:true,
		autoHeight:true,
		tbar:topTool,
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		tbar:topTool,
		renderTo: Ext.getBody(),
		store: userStore,
		selModel : {
			selType : 'checkboxmodel'
		},
		multiSelect : true,
		columns: [
		          { header: 'ID',  dataIndex: 'id',hidden:true},
		          { header: '用户名称',  dataIndex: 'userName' ,flex:1},
		          { header: '账户', dataIndex: 'accouont', flex:1},
		          { header: '角色id', dataIndex: 'tbRole.id', flex:1,hidden:true},
		          { header: 'psssword', dataIndex: 'psssword', flex:1,hidden:true},
		          { header: '角色', dataIndex: 'tbRole.rolename' ,flex:1},
		          { header: 'Email', dataIndex: 'email', flex:1},
		          { header: '电话', dataIndex: 'phoneNumber' ,flex:1},
		          { header: '操作', dataIndex: '',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  return '<a href="#" onclick="modify(\''+rec.get('id')+','+rec.get('userName')+','+rec.get('tbRole.id')+
		        	  ','+rec.get('email')+','+rec.get('phoneNumber')+','+rec.get('accouont')+','+rec.get('tbRole.rolename')+','+rec.get('accouont')+'\')">'+"修改"
		        	  +'</a>|<a href="#" onclick="deleteOneUser(\''+rec.get('id')+'\')">'+"删除"
		        	  +'</a>';
		          }}
		          ],
		          bbar : new Ext.PagingToolbar({   //添加翻页工具条
		        	  store : userStore,   //将数据源添加到分页的工具条中
		        	  displayInfo : true         //显示展示信息
		          })
	});

//		提交按钮
		var submitButton = new Ext.Button({
			text:"提交",
			listeners:{
				click:function(){
					saveServiceInfo(serviceInfoPanel);
				}
			}
		});
//		取消按钮
		var resetButton = new Ext.Button({
			text:"取消",
			listeners:{
				click:function(){Ext.getCmp('addwin').hide();}			
			}
		});
		/**
		 * 新增窗口panel
		 */		
		var serviceInfoPanel = Ext.create('Ext.form.FormPanel', {
			id:'form1',
			width: 580,
			height: 180,
			bodyPadding: 30,
			url:'',
			frame:true,	
			buttonAlign :'center',
			layout: {
				type: 'table',
				columns: 2
			},
			defaults: { width: 300, height: 28},
			items: [
			        {xtype:"textfield",fieldLabel:"id",id:'addid',width:270,hidden:true,allowBlank: true},
			        {xtype:"textfield",fieldLabel:"姓名",id:'name',width:270,allowBlank: false,maxLength:20},
			        {xtype:"textfield",fieldLabel:"帐号",id:'accountadd',width:270,allowBlank: false,maxLength:20,listeners:{
			        	blur:function(tes,epots){
							checkTheAccounts();
						}			
					}},  
			        {xtype:"textfield",fieldLabel:"email",id:'emailadd',width:270,allowBlank: false,vtype:'email',maxLength:50}, 
			        {xtype:"textfield",fieldLabel:"密码",id:'passwordadd',width:270,allowBlank: false,inputType:'password',maxLength:40}, 
			        {xtype:"textfield",fieldLabel:"电话号码",id:'phoneadd',width:270,vtype:'mobilephonenum',maxLength:30}, 
			        rolecombo
			        ],
			        buttons:[submitButton,resetButton]
		});

		/**
		 * 下面是东神的窗口相关
		 */
		var addUser=Ext.create('Ext.window.Window', {
			closeAction:'hide',
			id:'addwin',
			title: '设置用户',
			height: 250,
			width: 720,
			constrain:true,	//弹出窗不能超过父窗口的范围
	         modal:true,
			layout: 'fit',
			border: false
		}).add(serviceInfoPanel);
	});