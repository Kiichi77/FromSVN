package test.excel;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.web.util.Parameters;


public class MergCell extends BaseTest{
	@Test
	public void aa(){
		Parameters para = Parameters.getInstance();
		para.exportExcelPath = "D:\\";
		TbContract contract = contractService.getByPk(3);
		contractQuotationsService.exportContractTotalPrice(contract);
	}
	
}