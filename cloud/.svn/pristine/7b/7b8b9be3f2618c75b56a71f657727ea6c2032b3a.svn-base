<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.io.*"%>
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
   	InputStream stream = null; //输入流
   	OutputStream os = null;	//输出流
    //String paths = request.getSession().getServletContext().getRealPath("/download/quotations/");  //获得服务器相对路径
    try {
    	String filename = request.getParameter("filename")!=null?request.getParameter("filename").toString():"";
    	//String existFileName = request.getParameter("existFileName")!=null?request.getParameter("existFileName").toString():"";
    	String exportPath = request.getParameter("exportPath")!=null?request.getParameter("exportPath").toString():"";
    	
    	File file = new File(exportPath);
    	File[] fs = file.listFiles();
    	boolean flag = true;
    	if(fs!=null && fs.length>0) {
	    	for(File f:fs){
	    		if(f.getName().equals(filename)) {
	    			exportPath = exportPath +file.separator+ f.getName();
	    			flag = false;
	    			break;			
	    		}
	    	}			    	
    	}else {
    		%>
	    		<script type="text/javascript" language="javascript">
		    		alert('文件路径不存在，请联系管理员!');
    				window.history.back();
	    		</script>
	    	<%
    		return;
    	}
    	if(flag){
	    	%>
	    		<script type="text/javascript" language="javascript">
		    			alert('文件还没有打包喔，请先开始打包文件操作!');
	    				window.history.back();
	    		</script>
	    	<%
	    	return;
    	}
        stream = new FileInputStream(exportPath);
        response.setContentType("APPLICATION/DOWNLOAD");
   		response.setHeader("Content-Disposition","attachment; filename="+new String(filename));
        response.setContentLength(stream.available());
        os = response.getOutputStream();
        out.clear();
   		out = pageContext.pushBody(); //解决response.getOutputStream()与response.getWrite()的冲突
        int iBytesRead = 0; 
        byte[] buffer = new byte[Byte.MAX_VALUE];
        while ((iBytesRead = stream.read(buffer)) != -1) {
        	os.write(buffer);
        }
        os.flush();
    } catch (FileNotFoundException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    }finally{
    	if(stream!=null){
    		try{
    			stream.close();
    		}catch(Exception e){
    		}
    	}
    }
%>
<body>
</body>
</html>
