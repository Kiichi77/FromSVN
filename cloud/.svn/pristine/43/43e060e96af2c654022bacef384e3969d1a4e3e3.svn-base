package cn.com.kxcomm.contractmanage.web.action;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PreviewFileAction extends ActionSupport {
	private static final long serialVersionUID = 2941293222912440584L;
	
	/**
	 * 
	 * word2003预览
	 * 
	 * @return
	 * @throws Exception
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public String word2003() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");  
		response.setLocale(Locale.CHINESE);  
		response.setDateHeader("Expires", 0);  
		response.setHeader("Cache-Control", "no-cache");  
		response.setHeader("Pragma", "no-cache"); 
		String urlReal = request.getParameter("urlReal");
		if(urlReal==null || "".equals(urlReal) || !urlReal.endsWith(".doc")){
			return ERROR;
		}
		BufferedInputStream bis = null;
		URL url = null;
		HttpURLConnection httpUrl = null;
		url = new URL(urlReal);
		httpUrl = (HttpURLConnection) url.openConnection();

		httpUrl.connect();
		bis = new BufferedInputStream(httpUrl.getInputStream());
		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><title>预览文件</title></head><body>");
		
		WordExtractor ex = new WordExtractor(bis);
		
		String br = ex.getText().replaceAll("\n", "<br/>");
		bodyText.append(br);
		bodyText.append("</body></noframes></html>");
		response.getWriter().write(bodyText.toString());
		return NONE;
	}
	
	/**
	 * 预览word2007
	 * 
	 * @return
	 * @throws Exception
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public String word2007() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");  
		response.setLocale(Locale.CHINESE);  
		response.setDateHeader("Expires", 0);  
		response.setHeader("Cache-Control", "no-cache");  
		response.setHeader("Pragma", "no-cache"); 
		String file = request.getParameter("file");
		String title = request.getParameter("title");
		if(file==null || "".equals(file) || !file.endsWith(".docx")){
			return ERROR;
		}
		 OPCPackage opcPackage = POIXMLDocument.openPackage(file);   
	     POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage);    
	     if(title==null || "".equals(title)){
	    	 title ="预览Word2007";
	     }
		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><title>"+title+"</title></head><body>");
		String br = ex.getText().replaceAll("\n", "<br/>");
		bodyText.append(br);
		bodyText.append("</body></noframes></html>");
		response.getWriter().write(bodyText.toString());
		return NONE;
	}
}
