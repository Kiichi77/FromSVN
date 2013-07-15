//根据ID删除数据源
function deleteDataSource(id){
	$.messager.confirm('提示','确定要删除选中的数据吗？',function(r){  
	if (r){  
		//设置为同步
		$.ajaxSettings.async = false;
		$.post(
			ctx+'/dataSource/dataSource/dataSource!deleteDataSource.action',
			{id:id},
			function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					 if(data.msg=="ok"){
					    	$.messager.alert("提示","删除成功！");
					    	$("#tt").datagrid("reload");
					    	reloadDataGrid();
					 }else{
					    	$.messager.alert("提示","删除失败！");
					 }
				}
			});
		}
	});
}
			
//批量删除
function deleteSomeData(id) {
	//获取表格选择行
	if(id!=-1){
		$('#tt').datagrid('deleteRow',id);
	}else{
		var rows = $('#tt').datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length == 0) { 
			$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
			return; 
		} 
		var parm = ""; 
		// 循环给提交删除参数赋值(音乐风格编码)
		$.each(rows, function (i, n) {
			$('#tt').datagrid('deleteRow',i);
		});
	}
	
}		
/**
 * 添加，修改采购订单
 */
function showWindow(){
	window.location.href = ctx+"purchaseOrder/addOrders.jsp";
}

/**
 * 查看采购订单下的详情
 */
function showOrderDetail(paramsid){
	window.location.href = ctx+"purchaseOrder/ordersDetail.jsp?paramsid="+paramsid;
}
//清空上传、数据详情表单
function clearVMFormFun(params) {
	if(1 == params) {		//清空数据源上传层
		$("#modelsName").val("");
		$("#modelsmiaoshu").val("");
		$("#modelshangchuan").val("");
	} else {
		$("#dinghuohao").val("");
		$("#chanpinshu").val("");
		$("#danjia").val("");
		$("#zhekoulv").val("");
		$("#chanpinbei").val("");
		$("#chanpinmiaoshu").val("");
		$("#dierbao").val("");
		$("#diyibao").val("");
		$("#disanbao").val("");
		$("#qitafei").val("");
		$("#anzhuangfei").val("");
	}
}

//隐藏存储操信息操作
function hideDiv() {   
	clearVMFormFun(1);
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 

//隐藏数据详情窗口操作
function hideDataInfoDiv() {
	 clearVMFormFun(2);
	 $("#addDataInfoDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}

//重新加载列表
function reloadDataGrid() {
	var kwords = $("#starttime").datebox("getValue").trim();
	var filetype = $("#endtime").datebox("getValue").trim();
	$('#tt').datagrid('load',{"purchaseOrderVo.createTime":kwords,"purchaseOrderVo.endTime":filetype});
}

//加载机型配置详情列表
function showPurchaseOrderInfos(contractOrderId,params) {
	$('#executeDIVInfos').window('open');
	//报价表数据加载
	$('#jixingpeizhi').datagrid({
		url:ctx+'purchaseorder/purchaseorder/purchaseorder!listByContractOrderInfos.action?contractOrderId='+contractOrderId+'&id='+params,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
			{field:'id',title:'id',width:60,align:'center',hidden:true},
			{field:'configId',title:'configId',width:60,align:'center',hidden:true},
			{field:'configModelsName',title:'配置单名称',width:100,align:'center',
				formatter:function(value,rec,index)
                {
					var title=rec.configModelsName;
					var id=rec.configId;
					var edit;
					edit='<a href="#" onclick="wantTongyongInfo(\''+id+'\')">'+title+'</a>';
					return edit;
                }		
			},
			{field:'purchaseQuantity',title:'采购数量',width:50,align:'center'}
		]]
	});
}

//显示销售订单下的采购订单
var lastnumIndext;//表单的行索引
function showPurchaseOrder(params) {
	$('#executeDIVVV').window('open');
	//报价表数据加载
	$('#caigoudan').datagrid({
		url:ctx+'purchaseorder/purchaseorder/purchaseorder!listByContractOrderId1.action?contractOrderId='+params,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
			{field:'id',title:'id',width:60,align:'center',hidden:true},
			{field:'orderNo',title:'采购订单编号',width:80,align:'center',editor:'text'},
			{field:'quoteNo',title:'报价单号',width:50,align:'center',editor:'text'},
//			{field:'purchaseQuantity',title:'采购数量',width:50,align:'center'},
			{field:'configId',title:'配置单ID',width:50,align:'center',hidden:true},
//			{field:'configModelsName',title:'配置单名称',width:100,align:'center',
//				formatter:function(value,rec,index)
//                {
//					var title=rec.configModelsName;
//					var id=rec.configId;
//					var edit;
//					edit='<a href="#" onclick="wantTongyongInfo(\''+id+'\')">'+title+'</a>';
//					return edit;
//                }		
//			},
			{field:'receivertime',title:'收货时间',width:70,align:'center',
				formatter:function(value,rec,index){
					if(null == value || "null" == value) {
						return "";
					}
					return value;
				}
			},
			{field:'status',title:'状态',width:50,align:'center',
				formatter:function(value,rec,index){
					 var status=rec.status;
					 var edit;
					 if(7==status)
						 {
						  edit="到货";
						 }
					 else if(1==status)
						 {
						 edit="采购中";
						 }
					 return edit;
				//	var totalprices = Math.round(value*100)/100;
				//	return totalprices;
				}
			},
			{field:'afterSalesId',title:'售后人员',width:50,align:'center'},//
			{field:'remark',title:'备注',width:80,align:'center'},
			{field:'opt',title:'操作',width:50,align:'center',formatter:function(value,rec,index){
				var edit = '';
				edit +='<a href="#" onclick="showPurchaseOrderInfos(\''+params+'\',\''+rec.id+'\');">查看</a>';
				return edit;
			}
		}
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
		},
		onClickRow:function(rowIndex){
			if (lastnumIndext != rowIndex){
				$('#caigoudan').datagrid('endEdit', lastnumIndext);
				$('#caigoudan').datagrid('beginEdit', rowIndex);
			}
			lastnumIndext = rowIndex;
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			//设置为同步
			$.ajaxSettings.async = false;
			$.post(
				ctx+'purchaseorder/purchaseorder/purchaseorder!UpdateOrder.action',
				{'purchaseOrderVo.id':rowData.id,
				'purchaseOrderVo.orderNo':rowData.orderNo,
				'purchaseOrderVo.quoteNo':rowData.quoteNo},
				function(data){}
			);
		}
	});
}

