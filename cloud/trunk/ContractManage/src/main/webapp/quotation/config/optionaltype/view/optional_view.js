function deleteConfigModelfun(paramIndex) {//paramIndex
//	$('#ttt').datagrid('deleteRow', paramIndex);
	$('#ttt').datagrid('selectRow',paramIndex);
	var row = $('#ttt').datagrid('getSelected');
	if (row){
		var index = $('#ttt').datagrid('getRowIndex', row);
		$('#ttt').datagrid('deleteRow', index);
	}
}

//可选配置
var firsts = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'dataRecordId',title:'souceid',width:60,align:'center',hidden:true},
	{field:'typeId',title:'typeId',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center',editor:'text'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'totalprice',title:'目录合价',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'discountRate',title:'折扣率(%)',width:50,align:'center',editor:'text'},
	{field:'yincangzk',title:'隐藏折扣率(%)',width:60,align:'center',hidden:true},//,hidden:true
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'otherRates',title:'运保及其他费率(%)',width:50,align:'center'},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:50,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'operate',title:'操作',width:40,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit='<a href="#" onclick="deleteConfigModelfun(\''+index+'\');">删除</a>';//\''+index+'\'
			return edit;
		}//,hidden:true
	}
]];

//可选配置
var optiona = [[
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'typeId',title:'typeId',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'totalprice',title:'目录合价',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'discountRate',title:'折扣率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit=value*100;
			return edit;
		}
	},
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'otherRates',title:'运保及其他费率(%)',width:50,align:'center',
		formatter:function(value,rec,index){
			var edit;
			edit=value*100;
			return edit;
		}
	},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:30,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	}
]];

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