package cn.com.kxcomm.common.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.activiti.engine.task.Task;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.common.util.PublishingProcess;

public class SystemInitListener implements ServletContextListener {
/**
 * 把系统初始化工作的放到这里来.
 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//初始化activiti工作流引擎
		ProcessCustomService.getTaskService().createTaskQuery().list();
		new PublishingProcess().publishing();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
