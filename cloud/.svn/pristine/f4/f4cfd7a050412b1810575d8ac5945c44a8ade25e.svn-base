<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../commond/jquery-ui-1.9.1.custom/css/smoothness/jquery-ui-1.9.1.custom.css" rel="stylesheet">
<script src="../../commond/jquery-ui-1.9.1.custom/js/jquery-1.8.2.js"></script>
<script src="../../commond/jquery-ui-1.9.1.custom/js/jquery-ui-1.9.1.custom.js"></script>

 <script type="text/javascript">
 $(function(){
	 //全选
	   $("#all").click(function(){ 
         $("input[name='ids']").attr("checked",$(this).attr("checked"));
        });
	   //弹出窗口
	   $("#editinstances").click(function(){
		   window.open('editinstances.jsp','newwindow','height=300,width=650,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no'); 
	   });
 });
 </script>

</head>
<body style="background-image: url('../../commond/image/blace.jpg');">
  <div id="first" style="background-color: #f1f1f1;width: 100%;height: 50px;margin-top: -10px;margin-left: -10px;margin-right: -10px"><font style="font-size: 30px; margin-left: 30px;">所有运行实例</font></div>
  <br><br>
  <div id="second" style="margin-left: 30px;width: 95%"><font style="font-size: 22px;">运行实例</font><button style="height: 30px; float: right;background-color: #ff3333">终止运行实例</button></div>
  <br>
  <table style="border:1px gray solid; margin-left: 20px;margin-left: 30px;" cellpadding="3" width="95%;" align="center" rules="rows">
      <tr align="center" style="background-color: #F1F1F1;font-weight: bold;" height="40">
	      <td style=" border-right:1px gray solid;"><input type="checkbox" id="all" /></td>
	      <td style=" border-right:1px gray solid;">租户</td>
	      <td style=" border-right:1px gray solid;">主机</td>
	      <td style=" border-right:1px gray solid;">运行实例名称</td>
	      <td style=" border-right:1px gray solid;">IP地址</td>
	      <td style=" border-right:1px gray solid;">大小</td>
	      <td style=" border-right:1px gray solid;">状态</td>
	      <td style=" border-right:1px gray solid;">工作</td>
	      <td style=" border-right:1px gray solid;">电源状态</td>
	      <td style=" border-right:1px gray solid;">动作</td>
      </tr>
      <tr align="center" height="40">
          <td style=" border-right:1px gray solid;"><input type="checkbox" name="ids" /></td>
          <td style=" border-right:1px gray solid;">admin</td>
          <td style=" border-right:1px gray solid;">ubuntu</td>
          <td style=" border-right:1px gray solid;">window server 2003</td>
          <td style=" border-right:1px gray solid;">10.11.11.5<br>10.10.182.192</td>
          <td style=" border-right:1px gray solid;">2GB 内存 | 1 虚拟处理器 | 110.0GB 硬盘</td>
          <td style=" border-right:1px gray solid;">Suspended</td>
          <td style=" border-right:1px gray solid;">Resuming</td>
          <td style=" border-right:1px gray solid;">Running</td>
          <td style=" border-right:1px gray solid;">
	          <div id="opeDIV">
		          <button style="height: 30px;width: 140px" id="editinstances">编辑运行实例</button>
		         <select style="height: 30px">
		             <option value="1">VNC界面</option>
		             <option value="1">查看日志</option>
		             <option value="1">快照</option>
		             <option value="1">暂停  运行实例</option>
		             <option value="1">休眠  运行实例</option>
		             <option value="1">重启  运行实例</option>
		             <option value="1">终止运行  运行实例</option>
		         </select>	          
	          </div>
          </td>     
      </tr>        
      <tr align="center" style="background-color: #F1F1F1;" height="40">
          <td colspan="10" align="left">正在显示1个项目</td>
      </tr>
 </table>
</body>
</html>