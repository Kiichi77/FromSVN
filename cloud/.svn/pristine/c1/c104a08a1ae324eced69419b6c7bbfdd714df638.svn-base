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
<script type="text/javascript" src="view/addcontract_view.js"></script>
<script type="text/javascript" src="controller/addcontract_controller.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';	
var contractTemplatesId = '<%=request.getParameter("id")%>'; //合同模板总表id
var iscontext = '<%=request.getParameter("iscontext")%>'; //是否已经审核  0:未审核  1:已审核
var templatesId; //合同模板id，用于修改
</script>
<body class="easyui-layout">
	<div id="datagridsearch">
		文件名称:<input id="kwords" name="kword" class="easyui-validatebox" style="width:150px;padding: 2px;"></input>
	        文件类型:<select id="filesType" name="filesType" style="width:200px;" data-options="required:true"></select>
		<a href="javascript:void(0);" onclick="query();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提 交</a>
	</div>
	<div data-options="region:'center'" title="合同模版" style="overflow:hidden;">

	      <table id="tt"></table>
		  
		  		<!-- 弹出变量log -->
	  <div id="dd" data-options="iconCls:'icon-save'" title='合同模版' style="padding:5px;width:500px;height:400px;display: none;modal: true;">
	        <table id="ta"></table>
	</div>
	  
		  		<!-- 弹出上传log -->
<!-- 	<div id="add" title="上传文件" style="background:#fafafa;padding:20px;width:360px;height:200px;border: solid gray 1px;"> -->
<!-- 		<form id="addVmform" method="post" enctype="multipart/form-data" novalidate style="width: 350px;height:100px;"> -->
<!--             <div> -->
<!--             	请输入名称： -->
<!--             	<input id="tempName" type="text" class="easyui-validatebox" style="width:180px;" name="contractTemplatesDocVo.docname" data-options="required:true" /> -->
<!--             </div> -->
<!--             <br> -->
<!--             <div id="typeDIV" > -->
<!--             	请选择类型： -->
<!-- 			</div> -->
<!--             <br> -->
<!--             <div> -->
<!--             	  是否设置变量： -->
<!-- 	             <select id="isVariable" style="width:180px;" name="contractTemplatesDocVo.bVariable" > -->
<!-- 		              <option value="1">是</option> -->
<!-- 		              <option value="2">否</option> -->
<!-- 	             </select> -->
<!--             </div> -->
<!--             <br> -->
<!--             <div id="filediv"  > -->
<!--             	 选择您要上传的文件：<br> -->
<!--            		 <input id="upFile" type="file" name="templatesDoc" style="width: 180px"/> -->
<!--             </div> -->
<!--                   <div align="right" style="width: 350px;"> -->
<!-- 			    	 <a href="javascript:void(0)" onclick="uploadFileFun();"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a> -->
<!-- 			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a> -->
<!-- 		   		</div> -->
<!--    		 </form> -->
<!-- 	</div> -->
	</div>
</body>
</html>