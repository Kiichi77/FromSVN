package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.common.util.WordTools;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractQuotations;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractDocService;
import cn.com.kxcomm.contractmanage.service.IContractDocVariableService;
import cn.com.kxcomm.contractmanage.service.IContractQuotationsService;
import cn.com.kxcomm.contractmanage.service.IContractService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocService;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.web.util.FilUtil2;
import cn.com.kxcomm.contractmanage.web.util.Parameters;

/**
 * 
* 功能描述:合同模板总表
* @author chenliang 新增日期：2012-12-20
* @since ContractManage
 */
public class ContractQuotationsAction extends BaseAction<TbContractQuotations, String> {
	
	private static Logger LOGGER = Logger.getLogger(ContractQuotationsAction.class);
	
	@Autowired(required=true)
	public IContractQuotationsService icontractQuotationsService;
	private String contractId; // 模板id
	private String conid;// 合同ID 
	private IContractService contractService;
	private IContractDocService contractDocService;
	private IContractDocVariableService contractDocVariableService;
	private IContractTemplatesDocService contractTemplatesDocService;
	
	public IContractTemplatesDocService getContractTemplatesDocService() {
		return this.contractTemplatesDocService;
	}

	public void setContractTemplatesDocService(
			IContractTemplatesDocService contractTemplatesDocService) {
		this.contractTemplatesDocService = contractTemplatesDocService;
	}

	private Parameters para = Parameters.getInstance();
	public String getConid() {
		return this.conid;
	}

	public void setConid(String conid) {
		this.conid = conid;
	}

	public IContractDocVariableService getContractDocVariableService() {
		return this.contractDocVariableService;
	}

	public void setContractDocVariableService(
			IContractDocVariableService contractDocVariableService) {
		this.contractDocVariableService = contractDocVariableService;
	}

	public IContractDocService getContractDocService() {
		return this.contractDocService;
	}

	public void setContractDocService(IContractDocService contractDocService) {
		this.contractDocService = contractDocService;
	}

