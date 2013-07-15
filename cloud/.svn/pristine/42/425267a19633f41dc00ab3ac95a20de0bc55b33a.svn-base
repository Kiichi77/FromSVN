var suppliers; //供应商
var purchaseCompany; //采购方
function getSuppliers(){
	$.post(ctx+'suppliers/suppliers/suppliers!listSuppliers.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				suppliers = datas.rows;
			}
	});
}

function getPurchaseCompany(){
	$.post(ctx+'customer111/customer111/purchaseCompany!queryAllPurchaseCompany.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				purchaseCompany = datas.rows;
			}
	});
}

var oneCluomn = [[  		
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'configId',title:'configId',width:60,align:'center',hidden:true},
	{field:'sourceId',title:'所属数据源ID',width:60,align:'center',hidden:true},
	{field:'sourceName',title:'所属数据源',width:100,align:'center'},
	{field:'configName',title:'配置单名称',width:100,align:'center'},
	{field:'totalPrice',title:'价格',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'opt',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
		var id=rec.configId;
		var edit;
		edit='<a href="#" onclick="showConfigDIV(\''+id+'\');">详情</a>';
		return edit;
	}} 
]];
var twoCluomn = [[  		
	{field:'id',title:'ID',width:60,align:'center',hidden:true}, //合同模板文件id
	{field:'contractTemplatesId',title:'contractTemplatesId',width:60,align:'center',hidden:true}, //合同选中文件id
	{field:'templateDocName',title:'模板文件名称',width:100,align:'center'},
	{field:'typeName',title:'分类',width:100,align:'center'},
	{field:'docname',title:'合同文件名称',width:100,align:'center'},
	{field:'path',title:'文件路径',width:100,align:'center',hidden:true,
		formatter:function(value,rec,index){
			var url = ctx+'/online/online/preview!word2007.action?file='+templatesWordPath +rec.path+'&title='+rec.docname;
			var edit = '<a href="#" onclick="open1(\''+url+'\');">'+rec.path+'</a>';
			return edit;
		}},
	{field:'bVariable',title:'设置变量',width:100,align:'center',hidden:true,
		formatter:function(value,rec,index){
		if("1"==value){
			return '是';
		} else if("2"==value){
			return '否';
		}
	}},
	{field:'opt',title:'操作',width:80,align:'center',hidden:true,
		formatter:function(value,rec,index){
		var id=rec.contractTemplatesId;
		var fileName = rec.docname;
		var ppath = rec.path;
		var edit = '<a href="#" onclick="openUpdateWindow(\''+id+'\',\''+fileName+'\',\''+ppath+'\');">修改文件名</a>';
		if("1"==rec.bVariable){
			if(0!=id){
				edit +=' | <a href="#" onclick="openVarailWin(\''+id+'\');">设置变量值</a> | <a href="#" onclick="open2(\''+id+'\');">金额</a>';
			}else{
				edit +=' | 设置变量值 | 查看金额';
			}
		} else if("2"==rec.bVariable){
			edit += '';
		}
		return edit;
	}}
]];          	
var threeCluomn = [[  		
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'contractId',title:'contractId',width:60,align:'center',hidden:true},
	{field:'configModelId',title:'configModelId',width:60,align:'center',hidden:true},
	{field:'quotationsId',title:'quotationsId',width:60,align:'center',hidden:true},
	{field:'projectName',title:'项目名称',width:100,align:'center',editor:'text'},
	{field:'quotationName',title:'型号',width:100,align:'center',editor:'text'},
	{field:'unit',title:'单位',width:100,align:'center',
		formatter:function(value,rec,index){
		return	"台";
	}},
	{field:'quantity',title:'数量',width:100,align:'center',editor:'text'},
	{field:'totalPrice',title:'总价',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	}
]];  

var tanchuCluomn = [[  		
	{field:'id',title:'ID',width:60,align:'center',hidden:true}, //,hidden:true
	{field:'contractDocId',title:'templatesDocId',width:60,align:'center',hidden:true}, //,hidden:true
	{field:'variableName',title:'变量名',width:80,align:'center',hidden:true}, //,hidden:true
	{field:'description',title:'描述',width:80,align:'center'},
	{field:'content',title:'内容',width:80,align:'center',formatter:function(value,rec,index){
		var  variable = rec.variableName;
		if(variable=="$seller"){ //卖方
			for(var i=0; i<purchaseCompany.length; i++){
				if (purchaseCompany[i].id == value){ 
					return purchaseCompany[i].companyName;
				}else if(value==null || value=="" || value=="null"){
					xiang(purchaseCompany[0]);
					return purchaseCompany[0].companyName;
				}else{
					return value;
				}
			}
		}else{
			return value;
		}
	}}
]]; 

