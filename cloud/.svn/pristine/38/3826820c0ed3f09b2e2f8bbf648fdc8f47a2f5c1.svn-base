<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
   String path=request.getContextPath();
   request.setAttribute("ctx", path);
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript" src="view/addInquiryOrder_view.js"></script>
<script type="text/javascript">  
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="询价单" style="overflow:hidden;">
		<div>
		    <form id="addDataInfoform" method="post"  enctype="multipart/form-data" novalidate style="width: auto;height:60px;">
		    	<table style="margin-top:10px;width: auto;">
		    		<tr style="float: center;">
		    			<td style="width: 30%;">
							<font style="font-size: 13px;">开始时间：&nbsp;&nbsp;</font>
		    	    	<input id="starttime" name="starttime" class="easyui-datebox" style="width:165px;padding: 2px;"></input>
		    			</td>
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">结束时间：&nbsp;</font>
		    	    		<input id="endtime" name="endtime" class="easyui-datebox" style="width:170px;padding: 2px;"></input>
		    			</td>
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">审核人：</font>
			    	    	<select id="shujuyuan" name="model.dataSource.id" style="width:160px;">
				    	    	<option value="1">找小刘</option>
				    	    	<option value="2">六肖奇</option>
				    	    	<option value="3">陈晓吧</option>
				    	    </select>
		    			</td>
		    		</tr>
		    		<tr style="float: center;">
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">询价单名称：</font>
			    	    	<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;" data-options="required:true" />
		    			</td>
		    			<td style="width: 30%;">
		    				<font style="font-size: 13px;">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</font>
		    	    		<input id="dinghuohao" name="model.productOrderNo" class="easyui-validatebox" type="text" style="width: 160px;"  />
		    			</td>
		    		</tr>
		    	</table>
		   </form>
		   </div>
		   <div>
			 <table id="tt" >
		     </table>
		</div>
		<div>
			 <div align="right" style="width: 450px;margin-top: 10px;">
		    	<a href="javascript:void(0);" onclick="javascript:alert('添加成功！')" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	<a href="#" onclick="javascript:window.location.href='inquiryOrder.jsp';" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
	   	 	</div>
		</div>
	</div>
</body>
</html>