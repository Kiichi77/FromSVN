

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.SystemOperLogService;
import cn.com.kxcomm.ipmi.vo.SystemoperlogEntity;

/**
 * 
* 功能描述:系统操作日志测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class SystemOperLogTest {
	
	private static Logger log = Logger.getLogger(SystemOperLogTest.class);
	private ApplicationContext ctx;
	private SystemOperLogService systemOperLogService;
	
	public SystemOperLogTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		systemOperLogService = (SystemOperLogService) ctx.getBean("systemOperLogService");
	}
	
	/**
	 * 
	* 方法用途和描述: 操作日志分页查询test
	* @author chenl 新增日期：2012-8-9
	* @since ipmi_web
	 */
	public void pageBySystemOperLog(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse("2012-08-26");
			Timestamp time = new Timestamp(date.getTime());
			SystemoperlogEntity entity = new SystemoperlogEntity();
			entity.setDateTime("2012-08-26");
			TbUser tbuser = new TbUser();
//			tbuser.setAccouont("admin");
			entity.setTbUser(tbuser);
			entity.setOperationType("5");
			
			Page<TbSystemoperlog> pageInfo = new Page<TbSystemoperlog>();
			pageInfo.setPageSize(20);
			pageInfo.setPageNo(1);
			pageInfo.setStart(1);
			pageInfo.setEnd(20);
			
			Page<SystemoperlogEntity> pageLog = systemOperLogService.findByPageEntity(entity, pageInfo);
			log.info("pageLog:"+Json.toJson(pageLog));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

