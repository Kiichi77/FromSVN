
import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.RoleService;


public class RoleTest {
	
	private static Logger log = Logger.getLogger(RoleTest.class);
	private ApplicationContext ctx;
	
	private RoleService roleService;
	
	public RoleTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		roleService = (RoleService) ctx.getBean("roleService");
	}
	
	/**
	 * 分页
	 */
	public void list() {
		TbRole entity = new TbRole();
		Page<TbRole> pageInfo = new Page<TbRole>();
		pageInfo.setStart(1);
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setOrderFiled("id");
		pageInfo.setOrderDirection("desc");
		Page<TbRole> page = roleService.findByPage(entity, pageInfo);
		log.info("page:"+Json.toJson(page));
	}

	/**
	 * 保存角色
	 */
	public void saveRole() {
		TbRole model = new TbRole();
		model.setRolename("ChuiZiHua");
		model.setNote("remark");
		roleService.save(model);
		log.info("model.getId:"+model.getId());
	}
	
	/**
	 * 插入角色和权限的关系
	 */
	public void insertRoleRight(){
//		Long[] rightIds = {1L,2L,3L};
//		log.info("rightIds:"+Json.toJson(rightIds));
//		roleService.insertRoleRight(5L, rightIds);
		log.info("insert RoleRight success.");
	}
	
	/**
	 * 插入角色和权限的关系
	 */
	public void updateRoleRight(){
		Long[] rightIds = {4L,5L,1L};
		log.info("rightIds:"+Json.toJson(rightIds));
		roleService.updateRoleRight(5L, rightIds);
		log.info("insert RoleRight success.");
	}
	
}

