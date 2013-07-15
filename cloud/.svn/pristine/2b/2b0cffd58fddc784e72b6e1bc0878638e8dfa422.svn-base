var cloumns = [[  		
	/*{field:'ck',checkbox:true,align:'center'},*/
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'projectId',title:'项目id',width:60,align:'center',hidden:true},
	{field:'contractNumber',title:'合同编号',width:60,align:'center'},
	{field:'contractNo',title:'内部合同编号',width:60,align:'center'},
	{field:'title',title:'合同名称',width:60,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit='<a href="#" onclick="querytable(\''+rec.id+'\')">'+rec.title+'</a>';
			return edit;
		}	
	},
	{field:'projectName',title:'项目名称',width:60,align:'center'},
	{field:'contractTotalPrice',title:'合同总价(元)',width:50,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'contractGoodsPercent',title:'货款比例(%)',width:35,align:'center',
		formatter:function(value,rec,index){
			return value;
		}	
	},
	{field:'contractGoodsPrice',title:'货款(元)',width:45,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}		
	},
	{field:'contractBeginPercent',title:'初验比例(%)',width:35,align:'center',
		formatter:function(value,rec,index){
			return value+'';
		}		
	},
	{field:'contractBeginPrice',title:'初验(元)',width:45,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'contractFinalPercent',title:'终验比例(%)',width:35,align:'center',
		formatter:function(value,rec,index){
			return value+'';
		}			
	},
	{field:'contractFinalPrice',title:'终验(元)',width:45,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'contractTemplatesName',title:'合同模版',width:60,align:'center'},
	{field:'createTime',title:'创建时间',width:80,align:'center',hidden:true},
	{field:'status',title:'状态',width:40,align:'center',
		formatter:function(value,rec,index){
			if(0==value){
				return '进行中';
			}else if(1==value){
				return '下单完成';
			} else if(2==value){
			return '待审核';
		  } else if(3==value){
			return '审核同意';
		  }  else if(4==value){
			return '审核不同意';
		  }  else if(5==value){
			return '审核中';
		  } else if(6==value){
			return '到货';
		  } else if(7==value){
				return '到货款';
			}else if(8==value){
				return '初验';
			} else if(9==value){
				return '初验款';
			 } else if(10==value){
				return '终验';
			 } else if(11==value){
				return '终验款';
			}else if(12==value){
				return '合同完成';
			}
	}},
	{field:'downPath',title:'下载地址',width:80,align:'center',hidden:true},
	{field:'opt',title:'操作',width:100,align:'left',
		formatter:function(value,rec,index){
//		var downPath = ctx+"/download/quotations/"+rec.downPath; //下载地址
		var existFileName = rec.title;
		var fileName = rec.downPath;
		if(fileName!=null && ""!=fileName){
			existFileName += fileName.substring(fileName.indexOf("."),fileName.length);
		}
		var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath; 
		var status = rec.status; //状态
		var id=rec.id;
		var projectId=rec.projectId;//项目id
		var edit='';
		edit+='<a href="#" onclick="contractPaymentBycontractId(\''+id+'\');"><font style="color: red;">金额</font></a>&nbsp;|&nbsp;';
		 edit+='<a href="#" onclick="queryContarctByid(\''+id+'\',\''+status+'\');">修改</a>&nbsp;|&nbsp;';
		if(status!=2 &&status!=4&&status!=5){
			if(status==0||status==3){
				edit+='<a href="#" onclick="showExecuteDIV(\''+id+'\',\''+projectId+'\');">下单</a>&nbsp;|&nbsp;';
			}
			edit+='<a href="'+ctx+'/salesReceivables/salesReceivables.jsp?contractId='+id+'&processid='+process_id+'&id='+userTaskPkid+'"><font style="color: red;">收款</font></a>&nbsp;|&nbsp;';
			edit+='<a href="#" onclick="shengChengcon(\''+id+'\')">打包</a>&nbsp;|&nbsp;';
			edit+='<a href="'+downPath+'">下载</a>&nbsp;|&nbsp;';
		}
		
		if(status==2||status==4){
			edit+='<a href="#" onclick="showDataInfoDiv(\''+id+'\',\''+projectId+'\')">提交</a>&nbsp;|&nbsp;';
		}
//		if(status==0 || status==4)
//			{
//			 edit+='<a href="#" onclick="queryContarctByid(\''+id+'\',\''+status+'\');">修改</a>&nbsp;|&nbsp;';
//			}
		
		//edit += '<a href="#" onclick="deleteContract(\''+id+'\')">删除</a>&nbsp;|&nbsp;';
		//售后流程中销售收款
	/*	if(contract_id==null || contract_id=="null"){
			return edit;
		}else{
			return payment;
		}*/
		return edit;		
	}}
]];	
//初始化该页面所有数据
		$(function(){
			$('#tt').datagrid({
				url: ctx+'/contractTemplates/contractTemplates/contract!myContract.action?id='+contract_id,
				width: '95%',
				height: 'auto',
				rownumbers:true,
				remoteSort: false,
				nowrap: false,
				pagination:true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				showGroup:true,
				columns:cloumns,
				toolbar:[
				         {
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						clearMyForm();
						showWindow(null);
					}}
				         /*,{
					id:'btndelete',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						deleteSomeContract();
					}
				}*/],			
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
			//下拉选择框数据加载         报价表
			var sel1 = $("#title");  
			sel1.empty();//清除select中的 option
			$.post(ctx+'/quotations/quotations/quotations!queryAllQuotations.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].title).val(datas.rows[i].id);  
						sel1.append(opt);  
					}
				}
			});		
			//下拉选择框数据加载         合同模版
			var sel2 = $("#name");  
			sel2.empty();//清除select中的 option
			$.post(ctx+'/contractTemplates/contractTemplates/contractTemplates!queryAllContractTemplates.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
						sel2.append(opt);  
					}
				}
			});
			
			//下拉选择框数据加载  采购人员
			var sel3 = $("#purchase");  
			sel3.empty();//清除select中的 option
			$.post(ctx+'/user/user/user!queryAllPurchase.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
						sel3.append(opt);  
					}
				}
			});
			
			
			
			//下拉选择框数据加载  审核人
			var sel4 = $("#tbUser");  
			sel4.empty();//清除select中的 option
			$.post(ctx+'/user/user/user!queryAllCaudit.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
						sel4.append(opt);  
					}
				}
			});
			
			//下拉选择框数据加载  采购方公司
			var sel5 = $("#purchasepeople");  
			sel5.empty();//清除select中的 option
			$.post(ctx+'/customer111/customer111/purchaseCompany!queryAllPurchaseCompany.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
						sel5.append(opt);  
					}
				}
			});
			
			$(".datagrid-toolbar").append($("#datagridsearch"));
			var sel = $("#projectname");  
			sel.empty();//清除select中的 option
			$.post(ctx+'/projectManage1/projectManage1/project1!queryAllProject.action',function(data){
				//角色下拉选择框数据加载
				var datas = eval("("+data+")");
				$('#projectname').combobox({
					valueField:'id',
					textField:'projectName',
					data:datas.rows
				});
				if(null!=datas.rows[0].id && ""!=datas.rows[0].id){
					$('#projectname').combobox("select",datas.rows[0].id);
				}
			});
		});			

