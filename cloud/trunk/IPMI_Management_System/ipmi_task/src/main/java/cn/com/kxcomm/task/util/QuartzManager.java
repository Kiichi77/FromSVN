package cn.com.kxcomm.task.util;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import cn.com.kxcomm.ipmi.entity.TbPolicy;

/** *//**
 * @Title:Quartz管理类
 * 
 * @Description:
 * 
 * @Copyright: 
 * @version 1.00.000
 *
 */
public class QuartzManager {
	private static final Logger logger = Logger.getLogger(QuartzManager.class);
	/**
	 * ipmi项目添加一个启动策略方法
	 * @param jobDetail
	 * @param tbPolicy
	 * @param scheduler
	 * @return
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	public static boolean startjob(TbPolicy tbPolicy,Scheduler scheduler) throws ParseException, SchedulerException{
		//看看这条策略是否已经具备一个任务
		JobDetail jobDetail=scheduler.getJobDetail(tbPolicy.getId()+"0", "policyGroup");//开启策略JOB
		if(jobDetail==null){
			//如果为空就创建一个JOB
			JobDetail newjob = new JobDetail(tbPolicy.getId()+"0", "policyGroup", PolicyOpen.class);//任务名，任务组，任务执行类
			//触发器
			CronTrigger trigger = new CronTrigger(tbPolicy.getId()+"Trigger0","triggerGroup");
			String time="0 "+tbPolicy.getPolicyStartMinute()+" "+tbPolicy.getPolicyStartHour() +" * * ?";
			trigger.setCronExpression(time);
			scheduler.scheduleJob(newjob,trigger);
			if(!scheduler.isShutdown())
				scheduler.start();
			logger.info("新建策略startjob"+jobDetail.getName());
			return true;
		}else{
			//如果不为空就比较一下时间点是否有变更
			Trigger trigger = scheduler.getTrigger(tbPolicy.getId()+"Trigger0","triggerGroup");
			Date tempdate = trigger.getNextFireTime();
//			System.out.println(trigger.toString());
			Calendar calendar = GregorianCalendar.getInstance(); 
			calendar.setTime(tempdate);
//			System.out.println("===========打印下时间看看===============");
//			System.out.println(Integer.parseInt(tbPolicy.getPolicyStartHour()));
//			System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
//			System.out.println(Integer.parseInt(tbPolicy.getPolicyStartMinute()));
//			System.out.println(calendar.get(Calendar.MINUTE));
			if(Integer.parseInt(tbPolicy.getPolicyStartHour())==calendar.get(Calendar.HOUR_OF_DAY)&&
					Integer.parseInt(tbPolicy.getPolicyStartMinute())==calendar.get(Calendar.MINUTE)){ 
				logger.info(tbPolicy+"0开启策略任务时间没变更");
				return false;
			}else{
				//重新绑定job
				CronTrigger ct = (CronTrigger)trigger;
				//修改时间
				String time="0 "+tbPolicy.getPolicyStartMinute()+" "+tbPolicy.getPolicyStartHour() +" * * ?";
				ct.setCronExpression(time);
				//重启触发器
				scheduler.resumeTrigger(tbPolicy.getId()+"Trigger0","triggerGroup");
				logger.info("重新绑定时间startjob,jobName:"+jobDetail.getName());
			}
		}
		return true;
	}
	
	/**
	 * ipmi项目添加一个关闭策略方法
	 * @param jobDetail
	 * @param tbPolicy
	 * @param scheduler
	 * @return
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	public static boolean closejob(TbPolicy tbPolicy,Scheduler scheduler) throws ParseException, SchedulerException{
		JobDetail jobDetail=scheduler.getJobDetail(tbPolicy.getId()+"1", "policyGroup");//关闭策略JOB
		if(jobDetail==null){
			//如果为空就创建一个JOB
			JobDetail newjob = new JobDetail(tbPolicy.getId()+"1", "policyGroup", PolicyClose.class);//任务名，任务组，任务执行类
			//触发器
			CronTrigger trigger = new CronTrigger(tbPolicy.getId()+"Trigger1","triggerGroup");
			String time="0 "+tbPolicy.getPolicyStartMinute()+" "+tbPolicy.getPolicyStartHour() +" * * ?";
			trigger.setCronExpression(time);
			scheduler.scheduleJob(newjob,trigger);
			if(!scheduler.isShutdown())
				scheduler.start();
			logger.info("新建策略closejob"+jobDetail.getName());
			return true;
		}else{
			//如果不为空就比较一下时间点是否有变更
			Trigger trigger = scheduler.getTrigger(tbPolicy.getId()+"Trigger1","triggerGroup");
			Date tempdate = trigger.getNextFireTime();
			Calendar calendar = GregorianCalendar.getInstance(); 
			calendar.setTime(tempdate);
			if(Integer.parseInt(tbPolicy.getPolicyStartHour())==calendar.get(Calendar.HOUR_OF_DAY)&&
					Integer.parseInt(tbPolicy.getPolicyStartMinute())==calendar.get(Calendar.MINUTE)){ 
				logger.info(tbPolicy+"1禁止策略任务时间没变更");
				return false;
			}else{
				//重新绑定job
				CronTrigger ct = (CronTrigger)trigger;
				//修改时间
				String time="0 "+tbPolicy.getPolicyStartMinute()+" "+tbPolicy.getPolicyStartHour() +" * * ?";
				ct.setCronExpression(time);
				//重启触发器
				scheduler.resumeTrigger(tbPolicy.getId()+"Trigger1","triggerGroup");
				logger.info("重新绑定时间closejob,jobName:"+jobDetail.getName());
			}
		}
		return true;
	}
}

