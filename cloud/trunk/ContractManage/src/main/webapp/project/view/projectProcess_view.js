//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'taskID',title:'任务编号',width:60,align:'center',hidden:true},
	{field:'taskName',title:'任务名称',width:80,align:'center'},
	{field:'projectName',title:'项目名称',width:80,align:'center'},
	{field:'sponsorName',title:'发起人',width:80,align:'center'},
	{field:'state',title:'状态',width:80,align:'center',
		 formatter:function(value,rec,index){
			  var status=rec.state;
			  var edit;
			  if(0==status){
				 edit='未处理';
			  }else if(1==status)
				  {
				  edit='处理中';
				  }else if(2==status)
				  {
					  edit='已完成';
					  }else if(3==status)
					  {
						  edit='打回';
						  }
			  return edit;
		 }
		},
    {field:'remark',title:'备注',width:80,align:'center'}
//	{field:'operate',title:'操作',width:80,align:'center',
//		formatter:function(value,rec,index){
//			var id=rec.id;
//			var edit;
//			if(rec.state=='已完成'){
//				edit='-';
//			}else{
//				edit='<a href="javascript:void(0)" onclick="processPicJump(\''+rec.processId+'\')">查看流程图</a>';
//			}
//			return edit;
//		}
//	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/task/task/task!getTaskByProjectId.action?projectId='+projectid,
		width: '100%',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
			window.location.href='projectManager.jsp';
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
});