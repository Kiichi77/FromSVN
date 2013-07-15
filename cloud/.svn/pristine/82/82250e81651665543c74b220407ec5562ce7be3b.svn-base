package cn.com.kxcomm.task.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.task.dao.TbServerDAO;
import cn.com.kxcomm.task.service.CollectDataService;

public class TbServerTest {
	private ApplicationContext ctx;
	private TbServerDAO tbServerDAO;
	private CollectDataService collectDataService;

	public TbServerTest() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		tbServerDAO = (TbServerDAO) ctx.getBean("tbServerDAO");
		collectDataService = (CollectDataService) ctx.getBean("collectDataService");
	}

	@Test
	public void queryNormalServer() {
		List<TbServer> ll = tbServerDAO.queryNormalServer();
		for(TbServer cc :ll){
			System.out.println(cc.toString());
		}
	}
	

}