//隐藏销售订单下的采购订单
function hideexecuteDIVVV(){
	$('#caigoudan').datagrid('endEdit', lastnumIndext);
	$('#executeDIVVV').window('close');
}

//隐藏销售订单下的采购订单
function hideexecuteDIVInfos(){
	$('#executeDIVInfos').window('close');
}

//配置详情
function wantTongyongInfo(id) {
	var div_obj = $("#numberDIV");
	div_obj.window('open');
	
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+id,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
				{field:'id',title:'id',width:60,align:'center',hidden:true},
				{field:'productOrderNo',title:'产品订货号',width:100,align:'center'},
				{field:'name',title:'产品名称',width:100,align:'center'},
				{field:'quantity',title:'数量',width:100,align:'center'},
				{field:'productDesc',title:'产品描述',width:120,align:'center'},
				{field:'categoryName',title:'产品类型',width:120,align:'center'}
				]],  
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
}

function hideNumberDIV()
{
	$('#numberDIV').window('close');
}

/*
 * 机型配置
 */
//为表格4（合计）添加行
function addRowsToTable4() {
	$('#t4').datagrid('appendRow',{ 
		id:"",
		typeId:"",
		productOrderNo:"", 
		name:"", 
		productDesc:"典配总价", 
		quantity:"", 
		unitPrice:"",
		totalprice:""+muluhejia,
		yincangzk:"",
		discountRate:"",
		rebateprice:""+zhekouhoujia,
		otherRates:"",
		rebateafter:""+zhekouhouxianchang,
		installServiceCharge:"",
		firstYear:"",
		secondYear:"",
		thirdYear:"",
		total:""+hejijia
	});
}

//修改表格4的行
function updateRowsToTable4() {
	$('#t4').datagrid('updateRow',{index:0,row:{ 
		productDesc:"典配总价", 
		totalprice:""+muluhejia,
		rebateprice:""+zhekouhoujia,
		rebateafter:""+zhekouhouxianchang,
		total:""+hejijia
	}});
}

//可选配置
var optiona = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'typeId',title:'typeId',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center'},
	{field:'unitPrice',title:'目录单价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			if("" != value) {
				var num = new Number(value);
				return num.toFixed(0);
			} else {
				return "";
			}
		}			
	},
	{field:'totalprice',title:'目录合价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'discountRate',title:'折扣率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			if("" != value) {
				var edit;
				edit=value*100;
				return edit;
			} else {
				return "";
			}
		}
	},
	{field:'rebateprice',title:'折扣后价格(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'otherRates',title:'运保及其他费率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			if("" != value) {
				var edit;
				edit=value*100;
				return edit;
			} else {
				return "";
			}
		}
	},
	{field:'rebateafter',title:'折扣后现场价(元)',width:60,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'installServiceCharge',title:'安装服务费(元)',width:60,align:'center',
		formatter:function(value,rec,index){
			if("" != value) {
				var num = new Number(value);
				return num.toFixed(0);
			} else {
				return "";
			}
		}			
	},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计(元)',width:30,align:'center',
		formatter:function(value,rec,index){
//			value = value.replace(/,/g,"");
			var num = new Number(value);
			return num.toFixed(0);
		}			
	}
]];

