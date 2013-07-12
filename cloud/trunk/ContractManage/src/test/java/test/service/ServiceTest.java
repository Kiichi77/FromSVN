package test.service;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.InitExportQuotations;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
import cn.com.kxcomm.contractmanage.vo.ContractReceivePaymentVo;
import cn.com.kxcomm.contractmanage.web.util.Parameters;

public class ServiceTest extends BaseTest{
	//@Test
	public void saveOrUpdate(){
		Integer cotractId = 31;
		ContractReceivePaymentVo vo = icontractReceivePaymentService.getcontractReceivePayment(cotractId);
		System.out.println(vo.toString());
		TbContractReceivePayment  entity = new TbContractReceivePayment();
		EntityToVoUtil.copyObjValue(vo,entity);
		TbContract contract = contractService.getByPk(cotractId);
		entity.setContract(contract);
		Integer pId = Integer.parseInt(vo.getPurchaseCompanyId());
//		TbPurchaseCompany purchaseCompany =this.purchaseCompanyService.getByPk(pId);
//		entity.setPurchaseCompany(purchaseCompany);
		System.out.println(entity.toString());
		icontractReceivePaymentService.saveOrUpdate(entity);
	}
//	@Test
	public void select(){
		TbPurchaseOrder o = purchaseOrderService.getByPk(1);
		if(o==null){
			System.out.println(" null");
		}else{
			System.out.println("not null");
			System.out.println(o.toString());
		}
	}
//	@Test
	public void creat(){
		Parameters para = Parameters.getInstance();
		para.exportExcelPath = "D://Source//workspace//repos//ContractManage//src//main//webapp//download//quotations//";
		para.templatesExcelPath = "D://Source//workspace//repos//ContractManage//src//main//webapp//upload//quotations//";
		InitExportQuotations initExportQuotations = InitExportQuotations.getInstance();
		initExportQuotations.createExcel();
	}
	
	@Test
	public void export() throws Exception{
		String processId = "6625";
		PageInfo pageInfo =new PageInfo(); 
	}
}
