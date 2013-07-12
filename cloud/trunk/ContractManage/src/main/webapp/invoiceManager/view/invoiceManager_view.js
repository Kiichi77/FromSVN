function fasong(paramsindex,paramstates,id) {
	$.messager.confirm('提示','确定已寄送发票吗？',function(r){  
	if (r){  
				$.post(ctx+ '/invoice/invoice/invoice!updateByStauts.action',{
					id : id
				}, function(data) {
					var data = eval('(' + data + ')');
					if (null != data && "" != data) {
						if(data.msg=="ok"){
						$.messager.alert('提示', "操作成功", 'info');
						$('#tt').datagrid('updateRow',{index:paramsindex,row:{status:"1"}});
						}
					} else {
						$.messager.alert('提示', "系统异常，请稍后操作", 'info');
					}
				});
			}
	});
}

//单元数据总览列布局
var dyDataLayOut = [[  		   
//	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'ID',width:60,align:'center'},
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
	{field:'amount',title:'金额(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'status',title:'状态',width:80,align:'center',
		formatter:function(value,rec,index){
			if(1==value || 2==value) {
				return "已寄送发票";
			} else {
				return "未寄送发票";
			}
		}
	},
	{field:'userName',title:'发票申请人',width:100,align:'center'},
	{field:'createtime',title:'创建时间',width:100,align:'center'},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.id;
			var status=rec.status;
			var edit;
			if(status==1 || status==2)
				{
				 edit='无';
				}else{
			edit='<a href="#" onclick="fasong(\''+index+'\',\''+status+'\',\''+id+'\');">修改寄送状态</a>';
				}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/invoice/invoice/invoice!listInvoice.action',
		width: '95%',
		height: 'auto',
		pagination:true,
//		rownumbers:true,
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
});