package cn.com.kxcomm.test;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;

public class PolicyTest {
	private SerInfo ser = new SerInfo();
	public PolicyTest(){
		ser.setHost("192.168.1.26");
		ser.setUser("ipmi");
		ser.setPasswd("123456");
	}
	Policy aa = Policy.getSingleInstance();
	int policyId =9;
	int component = Parameter.domainSystem;
	

	/**
	 * 
	* 添加策略
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	//@Test
	public void addPolicy() {
		PolicyEntity p = new PolicyEntity();
		p.setPolicyId(policyId);
		p.setPolicyType(Parameter.policyPower);
		p.setPolicyEnabled(false);
		p.setComponent(component);
		p.setSendAlert(true);
		p.setShutdown(false);
		p.setStatReportingPeriod(10);
		p.setCorrectionTime(1000L);
		p.setPolicyTriggerLimit(400);
		p.setPolicyLimit(400);
		boolean is = aa.setPolicy(ser,p);
		System.out.println("创建结果：" + is);
	}
	
	/**
	 * 
	* 获取某个策略
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	//@Test
	public void getPolicyById(){
		PolicyEntity cc = aa.getPolicyById(ser,policyId, component);
		if (cc != null) {
			System.out.println(cc.toString());
		} else {
			System.out.println("无此策略");
		}
	}
	
	/**
	 * 
	* 设置策略状态
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void setPolicyStatus(){
		boolean cc = aa.setPolicyStatus(ser,policyId, false, component);
		if (cc ) {
			System.out.println("sucess");
		} else {
			System.out.println("faild");
		}
	}
	
	/**
	 * 
	* 删除某个策略
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void delPolicyById(){
		boolean cc = aa.removePolicyID(ser,policyId, component);
		if (cc ) {
			System.out.println("sucess");
		} else {
			System.out.println("faild");
		}
	}
	
	/**
	 * 
	* 删除所有的策略
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void delAllPolicy(){
		 aa.removeAllPolicy(ser);
		
	}
	
	@Test
	public void  queryAllPolicy(){
		 List<PolicyEntity> ll = aa.queryAllPolicy(ser);
		 for(PolicyEntity a : ll){
			 System.out.println(a.toString());
		 }
	}
	
}
