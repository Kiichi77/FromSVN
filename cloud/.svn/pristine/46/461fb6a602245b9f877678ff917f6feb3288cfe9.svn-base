//单元数据总览列布局
var dyDataLayOut = [[  		   
	//{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'用户id',width:60,align:'center',hidden:true},
	{field:'accouont',title:'用户名',width:80,align:'center'},
	{field:'userName',title:'姓名',width:70,align:'center'},
	{field:'jobDuties',title:'职务',width:80,align:'center',
		formatter:function(value,rec,index){
			return value.jobDutiesrName;
	}},
	{field:'jobPlace',title:'常驻地',width:50,align:'center',
		formatter:function(value,rec,index){
			return value.jobPlaceName;
	}},
	{field:'isInterface',title:'是否为接口人',width:70,align:'center',
		formatter:function(value,rec,index){
			var val;
			if(0==value){
				val = "否";
			}else if(1==value){
				val = "是";
			}
			return val;
	}},
	{field:'phoneNumber',title:'电话号码',width:150,align:'center'},
	{field:'email',title:'邮箱',width:150,align:'center'},
	{field:'tbRole',title:'角色',width:80,align:'center',
		formatter:function(value,rec,index){
			return value.rolename;
	}},
	{field:'note',title:'备注',width:100,align:'center'},
	{field:'operate',title:'操作',width:150,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var edit;
			edit='<a href="#" onclick="deleteDataSource('+id+')">删除</a>&nbsp;|&nbsp;<a href="#" onclick="updateUser('+id+')">修改</a>&nbsp;|&nbsp;<a href="#" onclick="initialize('+id+')">初始化密码</a>';
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'user/user/user!listUser.action',
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
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$("#userId").val(-1);
				$("#pwd").show();
				$("#aa").show();
				showWindow();
			}
		}/*,{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeData(-1);
			}
		}*/],			
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
	 
	//角色下拉选择框数据加载
	var sel = $("#tbRole");  
	sel.empty();//清除select中的 option
	$.post(ctx+'role/role/role!findAll.action',function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].rolename).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});	
	
	var jobDuties = $("#jobDuties");
	jobDuties.empty();//清除select中的 option
	$.post(ctx+'role/role/role!jobDuties.action',function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].jobDutiesrName).val(datas.rows[i].id);  
				jobDuties.append(opt);  
			}
		}
	});	
	
	var jobPlace = $("#jobPlace");
	jobPlace.empty();//清除select中的 option
	$.post(ctx+'role/role/role!jobPlace.action',function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].jobPlaceName).val(datas.rows[i].id);  
				jobPlace.append(opt);  
			}
		}
	});	
	
});
