//根据传入参数查询目标服务器
this.queryPlaceByNameOrIp = function (placeName,placeLevel) {
	Store.currentPage = 1;
	Store.pageSize = thePublicPageSize;
	Store.load({			
			params:{
			start:0,
		    limit:thePublicPageSize,
		    'blackNameEntity.serverEntity.serverName' : placeName,//服务器名称
			'blackNameEntity.serverEntity.serverIpIn' : placeLevel//服务器内网ip
		}
	});
	Store.on('beforeload', function (store, options) {
	    Ext.apply(store.proxy.extraParams, {
	    	'blackNameEntity.serverEntity.serverName' : placeName,//服务器名称
			'blackNameEntity.serverEntity.serverIpIn' : placeLevel//服务器内网ip
	    });
	});
}


//将服务器IP传入到服务器信息设置页面(新增或修改)
function carryServiceIpToQuery(blackId,serverId) {
	var url= ctx+'/blackName/blackName/blackName!toDetection.action';
	Ext.Ajax.request({
	  url: url,
	  params: { "blackNameEntity.id": blackId,'blackNameEntity.serviceId' : serverId},
	  method: 'post',
	  success: function (response, options) {
		  var pp  =  Ext.JSON.decode(response.responseText);
		  Ext.MessageBox.alert('提示',pp.msg);
		  queryPlaceByNameOrIp();
	  },
	  failure: function (response, options) {
	      Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
	  }
	});
}

//根据IP从黑名单中删除目标服务器
//paramsIp是黑名单主键id String数组
function deleteServiceByIp(paramsIp) {
	Ext.Ajax.request({
      url: ctx+"/blackName/blackName/blackName!delete.action",
      params: { "ids": paramsIp},
      method: 'post',
      success: function (response, options) {
          Ext.MessageBox.alert('提示', '操作成功');
          queryPlaceByNameOrIp();
      },
      failure: function (response, options) {
          Ext.MessageBox.alert('提示', '操作失败，请求超时或网络故障。');
      }
  });
}