//单元数据总览列布局
//代办任务
var dyDataLayOut = [[  		   
                     {field:'id',title:'任务编号',width:60,align:'center',hidden:true},
                     {field:'taskName',title:'任务名称',width:120,align:'center'},
                     {field:'sponsorName',title:'发起人',width:120,align:'center'},
                     {field:'projectName',title:'所属项目',width:60,align:'center'},
                     {field:'personInChargeName',title:'负责人',width:120,align:'center'},
                     {field:'state',title:'任务状态',width:60,align:'center',
                    	 formatter:function(value,rec,index){
                    		 var edit;
                    		 if(value==0){
                    			 edit = "未完成";
                    		 }else if(value==1){
                    			 edit = "完成";
                    		 }else{
                    			 edit = "未知";
                    		 }
                    		 return edit;
                    	 }
                     },
                     {field:'allocationTime',title:'分配时间',width:200,align:'center'},
                     {field:'remark',title:'备注',width:200,align:'center'},
                     {field:'operate',title:'操作',width:80,align:'center',
                    	 formatter:function(value,rec,index){
                    		 var id=rec.taskID;
                    		 var edit;
                    		 edit='<a href="#">任务分配</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">任务分配</a>';
                    		 return edit;
                    	 }
                     }	                    
                     ]];

//已完成的任务
var dyDataLayOut2 = [[  		   
                      {field:'id',title:'任务编号',width:60,align:'center',hidden:true},
                      {field:'sponsorName',title:'任务名称',width:120,align:'center'},
                      {field:'sponsorName',title:'发起人',width:120,align:'center'},
                      {field:'processId',title:'所属项目',width:60,align:'center'},
                      {field:'taskName',title:'负责人',width:120,align:'center'},
                      {field:'processId',title:'任务状态',width:60,align:'center'},
                      {field:'datetime',title:'分配时间',width:200,align:'center'},
                      {field:'datetime',title:'完成时间',width:200,align:'center'},
                      {field:'datetime',title:'备注',width:200,align:'center'}
                      ]];

$(function(){
	//影藏流程任务分配弹出框
	$('#addDataInfoform').hide();
	//代办任务
	$('#tt1').datagrid({
		url:  ctx+'activiti/activiti/activiti!getTheCurrentTask.action',
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
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


	//我的历史任务
	$('#tt3').datagrid({
		url:  ctx+'activiti/activiti/activiti!myHistoryTask.action',
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut2,
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
});
