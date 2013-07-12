<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/standardConfiguration_controller.js"></script>
<script type="text/javascript" src="view/standardConfiguration_view.js"></script>
<script>
var configsIds = '<%=request.getParameter("dataSourceIds")%>';
var tocheckIds = '<%=request.getParameter("tocheckIds")%>';
var tocheckName = '<%=request.getParameter("dataSourceNames")%>';
/***************DIV*****************/
//基本配置单元divID序列号
var accordionDivId1 = 0;
//同系列通用单元divID序列号
var accordionDivId2 = 0;
//全通用单元divID序列号
var accordionDivId3 = 0;
/***************column*****************/
//基本配置单元列模型序列号
var columnId1 = 0;
//基本配置单元列模型序列号
var columnId2 = 0;
//基本配置单元列模型序列号
var columnId3 = 0;
var lastIndex = 0;
$(function(){
	$('#ttt').datagrid({
		url:ctx+'quotations/quotations/quotations!getStandard.action?sourceId='+configsIds,
		width: '95%',
		title:"标准配置",
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		singleSelect: true,
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
			//获取表格选择行
			var rows = $('#ttt').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			$.each(rows, function(i, n) {
				if(i == lastIndex) {
					var endcha = n.discountRate;
					var fristnum = n.yincangzk;
					if(endcha<fristnum) {
						$.messager.alert('提示', "折扣率不可超过标准值", 'info');
						$('#ttt').datagrid('beginEdit', lastIndex);
						return;
					} else {
						$('#ttt').datagrid('beginEdit', rowIndex);
						lastIndex = rowIndex;
					}
				}
			});
		},
		onLoadSuccess: function(data) {
			//获取表格选择行
			var rows = $('#ttt').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			$.each(rows, function(i, n) {
				var discountRate = n.discountRate*100;
				discountRate = Math.floor(discountRate);
				var otherRates = n.otherRates*100;
				$('#ttt').datagrid('updateRow',{index:i,row:{yincangzk:discountRate,discountRate:discountRate,otherRates:otherRates}});
			});
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			//目录合价 c=a*b
			var totalprices = (rowData.unitPrice*1.0)*(rowData.quantity*1.0);
			totalprices = Math.round(totalprices*100)/100;
			//折扣后价格 e=c*(1-d)
			var rebateprices = (totalprices*1.0)*(1.0-(rowData.discountRate*0.01));
			rebateprices = Math.round(rebateprices*100)/100;
			//折扣后现场价格 g=e*(1+f)
			var rebateafters = (rebateprices*1.0)*(1.0+(rowData.otherRates*0.01));
			rebateafters = Math.round(rebateafters*100)/100;
			//合计x=g+h+j
			var totals = rebateafters+(rowData.installServiceCharge*1.0)+(rowData.firstYear*1.0)+(rowData.secondYear*1.0)+(rowData.thirdYear*1.0);
			$('#ttt').datagrid('updateRow',{index:rowIndex,row:{totalprice:totalprices,rebateprice:rebateprices,rebateafter:rebateafters,firstYear:rowData.firstYear,secondYear:rowData.secondYear,thirdYear:rowData.thirdYear,total:totals}});
		},
		toolbar:[{
			text:'返回',
			id:'savemodels',
			iconCls:'icon-back',
			handler:function(){
				history.go(-1);
			}
		},{
			text:'保存配置模板',
			id:'savemodels',
			iconCls:'icon-save',
			handler:function(){
				//保存模板信息所有
				$('#ttt').datagrid('endEdit',lastIndex);
				saveAndUpdateQuotation();
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
	//加载各个单元数据表格
	$.post(ctx+'dataSource/dataSource/dataRecord!listDetailDataRecord.action',{dataSourceId:configsIds},
		function(data){
		if(null!=data && ""!=data){
			for(var i = 0;i < data.rows.length; i++) {
				wantDivFun(data.rows[i].name,""+data.rows[i].param,data.rows[i].url);
			}
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});	
});

</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="可选机型配置" style="overflow:hidden;">	
	<div style="width:1800;height: 250px;overflow:scroll;"><table id="ttt"></table></div>
	<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;height:350px;">
		<div title="基本配置单元" data-options="tools:'#p-tools'">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:scroll;">
			</div>
		</div>
		<div title="同系列通用单元" data-options="tools:'#p-tools'">
			<div id="menu2" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:scroll;">
			</div>
		</div>
	    <div title="全通用单元" data-options="tools:'#p-tools'" >
			<div id="menu3" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow:scroll;">
			</div>
		</div>
	</div>
</div>
</body>
</html>