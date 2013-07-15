//单元数据总览列布局
var dyDataLayOut = [[  		   
	//{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'njhfghj',title:'项目名称',width:100,align:'center',
		formatter:function(value,rec,index){
			return projectNAMENAME;
		},hidden:true
	},
	{field:'orderNo',title:'销售订单号',width:80,align:'center'},
	{field:'catalogPrice',title:'目录合价总价(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'purchasePrice',title:'采购总价(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'totalPrice',title:'成交总价(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'inquiryer',title:'毛利率',width:80,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/%/g,"");
			var num = new Number(value);
			return num.toFixed(0)+"%";
		}		
		},
	{field:'cauditTime',title:'审核时间',width:100,align:'center'},
	{field:'status',title:' 订单状态',width:100,align:'center',
		formatter:function(value,rec,index){
			var totalprices = "无效";
			//0:问价中，1：待审核，2：审核同意，3：审核不同意，4：采购中...待定
			if("0"==value) {
				totalprices = "问价中";
			} else if("1"==value) {
				totalprices = "待审核";
			} else if("2"==value) {
				totalprices = "审核同意";
			} else if("3"==value) {
				totalprices = "审核不同意";
			} else if("4"==value) {
				totalprices = "采购中";
			}else if("5"==value) {
				totalprices = "到货";
			}else if("6"==value) {
				totalprices = "完成";
			}
			return totalprices;
		}
	},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var statusId=rec.status;
			var id = rec.id;
			var edit;
			if(rec.status=="1"){
				edit='<a href="#" onclick="showWindow(\''+statusId+'\',\''+id+'\')">审核</a>';
			}else{
				edit='<a href="#" onclick="showWindow(\''+statusId+'\',\''+id+'\')">查看</a>';
			}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'order/order/order!checkInquiryOrder.action?contractOrderId='+contractOrderId,
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
//	 $(".datagrid-toolbar").append($("#datagridsearch"));
});
