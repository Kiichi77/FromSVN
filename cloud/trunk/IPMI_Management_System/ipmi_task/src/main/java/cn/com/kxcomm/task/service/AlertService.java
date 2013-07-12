package cn.com.kxcomm.task.service;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.memcached.CacheClientImpl;
import cn.com.kxcomm.ipmitool.Power;
import cn.com.kxcomm.ipmitool.Sensor;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.task.dao.AlertDAO;
import cn.com.kxcomm.task.util.Config;
import cn.com.kxcomm.task.util.SendMail;

/**
 * 
 * 警报功能
 * <p>
 * 1.查询所有警报队列<br/>
 * 2.判断警告开关、严重开关是否打开<br/>
 * 3.如果都关闭则跳过,执行下一个报警<br/>
 * 4.假设warningEnable或seriousEnable为打开<br/>
 * 5.获取绑定了该报警的服务器列表<br/>
 * 6.如果无绑定任务服务器则跳过<br/>
 * 7.从缓存中判断今天的警告邮件或严重邮件次数是否超过3次<br/>
 * 8.如果超过3次，则跳出循环<br/>
 * 9.未超过发送邮件次数,有服务器则循环操作，根据筛选器类型查询对应的值<br/>
 * 10.判断当前值是否大于警告阀值，小于则不处理<br/>
 * 11.大于当前值，则保存发送警告或严重邮件的内容<br/>
 * 12.直到把该报警绑定的服务器都处理完<br/>
 * 12.判断邮件的内容是否为空，为空则跳出<br/>
 * 13.如果不为空则保存发送邮件的次数到缓存中，把报警结果发送到用户邮箱里，设置缓存时间为1天<br/>
 * </p>
 * @author zhangjh 新增日期：2012-8-30
 * @since ipmi_task
 */
@Service("alertService")
public class AlertService {
	@Autowired(required = true)
	private AlertDAO alertDAO;
	@Autowired(required = true)
	private CacheClientImpl cache;
	private Power power = Power.getSingleInstance();
	private Sensor sensor = Sensor.getSingleInstance();
	private static final Logger logger = Logger.getLogger(AlertService.class);  
	private SendMail mail = SendMail.getSingleInstance();
	private CompositeConfiguration conf = Config.getConfig();
	
	/**
	 * 
	 * 警报，达到阀值就发送邮件
	 * 
	 * @author zhangjh 新增日期：2012-8-30
	 * @since ipmi_task
	 */
	public void alert2SendMail() {
		int sendNum = 3;
		long dayTimeMs = 24*60*60*1000;
		List<Object[]> alertList = alertDAO.queryAlert();
		for(Object[] objAlert :alertList){
			AlertEntity alert = (AlertEntity) objAlert[0];
			TbUser user = (TbUser) objAlert[1];
			// 1.判断开关是否都打开
			if(!checkEnable(alert)){
				continue;
			}
			String warningKey = "alert2SendMail_Warning_"+user.getId()+"_"+alert.getAlertId();
			String seriousKey = "alert2SendMail_Serious_"+user.getId()+"_"+alert.getAlertId();
			/**
			 * 已经发送邮箱的名单(避免重复发送邮件)
			 * 如果缓存系统重启后，会丢失，则会重新发送3次
			 */
			boolean isWarningToDO = false;
			boolean isSeriousToDO = false;
			int iWarningCount = 0;
			int iSeriousCount = 0;
			// 2.判断邮件是否超过3次
			if(alert.getWarningEnable()==1){
				try{
					int count = (Integer) cache.get(warningKey);
					iWarningCount = count;
					if(count<sendNum){
						isWarningToDO = true;
					}
				}catch(NullPointerException e){
					isWarningToDO = true;
				}
			}
			if(alert.getSeriousEnable()==1){
				try{
					int count = (Integer) cache.get(seriousKey);
					iSeriousCount = count;
					if(count<sendNum){
						isSeriousToDO = true;
					}
				}catch(NullPointerException e){
					isSeriousToDO = true;
				}
			}
			if(!isWarningToDO && !isSeriousToDO){
				continue;
			}
			// 3.判断是否有绑定服务器
			List<TbServer> serverList = alertDAO.queryAlertServer(alert.getAlertId());
			if(serverList==null || serverList.size()<=0){
				continue;
			}
			//邮件主体
			StringBuilder warningBody = null;
			StringBuilder seriousBody = null;
			String filterName = getFilterName(alert.getFilterType());
			for(TbServer server :serverList){
				int current = getReading(alert.getFilterType(), server);
				if(isWarningToDO && current>=alert.getWarningNum()){
					if(warningBody==null)
						warningBody = new StringBuilder();
					warningBody.append("服务器"+server.getIdracIp()+"的"+filterName+"已经超出警告阀值,当前值为["+current+"],阀值为["+alert.getWarningNum()+"],已超出["+(current-alert.getWarningNum())+"],请检查服务器是否正常。\n");
				}
				if(isSeriousToDO && current>=alert.getSeriousNum()){
					if(seriousBody==null)
						seriousBody = new StringBuilder();
					seriousBody.append("服务器"+server.getIdracIp()+"的"+filterName+"已经超出严重阀值,当前值为["+current+"],阀值为["+alert.getWarningNum()+"],已超出["+(current-alert.getWarningNum())+"],请检查服务器是否正常。\n");
				}
			}
			if(warningBody!=null){
				sendMail(user.getEmail(),"来自IPMI服务器节能管理系统(报警<"+alert.getAlertId()+">)发出的警告邮件",warningBody.toString());
				++iWarningCount;
				cache.set(warningKey,iWarningCount ,dayTimeMs);
			}
			if(seriousBody!=null){
				sendMail(user.getEmail(),"来自IPMI服务器节能管理系统(报警<"+alert.getAlertId()+">)发出的严重邮件",seriousBody.toString());
				++iSeriousCount;
				cache.set(seriousKey, iSeriousCount,dayTimeMs);
			}
		}
	}
	
