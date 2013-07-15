package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbRightRole;

@Repository
public class RightRoleDAO extends CommonDAO<TbRightRole> {

	public TbRightRole query(Long roleId ,Integer  rightId){
		TbRightRole tbRightRole = null;
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select tb from TbRightRole tb where tb.tbRole.id=? and tb.tbRight.id=?" );
		Query query = session.createQuery(hql.toString());
		query.setLong(0, roleId);
		query.setInteger(1, rightId);
		List<TbRightRole> list = query.list();
		System.out.println("list.size():"+list.size());
		for(TbRightRole obj : list){
			System.out.println(obj);
			tbRightRole = obj;
		}
		return tbRightRole;
	}

}
