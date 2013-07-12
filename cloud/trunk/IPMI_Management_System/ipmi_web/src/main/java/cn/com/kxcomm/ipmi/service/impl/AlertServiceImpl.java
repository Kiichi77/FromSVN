package cn.com.kxcomm.ipmi.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.AlertDAO;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.AlertService;
import cn.com.kxcomm.ipmi.vo.AlertVO;

/**
 * 
* 功能描述:告警数据业务层
* @author chenl 新增日期：2012-8-30
* @since ipmi_web
 */
@Service("alertService")
public class AlertServiceImpl extends CommonServiceImpl<AlertEntity> implements
		AlertService {
	
	private static final Logger log = Logger.getLogger(AlertServiceImpl.class);
	
	@Autowired(required=true)
	private AlertDAO alertDAO;
	
	
	/**
	 * 分页重写
	 */
	@Override
	public Page<AlertVO> findByPage(AlertVO entity,Page<AlertEntity> pageInfo) {
		Page<AlertVO> page = new Page<AlertVO>();
		StringBuffer hql = new StringBuffer();
		hql.append("select ta.alert_id,ta.filter_type,ta.serious_enable,ta.serious_num,ta.warning_enable,ta.warning_num,tu.accouont,tu.id,tu.email,tu.phone_number,ts.server_ip_in " +
				" from tb_alert ta,tb_alert_server tas,tb_user tu,tb_server ts " +
				"  where ta.alert_id = tas.alert_id and tas.server_id = ts.id and ta.userid = tu.id ");
		if(!BlankUtil.isBlank(entity.getFilterType()) && -1!=entity.getFilterType()){
			hql.append(" and ta.filter_type=? ");
		}
		if(-1!=entity.getEnable()){
			hql.append(" and (ta.warning_enable=? or ta.serious_enable=?) ");
		}
		if(!BlankUtil.isBlank(entity.getTbServer())){
			if(!BlankUtil.isBlank(entity.getTbServer().getServerIpIn()) && !"".equals(entity.getTbServer().getServerIpIn())){
				hql.append(" and ts.server_ip_in like '%"+entity.getTbServer().getServerIpIn()+"%'");
			}
		}
		if(!BlankUtil.isBlank(entity.getTbUsers())){
			if(!BlankUtil.isBlank(entity.getTbUsers().getAccouont())){
				hql.append(" and tu.accouont like '%"+entity.getTbUsers().getAccouont()+"%'");
			}
		}
		String countHql = "select count(*) as count from ("+hql.toString()+") tt ";
		int count = alertDAO.findCount(countHql, entity.getFilterType(),entity.getEnable(),entity.getEnable());
		log.debug("count:"+count);
		List<AlertVO> alertList = new ArrayList<AlertVO>();
		List list = alertDAO.findByPage(hql.toString(), pageInfo, entity.getFilterType(),entity.getEnable(),entity.getEnable());
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			AlertVO alertvo = new AlertVO();
			alertvo.setAlertId((Integer) obj[0]);
			alertvo.setFilterType((Integer) obj[1]);
			alertvo.setSeriousEnable((Short) obj[2]);
			alertvo.setSeriousNum((Integer) obj[3]);
			alertvo.setWarningEnable((Short) obj[4]);
			alertvo.setWarningNum((Integer) obj[5]);
			TbUser user = new TbUser();
			user.setAccouont(BlankUtil.isNull(obj[6]));
			user.setId(Long.parseLong(((BigInteger) obj[7]).toString()));
			user.setEmail(BlankUtil.isNull(obj[8]));
			user.setPhoneNumber(BlankUtil.isNull(obj[9]));
			alertvo.setTbUsers(user);
			TbServer server = new TbServer();
			server.setServerIpIn(BlankUtil.isNull(obj[10]));
			alertvo.setTbServer(server);
			alertList.add(alertvo);
		}
		page.setTotalCount(count);
		page.setResult(alertList);
		return page;
	}

	/**
	 * 
	* 方法用途和描述: 修改
	* @param alertvo
	* @return
	* @author chenl 新增日期：2012-8-31
	* @since ipmi_web
	 */
	@Override
	public boolean updateAlert(AlertVO alertvo){
		StringBuffer hql = new StringBuffer("update AlertEntity set filterType = ?,serious_num=?,warning_num=?,userid=? where alertId=?");
		int result = alertDAO.executeByHQL(hql.toString(), alertvo.getFilterType(),alertvo.getSeriousNum(),alertvo.getWarningNum(),Integer.parseInt(alertvo.getTbUsers().getId().toString()),alertvo.getAlertId());
		return result>0?true:false;
	}

	@Override
	public CommonDAO<AlertEntity> getBindDao() {
		return alertDAO;
	}

}