	public IContractService getContractService() {
		return this.contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	public IContractQuotationsService getIcontractQuotationsService() {
		return icontractQuotationsService;
	}

	public void setIcontractQuotationsService(
			IContractQuotationsService icontractQuotationsService) {
		this.icontractQuotationsService = icontractQuotationsService;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * 
	* 方法用途和描述: 根据合同id查询合同报价总表
	* @return
	* @author chenliang 新增日期：2012-12-20
	* @since ContractManage
	 */
	public String listContractQuotations(){
		try {
			if(!BlankUtil.isBlank(contractId)){
				int templateId = Integer.parseInt(contractId);
				List<ContractQuotationsVo> volist = icontractQuotationsService.queryAllQuotations(templateId);
				PageInfo<ContractQuotationsVo> pageInfo = new PageInfo<ContractQuotationsVo>();
				pageInfo.setRows(volist);
				pageInfo.setTotal(volist.size());
				result = JSONObject.fromObject(pageInfo);
			}
		} catch (Exception e) {
			LOGGER.error("",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:根据合同ID查询下面的配置单,执行合同是查询配置单
	* @return
	* @author luj 新增日期：2013-3-1
	* @since ContractManage
	 */
	public String queryAllConfigModels(){
		try {
			if(!BlankUtil.isBlank(conid)){
				int templateId = Integer.parseInt(conid);
				List<ContractQuotationsVo> volist = icontractQuotationsService.queryAllConfigModels(Integer.parseInt(conid));
				PageInfo<ContractQuotationsVo> pageInfo = new PageInfo<ContractQuotationsVo>();
				pageInfo.setRows(volist);
				pageInfo.setTotal(volist.size());
				result = JSONObject.fromObject(pageInfo);
			}
		} catch (Exception e) {
			LOGGER.error("",e);
		}
		return SUCCESS;
	}
	/**
	 * 
	* 方法用途和描述:保存加个总表表单数据
	* @return
	* @author lizl 新增日期：2012-12-21
	* @since ContractManage
	 */
	public String saveContractQuotations(){
		LOGGER.info("data:  "+this.contractId);
		JSONArray jsonArray = JSONArray.fromObject(this.contractId);
		List<ContractQuotationsVo> volist = JSONArray.toList(jsonArray,ContractQuotationsVo.class);
		String end = this.icontractQuotationsService.saveAndUpdateQuotation(volist);
		long fileName = System.currentTimeMillis();
		TbContract contract = this.contractService.queryTbContractById(volist.get(0).getContractId());
		contract.setQuotationsExcel(fileName+".xlsx");
		end = this.contractService.updateTbContract(contract);
		this.result = JSONObject.fromObject(successInfo(end));
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:生成合同文件
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	public String shengchengContractFile(){
		WordTools tools = WordTools.getInstance();
		List<TbContractDocVariable> docVariable = null;
		TbContractDocVariable var = null;
		TbContractDoc contractDoc = null;
		String end = this.para.exportWordPath;
		String docTemp = this.para.templatesWordPath;
//		TbContract contract = new TbContract();
//		contract.setId(Integer.parseInt(this.conid));
//		contractDoc = new TbContractDoc();
//		contractDoc.setContract(contract);
		//查询该合同下的所有合同文件
		List<TbContractDoc> idsd = this.contractDocService.queryConFileById(Integer.parseInt(this.conid));
		long fileName = 0;
		//导出DOC文件
		System.out.println("****************************DocStart********************     List<TbContractDoc>:"+idsd.size());
		for(int i = 0;i < idsd.size(); i++) {
			contractDoc = idsd.get(i);
			if(null != contractDoc) {
				String endPath = contractDoc.getDoc().getPath();
				fileName = System.currentTimeMillis();
				//判断文件是否是图片，不是图片才进行下面操作
				if(endPath.endsWith("docx")){
					int id = idsd.get(i).getId();
					List<ContractDocVariableVo> volist = this.contractDocVariableService.queryAllContractDocVariable(id,true);
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
					System.out.println(docTemp+endPath+"       "+end+fileName+".docx");
					tools.export(docTemp+endPath,docVariable,end+fileName+".docx");
					contractDoc.setExportPath(fileName+".docx");
				}else if(endPath.endsWith("jpg")){
					//把图片移到下载的文件夹
					tools.moveFile(docTemp+endPath,end+fileName+".jpg");
					contractDoc.setExportPath(fileName+".jpg");
					LOGGER.info("文件是图片"+endPath);
				}
				this.contractDocService.updateContractDoc(contractDoc);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:打包合同文件
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	public String contractFileToPackage() {
		boolean checks = false;
		String end = this.para.exportExcelPath;
		TbContract contract = this.contractService.getByPk(Integer.parseInt(this.conid));
		List<TbContractTemplatesDoc> templatesDocs = this.contractTemplatesDocService.queryConTempDocById(this.conid);
		for(int j = 0;j < templatesDocs.size(); j++) {
			//判断文件变量是否通过
			boolean checked = this.contractDocVariableService.checkVariable(templatesDocs.get(j).getId(),Integer.parseInt(this.conid));
			if(checked) {
				checks = checked;
			}
		}
		if(!checks) {
			this.result = JSONObject.fromObject(successInfo("uncheck"));
			return SUCCESS;
		}	
		try {
			//开始生成doc合同文件
			shengchengContractFile();
		} catch (Exception e) {
			this.result = JSONObject.fromObject(successInfo("docSB"));
			e.printStackTrace();
			return SUCCESS;
		}
		//导出生成报价总表文件
		this.icontractQuotationsService.exportContractTotalPrice(contract);
//		TbContractDoc contractDoc = new TbContractDoc();
//		contractDoc.setContract(contract);
		File file = null;
		Map<String,Object> map = null;
		List<Map> filelist = new ArrayList<Map>();
		List<TbContractDoc> contractDocs = this.contractDocService.queryConFileById(Integer.parseInt(this.conid));
		System.out.println("****************************packageStart********************");
		for(int i = 0;i < contractDocs.size(); i++) {
			map = new HashMap<String,Object>();
			file = new File(this.para.exportWordPath+contractDocs.get(i).getExportPath());
			map.put("file",file);     
			map.put("fileName",FilUtil2.getContractRenameStr(contractDocs.get(i).getExportPath(),contractDocs.get(i).getFileName()));
			filelist.add(map);
		}
		LOGGER.debug("para.exportExcelPath"+para.exportExcelPath);
		LOGGER.debug("+++++++++++++++"+this.para.exportExcelPath+contract.getQuotationsExcel());
		file = new File(this.para.exportExcelPath+contract.getQuotationsExcel());
		map = new HashMap<String,Object>();
		map.put("file",file);
		map.put("fileName","价格总表.xlsx");
		filelist.add(map);
		String fn = "";
		if(null == contract.getDownloadUrl() || "".equals(contract.getDownloadUrl())) {
			fn = System.currentTimeMillis()+".zip";
		} else {
			fn = contract.getDownloadUrl();
			if(!fn.contains(".zip")){
				fn+= ".zip";
			}
		}
		FilUtil2.zipMoreFile(filelist,end+fn);
		System.out.println("****************************packageEnd********************");
		contract.setDownloadUrl(fn);
		contract.setStatus(3);
		this.contractService.updateTbContract(contract);
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return icontractQuotationsService;
	}

	@Override
	public TbContractQuotations getModel() {
		if(this.model == null){
			this.model = new TbContractQuotations();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractQuotations model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
