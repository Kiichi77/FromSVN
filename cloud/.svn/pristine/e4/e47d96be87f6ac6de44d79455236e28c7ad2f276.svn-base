

import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBelong;
import cn.com.kxcomm.ipmi.service.BelongService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;

/**
 * 
* 功能描述：服务器信息管理测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class BelongTest {
	
	private static Logger log = Logger.getLogger(BelongTest.class);
	private ApplicationContext ctx;
	private BelongService belongService;
	
	public BelongTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		belongService = (BelongService) ctx.getBean("belongService");
	}
	
//	/**
//	 * 
//	* 方法用途和描述:所有的菜单
//	* @author chenl 新增日期：2012-9-7
//	* @since ipmi_web
//	 */
//	@Test
//	public void testMenu(){
//		List<BelongEntity> belonglist = belongService.test(1L);
//		log.info("belongList:"+Json.toJson(belonglist));
//	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询
	* @author chenl 新增日期：2012-8-31
	* @since ipmi_web
	 */
	public void pageInfo(){
		TbBelong entity = new TbBelong();
		entity.setLevel((short) -1);
		Page<TbBelong> pageInfo = new Page<TbBelong>();
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setStart(1);
		pageInfo.setEnd(20);
		log.info("pageifno:"+Json.toJson(belongService.findByPage(entity, pageInfo)));
	}
	
	/**
	 * 
	* 方法用途和描述: 查询所有的菜单
	* @author chenl 新增日期：2012-8-29
	* @since ipmi_web
	 */
	public void belongMenuList(){
		List<BelongEntity> listEntity = belongService.getAllBelongMenu(101L);
		log.info("listEntity:"+Json.toJson(listEntity));
	}
	
	/**
	 * 
	* 方法用途和描述: 查询最大id
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	public void findMaxId(){
		Long maxId = belongService.findMaxId();
		log.info("maxId:"+maxId);
	}
	
	/**
	 * 
	* 方法用途和描述: 菜单查询
	* @author chenl 新增日期：2012-8-13
	* @since ipmi_web
	 */
	public void findMenu(){
		List<BelongEntity> belongList = belongService.findMenu(101L);
		log.info("belongList:"+Json.toJson(belongList));
	}
	
	/**
	 * 服务器保存
	 * level:1 数据中心 2机房 3机架系 4机架 5机箱
	 */
	//@Test
	public void save(){
		//保存一级数据
		TbBelong belong = new TbBelong();
		belong.setId(100L);
		belong.setLevel((short)1);
		belong.setName("数据中心1");
		belongService.save(belong);
		
		TbBelong belong0 = new TbBelong();
		belong0.setId(101L);
		belong0.setLevel((short)1);
		belong0.setName("数据中心2");
		belongService.save(belong0);
		
		//保存二级数据
		TbBelong belong1 = new TbBelong();
		belong1.setId(200L);
		belong1.setLevel((short)2);
		belong1.setName("机房1");
		belong1.setParId(101L);
		belongService.save(belong1);
		
		TbBelong belong2 = new TbBelong();
		belong2.setId(201L);
		belong2.setLevel((short)2);
		belong2.setName("机房2");
		belong2.setParId(101L);
		belongService.save(belong2);
		
		TbBelong belong4 = new TbBelong();
		belong4.setId(202L);
		belong4.setLevel((short)2);
		belong4.setName("机房3");
		belong4.setParId(101L);
		belongService.save(belong4);	
		
		TbBelong belong5 = new TbBelong();
		belong5.setId(203L);
		belong5.setLevel((short)2);
		belong5.setName("机房4");
		belong5.setParId(101L);
		belongService.save(belong5);
		
		//保存三级数据
		TbBelong belong6 = new TbBelong();
		belong6.setId(300L);
		belong6.setLevel((short)3);
		belong6.setName("机架系1");
		belong6.setParId(200L);
		belongService.save(belong6);
		
		TbBelong belong7 = new TbBelong();
		belong7.setId(301L);
		belong7.setLevel((short)3);
		belong7.setName("机架系2");
		belong7.setParId(201L);
		belongService.save(belong7);
		
		TbBelong belong8 = new TbBelong();
		belong8.setId(302L);
		belong8.setLevel((short)3);
		belong8.setName("机架系3");
		belong8.setParId(202L);
		belongService.save(belong8);
		
		TbBelong belong9 = new TbBelong();
		belong9.setId(303L);
		belong9.setLevel((short)3);
		belong9.setName("机架系4");
		belong9.setParId(203L);
		belongService.save(belong9);
		
		//保存四级数据
		TbBelong belong3 = new TbBelong();
		belong3.setId(400L);
		belong3.setLevel((short)4);
		belong3.setName("机架1");
		belong3.setParId(300L);
		belongService.save(belong3);
		
		TbBelong belong10 = new TbBelong();
		belong10.setId(401L);
		belong10.setLevel((short)4);
		belong10.setName("机架2");
		belong10.setParId(301L);
		belongService.save(belong10);
		
		TbBelong belong11 = new TbBelong();
		belong11.setId(402L);
		belong11.setLevel((short)4);
		belong11.setName("机架3");
		belong11.setParId(302L);
		belongService.save(belong11);
		
		TbBelong belong12 = new TbBelong();
		belong12.setId(403L);
		belong12.setLevel((short)4);
		belong12.setName("机架4");
		belong12.setParId(303L);
		belongService.save(belong12);
		
		//保存五级数据
		TbBelong belong13 = new TbBelong();
		belong13.setId(500L);
		belong13.setLevel((short)5);
		belong13.setName("机箱1");
		belong13.setParId(400L);
		belongService.save(belong13);
		
		TbBelong belong14 = new TbBelong();
		belong14.setId(501L);
		belong14.setLevel((short)5);
		belong14.setName("机箱2");
		belong14.setParId(401L);
		belongService.save(belong14);
		
		TbBelong belong15 = new TbBelong();
		belong15.setId(502L);
		belong15.setLevel((short)5);
		belong15.setName("机箱3");
		belong15.setParId(402L);
		belongService.save(belong15);
		
		TbBelong belong16 = new TbBelong();
		belong16.setId(503L);
		belong16.setLevel((short)5);
		belong16.setName("机箱4");
		belong16.setParId(403L);
		belongService.save(belong16);
		
	}
	
	/**
	 * 
	* 方法用途和描述: 查询上一级菜单的集合列表
	* @author chenl 新增日期：2012-8-13
	* @since ipmi_web
	 */
	public void findParentBylevel(){
		List<TbBelong> belonglist = belongService.findParentBylevel((short)2);
		log.info("belongList:"+Json.toJson(belonglist));
	}
	
}

