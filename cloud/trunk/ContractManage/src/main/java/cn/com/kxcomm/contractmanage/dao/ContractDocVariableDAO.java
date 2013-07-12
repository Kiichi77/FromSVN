package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
@Repository
public class ContractDocVariableDAO extends CommonDAO<TbContractDocVariable>{
	private static final Logger log =  Logger.getLogger(ContractDocVariableDAO.class);
	/**
	 * 
	 * 获取所有报价单的集合
	 * @author luj 新增日期：2012-12-21
	 * @param hql  执行的   
	 * @return
	 */
	public List queryAllContractDocVariable(String hql,Object ...objects) {
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
