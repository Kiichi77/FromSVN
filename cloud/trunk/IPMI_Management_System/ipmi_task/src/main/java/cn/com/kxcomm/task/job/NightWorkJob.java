package cn.com.kxcomm.task.job;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.task.service.PowerStatisticsService;
import cn.com.kxcomm.task.service.SelLogService;

/**
 * 
 * 每天凌晨1点半执行
 * 
 * @author zhangjh 新增日期：2012-9-4
 * @since ipmi_task
 */
@Service("selLogJob")
public class NightWorkJob {
	@Autowired(required = true)
	private SelLogService selLogService;
	@Autowired(required = true)
	private PowerStatisticsService statisticsService ;
	
	public void work(){
		try {
			selLogService.collectionSelLog();
			statisticsService.powerStatitics();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
