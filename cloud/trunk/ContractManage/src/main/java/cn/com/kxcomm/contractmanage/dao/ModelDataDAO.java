package cn.com.kxcomm.contractmanage.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbModelData;
@Repository
public class ModelDataDAO extends CommonDAO<TbModelData> {
	
	/**
	 * 删除该机型模板的管理数据.
	 *
	 * @author lizl 新增日期：2012-12-18
	 * @param modelId 
	 * @param jsonBeanList
	 * @return
	 */
	public void deleteByConfigModelId(String aparamsId) {
		TbConfigModels a = new TbConfigModels();
		a.setId(Integer.parseInt(aparamsId));
		a.setType(1);
		Session session = super.getSession();
		String hql="delete from TbModelData p where p.configModel = ?";
		Query query=session.createQuery(hql);
		query.setEntity(0, a);
		query.executeUpdate();
	}
}
