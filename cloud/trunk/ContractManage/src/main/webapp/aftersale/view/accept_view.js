
$(function(){
	$('#tt').datagrid({
		url: ctx+'/contractTemplates/contractTemplates/arrivalProved!queryAllArrivalProved.action?contractId='+contractParamId,
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
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'projectName',title:'项目名称',width:80,align:'center'},	
					{field:'contractId',title:'合同id',width:80,align:'center',hidden:true},	
					{field:'contractName',title:'合同名称',width:80,align:'center'},	
					{field:'sell',title:'销售人',width:30,align:'center'},
					{field:'customer',title:'客户',width:100,align:'center'},
					{field:'customerId',title:'客户od',width:80,align:'center',hidden:true},
					{field:'goodsProvedfile',title:'到货',width:80,align:'center',
						formatter:function(value,rec,index){
							var id=rec.id;
							var contractId=rec.contractId;
							var customerId = rec.customerId;
							var fileName = rec.goodsProvedfile;
							var existFileName = rec.goodsProvedfile;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath;
							var edit;
							if(null==value || ""==value){
								edit='<button onclick="showWindow(1,\''+id+'\',\''+contractId+'\',\''+customerId+'\')">到货</button>';
								if(null==id || ""==id){
									edit='<button onclick="showWindow(1,0,\''+contractId+'\',\''+customerId+'\')">到货</button>';
								}
							}else{
								edit='<a href="'+downPath+'">'+value+'</a>';
							}
							return edit;
						}
					},
					{field:'goodstime',title:'到货时间',width:100,align:'center'},
					{field:'beginProvedfile',title:'初验',width:80,align:'center',
						formatter:function(value,rec,index){
							var goodsProvedfile = rec.goodsProvedfile;  //到货证明文件
							var id=rec.id;
							var contractId=rec.contractId;
							var customerId = rec.customerId;
							var fileName = rec.beginProvedfile;
							var existFileName = rec.beginProvedfile;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath;
							var edit;
							if(null!=goodsProvedfile && ""!=goodsProvedfile){
								if(null==value || ""==value){
									edit='<button onclick="showWindow(2,\''+id+'\',\''+contractId+'\',\''+customerId+'\')">初验</button>';
									if(null==id || ""==id){
										edit='<button onclick="showWindow(1,0,\''+contractId+'\',\''+customerId+'\')">到货</button>';
									}
								}else{
									edit='<a href="'+downPath+'">'+value+'</a>';
								}
							}else{
								edit="初验";
							}
							return edit;
						}
					},
					{field:'begintime',title:'初验时间',width:100,align:'center'},
					{field:'finalProvedfile',title:'终验',width:80,align:'center',
						formatter:function(value,rec,index){
							var goodsProvedfile = rec.goodsProvedfile;  //到货证明文件
							var beginProvedfile = rec.beginProvedfile; //初验证明文件
							var id=rec.id;
							var contractId=rec.contractId;
							var customerId = rec.customerId;
							var fileName = rec.finalProvedfile;
							var existFileName = rec.finalProvedfile;
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath;
							var edit;
							if(null!=goodsProvedfile && ""!=goodsProvedfile && null!=beginProvedfile && ""!=beginProvedfile){
								if(null==value || ""==value){
									edit='<button onclick="showWindow(3,\''+id+'\',\''+contractId+'\',\''+customerId+'\')">终验</button>';
									if(null==id || ""==id){
										edit='<button onclick="showWindow(1,0,\''+contractId+'\',\''+customerId+'\')">到货</button>';
									}
								}else{
									edit='<a href="'+downPath+'">'+value+'</a>';
								}
							}else{
								edit = "终验";
							}
							return edit;
						}
					},
					{field:'finaltime',title:'终验时间',width:100,align:'center'}
					]],
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

	//加载用户下拉框
	onSelect();
});	

//加载用户下拉框
function onSelect(){
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'/user/user/user!queryByUserTaskIdOrDefaultSales.action'
		,{userTaskUrlid:taskUserPkid}	
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

//弹出层***************************************
function showWindow(type,provedId,contractId,customerId) {
	$("#fileType").val(type);
	$("#provedId").val(provedId);
	$("#contractId").val(contractId);
	$("#customerId").val(customerId);
	$("#processId").val(processParamId);
	$('#addVmDiv').window('open');
}

//隐藏存储操信息操作
function hideDiv() {
	$("#mask").remove();
	$("#addVmDiv").window('close'); 
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