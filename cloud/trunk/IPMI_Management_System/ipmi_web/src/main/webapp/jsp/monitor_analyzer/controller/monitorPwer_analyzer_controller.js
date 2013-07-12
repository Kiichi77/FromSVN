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
			'model.name' :Ext.getCmp("pName").getValue(),//地址名称
			'model.level' : Ext.getCmp("pwrStatas").getValue()//查询号段	
        });
    });
}

//查找图表
//查找图表
function queryServicePwerByTime() {
	mStore.load({			
			params:{
		    'dimTimeDate' : Ext.getCmp("startTime").getValue(),//时间
			'dateTimeType':Ext.getCmp("tableType").getValue(),   //报表类型			
			'factServerInfoEntity.dimDate.week':Ext.getCmp("week").getValue(), //周			
			'factServerInfoEntity.dimDate.month':Ext.getCmp("month").getValue(), //月
			'factServerInfoEntity.dimDate.year':Ext.getCmp("year").getValue(), //年
		    'factServerInfoEntity.collectType' : Ext.getCmp("dataType").getValue(),//采集数据类型
			'belongEntity.id' : Ext.getCmp("pid").getValue()//服务器所属集合id
		},
		callback: function(){
			var end = false;
			mStore.each(function(record) {   
				if("1" == record.get('isHaveData')){
					end = true;
				} 
			}); 
			if(!end) {
				Ext.MessageBox.alert('提示', '没有数据!');
			}
	    }
	});
	mStore.on('beforeload', function (store, options) {
        Ext.apply(store.proxy.extraParams, {
        	'dimTimeDate' :Ext.getCmp("startTime").getValue(),//时间
        	'dateTimeType':Ext.getCmp("tableType").getValue(),   //报表类型		
			'factServerInfoEntity.dimDate.week':Ext.getCmp("week").getValue(), //周			
			'factServerInfoEntity.dimDate.month':Ext.getCmp("month").getValue(), //月
			'factServerInfoEntity.dimDate.year':Ext.getCmp("year").getValue(), //年
        	'factServerInfoEntity.collectType' : Ext.getCmp("dataType").getValue(),//采集数据类型
			'belongEntity.id' : Ext.getCmp("pid").getValue()//服务器所属集合id
        });
    });
}
//展现查询用的图表
function queryServicePlaceInfo(paramsName,id){
	Ext.getCmp("pid").setValue(id);
	Ext.getCmp("firstWins").setTitle(paramsName+"数据分析图");
	Ext.getCmp("firstWins").show();
	queryServicePwerByTime();
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