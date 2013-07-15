package cn.com.kxcomm.common.util;

import java.util.List;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.kxcomm.contractmanage.dao.MailTimeTaskDAO;
import cn.com.kxcomm.contractmanage.entity.TbMailTimeTask;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.impl.ProjectService;
import cn.com.kxcomm.contractmanage.service.impl.UserServiceImpl;

/**
 * 容器启动时看看有那些发邮件的任务要建立
 * @author DongGod
 *
 */
public class MailTimeTaskCreate{
	private static final Logger log = Logger.getLogger(MailTimeTaskCreate.class);
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	private MailTimeTaskDAO mailTimeTaskDAO;
	private ProjectService projectService;
	private UserServiceImpl userService;
	
	public MailTimeTaskCreate(){
		mailTimeTaskDAO = (MailTimeTaskDAO) ctx.getBean("mailTimeTaskDAO");
		projectService = (ProjectService) ctx.getBean("projectService");
		userService = (UserServiceImpl) ctx.getBean("userService");
	}

	public void createTimeTask(){
		List<TbMailTimeTask> list=mailTimeTaskDAO.findAll();
		for(TbMailTimeTask tbMailTimeTask:list){
			int projectId=tbMailTimeTask.getProjectId();
			TbProject project=projectService.getByPk(projectId);
			TbUser tbUser=userService.getByPk(tbMailTimeTask.getUserId());
			Timer timer = new Timer();
			MailTimeTask mailTimeTask = new MailTimeTask(tbMailTimeTask,tbUser.getEmail(),tbMailTimeTask.getSubject(),tbMailTimeTask.getContent());
			timer.schedule(mailTimeTask,tbMailTimeTask.getDateTime());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MailTimeTaskCreate().createTimeTask();
	}
}

