package cn.com.kxcomm.task.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.task.service.PolicyService;

/**
 * 
 * 策略任务管理
 * 
 * @author zhangjh 新增日期：2012-9-5
 * @since ipmi_task
 */
@Service("policyJob")
public class PolicyJob {
	@Autowired(required = true)
	private PolicyService policyService;
	
	public void work(){
		policyService.run();
	}
}
