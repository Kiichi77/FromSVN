//可选配置
var firsts = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center',editor:'text'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center'},
	{field:'totalprice',title:'目录合价',width:60,align:'center'},
	{field:'discountRate',title:'折扣率',width:50,align:'center'},
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center'},
	{field:'otherRates',title:'运保及其他费率（%）',width:50,align:'center'},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center'},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:50,align:'center'}
]];
 

$(function(){
			$('#tt1').datagrid({
				url: 'Json/datagrid_data.json',
				width: '95%',
				height: 'auto',
				pagination:true,
				remoteSort: false,
				nowrap: false,
				singleSelect: true,
				autoRowHeight: false,
				striped: true,
				collapsible:false,
				fitColumns: true,
				showGroup:true,
				columns:firsts,
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
			 $('#tt1').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				     $(this).pagination('loaded');
				    }
			 });
			
			$('#tt2').datagrid({
					url: 'Json/datagrid_data.json',
					width: '95%',
					height: 'auto',
					pagination:true,
					remoteSort: false,
					nowrap: false,
					singleSelect: true,
					autoRowHeight: false,
					striped: true,
					collapsible:false,
					fitColumns: true,
					showGroup:true,
					columns:firsts,
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
			 $('#tt2').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				     $(this).pagination('loaded');
				    }
			 });
			$('#tt3').datagrid({
					url: 'Json/datagrid_data.json',
					width: '95%',
					height: 'auto',
					pagination:true,
					rownumbers:true,
					remoteSort: false,
					nowrap: false,
					singleSelect: true,
					autoRowHeight: false,
					striped: true,
					collapsible:false,
					fitColumns: true,
					showGroup:true,
					columns:firsts,
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
			 $('#tt3').datagrid('getPager').pagination({
				    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     $(this).pagination('loading');
				     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				     $(this).pagination('loaded');
				    }
			 });
		});	
	 //隐藏存储操信息操作
	   function hideDiv() {
	   	$("#mask").remove();
	   	$("#addVmDiv").animate({
	   		left : 0,
	   		top : 0,
	   		opacity : "hide"
	   	}, "slow");
	   }
		//弹出层***************************************
		function showWindow() {
		  var div_obj = $("#addVmDiv");  
		  var windowWidth = document.body.clientWidth;       
		  var windowHeight = document.body.clientHeight;  
		  var popupHeight = div_obj.height();       
		  var popupWidth = div_obj.width(); 
		  div_obj.css({"position": "absolute"})
		  .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
		}
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
