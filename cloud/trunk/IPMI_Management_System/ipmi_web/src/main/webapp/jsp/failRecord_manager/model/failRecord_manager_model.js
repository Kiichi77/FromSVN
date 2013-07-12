var cm = [
         { header: 'ID',  dataIndex: 'id',hidden:true,flax:1},
         { header: '服务器id', dataIndex: 'serverId',flax:1},
         { header: '策略id', dataIndex: 'policyId',flax:1},
	     { header: '服务器IP', dataIndex: 'serverIp',flax:1},
	     { header: '策略在服务器的ID', dataIndex: 'policyIdInServer',flax:1},
	     { header: '策略在服务器的域', dataIndex: 'domainInServer' ,flax:1},
	     { header: '失败操作', dataIndex: 'operat',flax:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
	    	 var result = rec.get('operat');
	    	 if(result==1){
	    		 return "添加";
	    	 }else if(result==2){
	    		 return "修改";
	    	 }else{
	    		 return "未知";
	    	 }
	     } },
	     { header: '失败原因', dataIndex: 'failReason',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
	    	 var result = rec.get('failReason');
	    	 if(result==1){
	    		 return "连接超时";
	    	 }else if(result==2){
	    		 return "其它原因";
	    	 }else{
	    		 return "未知";
	    	 }
	     } },
	     { header: '修改策略时输入的阀值', dataIndex: 'modifyEnterLimit',flex: 1},
	     { header: '修改策略时输入的期望值', dataIndex: 'modifyEnterHope',flex: 1},
	     { header: '操作时间', dataIndex: 'datetime',flex: 1},
	     { header: '操作', dataIndex: '',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
			return '<a href="#" onclick="carryServiceIpToQuery(\''+rec.get("id")+'\')">'+"重新检测"+'</a>'+
			'|<a href="#" onclick="deleteOneUser(\''+rec.get("id")+'\')">'+"删除"+'</a>';
	     }
}];