// 创建列菜单
function createColumnMenu() {
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#tt').datagrid('getColumnFields');
	for ( var i = 0; i < fields.length; i++) {
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick : function(item) {
			if (item.iconCls == 'icon-ok') {
				$('#tt').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target : item.target,
					iconCls : 'icon-empty'
				});
			} else {
				$('#tt').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target : item.target,
					iconCls : 'icon-ok'
				});
			}
		}
	});
}
// 弹出层***************************************
//添加存储操信息操作
function showWindow(params) {
	window.location = ctx+"/contract/createcontract/realcreatecontract.jsp";
	//$("#contractids").val(params);
	//$('#createManName').val(nowUser);
	//var div_obj = $("#addVmDiv");  
	//var windowWidth = document.body.clientWidth;       
	//var windowHeight = document.body.clientHeight;  
	//var popupHeight = div_obj.height();       
	//var popupWidth = div_obj.width(); 
	//div_obj.css({"position": "absolute"})
	//.animate({left: windowWidth/5-popupWidth/5,top: windowHeight/5-popupHeight/5, opacity: "show" }, "slow");   
}

//隐藏报价单数据表层
function hicacel(){
	$("#baojiaDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");
}

//隐藏存储操信息操作
function hideDiv() {   
	hicacel();  
	clearMyForm();
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
}  

//隐藏存储操信息操作
function hideDiv1() {   
	$("#corids").val("");
	$("#executeDIV").window('close'); 
	lastIndext = 0;
} 

//隐藏存储操信息操作
function hideDiv2() {   
	clearMyForm();
	$('#PaymentDIV').window('close');
}  

//添加选择的报价单
function addbaojia() {
	// 获取表格选择行
	var rows = $('#baojiabiao').datagrid('getSelections');
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '还未选择报价单!', 'info');
		return;
	}
	var parm = "";
	var parmname = "";
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		parm += n.id + ",";
		parmname += n.title + "、";
	});
	parm = parm.substring(0, parm.length - 1);
	parmname = parmname.substring(0, parmname.length - 1);
	$("#title").val(parm);
	$("#baojianame").val(parmname);
	hicacel();
}

//显示通用单元数量设置框
function showNumberDIV(params,yicaigou,paramindex,totalmoney) {
	$("#yicaigou").val(yicaigou);
	$("#praindex").val(paramindex);
	$("#totalmoney").val(totalmoney);
	var div_obj = $("#numberDIV");  
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/2-popupWidth/4,top: windowHeight/5-popupHeight/5, opacity: "show" }, "slow");   
	var configId = $("#corids").val();
	wantTongyongInfo(params,configId);
}
//隐藏通用单元数量设置框
function hideNumberDIV() {   
	$("#yicaigou").val("");
	$("#praindex").val("");
	$("#totalmoney").val("");
	$("#jxcgsl").val("");
	$("#numberDIV").animate({left: 0, top: 0, opacity: "hide" }, "slow");
} 
