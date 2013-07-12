//查看详情配置信息
function queryInfos(param) {
	window.location = "finish.jsp?configsIds="+param;
}

//保存加个总表内容信息
//function saveOrUpdataInfo() {
//	var rows = $('#t11').datagrid('getRows');
//	var json = "[";
//	//循环给提交删除参数赋值(音乐风格编码)
//	$.each(rows, function (i, n) {
//		var totalzs = ""+n.totalPrice;
//		var totz = totalzs.split(".");
//		json += "{'id':'"+n.id+"','contractId':'"+contractIds+"','quotationsId':'"+n.quotationsId+"','quantity':'"+n.quantity+"','projectName':'"+n.projectName+"','configModelId':'"+n.configModelId+"','quotationName':'"+n.quotationName+"','totalPrice':'"+totz[0]+"'}";//
//		if(i!=rows.length-1){
//			json += ',';
//		}
//	});
//	json += "]";
//	$.post(ctx+'quotations/quotations/contractQuotations!saveContractQuotations.action', {
//		contractId : json
//	}, function(data) {
//		if (null != data && "" != data && "ok" != data) {
//			$.messager.alert('提示', "操作成功", 'info');
//		} else {
//			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
//		}
//	});
//}

//初始化界面列表信息
$(function(){
	//报价表中的配置单
	$('#tt1').datagrid({
		url:ctx+'contractTemplates/contractTemplates/contract!contractDetail.action?id='+contractIds,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		pagination:false,
		autoRowHeight: false,
		striped: true,
		toolbar:[{
			id:'btnadd',
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
				var url = 'createcontract.jsp';
				if(backUrl!=''){
					url = ctx+backUrl;
				}
				window.location = url;
			}
		}],
		fitColumns: true,
		showGroup:true,
		columns:oneCluomn,
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#tt1').datagrid('endEdit', lastIndex);
				$('#tt1').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
	var seMoney = 0;
	//报价表中的价格总表
	$('#t11').datagrid({
		url:ctx+'quotations/quotations/contractQuotations!listContractQuotations.action?contractId='+contractIds,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
//		toolbar:[{
//			id:'btnadd',
//			text:'保存',
//			iconCls:'icon-save',
//			handler:function(){
//				$('#t11').datagrid('endEdit',lastIndex1);
//				$('#btnsave').linkbutton('enable');
//				saveOrUpdataInfo();
//			}
//		}],
		pagination:false,
		autoRowHeight: false,
		singleSelect:true,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:threeCluomn,
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
//		onDblClickRow: function(rowIndex, rowData) {
//			seMoney = rowData.quantity;
//			$('#t11').datagrid('endEdit',lastIndex1);
//			$('#t11').datagrid('beginEdit', rowIndex);
//			lastIndex1 = rowIndex;
//		},
//		onAfterEdit: function(rowIndex, rowData, changes) {
//			//总价 c=a*b
//			var totalprices = (rowData.quantity*1.0)*((rowData.totalPrice*1.0)/(seMoney*1.0));
//			$('#t11').datagrid('updateRow',{index:rowIndex,row:{totalPrice:totalprices}});
//		},
//		onClickRow:function(rowIndex, rowData){
//			$('#t11').datagrid('endEdit', lastIndex1);
//			lastIndex1 = rowIndex;
//		}
	});
	//合同模版
	$('#tt').datagrid({
		url:ctx+'contractTemplates/contractTemplates/contract!listContractTemplates.action?id='+contractIds,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		pagination:true,
		toolbar:[{
			id:'btnadd',
			text:'返回',
			iconCls:'icon-back',
			handler:function(){
				var url = 'createcontract.jsp';
				if(backUrl!=''){
					url = ctx+backUrl;
				}
				window.location = url;
			}
		}/*,{
			id:'scfbnt',
			text:'保存合同选中文件',
			iconCls:'icon-print',
			handler:function(){
				//获取表格选择行
				var rows = $('#tt').datagrid('getSelections');
				//判断是否选择行
				if (!rows || rows.length == 0) {
					$.messager.alert('提示', '请选择生成合同文件的数据!', 'info');
					return;
				}
				var json = "[";
				$.each(rows, function (i, n) {
					json += "{'id':'"+n.id+"','fileName':'"+n.docname+"','path':'"+n.path+"'}";
					if(i!=rows.length-1){
						json += ',';
					}
				});
				json += "]";
				$.post(ctx+'contractTemplates/contractTemplates/contractDoc!saveContractDoc.action', {
				    conid:contractIds,
					contractId :json
				}, function(data) {
					if (null != data && "" != data && "ok" != data) {
						$.messager.alert('提示', "操作成功", 'info');
						$('#tt').datagrid("reload");
					} else {
						$.messager.alert('提示', "系统异常，请稍后操作", 'info');
					}
				});
			}
		}*/],
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:twoCluomn,
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#tt').datagrid('endEdit', lastIndext);
				$('#tt').datagrid('beginEdit', rowIndex);
			}
			lastIndext = rowIndex;
		},
		onLoadSuccess:function(rowindex){
			setSelected();
		}
	});		
	
	//设置列表的复选框是否选中
	function setSelected(){
		//获取表格选择行
		var rows = $('#tt').datagrid('getRows');
		//判断是否选择行
		if (!rows || rows.length == 0) {
			return;
		}
		// 循环给提交删除参数赋值(音乐风格编码)
		$.each(rows, function(i, n) {
			var id=n.contractTemplatesId;
			if(0!=id){
				$('#tt').datagrid("selectRow",i);
			}
		});
	}
	//合同模板中的弹出框
	$('#ta').datagrid({
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		singleSelect:true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:tanchuCluomn,
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex,rowData){
			editorAdd(rowData.variableName);
			if (lastIndex1 != rowIndex){
				$('#ta').datagrid('endEdit', lastIndexa);
				$('#ta').datagrid('beginEdit', rowIndex);
			}
			lastIndexa = rowIndex;
		}
	});
	$("#dd").toggle();
	//合同金额查询的弹出框
	$('#tat').datagrid({
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		singleSelect:true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:tanchuMoneyCluomn,
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		}
	});
	$("#mone").toggle();
});

//修改
function updateContract(contractId){
	$('#updateform').form('submit', {
   		url:ctx+"contractTemplates/contractTemplates/contractDoc!updateContractDoc.action",
   		onSubmit: function(){
   			// 做某些检查
   			// 返回 false 来阻止提交
   			return $(this).form('validate'); 
   		},
   		success:function(data){
   			if(data!=null && data!=""){
   				if("error" == data.msg){
   					alert("操作失败，请从新尝试");
   				} else {
   					alert("操作成功");
   					$("#tt").datagrid("reload");
   					$('#updateform').form("clear");
   					$("#updateDIV").window('close');
   				}
   			} else {
   				alert("系统异常，请稍后操作");
   			}
   		}
   	});
}

/*
 *机型配置 
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