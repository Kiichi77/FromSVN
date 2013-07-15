 $(function(){
			$('#tt').datagrid({
				url: ctx+'suppliers/suppliers/suppliers!pageQuerySuppliers.action?id='+suppliersId,
				width: '95%',
				height: 'auto',
//				pagination:true,
				rownumbers:true,
				remoteSort: false,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				columns:[[ 
							{field:'ck',checkbox:true,align:'center'},
							{field:'id',title:'ID',width:60,align:'center',hidden:true},
							{field:'companyName',title:'公司名称',width:80,align:'center'},	
							{field:'address',title:'公司地址',width:80,align:'center'},	
							{field:'contact',title:'联系人',width:80,align:'center'},
							{field:'phone',title:'电话',width:80,align:'center'},
							{field:'email',title:'邮箱',width:80,align:'center'},
							{field:'taxpayerNumber',title:'纳税人识别号',width:80,align:'center'},
							{field:'openBank',title:'开户行',width:80,align:'center'},
							{field:'accountsName',title:'帐号名称',width:80,align:'center'},
							{field:'accounts',title:'帐号',width:80,align:'center'},
							{field:'state',title:'状态',width:80,align:'center',formatter:function(value,rec,index){
								 var status=rec.state;
								 var edit;
								 if(0==status){
									 edit='待审核';
								 }else if(1==status){
									 edit='审核中';
								 }else if(2==status){
									 edit='审核通过';
								 }else if(3==status){
									 edit='审核不通过';
								 }
								 return edit;
							 }
							},
							{field:'description',title:'描述',width:80,align:'center'},	
							{field:'opt',title:'操作',width:80,align:'center',
								formatter:function(value,rec,index){
									var status=rec.state;
									var id = rec.id;
									var edit;
									if(2!=status && 0!=status && 3!=status){
										edit='<a href="#" onclick="showWindow(\''+id+'\');">审核</a>';
									}else{
										edit='无';
									}
									return edit;
								}
								
							}
							]],
					//		toolbar:[],
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
 //隐藏存储操信息操作
function hideDiv() {
	$("#addVmDiv").window("close");
}

//审核弹出层***************************************
function showWindow(id) {
	$("#checkedVo_id").val(id);
	$("#addVmDiv").window("open");
}

//审核供应商
function chekcForm(){
	var radionValue = $("input[type='radio']:checked").val();	//是否同意
	if(null==radionValue || ""==radionValue){
		$.messager.alert("提示","请选择审核结果！");
		return;
	}
	
	$('#addVmform').form('submit', {
		url:ctx+ '/suppliers/suppliers/suppliers!checkSuppliers.action',
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					$.messager.alert("提示","提交审核成功");
					hideDiv();
					window.location.reload(true);
				}else {
					$.messager.alert("提示","提交失败!");
				}
			}
		}
	});
}