package cn.com.kxcomm.contractmanage.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.exception.SystemException;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FormulaUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.DataRecordDAO;
import cn.com.kxcomm.contractmanage.dao.ProductCategoryDAO;
import cn.com.kxcomm.contractmanage.dao.ProductTypeDAO;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.entity.TbProductCategory;
import cn.com.kxcomm.contractmanage.entity.TbProductType;
import cn.com.kxcomm.contractmanage.service.IDataRecordService;
import cn.com.kxcomm.contractmanage.vo.DataBean;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

@Service("dataRecordService")
public class DataRecordService extends
CommonService<TbDataRecord> implements IDataRecordService {
	
	private static final Logger log = Logger.getLogger(DataRecordService.class);
	
	@Autowired(required=true)
	private DataRecordDAO dataRecordDAO;
	@Autowired(required=true)
	private ProductCategoryDAO productCategoryDAO;
	@Autowired(required=true)
	private ProductTypeDAO productTypeDAO;
	
	@Override
	public CommonDAO<TbDataRecord> getBindDao() {
		return dataRecordDAO;
	}

	@Override
	public List<DataBean> listDetailDataRecord(int dataSourceId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select t.data_source_id,ty.id,ty.name,ty.category_id ");
		hql.append(" from tb_data_record t,tb_product_type ty ");
		hql.append(" where t.data_source_id=? ");
		hql.append(" and t.product_type_id=ty.id ");
		hql.append(" group by ty.id ");
		List lists = dataRecordDAO.findByPage(hql.toString(), dataSourceId);
		List<DataBean> dataBeans = new ArrayList<DataBean>();
		Object[] obj = new Object[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			DataBean bean = new DataBean();
			bean.setId(obj[0].toString());
			bean.setTypeId(obj[1].toString());
			bean.setName(obj[2].toString());
			bean.setParam(obj[3].toString());
			bean.setUrl("dataSource/dataSource/dataRecord!getDetail.action?typeId="+bean.getTypeId()+"&dataSourceId="+dataSourceId);
			dataBeans.add(bean);
		}
		return dataBeans;
	}

	@Override
	public PageInfo<DataRecordVo> getDetail(int typeId,int sourceId,PageInfo<DataRecordVo> pageinfo) {
		String hql = " from TbDataRecord where productType.id=? and dataSource.id=? ";
		String countHql = "select count(*) from TbDataRecord where productType.id=? and dataSource.id=? ";
		int count = dataRecordDAO.findTotalCount(countHql, typeId,sourceId);
		List<TbDataRecord> dataRecordList = dataRecordDAO.findPageInfo(pageinfo,hql, typeId,sourceId);
		List<DataRecordVo> volist = new ArrayList<DataRecordVo>();
		for (TbDataRecord tbDataRecord : dataRecordList) {
			DataRecordVo vo=new DataRecordVo();
			vo.setId(""+tbDataRecord.getId());
			vo.setProductOrderNo(tbDataRecord.getProductOrderNo());
			vo.setName(tbDataRecord.getProductType().getName());
			vo.setProductDesc(tbDataRecord.getProductDesc());
			vo.setQuantity(tbDataRecord.getQuantity());
			vo.setUnitPrice(tbDataRecord.getUnitPrice());
			vo.setDiscountRate(tbDataRecord.getDiscountRate());
			vo.setOtherRates(tbDataRecord.getOtherRates());
			vo.setInstallServiceCharge(tbDataRecord.getInstallServiceCharge());
			vo.setFirstYear(tbDataRecord.getFirstYear());
			vo.setSecondYear(tbDataRecord.getSecondYear());
			vo.setThirdYear(tbDataRecord.getThirdYear());
			vo.setTotalprice(FormulaUtil.planTotalPrice(vo));
			vo.setRebateprice(FormulaUtil.planRabateprice(vo));
			vo.setRebateafter(FormulaUtil.planRateafter(vo));
			vo.setTotal(FormulaUtil.planTotal(vo));
			vo.setTypeId(""+typeId);
			volist.add(vo);
		}
		PageInfo<DataRecordVo> page = new PageInfo<DataRecordVo>(pageinfo.getCurrentPage(), count, pageinfo.getPageSize(), volist);
		return page;
	}
	
	@Override
	/**
	 * 
	 * 方法用途和描述: 导入excel元数据
	 * 
	 * @param workbook
	 * @param dataSource
	 * @return
	 * @throws Exception
	 * @author zhangjh 新增日期：2012-12-21
	 * @since ContractManage
	 */
	public int uploadDataSource(XSSFWorkbook workbook,TbDataSource dataSource) throws SystemException {
		int result = 0;
		try {
			if (!updateCategoryAndType(workbook))
				return 0;

			HashMap mapType = new HashMap();
			List<TbProductType> l2 = productTypeDAO.findAll();
			for (TbProductType tmp : l2) {
				mapType.put(tmp.getName(), tmp);
			}

			if(mapType.size()<=0)
				return 0;
			
			XSSFSheet sheet = workbook.getSheetAt(0); // 单元表
			XSSFRow row = null; // 行
			String cell = null; // 列
			if (BlankUtil.isBlank(workbook)) {
				return 0;
			}
			List<TbDataRecord> recordlist = new ArrayList<TbDataRecord>();
			for (int i = sheet.getFirstRowNum(); i < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				// 判断是否分类，分类则跳出
				if (row.getCell(1) == null
						|| "".equals(row.getCell(1).toString().trim())) {
					cell = row.getCell(0).toString().trim();
					if ("".equals(cell.trim()))
						continue;
				}
				TbDataRecord dataRecord = new TbDataRecord();
				if(null!=row.getCell(0)){
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setProductOrderNo(row.getCell(0).getStringCellValue()); // 产品订货号
				}
				if(null!=row.getCell(1)){
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String productTypeName = row.getCell(1).getStringCellValue().trim();
					TbProductType productType = (TbProductType) mapType.get(productTypeName);
					if(productType==null)
						continue;
					dataRecord.setProductType(productType);
				}
				dataRecord.setDataSource(dataSource);
				if(null!=row.getCell(2)){
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setProductDesc(row.getCell(2).getStringCellValue());// 产品描述
				}
				if(null!=row.getCell(3)){
					row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setQuantity(row.getCell(3).getNumericCellValue());// 数量
				}
				if(null!=row.getCell(4)){
					row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setUnitPrice(row.getCell(4).getNumericCellValue());// 目录单价
				}
				if(null!=row.getCell(6)){
					row.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setDiscountRate(row.getCell(6).getNumericCellValue());// 折扣率
				}
				if(null!=row.getCell(8)){
					row.getCell(8).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setOtherRates(row.getCell(8).getNumericCellValue());// 运保及其他费率
				}
				if(null!=row.getCell(10)){
					row.getCell(10).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setInstallServiceCharge(row.getCell(10)
							.getNumericCellValue());// 安装服务费
				}
				// 保修费用等于数量的值
				if(null!=row.getCell(11)){
					row.getCell(11).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setFirstYear(row.getCell(11).getNumericCellValue());// 第一年保修费用
				}
				if(null!=row.getCell(12)){
					row.getCell(12).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setSecondYear(row.getCell(12).getNumericCellValue());// 第二年保修费用
				}
				if(null!=row.getCell(13)){
					row.getCell(13).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setThirdYear(row.getCell(13).getNumericCellValue());// 第三年保修费用
				}
				if(null!=row.getCell(16)){
					row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setRemark(row.getCell(16).getStringCellValue()); // 备注
				}
				recordlist.add(dataRecord);
			}
			result += batchSaveDataSource(recordlist);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("请检查excel的数据类型是否符合格式,[String,String,String,Numeric,Numeric,Numeric,Numeric,Numeric,Numeric,Numeric,String]");
		}
		return result;
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
	
	/**
	 * 
	 * 方法用途和描述: 更新excel内的分类和类型
	 * 
	 * @throws Exception
	 * @author zhangjh 新增日期：2012-12-21
	 * @since ContractManage
	 */
	private boolean updateCategoryAndType(XSSFWorkbook workbook) throws Exception{
		XSSFSheet sheet = workbook.getSheetAt(0); //单元表
		XSSFRow row = null;    //行
		String cell = null;  //列
		String category = null;
		if(BlankUtil.isBlank(workbook)){
			return false;
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
		return true;
	}
	
	
}
