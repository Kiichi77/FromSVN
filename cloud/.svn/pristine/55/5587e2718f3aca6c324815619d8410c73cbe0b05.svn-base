<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Excel导出</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
     
 </head>
 <body>
 	  <div style="margin:10px 0;"></div>  
    <div class="easyui-panel" title="用户信息" style="width:400px">  
        <div style="padding:10px 0 10px 60px">  
        <form id="excelForm" method="post" enctype="multipart/form-data" action="jpPersonController.do?implXls">  
            <table>  
				<tr>
					<td>文件路径</td>
					<td><input name="filedata" class="easyui-validatebox" 
					                                   required="true" type="file" missingMessage="请选择上传文件" /></td>
				</tr>            
            </table>  
        </form>  
        </div>  
        <script type="text/javascript"> 
      
        /*
       function importExcel(){
    	   alert("ok");
    		if($('#excelForm').form('validate')){
    			$('#excelForm').submit();	
     		}
       }
        */
        function importExcel(){
			$('#excelForm').form({
				success : function(result) {
					try {
						var r = $.parseJSON(result);
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					} catch (e) {
						$.messager.alert('提示', result);
					}
				}
			});    	   
    		if($('#excelForm').form('validate')){
    			$('#excelForm').submit();	
     		}
       }
   </script>  
        <div style="text-align:center;padding:5px">  
             <a href="#" class="easyui-linkbutton" id="btn_sub" iconCls="icon-ok" onclick="importExcel();">上传</a>
        </div>  
    </div>  
 </body>