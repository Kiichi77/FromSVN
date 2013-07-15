

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.service.PolicyServerService;
import cn.com.kxcomm.ipmi.service.PolicyService;
import cn.com.kxcomm.ipmi.vo.PolicyServerEntity;

/**
 * 
* 功能描述：策略测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class PolicyTest {
	
	private static Logger log = Logger.getLogger(PolicyTest.class);
	private ApplicationContext ctx;
	private PolicyService policyService;
	private PolicyServerService policyServerService;
	
	public PolicyTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		policyService = (PolicyService) ctx.getBean("policyService");
		policyServerService = (PolicyServerService) ctx.getBean("policyServerService");
	}
	
	/**
	 * 
	* 方法用途和描述: 修改策略
	* @author chenl 新增日期：2012-8-22
	* @since ipmi_web
	 */
	public void updateSetPolicy(){
		TbPolicy tbpolicy =  new TbPolicy();
		tbpolicy.setId(19L);
		//修改策略，需要调用张建华那边的接口，同步发送命令修改每台服务器的策略。
		//根据策略id查找管理服务器的服务器策略id
		Long id = tbpolicy.getId();
		List<TbPolicySever> serverPolicyMan = policyServerService.findByPolicyId(id);
		log.info("serverPolicyMan:"+serverPolicyMan.size());
//		//调用张建华更新服务器策略的接口，只能修改阀值，所以需要把tbpolicy传入过去,serverPolicyMan是策略与策略id与服务器之间的关系数据
//		boolean flat = policyService.updateSetPolicy(serverPolicyMan,tbpolicy);
//		log.debug("call interface is "+flat);
//		//接口更新成功，修改本地数据库
//		if(flat){
//			policyService.update(tbpolicy);
//		}else{
//			log.debug("local database update error.call interface is "+flat);
//		}
	}
	
	
	/**
	 * 
	* 方法用途和描述: 保存
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public void save(){
		for (int i = 0; i < 10; i++) {
			TbPolicy policy = new TbPolicy();
			policy.setPolicyName("功耗策略"+i);
//			policy.setPolicyStartTime(new Time(new Date().getTime()));
//			policy.setPolicyEndTime(new Time(new Date().getTime()));
			policy.setPolicyType((short) (1+i));
			policy.setPolicyLimit(100+i);
			policy.setCommand("abc"+i);
			policy.setPolicyTrigger("cpu"+i);
			policy.setDomain("aa"+i);
			policyService.save(policy);
		}
	}
	
	/**
	 * 策略分页查询
	 */
	public void findByPage(){
		TbPolicy entity = new TbPolicy();
//		entity.setPolicyType((short) 1);
//		entity.setPolicyName("功耗");
		Page<TbPolicy> pageInfo = new Page<TbPolicy>();
		pageInfo.setPageSize(10);
		pageInfo.setPageNo(2);
		pageInfo.setStart(10);
		pageInfo.setEnd(19);
		Page<TbPolicy> page = policyService.findByPage(entity, pageInfo);
		log.info("pagePolicy:"+Json.toJson(page));
	}
	
	/**
	 * 
	* 方法用途和描述: 设置策略与服务器之间的关系
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public void setPolicy(){
		Long[] serverId = {1L,2L,3L};
		PolicyServerEntity policyServerEntity = new PolicyServerEntity();
		policyServerEntity.setPolicyId(1L);
		policyServerEntity.setBiningType(12);
		policyServerEntity.setBiningState(2);
		policyServerEntity.setBiningServerType(1);
		policyServerEntity.setServerId(serverId);
		policyService.saveOrUpdateSetPolicy(policyServerEntity);
		Long[] serverId1 = {2L,3L,4L};
		PolicyServerEntity policyServerEntity1 = new PolicyServerEntity();
		policyServerEntity1.setPolicyId(2L);
		policyServerEntity1.setBiningType(13);
		policyServerEntity1.setBiningState(2);
		policyServerEntity1.setBiningServerType(1);
		policyServerEntity1.setServerId(serverId1);
		policyService.saveOrUpdateSetPolicy(policyServerEntity1);
	}
	
}

