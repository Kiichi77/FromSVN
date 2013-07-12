package test.excel;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.entity.TbProductCategory;
import cn.com.kxcomm.contractmanage.entity.TbProductType;

public class ExcelTest extends BaseTest{
	//@Test
	public void updateCategoryAndType() throws Exception{
		XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(new FileInputStream("D:\\docx\\data.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0); //单元表
		XSSFRow row = null;    //行
		String cell = null;  //列
		String category = null;
		if(BlankUtil.isBlank(workbook)){
			return ;
		}
		HashMap mapType = new HashMap();
		HashMap mapCategory = new HashMap();
		for(int i= sheet.getFirstRowNum();i<sheet.getPhysicalNumberOfRows();i++){
			row = sheet.getRow(i);
			if(row.getCell(1)==null||"".equals(row.getCell(1).toString().trim())){
				category = row.getCell(0).toString().trim();
				if("".equals(category.trim()))
						continue;
				
				mapCategory.put(category,null);
			}
			cell = row.getCell(1).toString().trim();
			if("".equals(cell.trim()))
				continue;
			mapType.put(cell, category);
		}
	
		//ProductCategory
		List<TbProductCategory> l = productCategoryDAO.findAll();
		HashMap cMap = new HashMap();
		for(TbProductCategory tmp:l){
			cMap.put(tmp.getName(), tmp);
		}
		Object[] a = mapCategory.keySet().toArray();
		for(int i=0;i<a.length;i++){
			String cName = a[i].toString().trim();
			if(cMap.get(cName)==null){
				//System.out.println("新的分类:"+cName+"\t");
				TbProductCategory ca = new TbProductCategory();
				ca.setName(cName);
				productCategoryDAO.save(ca);
			}
		}
		
		//ProductType
		l = productCategoryDAO.findAll();
		cMap = new HashMap();
		for(TbProductCategory tmp:l){
			System.out.println(tmp.getName());
			cMap.put(tmp.getName(), tmp);
		}
		List<TbProductType> l2 = productTypeDAO.findAll();
		HashMap tMap = new HashMap();
		for(TbProductType tmp:l2){
			tMap.put(tmp.getName(), tmp.getId());
		}
		Object[] b = mapType.keySet().toArray();
		for(int i=0;i<b.length;i++)
		{
			String typeName = b[i].toString().trim();
		   if(tMap.get(typeName)==null){
			 //  System.out.println("类型："+typeName+",归类："+mapType.get(typeName));
			   TbProductType type = new TbProductType();
			   TbProductCategory apb = (TbProductCategory) cMap.get(mapType.get(typeName));
			  // System.out.println(apb.getName()+apb.getId());
			   type.setCategory(apb);
			   type.setName(typeName);
			   productTypeDAO.save(type);
		   }
		}
	}
//	@Test
	public void uploadExcel() throws Exception{
		updateCategoryAndType();
		HashMap mapType = new HashMap();
		List<TbProductType> l2 = productTypeDAO.findAll();
		for(TbProductType tmp:l2){
			mapType.put(tmp.getName(), tmp);
		}
		List<TbDataRecord> recordlist = new ArrayList<TbDataRecord>();
		TbDataSource dataSource = dataSourceDAO.findById(2);
		XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(new FileInputStream("D:\\docx\\abc.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0); //单元表
		XSSFRow row = null;    //行
		String cell = null;  //列
		if(BlankUtil.isBlank(workbook)){
			return ;
		}
		int result = 0;
		for(int i= sheet.getFirstRowNum();i<sheet.getPhysicalNumberOfRows();i++){
			row = sheet.getRow(i);
			//如果是分类则跳过
			if(row.getCell(1)==null||"".equals(row.getCell(1).toString().trim())){
				continue;
			}
			TbDataRecord dataRecord = new TbDataRecord();
			dataRecord.setProductOrderNo(row.getCell(0).getStringCellValue());  // 产品订货号
			System.out.println(row.getCell(1).getStringCellValue().trim());
			TbProductType productType = (TbProductType) mapType.get(row.getCell(1).getStringCellValue().trim());
			dataRecord.setProductType(productType);
			dataRecord.setDataSource(dataSource);
			dataRecord.setProductDesc(row.getCell(2).getStringCellValue());// 产品描述
			dataRecord.setQuantity(row.getCell(3).getNumericCellValue());// 数量
			dataRecord.setUnitPrice(row.getCell(4).getNumericCellValue());// 目录单价
			dataRecord.setDiscountRate(row.getCell(6).getNumericCellValue());// 折扣率
			dataRecord.setOtherRates(row.getCell(8).getNumericCellValue());// 运保及其他费率
			dataRecord.setInstallServiceCharge(row.getCell(10).getNumericCellValue());// 安装服务费
			//保修费用等于数量的值
			dataRecord.setFirstYear(row.getCell(12).getNumericCellValue());// 第一年保修费用
			dataRecord.setSecondYear(row.getCell(13).getNumericCellValue());// 第二年保修费用
			dataRecord.setThirdYear(row.getCell(14).getNumericCellValue());// 第三年保修费用
			dataRecord.setRemark(row.getCell(16).getStringCellValue()); //备注
			recordlist.add(dataRecord);
//			for(int j=row.getFirstCellNum();j<row.getPhysicalNumberOfCells();j++){
//				if(row.getCell(j)==null||"".equals(row.getCell(j).toString().trim()))
//					continue;
//				cell = row.getCell(j).toString().trim();
//				System.out.println("Cell("+j+"):"+cell+"\t");
//			}
		}
		result+=batchSaveDataSource(recordlist);
	}
	
	/**
	 * 
	* 方法用途和描述: 批量保存
	* @author chenliang 新增日期：2012-12-12
	 * @throws SQLException 
	* @since ContractManage
	 */
	private int batchSaveDataSource(List<TbDataRecord> dataRecordList) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into tb_data_record(product_type_id,data_source_id,productOrderNo,productDesc,quantity," +
				"unitPrice,discountRate,otherRates,InstallServiceCharge,first_year,second_year,third_year,remark" +
				") values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return dataRecordDAO.batchSave(dataRecordList, sql.toString());
	}
}