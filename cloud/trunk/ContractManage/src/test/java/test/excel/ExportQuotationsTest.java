package test.excel;

import java.util.List;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.web.util.ExportQuotations;

public class ExportQuotationsTest extends BaseTest {
	@Test
	public void aa() throws Exception {
		TbQuotations  model =quotationsDAO.findById(1);
		String id = "2,3,4";
		String[] idd = id.split(",");
		String fileName = model.getId()+".xlsx";
		List<List<DataRecordVo>> volist = configModelsService.listConfigModelByIds(idd);
		String exportDoc = "D:\\"+fileName;
		ExportQuotations ex = new ExportQuotations();
		String templatesDoc = "D:\\docx\\quotations_templates.xlsx";
		ex.export(templatesDoc, exportDoc, volist);

	}
}
