//动态添加层，装在table的div层
function wantDivFun(paramName,which,tdata) {
	var paramsIds = "";
	var cid = "";
	if("1" == which) {//基本配置单元divID序列号
		paramsIds = "jdiv"+accordionDivId1;
		$('#menu1').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "jcol"+columnId1;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId1 = accordionDivId1+1;
		columnId1 = columnId1+1;
	} else if("2" == which) {//同系列通用单元divID序列号
		paramsIds = "tdiv"+accordionDivId2;
		$('#menu2').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "tcol"+columnId2;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId2 = accordionDivId2+1;
		columnId2 = columnId2+1;
	} else if("3" == which) {//全通用单元divID序列号
		paramsIds = "qdiv"+accordionDivId3;
		$('#menu3').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "qcol"+columnId3;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId3 = accordionDivId3+1;
		columnId3 = columnId3+1;
	}
}

function wantDataGrid(paramsId,cid,tdata) {
	$('#'+paramsId+'').datagrid({
		url:ctx+tdata,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		pagination:true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:infoDataLayOut,
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#'+cid+'').length){
				createColumnMenu(paramsId,cid);
			}
			$('#'+cid+'').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
	//分页工具条
	 $('#'+paramsId+'').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
}

//创建列菜单
function createColumnMenu(paramsId,cid){
	var tmenu = $('<div id="'+cid+'" style="width:100px;"></div>').appendTo('body');
	var fields = $('#'+paramsId+'').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#'+paramsId+'').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#'+paramsId+'').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}
