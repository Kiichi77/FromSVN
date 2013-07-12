package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;
@Repository
public class PurchaseOrderContractFileDAO extends CommonDAO<TbPurchaseOrderContractFile>{

	private static final Logger log = Logger.getLogger(PurchaseOrderContractFileDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author luj 新增日期：2013-3-13
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findById(String hql,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i]) && -1!=Integer.parseInt(objects[i].toString())){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	/**
	 * 
	 * 执行hql
	 * @author luj 新增日期：2013-3-13
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List<TbPurchaseOrderContractFile> findHql(PageInfo<PurchaseOrderContractFileVo> pageInfo,String hql,Object ...objects){
		Session  sessionUse = this.getSession();
		Query sq = this.createQuery(sessionUse, hql, objects);
		List<TbPurchaseOrderContractFile> lista = sq.list();
		if ((pageInfo.getCurrentPage() !=0 && pageInfo.getPageSize() !=0)  ) {  
			sq.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPageSize()).setMaxResults(pageInfo.getPageSize());
		}
		return lista;
	}
}
