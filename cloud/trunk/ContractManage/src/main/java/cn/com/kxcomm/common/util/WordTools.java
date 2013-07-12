package cn.com.kxcomm.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import cn.com.kxcomm.base.exception.SystemException;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;

/**
 * 
 * 操作word文档查找替换
 * 
 * @author zhangjh 新增日期：2012-12-19
 * @since ContractManage
 */
public class WordTools {
	private static WordTools instance = null;
	Logger log = Logger.getLogger(WordTools.class);
	private WordTools(){
		
	}
	
	public static WordTools getInstance(){
		if(instance == null)
			instance = new WordTools();
		
		return instance;
	}
	
	public static WordTools getNewInstance(){
		return new WordTools();
	}
	
	/**
	 * 
	 * 替换word中的变量并导出.docx
	 * 
	 * @param templatesDoc
	 * @param docVariable
	 * @param exportDoc
	 * @return
	 * @author zhangjh 新增日期：2012-12-20
	 * @since ContractManage
	 */
	public boolean export(String templatesDoc,List<TbContractDocVariable> docVariable,String exportDoc ){
		File bfile = null;
		XWPFDocument doc = null;
		File file = null;
		FileOutputStream fos = null;
		OPCPackage pack  = null;
		try {
			log.info("==================================================templatesDoc:"+templatesDoc+",exportDoc:"+exportDoc);
			bfile = new File(templatesDoc);
			if(!bfile.exists()){
				throw new SystemException("合同模版文件不存在，请检查"+templatesDoc+"是否存在");
			}
			pack = POIXMLDocument.openPackage(templatesDoc);
			doc = new XWPFDocument(pack);
			List<XWPFParagraph> paragraphs = doc.getParagraphs();
			for (XWPFParagraph tmp : paragraphs) {
				List<XWPFRun> runs = tmp.getRuns();
				for (XWPFRun aa : runs) {
					if(aa.getText(0)==null)
						continue;
					String docText = aa.getText(0)!=null?aa.getText(0).trim():null;
					
					for(TbContractDocVariable var:docVariable){
						if (var.getVariableName().equals(docText)) {
							log.info("找到匹配：文档内容->"+docText+",变量名称->"+var.getVariableName());
							aa.setText(var.getContent(), 0);
						}
					}
				}
			}
			file = new File(exportDoc);
			if(file.exists()){
				file.delete();
			}
			fos = new FileOutputStream(exportDoc);
			doc.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bfile!=null){
				bfile = null;
			}
			if(doc!=null){
				doc = null;
			}
			if(file!=null){
				file = null;
			}
			if(pack!=null){
				pack.flush();
				try {
					pack.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 返回word中有效的变量
	 * 
	 * @param templatesDoc
	 * @param docVariable
	 * @return
	 * @author zhangjh 新增日期：2012-12-20
	 * @since ContractManage
	 */
	public HashMap<String, TbContractDocVariable> checkVariable(String templatesDoc,List<TbContractDocVariable> docVariable){
		HashMap<String, TbContractDocVariable> map = new HashMap<String, TbContractDocVariable>();
		try {
			
			OPCPackage pack = POIXMLDocument.openPackage(templatesDoc);
			XWPFDocument doc = new XWPFDocument(pack);
			List<XWPFParagraph> paragraphs = doc.getParagraphs();
			for (XWPFParagraph tmp : paragraphs) {
				List<XWPFRun> runs = tmp.getRuns();
				for (XWPFRun aa : runs) {
					for(TbContractDocVariable var:docVariable){
						if (var.getVariableName().equals(aa.getText(0))) {
							map.put(var.getVariableName(), var);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	* 方法用途和描述: 文件移动
	* @param string  文件存在地址
	* @param string2   文件需要保存的地址
	* @author chenliang 新增日期：2013-1-11
	* @since ContractManage
	 */
	public void moveFile(String string, String string2) {
		try {
			File uploadFile = new File(string);
			if(uploadFile.exists() && uploadFile.isFile()){
				File downPath = new File(string2);
				FileUtils.copyFile(uploadFile, downPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WordTools tools =WordTools.getInstance();
		List<TbContractDocVariable> docVariable = new ArrayList();
		TbContractDocVariable var=new TbContractDocVariable();
		var.setVariableName("$buyer");
		var.setContent("中国联合网络通信有限公司广东省分公司");
		docVariable.add(var);
		var=new TbContractDocVariable();
		var.setVariableName("$perceRateRMBlower");
		var.setContent("￥529,936");
		docVariable.add(var);
		var=new TbContractDocVariable();
		var.setVariableName("$perceRateRMBCaptial");
		var.setContent("伍拾贰万玖仟玖佰叁拾陆圆整");
		docVariable.add(var);
		var=new TbContractDocVariable();
		var.setVariableName("$PrelimipayRMBlower");
		var.setContent("￥151,410");
		docVariable.add(var);
		var=new TbContractDocVariable();
		var.setVariableName("$finalPayRMBlower");
		var.setContent("￥75,705");
		docVariable.add(var);
		var=new TbContractDocVariable();
		var.setVariableName("$finalPayRMBCapital");
		var.setContent("柒万伍仟柒佰零伍");
		docVariable.add(var);
		try {
			tools.export("D://1364354107634.docx",docVariable, "D://aa.docx");
		//	tools.export("E://be use to tools//apache-tomcat-6.0.20//wtpwebapps//ContractManage//upload//ContractTemplates//1357350463089.docx",docVariable, "E://be use to tools//apache-tomcat-6.0.20//wtpwebapps//ContractManage//download//ContractTemplates//1357350463843.docx");
		//	List<File> filelist = new ArrayList();
	//		File file1 = new File("E://be use to tools//apache-tomcat-6.0.20//wtpwebapps//ContractManage//download//ContractTemplates//1357350710201.docx");
		//	File file2 = new File("E://be use to tools//apache-tomcat-6.0.20//wtpwebapps//ContractManage//download//ContractTemplates//1357350463843.docx");
//			File file3 = new File("D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp0//wtpwebapps//ContractManage//download//quotations//\1356923564634.doc");
//			File file4 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包2.docx");
//			File file5 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包3.docx");
//			File file6 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包4.docx");
	//		filelist.add(file1);filelist.add(file2);
//			filelist.add(file3);//filelist.add(file4);filelist.add(file5);filelist.add(file6);
//			FilUtil2.zipMoreFile(filelist, "E://be use to tools//apache-tomcat-6.0.20//wtpwebapps//ContractManage//download//ContractTemplates//ii.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
