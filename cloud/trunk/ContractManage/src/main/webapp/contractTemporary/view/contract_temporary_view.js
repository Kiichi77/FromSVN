//自动生成订单号
function officialOrderNo(contracid){
	$.post(ctx+ '/order/order/order!generateSaleOrder.action?contractId='+contracid+'&typeId=2', function(data) {
		var data = eval('(' + data + ')');
		if (null != data && "" != data && "ok" == data.msg) {
			$("#ordernum").val(data.orderNO);
		} else {
			$.messager.alert('提示', "出现异常");
		}
	});
}
//弹出合同执行选择层
//contracid:临时合同ID
function showExecuteDIV(contracid) {
	 $("#executeDIV").window('open');
	 $("#corids").val(contracid);
	 officialOrderNo(contracid);
	//报价表数据加载
		$('#execute').datagrid({
			url: ctx+'/contractTemplates/contractTemplates/contractTemporary!queryAllConfigModels.action?id='+contracid,
			width: '95%',
			height: 'auto',
			pagination:false,
			rownumbers:true,
			remoteSort: false,
			nowrap: true,
			title:'机型配置',
			autoRowHeight: false,
			striped: true,
			fitColumns: true,
			columns:[[ 
					{field:'ck',checkbox:true,align:'center'},
					{field:'contractId',title:'ContractId',width:60,align:'center',hidden:true},
					{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
					{field:'quotationsId',title:'QuotationsId',width:60,align:'center',hidden:true},
					{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
					{field:'quantity',title:'总数',width:50,align:'center'},
					{field:'totalPrice',title:'隐藏总价',width:50,align:'center',hidden:true},//,hidden:true
					{field:'danjia',title:'单价(元)',width:80,align:'center'},
					{field:'ordered',title:'已下单总数',width:50,align:'center'},
					{field:'remainingQuantity',title:'下单数',width:50,align:'center',editor:'text'},//,editor:'text'
					{field:'soutotal',title:'小计(元)',width:80,align:'center'}
					]],  
			onLoadSuccess: function(data){
				// 获取表格行
				var rows = $('#execute').datagrid('getRows');
				// 循环给提交删除参数赋值
				$.each(rows, function(i, n) {
					var danjia = (n.totalPrice*1.0)/(n.quantity*1.0);
					danjia = Math.round(danjia*100)/100;
					var num = new Number(danjia);
					danjia = num.toFixed(0);
					var totalprices = danjia*n.remainingQuantity;
					var num1 = new Number(totalprices);
					totalprices = num1.toFixed(0);
					$('#execute').datagrid('updateRow',{index:i,row:{soutotal:totalprices,danjia:danjia}});
				});
			},
			onClickRow:function(rowIndex, rowData){
				if (lastIndext != rowIndex){
					$('#execute').datagrid('endEdit', lastIndext);
					$('#execute').datagrid('beginEdit', rowIndex);
				}
				lastIndext = rowIndex;
			},
			onAfterEdit: function(rowIndex, rowData, changes) {
				var cha=rowData.quantity*1.0-rowData.ordered*1.0-rowData.remainingQuantity*1.0;
				if(cha<0){
					$.messager.alert('提示', "下单数超过了总数", 'info');
				}else{
					var total=(rowData.danjia*1.0)*(rowData.remainingQuantity*1.0);
					total=Math.round(total*100)/100;
					var num = new Number(total);
					total = num.toFixed(0);
					$('#execute').datagrid('updateRow',{index:rowIndex,row:{soutotal:total}});
				}
			}
		});
		//加载收货地址的数据
		$('#address').combobox({ 
			 url:ctx+'/customer111/customer111/customer111!listAddressInfo.action',
			 valueField:'id', 
			 textField:'receiveInfo'
		});
}

//查看
function tolookContactInfo(paramsid) {
	window.location = "contractTemporaryInfo.jsp?contractIds="+paramsid;
}

//隐藏存储操信息操作
function hideDiv1() {   
	$("#corids").val("");
	$("#executeDIV").window('close'); 
	lastIndext = 0;
} 

$(function(){
	$('#tt').datagrid({
		url: ctx+'/contractTemplates/contractTemplates/contractTemporary!listAll.action',
		width: '100%',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		   
                    {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'contractNo',title:'临时合同编号',width:60,align:'center'},
					{field:'title',title:'临时合同名称',width:60,align:'center',
						formatter:function(value,rec,index){
							var edit='<a href="#" onclick="tolookContactInfo(\''+rec.id+'\')">'+rec.title+'</a>';
							return edit;
						}},
					{field:'price',title:'合同总价(元)',width:50,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'creatorId',title:'creatorId',width:60,align:'center',hidden:true},
					{field:'status',title:'状态',width:50,align:'center',
						formatter:function(value,rec,index){
							  var status=rec.status;
							  var edit;
							  if(0==value){
									return '进行中';
								}else if(1==value){
									return '已完成';
								} else if(2==value){
								return '待审核';
							} else if(3==value){
								return '审核同意';
							}  else if(4==value){
								return '审核不同意';
							}  else if(5==value){
								return '审核中';
							}  else if(6==value){
								return '到货';
							  } else if(7==value){
									return '初验';
								}
							  return edit;
						 }	
					},
					{field:'fileForPDF',title:'文件',width:50,align:'center',hidden:true},
					{field:'creatorName',title:'创建者',width:50,align:'center',hidden:true},
					{field:'createTime',title:'创建时间',width:75,align:'center'},
					{field:'operate',title:'操作',width:80,align:'center',
						formatter:function(value,rec,index){
							var id=rec.id;
							var status=rec.status;
							var existFileName = rec.title;
							var fileName = rec.fileForPDF;
							if(fileName!=null && ""!=fileName){
								existFileName += fileName.substring(fileName.indexOf("."),fileName.length);
							}
							var downPath = ctx+"/jquery-easyui-1.3.1/downLoadFile.jsp?filename="+fileName+"&existFileName="+existFileName+"&exportPath="+exportPath; 
							var edit='';
							if(status!=2 &&status!=4&&status!=5){
								if(status==0||status==3){
									edit+='<a href="#" onclick="showExecuteDIV(\''+id+'\');">下单</a>&nbsp;|&nbsp;';
								}
								edit+='<a href="'+downPath+'">下载</a>&nbsp;|&nbsp;';
							}
							if(status==2||status==4){
								edit+='<a href="#"  onclick="showDataInfoDiv(\''+id+'\')">提交</a>&nbsp;|&nbsp;';
							}
							return edit;
						}
					}
					]],
					
					toolbar:[ {
						id:'btnadd',
						text:'新增临时合同',
						iconCls:'icon-add',
						handler:function(){
							window.location = "addcontractTemporary.jsp";
						}
					},{
						id:'btnsave',
						text:'合并临时合同',
						iconCls:'icon-remove',
						handler:function(){
							togetherContact();
						}
					}
					],
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
});	
