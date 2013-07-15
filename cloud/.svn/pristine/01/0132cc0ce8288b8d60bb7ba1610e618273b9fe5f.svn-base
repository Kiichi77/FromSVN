//单元数据总览列布局
var dyDataLayOut = [[  		   
	//{field:'ck',checkbox:true,align:'center'},
	{field:'contractPaymentId',title:'ID',width:60,align:'center',hidden:true},
	{field:'contractId',title:'contractId',width:60,align:'center',hidden:true},
	{field:'projectName',title:'项目名称',width:100,align:'center'},
	{field:'contractNo',title:'合同编号',width:100,align:'center'},
	{field:'contractName',title:'合同名称',width:100,align:'center'},
	{field:'buyerCompany',title:'买方公司',width:100,align:'center'},
	//{field:'sellerCompany',title:'卖方公司',width:100,align:'center'},
	{field:'contractMoney',title:'合同金额(元)',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'sellerName',title:'销售人员',width:100,align:'center'},
	{field:'contractStatus',title:'收款状态',width:80,align:'center',
		formatter:function(value,rec,index){
			//收款状态( 1：待收货款 2：待收初验款 3：待收终验款 0：完成收款)
			if(1==value) {
				return "待收货款";
			} else if(2==value){
				return "待收初验款";
			}else if(3==value){
				return "待收终验款";
			}else if(0==value){
				return "完成收款";
			}else{
				return "未知状态";
			}
		}
	},
	{field:'receiveMoney',title:'已到账款',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'unReceiveMoney',title:'未到账款',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}			
	},
	{field:'operate',title:'操作',width:80,align:'center',
		formatter:function(value,rec,index){
			var id=rec.contractPaymentId;
			var contractStatus = rec.contractStatus;
			var contactId=rec.contractId;
			var edit;
		
			if(contractStatus==0){
				edit='收款&nbsp;|&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" onclick="contractPaymentDataList('+id+')">明细</a><input type="hidden" id="processId" name="process_id" >';
			}else{
				edit='<a href="#" onclick="showWindow('+id+','+contractStatus+','+contactId+');">收款</a>&nbsp;|&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" onclick="contractPaymentDataList('+id+')">明细</a><input type="hidden" id="processId" name="process_id" >';
			}
			return edit;
		}
	}	                    
]];

$(function(){
	$('#tt').datagrid({
		url:  ctx+'/contractTemplates/contractTemplates/contractPayment!paymentListBySales.action?id='+contractId,
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
		
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu($('#tt'));
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
	 
	//下拉选择框数据加载  财务人员
		var sel = $("#payDatafinancialName");  
		sel.empty();//清除select中的 option
		$.post(ctx+'/user/user/user!queryAllfinancials.action',
				{'userTaskUrlid':taskPkId},
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					for(var i = 0;i < datas.rows.length; i++) {
						var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
						sel.append(opt);  
					}
				}
		});

});

/**
 * 合同收款明细
 * @param id
 */
function contractPaymentDataList(id){
	$('#w').window('open');
	
	var dataList = [[  		   
	                  	{field:'buyerAccount',title:'买方帐号',width:150,align:'center'},
	              //    	{field:'sellerAccount',title:'卖方帐号',width:150,align:'center'},
	                	{field:'payType',title:'收款类型',width:80,align:'center',
	                  		formatter:function(value,rec,index){
	                  			//收款状态(11：收货 12：初验 13：终验)
	                  			if(11==value) {
	                  				return "收货";
	                  			} else if(12==value){
	                  				return "初验";
	                  			}else if(13==value){
	                  				return "终验";
	                  			}else{
	                  				return "未知状态";
	                  			}
	                  		}
	                  	},
	                  	{field:'money',title:'收款金额',width:100,align:'center',
	                		formatter:function(value,rec,index){
	                			var num = new Number(value);
	                			return num.toFixed(0);
	                		}			
	                	},
	                  	{field:'receiveTime',title:'预计到账时间',width:100,align:'center'},
	                  	{field:'accpetTime',title:'接收款项时间',width:100,align:'center'},
	                  	{field:'financialName',title:'财务',width:100,align:'center'},
	                  	{field:'status',title:'状态',width:80,align:'center',
	                  		formatter:function(value,rec,index){
	                  			//收款状态(0：未收款 1：已收款)
	                  			if(0==value) {
	                  				return "未收款";
	                  			} else if(1==value){
	                  				return "已收款";
	                  			}else{
	                  				return "未知状态";
	                  			}
	                  		}
	                  	},
	                  	{field:'notes',title:'备注',width:100,align:'center'}                    
	                  ]];
	
	$('#moneylist').datagrid({
		url:  ctx+'/contractTemplates/contractTemplates/contractPayment!contractPaymentDataList.action?id='+id,
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
		columns:dataList,
		
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenuList();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
	//分页工具条
	 $('#moneylist').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
}

//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#tt').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#tt').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#tt').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}

//创建列菜单
function createColumnMenuList(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#moneylist').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#moneylist').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#moneylist').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}