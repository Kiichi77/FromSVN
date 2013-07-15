function createNewWin() {
		// 提交按钮
		var saveButton = new Ext.Button({
			text:"确定",
			listeners:{
				click:function(){
					editsId = null;
					carryServiceIpToQuery();
				}
			}
		});
		// 取消按钮
		var cancleButton = new Ext.Button({
			text:"取消",
			listeners:{
				click:function(){
					clearInfoSetWin();
					Ext.getCmp("servicePlaceWins").destroy();
				}			
			}
		});
		//设置服务器位置的表单
		var serviceInfoPanel = Ext.create('Ext.form.Panel', {
		    width: 380,
		    height: 150,
		    bodyPadding: 10,
		    id:'serviceInfoPanels',
//		    frame:true,	
		    buttonAlign :'center',
		    layout: {
		        type: 'table',
		        columns: 1
		    },
		    defaults: {width: 270, height: 28,allowBlank: false},
		    items: [{xtype:"textfield",fieldLabel:"",id:'palceId',hidden:true,allowBlank: true},
					{xtype:"textfield",fieldLabel:"位置名称",id:'nname',width:270,maxLength:40},
					{
					       xtype: 'combobox',
					       fieldLabel: '位置类型',
					       store:placeTypeSave,
					       editable:false,
					       queryMode: 'local',
					       displayField: 'ids',
					       valueField: 'names',
					       allowBlank: false,
					       name:'plac',
					       id:'plac',
					       listeners:{
					           'select': function(combo) {
					        	   wantMBplace(combo.getValue());
					        	   Ext.getCmp("parents").setValue("");
					           }
					       }
					},{
					       xtype: 'combobox',
					       fieldLabel: '所属父级',
					       store:parentsPlace,
					       editable:false,
					       queryMode: 'local',
					       allowBlank: true,
					       displayField: 'name',
					       valueField: 'id',
					       name:'parents',
					       id:'parents'
					}
				]	
		});
		//弹出的设置服务器位置的窗口
		var servicePlaceWin = Ext.create('Ext.Window', {
	         title: '设置位置信息',
	         width: 380,
	         height: 180,
	         hidden:true,
	         constrain:true,	//弹出窗不能超过父窗口的范围
	         modal:true,
	         closeAction: 'destroy',	 //关闭时隐藏窗口,而不销毁,
	         x: 320,
	         y: 220,
	         id:'servicePlaceWins',
	         layout: 'fit',
	         items: [serviceInfoPanel],
	         buttons: [saveButton,cancleButton],
	         listeners:{
	         	beforehide:function(obj,opt){
	         		clearInfoSetWin();
	         	}
	         }
	     });
	}
//重新初始化信息操作节面
function clearInfoSetWin() {
//	Ext.getCmp("plac").enable();
//	Ext.getCmp("parents").enable();
	Ext.getCmp("serviceInfoPanels").form.reset();
}

//根据传入参数查询目标服务器
function queryPlaceByNameOrIp() {
	Store.currentPage = 1;
	Store.pageSize = thePublicPageSize;
	Store.load({			
			params:{
			start:0,
		    limit:thePublicPageSize,
		    'model.name' : Ext.getCmp("pName").getValue(),//地址名称
			'model.level' : Ext.getCmp("pwrStatas").getValue()//查询号段
		}
	});
	Store.on('beforeload', function (store, options) {
        Ext.apply(store.proxy.extraParams, {
			'model.name' : Ext.getCmp("pName").getValue(),//地址名称
			'model.level' : Ext.getCmp("pwrStatas").getValue()//查询号段	
        });
    });
}


//将服务器IP传入到服务器信息设置页面(新增或修改)
function carryServiceIpToQuery() {//paramWin,paramForm,
	if(Ext.getCmp("serviceInfoPanels").form.isValid()) {
		var placeName = Ext.getCmp("nname").getValue();
		var placeParentsId = Ext.getCmp("parents").getValue();
		var placeLevel = null; 
		if("数据中心" == Ext.getCmp("plac").getValue()||1 == Ext.getCmp("plac").getValue()) {
			placeLevel = 1;
		  } else if("机房" == Ext.getCmp("plac").getValue()||2 == Ext.getCmp("plac").getValue()) {
			  placeLevel = 2;
		  } else if("机架系" == Ext.getCmp("plac").getValue()||3 == Ext.getCmp("plac").getValue()) {
			  placeLevel = 3;
		  } else if("机架" == Ext.getCmp("plac").getValue()||4 == Ext.getCmp("plac").getValue()) {
			  placeLevel = 4;
		  } else if("机箱" == Ext.getCmp("plac").getValue()||5 == Ext.getCmp("plac").getValue()) {
			  placeLevel = 5;
		  } else {
			  placeLevel = 6;
		  }
		var editsId = Ext.getCmp("palceId").getValue();
		var urls = "";
		if(null == editsId || "" == editsId) {
			urls = ctx+'/datacenter/datacenter/datacenter!save.action';
		} else {
			urls = ctx+'/datacenter/datacenter/datacenter!update.action';
		}
		Ext.Ajax.request({
		  url: urls,
		  params: { "model.id": editsId,"model.name": placeName,'model.level' : placeLevel,'model.parId' : placeParentsId},
		  method: 'post',
		  success: function (response, options) {
			  Ext.Msg.alert('提示', '操作成功');
			  Ext.getCmp("servicePlaceWins").destroy();
			  queryPlaceByNameOrIp();
    		  treeStore.load({
				callback:function(){
					Ext.getCmp("servertree").expandAll();
				}
		  	  });
//		      Ext.Ajax.request({
//				  url: ctx+"/datacenter/datacenter/datacenter!getBelongMenu.action",
//				  params: {'model.parId' : placeParentsId},
//				  method: 'post',
//				  success: function (response, options) {
//				      Ext.Msg.alert('提示', '操作成功');
//					  Ext.getCmp("servicePlaceWins").destroy();
//					  queryPlaceByNameOrIp();
//					  Ext.getCmp("servertree").expandAll();
//				  }
//				});
		  },
		  failure: function (response, options) {
		      Ext.Msg.alert('提示', '操作失败，请求超时或网络故障。');
		  }
		});
	}
}

