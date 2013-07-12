<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../view/head.jsp"%>
<%
String srealPath = session.getAttribute("realPath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>top</title>
<script type="text/javascript">
$(function(){
// 	$('#menu').accordion('select',0);
// 	$('#menu').accordion({
// 		onSelect:function(){
// 			var pp = $('#menu').accordion('getSelected');
// 			if (pp){
// 				var index = $('#menu').accordion('getPanelIndex',pp);
// 			//	alert(index);
// 			}
// 		}
// 	});
	$.post(ctx+ 'customer/customer/customer!queryMenuById.action',function(data) {
		if (null != data.menulist && 0 < data.menulist.length) {
			var xiaodiv = "";
			for(var i = 0; i < data.menulist.length; i++) {
				xiaodiv += "<div title='"+data.menulist[i].menuName+"'>";
				if(null != data.menulist[i].list && 0 < data.menulist[i].list.length) {
					for(var j = 0; j < data.menulist[i].list.length; j++) {
						xiaodiv += "<p><a href='"+realCtx+data.menulist[i].list[j].url+"' target='main'>"+data.menulist[i].list[j].rightName+"</a><p/>";
					}
				}
				xiaodiv += "</div>";
				$('#menu').accordion('add',{
					title:data.menulist[i].menuName,
					content:xiaodiv
				});
				xiaodiv = "";
			}
		}
	});
});
</script>
</head>

<body class="easyui-layout" >
		<div id="menu" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:190px;padding1:0px;overflow:hidden;">
<!-- 			<div title="系统初始化管理"> -->
<!-- 	    	    <p><a href="../Design/source/sourceData_manage.jsp" target="main">可选配件源数据</a><p/> -->
<!-- 	        	<p><a href="../contract/contract.jsp" target="main">合同模版管理</a><p/> -->
<!-- 	        	<p><a href="../systemUser/systemUser.jsp" target="main">账户管理</a><p/> -->
<!-- 	        	<p><a href="../roleManager/roleManager.jsp" target="main">角色管理</a><p/> -->
<!-- 	        	<p><a href="../logmanager/logManager.jsp" target="main">日志记录</a><p/> -->
<!--     		</div> -->
<!-- 			<div title="销售管理"> -->
<!--                 <p><a href="../project/projectManager.jsp" target="main">项目管理</a><p/> -->
<!--                 <p><a href="../flowChart/flowChart.jsp" target="main">项目流程</a><p/> -->
<!-- 	            <p><a href="../Design/client/client_manage.jsp" target="main">客户管理</a><p/> -->
<!-- 	            <p><a href="../Design/config/config.jsp" target="main">机型配置管理</a><p/> -->
<!-- 	            <p><a href="../Design/rapaport/rapaport.jsp" target="main">报价表</a><p/> -->
<!-- 				<p><a href="../contract/createcontract/createcontract.jsp" target="main">生成合同</a><p/> -->
<!-- 				<p><a href="../salesOrderManager/salesOrderManager.jsp" target="main">销售订单管理</a><p/> -->
<!-- 	           	<p><a href="../invoiceApplication/invoiceApplication.jsp" target="main">发票申请</a><p/> -->
<!-- 	           	<p><a href="../salesReceivables/salesReceivables.jsp" target="main">收款项</a><p/> -->
<!--             </div> -->
<!-- 			<div title="采购管理" > -->
<!--                 <p><a href="../Design/supplier/supplier_manage.jsp" target="main"> 供应商管理</a><p/> -->
<!--               	<p><a href="../InquiryOrder/inquiryOrder.jsp" target="main">询价单</a><p/> -->
<!--              	<p><a href="../purchaseOrder/orders.jsp" target="main">采购订单</a><p/> -->
<!--             </div> -->
<!--             <div title="售后管理" > -->
<!--                 <p><a href="../Design/aftersale/aftersale_manage.jsp" target="main">收货列表</a><p/> -->
<!--                 <p><a href="../Design/aftersale/accept.jsp" target="main">初验/终验</a><p/> -->
<!--             </div> -->
<!--             <div title="财务管理" > -->
<!--                 <p><a href="../Design/finance/payment.jsp" target="main">采购付款项</a><p/> -->
<!--            		<p><a href="../financialProceedsReceived/financialProceedsReceived.jsp" target="main">销售收款项</a><p/> -->
<!--            	    <p><a href="../invoiceManager/invoiceManager.jsp" target="main">发票管理</a><p/> -->
<!--             </div> -->
<!--             <div title="审核管理" > -->
<!--                 <p><a href="../Design/check/checkRapaport_manage.jsp" target="main">审核报价表</a><p/> -->
<!--             	<p><a href="../auditInvoices/auditInvoices.jsp" target="main">审核发票</a><p/> -->
<!--             	<p><a href="../InquiryOrder/orderChecked.jsp" target="main">审核订单</a><p/> -->
<!--             </div> -->
		</div>

	<div id="themains" data-options="region:'center'" title="" style="overflow: hidden; padding: 10px; margin-top: 40px;"></div>
</body>
</html>