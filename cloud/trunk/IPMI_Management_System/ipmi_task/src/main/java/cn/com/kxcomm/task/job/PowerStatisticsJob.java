package cn.com.kxcomm.task.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.task.service.PowerStatisticsService;

/**
 * 
* 功能描述:功耗分析数据统计任务
* @author chenl 新增日期：2012-9-20
* @since ipmi_task
 */
@Service("powerStatisticsJob")
public class PowerStatisticsJob {

	private static final Logger log = Logger.getLogger(PowerStatisticsJob.class);
	
	@Autowired(required=true)
	private PowerStatisticsService powerStatsticsService;
	
	public void work(){
		log.info("开始功耗统计."+System.currentTimeMillis());
		long startTime = System.currentTimeMillis();
		powerStatsticsService.powerStatitics();
		log.info("结束功耗统计.userd time："+(System.currentTimeMillis()-startTime));
	}
}
