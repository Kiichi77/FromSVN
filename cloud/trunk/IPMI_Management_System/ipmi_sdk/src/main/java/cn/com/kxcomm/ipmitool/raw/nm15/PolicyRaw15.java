package cn.com.kxcomm.ipmitool.raw.nm15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.PolicyRaw;
import cn.com.kxcomm.ipmitool.util.Util;

public class PolicyRaw15 extends PolicyRaw {
	public PolicyRaw15(SerInfo ser) {
		super(ser);
	}

	private static final Logger log = Logger.getLogger(PolicyRaw15.class);
	@Override
	public boolean setPolicyStatus( int policyId, boolean status,int component) {
		String s_status = "0x01";
		if (!status)
			s_status = "0x00";
		
		String domain =Util.getDomain(component);
		String raw = " raw 0x2e 0xc0 0x57 0x01 0x00 " + s_status + " "+domain+" 0x" + Util.int2Hex(policyId);
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + raw;
		log.info("command:" + command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PolicyEntity entity = getPolicyById(policyId,component);
		if(status != entity.isPolicyEnabled()){
			log.info("result:设置策略状态失败"+policyId);
			return false;
		}
		log.info("result:设置策略状态成功"+policyId+"为"+status);
		return true;
	}
	
	@Override
	public boolean removePolicyID(int policyId, int component) {
		if(policyId<=5||policyId==13)
			return false;
		PolicyEntity entity = getPolicyById(policyId,component);
		if(entity==null)
			return true;
		if(entity.isPolicyEnabled()){
			setPolicyStatus(policyId, false,component);
		}
		String domain =Util.getDomain(component);
		String raw = " raw 0x2e 0xc1 0x57 0x01 0x00 "+domain+" 0x"+Util.int2Hex(policyId)+" 0x00 0x03 0xC8 0x00 0x70 0x17 0x00 0x00 0x00 0x00 0x01 0x00";
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + raw;

		log.info("command:" + command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		entity = getPolicyById(policyId,component);
		if(entity!=null){
			log.info("result:删除策略失败:"+policyId);
			return false;
		}else{
			log.info("result:删除策略成功:"+policyId);
		}
		return true;
	}

}
