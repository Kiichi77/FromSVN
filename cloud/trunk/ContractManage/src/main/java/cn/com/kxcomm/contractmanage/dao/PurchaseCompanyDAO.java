package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
@Repository
public class PurchaseCompanyDAO extends CommonDAO<TbPurchaseCompany>{
	private static final Logger log = Logger.getLogger(PurchaseCompanyDAO.class);
	
	/**
	 * 
	 * 获取所有公司的集合
	 * @author luj 新增日期：2013-2-25
	 * @param hql  执行的   
	 * @return
	 */
	public List queryAllCompany(String hql,Object ...objects) {
		Session sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof Integer){
				sq.setInteger(0, Integer.parseInt(objects[i].toString()));
			}
		}
		List lista = sq.list();
		return lista;
	}
}
