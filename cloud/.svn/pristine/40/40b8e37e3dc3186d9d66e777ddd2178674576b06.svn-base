Ext.onReady(function() {
	/*********************************************策略列表表单start*************************************************/
	// 工具条
	var topTool = Ext.create("Ext.toolbar.Toolbar",{
		items:[
		       "开始日期:",
		       {xtype:"datefield",fieldLabel:"",id:'dateTime',width:200, format: 'Y-m-d',value:getEndTime()},
		       "-",
		       "结束日期:",
		       {xtype:"datefield",fieldLabel:"",id:'endTime',width:200, format: 'Y-m-d',value:getEndTime()},
		       "-",
		       "IPMI_IP：",
		       {xtype:"textfield",fieldLabel:"",id:'idracIp',width:200,maxLength:20},
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
		    			   Ext.getCmp("dateTime").setValue("");
		    			   Ext.getCmp("endTime").setValue("");
		    			   Ext.getCmp("idracIp").setValue("");
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
		store: selStore,
		columns: [
		          { header: 'ID',  dataIndex: 'id',hidden:true},
		          { header: 'IPMI_IP',  dataIndex: 'serverIp' ,flex:1},
		          { header: '日志内容',  dataIndex: 'selMsg' ,flex:1},
		          { header: '时间',  dataIndex: 'createTime',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		        	  return rec.get('createTime').replace("T"," ");
		          }},
		          { header: '电平状态',  dataIndex: 'state' ,flex:1}
		          ],
		          bbar : new Ext.PagingToolbar({   //添加翻页工具条
		        	  store : selStore,   //将数据源添加到分页的工具条中
		        	  displayInfo : true         //显示展示信息
		          })
	});
	/*********************************************列表表单end************************************************/
});