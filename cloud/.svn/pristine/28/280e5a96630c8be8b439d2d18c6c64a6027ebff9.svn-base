<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript">
var contractid = <%=request.getParameter("quotationsId")%>; 
</script>

<body class="easyui-layout">
	<!-- 弹出上传log -->
	<div id="add" title="上传合同文件"
		style="width: 360px; height: 360px;vertical-align: middle;margin-left: 50px;margin-top: 30px;">
		<form id="addVmform" method="post" enctype="multipart/form-data">
		<input type="hidden" id="processId1" name="processId" value="<%=request.getParameter("processid")%>">
			<div style="margin-top: 10px,float: left;">
				<font style="font-size: 13px;">请选择所属合同 ：</font> <select
					id="contract" name="purchaseOrderContractFile.contract.id"
					style="width: 200px;" data-options="required:true"></select>
					</div>
			<br>
			<div style="margin-top: 10px,float: left;">
				<font style="font-size: 13px;">请选择供应商 ： </font>&nbsp;&nbsp;<select
					id="suppliers" name="purchaseOrderContractFile.suppliers.id"
					style="width: 200px;" data-options="required:true"></select>
			</div>
			<br>
			<div>
				<font style="font-size: 13px;">请输入名称 ：&nbsp;&nbsp;&nbsp;&nbsp; </font> <input id="contractName" type="text"
					class="easyui-validatebox" style="width: 180px;"
					name="purchaseOrderContractFile.title" data-options="required:true" />
			</div>
			<br>
			<div>
				<font style="font-size: 13px;">请输入金额 ：&nbsp;&nbsp;&nbsp;&nbsp; </font><input id="contractMoney" type="text"
					class="easyui-validatebox" style="width: 180px;"
					name="purchaseOrderContractFile.contractMoney"
					data-options="required:true" />
			</div>
			<br> <font style="font-size: 13px;">采购公司 ：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> <select
				id="purchaseCompany" name="purchaseCompanyId" style="width: 200px;"
				data-options="required:true"></select> <br> <br>
			<div id="filediv">
				<font style="font-size: 13px;">选择您要上传的文件：</font> <br> <input id="upFile" type="file"
					name="purchaseContract" style="width: 180px" />
			</div> <br>
			<div>
				 <font style="font-size: 13px;">请选择财务付款：</font>&nbsp;&nbsp;&nbsp;</br>
					<select id="userId1" name="userId"  style="width: 200px;"
						data-options="required:true"></select>

				</div><br>
			<div align="right" style="width: 350px;">
				<a href="javascript:void(0)" onclick="uploadFileFun();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">确
					定</a>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
//下拉选择框数据加载  合同
var sel = $("#contract");  
sel.empty();//清除select中的 option
$.post(ctx+'/contractTemplates/contractTemplates/contract!listContract.action?id='+contractid,
function(data){
	var datas = eval("("+data+")");
	if(null!=datas && ""!=datas){
		for(var i = 0;i < datas.rows.length; i++) {
			var opt = $("<option>").text(datas.rows[i].title).val(datas.rows[i].id);  
			sel.append(opt);  
		}
	}
});
//下拉选择框数据加载  供应商
var sel1 = $("#suppliers");  
sel1.empty();//清除select中的 option
$.post(ctx+'/suppliers/suppliers/suppliers!listSuppliers.action',
function(data){
	var datas = eval("("+data+")");
	if(null!=datas && ""!=datas){
		for(var i = 0;i < datas.rows.length; i++) {
			var opt = $("<option>").text(datas.rows[i].companyName).val(datas.rows[i].id);  
			sel1.append(opt);  
		}
	}
});
//下拉选择框数据加载  采购方公司
var sel5 = $("#purchaseCompany");  
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
var sel6 = $("#userId1");
sel6.empty();//清除select中的 option
$.post(ctx+'/user/user/user!queryAllfinancials.action'
		,function(data){
	var datas = eval("("+data+")");
	if(null!=datas && ""!=datas){
		for(var i = 0;i < datas.rows.length; i++) {
			var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
			sel6.append(opt);  
		}
	}
});	
var optType = 1;
//文件上传，保存
function uploadFileFun(){
	var docType = "";
		var fileName = $('input[name="purchaseContract"]').val();
		if(fileName=="" || null==fileName){
			$.messager.alert('提示', "请选择需要上传的文件。", 'info');
			return;
		}
		var index = fileName.lastIndexOf(".");
		fileName = fileName.substring(index+1,fileName.length);
		if('docx'!=fileName && 'jpg'!=fileName){
			$.messager.alert('提示', "您选择的文件类型不对，只能上传docx和jpg文件。", 'info');
			return;
		}
	$('#addVmform').form('submit', {
 		url:ctx+"purchasecontract/purchasecontract/purchasecontract!savePurchaseContract.action",
 		onSubmit: function(){
 			// 做某些检查
 			// 返回 false 来阻止提交
 			return $(this).form('validate'); 
 		},
 		success:function(data){
 			if(data!=null && data!=""){
 				var data = eval('(' + data + ')');
 				if("ok" == data.msg){
 					$.messager.alert('提示', "操作成功", 'info');
                    window.location.href="../view/main.jsp";
 				} else {
 					$.messager.alert('提示', "操作失败，请从新尝试", 'info');
 				}
 			} else {
 				$.messager.alert('提示', "系统异常，请稍后操作", 'info');
 			}
 		}
 	});
}

</script>
</html>