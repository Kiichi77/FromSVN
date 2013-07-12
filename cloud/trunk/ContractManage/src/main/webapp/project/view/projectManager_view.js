//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'id',title:'项目ID',width:60,align:'center',hidden:true},
	{field:'projectName',title:'项目名称',width:80,align:'center'},
	{field:'description',title:'项目描述',width:80,align:'center'},
	{field:'customerName',title:'客户公司名称',width:80,align:'center'},
	{field:'creatorName',title:'创建人',width:80,align:'center'},
	{field:'createtime',title:'创建时间',width:80,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="projectProcess.jsp?projectid='+id+'">进度</a>|<a href="#" onclick="updateProject(\''+id+'\')">修改</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/projectManage1/projectManage1/project1!myProject.action?processUser.projectId=',
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
			text:'新增项目',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$('projectId').val(-1);
				showWindow();
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
//	 $(".datagrid-toolbar").append($("#datagridsearch"));
	 
		//下拉选择框数据加载  客户
		var sel = $("#customer");  
		sel.empty();//清除select中的 option
		$.post(ctx+'/customer111/customer111/customer111!queryAllCustomer.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				for(var i = 0;i < datas.rows.length; i++) {
					var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
					sel.append(opt);  
				}
			}
		});
		
		//下拉选择框数据加载  客户
		var sel1 = $("#customer1");  
		sel1.empty();//清除select中的 option
		$.post(ctx+'/customer111/customer111/customer111!queryAllCustomer.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				for(var i = 0;i < datas.rows.length; i++) {
					var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
					sel1.append(opt);  
				}
			}
		});
//		
//		
//	onSelect();
});


//加载用户下拉框
function onSelect(){
	$("#process_id").val(processid);
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'/user/user/user!queryUserListByrUserTaskUrlId.action',
			{'userTaskUrlid':taskPkId}
			,function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});	
};