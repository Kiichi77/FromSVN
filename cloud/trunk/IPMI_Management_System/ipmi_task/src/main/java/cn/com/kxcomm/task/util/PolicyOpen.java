package cn.com.kxcomm.task.util;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.task.dao.PolicyServerDAO;

public class PolicyOpen implements Job{
	@Autowired(required = true)
	private PolicyServerDAO policyServerDAO;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Policy aa = Policy.getSingleInstance();
		//扫描tb_server_policy表;(条件为改任务的JOBID,JOBID对应策略ID)
		String policyid=context.getJobDetail().getName().substring(0,context.getJobDetail().getName().length()-1);
		TbPolicySever tbPolicySever=new TbPolicySever();
		TbPolicy tbPolicy=new TbPolicy();
		tbPolicy.setId(Long.parseLong(policyid));
		tbPolicySever.setTbPolicy(tbPolicy);
		List<TbPolicySever> tbPolicySeverList= policyServerDAO.findByExample(tbPolicySever);
		for(TbPolicySever policySever:tbPolicySeverList){
			//发启动任务命令儿;
			SerInfo ser = new SerInfo();
			ser.setHost(policySever.getTbServer().getIdracIp());
			ser.setUser(policySever.getTbServer().getIdracUser());
			ser.setPasswd(policySever.getTbServer().getIdracPassword());
			int domain=0;
			if(policySever.getTbPolicy().getPolicyType()==1||policySever.getTbPolicy().getPolicyType()==4)domain=Parameter.domainSystem;
			if(policySever.getTbPolicy().getPolicyType()==2)domain=Parameter.domainCpu;
			if(policySever.getTbPolicy().getPolicyType()==3)domain=Parameter.domainMemory;
			//把策略查出来
			PolicyEntity p = aa.getPolicyById(ser, policySever.getPolicyidInServer(), domain);
			//把策略打开
			p.setPolicyEnabled(true);
			boolean flag=aa.setPolicy(ser, p);
			//修改数据库关系表的启动状态为1
			policySever.setBiningState(1);
			if(flag)policyServerDAO.saveOrUpdate(policySever);
		}
	}
}
