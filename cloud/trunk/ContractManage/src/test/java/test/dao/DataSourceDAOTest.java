package test.dao;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;

public class DataSourceDAOTest extends BaseTest{
	
	//@Test
	public void save() {
		TbDataSource entity = new TbDataSource();
		entity.setCreatetime(new Date());
		entity.setDescription("test");
		entity.setName("BBB");
		entity.setStatus(1);
		dataSourceDAO.save(entity);
	}
	
	//@Test
	public void delete() {
		TbDataSource entity = dataSourceDAO.findById(4);
		if(entity!=null)
			dataSourceDAO.delete(entity);
	}
	
	//@Test
	public void findById(){
		TbDataSource entity = dataSourceDAO.findById(4);
		System.out.println(entity.toString());
	}
	
//	@Test
	public void findAll(){
//		List<TbDataSource> list = dataSourceDAO.findAll();
//		for(TbDataSource entity : list){
//			System.out.println(entity.toString());
//		}
	}
	
	public void update(){
//		TbDataSource entity = dataSourceDAO.findById(2);
//		entity.setCreatetime(new Date());
//		dataSourceDAO.saveOrUpdate(entity);
//		System.out.println(entity.toString());
	}
	
	//@Test
	public void test(){
		this.purchaseOrderService.confirmAllReceipt(1);
	}
	@Test
	public void aa(){
		 TbContract update = contractDAO.findById(1);
		 update.setQuotationsExcel("aaa");
		 this.contractDAO.saveOrUpdate(update);
	}
}
