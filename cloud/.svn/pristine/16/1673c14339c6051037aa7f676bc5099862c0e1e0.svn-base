
$(function(){
	$('#tt').datagrid({
		url: 'Json/datagrid_data1.json',
		width: '100%',
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
		columns:[[  		   
					{field:'id',title:'ID',width:60,align:'center'},
					{field:'name',title:'项目名称',width:120,align:'center'},	
					{field:'contractName',title:'合同名称',width:80,align:'center'},	
					{field:'purchaseName',title:'销售人',width:80,align:'center'},
					{field:'client',title:'客户',width:80,align:'center'},
					{field:'createTime',title:'创建时间',width:80,align:'center'},
					{field:'preliminary',title:'初验',width:80,align:'center',
						formatter:function(value,rec,index){
							var edit;
							edit='<button onclick="showWindow()">初验</button>';
							return edit;
						}
					},
					{field:'final',title:'终验',width:80,align:'center',
						formatter:function(value,rec,index){
							var edit;
							edit='<button onclick="showWindow()">终验</button>';
							return edit;
						}
					}
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
	$(".datagrid-toolbar").append($("#datagridsearch"));

});	

//弹出层***************************************
function showWindow() {
	var div_obj = $("#addVmDiv");
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	var popupHeight = div_obj.height();
	var popupWidth = div_obj.width();
	div_obj.css({
		"position" : "absolute"
	}).animate({
		left : windowWidth / 2 - popupWidth / 2,
		top : windowHeight / 2 - popupHeight / 2,
		opacity : "show"
	}, "slow");
}

//隐藏存储操信息操作
function hideDiv() {
	$("#mask").remove();
	$("#addVmDiv").animate({
		left : 0,
		top : 0,
		opacity : "hide"
	}, "slow");
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