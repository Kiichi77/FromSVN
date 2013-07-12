var cm = [
	          { header: 'ID',  dataIndex: 'id',hidden:true},
	         { header: '服务器id', dataIndex: 'serviceId',hidden:true},
		     { header: '内网IP', dataIndex: 'serverEntity.serverIpIn',flex:1},
		     { header: '外网IP', dataIndex: 'serverEntity.serverIpOut',flex:1},
		     { header: '服务器名称', dataIndex: 'serverEntity.serverName',flex:1},
		     { header: '操作', dataIndex: '',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
   				return '<a href="#" onclick="carryServiceIpToQuery(\''+rec.get("id")+'\',\''+rec.get("serviceId")+'\')">'+"重新检测"+'</a>';
		     }
}];