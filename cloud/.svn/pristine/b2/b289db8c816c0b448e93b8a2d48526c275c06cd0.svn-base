package cn.com.kxcomm.task;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
* 功能描述:任务引擎入口
* <p>版权所有：康讯通讯
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author chennl 新增日期：2010-6-8
* @author 你的姓名 修改日期：2010-6-8
* @since ipmi_task
 */
public class MainService {
	private static ApplicationContext springContext = null;
	public static MainService instance = null;
	private static Scheduler scheduler = null; 
	public static ApplicationContext springContext (){
		return springContext;
	}
	
	public static Scheduler scheduler (){
		return scheduler;
	}
	
	public MainService(){
		if(instance == null)
		  springContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-quartz.xml"});
	
		scheduler = (Scheduler) springContext.getBean("schedulerFactory");
	}
	
	
    public static void main( String[] args ) {
    	new MainService();
    }

}
