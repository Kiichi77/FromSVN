
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
	{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
	{field:'danjia',title:'单价',width:80,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'quantity',title:'采购数',width:30,align:'center',editor:'text'},
	{field:'catalogPrice',title:'目录合价总价(元)',width:80,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'purchasePrice',title:'采购总价(元)',width:80,align:'center',
		formatter:function(value,rec,index){
			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'totalPrice',title:'成交总价(元)',width:80,align:'center',
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
	{field:'suppliersQuotationUrl',title:'供应商报价单下载地址',width:80,align:'center',hidden:true},
	{field:'opt',title:'操作',width:100,align:'center',
		formatter:function(value,rec,index){
			var id = rec.configModelId;
			var url = rec.suppliersQuotationUrl;
			var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+url+"&exportPath="+exportPath; 
			var edit = '<a href="#" onclick="wantConfigInfo(\''+id+'\')">查看机型配置详情</a>&nbsp;|&nbsp;<a href="'+downPath+'">下载供应商报价单</a>';//<a href="#" onclick="wantTongyongInfo(\''+id+'\')">查看通用配置</a> | 
			return edit;
		}
	}			
]];
var lastIndex = 0;
$(function(){
	$('#tt').datagrid({
		url:  ctx+'order/order/order!checkInquiryOrderDetail.action?contractOrderId='+contractOrderId,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
	    pagination:false,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		toolbar:[{
			id:'btnadd',
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
				$('#btnadd').linkbutton('enable');
				window.location.href = ctx+"InquiryOrder/orderChecked.jsp";
			}
		},{
			id:'btndelete',
			text:'审核',
			iconCls:'icon-ok',
			disabled:iscanOpen,
			handler:function(){
				orderChecked();
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
			//获取表格选择行
			var rows = $('#tt').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			$.each(rows, function(i, n) {
				var danjia = (n.totalPrice*1.0)/(n.quantity*1.0);
				danjia = Math.round(danjia*100)/100;
				var totalprices = Math.round(n.totalPrice*100)/100;
				$('#tt').datagrid('updateRow',{index:i,row:{danjia:danjia}});
			});
		}
	});
	if(1==isCanAnble){ //审核通过
		$('#btndelete').linkbutton('enable'); //禁用
	}else{
		$('#btndelete').linkbutton('disable'); //启用
	}
});
