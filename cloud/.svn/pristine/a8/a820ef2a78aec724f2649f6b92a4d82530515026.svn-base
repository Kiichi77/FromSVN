<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/attachtype_view.js"></script>
<script type="text/javascript" src="controller/attachtype_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
</script>


<body class="easyui-layout">
	<div data-options="region:'center'" title="合同附件分类" style="overflow:hidden;">
	<div>
	 <table id="tt"></table>
		</div>	   
		
		
		<!-- 上传合同 -->
  <div id="addVmDiv" style="background:#fafafa;padding:20px;width:360px;height:100px;display: none;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 350px;height:100px;">
	    	<div id="thevmleft" style="width: 210px;height:100px;float: left;">
	             <br>
	    	     <div><font style="font-weight: bold;font-size: 11px;">请输入一个分类名称   </font>
	    	    <br>
	    	     <input id="name" name="contractDocType.name" type="text" style="width:200px" /></div>
	    	   <br>
	    	      <div align="right" style="width: 350px;">
		    	 <a href="#" onclick="addContractDocType();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
		    	 <a href="#" onclick="javascript:$('#addVmDiv').css('display','none');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    </div>
	    	</div>
	    	</form>
	    	</div>
	</div>
</body>
</html>