function xiang(obj) {
	var myrows = $("#ta").datagrid('getRows');
	$.each(myrows, function(i, n) {
		if(n.variableName!=null){
			if("$openBank"==n.variableName){ //设置开户行
				$('#ta').datagrid('updateRow',{index:i,row:{'content':obj.accountOpeningBank}});
			}else if("$accountName"==n.variableName){ //设置账户名称
				$('#ta').datagrid('updateRow',{index:i,row:{'content':obj.accountName}});
			}else if("$accountNumber"==n.variableName){ //设置开户账号
				$('#ta').datagrid('updateRow',{index:i,row:{'content':obj.accountNumber}});
			}else if("$seller"==n.variableName){ //设置选中的卖方
				$('#ta').datagrid('updateRow',{index:i,row:{'content':obj.companyName}});
			}
		}
	});
}

function editorAdd(variableName){
	if(variableName=="$seller"){ //卖方
		$("#ta").datagrid('addEditor', {
			field : 'content',
			editor:{type:'combobox',
					options:{
						textField:'companyName',
						valueField:'id',
						data:purchaseCompany,
						onSelect:function(value){
							for(var i=0; i<purchaseCompany.length; i++){
								if (purchaseCompany[i].id == value.id) {
									xiang(purchaseCompany[i]);
								}
							}
						},
						required:true
					}}
		});
	}else if(variableName=="$openBank" || variableName=="$accountName" || variableName =="$accountNumber"){
		$("#ta").datagrid('removeEditor', {
			field : 'content',
			editor : "text"
		});
	}else{
		$("#ta").datagrid('addEditor', {
			field : 'content',
			editor : "text"
		});
	}
}

var tanchuMoneyCluomn = [[ 
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'variableName',title:'变量名',width:100,align:'center',hidden:true},
	{field:'description',title:'描述',width:80,align:'center'},
	{field:'content',title:'内容',width:100,align:'center'}
]];            	

//弹出层***************************************
//添加存储操信息操作
function showWindow() {
	var div_obj = $("#addVmDiv");  
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
}

//隐藏存储操信息操作
function hideDiv() {   
	$("#mask").remove();   
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}  

//弹出log：变量设置
function showlog(){
	$('#dd').dialog({
		buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
				$('#ta').datagrid('endEdit', lastIndexa);
				var rows = $('#ta').datagrid('getRows');
				var json = "[";
				//循环给提交删除参数赋值(音乐风格编码)
				$.each(rows, function (i, n) {
					json += "{'id':'"+n.id+"','contractDocId':'"+n.contractDocId+"','variableName':'"+n.variableName+"','content':'"+n.content+"'}";
					if(i!=rows.length-1){
						json += ',';
					}
				});
				json += "]";
				$.post(ctx+'variable/variable/templatesDocVariable!saveUpdataContractDocVariable.action', {
					id : json,
					templatesDocId : contractIds
				}, function(data) {
					if (null != data && "" != data && "ok" != data) {
						$.messager.alert('提示', "操作成功", 'info');
					} else {
						$.messager.alert('提示', "系统异常，请稍后操作", 'info');
					}
				});
			}
		},{
			text:'关闭',
			handler:function(){
				$('#dd').dialog('close');
			}
		}]
	});
};

//弹出log:金额查看
function showlog2(){
	$('#mone').dialog({
		buttons:[{
			text:'确认',
			handler:function(){
				$('#mone').dialog('close');
			}
		}]
	});
};

//弹出变量修改窗口
function openVarailWin(params){
	getSuppliers();
	getPurchaseCompany();
	cotrostId = params;
	showlog();
	$('#ta').datagrid("options").url = ctx+'variable/variable/contractDocVariable!listContractDocVariable.action';
	$('#ta').datagrid('load',{
		'contractDocId':params  
	});
	$('#dd').dialog('open');
}
function handler(){
	$('#ta').datagrid('acceptChanges');
}

//弹出变量修改窗口
function open2(params){
	cotrostId = params;
	showlog2();
	$('#tat').datagrid("options").url = ctx+'variable/variable/templatesDocVariable!queryPrice.action';
	$('#tat').datagrid('load',{
		'id':params  
	});
	$('#mone').dialog('open');
}
function handler2(){
	$('#tat').datagrid('acceptChanges');
}

//弹出修改文件名的窗口
function openUpdateWindow(id,fileName,exportPath){
	$("#contractId").val(id);
	$("#fileName").val(fileName);
	$('#ppath').val(exportPath);
	var index = exportPath.lastIndexOf(".");
	exportPath = exportPath.substring(index+1,exportPath.length);
//	if('docx'==exportPath){
//		$('#unit').text('.docx');
//	}else if('jpg' == exportPath){
//		$('#unit').text('.jpg');
//	}
	$("#updateDIV").window('open');
}
//影藏弹出框
function hide(){
	 $("#updateDIV").window('close');
}

function open1(url){
	var theNewWin = window.open(url, 'newwindow', 'height=600px, width=800px, top=160, left=410, toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no');
}	