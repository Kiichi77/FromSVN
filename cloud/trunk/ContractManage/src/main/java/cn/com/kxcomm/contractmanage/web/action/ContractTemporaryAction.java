package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractTemporaryService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemporaryVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;
import cn.com.kxcomm.contractmanage.web.util.FtpSync;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class ContractTemporaryAction  extends BaseAction<TbContractTemporary, String>{
	private static final long serialVersionUID = -7301825710053397310L;
	private static Logger LOGGER = Logger.getLogger(PurchaseOrderContractFileAction.class);
	@Autowired(required=true)
	private IContractTemporaryService contractTemporaryService;
	private ContractTemporaryVo temporaryVo;
	private File contractFileSource;//上传文件
	private String contractFileSourceFileName; //文件名称   根据上传文件（purchaseContract）后面加上FileName命名
	private String id;
	 private CheckedVo checkedVo; //审核vo
	public ContractTemporaryVo getTemporaryVo() {
		return temporaryVo;
	}

	public String getId() {
		return id;
	}

	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTemporaryVo(ContractTemporaryVo temporaryVo) {
		this.temporaryVo = temporaryVo;
	}
	@Override
	public ICommonService getCommonService() {
		return contractTemporaryService;
	}
	
	public IContractTemporaryService getContractTemporaryService() {
		return contractTemporaryService;
	}

	public void setContractTemporaryService(
			IContractTemporaryService contractTemporaryService) {
		this.contractTemporaryService = contractTemporaryService;
	}

	public File getContractFileSource() {
		return contractFileSource;
	}

	public void setContractFileSource(File contractFileSource) {
		this.contractFileSource = contractFileSource;
	}

	public String getContractFileSourceFileName() {
		return contractFileSourceFileName;
	}

	public void setContractFileSourceFileName(String contractFileSourceFileName) {
		this.contractFileSourceFileName = contractFileSourceFileName;
	}

	public String saveContractTemporary(){
		if(temporaryVo==null ||null==contractFileSource){
			result = JSONObject.fromObject(successInfo("error"));
			return SUCCESS;
		}
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		cn.com.kxcomm.contractmanage.web.util.Parameters para = cn.com.kxcomm.contractmanage.web.util.Parameters.getInstance();
		String FileName = System.currentTimeMillis()+contractFileSourceFileName.substring(contractFileSourceFileName.lastIndexOf("."), contractFileSourceFileName.length());//上传到服务器的文件名
		FileUtil.bakTemplatesDoc(contractFileSource, para.contractTemporaryPath, FileName, false,0);
		FtpSync.ftpUploadSync(para.contractTemporaryPath, "contractTemporaryPath", FileName);
		temporaryVo.setFileForPDF(FileName) ;
		this.contractTemporaryService.addContractTemporary(temporaryVo,userId);
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	public String listAll(){
		try {
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			if (BlankUtil.isBlank(temporaryVo)) {
				temporaryVo = new ContractTemporaryVo();
			}
			PageInfo<ContractTemporaryVo> page = new PageInfo<ContractTemporaryVo>(ServletActionContext.getRequest());
			PageInfo<ContractTemporaryVo> rs = contractTemporaryService.queryAll(page, userId, temporaryVo);
			result = JSONObject.fromObject(rs);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
	
	public String checkListAll(){
		try {
			if (BlankUtil.isBlank(temporaryVo)) {
				temporaryVo = new ContractTemporaryVo();
			}
			PageInfo<ContractTemporaryVo> page = new PageInfo<ContractTemporaryVo>(ServletActionContext.getRequest());
			PageInfo<ContractTemporaryVo> rs = contractTemporaryService.queryAll(page, null, temporaryVo);
			result = JSONObject.fromObject(rs);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
	
	
	public String queryAllConfigModels(){
		if(!BlankUtil.isBlank(id)){
			 List<ContractQuotationsVo>  rs = contractTemporaryService.queryAllConfigModels(id);
			 PageInfo<ContractQuotationsVo> pageInfo = new PageInfo<ContractQuotationsVo>();
			pageInfo.setRows(rs);
			pageInfo.setTotal(rs.size());
			result = JSONObject.fromObject(pageInfo);
		}
		return SUCCESS;
	}
	
	public String getContractTemporaryById(){
		if(!BlankUtil.isBlank(id)){
			Integer contractId = Integer.parseInt(id);
			temporaryVo = contractTemporaryService.getById(contractId);
			result = JSONObject.fromObject(temporaryVo);
		}
		return SUCCESS;
	}

	public String editContractTemporary(){
		if(!BlankUtil.isBlank(temporaryVo)){
			try{
				contractTemporaryService.editContractTemporary(temporaryVo);
				result = JSONObject.fromObject(successInfo("ok"));
			}catch(Exception e){
				result = JSONObject.fromObject(successInfo("error"));
				e.printStackTrace();
				
			}
		}
		return SUCCESS;
	}
	
	public String saveTemporaryDataToFormal(){
		if(!BlankUtil.isBlank(temporaryVo)){
			try{
				HttpSession session = getHttpSession();
				Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
				contractTemporaryService.saveTemporaryDataToFormal(temporaryVo,userId);
				result = JSONObject.fromObject(successInfo("ok"));
			}catch(Exception e){
				result = JSONObject.fromObject(successInfo("error"));
				e.printStackTrace();
				
			}
		}
		return SUCCESS;
	}
	
	public String submitCheck(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			contractTemporaryService.submitCheck(checkedVo);
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("checkRapaport error.",e);
		}
		return SUCCESS;
	}
	
	@Override
	public TbContractTemporary getModel() {
		if (this.model == null) {
			this.model = new TbContractTemporary();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractTemporary model) {
		this.model=model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids=ids;
	}

}
