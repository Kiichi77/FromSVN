<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../view/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
<title>Insert title here</title>
</head>
<%
String projectname=request.getParameter("projectname");
projectname=new String(projectname.getBytes("ISO-8859-1"),"UTF8");

%>
<body class="easyui-layout">
	<!-- 询价 -->
	<div id="executeDIV" data-options="region:'center'" title="生成合同" style=" overflow-y:auto;">
			<table id="execute"></table><br>
		<div align="right">
		<font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;
			<select id="tbUser" name="cauditId" style="width:160px;" data-options="required:true"></select>
		<a href="#" onclick="updateRelationship();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		<!-- <a href="#" onclick="hideDiv1();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a> -->
		</div>
	</div>
	
	<!-- 机型配置的详情 -->
	<div id="numberDIV" style="background:#fafafa;padding:20px;width:650px;height:280px;display: none;border: solid gray 1px;margin-top: 100px; overflow-y:auto;">
		<div style="overflow-y: scroll;height: 250px;">
			<table id="tynumbers"></table>
		</div>
		<br>
		<div align="right" style="width: 650px;">
		    <a href="#" onclick="hideNumberDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">取消</a>
		</div>
	</div>
<script type="text/javascript">

//查询并填充订单信息
//弹出合同执行选择层
var lastnumIndext;//表单的行索引
var suppliers;
var projectNAMENAME='<%=projectname%>';
var process_id = '<%=request.getParameter("processid")%>';  //进程id
var taskPkId = <%=request.getParameter("id")%>;  //任务url跳转表的id
var ctx = '${ctx}';
var contractOrderId=<%=request.getParameter("quotationsId")%>;
function productFormatter(value){
	for(var i=0; i<suppliers.length; i++){
		if (suppliers[i].id == value) return suppliers[i].companyName;
	}
	return value;
}
//隐藏通用单元数量设置框
function hideNumberDIV() {   
	$("#numberDIV").animate({left: 0, top: 0, opacity: "hide" }, "slow");
} 
//配置详情
function wantTongyongInfo(id) {
	var div_obj = $("#numberDIV");
	var windowWidth = document.body.clientWidth;       
	var windowHeight = document.body.clientHeight;  
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width(); 
	div_obj.css({"position": "absolute"})
	.animate({left: windowWidth/4-popupWidth/4,top: windowHeight/4-popupHeight/2, opacity: "show" }, "slow");
	//报价表数据加载
	$('#tynumbers').datagrid({
		url:ctx+'/configModel/configModel/configModel!listConfigModelById.action?id='+id,
		width: '95%',
		height: 'auto',
		pagination:false,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		columns:[[ 
				{field:'id',title:'id',width:60,align:'center',hidden:true},
				{field:'productOrderNo',title:'产品订货号',width:100,align:'center'},
				{field:'name',title:'产品名称',width:100,align:'center'},
				{field:'productDesc',title:'产品描述',width:120,align:'center'}
				]],  
		toolbar:[],
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
}
//更新询价单的价格和供应商
function updateRelationship() {
	$('#execute').datagrid('acceptChanges');
	var rows = $('#execute').datagrid('getRows');
	var tbUser=$('#tbUser').val();
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		json += "{'id':'"+n.id+"','purchasePrice':'"+n.purchasePrice+"','suppliers':'"+n.suppliers+"'}";
		if(i!=rows.length-1){
			json += ',';
		}
	});
	json += "]";
	$.post(ctx+ '/order/order/order!updateRelationship.action?processid='+process_id+'&cauditId='+tbUser, {
		id : json
	}, function(data) {
		var datas = eval("("+data+")");
		if (null != datas && "" != datas) {
			if("ok" == datas.msg){
			$.messager.alert('提示', "操作成功", 'info');
	        window.location.href="../view/main.jsp";
			hideDiv1();
			} else {
				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
			}
		}
	});
}

$.post(ctx+'/suppliers/suppliers/suppliers!listSuppliers.action',
			function(data){
				var datas = eval("("+data+")");
				if(null!=datas && ""!=datas){
					suppliers = datas.rows;
				}
				//初始化表单
				$('#execute').datagrid({
					url: ctx+'/order/order/order!findOrderDetailByContractId.action?contractOrderId='+contractOrderId,
					width: '95%',
					height: 'auto',
					pagination:false,
					rownumbers:true,
					remoteSort: false,
					nowrap: true,
					autoRowHeight: false,
					striped: true,
					fitColumns: true,
					columns:[[ 
							{field:'id',title:'id',width:60,align:'center',hidden:true},
							{field:'configModelId',title:'ConfigModelId',width:60,align:'center',hidden:true},
							{field:'njhfghj',title:'项目名称',width:100,align:'center',
								formatter:function(value,rec,index){
									return projectNAMENAME;
								}
							},
							{field:'configModelName',title:'机型配置单名称',width:100,align:'center'},
							{field:'purchasePrice',title:'采购价',width:80,align:'center',editor:'text'},
							{field:'quantity',title:'采购数',width:50,align:'center'},
							{field:'suppliers',title:'供应商',width:80,align:'center',formatter:productFormatter,
								editor:{
									type:'combobox',
									options:{
										textField:'companyName',
										valueField:'id',
										data:suppliers,
										required:true
									}
								}
							},
							{field:'opt',title:'查看详情',width:80,align:'center',
								formatter:function(value,rec,index){
									var id = rec.configModelId;
									var edit = '<a href="#" onclick="wantTongyongInfo(\''+id+'\')">查看配置详情</a>';//
									return edit;
								}
							}
							]],  
					toolbar:[],
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
				onClickRow:function(rowIndex){
					if (lastnumIndext != rowIndex){
						$('#execute').datagrid('endEdit', lastnumIndext);
						$('#execute').datagrid('beginEdit', rowIndex);
					}
					lastnumIndext = rowIndex;
				}
				});
			});
//加载用户下拉框
	var sel = $("#tbUser");
	sel.empty();//清除select中的 option
	$.post(ctx+'/user/user/user!queryUserListByrUserTaskUrlId.action',
			{'userTaskUrlid':taskPkId}
			,function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});	
</script>
</body>
</html>