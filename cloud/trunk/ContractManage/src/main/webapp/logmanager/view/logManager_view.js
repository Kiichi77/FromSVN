//根据查询条件查询数据信息
function queryByTiaojian() {
	var starttime = $("#starttime").datebox("getValue"); //开始时间
	var endtime = $("#endtime").datebox("getValue");//结束时间
	var operaterName = $("#operaterName").val().trim(); //操作人
	$('#tt').datagrid('load',{"systemLogVo.starttime":starttime,"systemLogVo.endtime":endtime,"systemLogVo.operaterName":operaterName});
}
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'id',title:'日志ID',width:60,align:'center',hidden:true},
	{field:'logType',title:'操作类型',width:100,align:'center'},
	{field:'operaterName',title:'操作人名称',width:100,align:'center'},
	{field:'result',title:'操作结果',width:80,align:'center'},
	{field:'operTime',title:'操作时间',width:100,align:'center'},
	{field:'operatContent',title:'操作内容',width:100,align:'center'},
	{field:'message',title:'操作失败描述',width:200,align:'center'}
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/systemlog/systemlog/systemlog!listSystemLog.action',
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
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	 $(".datagrid-toolbar").append($("#datagridsearch"));
});