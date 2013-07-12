

import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbRight;
import cn.com.kxcomm.ipmi.service.RightService;

/**
 * 
* 功能描述:权限测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class RightTest {
	
	private static Logger log = Logger.getLogger(RightTest.class);
	private ApplicationContext ctx;
	private RightService rightService;
	
	public RightTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		rightService = (RightService) ctx.getBean("rightService");
	}
	
	/**
	 * 分页
	 */
	public void list(){
		TbRight entity = new TbRight();
		Page<TbRight> pageInfo = new Page<TbRight>();
		pageInfo.setStart(1);
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setOrderFiled("id");
		pageInfo.setOrderDirection("desc");
		Page<TbRight> page = rightService.findByPage(entity, pageInfo);
		log.info("page:"+Json.toJson(page));
	}
	
//	/**
//	 * 查找菜单
//	 */
//	@Test
//	public void queryMenu(){
//		List<MenuEntity> menuList = rightService.queryMenu();
//		log.info("menuList:"+Json.toJson(menuList));
//	}
}

