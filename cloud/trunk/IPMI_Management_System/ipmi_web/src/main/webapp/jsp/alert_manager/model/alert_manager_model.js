var cm = [
	          { header: 'alertId',  dataIndex: 'alertId',hidden:true},
	         { header: '筛选类型', dataIndex: 'filterType',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		    	 var result = rec.get('filterType');
		    	 if(result==1){
		    		 return "系统功耗";
		    	 }else if(result==2){
		    		 return "出风口温度";
		    	 }else if(result==3){
		    		 return "入风口温度";
		    	 }else if(result==4){
		    		 return "CPU功耗";
		    	 }else if(result==5){
		    		 return "内存功耗";
		    	 }else{
		    		 return "无法检测";
		    	 }
		     }},
//		     { header: '报警类型', dataIndex: 'serious',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
//		    	 var result = rec.get('filterType');
//		    	 if(result==1){
//		    		 return "警告";
//		    	 }else if(result==2){
//		    		 return "告警";
//		    	 }else{
//		    		 return "无法检测";
//		    	 }
//		     }},
		     { header: '触发告警阀值', dataIndex: 'warningNum',flex:1},
		     { header: '触发严重阀值', dataIndex: 'seriousNum',flex:1},
		     { header: '内网IP', dataIndex: 'tbServer.serverIpIn',flex:1},
		     { header: '用户id', dataIndex: 'tbUsers.id',hidden:true},
		     { header: '用户名', dataIndex: 'tbUsers.accouont',flex:1},
		     { header: '邮箱', dataIndex: 'tbUsers.email',flex:1},
		     { header: '电话号码', dataIndex: 'tbUsers.phoneNumber',flex:1},
		     { header: '操作', dataIndex: '',flex:1,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
   				return '<a href="#" onclick="update(\''+rec.get("alertId")+'\',\''+
   						rec.get("filterType")+'\',\''+
   						rec.get("warningNum")+'\',\''+
   						rec.get("seriousNum")+'\',\''+
   						rec.get("tbUsers.accouont")+'\',\''+
   						rec.get("tbUsers.id")+'\')">'+"修改"+'</a>  |  <a href="#" onclick="deleteByOne(\''+rec.get("alertId")+'\')">'+"删除"+'</a>';
		     }
}];