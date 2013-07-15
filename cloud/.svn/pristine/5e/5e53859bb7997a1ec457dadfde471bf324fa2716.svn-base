package test.dao;

import java.util.Date;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;

public class ConfigModelsTest  extends BaseTest{
	@Test
	public void save() {
		TbDataSource dataSource = new TbDataSource();
		dataSource.setId(4);
		dataSource.setCreatetime(new Date());
		dataSource.setDescription("bbbbbb");
		dataSource.setStatus(1);
		dataSource.setName("bbbbbb");
		
		TbConfigModels entity = new TbConfigModels();
		entity.setCreatetime(new Date());
		entity.setDataSource(dataSource);
		entity.setName("aa");
		entity.setStatus(1);
		entity.setType(1);
		configModelsDAO.save(entity);
	}
}
