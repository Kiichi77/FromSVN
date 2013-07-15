package cn.com.kxcomm.ipmitool.raw;

import org.apache.commons.lang.StringUtils;

import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.nm15.PolicyRaw15;
import cn.com.kxcomm.ipmitool.raw.nm15.SystemRaw15;
import cn.com.kxcomm.ipmitool.raw.nm20.PolicyRaw20;
import cn.com.kxcomm.ipmitool.raw.nm20.SystemRaw20;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 
 * 创建对应版本的类
 * 
 * @param type
 * @return
 * @author zhangjh 新增日期：2012-9-28
 * @since ipmi_sdk
 */
public class RawCreator {
	private static SupportTool supportTool = SupportTool.getSingleInstance();
	
	public static SystemRaw creatorSystem(SerInfo ser){
		String version = supportTool.getIpmiVersion(ser);
		SystemRaw c = new SystemRaw(ser);
		if(StringUtils.equals(Parameter.NodeManager_15, version)){
			c = new SystemRaw15(ser);
		}
		if(StringUtils.equals(Parameter.NodeManager_20, version)){
			c = new SystemRaw20(ser);
		}
		return c;
	}
	
	public static PolicyRaw creatorPolicy(SerInfo ser){
		String version = supportTool.getIpmiVersion(ser);
		PolicyRaw c = new PolicyRaw(ser);
		if(StringUtils.equals(Parameter.NodeManager_15, version)){
			c = new PolicyRaw15(ser);
		}
		if(StringUtils.equals(Parameter.NodeManager_20, version)){
			c = new PolicyRaw20(ser);
		}
		return c;
	}
}
