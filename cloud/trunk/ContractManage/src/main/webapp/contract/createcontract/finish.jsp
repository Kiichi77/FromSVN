<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript">
var configsIds = '<%=request.getParameter("configsIds")%>';
//可选配置
var firsts = [[
	{field:'id',title:'id',width:60,align:'center',hidden:true},
	{field:'dataRecordId',title:'souceid',width:60,align:'center',hidden:true},
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center',editor:'text'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center'},
	{field:'totalprice',title:'目录合价',width:60,align:'center'},
	{field:'discountRate',title:'折扣率',width:50,align:'center',editor:'text'},
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center'},
	{field:'otherRates',title:'运保及其他费率（%）',width:50,align:'center'},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center'},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:50,align:'center'}
]];
$(function(){
	var lastIndex;
	$('#ttt').datagrid({
		url:ctx+'quotations/quotations/quotations!queryQuotationInfo.action?id='+configsIds,
		width: '95%',
		title:"已选配置",
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:false,
		fitColumns: true,
		showGroup:true,
		columns:firsts,
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenus();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},
		onDblClickRow: function(rowIndex, rowData) {
			$('#ttt').datagrid('endEdit',lastIndex);
			$('#ttt').datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			//目录合价 c=a*b
			var totalprices = (rowData.unitPrice*1.0)*(rowData.quantity*1.0);
			totalprices = Math.round(totalprices*100)/100;
			//折扣后价格 e=c*(1-d)
			var rebateprices = (totalprices*1.0)*(1.0-(rowData.discountRate*1.0));
			rebateprices = Math.round(rebateprices*100)/100;
			//折扣后现场价格 g=e*(1+f)
			var rebateafters = (rebateprices*1.0)*(1.0+(rowData.otherRates*1.0));
			rebateafters = Math.round(rebateafters*100)/100;
			//合计x=g+h+j
			var totals = rebateafters+(rowData.installServiceCharge*1.0)+(rowData.firstYear*1.0)+(rowData.secondYear*1.0)+(rowData.thirdYear*1.0);
			$('#ttt').datagrid('updateRow',{index:rowIndex,row:{totalprice:totalprices,rebateprice:rebateprices,rebateafter:rebateafters,total:totals}});
		},
		toolbar:[{
			text:'返回',
			id:'savemodels',
			iconCls:'icon-back',
			handler:function(){
				history.go(-1);
			}
		}],
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		}
	});
	//创建列菜单
	function createColumnMenus(){
		var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
		var fields = $('#ttt').datagrid('getColumnFields');
		for(var i=0; i<fields.length; i++){
			$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
		}
		tmenu.menu({
			onClick: function(item){
				if (item.iconCls=='icon-ok'){
					$('#ttt').datagrid('hideColumn', item.text);
					tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				} else {
					$('#ttt').datagrid('showColumn', item.text);
					tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
					});
				}
			}
		});
	}
});
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="已选配置" style="overflow:hidden;">		
	 	<div style="margin-left: 10px"><br>  
	         <div><font style="font-weight: bold;">实配机型及其指定配件</font></div> 
	         <hr>	          
	         <div>
	 			<table id="ttt"></table> <br>
 			 </div>
	 	</div>
	</div>
</body>
</html>