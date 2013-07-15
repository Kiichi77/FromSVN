<%@page import="cn.com.kxcomm.activiti.tools.ProcessCustomService,java.io.*"%>
<%
	String taskId = request.getParameter("taskId");
	InputStream imageStream =  
			ProcessCustomService.getImageStream(taskId);
	byte[] b = new byte[1024];
	int len = -1;
	OutputStream outputStream = response.getOutputStream();
	while ((len = imageStream.read(b, 0, 1024)) != -1) {
		outputStream.write(b, 0, len);
	}
	out.clear();
	out = pageContext.pushBody();
%>