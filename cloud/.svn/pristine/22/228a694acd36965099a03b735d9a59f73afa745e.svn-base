package test.dao;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbProject;

public class ContractDAOTest extends BaseTest{
	
	public void findById(){
		TbContract tbcontract = contractDAO.findById(2);
		System.out.println(tbcontract.toString());
		TbProject p = tbcontract.getProject();
		System.out.println(p.toString());
	}
	
	
	public void querySaleOrder(){
		Integer contractOrderId =1;
		contractOrderDAO.querySaleOrder(contractOrderId,5);
	}
	
	public void contractOrdered(){
		Long kk = contractOrderDAO.contractOrdered(34);
		System.out.println(kk);
	}
	@Test
	public void queryContractOrderRelationshipData(){
		contractOrderRelationshipDataDAO.queryContractOrderRelationshipData(34);
	}
}
