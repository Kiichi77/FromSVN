

import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.entity.AlertServerEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.AlertServerService;
import cn.com.kxcomm.ipmi.service.AlertService;
import cn.com.kxcomm.ipmi.vo.AlertVO;

/**
 * 
* 功能描述：服务器信息管理测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class AlertTest {
	
	private static Logger log = Logger.getLogger(AlertTest.class);
	private ApplicationContext ctx;
	private AlertService alertService;
	private AlertServerService alertServerService;
	
	public AlertTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		alertService = (AlertService) ctx.getBean("alertService");
		alertServerService = (AlertServerService) ctx.getBean("alertServerService");
	}
	
	/**
	 * 
	* 方法用途和描述: 查询所有的菜单
	* @author chenl 新增日期：2012-8-29
	* @since ipmi_web
	 */
	public void belongMenuList(){
		AlertVO entity = new AlertVO();
		Page<AlertEntity> pageInfo = new Page<AlertEntity>();
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setStart(1);
		pageInfo.setEnd(20);
		Page<AlertVO> listEntity = alertService.findByPage(entity, pageInfo);
		log.info("listEntity:"+Json.toJson(listEntity));
	}
	
	/**
	 * 
	* 方法用途和描述: 保存警告信息
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public void save(){
		String[] ids = new String[1];
		ids[0] = "1";
		AlertEntity entity = new AlertEntity();
		entity.setSeriousNum(98);
		entity.setSeriousEnable((short) 1);
		entity.setWarningNum(80);
		entity.setWarningEnable((short) 1);
		entity.setFilterType(1);
		entity.setUserId(1L);
		alertService.save(entity);
		for (int i = 0; i < ids.length; i++) {
			AlertServerEntity alertServerEntity = new AlertServerEntity();
			alertServerEntity.setAlert(entity);
			TbServer server = new TbServer();
			server.setId(Long.parseLong(ids[i]));
			alertServerEntity.setServer(server);
			alertServerService.save(alertServerEntity);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 保存警告信息
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public void update(){
		String[] ids = new String[1];
		ids[0] = "1";
		
		AlertVO alertVo = new AlertVO();
		alertVo.setAlertId(1);
		alertVo.setSeriousNum(200);
		alertVo.setWarningNum(200);
		alertVo.setFilterType(1);
		TbUser user = new TbUser();
		user.setId(1L);
		alertVo.setTbUsers(user);
		
		//修改告警信息关联的服务器
		if(!BlankUtil.isBlank(ids)&&ids.length>0){
			int alertId = alertVo.getAlertId();
			Integer[] alertids = new Integer[1];
			alertids[0] = alertId;
			//删除该告警信息关联的所有服务器
			alertServerService.deleteByAlertID(alertids);
			//添加告警信息id和服务器的关系
			for (int i = 0; i < ids.length; i++) {
				AlertServerEntity alertServerEntity = new AlertServerEntity();
				AlertEntity alertEntity = new AlertEntity();
				alertEntity.setAlertId(alertId);
				alertServerEntity.setAlert(alertEntity);
				TbServer server = new TbServer();
				server.setId(Long.parseLong(ids[i]));
				alertServerEntity.setServer(server);
				alertServerService.save(alertServerEntity);
			}
		}
		//修改告警信息
		boolean flat = alertService.updateAlert(alertVo);
	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询
	* @author chenl 新增日期：2012-9-20
	* @since ipmi_web
	 */
	
	public void findBypage(){
		AlertVO alertVo = new AlertVO();
		Page<AlertEntity> pageInfo = new Page<AlertEntity>();
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setStart(1);
		pageInfo.setEnd(20);
		Page<AlertVO> page = alertService.findByPage(alertVo, pageInfo);
		log.info(""+Json.toJson(page));
	}
	
	/**
	 * 
	* 方法用途和描述:删除
	* @author chenl 新增日期：2012-9-20
	* @since ipmi_web
	 */
	public void deleteAll(){
		Integer[] idslong = new Integer[1];
		idslong[0]=3;
		//先删除告警信息与服务器信息之间的关系
		alertServerService.deleteByAlertID(idslong);
		//删除告警信息
		alertService.deleteAllByPks(idslong);
	}
	
	@Test
	public void queryServerByAlertId() {
		List<AlertServerEntity> alertServerEntitys = (List<AlertServerEntity>) alertServerService.findListByField("alert.alertId",1);
		System.out.println("*******************size:  "+alertServerEntitys.size());
	}
}

