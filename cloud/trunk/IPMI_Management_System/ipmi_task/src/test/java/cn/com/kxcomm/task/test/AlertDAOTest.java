package cn.com.kxcomm.task.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.task.dao.AlertDAO;

public class AlertDAOTest {
	private ApplicationContext ctx;
	private AlertDAO alertDAO;

	public AlertDAOTest() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		alertDAO = (AlertDAO) ctx.getBean("alertDAO");
	}

	//@Test
	public void query() {
		List<Object[]> ll = alertDAO.queryAlert();
		System.out.println(ll.size());
		for(Object[] cc :ll){
			AlertEntity a = (AlertEntity) cc[0];
			TbServer b = (TbServer) cc[1];
			TbUser c = (TbUser) cc[2];
			System.out.println(a.toString());
			System.out.println(b.toString());
			System.out.println(c.toString());
		}
	}
	
	
	@Test
	public void queryAlertUser(){
		List<TbUser> ll = alertDAO.queryAlertServer(1);
		System.out.println(ll.size());
		for(TbUser cc :ll){
			System.out.println(cc.toString());
		}
	}
}
