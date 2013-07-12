//function fasong(paramsindex,paramstates) {
//	$.messager.confirm('提示','确定已经吗？',function(r){  
//	if (r){  
//			if(1==paramstates) {
//				$('#tt').datagrid('updateRow',{index:paramsindex,row:{status:"0"}});
//			} else {
//				$('#tt').datagrid('updateRow',{index:paramsindex,row:{status:"1"}});
//			}
//		}
//	});
//}&nbsp;|&nbsp;<a href="#" onclick="fasong(\''+index+'\',\''+status+'\');">修改寄送情况</a>

//单元数据总览列布局
var dyDataLayOut = [[  		   
	//{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'contractTitle',title:'合同名称',width:100,align:'center'},
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
	{field:'amount',title:'发票金额(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'status',title:'状态',width:80,align:'center',
		formatter:function(value,rec,index){
			if(1==value) {
				return "未查收发票";
			} else if(0==value){
				return "未查收发票";
			}else
				{
				return "已查收发票";
				}
		}
	},
	{field:'userName',title:'发票申请人',width:100,align:'center'},
	{field:'createtime',title:'申请时间',width:100,align:'center'},//,
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var status=rec.status;
			var edit;
			if(2==status || 0==status)
				{
				 edit='无';
				}else{
			     edit='<a href="#" onclick="updateInvoice(\''+id+'\');">收票</a>';
				}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/invoice/invoice/invoice!myInvoice.action',
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
			text:'申请发票',
			iconCls:'icon-add',
			handler:function(){
			//	$('#btnsave').linkbutton('enable');
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
	
	//下拉选择框数据加载  客户
	var sel = $("#customer");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/customer111/customer111/customer111!queryAllCustomer.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
		//		if(datas.rows[i].id==customerParamId){//只显示一个客户
					sel.append(opt);  
		//			return;
		//		}
			}
		}
	});
	//下拉选择框数据加载  合同
	var sel1 = $("#contractTitle");  
	sel1.empty();//清除select中的 option
	$.post(ctx+'/contractTemplates/contractTemplates/contract!findAll.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].title).val(datas.rows[i].id);  
		//		if(datas.rows[i].id==contractParamId){ //只显示一个合同
					sel1.append(opt);
		//			return;
		//		}
			}
		}
	});
	//下拉选择框数据加载  公司名称
	var sel2 = $("#companyName");  
	sel2.empty();//清除select中的 option
	$.post(ctx+'/customer111/customer111/purchaseCompany!queryAllPurchaseCompany.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
				sel2.append(opt);  
			}
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
	 //进程id赋值
	 $("#process_id").val(processParamId);
});

function change()
{
	var contractId=$("#contractTitle").val();
	$.post(ctx+'/contractTemplates/contractTemplates/contractPayment!totleMoneyByContractId.action?contractId='+contractId,
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					var num = new Number(datas.msg);
				$("#amount").val(num.toFixed(0));
				}
			});	
}

function onblurpercent()
{
	var percent=$("#percent").val();
	var amount=$("#amount").val();
	var invoicemoney=(amount*1.0)*(percent*1.0/100)
		var num = new Number(invoicemoney);
	$("#invoicemoney").val(num.toFixed(0));
}
//加载用户下拉框
function onSelect(){
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