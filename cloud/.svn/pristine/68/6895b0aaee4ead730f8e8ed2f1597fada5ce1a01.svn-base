

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.PolicyServerService;
import cn.com.kxcomm.ipmi.service.PolicyService;

/**
 * 
* 功能描述：策略测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class PolicyServerTest {
	
	private static Logger log = Logger.getLogger(PolicyServerTest.class);
	private ApplicationContext ctx;
	private PolicyServerService policyServerService;
	
	public PolicyServerTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		policyServerService = (PolicyServerService) ctx.getBean("policyServerService");
	}
	
	/**
	 * 策略分页查询
	 */
	public void findByPage(){

	}
	
}

