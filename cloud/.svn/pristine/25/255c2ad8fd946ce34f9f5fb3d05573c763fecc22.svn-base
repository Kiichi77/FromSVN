package cn.com.kxcomm.task.dao;

import java.util.List;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.task.base.CommonDAO;


public class TbServerDAO extends CommonDAO<TbServer> {
	/**
	 * 
	 * 返回黑名单以外有效的服务器信息
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-8-29
	 * @since ipmi_task
	 */
	@SuppressWarnings("rawtypes")
	public List queryNormalServer(){
		List list =  this.getSession().createQuery("from TbServer tbserver where tbserver.id not in (select distinct serverId from TbBlackName) ").list();
		return list;
	}
}
