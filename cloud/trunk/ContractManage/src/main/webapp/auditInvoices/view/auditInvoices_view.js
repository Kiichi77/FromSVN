function fasong(paramsindex,paramstates) {
	$.messager.confirm('提示','确定审核通过吗？',function(r){  
	if (r){  
			if(1==paramstates) {
				$('#tt').datagrid('updateRow',{index:paramsindex,row:{status:"0"}});
			} else {
				$('#tt').datagrid('updateRow',{index:paramsindex,row:{status:"1"}});
			}
		}
	});
}
var strind = "相关合同文件已经打包下载,请查阅!";

//单元数据总览列布局
var dyDataLayOut = [[  		   
//	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'contractTitle',title:'合同名称',width:100,align:'center'},
	{field:'contractDownPath',title:'合同下载地址',width:100,align:'center',hidden:true},
	{field:'customer',title:'客户公司抬头名称',width:100,align:'center'},
	{field:'taxType',title:'增值税类型',width:100,align:'center',
		formatter:function(value,rec,index){
			if(1==value) {
				return "增值税专用发票";
			} else {
				return "增值税普通发票";
			}
		}
	},
	{field:'amount',title:'金额(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'status',title:'发票状态',width:80,align:'center',
		formatter:function(value,rec,index){
			if(1==value) {
				return "已寄送发票";
			} else {
				return "未寄送发票";
			}
		}
	},
	{field:'userName',title:'发票申请人',width:100,align:'center'},
	{field:'createtime',title:'创建时间',width:100,align:'center'},
	{field:'checkStatus',title:'审核状态',width:100,align:'center',
		formatter:function(value,rec,index){
			if(1==value) {
				return "同意";
			}else if(2==value){
				return "不同意";
			}else{
				return "未知";
			}
		}
	},
	{field:'remain',title:'批注',width:100,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var status=rec.status;
			var fileName = rec.contractDownPath;
			var existFileName = rec.contractDownPath;
			var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath; 
			var edit;
			if(status==1){
				 edit='审核&nbsp;|&nbsp;<a href="'+downPath+'">下载查看合同信息</a>';
			}else{
				edit='<a href="#" onclick="showWindow(\''+id+'\');">审核</a>&nbsp;|&nbsp;<a href="'+downPath+'">下载查看合同信息</a>';
			}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'invoice/invoice/invoice!listInvoice.action?checkedVo.process_id='+processParamId,
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
	//	toolbar:[],			
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
	 
	//加载用户下拉框
	onSelect();
	//加载上一级审核流程
	//onParentCheck();
	$("#reject").hide(); 
	$("#checker").show();
});

//加载用户下拉框
function onSelect(){
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'user/user/user!queryUserListByrUserTaskUrlId.action',
			{'userTaskUrlid':taskPkId}
			,function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			if("ok"==datas.msg){
				for(var i = 0;i < datas.rows.length; i++) {
					var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
					sel.append(opt);  
				}
			}else if("no"==datas.msg){  //已经是最后一个活动，不需要显示审核人
				$("#reject").hide(); 
				$("#checker").hide();
				wetherLastActive=true;
			}
		}
	});	
};

//加载上一级审核流程
function onParentCheck(){
	var sel = $("#process_Step");
	sel.empty();//清除select中的 option
	$.post(ctx+'activiti/activiti/activiti!getPartentTask.action',{
		process_id : processParamId
	},function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].taskName).val(datas.rows[i].taskID);  
				sel.append(opt);  
			}
		}
	});	
}