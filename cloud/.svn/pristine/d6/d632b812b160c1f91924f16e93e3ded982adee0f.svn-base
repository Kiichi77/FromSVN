//重新加载销售订单列表列表
function querySaleOrder() {
	var kwords = $("#kwords").val();
	if(null != kwords) {
		kwords = kwords.trim();
	}
	$('#tt').datagrid('load',{"contractOrderVo.contractNo":kwords});
}
//查看销售订单详情
function queryInfos(parasm) {
	window.location = "salesOrderInfo/salesOrderInfo.jsp";
}

//查询并填充订单信息
//弹出合同执行选择层
function showExecuteDIV(contractOrderId) {
	$('#executeDIV').window('open');
	//初始化表单
	$('#execute').datagrid({
		url: ctx+'/order/order/order!findOrderDetailByContractId.action?contractOrderId='+contractOrderId,
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
				{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
				{field:'configModelName',title:'机型配置单名称',width:100,align:'center',
					formatter:function(value,rec,index)
                    {
						var configModelId=rec.configModelId;
						var configModelName=rec.configModelName;
						var edit;
						edit='<a href="#" onclick="showConfigDIV(\''+configModelId+'\')">'+configModelName+'</a>';
						return edit;
                    }				
				},
				{field:'totalPrice',title:'单价',width:80,align:'center',
					formatter:function(value,rec,index){
						var totalprices = (value*1.0)/(rec.quantity*1.0);
						totalprices = Math.round(totalprices*100)/100;
						var num = new Number(totalprices);
						return num.toFixed(0);
					}
				},
				{field:'quantity',title:'采购数',width:50,align:'center',editor:'text'},
				{field:'subtotal',title:'小计',width:80,align:'center',
					formatter:function(value,rec,index){
						var totalprices = Math.round(rec.totalPrice*100)/100;
						var num = new Number(totalprices);
						return num.toFixed(0);
					}
				}/*,
				{field:'opt',title:'查看通用单元',width:80,align:'center',
					formatter:function(value,rec,index){
						var id = rec.id;
						var edit = '<a href="#" onclick="wantTongyongInfo(\''+id+'\')">查看通用配置</a>';//
						return edit;
					}
				}*/
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
}

//隐藏存储操信息操作
function hideDiv1() {   
	$('#executeDIV').window('close');
}

//通用单元表单
function wantTongyongInfo(contractOrderId) {
	var div_obj = $("#numberDIV");
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/2, opacity: "show" }, "slow");
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'/order/order/order!findCurrentUnitDetail.action?contractOrderShipId='+contractOrderId,
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
				{field:'productDesc',title:'单元名称描述',width:100,align:'center'},
				{field:'quantity',title:'总数',width:50,align:'center'},
				{field:'unitPrice',title:'单价',width:80,align:'center',
					formatter:function(value,rec,index){
						var totalprices = Math.round(value*100)/100;
						return totalprices;
					}
				},
				{field:'ordered',title:'采购数',width:50,align:'center'},//
				{field:'totalPrice',title:'小计',width:80,align:'center'}
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
}
//显示销售订单下的采购订单
function showPurchaseOrder(params) {
	$('#executeDIVVV').window('open');
	//报价表数据加载
	$('#caigoudan').datagrid({
		url:ctx+'/purchaseorder/purchaseorder/purchaseorder!listByContractOrderId1.action?contractOrderId='+params,
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
			{field:'orderNo',title:'订单编号',width:80,align:'center'},
			{field:'quoteNo',title:'报价单号',width:50,align:'center'},
			{field:'purchaseQuantity',title:'采购数量',width:50,align:'center'},
			{field:'configModelsName',title:'配置单名称',width:100,align:'center'},
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
//					var totalprices = Math.round(value*100)/100;
//					return totalprices;
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
				}
			},
			{field:'afterSalesId',title:'售后人员',width:50,align:'center'},//
			{field:'remark',title:'备注',width:80,align:'center'}
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
}

//隐藏销售订单下的采购订单
function hideexecuteDIVVV(){
	$('#executeDIVVV').window('close');
}

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