//根据位置ID返回位置名称
function backNameByPlaceId(params) {
	var backPlaceName = "";
	if(1 == params) {
		backPlaceName = "数据中心";
	  } else if(2 == params) {
		  backPlaceName = "机房";
	  } else if(3 == params) {
		  backPlaceName = "机架系";
	  } else if(4 == params) {
		  backPlaceName = "机架";
	  } else if(5 == params) {
		  backPlaceName = "机箱";
	  } else {
		  backPlaceName = "无";
	  }
	return backPlaceName;
}

//往位置信息设置界面加入对应的值
function queryServicePlaceInfo(paramsId) {
	if(null == Ext.getCmp("servicePlaceWins")) {
		createNewWin();
	}
	var paramWin = Ext.getCmp("servicePlaceWins");
	Ext.getCmp("palceId").setValue(paramsId);
	Ext.Ajax.request({
		  url: ctx+'/datacenter/datacenter/datacenter!findById.action',
		  params: { "model.id": paramsId},
		  method: 'post',
		  success: function (response, options) {
			  var obj = Ext.JSON.decode(response.responseText);  
			  Ext.getCmp("nname").setValue(obj.model.name);
			  Ext.getCmp("plac").setValue(backNameByPlaceId(obj.model.level));
			  Ext.getCmp("plac").disable();
			  wantMBplace(obj.model.level);
			  Ext.getCmp("parents").setValue(obj.model.parId);
			  Ext.getCmp("parents").disable();
			  paramWin.show();
		  },
		  failure: function (response, options) {
		      Ext.Msg.alert('提示', '操作失败，请求超时或网络故障。');
		  }
	});
}

//根据IP删除目标服务器
function deleteServiceByIp(ids) {
	var selModel = Ext.getCmp("firstGrid").getSelectionModel();   
	var rows = selModel.getSelection();
	var arr=new Array();
	if(rows.length>0){ 
		Ext.MessageBox.confirm("提示", "是否真的要删除?",function (btn){
			if(btn=='no')return;
			Ext.Array.each(rows, function(item) {arr.push(item.get('id'));});
			Ext.Ajax.request({
			      url:  ctx+'/datacenter/datacenter/datacenter!delete.action',
			      params: {"ids": arr},
			      method: 'post',
			      success: function (response, options) {
			    	  var obj = Ext.JSON.decode(response.responseText); 
			    	  if(obj.msg=="ok"){
			    		  Ext.Msg.alert('提示', '操作成功。');
			    		  Ext.Array.each(rows, function(item) {
			      			  var node = treeStore.getNodeById(item.get('id'));
			      			  var prenodes = node.parentNode;
			      			  prenodes.removeChild(node);
			    			});
			    		  queryPlaceByNameOrIp();
			    	  }else{
			    		  Ext.Msg.alert("提示","该位置关联了服务器，不能被删除，请联系管理员。");
			    	  }
			      },
			      failure: function (response, options) {
			    	  Ext.Msg.alert('提示', '操作失败，请求超时或网络故障。');
			      }
			  });
		});
	}else {
		Ext.Msg.alert("提示","请选择要删除的数据。");
	}
}

//加载位置数据
this.wantMBplace = function(params) {
	parentsPlace.load({			
		params:{
		'model.level' : params//查询号段
		}
	});
}

//查看详细的层级结构树形
function queryInfoTree(paramsId) {
	var selNodes = Ext.getCmp("servertree").getChecked();
	if(0!=selNodes.length) {
		Ext.each(selNodes, function(nodes){
			nodes.set("checked", false);
	    });
	}
	var node = Ext.getCmp("servertree").getStore().getNodeById(paramsId);
	node.set("checked", true);
	//获得父节点
    pNode = node.parentNode;
    //当checked == true通过循环将所有父节点选中
    for (; pNode != null; pNode = pNode.parentNode) {
	     pNode.set("checked", true);
	}
	Ext.getCmp("placeInfoTreePanel").show();
}