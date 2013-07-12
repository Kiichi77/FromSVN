package cn.com.kxcomm.task.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.kxcomm.ipmi.memcached.CacheClientImpl;
import cn.com.kxcomm.ipmi.memcached.MemcachedKey;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.task.service.PolicyService;

public class RunPolicyTest {
    public static void main( String[] args ) {
    	ApplicationContext springContext =  new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    	PolicyService policyService = (PolicyService) springContext.getBean("policyService");
    	CacheClientImpl cl = (CacheClientImpl)springContext.getBean("cache");
    	if( cl!=null){
    		System.out.println("success");
    	}else{
    		System.out.println("falid");
    	}
    	cl.set("name", "yuky");
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(cl.get("name"));
    	PowerReading p = (PowerReading) cl.get("192.168.1.20"+MemcachedKey.IPMI_SYSTEMPOWER);
    	if(p!=null){
    		System.out.println("不为空");
    		System.out.println(p.toString());
    	}else{
    		System.out.println("为空");
    	}
    	policyService.run();
    }
}