	/**
	 * 
	* 返回筛选器类型的名字(1:系统功耗,2:出风口温度,3.入风口温度,4.CPU功耗,5.内存功耗)
	* @param filter
	* @return
	* @author zhangjh 新增日期：2012-9-20
	* @since ipmi_task
	 */
	private String getFilterName(int filter){
		String name = "";
		switch (filter) {
		case 1:
			name = "系统功耗";
			break;
		case 2:
			name = "出风口温度";
			break;
		case 3:
			name = "入风口温度";
			break;
		case 4:
			name = "CPU功耗";
			break;
		case 5:
			name = "内存功耗";
			break;
		}
		return name;
	}
	/**
	 * 
	 * 检测是否已打开
	 * 
	 * @param e
	 * @return
	 * @author zhangjh 新增日期：2012-9-19
	 * @since ipmi_task
	 */
	private boolean checkEnable(AlertEntity e){
		if(e.getSeriousEnable()==1 || e.getWarningEnable()==1)
			return true;
		else
			return false;
	}
	/**
	 * 
	 * 获取当前功耗或温度
	 * 
	 * @param alertType 1:系统功耗,2:出风口温度,3.入风口温度,4.CPU功耗,5.内存功耗
	 * @param server
	 * @return
	 * @author zhangjh 新增日期：2012-8-30
	 * @since ipmi_task
	 */
	private int getReading(int alertType,TbServer server){
		int reading = 0;
		SerInfo ser = new SerInfo();
		ser.setHost(server.getIdracIp());
		ser.setPasswd(server.getIdracPassword());
		ser.setUser(server.getIdracUser());
		if(alertType == 1){
			PowerReading cc = power.getPlatformPowerReading(ser, Parameter.domainSystem);
			reading = cc.getCurrent();
		}else if(alertType == 2){
			GobblerServer gobblerServer = new GobblerServer(conf.getString("gobbler.host"),conf.getString("gobbler.user"),conf.getString("gobbler.passwd"));
			reading = sensor.getTempSensorReading(ser, gobblerServer, Parameter.ExhaustTemp);
		}else if(alertType == 3){
			GobblerServer gobblerServer = new GobblerServer(conf.getString("gobbler.host"),conf.getString("gobbler.user"),conf.getString("gobbler.passwd"));
			reading = sensor.getTempSensorReading(ser, gobblerServer, Parameter.InletTemp);
		}else if(alertType == 4){
			PowerReading cc = power.getPlatformPowerReading(ser, Parameter.domainCpu);
			reading = cc.getCurrent();
		}else if(alertType == 5){
			PowerReading cc = power.getPlatformPowerReading(ser, Parameter.domainMemory);
			reading = cc.getCurrent();
		}
		return reading;
	}
	
	/**
	 * 
	 * 发送邮件
	 * 
	 * @param tofrom
	 * @param body
	 * @author zhangjh 新增日期：2012-8-30
	 * @since ipmi_task
	 */
	private void sendMail(String tofrom,String title ,String body){
		String mailfrom = conf.getString("mail.from");
		String mailsmtp = conf.getString("mail.smtp");
		String username = conf.getString("mail.user");
		String password = conf.getString("mail.passwd");
		mail.sendMail(mailfrom,new String[]{tofrom}, title, body,mailsmtp, username, password);
	}
}
