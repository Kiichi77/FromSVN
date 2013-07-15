package cn.com.kxcomm.ipmitool.raw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.interfaces.IPolicyRaw;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.ipmitool.util.Util;

public class PolicyRaw implements IPolicyRaw{
	protected SerInfo ser;
	
	public PolicyRaw (SerInfo ser){
		this.ser = ser;
	}
	private static final Logger log = Logger.getLogger(PolicyRaw.class);
	@Override
	public PolicyEntity getPolicyById( int policyId,Integer component) {
		String domain =Util.getDomain(component);		
		String command = "ipmitool  -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " raw 0x2E 0xC2 0x57 0x01 0x00 "+domain+" 0x" + Util.int2Hex(policyId);
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
		String str = sb.toString();
		if ("".equals(str.trim())) {
			return null;
		}
		log.info("return:" + str);
		String[] tmp = str.split(" ");
		/*for (int i = 0; i < tmp.length; i++) {
			 System.out.println("byte["+(i+1)+"]"+tmp[i]+",下标:"+i);
		}*/
		// 解析返回值
		PolicyEntity entity = new PolicyEntity();
		entity.setPolicyId(policyId);
		// byte 5 : Policy is per policy Disabled, per domain enabled, and  globally enabled 0x60:disable,0x70:enable
		int cc = Integer.parseInt(tmp[4], 16);
		if (0x70 == cc) {
			entity.setPolicyEnabled(true);
			entity.setComponent(Parameter.domainSystem);
		} else if (0x60 == cc) {
			entity.setPolicyEnabled(false);
			entity.setComponent(Parameter.domainSystem);
		}else if (0x71 == cc) {
			entity.setPolicyEnabled(true);
			entity.setComponent(Parameter.domainCpu);
		} else if (0x61 == cc) {
			entity.setPolicyEnabled(false);
			entity.setComponent(Parameter.domainCpu);
		}else if (0x72 == cc) {
			entity.setPolicyEnabled(true);
			entity.setComponent(Parameter.domainMemory);
		} else if (0x62 == cc) {
			entity.setPolicyEnabled(false);
			entity.setComponent(Parameter.domainMemory);
		}
		// byte 6:power policy or Temperature 0x10:power 0x11:Thermal
		if (0x10 == Integer.parseInt(tmp[5], 16)) {
			entity.setPolicyType(0);
		} else if (0x11 == Integer.parseInt(tmp[5], 16)) {
			entity.setPolicyType(1);
		} else if (0x50 == Integer.parseInt(tmp[5], 16)) {
			entity.setPolicyType(2);
		} else if (0x14 == Integer.parseInt(tmp[5], 16)) {
			entity.setPolicyType(3);
		} else if (0x12 == Integer.parseInt(tmp[5], 16)) {
			entity.setPolicyType(4);
		}
		// Byte 7：Policy exception action - Send alert
		if (0x01 == Integer.parseInt(tmp[6], 16)) {
			entity.setSendAlert(true);
		}
		if (0x03 == Integer.parseInt(tmp[6], 16)) {
			entity.setShutdown(true);
			entity.setSendAlert(true);
		}
		// byte 8 ,9
		entity.setPolicyLimit(Integer.parseInt(tmp[8] + tmp[7], 16));
		// byte 13 12 11 10
		entity.setCorrectionTime(Long.parseLong(tmp[12] + tmp[11] + tmp[10]
				+ tmp[9], 16));
		// byte 14,15
		entity.setPolicyTriggerLimit(Integer.parseInt(tmp[14] + tmp[13], 16));
		// byte 16,17
		entity.setStatReportingPeriod(Integer.parseInt(
				tmp[16].trim() + tmp[15].trim(), 16));
		
		log.debug("result:"+entity.toString());
		return entity;
	}

	@Override
	public List<PolicyEntity> queryAllPolicy() {
		List<PolicyEntity> list = new ArrayList<PolicyEntity>();
		for (int i = 0; i < Parameter.maxPolicy; i++) {
			PolicyEntity entity = getPolicyById(i,Parameter.domainSystem);
			if(entity!=null){
				list.add(entity);
				continue;
			}
			entity = getPolicyById(i,Parameter.domainCpu);
			if(entity!=null){
				list.add(entity);
				continue;
			}
			entity = getPolicyById(i,Parameter.domainMemory);
			if(entity!=null){
				list.add(entity);
			}
		}
		log.info("result:"+list.size());
		return list;
	}

