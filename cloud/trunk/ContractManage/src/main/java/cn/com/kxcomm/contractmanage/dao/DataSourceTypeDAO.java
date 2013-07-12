package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbDataSourceType;
@Repository
public class DataSourceTypeDAO extends CommonDAO<TbDataSourceType>{
	/**
	 * 
	 * 获取所有分类的集合
	 * @author luj 新增日期：2013-2-20
	 * @param hql  执行的   
	 * @return
	 */
	public List queryAllDataSource(String hql,Object ...objects) {
		Session sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		List lista = sq.list();
		return lista;
	}

}
