package cn.com.kxcomm.contractmanage.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractDocVariableService;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;

public class ContractDocVariableAction extends BaseAction<TbContractDocVariable, String>{
	private static Logger LOGGER = Logger.getLogger(ContractDocVariableAction.class);
	
	  private IContractDocVariableService contractDocVariableService;
	  
	  private String contractDocId;   //合同文件Id

	public IContractDocVariableService getContractDocVariableService() {
		return contractDocVariableService;
	}

	public void setContractDocVariableService(
			IContractDocVariableService contractDocVariableService) {
		this.contractDocVariableService = contractDocVariableService;
	}

	public String getContractDocId() {
		return contractDocId;
	}

	public void setContractDocId(String contractDocId) {
		this.contractDocId = contractDocId;
	}
	  
	/**
	 * 
	* 方法用途和描述: 获取合同文件下的变量
	* @return
	* @author lujia 新增日期：2012-12-17
	* @since ContractManage
	 */
	 public String listContractDocVariable(){
			try {
				if(!BlankUtil.isBlank(contractDocId)){
					int id = Integer.parseInt(contractDocId);
					List<ContractDocVariableVo> volist = contractDocVariableService.queryAllContractDocVariable(id,false);
					PageInfo<ContractDocVariableVo> pageInfo = new PageInfo<ContractDocVariableVo>();
					pageInfo.setRows(volist);
					pageInfo.setMsg("ok");
					result = JSONObject.fromObject(pageInfo);
				}
			} catch (Exception e) {
				log.error("listContractDocVariable error.",e);
			}
			return SUCCESS;
	   }

	@Override
	public ICommonService getCommonService() {
			return contractDocVariableService;
	}

	@Override
	public TbContractDocVariable getModel() {
		if (this.model == null) {
			this.model = new TbContractDocVariable();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractDocVariable model) {
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
