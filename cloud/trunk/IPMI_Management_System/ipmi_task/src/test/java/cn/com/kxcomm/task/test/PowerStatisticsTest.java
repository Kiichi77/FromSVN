package cn.com.kxcomm.task.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.task.job.CollectMachineInfoJob;
import cn.com.kxcomm.task.service.PowerStatisticsService;

public class PowerStatisticsTest {
	private ApplicationContext ctx;
	private PowerStatisticsService powerStatisticsService;
	private CollectMachineInfoJob collectMachineInfoJob;

	public PowerStatisticsTest() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		powerStatisticsService = (PowerStatisticsService) ctx.getBean("powerStatisticsService");
		collectMachineInfoJob = (CollectMachineInfoJob) ctx.getBean("collectMachineInfoJob");
		
	}
	
	@Test
	public void addDate(){
	//	collectMachineInfoJob.work();
		powerStatisticsService.powerStatitics();
	}

}
