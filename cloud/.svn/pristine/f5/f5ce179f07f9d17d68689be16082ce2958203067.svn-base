//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'数据源ID',width:60,align:'center',hidden:true},
	{field:'name',title:'数据源名称',width:100,align:'center'},
	{field:'typeName',title:'分类',width:80,align:'center'},
	{field:'status',title:'状态',width:80,align:'center',hidden:true,
		formatter:function(value,rec,index){
			if(1==value) {
				return "有效";
			} else {
				return "无效";
			}
		}
	},
	{field:'description',title:'描述',width:200,align:'center'},
	{field:'createtime',title:'创建时间',width:100,align:'center'},
	{field:'operate',title:'操作',width:120,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="#" onclick="updateModelsInfos(\''+id+'\',\''+rec.name+'\')">设置标准配置</a>&nbsp;|&nbsp;<a href="#" onclick="deleteDataSource(\''+id+'\')">删除</a>&nbsp;|&nbsp;<a href="../quotation/datasource/data_details.jsp?tocheckId='+id+'">查看详情</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/dataSource/dataSource/dataSource!listDataSource.action',
		width: '95%',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
	    pagination:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'上传',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeData();
			}
		},{
			id:'btninfoadd',
			text:'添加数据明细',
			iconCls:'icon-add',
			handler:function(){
				$('#btninfoadd').linkbutton('enable');
				showDataInfoWindow();
			}
		}],			
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
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	//下拉选择框数据加载         数据源
	 var sel1 = $("#shujuyuan");  
	 sel1.empty();//清除select中的 option
	 $.post(ctx+'/dataSource/dataSource/dataSource!getAllDataSource.action',
	 function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
				sel1.append(opt);  
			}
		}	
	});	
	//下拉选择框数据加载         产品类
	 var sel2 = $("#chanpinlei");  
	 sel2.empty();//清除select中的 option
	 $.post(ctx+'/dataSource/dataSource/dataRecord!getProductType.action',
	 function(data){
		var datas = data;
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
				sel2.append(opt);  
			}
		}	
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
	
	//下拉选择框数据加载
	var sel = $("#sourceType");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/dataSource/dataSource/dataSourceType!queryAllDataSourceType.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].typeName).val(datas.rows[i].id);  
				sel.append(opt);  
			}
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
