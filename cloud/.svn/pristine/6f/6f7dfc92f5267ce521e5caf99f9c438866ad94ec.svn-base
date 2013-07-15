<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String projectname=request.getParameter("projectname");
if(null!=projectname && !"".equals(projectname)){
	projectname=new String(projectname.getBytes("ISO-8859-1"),"UTF8");
}
%>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/realcreatecontract_view.js"></script>
<script type="text/javascript" src="controller/realcreatecontract_controller.js"></script>
<head>
<script type="text/javascript">
var gowhatpage = '<%=request.getParameter("gowhatpage")%>';
//判断关闭按钮跳转到那个页面
function hicacel() {
	if(1 == gowhatpage) {
		window.location = "../../contractTemporary/contract_temporary.jsp";
	} else {
		window.location = "createcontract.jsp";
	}
}

var ctx = '${ctx}';
var pkContractId = <%=request.getParameter("contractId")%>;
var contractStatue = <%=request.getParameter("contractStatue")%>;
var json;
var lastIndex1;
var lastIndex;
var lastIndexa;
function addContract() {
	if(contractStatue==2 || contractStatue==4 || contractStatue==null || contractStatue==""){ //状态为待审核和审核不通过的时候才需要选择报价表
		var flat = addbaojia();
		if(!flat){
			return;
		}
	}
	if (null == $("#proname").val() || "" == $("#proname").val()) {
	    $.messager.alert('提示', '合同名称不能为空!', 'info');
	    return;
	}
	$.post(ctx+ '/contractTemplates/contractTemplates/contract!addOrUpdateContract.action',{
		'contractVo.id':$("#contractids").val(),
		'contractVo.type':$("#contractType").val(),
		'contractVo.title':$("#proname").val(),
		'contractVo.quotationsTitle':$("#title").val(),
		'contractVo.contractNo':$("#contractnumber").val(),
		'contractVo.projectId':$('#project').val(),
		'contractVo.contractTemplatesId':$("#name").val(),
		'contractVo.contractNumber':$('#contractno').val(),
		'contractVo.status':$('#contractState').val()
	},function(data) {
		if (null != data && "" != data) {
			var data = eval('(' + data + ')');
			if(data.msg == "ok"){
				if($("#contractids").val()==null||$("#contractids").val()==""){
					$("#contractids").val(data.contractId);
					showContract();
					$("#contract").show();
					$("#contractTemplate").hide();
				}
				$("#contractids").val(data.contractId);
				$("#contractnumber").attr('disabled','disabled');
				$("#proname").attr('disabled','disabled');
				$("#name").attr('disabled','disabled');
				$("#project").attr('disabled','disabled');
				$("#contractno").attr('disabled','disabled');
			//	$('#btn').linkbutton({disabled:true})
				$('#btn').hide();
				if(null!=$("#contractids").val() && ""!=$("#contractids").val())
					{
					$.messager.alert('提示', "操作成功!", 'info');
					}else{
			   $.messager.alert('提示', "操作成功,请设置变量值。", 'info');
					}
       //    window.location.href="createcontract.jsp";
			}else
				{
				 $.messager.alert('提示', "操作失败", 'info');
				}
		} else {
		   $.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}
//合同文件数据加载
function showContract()
{
	var contractId=$("#contractids").val();
	$('#tt').datagrid({
		url:ctx+'/contractTemplates/contractTemplates/contract!listContractTemplates.action?id='+contractId,
		width: '95%',
		height: 'auto',
		title:'合同文件',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
    	pagination:false,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:twoCluomn,
	});		
}
//合同模版文件数据加载
function showContractTemplate(templateId)
{
	var contractIds;
	if(templateId==null){
		contractIds=$("#name").val();
	}else{
		contractIds=templateId;
	}
	$('#tp').datagrid({
		url:ctx+'/contractTemplates/contractTemplates/contractTemplatesDoc!listContractTemplatesDoc.action?contractTemplatesId='+contractIds,
		width: '95%',
		height: 'auto',
		title:'合同模版文件',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
    	pagination:false,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		showGroup:true,
		columns:contractTemplateCluomn,
	});		
}

//弹出报价表选择层
function showCheckBaojia() {
	/* var div_obj = $("#baojiaDiv");
	var contracids = $("#contractids").val();
	var projectId = $("#projectid").val(); */
	
		//报价表数据加载
		$('#baojiabiao').datagrid({
			url: ctx+'/quotations/quotations/quotations!listAll.action?contractId='+pkContractId,
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
// 					{field:'dataSourceName',title:'所属数据源',width:300,align:'center'},
				  //{field:'projectId',title:'所属项目id',width:300,align:'center',hidden:true},
				  //{field:'projectName',title:'所属项目',width:250,align:'center'},
				  //{field:'downUrl',title:'下载地址',width:300,align:'center'},
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
		return false;
	}else
		{
	var parm = "";
	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		parm += n.id + ",";
	});
	parm = parm.substring(0, parm.length - 1);
	$("#title").val(parm);
		}
	return true;
}

//自定义属性
$.extend($.fn.datagrid.methods, {
	  addEditor : function(jq, param) {
	      if (param instanceof Array) {
	          $.each(param, function(index, item) {
	              var e = $(jq).datagrid('getColumnOption', item.field);
	              e.editor = item.editor;
	          });
	      } else {
	          var e = $(jq).datagrid('getColumnOption', param.field);
	          e.editor = param.editor;
	      }
	  },
	  removeEditor : function(jq, param) {
	      if (param instanceof Array) {
	          $.each(param, function(index, item) {
	              var e = $(jq).datagrid('getColumnOption', item.field);
	              e.editor = {};
	          });
	      } else {
	          var e = $(jq).datagrid('getColumnOption', param.field);
	          e.editor = {};
	      }
	  }
});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body  class="easyui-layout" >
<div data-options="region:'center'" title="创建合同" style="overflow:hidden;background-color: #F7F7F7;">

<!-- 生成合同弹出层 -->
	<fieldset title="创建合同"  style="width: 98%;height:auto;margin-left: 3px;margin-top: 3px;margin-right:5px;overflow-y:auto;overflow: hidden;">
     <div id="addVmDiv" style="padding:20px;width: 100%;height:auto; margin-left:10px;margin-top: 10px;">
	    <form id="addVmform" method="post" novalidate style="width: 100%;height:auto; ">
	    <%-- <input type="hidden" id="projectid" name="checkedVo.process_id" value="<%=request.getParameter("projectid")%>">
	    <input type="hidden" id="processId" name="checkedVo.process_id" value="<%=request.getParameter("processid")%>">	
	    <input type="hidden" id="userId" name="userId" value="<%=session.getAttribute("USER_ID") %>"> --%>
	    <input type="hidden" id="contractState" value="<%=request.getParameter("contractStatue")%>">
	    		 <div>
	    		   <font style="font-size: 13px;">合&nbsp;同&nbsp;号&nbsp; :</font>
	    		   <input id="contractno" type="text" style="width:194px" data-options="required:true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	       <font style="font-size: 13px;">内部合同编号&nbsp;:</font>
	               <input id="contractnumber" type="text" style="width:194px" data-options="required:true" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    		 </div>
	    		 <br>
	    		 <div>
	                  <font style="font-size: 13px;">合同名称&nbsp;：</font>
	    		 	  <input id="proname" type="text" style="width:194px" />
	    		 	  <input id="contractids" type="text" style="width:194px;display:none ;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    		 	  <font style="font-size: 13px;">合同模版 :</font>
	                     <select id="name" name="name" style="width:200px;" onchange="showContractTemplate();">
	                     </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                  <font style="font-size: 13px;">所属项目 ：</font>
	                     <select id="project" name="purchasepeople" style="width:200px;" data-options="required:true">
	                     </select>
	                 <input id="title" type="text" style="width:198px;display: none;"/>
	             </div><br>
	    	 <!-- 报价表选择弹出层 -->
	    	 <div id="contractTemplate" style="width:95%;">
				<div style="width:1200px;overflow-y:auto;height:auto;"> <table id="tp"></table></div>
			</div>
			<br>
			<div id="baojiaDiv" style="width:95%;">
				<div style="width:1200px;overflow-y:auto;height:auto;"><table id="baojiabiao"></table></div>
			</div>
			<br>
			<div id="contract" style="width:95%;">
				<div style="width:1200px;overflow-y:auto;height:auto;"><table id="tt"></table></div>
			</div>
	    </form>
	 </div>
	 <div align="center" style="width: 1200px;">
		<a href="#" onclick="addContract();" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
		<a href="#" onclick="hicacel();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
	</div>
	 </fieldset>
	 	<!-- 弹出log1：变量设置-->
	   	<div id="dd" data-options="iconCls:'icon-save'" title='合同文件变量' style="padding:5px;width:800px;height:500px;display: none;"> 
	    	<table id="ta"></table>
		</div>
	 	<!-- 弹出log2：查看金额-->
	  	<div id="mone" data-options="iconCls:'icon-save'" title='合同中金额详情' style="padding:5px;width:800px;height:500px;display: none;">
	    	<table id="tat"></table>
		</div>
	 	<!-- 弹出层 -->
     <div id="updateDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="修改文件名" style="background:#fafafa;padding:20px;width:400px;height:150px;overflow:hidden">
	    <form id="updateform" method="post" novalidate style="width: 350px;height:50px;">
	    	<div id="thevmleft" style="width: 350px;height:50px;float: left;">
		    	<div align="left" style="width: 350px;margin-bottom: 10px;">
		    		<input id="contractId" type="hidden" name="model.id">
		    		<input id="ppath" type="hidden" name="model.exportPath">
		    		<font style="font-size: 13px;">文件名：</font>
		    		<input id="fileName" type="text" class="easyui-validatebox" style="width:180px;" name="model.fileName" data-options="required:true" />
		    	</div>
		    	<div align="right" style="width: 350px;margin-top: 15px;">
			    	 <a href="#" onclick="updateContract();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a>
			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			   	</div>
	    	</div>
	    </form>
	 </div>
	 </div>
</body>
<script type="text/javascript">
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
			showContractTemplate();
		}
	});
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
	//修改合同,根据合同id查询合同信息
	queryContarctByid();
</script>
</html>