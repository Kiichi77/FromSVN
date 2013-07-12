package test.dao;

import java.util.Date;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;

public class ContractOrderDAOTest extends BaseTest{
	
	public void save(){
		TbContractOrder entity = new TbContractOrder();
		TbUser caudit = userDAO.findById(1L);
		entity.setCaudit(caudit);
		TbContract contract = contractDAO.findById(41);
		entity.setContract(contract);
		entity.setCreatetime(new Date());
		entity.setCreator(caudit);
		entity.setStatus(0);
		entity.setPurchase(caudit);
		contractOrderDAO.save(entity);
	}
	
	
}
