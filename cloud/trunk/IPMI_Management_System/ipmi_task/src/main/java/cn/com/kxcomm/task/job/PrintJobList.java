package cn.com.kxcomm.task.job;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.com.kxcomm.task.MainService;

public class PrintJobList extends QuartzJobBean{
	private static final Logger log = Logger.getLogger(PrintJobList.class);
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	protected void executeInternal(JobExecutionContext jobCtx)
	throws JobExecutionException {
		JobDataMap dataMap = jobCtx.getJobDetail().getJobDataMap();
		String isPrint = dataMap.getString("isPrint");
		if (isPrint.equals("true")) {
			log.info("======================JobList==========================");
			try {
				//				String groupName = "printGroup";
				if (MainService.scheduler() == null)
					return;
				Scheduler scheduler=MainService.scheduler();
				//获取当前运行的任务
				List<JobExecutionContext> jobList=scheduler.getCurrentlyExecutingJobs();
				for(JobExecutionContext jobExecutionContext:jobList){
					log.info("=================当前运行中的任务=================");
					log.info(jobExecutionContext.toString());
				}
				//获取当前运行中的任务名称
				String groupNames[]=scheduler.getJobGroupNames();
				for(String groupName:groupNames){
					String[] jobNames=scheduler.getJobNames(groupName);
					for(String jobName:jobNames){
						log.info("================="+groupName+"的任务:=================");
						log.info(jobName);
					}
				}
			} catch (SchedulerException e) {
				log.info("JobListTask Exception:", e);
			}
			log.info("======================JobList End========================");		
		}
	}
}
