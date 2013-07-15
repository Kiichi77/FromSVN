package cn.com.kxcomm.task.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.task.dao.PolicyDAO;
import cn.com.kxcomm.task.dao.PolicyServerDAO;

public class PolicyDAOTest {

	private ApplicationContext ctx;
	private PolicyDAO policyDAO;
	private PolicyServerDAO policyServerDAO;

	public PolicyDAOTest() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		policyDAO = (PolicyDAO) ctx.getBean("policyDAO");
		policyServerDAO = (PolicyServerDAO) ctx.getBean("policyServerDAO");
	}

	
	public void list() {
		List ll = policyDAO.findAll();
		System.out.println(ll.size());
	}
	
	@Test
	public void update(){
		List<Object[]> serverList = policyServerDAO.queryServerByPolicyId(2);
		for(Object[] obj : serverList){
			TbPolicySever ser = (TbPolicySever) obj[0];
			ser.setBiningState(2);
			policyServerDAO.saveOrUpdate(ser);
		//	System.out.println(ser.toString());
		}
	}

}
