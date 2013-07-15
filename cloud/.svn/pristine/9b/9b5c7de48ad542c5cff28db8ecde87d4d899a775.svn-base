Ext.onReady(function () {
	var topToolOne = Ext.create("Ext.toolbar.Toolbar",{
		items:[
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
	Ext.getCmp("pwrStatas").setValue("1");
	var serviceTable = Ext.create('Ext.grid.Panel',{
		id:'firstGrid',
    	autoHeight : true, 
    	renderTo:'firstForm',
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		tbar:topToolOne,
		selModel : {
			 selType : 'checkboxmodel'
		},multiSelect : true,
		viewConfig: {forceFit:true}, 		//列自适应宽
		columns :cm,
		stripeRows: true,
		trackMouseOver : true ,		
		//frame:true,
		store:Ext.data.StoreManager.lookup('simpsonsStores'),  //将数据源添加到grid中
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
		    store : Ext.data.StoreManager.lookup('simpsonsStores'),   //将数据源添加到分页的工具条中
		    displayInfo : true         //显示展示信息
		})
	});
//	var placeName = Ext.getCmp("pName").getValue();
//	var placeLevel = Ext.getCmp("pwrStatas").getValue();
	queryPlaceByNameOrIp();
/****************************************************************************************************************************************/	
	var topToolTwo = Ext.create("Ext.toolbar.Toolbar",{
		items:[
				{xtype:"textfield",fieldLabel:"",id:'pid',width:200,hidden:true},
				  "报表：",
		            {
		              xtype:'combobox',
		              fieldLabel:'',
		              store: tableType,
		              queryMode:'local',
		              editable:false,
	                  displayField: 'ids',
	                  valueField: 'names',
	                  name:'tId',
	                  id:'tableType',
	                  width:120,
	                  listeners:{
							'select':function(params){
								if("1" == params.getValue()) {
									Ext.getCmp("startTime").show();									
									Ext.getCmp("week").hide();
									Ext.getCmp("month").hide();
									Ext.getCmp("year").hide();
								} else if ("2" == params.getValue()) {
									Ext.getCmp("week").show();							
									Ext.getCmp("startTime").hide();
									Ext.getCmp("month").hide();
									Ext.getCmp("year").show();
								}else if ("3" == params.getValue()) {
									Ext.getCmp("month").show();					
									Ext.getCmp("startTime").hide();
									Ext.getCmp("week").hide();									
									Ext.getCmp("year").show();
								}else if ("4" == params.getValue()) {
									Ext.getCmp("year").show();								
									Ext.getCmp("startTime").hide();
									Ext.getCmp("week").hide();
									Ext.getCmp("month").hide();
									
								}
							}
						}
		            },
					"-",
				"时间:",
				{xtype:"datefield",fieldLabel:"",id:'startTime',width:100,format: "Y-m-d",value:getStartTime()},	

				//年报
				{
					xtype:"combobox",
					fieldLabel:"",
					id:'year',
					store: year,
					queryMode:'local',
					editable:false,
					 hidden:true,
	                displayField: 'ids',
	                valueField: 'names',
	                name:'tId',	               
	                width:120
				},
				//周报
				{
					xtype:"combobox",
					fieldLabel:"",
					id:'week',
					store: week,
					queryMode:'local',
		            editable:false,
		            hidden:true,
	                displayField: 'ids',
	                valueField: 'names',
	                name:'tId',	               
	                width:100
				},
				//月报
				{
					xtype:"combobox",
					fieldLabel:"",
					id:'month',					
					store: month,
					queryMode:'local',
		            editable:false,
		            hidden:true,
	                displayField: 'ids',
	                valueField: 'names',
	                name:'tId',	               
	                width:120
				},
				"采集数据类型:",
				{
                   xtype: 'combobox',
                   fieldLabel: '',
                   store:dataType,
                   queryMode: 'local',
                   editable:false,
                   displayField: 'ids',
                   valueField: 'names',
                   name:'pwerId',
                   id:'dataType',
                   width:120
	            },
	            "-",
				{
					xtype:'button',
					text:"查询",
					iconCls:'search_16x16',
					listeners:{
						"click":function(){
							queryServicePwerByTime();
						}
					}
		 		}
          ]
	});
	Ext.getCmp("dataType").setValue("1");
	Ext.getCmp("tableType").setValue("1");
	Ext.getCmp("week").setValue(1);
	Ext.getCmp("month").setValue("1");
	Ext.getCmp("year").setValue("2010");

    var chart = Ext.create('Ext.chart.Chart', {
            id: 'chartCmp',
            xtype: 'chart',
            style: 'background:#fff',
            animate: true,
            shadow: true,
            store: mStore,
            legend: {
              position: 'right'  
            },
            axes: [{
                type: 'Numeric',
                position: 'bottom',
                fields: [ '最大值', '最小值', '平均值'],
                minimum: 0,
                label: {
                    renderer: Ext.util.Format.numberRenderer('0,0')
                },
                grid: true,
                title: '功耗值(瓦特)'
            }, {
                type: 'Category',
                position: 'left',
                fields: ['serverIpIn'],
                title: '服务器IP'
            }],
            series: [{
                type: 'bar',
                axis: 'bottom',
                xField: 'name',
                yField: [ '最大值', '最小值', '平均值']
            }]
        });

    var thePanel = Ext.create('Ext.panel.Panel', {
        width: 800,
        height: 600,
        tbar:topToolTwo,
        minHeight: 400,
        minWidth: 550,
        maximizable: true,
        layout: 'fit',
        items: chart
    });
    
    var win = Ext.create('Ext.window.Window', {
        title: '数据分析图',
//        hidden:true,
        constrain:true,	//弹出窗不能超过父窗口的范围
        modal:true,
        width: 800,
        closeAction: 'hide',	 //关闭时隐藏窗口,而不销毁,
        id:'firstWins',
        height: 600,
        renderTo: Ext.getBody(),
        layout: 'fit',
        items:thePanel,
        listeners: {
            close : function(){
            	mStore.removeAll();
            }
        }
    });
});