function showConfigDIV(configId)
{

	$('#t1').datagrid({
		url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=1",
		width: '95%',
		height: 'auto',
		rownumbers:true,
		title:'基本配置单元',
		remoteSort: false,
		nowrap: false,
		pagination:false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:optiona,
		onLoadSuccess: function(data) {
			//获取表格选择行
			var rows = $('#t1').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			var mlhj = 0;
			var zkhjg = 0;
			var zkhxcj = 0;
			var azfwf = 0;
			var dynbx = 0;
			var denbx = 0;
			var dsnbx = 0;
			var hj = 0;
			$.each(rows, function(i, n) {
				mlhj = mlhj + n.totalprice;
				zkhjg = zkhjg + n.rebateprice;
				zkhxcj = zkhxcj + n.rebateafter;
				azfwf = azfwf + n.installServiceCharge;
				dynbx = dynbx + n.firstYear;
				denbx = denbx + n.secondYear;
				dsnbx = dsnbx + n.thirdYear;
				hj = hj + n.total;
			});
			muluhejia = muluhejia+mlhj;
			zhekouhoujia = zhekouhoujia+zkhjg;
			zhekouhouxianchang = zhekouhouxianchang+zkhxcj;
			hejijia = hejijia+hj;
			$('#t1').datagrid('appendRow',{ 
				id:"",
				typeId:"",
				productOrderNo:"", 
				name:"", 
				productDesc:"基本配置单元小计", 
				quantity:"", 
				unitPrice:"",
				totalprice:""+mlhj,
				yincangzk:"",
				discountRate:"",
				rebateprice:""+zkhjg,
				otherRates:"",
				rebateafter:""+zkhxcj,
				installServiceCharge:""+azfwf,
				firstYear:""+dynbx,
				secondYear:""+denbx,
				thirdYear:""+dsnbx,
				total:""+hj
			});
			updateRowsToTable4();
		}
	});
	
		$('#t2').datagrid({
			url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=2",
			width: '95%',
			height: 'auto',
			title:'同系列通用单元',
			rownumbers:true,
			remoteSort: false,
			nowrap: false,
			pagination:false,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			fitColumns: true,
			showGroup:true,
			columns:optiona,
			onLoadSuccess: function(data) {
				//获取表格选择行
				var rows = $('#t2').datagrid('getRows');
				//获得模板与合同模板文件之间的关系，并勾选相应文件
				// 循环给提交删除参数赋值(合同文件ID)
				var mlhj = 0;
				var zkhjg = 0;
				var zkhxcj = 0;
				var azfwf = 0;
				var dynbx = 0;
				var denbx = 0;
				var dsnbx = 0;
				var hj = 0;
				$.each(rows, function(i, n) {
					mlhj = mlhj + n.totalprice;
					zkhjg = zkhjg + n.rebateprice;
					zkhxcj = zkhxcj + n.rebateafter;
					azfwf = azfwf + n.installServiceCharge;
					dynbx = dynbx + n.firstYear;
					denbx = denbx + n.secondYear;
					dsnbx = dsnbx + n.thirdYear;
					hj = hj + n.total;
				});
				muluhejia = muluhejia+mlhj;
				zhekouhoujia = zhekouhoujia+zkhjg;
				zhekouhouxianchang = zhekouhouxianchang+zkhxcj;
				hejijia = hejijia+hj;
				$('#t2').datagrid('appendRow',{ 
					id:"",
					typeId:"",
					productOrderNo:"", 
					name:"", 
					productDesc:"同系列通用单元小计", 
					quantity:"", 
					unitPrice:"",
					totalprice:""+mlhj,
					yincangzk:"",
					discountRate:"",
					rebateprice:""+zkhjg,
					otherRates:"",
					rebateafter:""+zkhxcj,
					installServiceCharge:""+azfwf,
					firstYear:""+dynbx,
					secondYear:""+denbx,
					thirdYear:""+dsnbx,
					total:""+hj
				});
				updateRowsToTable4();
			}
		});
		
		$('#t3').datagrid({
			url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+configId + "&typeId=3",
			width: '95%',
			height: 'auto',
			rownumbers:true,
			title:'全通用单元',
			remoteSort: false,
			nowrap: false,
			pagination:false,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			fitColumns: true,
			showGroup:true,
			columns:optiona,
			onLoadSuccess: function(data) {
				//获取表格选择行
				var rows = $('#t3').datagrid('getRows');
				//获得模板与合同模板文件之间的关系，并勾选相应文件
				// 循环给提交删除参数赋值(合同文件ID)
				var mlhj = 0;
				var zkhjg = 0;
				var zkhxcj = 0;
				var azfwf = 0;
				var dynbx = 0;
				var denbx = 0;
				var dsnbx = 0;
				var hj = 0;
				$.each(rows, function(i, n) {
					mlhj = mlhj + n.totalprice;
					zkhjg = zkhjg + n.rebateprice;
					zkhxcj = zkhxcj + n.rebateafter;
					azfwf = azfwf + n.installServiceCharge;
					dynbx = dynbx + n.firstYear;
					denbx = denbx + n.secondYear;
					dsnbx = dsnbx + n.thirdYear;
					hj = hj + n.total;
				});
				muluhejia = muluhejia+mlhj;
				zhekouhoujia = zhekouhoujia+zkhjg;
				zhekouhouxianchang = zhekouhouxianchang+zkhxcj;
				hejijia = hejijia+hj;
				$('#t3').datagrid('appendRow',{ 
					id:"",
					typeId:"",
					productOrderNo:"", 
					name:"", 
					productDesc:"全通用单元小计", 
					quantity:"", 
					unitPrice:"",
					totalprice:""+mlhj,
					yincangzk:"",
					discountRate:"",
					rebateprice:""+zkhjg,
					otherRates:"",
					rebateafter:""+zkhxcj,
					installServiceCharge:""+azfwf,
					firstYear:""+dynbx,
					secondYear:""+denbx,
					thirdYear:""+dsnbx,
					total:""+hj
				});
				updateRowsToTable4();
			}
		});
		
		$('#t4').datagrid({
			width: '95%',
			height: 'auto',
			rownumbers:true,
			remoteSort: false,
			nowrap: false,
			pagination:false,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			fitColumns: true,
			showGroup:true,
			columns:[[
			      	{field:'id',title:'',width:60,align:'center',hidden:true},
			    	{field:'typeId',title:'',width:60,align:'center',hidden:true},
			    	{field:'productOrderNo',title:'',width:60,align:'center'},
			    	{field:'name',title:'',width:100,align:'center'},
			    	{field:'productDesc',title:'',width:200,align:'center'},
			    	{field:'quantity',title:'',width:60,align:'center'},
			    	{field:'unitPrice',title:'',width:60,align:'center',
			    		formatter:function(value,rec,index){
//			    			value = value.replace(/,/g,"");
			    			if("" != value) {
			    				var num = new Number(value);
			    				return num.toFixed(0);
			    			} else {
			    				return "";
			    			}
			    		}			
			    	},
			    	{field:'totalprice',title:'目录合价(元)',width:60,align:'center',
			    		formatter:function(value,rec,index){
//			    			value = value.replace(/,/g,"");
			    			var num = new Number(muluhejia);
			    			return num.toFixed(0);
			    		}			
			    	},
			    	{field:'discountRate',title:'',width:50,align:'center',
			    		formatter:function(value,rec,index){
			    			if("" != value) {
			    				var edit;
			    				edit=value*100;
			    				return edit;
			    			} else {
			    				return "";
			    			}
			    		}
			    	},
			    	{field:'rebateprice',title:'折扣后价格(元)',width:60,align:'center',
			    		formatter:function(value,rec,index){
//			    			value = value.replace(/,/g,"");
			    			var num = new Number(zhekouhoujia);
			    			return num.toFixed(0);
			    		}			
			    	},
			    	{field:'otherRates',title:'',width:50,align:'center',
			    		formatter:function(value,rec,index){
			    			if("" != value) {
			    				var edit;
			    				edit=value*100;
			    				return edit;
			    			} else {
			    				return "";
			    			}
			    		}
			    	},
			    	{field:'rebateafter',title:'折扣后现场价(元)',width:60,align:'center',
			    		formatter:function(value,rec,index){
//			    			value = value.replace(/,/g,"");
			    			var num = new Number(zhekouhouxianchang);
			    			return num.toFixed(0);
			    		}			
			    	},
			    	{field:'installServiceCharge',title:'',width:60,align:'center',
			    		formatter:function(value,rec,index){
			    			if("" != value) {
			    				var num = new Number(value);
			    				return num.toFixed(0);
			    			} else {
			    				return "";
			    			}
			    		}			
			    	},
			    	{field:'firstYear',title:'',width:60,align:'center'},
			    	{field:'secondYear',title:'',width:60,align:'center'},
			    	{field:'thirdYear',title:'',width:60,align:'center'},
			    	{field:'total',title:'合计(元)',width:30,align:'center',
			    		formatter:function(value,rec,index){
//			    			value = value.replace(/,/g,"");
			    			var num = new Number(hejijia);
			    			return num.toFixed(0);
			    		}			
			    	}
			    ]]
		});
		addRowsToTable4();

	$('#configDIV').window('open');
}

function hideConfigDIV()
{
	$('#configDIV').window('close');
}
