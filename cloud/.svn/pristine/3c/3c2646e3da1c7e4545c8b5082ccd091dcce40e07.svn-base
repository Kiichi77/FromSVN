package cn.com.kxcomm.ipmi.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.SystemOperLogDAO;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.SystemOperLogService;
import cn.com.kxcomm.ipmi.vo.SystemoperlogEntity;

/**
 * 系统操作日志业务逻辑处理类
 * @author chenliang
 *
 */
@Service("systemOperLogService")
public class SystemOperLogServiceImpl extends CommonServiceImpl<TbSystemoperlog> implements SystemOperLogService {
	
	private static Logger log = Logger.getLogger(SystemOperLogServiceImpl.class);
	
	@Autowired(required=true)
	public SystemOperLogDAO systemOperLogDAO;
	
	@Override
	public CommonDAO<TbSystemoperlog> getBindDao() {
		return systemOperLogDAO;
	}

	/**
	 * 重新父类分页方法
	 * params:entity.getDateTime操作时间，entity.getTbUser().getAccouont操作者
	 * return:返回pagelist集合
	 */
	@Override
	public Page<SystemoperlogEntity> findByPageEntity(SystemoperlogEntity entity,Page<TbSystemoperlog> pageInfo) {
		Page<SystemoperlogEntity> pagelist = new Page<SystemoperlogEntity>();
		//查找所有lists
		StringBuffer hql = new StringBuffer();
		hql.append(" select tu.accouont,tu.id as userId,date_format(ts.date_time,'%Y-%m-%d %H:%i:%s') as date_time,ts.id,ts.operation_result,ts.operation_type,ts.param,ts.note,ts.ip "+
				   " from tb_systemoperlog ts,tb_user tu"+
				   " where ts.admin_id = tu.id ");
		// 拼接条件
		if(!BlankUtil.isBlank(entity.getStartTime())){
			hql.append(" and date_format(ts.date_time,'%Y-%m-%d')>=date_format('"+entity.getStartTime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(entity.getEndTime())){
			hql.append(" and date_format(ts.date_time,'%Y-%m-%d')<=date_format('"+entity.getEndTime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(entity.getTbUser())){
			if(!BlankUtil.isBlank(entity.getTbUser().getAccouont()) && !"".equals(entity.getTbUser().getAccouont())){	
				hql.append(" and tu.accouont = ?");
			}
		}
		if(!BlankUtil.isBlank(entity.getOperationType()) && !"".equals(entity.getOperationType())){
			if("-1".equals(entity.getOperationType())){
				entity.setOperationType("");
			}else{
				hql.append(" and ts.operation_type = "+entity.getOperationType());
			}
		}
		hql.append(" order by date_time desc ");
		//查找总记录数sql
		String counthql = "select count(*) as count from ("+hql.toString()+") t ";
		log.debug("[counthql]"+counthql);
		int count = systemOperLogDAO.findCount(counthql,entity.getTbUser().getAccouont());
		log.debug("count:"+count);
		log.debug("[hql]"+hql);
		List list = systemOperLogDAO.findByPage(hql.toString(),pageInfo,entity.getTbUser().getAccouont());
		log.debug("list:"+list.toString());
		List<SystemoperlogEntity> systemLogList = new ArrayList<SystemoperlogEntity>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			TbUser user = new TbUser();
			user.setAccouont(BlankUtil.isNull(obj[0]));
			user.setId(parserLong((BigInteger) obj[1]));
			SystemoperlogEntity systemLog = new SystemoperlogEntity();
			systemLog.setDateTime(BlankUtil.isNull(obj[2]));
			systemLog.setId(parserLong((BigInteger) obj[3]));
			systemLog.setOperationResult((Short) obj[4]);
			systemLog.setOperationType(BlankUtil.isNull(obj[5]));
			systemLog.setParam(BlankUtil.isNull(obj[6]));
			systemLog.setNote(BlankUtil.isNull(obj[7]));
			systemLog.setIp(BlankUtil.isNull(obj[8]));
			systemLog.setTbUser(user);
			systemLogList.add(systemLog);
		}
		pagelist.setTotalCount(count);
		pagelist.setResult(systemLogList);
		log.debug("[pagelist]"+pagelist.toString());
		return pagelist;
	}

	/**
	 * 
	* 方法用途和描述:BigInteger类型转为Long类型
	* @param params
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	private Long parserLong(BigInteger params){
		Long result = 0L;
		if(!BlankUtil.isBlank(params)){
			result = Long.parseLong(params.toString());
		}
		return result;
	}
	
	/**
	 * 
	* 方法用途和描述:TinyInt类型转为short类型
	* @param params
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	private Short parserShort(Byte params){
		Short result = 0;
		if(!BlankUtil.isBlank(params)){
			result = params.shortValue();
		}
		return result;
	}
	
	
}
