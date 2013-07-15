package test.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbProductType;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

public class Quotations extends BaseTest{
	private XSSFCell  cells = null;
	private XSSFSheet sheet = null;
	private int endNumCell = 17;
	private XSSFCellStyle categoryStyle = null;
	private XSSFCellStyle categoryBottomBorder = null;
	private XSSFCellStyle categoryRightBorder = null;
	private XSSFCellStyle dataStyle = null;
	private XSSFCellStyle percentStyle=null; 
	private HashMap<String, TbProductType> mapType = new HashMap<String, TbProductType>();
	private int all1=0;
	private int all2=0;
	private int all3=0;
	//当前最后一行ID
	private int rowid = 0;
	@Test
	public void create() throws Exception{
		String[] idd = new String[]{"2","9","14"};
		List<List<DataRecordVo>> volist = configModelsService.listConfigModelByIds(idd);
		export("D:\\docx\\quotations_templates.xlsx","D:\\docx\\exprot.xlsx",volist);
	}
	
	public void export(String templatesDoc,String exportDoc,List<List<DataRecordVo>> volist)throws FileNotFoundException, IOException, InterruptedException{
		XSSFWorkbook workbook = null;
		int sheetId = 0;
		workbook = new XSSFWorkbook(new FileInputStream(templatesDoc));
		for(List<DataRecordVo> configModelsList: volist){
			insert(workbook,sheetId,configModelsList);
			sheetId++;
		}
		// 导出execl文件
		File file = new File(exportDoc);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream fos = new FileOutputStream(exportDoc);
		workbook.write(fos);
		fos.flush();
		fos.close();
	}
	
	//入口
	public void insert(XSSFWorkbook workbook,int sheetId,List<DataRecordVo> configModelsList)throws FileNotFoundException, IOException{
		sheet = workbook.getSheetAt(sheetId);
		dataStyle = workbook.createCellStyle();  
		dataStyle.setWrapText(true);
		percentStyle = workbook.createCellStyle();  
		XSSFDataFormat fmt = workbook.createDataFormat();  
		percentStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);  
		percentStyle.setDataFormat(fmt.getFormat("0%"));  
		
		categoryStyle = getCategoryStyle();
		categoryBottomBorder = getCategoryBottomBorder();
		categoryRightBorder = getCategoryRightBorder();
		rowid = sheet.getPhysicalNumberOfRows();
		List<TbProductType> l2 = productTypeDAO.findAll();
		for(TbProductType tmp:l2){
			mapType.put(tmp.getName(), tmp);
		}
		
