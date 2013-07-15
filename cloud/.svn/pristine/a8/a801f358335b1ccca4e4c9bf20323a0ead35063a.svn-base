package cn.com.kxcomm.common.util;

import java.io.File;
import java.util.List;

import cn.com.kxcomm.contractmanage.InitBean;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.web.util.ExportQuotations;
import cn.com.kxcomm.contractmanage.web.util.Parameters;

public class InitExportQuotations {
	private static InitExportQuotations instance;
	private InitBean initBean = InitBean.getInstance();
	private Parameters para = Parameters.getInstance();
	private InitExportQuotations(){
		
	}
	
	public static InitExportQuotations getInstance(){
		if(instance == null)
			instance = new InitExportQuotations();
		return instance;
	}
	
	public void createExcel() {
		try{
		List<TbQuotations> list = initBean.quotationsDAO.findAll();
		for(TbQuotations t :list){
			List<TbQuotationsData>  dataList = initBean.quotationsDataDAO.queryDataById(t.getId());
			String[] ids = new String[dataList.size()];
			int i = 0;
			for(TbQuotationsData d:dataList){
				if(i>=10)
					break;
				ids[i] = d.getConfigModels().getId().toString();
				i++;
			}
			List<List<DataRecordVo>> volist = initBean.configModelsService.listConfigModelByIds(ids);
			String fileName = t.getDownUrl();
			String exportDoc = para.exportExcelPath+fileName;
			String templatesDoc = para.templatesExcelPath+"quotations_templates.xlsx";
			ExportQuotations exportQuotations = new ExportQuotations();
			exportQuotations.export(templatesDoc, exportDoc, volist);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
