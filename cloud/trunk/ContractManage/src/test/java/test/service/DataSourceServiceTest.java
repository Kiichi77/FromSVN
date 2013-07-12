package test.service;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;

public class DataSourceServiceTest extends BaseTest{
		@Test
		public void save() {
			TbDataSource entity = new TbDataSource();
			entity.setCreatetime(new Date());
			entity.setDescription("test");
			entity.setName("xxx");
			entity.setStatus(1);
//			dataSourceService.save(entity);
		}
		
//		@Test
		public void delete() {
//			dataSourceService.deleteByPk(110);
		}
		
		
		public void findById(){
//			TbDataSource entity = dataSourceService.getByPk(1);
//			System.out.println(entity.toString());
		}
		
		//@Test
		public void findAll(){
//			List<TbDataSource> list = dataSourceService.findAll();
//			for(TbDataSource entity : list){
//				System.out.println(entity.toString());
//			}
		}
		
		public void update(){
//			TbDataSource entity = dataSourceService.getByPk(2);
//			entity.setCreatetime(new Date());
//			dataSourceService.update(entity);
//			System.out.println(entity.toString());
		}
}
