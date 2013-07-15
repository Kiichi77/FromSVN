package test.dao;

import java.util.Date;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.entity.TbProductType;

public class DataRecordTest  extends BaseTest{
	@Test
	public void save() {
		TbDataSource dataSource = new TbDataSource();
		dataSource.setId(4);
		dataSource.setCreatetime(new Date());
		dataSource.setDescription("bbbbbb");
		dataSource.setStatus(1);
		dataSource.setName("bbbbbb");
		TbProductType productType = new TbProductType();
		productType.setId(1);
		productType.setName("lks");
		Double price = (double)11;
		TbDataRecord entity = new TbDataRecord();
		entity.setDataSource(dataSource);
		entity.setDiscountRate((double) 28);
		entity.setInstallServiceCharge((double)11);
		entity.setOtherRates((double)11);
		entity.setProductDesc("ss");
		entity.setProductOrderNo("1112");
		entity.setProductType(productType);
		entity.setQuantity(price);
		entity.setRemark("ss");
		entity.setSecondYear(price);
		entity.setThirdYear(price);
		entity.setUnitPrice(price);
		entity.setFirstYear(price);
		dataRecordDAO.save(entity);
		
	}
	//@Test
	public void delete() {
		System.out.println("------------------------");
		TbDataRecord entity = dataRecordDAO.findById(8);
		if(entity!=null){
			System.out.println("resaa--->"+entity.toString());
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			dataRecordDAO.delete(entity);
		}
		System.out.println("-------------end-----------");
	}
	
	public void findById(){
		TbDataRecord entity = dataRecordDAO.findById(8);
		System.out.println(entity.toString());
	}
}
