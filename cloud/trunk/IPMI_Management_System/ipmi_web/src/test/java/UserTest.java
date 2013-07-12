

import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbRight;
import cn.com.kxcomm.ipmi.entity.TbRightRole;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.UserService;
import cn.com.kxcomm.ipmi.vo.MenuEntity;
import cn.com.kxcomm.ipmi.vo.RightEntity;


public class UserTest {
	
	private static Logger log = Logger.getLogger(UserTest.class);
	private ApplicationContext ctx;
	
	private UserService userService;
	
	public UserTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		userService = (UserService) ctx.getBean("userService");
	}

	public void list() {
		TbUser entity = new TbUser();
		Page<TbUser> pageInfo = new Page<TbUser>();
		pageInfo.setStart(1);
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setOrderFiled("id");
		pageInfo.setOrderDirection("desc");
		Page<TbUser> page = userService.findByPage(entity, pageInfo);
		log.info("page:"+Json.toJson(page));
	}
	
	
	/**
	 * 查询用户角色
	 * params:consumerId 用户id
	 * return: 角色对象
	 */
	public void queryConsumerRole() {
		System.out.println("success  ssssssss...............");
		TbRole role = userService.queryConsumerRole(1L);
		log.info("role:"+Json.toJson(role));
	}
	
	/**
	 * 根据角色id查询角色权限集合
	 */
	public void queryRoleRightListByRoleId() {
		List<RightEntity> userList = userService.queryRoleRightListByRoleId(1L);
		for (int i = 0; i < userList.size(); i++) {
			log.info("角色权限："+Json.toJson(userList.get(i)));
		}
	}
	
	/**
	 * 验证旧秘密
	 */
	public void checkOldpasswd(){
		boolean flat = userService.checkPasswd(1L, "123");
		log.info("flat:"+flat);
	}
	
	/**
	 * 修改密码
	 */
	public void update(){
		userService.updatePasswd(2L, "123");
		log.info("updatepasswd is successful.");
	}
	
	/**
	 * 根据角色id查找菜单
	 */
	public void queryMenuEntityByRoleId(){
		List<MenuEntity> menuList = userService.queryMenuEntityByRoleId(1L);
		for (int i = 0; i < menuList.size(); i++) {
			log.info("menulist:"+Json.toJson(menuList));
		}
	}
}

