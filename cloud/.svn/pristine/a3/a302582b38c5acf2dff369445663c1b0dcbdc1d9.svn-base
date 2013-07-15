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
<script type="text/javascript" src="view/addcontractTemporary_view.js"></script>
<script type="text/javascript" src="controller/addcontractTemporary_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
//添加合同
function addContract() {
	var fileName = $('input[name="contractFileSource"]').val();
	if(fileName=="" || null==fileName){
		$.messager.alert('提示', "请选择需要上传的文件。", 'info');
		return;
	}
	var index = fileName.lastIndexOf(".");
	fileName = fileName.substring(index+1,fileName.length);
	if('pdf'!=fileName){
		$.messager.alert('提示', "您选择的文件类型不对，只能上传pdf文件。", 'info');
		return;
	}
	addbaojia();
	$('#addVmform').form('submit', {
		url:ctx+"/contractTemplates/contractTemplates/contractTemporary!saveContractTemporary.action",
		onSubmit: function(){
			// 做某些检查
			// 返回 false 来阻止提交
			return $(this).form('validate'); 
		},
		success:function(data){
			if(data!=null && data!=""){
				if("error" == data.msg){
					$.messager.alert("提示","操作失败，请从新尝试!");
				} else {
					togetherContract();
				}
			} else {
				$.messager.alert("提示","系统异常，请稍后操作!");
			}
		}
	});
}

//弹出报价表选择层
function showCheckBaojia() {
		//报价表数据加载
		$('#baojiabiao').datagrid({
			url: ctx+'/quotations/quotations/quotations!listAll.action',
			width: '95%',
			height: 'auto',
			pagination:false,
			rownumbers:true,
			title:'报价表',
			remoteSort: false,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			fitColumns: true,
			columns:[[ 
					{field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'projectId',title:'所属项目id',width:300,align:'center',hidden:true},
					{field:'projectName',title:'所属项目',width:250,align:'center',hidden:true},
					{field:'title',title:'报价表名称',width:250,align:'center'},
					{field:'catalogTotalPrice',title:'目录合价(元)',width:300,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'afterDiscountPrice',title:'折扣后价格(元)',width:300,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'afterDiscountNowPrice',title:'折扣后现场价(元)',width:300,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'totalPrice',title:'合计(元)',width:250,align:'center',
						formatter:function(value,rec,index){
							value = value.replace(/,/g,"");
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'createtime',title:'创建时间',width:250,align:'center'}//,
// 					{field:'creatorName',title:'创建者',width:100,align:'center'}
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
				});
			}
		});
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
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		parm += n.id + ",";
	});
	parm = parm.substring(0, parm.length - 1);
	$("#title").val(parm);
}
</script>

<body class="easyui-layout" >
     <div id="addVmDiv" data-options="region:'center'" title="新增临时合同" style="background-color: #F7F7F7;">
	    <form id="addVmform" method="post" enctype="multipart/form-data" novalidate style="width: 650px;height:460px;margin-left: 10px;margin-top: 10px;">
	    <input type="hidden" id="userId" name="userId" value="<%=session.getAttribute("USER_ID") %>">
	    	<div id="thevmleft" style="width: 210px;height:125px;float: left;">
	    		 <div>
	    		 	<font style="font-size: 13px;">合同名称：</font>
	    		 	<input id="proname" type="text" style="width:194px" name="temporaryVo.title" data-options="required:true"/>
	    		 </div><br>
	    		 <div>
	    	     	  <font style="font-size: 13px;">合同编号：</font>
	                  <input id="contractnumber" name="temporaryVo.contractNo" type="text" style="width:194px;" readonly="readonly" data-options="required:true"/>
	             </div><br>
				 <!-- 报价表ID，逗号分隔 -->
	             <input id="title" type="text" name="temporaryVo.quotationsId" style="width:198px;display: none;"/>
	    	</div>
	    	<div id="thevmright" style="width: 210px;height:125px;float: left;margin-left: 50px;">
	             <div>
	             	  <font style="font-size: 13px;">创建人：</font>
	             	  <input id="createManName" type="text" style="width:194px;" value="<%=session.getAttribute("username") %>" disabled="disabled"/>
	             </div><br>
	             <div>
	    		 	<font style="font-size: 13px;">合同文件：</font>
	    		 	<input id="contractfile" type="file" name="contractFileSource" style="width:194px" />
	    		 </div>
	    	</div><br>
	    	 <!-- 报价表选择弹出层 -->
			<div id="baojiaDiv" style="width:1100px;">
				<br>
				<div style="width:1100px;overflow-y:auto;height:300px;"><table id="baojiabiao"></table></div>
			</div><br>
			<div align="right" style="width: 980px;">
				<a href="#" onclick="addContract();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
				<a href="#" onclick="togetherContract();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			</div>
	    </form>
	 </div>
</body>
<script type="text/javascript">
//获得临时合同编号
function wantLSContactNum() {
	$.post(ctx+ '/contractTemplates/contractTemplates/contract!temporaryContractNo.action',function(data) {
		if (null != data && "" != data) {
			var datas = eval('(' + data + ')');
			$("#contractnumber").val(datas.contractNo);
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}
wantLSContactNum();
//加载报价表列表数据
showCheckBaojia();
</script>
</html>