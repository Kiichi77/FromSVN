<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  	String id=request.getParameter("id");
   	String path=request.getContextPath();
  	request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/togetherContract_view.js"></script>
<script type="text/javascript" src="controller/togetherContract_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
var manycontractids = '<%=request.getParameter("contractIds")%>';
var lastIndex = 0;
function addContract() {
	$('#baojiabiao').datagrid('endEdit',lastIndex);
	var flat = addbaojia();
	if(!flat){
		return;
	}
	
	if (null == $("#proname").val() || "" == $("#proname").val()) {
	    $.messager.alert('提示', '报价表名称不能为空!', 'info');
	    return;
	}
	var rows = $('#baojiabiao').datagrid('getSelections');
	var quotationsId = "";	//报价表ID
	var projectname = "";	//项目名称
	var configName = "";    //机配名称
	var remainingQuantity = "";    //采购数
	var zhanxian = "";    //使用数
	var scontractids = "";
	var iscanconmmit = true;
	// 循环给提交删除参数赋值(音乐风格编码)
	var pronameEnd = true;
	$.each(rows, function(i, n) {
		if((n.quantity*1)<(n.usedNum*1+n.zhanxian*1)) {
			$.messager.alert('提示', "使用数和已使用数大于总数", 'info');
			iscanconmmit = false;
		}
	   if(n.zhanxian*1==0)
		   {
		   $.messager.alert('提示', "报价表已使用完", 'info');
			iscanconmmit = false;
		   }
		quotationsId += n.id +",";
		if(null == n.projectName || "" == n.projectName) {
			pronameEnd = false;
		}
		projectname += n.projectName + ",";
		configName += n.configModelName + ",";
		remainingQuantity += n.remainingQuantity + ",";
		zhanxian += n.zhanxian + ",";
		scontractids += n.contractId		 + ",";
	});
	if(!pronameEnd) {
		$.messager.alert('提示', "项目名称不能为空", 'info');
		return;
	}
	if(!iscanconmmit) {
		return;
	}
	quotationsId = quotationsId.substring(0, quotationsId.length - 1);
	projectname = projectname.substring(0, projectname.length - 1);
	configName = configName.substring(0, configName.length - 1);
	remainingQuantity = remainingQuantity.substring(0, remainingQuantity.length - 1);
	zhanxian = zhanxian.substring(0, zhanxian.length - 1);
	scontractids = scontractids.substring(0, scontractids.length - 1);
	$.post(ctx+ '/contractTemplates/contractTemplates/contractTemporary!saveTemporaryDataToFormal.action',{
		'temporaryVo.id':scontractids,
		'temporaryVo.quotationsId':quotationsId,
		'temporaryVo.quantity':remainingQuantity,
		'temporaryVo.usedNum':zhanxian,
		'temporaryVo.title':$("#proname").val(),
//		'temporaryVo.projectId':$('#project').val(),
		'temporaryVo.name':configName,
		'temporaryVo.projectName':projectname
	},function(data) {
		if (null != data && "" != data) {
			var data = eval('(' + data + ')');
			if(data.msg == "ok"){
           		window.location.href="../contract/createcontract/realcreatecontract.jsp?gowhatpage=1";
//            		history.go(-1);
			}else{
				 $.messager.alert('提示', "操作失败", 'info');
			}
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}
//弹出报价表选择层
function showCheckBaojia() {
// 	var projectId = $("#projectid").val();
	//报价表数据加载
	$('#baojiabiao').datagrid({
		url: ctx+'/contractTemplates/contractTemplates/contractTemporary!queryAllConfigModels.action?id='+manycontractids,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		title:'机型配置列表',
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
				{field:'ck',checkbox:true,align:'center'},
				{field:'id',title:'ID',width:60,align:'center',hidden:true},//
				{field:'contractId',title:'项目id',width:300,align:'center',hidden:true},//
				{field:'quotationsId',title:'所属报价表id',width:300,align:'center',hidden:true},//
				{field:'projectName',title:'项目名称',width:250,align:'center',editor:'text'},
				{field:'quotationName',title:'所属报价表',width:250,align:'center'},
				{field:'configModelName',title:'配置名称',width:250,align:'center',editor:'text'},
				{field:'unitPrice',title:'单价',width:250,align:'center',hidden:true},
				{field:'quantity',title:'总数',width:250,align:'center'},
				{field:'ordered',title:'已下单数',width:250,align:'center',hidden:true},
 				{field:'remainingQuantity',title:'下单数',width:250,align:'center',hidden:true},
 				{field:'usedNum',title:'已使用数量',width:250,align:'center'},
 				{field:'zhanxian',title:'使用数量',width:250,align:'center',editor:'text'},
				{field:'totalPrice',title:'合计(元)',width:250,align:'center'},
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
		},
		onDblClickRow: function(rowIndex, rowData) {
			$('#baojiabiao').datagrid('endEdit',lastIndex);
			$('#baojiabiao').datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			var quantity = rowData.quantity*1;
			var canend = (rowData.usedNum*1)+(rowData.zhanxian*1);
			if(quantity<canend) {
				$.messager.alert('提示', '使用数和已使用数大于总数!', 'info');
			}
			//合计x=g+h+j
			var totals = (rowData.unitPrice*1.0)*(rowData.zhanxian*1.0);
			var num = new Number(totals);
			totals = num.toFixed(0);
			$('#baojiabiao').datagrid('updateRow',{index:rowIndex,row:{totalPrice:totals}});
		},
		onLoadSuccess: function(data){
			//获取表格选择行
			var rows = $('#baojiabiao').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			$.each(rows, function(i, n) {
				var id=n.contractQuotationId;
				if(null != id && 0 != id && "0" != id){
					$('#baojiabiao').datagrid("selectRow",i);
				}
				var zhanxians = (n.quantity*1)-(n.usedNum*1);
				var num = new Number(n.totalPrice);
				$('#baojiabiao').datagrid('updateRow',{index:i,row:{zhanxian:zhanxians,totalPrice: num.toFixed(0)}});
			});
		}
	});	
}

//添加选择的报价单
function addbaojia() {
	// 获取表格选择行
	var rows = $('#baojiabiao').datagrid('getSelections');
	// 判断是否选择行
	var end = true;
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '还未选择报价单!', 'info');
		end = false;
	}
	return end;
}
</script>

