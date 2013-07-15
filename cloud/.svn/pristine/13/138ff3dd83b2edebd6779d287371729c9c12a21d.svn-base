package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractOrderRelationshipDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationshipData;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipDataService;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;

@Service("contractOrderRelationshipDataService")
public class ContractOrderRelationshipDataService extends
CommonService<TbContractOrderRelationshipData> implements IContractOrderRelationshipDataService{
	@Autowired(required=true)
	private ContractOrderRelationshipDataDAO contractOrderRelationshipDataDAO;

	@Override
	public CommonDAO<TbContractOrderRelationshipData> getBindDao() {
		return contractOrderRelationshipDataDAO;
	}

	@Override
	public List<CurrentUnitVo> queryContractOrderRelationshipData(
			Integer contractOrderRelationshipId) {
		return contractOrderRelationshipDataDAO.queryContractOrderRelationshipData(contractOrderRelationshipId);
	}

}
