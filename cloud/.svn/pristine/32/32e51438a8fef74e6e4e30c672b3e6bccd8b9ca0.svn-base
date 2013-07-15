package cn.com.kxcomm.contractmanage.web.action;


import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractDocService;
import cn.com.kxcomm.contractmanage.service.IContractDocVariableService;
import cn.com.kxcomm.contractmanage.vo.ContractDocVo;

/**
 * 
* 功能描述:合同与合同选中文件的关系类
* @author chenliang 新增日期：2012-12-28
* @since ContractManage
 */
public class ContractDocAction extends BaseAction<TbContractDoc, String>{
	private static Logger LOGGER = Logger.getLogger(ContractDocAction.class);

	@Autowired(required=true)
	private IContractDocService contractDocService;
	@Autowired(required=true)
	private IContractDocVariableService contractDocVariableService;
	
	public IContractDocVariableService getContractDocVariableService() {
		return contractDocVariableService;
	}

	public void setContractDocVariableService(
			IContractDocVariableService contractDocVariableService) {
		this.contractDocVariableService = contractDocVariableService;
	}
	
	private String conid;  //合同id
	private String contractId;  //合同选中的文件id的json数组
	
	public IContractDocService getContractDocService() {
		return contractDocService;
	}

	public void setContractDocService(IContractDocService contractDocService) {
		this.contractDocService = contractDocService;
	}

	public String getConid() {
		return conid;
	}

	public void setConid(String conid) {
		this.conid = conid;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * 
	* 方法用途和描述: 保存合同与合同选中的文件
	* @return
	* @author chenliang 新增日期：2012-12-28
	* @since ContractManage
	 */
	public String saveContractDoc(){
		try {
			if(!BlankUtil.isBlank(conid) && !BlankUtil.isBlank(contractId)){
				//获取文件生成目录
				String path = getHttpRequest().getSession().getServletContext().getRealPath("/download/ContractTemplates");
				int cId = Integer.parseInt(conid);
				//1、根据合同id先删除合同选中文件表中的数据
				contractDocService.deleteByContractId(cId);
				int cDocId;
				JSONArray array = JSONArray.fromObject(contractId);
				List<ContractDocVo> jsonListVo = JSONArray.toList(array,ContractDocVo.class);
				TbContractDoc contractDoc;
				String docVoPath;
				for (int i = 0; i < jsonListVo.size(); i++) {
					ContractDocVo contractDocVo = jsonListVo.get(i);
					contractDoc = new TbContractDoc();
					cDocId = Integer.parseInt(contractDocVo.getId());
					TbContract contract = new TbContract();
					contract.setId(cId);
					contractDoc.setContract(contract);
					contractDoc.setCreatetime(new Date());
					TbContractTemplatesDoc tempDoc = new TbContractTemplatesDoc();
					tempDoc.setId(cDocId);
					contractDoc.setDoc(tempDoc);
					docVoPath = contractDocVo.getPath();
					LOGGER.info("docVoPath:"+docVoPath);
					if(!BlankUtil.isBlank(docVoPath)){
						contractDoc.setExportPath(docVoPath);
					}else{
						contractDoc.setExportPath(path);
					}
					if(!BlankUtil.isBlank(contractDocVo.getFileName())){
						contractDoc.setFileName(contractDocVo.getFileName());
					}else{
						if(!BlankUtil.isBlank(docVoPath)){
							contractDoc.setFileName("文件"+i+"_"+System.currentTimeMillis()+docVoPath.substring(docVoPath.lastIndexOf("."), docVoPath.length()));
						}else{
							LOGGER.info("文件名称为空！");
						}
					}
					//2、保存合同选中的文件
					contractDocService.save(contractDoc);
					//3.保存合同文件变量设置
					LOGGER.info("contractDoc.getId:"+contractDoc.getId());
					contractDocVariableService.saveContractDocVariable(contractDoc.getId());
				}
				result = JSONObject.fromObject(successInfo("ok"));
			}else {
				result = JSONObject.fromObject(successInfo("no"));
			}
		} catch (Exception e) {
			LOGGER.info("saveContractDoc error.",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改合同选中文件的文件名
	* @return
	* @author chenliang 新增日期：2013-1-5
	* @since ContractManage
	 */
	public String updateContractDoc(){
		try {
			LOGGER.info("exportPath:"+model.getExportPath());
			if(!BlankUtil.isBlank(model.getFileName())){
				if(!model.getFileName().endsWith(".docx") && !model.getFileName().endsWith(".jpg")){
					String exportpath = model.getExportPath();
					exportpath = exportpath.substring(exportpath.indexOf("."), exportpath.length());
					model.setFileName(model.getFileName()+exportpath);
				}
			}
			boolean flat = contractDocService.updateContractField(model);
			if(flat){
				result = JSONObject.fromObject(successInfo("ok"));
			}else{
				result = JSONObject.fromObject(successInfo("no"));
			}
		} catch (Exception e) {
			LOGGER.info("updateContractDoc error.",e);
		}
		return SUCCESS;
	}

	@Override
	public ICommonService getCommonService() {
		return contractDocService;
	}

	@Override
	public TbContractDoc getModel() {
		if(this.model == null){
			this.model = new TbContractDoc();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractDoc model) {
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
