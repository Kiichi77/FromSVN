//单元数据总览列布局
var dyDataLayOut = [[  		   
/*	{field:'ck',checkbox:true,align:'center'},*/
	{field:'dataId',title:'ID',width:60,align:'center',hidden:true},
	{field:'projectName',title:'所属项目',width:100,align:'center'},
	{field:'contractNo',title:'合同编号',width:100,align:'center'},
	{field:'contractId',title:'合同ID',width:100,align:'center',hidden:true},
	{field:'contractName',title:'合同名称',width:100,align:'center'},
	{field:'buyerCompany',title:'买家公司',width:100,align:'center'},
//	{field:'sellerCompany',title:'卖家公司',width:100,align:'center'},
	{field:'money',title:'收款金额(元)',width:100,align:'center'},
	{field:'payType',title:'收款类型',width:80,align:'center',
		formatter:function(value,rec,index){
			if(11==value) {
				return "收货";
			} else if(12==value) {
				return "初验";
			}else if(13==value) {
				return "终验";
			}else{
				return "未知";
			}
		}
	},
	{field:'receiveTime',title:'预计到账时间',width:100,align:'center'},
	{field:'notes',title:'备注',width:100,align:'center'},
	{field:'status',title:'状态',width:80,align:'center',
		formatter:function(value,rec,index){
			if(1==value) {
				return "已收款";
			} else {
				return "未收款";
			}
		}
	},
	{field:'buyerAccount',title:'买方银行帐号',width:100,align:'center'},
	{field:'sellerAccount',title:'卖方银行帐号',width:100,align:'center'},
	{field:'accpetTime',title:'到账时间',width:100,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.dataId;
			var status=rec.status;
			var payType=rec.payType;
			var contractId=rec.contractId;
			var edit;
			if(status!=1){
				edit='<a href="#" onclick="showWindow('+id+','+payType+','+contractId+');">确认收款</a>';
			}else{
				edit='无';
			}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/contractTemplates/contractTemplates/contractPayment!paymentDataList.action?id='+contractId,
		width: '95%',
		height: 'auto',
	//	pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
	    pagination:true,
		fitColumns: true,
	//	showGroup:true,
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
	 
	//加载用户下拉框
	onSelect();
});

//加载用户下拉框
function onSelect(){
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'/user/user/user!queryUserListByrUserTaskUrlId.action',
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
				$("#checker").hide();
			}
		}
	});	
};