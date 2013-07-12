$(function(){
			$('#tt').datagrid({
				url: ctx+'/salesOrderManager/salesOrderInfo/datajson/salesOrderInfo.json',
				width: '95%',
				height: 'auto',
				pagination:true,
				rownumbers:true,
				remoteSort: false,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				columns:[[ 
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'protactnum',title:'产品编号',width:200,align:'center'},
					{field:'protactname',title:'产品名称',width:200,align:'center'},
					{field:'protacttype',title:'产品类型',width:100,align:'center'},
					{field:'numbers',title:'数量',width:100,align:'center'}
				]],
				toolbar:[],
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
					
				}
			});
			//分页工具条
			 $('#tt').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     $(this).pagination('loaded');
				    }
			 });
			 $(".datagrid-toolbar").append($("#datagridsearch"));
		});	
		//创建列菜单
		function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
			var fields = $('#tt').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						$('#tt').datagrid('hideColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						$('#tt').datagrid('showColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}