		addCategoryRow(rowid,"1.1 基本配置单元");
		all1 = addUnitRow(1,"1.1.","基本配置单元小计",configModelsList);
		rowid++;
		addCategoryRow(rowid,"1.2 全系列通用单元");
		all2 = addUnitRow(2,"1.2.","全系列通用单元小计",configModelsList);
		rowid++;
		addCategoryRow(rowid,"1.3 全通用单元");
		all3 = addUnitRow(3,"1.3.","全通用单元小计",configModelsList);
		addTotal();
		
	}
	
	/**
	 * 
	 * 分类栏样式
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-12-26
	 * @since ContractManage
	 */
	private XSSFCellStyle getCategoryStyle(){
		XSSFRow rows = sheet.getRow(5);
		cells = rows.getCell(0);
		System.out.println(cells.getStringCellValue());
		XSSFCellStyle style = cells.getCellStyle(); //分类样式
		return style;
	}
	

	
	/**
	 * 
	 * 分类栏底部样式
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-12-26
	 * @since ContractManage
	 */
	private XSSFCellStyle getCategoryBottomBorder(){
		XSSFRow rows = sheet.getRow(5);
		cells = rows.getCell(1);
		System.out.println(cells.getStringCellValue());
		XSSFCellStyle style = cells.getCellStyle(); //分类样式

		return style;
	}
	
	/**
	 * 
	 * 分类栏右边样式
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-12-26
	 * @since ContractManage
	 */
	private XSSFCellStyle getCategoryRightBorder(){
		XSSFRow rows = sheet.getRow(5);
		cells = rows.getCell(17);
		System.out.println(cells.getStringCellValue());
		XSSFCellStyle style = cells.getCellStyle(); //分类样式
		return style;
	}
	
	/**
	 * 
	 * 添加分类行
	 * 
	 * @param sheet
	 * @param rowid
	 * @param categoryName
	 * @author zhangjh 新增日期：2012-12-26
	 * @since ContractManage
	 */
	private void addCategoryRow(int rowid,String categoryName){
			XSSFRow rows = sheet.createRow(rowid);
			sheet.addMergedRegion(new CellRangeAddress(rowid, rowid, 0, endNumCell));//第二行 二到三列  
			cells = rows.createCell(0);
			cells.setCellStyle(categoryStyle);
			cells.setCellValue(new XSSFRichTextString(categoryName));
			for(int i =1;i<endNumCell;i++){
				cells = rows.createCell(i);
				cells.setCellStyle(categoryBottomBorder);
			}
			cells = rows.createCell(17);
			cells.setCellStyle(categoryRightBorder);
	 }
	
	//写入各个单元数据
	private int addUnitRow(int type,String no,String totalName,List<DataRecordVo> configModelsList){
		double Totalprice = 0.0;
		double Rebateprice = 0.0;
		double Rebateafter = 0.0;
		double InstallServiceCharge = 0.0;
		double FirstYear = 0.0;
		double SecondYear = 0.0;
		double ThirdYear = 0.0;
		double TotalYear = 0.0;
		double Total = 0.0;
		int iRow = 1;
		int start = rowid+2;
		int end = 0;
		for(DataRecordVo vo : configModelsList){
			String typeName = vo.getName();
			TbProductType p = mapType.get(typeName);
			if(p.getCategory().getId()!=type)
				continue;
			rowid++;
			setCellValue(vo,rowid,no+iRow);
			Totalprice+=vo.getTotalprice();
			Rebateprice+=vo.getRebateprice();
			Rebateafter+=vo.getRebateafter();
			InstallServiceCharge+=vo.getInstallServiceCharge();
			FirstYear+=vo.getFirstYear();
			SecondYear+=vo.getSecondYear();
			ThirdYear +=vo.getThirdYear();
			double to = vo.getFirstYear()+vo.getSecondYear()+vo.getThirdYear();
			TotalYear += to;
			Total += vo.getTotal();
			iRow++;
		}
		end = rowid+1;
		//写入小计总数
		rowid++;		
		sheet.addMergedRegion(new CellRangeAddress(rowid, rowid, 0, 3));
		XSSFRow rows = sheet.createRow(rowid);
		int allNo = rowid;
		if(iRow<=1){
			cells = rows.createCell(0);
			cells.setCellValue(totalName);
			cells = rows.createCell(6);
			cells.setCellValue(0);
			cells = rows.createCell(8);
			cells.setCellValue(0);
			cells = rows.createCell(10);
			cells.setCellValue(0);
			cells = rows.createCell(11);
			cells.setCellValue(0);
			cells = rows.createCell(12);
			cells.setCellValue(0);
			cells = rows.createCell(13);
			cells.setCellValue(0);
			cells = rows.createCell(14);
			cells.setCellValue(0);
			cells = rows.createCell(15);
			cells.setCellValue(0);
			cells = rows.createCell(16);
			cells.setCellValue(0);
		}else{
			cells = rows.createCell(0);
			cells.setCellValue(totalName);
			cells = rows.createCell(6);
			cells.setCellFormula("SUM(G"+start+":G"+end+")");
			cells = rows.createCell(8);
			cells.setCellFormula("SUM(I"+start+":I"+end+")");
			cells = rows.createCell(10);
			cells.setCellFormula("SUM(K"+start+":K"+end+")");
			cells = rows.createCell(11);
			cells.setCellFormula("SUM(L"+start+":L"+end+")");
			cells = rows.createCell(12);
			cells.setCellFormula("SUM(M"+start+":M"+end+")");
			cells = rows.createCell(13);
			cells.setCellFormula("SUM(N"+start+":N"+end+")");
			cells = rows.createCell(14);
			cells.setCellFormula("SUM(O"+start+":O"+end+")");
			cells = rows.createCell(15);
			cells.setCellFormula("SUM(P"+start+":P"+end+")");
			cells = rows.createCell(16);
			cells.setCellFormula("SUM(Q"+start+":Q"+end+")");
		}
		return allNo+1;
	}
	
	private void setCellValue(DataRecordVo vo,int index,String No){
			int curNo = index+1;
			XSSFRow rows = sheet.createRow(index);
			cells = rows.createCell(0);
			cells.setCellValue(No);
			cells = rows.createCell(1);
			cells.setCellValue(vo.getProductOrderNo());
			cells.setCellStyle(dataStyle);
			cells = rows.createCell(2);
			cells.setCellValue(vo.getName());
			cells = rows.createCell(3);
			cells.setCellValue(vo.getProductDesc());
			cells.setCellStyle(dataStyle);
			cells = rows.createCell(4);
			cells.setCellValue(vo.getQuantity());
			cells = rows.createCell(5);
			cells.setCellValue(vo.getUnitPrice());
			cells = rows.createCell(6);
			//cells.setCellValue(vo.getTotalprice());
			cells.setCellFormula("E"+curNo+"*F"+curNo);   
			cells = rows.createCell(7);
			cells.setCellValue(vo.getDiscountRate());
			cells.setCellStyle(percentStyle);
			cells = rows.createCell(8);
//			cells.setCellValue(vo.getRebateprice());
			cells.setCellFormula("G"+curNo+"*(1-H"+curNo+")");
			cells = rows.createCell(9);
			cells.setCellValue(vo.getOtherRates());
			cells.setCellStyle(percentStyle);
			cells = rows.createCell(10);
			//cells.setCellValue(vo.getRebateafter());
			cells.setCellFormula("I"+curNo+"*(1+J"+curNo+")");
			cells = rows.createCell(11);
			cells.setCellValue(vo.getInstallServiceCharge());
			cells = rows.createCell(12);
			cells.setCellFormula("E"+curNo);
			cells = rows.createCell(13);
			cells.setCellFormula("M"+curNo);
			cells = rows.createCell(14);
			cells.setCellFormula("N"+curNo);
			cells = rows.createCell(15);
			//cells.setCellValue((vo.getFirstYear()+vo.getSecondYear()+vo.getThirdYear()));
			cells.setCellFormula("SUM(M"+curNo+":O"+curNo+")");
			cells = rows.createCell(16);
			cells.setCellValue(vo.getTotal());
			cells = rows.createCell(17);
			cells.setCellValue(vo.getRemark());
		
	}
	
	private void addTotal() {
		// 典配总价
		rowid++;
		sheet.addMergedRegion(new CellRangeAddress(rowid, rowid, 0, 3));
		XSSFRow rows = sheet.createRow(rowid);
		for (int i = 1; i <= endNumCell; i++) {
			cells = rows.createCell(i);
			cells.setCellStyle(categoryBottomBorder);
		}
		cells = rows.createCell(0);
		cells.setCellStyle(categoryStyle);
		cells.setCellValue("典配总价");
		cells = rows.createCell(6);
		cells.setCellStyle(categoryStyle);
		cells.setCellFormula("G"+all1+"+G"+all2+"+G"+all3+"");
		cells = rows.createCell(8);
		cells.setCellStyle(categoryStyle);
		cells.setCellFormula("I"+all1+"+I"+all2+"+I"+all3+"");
		cells = rows.createCell(8);
		cells.setCellStyle(categoryStyle);
		cells.setCellFormula("K"+all1+"+K"+all2+"+K"+all3+"");
		cells = rows.createCell(16);
		cells.setCellStyle(categoryStyle);
		cells.setCellFormula("Q"+all1+"+Q"+all2+"+Q"+all3+"");
	}
}
