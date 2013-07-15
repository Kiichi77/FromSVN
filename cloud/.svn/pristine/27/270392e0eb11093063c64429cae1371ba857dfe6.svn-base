Ext.onReady(function() {	
	function wantTreerPanel(params){
		Ext.Ajax.request({   
			url: ctx+'/role/role/role!selectRole.action',
			method: 'post',   
			loadMask:true,
			success:function(form,action){   
				var obj = Ext.decode(form.responseText);
				var menu = obj.root;
				panel = getTreeStore(menu);
				params.add(panel);
			}
		});
	}
	//提交按钮
	var submitButton = new Ext.Button({
		text:"提交",
		border: false,
		listeners:{
			click:function(){
				saveServiceInfo(serviceInfoPanel);
			}
		}
	});
	//取消按钮
	var resetButton = new Ext.Button({	
		text:"取消",
		border: false,
		listeners:{
			click:function(){var addwin = Ext.getCmp('addwin');addwin.hide();}			
		}
	});

	/**
	 * 新增窗口panel
	 */		
	var serviceInfoPanel = Ext.create('Ext.form.FormPanel', {
		id:'form1',
		width: 280,
		height: 400,
		bodyPadding: 1,
		url:'',
		frame:true,	
		buttonAlign :'center',
		layout: {
			type: 'column',
			columns: 2
		},
		defaults: { width: 280, height: 28},
		items: [
	            {xtype:"textfield",fieldLabel:"id",id:'id',width:100,allowBlank: false,hidden:true},
	            {
	                xtype: 'label',
	                html: '角色名:'
	           },{xtype:"textfield",fieldLabel:"角色名",id:'name',width:240,allowBlank: false,hideLabel:true,maxLength:30},
	           {
	               xtype: 'label',
	               html: '备注:'
	          },
		        {xtype:"textfield",fieldLabel:"备注",id:'note',width:240,allowBlank: true,hideLabel:true,maxLength:200},
		           {
		               xtype: 'label',
		               html: '选择菜单:'
		          }
		        ],
		buttons:[submitButton,resetButton]
	});
	wantTreerPanel(serviceInfoPanel);


	/**
	 * 下面是东神的窗口
	 */
	Ext.create('Ext.window.Window', {
		id:'addwin',
		closeAction:'hide',
		title: '设置角色',
		height: 400,
		constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
		width: 280,
		layout: 'fit',
		border: false
	}).add(serviceInfoPanel);
	
	
/**
 * 工具条
 */
var topTool = Ext.create("Ext.toolbar.Toolbar",{
	items:[{
		xtype:'button',
		text:"新增角色",
		iconCls:'add',
		listeners:{
			"click":function(){
				Ext.getCmp('name').setValue('');
				Ext.getCmp('note').setValue('');
				if(null != Ext.getCmp('tree')) {
					var treeer = Ext.getCmp('tree').getChecked();
				}
				Ext.each(treeer, function (item) {	
					item.set('checked', false);
				});
				var addwin = Ext.getCmp('addwin');
				addwin.show();
			}
		}
	},"-",
    {
		xtype:'button',
		text:"删除",
		iconCls:'delete',
		listeners:{
			"click":function(){
				deleteRole(griduser);
			}
		}
	},
	"角色名称：",
	{xtype:"textfield",fieldLabel:"",id:'serviceName',width:200,maxLength:30},
	"-",
	{
		xtype:'button',
		text:"查询",
		iconCls:'search_16x16',
		listeners:{
			"click":function(){
				queryUserList(griduser);
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
					Ext.getCmp("serviceName").setValue("");
				}
			}
		}
	]
});






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
	store: rolestore,
    selModel : {
  	  selType : 'checkboxmodel'
    },
    multiSelect : true,
	columns: [
              { header: 'ID',  dataIndex: 'id' ,hidden:true},
	          { header: '角色名称',  dataIndex: 'rolename' ,flex:1},
	          { header: '备注', dataIndex: 'note', flex:1},
	          { header: '操作', dataIndex: '',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
	   				return '<a href="#" onclick="modify(\''+rec.get('id')+','+rec.get('rolename')+','+rec.get('note')+'\')">'+"修改"
	   				+'</a>|<a href="#" onclick="deleteOneRole(\''+rec.get('id')+'\')">'+"删除"
	   				+'</a>';
	          }}
	          ],
	          bbar : new Ext.PagingToolbar({   //添加翻页工具条
	        	  pageSize : thePublicPageSize,   //页面容量
	        	  store : rolestore,   //将数据源添加到分页的工具条中
	        	  displayInfo : true         //显示展示信息
	          })
});
})