var suppliers; //供应商
var purchaseCompany; //采购方
function getSuppliers(){
	$.post(ctx+'/suppliers/suppliers/suppliers!listSuppliers.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				suppliers = datas.rows;
			}
	});
}

function getPurchaseCompany(){
	$.post(ctx+'/customer111/customer111/purchaseCompany!queryAllPurchaseCompany.action',
		function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				purchaseCompany = datas.rows;
			}
	});
}

var twoCluomn = [[  		
	{field:'id',title:'ID',width:60,align:'center',hidden:true}, //合同模板文件id
	{field:'contractTemplatesId',title:'contractTemplatesId',width:60,align:'center',hidden:true}, //合同选中文件id
	{field:'templateDocName',title:'模板文件名称',width:100,align:'center'},
	{field:'typeName',title:'分类',width:100,align:'center'},
	{field:'docname',title:'合同文件名称',width:100,align:'center'},
	{field:'path',title:'文件路径',width:100,align:'center',hidden:true},
	{field:'bVariable',title:'设置变量',width:100,align:'center',hidden:true,
		formatter:function(value,rec,index){
		if("1"==value){
			return '是';
		} else if("2"==value){
			return '否';
		}
	}},
	{field:'opt',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
		var id=rec.contractTemplatesId;
		var fileName = rec.docname;
		var ppath = rec.path;
		var edit = '<a href="#" onclick="openUpdateWindow(\''+id+'\',\''+fileName+'\',\''+ppath+'\');">修改文件名</a>';
		if("1"==rec.bVariable){
			if(contractStatue==2 || contractStatue==4 || contractStatue==null || contractStatue ==""){ //当合同状态为待审核和审核不同意的时候才能修改
				if(0!=id){
					edit +=' | <a href="#" onclick="open1(\''+id+'\');">设置变量值</a> | <a href="#" onclick="open2(\''+id+'\');">查看金额</a>';
				}else{
					edit +=' | 设置变量值 | 查看金额';
				}
			}else{
				edit +=' | 设置变量值 | 查看金额';
			}
		} else if("2"==rec.bVariable){
			edit += '';
		}
		return edit;
	}}
]];  
var tanchuMoneyCluomn = [[ 
                      	{field:'id',title:'ID',width:60,align:'center',hidden:true},
                      	{field:'variableName',title:'变量名',width:100,align:'center',hidden:true},
                      	{field:'description',title:'描述',width:80,align:'center'},
                      	{field:'content',title:'内容',width:100,align:'center'}
                      ]];  
var tanchuCluomn = [[  		
                 	{field:'id',title:'ID',width:60,align:'center',hidden:true},
                 	{field:'contractDocId',title:'templatesDocId',width:60,align:'center',hidden:true},
                 	{field:'variableName',title:'变量名',width:80,align:'center',hidden:true},
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
                	}
                 	}
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

var contractTemplateCluomn = [[  		
{field:'id',title:'ID',width:60,align:'center',hidden:true}, //合同模板文件id
{field:'docname',title:'模板文件名称',width:100,align:'center'},
{field:'createtime',title:'创建时间',width:100,align:'center'},
{field:'typeName',title:'合同模版文件类型',width:100,align:'center'},
{field:'path',title:'地址',width:100,align:'center',hidden:true},
{field:'bVariable',title:'设置变量',width:100,align:'center',hidden:true,
	formatter:function(value,rec,index){
	if("1"==value){
		return '是';
	} else if("2"==value){
		return '否';
	}
}}
                  ]];  
//弹出log：变量设置
function showlog(contractIds){
	$('#dd').dialog({
		buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
				$('#ta').datagrid('endEdit', lastIndexa);
				var rows = $('#ta').datagrid('getRows');
			    json = "[";
				//循环给提交删除参数赋值(音乐风格编码)
				$.each(rows, function (i, n) {
					json += "{'id':'"+n.id+"','contractDocId':'"+n.contractDocId+"','variableName':'"+n.variableName+"','content':'"+n.content+"'}";
					if(i!=rows.length-1){
						json += ',';
					}
				});
				json += "]";
				$.post(ctx+'/variable/variable/templatesDocVariable!saveUpdataContractDocVariable.action', {
					id : json,
					templatesDocId : contractIds
				}, function(data) {
					if (null != data && "" != data && "ok" != data) {
						$.messager.alert('提示', "操作成功", 'info');
						$('#dd').dialog('close');
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

//弹出变量修改窗口
function open1(params){
	getSuppliers();
	getPurchaseCompany();
	var contractids=$("#contractids").val();
	if(null == contractids || "" == contractids){
		$.messager.alert('提示', "大哥您还没创合同呢", 'info');
	}else{
		cotrostId = params;
		showlog(contractids);
		$('#ta').datagrid("options").url = ctx+'/variable/variable/contractDocVariable!listContractDocVariable.action';
		$('#ta').datagrid('load',{
			'contractDocId':params  
		});
		$('#dd').dialog('open');
	}
}
function handler(){
	$('#ta').datagrid('acceptChanges');
}
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
function open2(params){
	cotrostId = params;
	showlog2();
	$('#tat').datagrid("options").url = ctx+'/variable/variable/templatesDocVariable!queryPrice.action';
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
	$("#updateDIV").window('open');
}

//隐藏数据详情窗口操作
function hideDiv() {
	 $("#updateDIV").window('close'); 
}
$(function(){
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
			if (lastIndex != rowIndex){
				$('#ta').datagrid('endEdit', lastIndexa);
				$('#ta').datagrid('beginEdit', rowIndex);
			}
			lastIndexa = rowIndex;
		}
	});
	$("#dd").toggle();
	//查看金额
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
	
	//获取正式合同编号
	if(pkContractId==null || pkContractId==""){
		officialContractNo();
	}
})

//获取正式合同编号
function officialContractNo(){
	$.post(ctx+ '/contractTemplates/contractTemplates/contract!officialContractNo.action', function(data) {
		var data = eval('(' + data + ')');
		if (null != data && "" != data && "ok" == data.msg) {
			$("#contractnumber").val(data.contractNo);
		} else {
			$.messager.alert('提示', "出现异常");
		}
	});
}