var cm = [{ header: 'ID',  dataIndex: 'id',hidden:true},
		     { header: '位置名称', dataIndex: 'text',flex:1},
		     { header: '父级', dataIndex: 'parName',flex:1},
		     { header: '位置类型', dataIndex: 'level',renderer:function(v) {
		    	 return backNameByPlaceId(v);
		     },flex:1},
		     { header: '操作', dataIndex: '',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
   				return '<a href="#" onclick="queryServicePlaceInfo(\''+rec.get("id")+'\')">'+"修改"
   				+'</a>|<a href="#" onclick="deleteServiceByIp(\''+rec.get("id")+'\')">'+"删除"
   				+'</a>|<a href="#" onclick="queryInfoTree(\''+rec.get("id")+'\')">'+"查看详细层级关系"
   				+'</a>';
		     }
}];