var cm = [{ header: 'ID',  dataIndex: 'id',hidden:true},
		     { header: '位置名称', dataIndex: 'text',flex:1},
		     { header: '父级', dataIndex: 'parName',flex:1},
		     { header: '位置类型',flex:1, dataIndex: 'level',renderer:function(v) {
		    	 return backNameByPlaceId(v);
		     }},
		     { header: '操作', dataIndex: '',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
   				return '<a href="#" onclick="queryServicePlaceInfo(\''+rec.get("text")+'\',\''+rec.get("id")+'\')">'+"查询报表"
   				+'</a>';
		     }
}];