<body class="easyui-layout" >
     <div data-options="region:'center'" title="临时合同合并正式合同" style="overflow:hidden;background-color: #F7F7F7;">
	<!-- 生成合同弹出层 -->
     <div id="addVmDiv" style="padding:20px;width: 100%;height:auto; margin-left:10px;margin-top: 10px;">
	    <form id="addVmform" method="post" novalidate style="width: 100%;height:auto; ">
	    	<input type="hidden" id="userId" name="userId" value="<%=session.getAttribute("USER_ID") %>">
	    	<!-- 
	    		 <div><font style="font-size: 13px;">所&nbsp;属&nbsp;项&nbsp;目 ：</font>
		               <select id="project" name="purchasepeople" style="width:200px;" data-options="required:true"></select>
	    		 </div><br>-->
	    		 <div><font style="font-size: 13px;">报价表名称(临转正)&nbsp;&nbsp;：</font>
	    		 	  <input id="proname" type="text" style="width:194px" />
	             </div><br>
	              
			 <div id="baojiaDiv" style="width:95%;">
				<div style="width:1200px;overflow-y:auto;height:auto;"><table id="baojiabiao"></table></div>
			 </div><br>
	    </form>
	 </div>
	 <div align="right" style="width: 1200px;">
		<a href="#" onclick="addContract();" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		<a href="#" onclick="comebacktocon();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	 </div>
	 </div>
</body>
<script type="text/javascript">
	//加载所属项目
	var sel4 = $("#project");  
	sel4.empty();//清除select中的 option
	$.post(ctx+'/projectManage1/projectManage1/project1!myProject.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].projectName).val(datas.rows[i].id);  
				sel4.append(opt);  
			}
		}
	});
	showCheckBaojia();
</script>
</html>