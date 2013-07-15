package cn.com.kxcomm.task.service;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.Power;
import cn.com.kxcomm.ipmitool.Sensor;
import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.task.dao.PolicyDAO;
import cn.com.kxcomm.task.dao.PolicyServerDAO;
import cn.com.kxcomm.task.dao.TbServerDAO;
import cn.com.kxcomm.task.util.Config;

/**
 * 
 * 管理策略任务(包括功耗CPU内存和出风口策略)
 * 
 * @author zhangjh 新增日期：2012-9-5
 * @since ipmi_task
 */
@Service("policyService")
public class PolicyService {
	@Autowired(required = true)
	private TbServerDAO tbServerDAO;
	@Autowired(required = true)
	private PolicyDAO policyDAO;
	@Autowired(required = true)
	private PolicyServerDAO policyServerDAO;
	private static Policy ipmiPolicy = Policy.getSingleInstance();
	private static Power ipmiPower = Power.getSingleInstance();
	private static Sensor ipmiSensor = Sensor.getSingleInstance();
	private static final Logger logger = Logger.getLogger(PolicyService.class); 
	
	/**
	 * 
	 * 管理策略的开启与关闭
	 * <p>
	 * 1.查询所有的策略<br/>
	 * 2.判断是否在时间段范围内<br/>
	 * 3.在时间段范围外则把该策略关联的服务器的对应策略设置为关闭<br/>
	 * 4.在时间段范围内则判断该策略关联的服务器的阀值是否达到?<br/>
	 * 5.未超过阀值则不处理<br/>
	 * 6.超过阀值则创建/设置对应的策略的值,然后使该策略打开<br/>
	 * </p>
	 * <p>
	 * 策略分为:电源功耗策略,CPU功耗策略,内存策略,进风口温度策略<br/>
	 * 进风口温度策略逻辑:<br/>
	 * 通过传感器获取进风口温度,判断是否大于设置的阀值,如果大于则把功耗降低到当前功耗的15%,<br/>
	 * 假设当前功耗为300W,300W * 0.15 = 45W,300-45 = 255W,如此类推.
	 * </p>
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	public void run(){
		List<TbPolicy> policyList = policyDAO.findAll();
		int startHour;int startMinute;int endHour;int endMinute;
		for(TbPolicy policy : policyList){
			startHour = Integer.parseInt(policy.getPolicyStartHour());
			startMinute = Integer.parseInt(policy.getPolicyStartMinute());
			endHour = Integer.parseInt(policy.getPolicyEndHour());
			endMinute = Integer.parseInt(policy.getPolicyEndMinute());
			logger.info("----------------------------------------------------------------------------------");
			boolean isTimeRange = inTimeRange( startHour, startMinute, endHour, endMinute);
			logger.info("1.policy.id:"+policy.getId()+",policy.name:"+policy.getPolicyName()+",isTimeRange:"+isTimeRange);
			@SuppressWarnings("unchecked")
			List<Object[]> serverList = policyServerDAO.queryServerByPolicyId(policy.getId());
			logger.info("2.扫描绑定<"+policy.getPolicyName()+">的服务器列表,一共已绑定<"+serverList.size()+">台服务器.");
			if(serverList.size()<=0){
				logger.info("3.无绑定任务服务器,退出.");
				continue;
			}
			if(isTimeRange){
				//在时间段范围内的策略
				logger.info("3.策略在指定时间段范围内,裁定生效.下面开始扫描并判断阀值,若达到阀值则触发启动策略.");
				enablePolicy(serverList,policy);
			}else{
				//时间段范围外的策略
				logger.info("3.策略不在指定时间段范围内,裁定失效.下面开始扫描并关闭策略.");
				closePolicy(serverList,policy);
			}
		}
	}

	/**
	 * 
	 * 判断服务器的当前值是否大于阀值,是则打开策略,降低功耗
	 * 
	 * @param serverList
	 * @param p
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	private void enablePolicy(List<Object[]> serverList,TbPolicy p){
		for(Object[] cc :serverList){
			TbPolicySever serverPolicy = (TbPolicySever) cc[0];
			TbServer server = (TbServer) cc[1];
			SerInfo ser = new SerInfo();
			ser.setHost(server.getIdracIp());
			ser.setUser(server.getIdracUser());
			ser.setPasswd(server.getIdracPassword());
			if(p.getPolicyType()==4){
				boolean isOver = overPolicyLimit(ser ,p.getPolicyType(),p.getPolicyLimit());
				if(!isOver){
					continue;
				}
			}
			PolicyEntity policyEntity = ipmiPolicy.getPolicyById(ser, serverPolicy.getPolicyidInServer(),policyType2Domain(p.getPolicyType()));
			
			if(policyEntity!=null && p.getPolicyType()!=4 && policyEntity.getPolicyLimit()==p.getPolicyLimit() && policyEntity.isPolicyEnabled()){
				continue;
			}
			int policyLimit = p.getPolicyLimit();
			//当为入风口温度策略时,把策略阀值降低到当前功耗的百分几
			if(p.getPolicyType()==4){
				PowerReading powerReading = ipmiPower.getPlatformPowerReading(ser, policyType2Domain(p.getPolicyType()));
				policyLimit = powerReading.getCurrent() * (100-p.getPolicyBumber()) /100;
			}
			if(policyEntity!=null){
				policyEntity.setPolicyEnabled(true);
				policyEntity.setPolicyLimit(policyLimit);
				policyEntity.setPolicyTriggerLimit(policyLimit);
			}else{
				policyEntity = new PolicyEntity();
				policyEntity.setPolicyId(serverPolicy.getPolicyidInServer());
				policyEntity.setPolicyType(Parameter.policyPower);
				policyEntity.setPolicyEnabled(true);
				policyEntity.setComponent(policyType2Domain(p.getPolicyType()));
				policyEntity.setSendAlert(false);
				policyEntity.setShutdown(false);
				policyEntity.setStatReportingPeriod(10);
				policyEntity.setCorrectionTime(1000L);
				policyEntity.setPolicyTriggerLimit(policyLimit);
				policyEntity.setPolicyLimit(policyLimit);
			}
			serverPolicy.setBiningType(policyEntity.getPolicyType());
			serverPolicy.setBiningState(1);
			policyServerDAO.saveOrUpdate(serverPolicy);
			ipmiPolicy.setPolicy(ser, policyEntity);
			logger.info("5.设置["+ser.getHost()+"]的<"+p.getPolicyName()+">("+policyType2Name(p.getPolicyType())+")策略,策略ID为<"+policyEntity.getPolicyId()+">,阀值设定为<"+policyEntity.getPolicyLimit()+">,策略状态设置为开启.");
		}
		logger.info("6.启动绑定该策略的所有服务器(前提超过阀值),完毕!");
	}
	
	/**
	 * 
	 * 关闭服务器上的策略
	 * 
	 * @param serverList
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	private void closePolicy(List<Object[]> serverList,TbPolicy p){
		for(Object[] cc :serverList){
			TbPolicySever serverPolicy = (TbPolicySever) cc[0];
			TbServer server = (TbServer) cc[1];
			SerInfo ser = new SerInfo();
			ser.setHost(server.getIdracIp());
			ser.setUser(server.getIdracUser());
			ser.setPasswd(server.getIdracPassword());
			PolicyEntity e = ipmiPolicy.getPolicyById(ser, serverPolicy.getPolicyidInServer(), policyType2Domain(p.getPolicyType()));
			if(e==null){
				logger.info("4.["+ser.getHost()+"]的<"+serverPolicy.getPolicyidInServer()+">策略未建立,不必关闭.");
				continue; 
			}
			if(e.isPolicyEnabled()){
				ipmiPolicy.setPolicyStatus(ser, serverPolicy.getPolicyidInServer(), false, policyType2Domain(p.getPolicyType()));
				logger.info("4.设置["+ser.getHost()+"]的<"+p.getPolicyName()+">("+policyType2Name(p.getPolicyType())+")策略,策略ID为<"+e.getPolicyId()+">,,策略状态设置为关闭.");
			}
			if(serverPolicy.getBiningState()!=0){
				serverPolicy.setBiningType(e.getPolicyType());
				serverPolicy.setBiningState(0);
				policyServerDAO.saveOrUpdate(serverPolicy);
			}
		}
		logger.info("5.关闭绑定该策略的所有服务器,完毕!");
	}
	
	/**
	 * 
	 * 策略类型转domain
	 * 
	 * @param policyType 1: 系统  2:CPU 3:内存 4:入风口温度
	 * @return
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	private int policyType2Domain(int policyType){
		int domain = 0;
		if(policyType==2){
			domain = 1;
		}else if(policyType==3){
			domain = 2;
		}
		return domain;
	}
	
	/**
	 * 
	 * 判断是否在时间段范围内
	 * <p>
	 * 当startHour - endHour大于或等于12,则endTime加一天
	 * <p/>
	 * @param startHour
	 * @param startMinute
	 * @param endHour
	 * @param endMinute
	 * @return boolean
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	private boolean inTimeRange(int startHour,int startMinute,int endHour,int endMinute){
		Calendar nowTime = Calendar.getInstance(); //现在的时间
		Calendar startTime = Calendar.getInstance();//开始的时间
		startTime.set(Calendar.HOUR_OF_DAY, startHour);
		startTime.set(Calendar.MINUTE, startMinute);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();//结束的时间
		if(endHour<startHour){
			endTime.add(Calendar.DATE,1);
		}
		if(endHour==startHour && startMinute>=endMinute){
			endTime.add(Calendar.DATE,1);
		}
		endTime.set(Calendar.HOUR_OF_DAY, endHour);
		endTime.set(Calendar.MINUTE, endMinute);
		endTime.set(Calendar.MILLISECOND, 59);
		
		logger.info("现在的时间:"+nowTime.get(Calendar.YEAR)+"-"+nowTime.get(Calendar.MONTH)+"-"+nowTime.get(Calendar.DAY_OF_MONTH)+" "+nowTime.get(Calendar.HOUR_OF_DAY)+":"+nowTime.get(Calendar.MINUTE));
		logger.info("开始的时间:"+startTime.get(Calendar.YEAR)+"-"+startTime.get(Calendar.MONTH)+"-"+startTime.get(Calendar.DAY_OF_MONTH)+" "+startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE));
		logger.info("结束的时间:"+endTime.get(Calendar.YEAR)+"-"+endTime.get(Calendar.MONTH)+"-"+endTime.get(Calendar.DAY_OF_MONTH)+" "+endTime.get(Calendar.HOUR_OF_DAY)+":"+endTime.get(Calendar.MINUTE));
	
		if (nowTime.compareTo(startTime) >= 0 && nowTime.compareTo(endTime) <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否超出策略阀值
	 * 
	 * @param policyType 1:系统功耗,2:CPU功耗,3:内存功耗,4:进风口温度
	 * @param policyLimt 阀值
	 * @return
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	private boolean overPolicyLimit(SerInfo ser ,int policyType,int policyLimt) {
		int current = 0;
		if(policyType == 4){
			CompositeConfiguration conf = Config.getConfig();
			GobblerServer gobblerServer = new GobblerServer(conf.getString("gobbler.host"),conf.getString("gobbler.user"),conf.getString("gobbler.passwd"));
			current = ipmiSensor.getTempSensorReading(ser,gobblerServer,Parameter.InletTemp);
		}else{
			PowerReading p = ipmiPower.getPlatformPowerReading(ser, policyType2Domain(policyType));
			current = p.getCurrent();
		}
		if(current>=policyLimt){
			logger.info("4.["+ser.getHost()+"]的当前"+policyType2Name(policyType)+"为<"+current+">,阀值为<"+policyLimt+">,已超过阀值.");
			return true;
		}
		else{
			logger.info("4.["+ser.getHost()+"]的当前"+policyType2Name(policyType)+"为<"+current+">,阀值为<"+policyLimt+">,未超过阀值,退出.");
			return false;
		}
		
	}
	
	/**
	 * 
	 * 转换TbPolicy.policyType的类型名字
	 * @param type 1: 系统  2:CPU 3:内存 4:入风口温度
	 * @return
	 * @author zhangjh 新增日期：2012-9-7
	 * @since ipmi_task
	 */
	private String policyType2Name(int type){
		String name = "未知策略";
		switch (type) {
		case 1:
			name = "系统功耗";
			break;
		case 2:
			name = "CPU功耗";
			break;
		case 3:
			name = "内存功耗";
			break;
		case 4:
			name = "入风口温度";
			break;
		default:
			break;
		}
		return name;
	}
}
