package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbArrivalProved;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.IArrivalProvedService;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.vo.ArrivalProvedVo;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class ArrivalProvedAction extends BaseAction<TbArrivalProved, String> {
	private static Logger LOGGER = Logger.getLogger(ArrivalProvedAction.class);
	@Autowired(required = true)
	private IArrivalProvedService arrivalProvedService;
	@Autowired(required = true)
	private IactivitiService activitiService; // 流程控制服务
	@Autowired(required = true)
	private IProcessUserService processUserService;

	private File proved;
	private String provedFileName;
	private Parameters para = Parameters.getInstance();
	private String id;
	private String fileType; // 到货（1）、初验（2）、终验（3）
	private String contractId; // 合同ID
	private String customerId; // 客户id

	private CheckedVo checkedVo; // 审核vo

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public IArrivalProvedService getArrivalProvedService() {
		return arrivalProvedService;
	}

	public void setArrivalProvedService(
			IArrivalProvedService arrivalProvedService) {
		this.arrivalProvedService = arrivalProvedService;
	}

	public File getProved() {
		return proved;
	}

	public void setProved(File proved) {
		this.proved = proved;
	}

	public String getProvedFileName() {
		return provedFileName;
	}

	public void setProvedFileName(String provedFileName) {
		this.provedFileName = provedFileName;
	}

	/**
	 * 
	 * 方法用途和描述: 获取所有的合同
	 * 
	 * @return
	 * @author lujia 新增日期：2013-2-25
	 * @since ContractManage
	 */
	public String queryAllArrivalProved() {
		try {
			PageInfo<ArrivalProvedVo> page = new PageInfo<ArrivalProvedVo>(
					ServletActionContext.getRequest());
			LOGGER.info("queryAllArrivalProved id:"+id);
			PageInfo<ArrivalProvedVo> PurchaseOrderList=this.arrivalProvedService.queryAllArrivalProved(page,contractId);
			 result = JSONObject.fromObject(PurchaseOrderList);
		} catch (Exception e) {
			LOGGER.error("queryAllCustomer ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 上传到货、初验、终验文件
	 * 
	 * @return
	 * @author lujia 新增日期：2013-3-19
	 * @since ContractManage
	 */
	public String uploadProved()
	{
		try {
			if(null!=proved){
				//上传文件
				String bakPath = para.purchaseWordPath; //文件上传到服务器的地址
				LOGGER.info("bakPath:"+bakPath);
				String FileName = System.currentTimeMillis()+provedFileName.substring(provedFileName.lastIndexOf("."), provedFileName.length());//上传到服务器的文件名
				FileUtil.bakTemplatesDoc(proved, bakPath, FileName, true,0);
				int provedId=Integer.parseInt(id);
				int type=Integer.parseInt(fileType);
				 arrivalProvedService.uploadProved(provedId, contractId, type, FileName);
				
				HttpSession session = getHttpSession();
				Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
				//到货是售后流程的最开始活动，该活动是当销售订单全部采购完成后在开启。
				//type=2和3的时候也是调用同一个方法，所以后面分配任务是共用的。
//				if(type==1){ //type=1到货，type=2初验，type=3终验
//					//处理到货活动的时候，把合同信息和客户信息更新到tbProcessUser表中
//					if(!BlankUtil.isBlank(checkedVo.getProcess_id())){ 
//						// 把流程ID与用户ID关联起来
//						TbProcessUser tbProcessUser=processUserService.findByField("processId",checkedVo.getProcess_id());					
//						tbProcessUser.setCustomerId(customerId!=null?Long.parseLong(customerId):0l);
//						tbProcessUser.setContractId(contractId!=null?Long.parseLong(contractId):0l);
//						processUserService.update(tbProcessUser);
//					}
//				}
//				//3、销售申请发票，分配任务
//				//3.1、完成上传到货证明的任务
//				activitiService.finishTheTask(checkedVo.getProcess_id(), ""+useridsession, null);
//				//3.2、分配销售申请发票的任务,分配下一个任务
//				activitiService.taskClaim(checkedVo.getProcess_id(), checkedVo.getUser_id(), null);
				result = JSONObject.fromObject(successInfo("ok"));
			}
		}catch(Exception e)
		{
			LOGGER.error("uploadProved error.",e);
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return arrivalProvedService;
	}

	@Override
	public TbArrivalProved getModel() {
		if (this.model == null) {
			this.model = new TbArrivalProved();
		}
		return this.model;
	}

	@Override
	public void setModel(TbArrivalProved model) {
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
