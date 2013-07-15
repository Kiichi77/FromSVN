
$(function(){
	$('#tt').datagrid({
		url: 'Json/datagrid_data.json',
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
					{field:'no',title:'销售订单号',width:80,align:'center'},	
					{field:'purchaseName',title:'采购人',width:80,align:'center'},
					{field:'supplier',title:'供应商',width:80,align:'center'},
					{field:'createTime',title:'创建时间',width:80,align:'center'},
					{field:'status',title:'状态',width:80,align:'center'},
					{field:'opt',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								edit='<a href="aftersale_detail_manage.jsp">查看订单</a>';
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