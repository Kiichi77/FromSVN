import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.BlackNameService;
import cn.com.kxcomm.ipmi.vo.BlackNameEntity;

/**
 * 
* 功能描述:黑名单测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class BlackNameTest {
	
	private static Logger log = Logger.getLogger(BlackNameTest.class);
	private ApplicationContext ctx;
	private BlackNameService blackNameService;
	
	public BlackNameTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		blackNameService = (BlackNameService) ctx.getBean("blackNameService");
	}
	
	/**
	 * 
	* 方法用途和描述:重新检测
	* @author chenl 新增日期：2012-8-29
	* @since ipmi_web
	 */
	public void toDetection(){
		TbServer server = new TbServer();
		server.setIdracIp("192.168.1.20");
		server.setIdracPassword("123456");
		server.setIdracUser("test");
		boolean flat = blackNameService.toDetection(server, 2L);
		log.debug("flat:"+flat);
	}
	
	/**
	 * 黑名单分页查询
	 */
	public void findByPage(){
		BlackNameEntity entity = new BlackNameEntity();
		Page<TbBlackName> pageInfo = new Page<TbBlackName>();
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setStart(1);
		pageInfo.setEnd(20);
		Page<BlackNameEntity> page = blackNameService.queryPage(entity, pageInfo);
		log.info("menuList:"+Json.toJson(page));
	}
	
	/**
	 * 
	* 方法用途和描述: 保存
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	
	public void save(){
		TbBlackName blackName = new TbBlackName();
		blackName.setId(1L);
		blackName.setServerId(3L);
		blackNameService.save(blackName);
		
		TbBlackName blackName1 = new TbBlackName();
		blackName1.setId(1L);
		blackName1.setServerId(4L);
		blackNameService.save(blackName1);
		
		TbBlackName blackName2 = new TbBlackName();
		blackName2.setId(1L);
		blackName2.setServerId(5L);
		blackNameService.save(blackName2);
		
		TbBlackName blackName3 = new TbBlackName();
		blackName3.setId(1L);
		blackName3.setServerId(6L);
		blackNameService.save(blackName3);
		
		TbBlackName blackName4 = new TbBlackName();
		blackName4.setId(1L);
		blackName4.setServerId(7L);
		blackNameService.save(blackName4);
		
		TbBlackName blackName5 = new TbBlackName();
		blackName5.setId(1L);
		blackName5.setServerId(8L);
		blackNameService.save(blackName5);
		
		TbBlackName blackName6 = new TbBlackName();
		blackName6.setId(2L);
		blackName6.setServerId(1L);
		blackNameService.save(blackName6);
		
		TbBlackName blackName7 = new TbBlackName();
		blackName7.setId(2L);
		blackName7.setServerId(3L);
		blackNameService.save(blackName7);
		
		TbBlackName blackName8 = new TbBlackName();
		blackName8.setId(2L);
		blackName8.setServerId(4L);
		blackNameService.save(blackName);
		
		TbBlackName blackName9 = new TbBlackName();
		blackName9.setId(2L);
		blackName9.setServerId(5L);
		blackNameService.save(blackName9);
		
		TbBlackName blackName10 = new TbBlackName();
		blackName10.setId(2L);
		blackName10.setServerId(6L);
		blackNameService.save(blackName10);
		
		TbBlackName blackName11 = new TbBlackName();
		blackName11.setId(2L);
		blackName11.setServerId(7L);
		blackNameService.save(blackName11);
		
		TbBlackName blackName12 = new TbBlackName();
		blackName12.setId(2L);
		blackName12.setServerId(8L);
		blackNameService.save(blackName12);
		
		TbBlackName blackName13 = new TbBlackName();
		blackName13.setId(2L);
		blackName13.setServerId(9L);
		blackNameService.save(blackName13);
		
		TbBlackName blackName14 = new TbBlackName();
		blackName14.setId(2L);
		blackName14.setServerId(10L);
		blackNameService.save(blackName14);
		
		TbBlackName blackName15 = new TbBlackName();
		blackName15.setId(2L);
		blackName15.setServerId(11L);
		blackNameService.save(blackName15);
	}
	
}

