package cn.com.kxcomm.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import cn.com.kxcomm.contractmanage.InitBean;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.web.util.FilUtil2;
import cn.com.kxcomm.contractmanage.web.util.Parameters;

/**
 * 初始化生成导出打包文件.
 *
 * @author 林洒玛.
 *         Created 2012-12-28.
 */
public class InitExportContract {
	private static InitExportContract instance;
	private InitBean initBean = InitBean.getInstance();
	private Parameters para = Parameters.getInstance();
	private InitExportContract(){
		
	}
	
	public static InitExportContract getInstance(){
		if(instance == null)
			instance = new InitExportContract();
		return instance;
	}
	
	/**
	 * 查询合同文件并导出生成DOC.
	 *
	 * @param conid
	 */
	public void shengchengContractFile(Integer conid){
		
		List<TbContractDocVariable> docVariable = null;
		TbContractDocVariable var = null;
		TbContractDoc contractDoc = null;
		String exportExcelPath = this.para.exportExcelPath;
//		TbContract contract = new TbContract();
//		contract.setId(Integer.parseInt(conid));
//		contractDoc = new TbContractDoc();
//		contractDoc.setContract(contract);
		//查询该合同下的所有合同文件
		List<TbContractDoc> idsd = this.initBean.contractDocService.queryConFileById(conid);
		long fileName = 0;
		//导出DOC文件
		for(int i = 0;i < idsd.size(); i++) {
			contractDoc = idsd.get(i);
			if(null != contractDoc) {
				fileName = System.currentTimeMillis();
				int id = idsd.get(i).getId();
				List<ContractDocVariableVo> volist = this.initBean.contractDocVariableService.queryAllContractDocVariable(id,true);
				docVariable = new ArrayList<TbContractDocVariable>();
				for(int j = 0;j < volist.size(); j++) {
					var = new TbContractDocVariable();
					var.setVariableName(volist.get(j).getVariableName());
					var.setContent(volist.get(j).getContent());
					docVariable.add(var);
				}
				/* @param templatesDoc
				 * @param docVariable
				 * @param exportDoc*/
				String endFile = contractDoc.getDoc().getPath().substring(contractDoc.getDoc().getPath().lastIndexOf("."), contractDoc.getDoc().getPath().length());
				WordTools tools = WordTools.getNewInstance();
				tools.export(para.templatesWordPath+contractDoc.getDoc().getPath(),docVariable,exportExcelPath+fileName+endFile);
				contractDoc.setExportPath(fileName+endFile);
				this.initBean.contractDocService.updateContractDoc(contractDoc);
			}
		}
	}
	
	/**
	 * 导出打包合同文件
	 *
	 * @param conid
	 */
	public void contractFileToPackage(Integer conid) {
		boolean checks = true;
		String end = this.para.exportExcelPath;
		TbContract contract = this.initBean.contractService.getByPk(conid);
		List<TbContractTemplatesDoc> templatesDocs = this.initBean.contractTemplatesDocService.queryConTempDocById(conid.toString());
		for(int j = 0;j < templatesDocs.size(); j++) {
			//判断文件变量是否通过
			boolean checked = this.initBean.contractDocVariableService.checkVariable(templatesDocs.get(j).getId(),conid);
			if(!checked) {
				checks = checked;
			}
		}
		if(!checks) {
			return ;
		}	
		//开始生成doc合同文件
		shengchengContractFile(conid);
		//导出生成报价总表文件
		this.initBean.icontractQuotationsService.exportContractTotalPrice(contract);
		TbContractDoc contractDoc = new TbContractDoc();
		contractDoc.setContract(contract);
		File file = null;
		List<File> filelist = new ArrayList<File>();
		List<TbContractDoc> contractDocs = this.initBean.contractDocService.queryConFileById(contractDoc.getContract().getId());
		for(int i = 0;i < contractDocs.size(); i++) {
			file = new File(end+contractDocs.get(i).getExportPath()+".doc");
			filelist.add(file);
		}
		file = new File(contract.getQuotationsExcel());
		filelist.add(file);
		String fn = System.currentTimeMillis()+".rar";
//		FilUtil2.zipMoreFile(filelist,end+fn);
		contract.setDownloadUrl(fn);
		contract.setStatus(3);
		this.initBean.contractService.updateTbContract(contract);
	}

	/**
	 * 
	 * 同步合同文件到本地服务器
	 * 
	 * @author zhangjh 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public void syncContractDocuments(){
		this.initBean.contractTemplatesDocService.ftpDownloadSync(para.templatesWordPath);
		this.initBean.purchaseOrderContractFileService.ftpDownloadSync(para.purchaseWordPath);
	}

	
	/**
	 * 初始化生成并导出打包所有合同文件.
	 *
	 */
	public void contractFileToPackage() {
		/*List<TbContract> contracts = this.initBean.contractService.findAll();
		for(int i = 0;i < contracts.size(); i++) {
			contractFileToPackage(contracts.get(i).getId());
		}	*/
	}
}
