package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemporaryQuotationsDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporaryQuotations;
import cn.com.kxcomm.contractmanage.service.IContractTemporaryQuotationsService;

@Service("contractTemporaryQuotationsService")
public class ContractTemporaryQuotationsService  extends CommonService<TbContractTemporaryQuotations>
implements IContractTemporaryQuotationsService{

	@Autowired(required=true)
	private ContractTemporaryQuotationsDAO contractTemporaryQuotationsDAO;
	
	@Override
	public CommonDAO<TbContractTemporaryQuotations> getBindDao() {
		return contractTemporaryQuotationsDAO;
	}

	@Override
	public Integer getContractTemporaryByQuotationId(
			Integer quotationId) {
		String hql = "select count(*) from TbContractTemporaryQuotations where quotations.id = ?";
		Integer  rs = this.contractTemporaryQuotationsDAO.findTotalCount(hql, quotationId);
		return rs;
	}
}
