package cn.com.kxcomm.task.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.task.service.AlertService;

/**
 * 
 * 警告任务
 * 
 * @author zhangjh 新增日期：2012-8-30
 * @since ipmi_task
 */
@Service("alertJob")
public class AlertJob {
	@Autowired(required = true)
	private AlertService alertService;
	
	public void work(){
		alertService.alert2SendMail();
	}
}
