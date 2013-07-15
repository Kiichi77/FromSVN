<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../view/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../images/mm_health_nutr.css" type="text/css">
<title>首页</title>
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<script language="javascript">
<!--

//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("一月","二月","三月","四月","五月","六月","七月","八月","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
//-->
</script>
</head>
<body style="overflow-y:hidden">
<div id="container">
  <div id="header" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="text-align: center;">
  <tr bgcolor="#D5EDB3">
    <td width="800" height="50"  valign="bottom" align="center" nowrap  id="logo"><img src="../images/title.png" alt="" />
 </td>
    
  </tr>

  </table>
  </div>
  <div id="menu">
  <div id="dateformat" style="margin-left:10px;">
  <script language="javascript">
      document.write(TODAY);	</script>
      <span style="text-align: right;"><a href="mywork.jsp" target="main">我的任务</a>  <a href="../login.jsp" target="_self">退出</a></span>
  </div>
  </div>
      <div id="mainContent">
    <div id="sidebar">
    	<div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:1800;height:700px;">
    	<div title="管理" data-options="tools:'#p-tools'">
    	<br>
    	    <a href="source/sourceData_manage.jsp" target="main">可选配件源数据</a><p/>
        	<a href="../contract/contract.jsp" target="main">合同模版管理</a><p/>
        	<a href="../systemUser/systemUser.jsp" target="main">账户管理</a><p/>
        	<a href="../roleManager/roleManager.jsp" target="main">角色管理</a><p/>
        	<a href="../logmanager/logManager.jsp" target="main">日志记录</a><p/>
    	</div>
    	<div title="项目" data-options="tools:'#p-tools'">
    		<div id="menu22" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="padding1:0px;overflow:hidden;">
               <div title="销售管理" >
                <a href="../project/projectManager.jsp" target="main">项目管理</a><p/>
                <a href="../flowChart/flowChart.jsp" target="main">项目流程</a><p/>
	            <a href="client/client_manage.jsp" target="main">客户管理</a><p/>
	            <a href="config/config.jsp" target="main">机型配置管理</a><p/>
	            <a href="rapaport/rapaport.jsp" target="main">报价表</a><p/>
				<a href="../contract/createcontract/createcontract.jsp" target="main">生成合同</a><p/>
				<a href="../salesOrderManager/salesOrderManager.jsp" target="main">销售订单管理</a><p/>
	           	<a href="../invoiceApplication/invoiceApplication.jsp" target="main">发票申请</a><p/>
	           	<a href="../salesReceivables/salesReceivables.jsp" target="main">收款项</a><p/>
               </div>
               <div title="采购管理" >
                <a href="supplier/supplier_manage.jsp" target="main"> 供应商管理</a><p/>
            	<!-- <a href="../purchaseOrder/purchaseOrder.jsp" target="main">采购申请单</a><p/> -->
              	<a href="../InquiryOrder/inquiryOrder.jsp" target="main">询价单</a><p/>
             	<a href="../purchaseOrder/orders.jsp" target="main">采购订单</a><p/>
               </div>
               <div title="售后管理" >
                <a href="aftersale/aftersale_manage.jsp" target="main">收货列表</a><p/>
                <a href="aftersale/accept.jsp" target="main">初验/终验</a><p/>
               </div>
               <div title="财务管理" >
                <a href="finance/payment.jsp" target="main">采购付款项</a><p/>
           		<a href="../financialProceedsReceived/financialProceedsReceived.jsp" target="main">销售收款项</a><p/>
           	    <a href="../invoiceManager/invoiceManager.jsp" target="main">发票管理</a><p/>
               </div>
               <div title="审核管理" >
                <a href="check/checkRapaport_manage.jsp" target="main">审核报价表</a><p/>
            	<a href="../auditInvoices/auditInvoices.jsp" target="main">审核发票</a><p/>
            	<a href="../InquiryOrder/orderChecked.jsp" target="main">审核订单</a><p/>
               </div>
             </div>
    	</div>
    	</div>
     	
    </div>
    <div id="content">
     <iframe name="main" src="mywork.jsp" width="98%" height="100%"  frameborder="no"  scrolling="auto" style="overflow-y:hidden"></iframe>
    </div>
  </div>
  
  
  

  

</div>


</body>
</html>