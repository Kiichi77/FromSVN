var cm = [{ header: 'ID',  dataIndex: 'id',hidden:true},
		     { header: '内网服务器IP', dataIndex: 'serverIpIn'},
		     { header: '外网服务器IP', dataIndex: 'serverIpOut'},
		     { header: '服务器名称', dataIndex: 'serverName' },
		     { header: '电源状态', dataIndex: 'cangetPowerConsumption',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		    	 var result = rec.get('cangetPowerConsumption');
		    	 if(result==1){
		    		 return "打开";
		    	 }else if(result==2){
		    		 return "关闭";
		    	 }else{
		    		 return "无法检测";
		    	 }
		     } },
		     { header: '系统型号快速服务代码', dataIndex: 'quickServerCode' , flex: 1 },
		     { header: '系统型号', dataIndex: 'operateSystem' },
		     { header: '操作', dataIndex: '',flex: 1 ,renderer:function(value, meta, rec, rowIdx, colIdx, ds){
		    	 var powerState = rec.get("cangetPowerConsumption");
		    	 if(1==powerState){
		    		 return '<a href="#" onclick="openOrclosePwrByIp(\''+rec.get("id")+'\',2)">'+"关机"
		   			 +'</a>|<a href="#" onclick="openDetail(\''+rec.get("id")+'\')">'+"详情</a>";
		    	 }else if(2==powerState){
		    		 return '<a href="#" onclick="openOrclosePwrByIp(\''+rec.get("id")+'\',1)">'+"开机"
		    		 +'</a>|<a href="#" onclick="openDetail(\''+rec.get("id")+'\')">'+"详情</a>";
		    	 }else{
		    		 return '无法操作|<a href="#" onclick="openDetail(\''+rec.get("id")+'\')">'+"详情</a>";
		    	 }
}}];