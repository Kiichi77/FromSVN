package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbTemplateChecked;

@Repository
public class TemplateCheckedDAO extends CommonDAO<TbTemplateChecked> {

	private static final Logger LOGGER = Logger.getLogger(TemplateCheckedDAO.class);
	
	/**
	 * 
	 * 查询审核内容
	 * @author luj 新增日期：2013-2-28
	 * @param hql  执行的   
	 * @return
	 */
	public List queryTemplateChecked(String hql,Object ...objects) {
		Session sessionUse = this.getSession();
		Query sq = sessionUse.createQuery(hql);
		int j = 0;
		for (int i = 0; i < objects.length; i++) {
			LOGGER.info("objects:"+objects[i]);
			if(null != objects[i] && !"".equals(objects[i])){
				if(objects[i] instanceof Integer){
					sq.setInteger(j, Integer.parseInt(objects[i].toString()));
				}else if(objects[i] instanceof String){
					sq.setString(j, objects[i].toString());
				}
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}
}
