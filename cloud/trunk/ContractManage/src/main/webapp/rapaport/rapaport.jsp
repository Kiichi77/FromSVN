<%@page import="cn.com.kxcomm.contractmanage.web.util.Parameters"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
String download = request.getSession().getServletContext().getRealPath("/download/quotations/");
request.setAttribute("download", download);

//合同打包文件下载地址
Parameters param = Parameters.getInstance();
String exportPath = param.exportExcelPath;
request.setAttribute("exportPath", exportPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/rapaport_view.js"></script>
<script type="text/javascript" src="controller/rapaport_controller.js"></script>
<script type="text/javascript">
	   var ctx = '${ctx}';
	   var exportPath='${exportPath}';
	   <%-- var processid = '<%=request.getParameter("processid")%>'; --%>
	   var userId = '<%=request.getParameter("caudit")%>';
	   var projectId='<%=request.getParameter("projectId")%>';
	   var userTaskPkid='<%=request.getParameter("id")%>';
	   var muluhejia = 0;
		var zhekouhoujia = 0;
		var zhekouhouxianchang = 0;
		var hejijia = 0;
</script>
<body class="easyui-layout">
	<div data-options="region:'center'" title="报价表" style="overflow:hidden;">      
	    <table id="tt"></table>
    </div>
    
    <!-- 分配任务给下一个处理人 -->
	<div id="addprocessdiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="合同模板审核" style="padding: 10px;width: 350px;height:250px;overflow:hidden" iconCls="icon-add">
   	  <form id="addVmform1" method="post" novalidate style="width: 300px;height:100px;">
	    	<div id="processdiv" style="float: center;">
    			<input type="hidden" id="quotationsId" name="checkedVo.id"/>
    			<input type="hidden" id="projectId" name="checkedVo.projectId"/>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">审核人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					 <select id="tbUser" name="checkedVo.user_id" style="width:180px;" data-options="required:true"></select>
	    	    </div><br>
	    	    <div style="margin-left: 20px"><font style="font-size: 13px;">备   注：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <textarea id="describe"  class="easyui-validatebox" rows="4" cols="30" name="checkedVo.describe"></textarea></div>
	    	    </div><br>
	    	    <div align="right" >
			    	<a href="javascript:void(0);" onclick="checkedQuotation();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	<a href="#" onclick="hideDataInfoDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 		</div>
      	</form>
	</div>
	
		<!-- 弹出层报价表详情 -->
    <div id="addVmDiv1" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="报价表详情" style="background:#fafafa;padding:20px;width:800px;height:420px;">
		    <input type="hidden" id="alltable" name="baoJiaTable">
		    <div id="addthevmleft" style="width: 280px;height:80px;float: left;">
		    	<!-- <div><font style="font-size: 13px;">项目名称&nbsp;&nbsp;：</font>
		           <input id="projectNames" type="text" style="width:160px" disabled="disabled"/>
		        </div> -->
		        <div><font style="font-size: 13px;">报价表名称：</font>
		        	<input id="rapaportNames" type="text" style="width:160px" disabled="disabled"/>
		        </div>
		    </div>
		    <br>
	    <!--  价格总表table  -->
	    <div style="width: 760px;height:220px;overflow-y:scroll;"><table id="t11"></table></div><br>
	    <div align="right" style="width: 720px;">
			<a href="#" onclick="hideDiv1();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
		</div>
	</div>
			<!-- 配置详情弹出层 -->
	<div id="configDIV" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="配置详情" style="padding:20px;width:auto;height:auto;" iconCls="icon-add">
		<div style="overflow-y: scroll;height: 250px;">
			<div id="menu1" data-options="region:'west',split:true" class="easyui-accordion" data-options="fit:true,border:false" style="width:auto;padding1:0px;overflow-x:auto;">
		    <table id="t1"></table>
		    <table id="t2"></table>
		    <table id="t3"></table>
		    <table id="t4"></table>
			</div>
		</div>
		<br>
		<div align="right" style="width: 1200px;">
		    <a href="#" onclick="hideConfigDIV();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">关闭</a>
		</div>
	</div>
</body>
</html>