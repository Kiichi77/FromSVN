package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseCompanyDAO;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.service.IPurchaseCompanyService;
@Service("purchaseCompanyService")
public class PurchaseCompanyService extends CommonService<TbPurchaseCompany> implements IPurchaseCompanyService{
	private static final Logger LOG = Logger.getLogger(PurchaseCompanyService.class);
	@Autowired(required=true)
	private PurchaseCompanyDAO purchaseCompanyDAO;
	
	@Override
	public CommonDAO<TbPurchaseCompany> getBindDao() {
		return purchaseCompanyDAO;
	}
	
	/**
	* 
	* 方法用途和描述: 查询所有的客户
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
	@Override
	public List<TbPurchaseCompany> queryAllPurchaseCompany() {
		String hql=" from TbPurchaseCompany pc ";
		List list = this.purchaseCompanyDAO.find(hql.toString());
		return list;
	}
}
