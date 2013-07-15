var cm = [{ header: 'ID',  dataIndex: 'id' ,hidden:true},
          { header: '内网服务器IP', dataIndex: 'serverIpIn',flex: 1},
          { header: '外网服务器IP', dataIndex: 'serverIpOut',flex: 1},
          { header: 'IDRACIP', dataIndex: 'idracIp',flex: 1},
          { header: '服务器名称', dataIndex: 'serverName' ,flex: 1},
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
          { header: '系统型号快速服务代码', dataIndex: 'quickServerCode',flex: 1},
          { header: '系统型号', dataIndex: 'operateSystem' ,flex: 1},
          { header: '操作', dataIndex: '',renderer:function(value, meta, rec, rowIdx, colIdx, ds){
        	  var fontcolor="<font color='blue'>修改</font>";
        	  if(rec.get("serverIpIn")=="自动导入")
        		  fontcolor="<font color='red'>修改</font>";
        	  if(rec.get("quickServerCode")=="自动导入")
        		  fontcolor="<font color='red'>修改</font>";
        	  return '<a href="#" onclick="carryServiceIpToQuery(\''+rec.get("id")+'\')">'+fontcolor
        	  +'</a>|<a href="#" onclick="deleteServiceByIp(\''+rec.get("id")+'\')">'+"删除"
        	  +'</a>';
          }}];