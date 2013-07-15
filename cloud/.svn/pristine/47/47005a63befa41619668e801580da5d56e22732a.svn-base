
$(function(){
	$('#tt').datagrid({
		url: ctx+'/contractTemplates/contractTemplates/contractTemplatesDoc!list.action?contractTemplatesDocVo.contractTemplatesId='+contractTemplatesId,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		   
//                    {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'templateDocTypeId',title:'templateDocTypeId',width:60,align:'center',hidden:true},
					{field:'typeName',title:'分类',width:60,align:'center'},
					{field:'docname',title:'名称',width:60,align:'center'},
					{field:'path',title:'文件',width:60,align:'center'},
					{field:'createtime',title:'创建时间',width:60,align:'center'},
					{field:'bVariable',title:'设置变量',width:100,align:'center',
						formatter:function(value,rec,index){
							var bVariable=rec.bVariable;
							var edit;
							if("2"==bVariable){
								return '否';
							}else{
								return '是';
							}
						}
					},{field:'operate',title:'操作',width:100,align:'center',
						formatter:function(value,rec,index){
							var name = rec.path;
							var id=rec.id;
							var bVariable=rec.bVariable;
							var docname = rec.docname;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+name+"&existFileName="+docname+"&exportPath="+exportPath;
							if("2"==bVariable){
							return '<a href="'+downPath+'">下载</a>';
							}else{
								return '<a href="'+downPath+'">下载</a> &nbsp;|&nbsp;<a href="#" onclick="open1('+id+')">查看变量</a>';
							}
						}
					}
					]],
					toolbar:[{
						id:'btnsave',
						text:'返回',					
						iconCls:'icon-back',
						handler:function(){
							window.location.href = ctx+"/templateCheck/templateCheck.jsp";
						}
					},{
						id:'btnadd',
						text:'审核',
						iconCls:'icon-save',
						handler:function(){
							$('#btnsave').linkbutton('enable');
							//保存合同模板文件和合同模板之间的关系
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
		},
		onLoadSuccess: function(data){
		}
	});
	
	//根据状态判断审核按钮是否开放
	checkButton();
	//加载用户下拉框
	onSelect();
});	

//加载用户下拉框
function onSelect(){
	var radioValue = $("input[type='radio']:checked").val();
	if(0==radioValue){
		if(4==status){
			$("#checker").hide();
			return;
		}else{
			$("#checker").show();
		}
	}else if(1==radioValue){
		$("#checker").show();
	}
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'/user/user/user!queryUserCheck.action',
			{
			 'status':status,
			 'checkResult':radioValue
			}
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
				var opt = $("<option>").text("1").val("last");  
				sel.append(opt);  
				wetherLastActive=true;
			}
		}
	});	
};

//根据状态判断审核按钮是否开放
function checkButton(){
	//0:审核中，1:未审核，2:销售审核通过,3:销售审核未通过,4:财务审核通过,5:财务审核未通过,6:经理审核通过,7:经理审核未通过
	//根据角色判断
	if(roleSeller==nowUserRole){ //销售
		if(0==status||5==status){
			$('#btnadd').linkbutton('enable'); //启用
		}else{
			$('#btnadd').linkbutton('disable'); //禁用
		}
	}else if(roleFinancial==nowUserRole){ //财务
		if(2==status||7==status){
			$('#btnadd').linkbutton('enable'); //启用
		}else{
			$('#btnadd').linkbutton('disable'); //禁用
		}
	}else if(roleManager==nowUserRole){ //经理
		if(4==status){
			$('#btnadd').linkbutton('enable'); //启用
		}else{
			$('#btnadd').linkbutton('disable'); //禁用
		}
	}
	
	
}
