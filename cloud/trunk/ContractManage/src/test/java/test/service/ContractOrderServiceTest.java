package test.service;

import java.util.List;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;

public class ContractOrderServiceTest extends BaseTest{
	//@Test
	public void findByPage(){
		PageInfo<TbContractOrder> p = new PageInfo<TbContractOrder>();
		p.setCurrentPage(0);
		p.setPageSize(20);
		
		TbContractOrder e =new TbContractOrder();
		PageInfo<TbContractOrder> ll = contractOrderService.findByPage(e, p);
		System.out.println(ll.getTotal());
		List<TbContractOrder> row = ll.getRows();
		for(TbContractOrder t : row){
			System.out.println(t.toString());
		}
	}
	
	
	public void querySaleOrder(){
		Integer contractOrderId = 1;
		Integer suppliersId = 1;
		List<ContractQuotationsVo>  ll = this.contractOrderService.querySaleOrder(contractOrderId, suppliersId);
		System.out.println(ll.size());
		for(ContractQuotationsVo vo : ll){
			System.out.println(vo.toString());
		}
	}
	
	@Test
	public void queryContractOrderRelationshipData(){
		Integer contractOrderRelationshipId = 1;
		List<CurrentUnitVo>  ll = this.contractOrderRelationshipDataService.queryContractOrderRelationshipData(contractOrderRelationshipId);
		System.out.println(ll.size());
		for(CurrentUnitVo vo : ll){
			System.out.println(vo.toString());
		}
	}
	
	public void queryAllConfigModels(){
		Integer conid = 1;
		this.contractQuotationsService.queryAllConfigModels(conid);
	}
}