	@Override
	public boolean setPolicyStatus( int policyId, boolean status,int component) {
		String s_status = "0x05";
		if (!status)
			s_status = "0x04";
		
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
	public boolean removePolicyID( int policyId, int component) {
		if(policyId<=5||policyId==13)
			return false;
		PolicyEntity entity = getPolicyById(policyId,component);
		if(entity==null)
			return true;
		if(entity.isPolicyEnabled()){
			setPolicyStatus(policyId, false,component);
		}
		String domain =Util.getDomain(component);
		String raw = " raw 0x2e 0xc1 0x57 0x01 0x00 "+domain+" 0x"+Util.int2Hex(policyId)+" 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00";
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

	@Override
	public boolean setPolicy( PolicyEntity req) {
		PolicyEntity entity = getPolicyById(req.getPolicyId(),req.getComponent());

		if(entity!=null && entity.isPolicyEnabled()){
			setPolicyStatus(req.getPolicyId(), false,entity.getComponent());
			log.info("******************************修改策略******************************");
		}else{
			log.info("******************************新增策略******************************");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " raw 0x2e 0xc1 0x57 0x01 0x00 ");
		String component="0x00";
	
		if(0x00==req.getComponent()&& !req.isPolicyEnabled()){
			component="0x00";
		}else if(0x00==req.getComponent()&& req.isPolicyEnabled()){
			component="0x10";
		}else if(0x01==req.getComponent()&& !req.isPolicyEnabled()){
			component="0x01";
		}else if(0x01==req.getComponent()&& req.isPolicyEnabled()){
			component="0x11";
		}else if(0x02==req.getComponent()&& req.isPolicyEnabled()){
			component="0x02";
		}else if(0x02==req.getComponent()&& !req.isPolicyEnabled()){
			component="0x12";
		}
		
		sb.append(component);//byte 4 域
		sb.append(" 0x"+Util.int2Hex(req.getPolicyId()));//byte 5 策略ID
		String policyType = "0x10";// power policy
		if (req.getPolicyType() == 1) { 
			policyType = "0x11";// Temper policy
		} 
		sb.append(" "+policyType); //byte 6
		String send="0x00";
		if (req.isSendAlert() == true && req.isShutdown() == false)
			send = "0x01";
		else if (req.isSendAlert() == true && req.isShutdown() == true)
			send = "0x03";
		sb.append(" "+send);//byte 7
		Integer limit = Util.hex2Int(req.getPolicyLimit());
		sb.append(" 0x"+Util.firstByteInt(limit));//byte 8
		sb.append(" 0x"+Util.twoByteInt(limit));//byte 9
		
		Long correctionTime = Util.hex2Long(req.getCorrectionTime());
		sb.append(" 0x"+Util.firstByteLong(correctionTime));//byte 10
		sb.append(" 0x"+Util.twoByteLong(correctionTime));//byte 11
		sb.append(" 0x"+Util.threeByteLong(correctionTime));//byte 12
		sb.append(" 0x"+Util.fourByteLong(correctionTime));//byte 13
		
		Integer policyTriggerLimit = Util.hex2Int(req.getPolicyTriggerLimit());
		sb.append(" 0x"+Util.firstByteInt(policyTriggerLimit));//byte 14
		sb.append(" 0x"+Util.twoByteInt(policyTriggerLimit));//byte 15
		
		Integer statReportingPeriod = Util.hex2Int(req.getStatReportingPeriod());
		sb.append(" 0x"+Util.firstByteInt(statReportingPeriod));//byte 14
		sb.append(" 0x"+Util.twoByteInt(statReportingPeriod));//byte 15
		String command = sb.toString();
		log.info("command:" + command);
		Process p;
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
		
		PolicyEntity cc = getPolicyById(req.getPolicyId(),req.getComponent());
		if(cc==null)
			return false;
		if(req.isPolicyEnabled())
			setPolicyStatus(req.getPolicyId(), true,req.getComponent());
		log.info("result:新增或者修改策略："+req.toString());
		return true;
	}

	@Override
	public void removeAllPolicy() {
		List<PolicyEntity> list = queryAllPolicy();
		for(int i=0;i<list.size();i++){
			PolicyEntity e = (PolicyEntity) list.get(i);
			if(e.getPolicyId()<=5||e.getPolicyId()==13)
				continue;
			removePolicyID(e.getPolicyId(),e.getComponent());
		}
	}

}
