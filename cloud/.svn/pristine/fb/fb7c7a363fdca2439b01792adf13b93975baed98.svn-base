package cn.com.kxcomm.task.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.task.service.CollectDataService;

/**
 * 
 * 采集数据任务
 * 
 * @author zhangjh 新增日期：2012-8-29
 * @since ipmi_task
 */
@Service("collectMachineInfoJob")
public class CollectMachineInfoJob {
	@Autowired(required = true)
	private CollectDataService collectDataService;
	
	public void work(){
		collectDataService.collectMachineInfo();		
	}
	
}
