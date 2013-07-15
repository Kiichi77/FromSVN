var cm = [
          { header: 'ID',  dataIndex: 'id',hidden:true},
		     { header: '操作', flex: 1, dataIndex: 'operationType',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		    	 var result = rec.get('operationType');
		    	 if(result==1){
		    		 return "用户登陆";
		    	 }else if(result==2){
		    		 return "添加";
		    	 }else if(result==3){
		    		 return "修改";
		    	 }else if(result==4){
		    		 return "单个删除";
		    	 }else if(result==5){
		    		 return "批量删除"
		    	 }
		     }},
		     { header: '操作结果', flex: 1, dataIndex: 'operationResult',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		    	 var result = rec.get('operationResult');
		    	 if(result==0){
		    		 return "失败";
		    	 }else if(result==1){
		    		 return "成功";
		    	 }else{
		    		 return "无效";
		    	 }
		     }},
		     { header: '操作者', flex: 1, dataIndex: 'tbUser.accouont' },
		     { header: '操作机器IP', flex: 1, dataIndex: 'ip' },
		     { header: '操作时间', flex: 1, dataIndex: 'dateTime'},
		     { header: '参数', flex: 1, dataIndex: 'param'},
		     { header: '备注', flex: 1, dataIndex: 'note'}
];