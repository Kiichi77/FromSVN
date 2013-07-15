package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IPurchaseCompanyService;

public class PurchaseCompanyAction extends BaseAction<TbPurchaseCompany, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseCompanyAction.class);
    
	@Autowired(required = true)
	private IPurchaseCompanyService purchaseCompanyService;
	
	
	public IPurchaseCompanyService getPurchaseCompanyService() {
		return purchaseCompanyService;
	}

	public void setPurchaseCompanyService(
			IPurchaseCompanyService purchaseCompanyService) {
		this.purchaseCompanyService = purchaseCompanyService;
	}
  
	/**
	 * 
	* 方法用途和描述: 获取所有的公司
	* @return
	* @author lujia 新增日期：2013-3-7
	* @since ContractManage
	 */
	//TODO 没有做分页查询，日后补充 zhangjh
	public String queryAllPurchaseCompany() {
		try {
			PageInfo<TbPurchaseCompany> page = new PageInfo<TbPurchaseCompany>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.purchaseCompanyService.queryAllPurchaseCompany();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return purchaseCompanyService;
	}

	@Override
	public TbPurchaseCompany getModel() {
		if(this.model==null){
			 this.model = new TbPurchaseCompany();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPurchaseCompany model) {
		this.model=model;
		
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
