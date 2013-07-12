package cn.com.kxcomm.common.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.kxcomm.ipmi.dao.RightDAO;
import cn.com.kxcomm.ipmi.dao.RightRoleDAO;
import cn.com.kxcomm.ipmi.dao.RoleDAO;
import cn.com.kxcomm.ipmi.dao.UserDAO;
import cn.com.kxcomm.ipmi.entity.TbRight;
import cn.com.kxcomm.ipmi.entity.TbRightRole;
import cn.com.kxcomm.ipmi.entity.TbRightRoleId;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbUser;

/**
 * 初始化系统数据
 * 
 * @author Administrator
 * 
 */
public class InitDatabase {
	private ApplicationContext ctx;
	private static RightDAO rightDAO;
	private RoleDAO roleDAO;
	private UserDAO userDAO;
	private RightRoleDAO rightRoleDAO;
	private static InitDatabase instance = null;
	private static final Logger log = Logger.getLogger(InitDatabase.class);
	
	public InitDatabase() {
		ctx = new ClassPathXmlApplicationContext(
		"modules/applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		rightDAO = (RightDAO) ctx.getBean("rightDAO");
		roleDAO = (RoleDAO) ctx.getBean("roleDAO");
		userDAO = (UserDAO) ctx.getBean("userDAO");
		rightRoleDAO = (RightRoleDAO) ctx.getBean("rightRoleDAO");
	}

	public static InitDatabase getSingleInstance(){
		if(instance==null){
			instance = new InitDatabase();
		}
		return instance;
	}

	//TODO 
	public void writeData(){
		/**
		 * 初始化权限数据
		 */
		String hql = "select count(tr.id) from TbRight tr ";
		int total = rightDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:"+total);
		if(total<=0){
			//**********************第一级菜单*********************  
			//=======================1、系统管理======================
			TbRight tbRight = new TbRight();
			tbRight.setId(1l);
			tbRight.setRightName("系统管理");
			tbRight.setRightLevel(1);
			rightDAO.save(tbRight);
			//=======================2、服务器信息======================
			tbRight = new TbRight();
			tbRight.setId(2l);
			tbRight.setRightName("服务器信息");
			tbRight.setRightLevel(1);
			rightDAO.save(tbRight);
			//=======================3、监控分析======================
			tbRight = new TbRight();
			tbRight.setId(3l);
			tbRight.setRightName("监控分析");
			tbRight.setRightLevel(1);
			rightDAO.save(tbRight);
			//=======================4、策略管理======================
			tbRight = new TbRight();
			tbRight.setId(4l);
			tbRight.setRightName("策略管理");
			tbRight.setRightLevel(1);
			rightDAO.save(tbRight);
			
			//**********************第二级菜单*********************
			//========1、系统管理========
			//+++++++++++++++++++++++++++++++++
			//        1.1、用户管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(11l);
			tbRight.setRightName("用户管理");
			tbRight.setParid(1);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/user_manager/user_manager.jsp");
			rightDAO.save(tbRight);
			//+++++++++++++++++++++++++++++++++
			//        1.2、角色管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(12l);
			tbRight.setRightName("角色管理");
			tbRight.setParid(1);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/role_manager/role_manager.jsp");
			rightDAO.save(tbRight);
			//+++++++++++++++++++++++++++++++++
			//        1.3、系统日志管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(13l);
			tbRight.setRightName("系统日志管理");
			tbRight.setParid(1);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/systemLog_manager/systemLoglist_manager.jsp");
			rightDAO.save(tbRight);
			
			//========2、服务器信息========
			//+++++++++++++++++++++++++++++++++
			//        2.1、服务器信息摘要
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(21l);
			tbRight.setRightName("服务器信息摘要");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/serviceInfo_manager/serviceInfo_summary.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.2、目标服务器管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(22l);
			tbRight.setRightName("目标服务器管理");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/targetService_manager/targerService_manager.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.3、服务器系统日志  TODO未完成
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(23l);
			tbRight.setRightName("服务器系统日志");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/serverEventLog_manager/serverEventLog_manager.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.4、服务器报警  TODO未完成
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(24l);
			tbRight.setRightName("服务器报警");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/alert_manager/alert_manager.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.5、黑名单
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(25l);
			tbRight.setRightName("黑名单");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/blacklist_manager/blacklist_manager.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.6、服务器位置维护
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(26l);
			tbRight.setRightName("服务器位置维护");
			tbRight.setParid(2);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/dim_servicePlace/dataCenter_manager.jsp");
			rightDAO.save(tbRight);
			
			//========3、监控分析========
			//+++++++++++++++++++++++++++++++++
			//        3.1、功耗分析
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(31l);
			tbRight.setRightName("功耗分析");
			tbRight.setParid(3);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/monitor_analyzer/monitorPwer_analyzer.jsp");
			rightDAO.save(tbRight);
			
			//========4、策略管理========
			//+++++++++++++++++++++++++++++++++
			//        4.1、策略信息管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(41l);
			tbRight.setRightName("策略信息管理");
			tbRight.setParid(4);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/policy_manager/policy_manager.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        4.2、服务器策略
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(42l);
			tbRight.setRightName("服务器策略");
			tbRight.setParid(4);
			tbRight.setRightLevel(2);
			tbRight.setUrl("/jsp/servicePolicy_manager/servicePolicy_manager.jsp");
			rightDAO.save(tbRight);
			

			
			//**********************第三级菜单*********************
			//+++++++++++++++++++++++++++++++++
			//        1.1.1、用户管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(111l);
			tbRight.setRightName("用户action");
			tbRight.setParid(11);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/user/user/user");
			rightDAO.save(tbRight);
			//---------------------------------//
			tbRight = new TbRight();
			tbRight.setId(112l);
			tbRight.setRightName("用户action");
			tbRight.setParid(11);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/role/role/role");
			rightDAO.save(tbRight);
			//+++++++++++++++++++++++++++++++++
			//        1.1.2、角色管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(121l);
			tbRight.setRightName("角色action");
			tbRight.setParid(12);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/role/role/role");
			rightDAO.save(tbRight);
			//---------------------------------//
			tbRight = new TbRight();
			tbRight.setId(122l);
			tbRight.setRightName("用户action");
			tbRight.setParid(12);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/right/right/right");
			rightDAO.save(tbRight);
			//+++++++++++++++++++++++++++++++++
			//        1.1.3、系统操作日志管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(131l);
			tbRight.setRightName("系统操作action");
			tbRight.setParid(13);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/systemLog/systemLog/systemLog");
			rightDAO.save(tbRight);
			log.info("初始化TbRight数据");
			
			//+++++++++++++++++++++++++++++++++
			//        2.1.1、服务器信息摘要
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(211l);
			tbRight.setRightName("服务器信息摘要action");
			tbRight.setParid(21);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/datacenter/datacenter/datacenter");
			rightDAO.save(tbRight);
			//---------------------------------//
			tbRight = new TbRight();
			tbRight.setId(212l);
			tbRight.setRightName("服务器信息摘要action");
			tbRight.setParid(21);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/serviceinfo/serviceinfo/serviceinfo");
			rightDAO.save(tbRight);
			//---------------------------------//
			tbRight = new TbRight();
			tbRight.setId(213l);
			tbRight.setRightName("服务器信息摘要action");
			tbRight.setParid(21);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/jsp/serviceInfo_manager/serviceInfo_details.jsp");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.2.2、目标服务器管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(221l);
			tbRight.setRightName("目标服务器action");
			tbRight.setParid(22);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/serviceinfo/serviceinfo/serviceinfo");
			rightDAO.save(tbRight);
			//---------------------------------//
			tbRight = new TbRight();
			tbRight.setId(222l);
			tbRight.setRightName("目标服务器action");
			tbRight.setParid(22);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/datacenter/datacenter/datacenter");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.2.3、服务器系统日志
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(231l);
			tbRight.setRightName("服务器系统日志action");
			tbRight.setParid(23);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/systemeventlog/systemeventlog/systemeventlog");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.4、服务器报警  
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(241l);
			tbRight.setRightName("服务器报警action");
			tbRight.setParid(24);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/alert/alert/alert");
			rightDAO.save(tbRight);
			
			tbRight = new TbRight();
			tbRight.setId(242l);
			tbRight.setRightName("服务器报警action");
			tbRight.setParid(24);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/user/user/user");
			rightDAO.save(tbRight);
			
			tbRight = new TbRight();
			tbRight.setId(243l);
			tbRight.setRightName("服务器报警action");
			tbRight.setParid(24);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/datacenter/datacenter/datacenter");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.2.5、黑名单
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(251l);
			tbRight.setRightName("黑名单action");
			tbRight.setParid(25);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/blackName/blackName/blackName");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        2.2.6、服务器位置维护
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(261l);
			tbRight.setRightName("服务器位置维护action");
			tbRight.setParid(26);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/datacenter/datacenter/datacenter");
			rightDAO.save(tbRight);
			
			tbRight = new TbRight();
			tbRight.setId(262l);
			tbRight.setRightName("服务器位置维护action");
			tbRight.setParid(26);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/belong/belong/belong");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        3.3.1、功耗分析
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(311l);
			tbRight.setRightName("功耗分析action");
			tbRight.setParid(31);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/historyServer/historyServer/historyServer");
			rightDAO.save(tbRight);
			//----------------------------------//
			tbRight = new TbRight();
			tbRight.setId(312l);
			tbRight.setRightName("服务器位置维护action");
			tbRight.setParid(31);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/datacenter/datacenter/datacenter");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        4.4.1、策略信息管理
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(411l);
			tbRight.setRightName("策略信息管理");
			tbRight.setParid(41);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/policy/policy/policy");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        4.4.2、服务器策略
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(421l);
			tbRight.setRightName("服务器策略");
			tbRight.setParid(42);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/policy/policy/policy");
			rightDAO.save(tbRight);
			
			//+++++++++++++++++++++++++++++++++
			//        4.4.3、错误代码
			//+++++++++++++++++++++++++++++++++
			tbRight = new TbRight();
			tbRight.setId(431l);
			tbRight.setRightName("服务器策略");
			tbRight.setParid(43);
			tbRight.setRightLevel(3);
			tbRight.setUrl("/failRecode/failRecode/failRecode");
			rightDAO.save(tbRight);
			log.info("初始化TbRight数据");
		}
		/**
		 * 初始角色限数据
		 */
		hql = "select count(*) from TbRole tr ";
		total = roleDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:"+total);
		if(total<=0){
			TbRole tbrole = new TbRole();
			tbrole.setRolename("系统管理员");
			tbrole.setNote("初始化管理员");
			tbrole.setId(1l);
			roleDAO.save(tbrole);
			log.info("初始化TbRole数据");
		}
		/**
		 * 初始化用户数据
		 */
		hql = "select count(*) from TbUser tr ";
		total = userDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:"+total);
		if(total<=0){
			TbUser tbuser = new TbUser();
			tbuser.setAccouont("admin");
			tbuser.setEmail("zhangjianhua@kxcomm.com.cn");
			tbuser.setNote("初始化管理员");
			tbuser.setPassword(MD5.toMD5("123"));
			tbuser.setPhoneNumber("15817130480");
			TbRole tbrole=new TbRole();
			tbrole.setId(1l);
			tbuser.setTbRole(tbrole);
			tbuser.setUserName("张建华");
			userDAO.save(tbuser);
			log.info("初始化TbUser数据");
		}
		/**
		 * 初始化角色权限关系表
		 */
		hql = "select count(*) from TbRightRole tr ";
		total = roleDAO.findTotalCount(hql, null);
		log.info("rightDAO:"+total);
		if(total<=0){
			TbRightRoleId tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(1l);
			tbRightRoleId.setRoleId(1l);
			TbRightRole tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(2l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(3l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(4l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(11l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(12l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(13l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(21l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(22l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(23l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(24l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(25l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(26l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);

			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(31l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(41l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);
			//--------------------------------------------------------//
			tbRightRoleId=new TbRightRoleId();
			tbRightRoleId.setRightId(42l);
			tbRightRoleId.setRoleId(1l);
			tbRightRole = new TbRightRole();
			tbRightRole.setId(tbRightRoleId);
			rightRoleDAO.save(tbRightRole);			
		}
	}
	public static void main(String args[]){
		InitDatabase aa = InitDatabase.getSingleInstance();
		aa.writeData();
	}
}
