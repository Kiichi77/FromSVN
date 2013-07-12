package test.excel;

import org.testng.annotations.Test;

public class ExportContract {
	@Test
	public void aa(){
		cn.com.kxcomm.contractmanage.web.util.Parameters para = cn.com.kxcomm.contractmanage.web.util.Parameters.getInstance();
		para.exportExcelPath = "D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp2//wtpwebapps//ContractManage//download//quotations//";
		para.exportWordPath = "D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp2//wtpwebapps//ContractManage//download//quotations//";
		para.templatesExcelPath =  "D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp2//wtpwebapps//ContractManage//upload//quotations//";
		para.templatesWordPath = "D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp2//wtpwebapps//ContractManage//upload//ContractTemplates//";
		
		cn.com.kxcomm.common.util.InitExportContract initExportContract = cn.com.kxcomm.common.util.InitExportContract.getInstance();
		initExportContract.contractFileToPackage();
	}
}
