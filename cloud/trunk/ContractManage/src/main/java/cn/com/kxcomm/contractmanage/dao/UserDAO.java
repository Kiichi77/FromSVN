package cn.com.kxcomm.contractmanage.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;
import cn.com.kxcomm.contractmanage.vo.RoleVo;
import cn.com.kxcomm.contractmanage.vo.UserVo;


@Repository
public class UserDAO extends CommonDAO<TbUser>{
	
	private static Logger log = Logger.getLogger(UserDAO.class);

	/**
	 * 登录判断是否有此用户
	 * @return
	 */
	public boolean login(String accouont,String passWord){
		boolean flag = false;
		TbUser tbuser= new TbUser();
		tbuser.setAccouont(accouont);
		tbuser.setPassword(passWord);
		List a = this.findByExample(tbuser);
		if(a.size()>0)flag=true;
		return flag;
	}
	
	public boolean loginHql(String accouont,String passWord){
		boolean flag = false;
        String hql="from TbUser where accouont=? and password=?";
        String[] values=new String[2];
        values[0]=accouont;values[1]=passWord;
        List a=this.find(hql, values);	
		if(a.size()>0)flag=true;
		return flag;
	}
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List executeHql(String hql,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	public  List<UserVo> queryUserByRole(long roleId) {
		String hql = " from TbUser tu where tu.tbRole.id=? ";
		List<TbUser> list = this.find(hql, roleId);
		List<UserVo> listUser=new ArrayList<UserVo>();
		for (TbUser user:list) {
			UserVo userVo=new UserVo();
			userVo.setId(user.getId());
			userVo.setAccouont(user.getAccouont());
			userVo.setEmail(user.getEmail());
			userVo.setNote(user.getNote());
			userVo.setUserName(user.getUserName());
			userVo.setJobDuties(user.getJobDuties());
			userVo.setJobPlace(user.getJobPlace());
			RoleVo roleVo = new RoleVo();
			roleVo.setId(user.getTbRole().getId());
			userVo.setTbRole(roleVo);
			listUser.add(userVo);
		}
		return listUser;
	}
	
	public  List<UserVo> queryIpUserByRole(long roleId) {
		String hql = " from TbUser tu where tu.tbRole.id=? and tu.isInterface=1";
		List<TbUser> list = this.find(hql, roleId);
		List<UserVo> listUser=new ArrayList<UserVo>();
		for (TbUser user:list) {
			UserVo userVo=new UserVo();
			userVo.setId(user.getId());
			userVo.setAccouont(user.getAccouont());
			userVo.setEmail(user.getEmail());
			userVo.setNote(user.getNote());
			userVo.setUserName(user.getUserName());
			userVo.setJobDuties(user.getJobDuties());
			userVo.setJobPlace(user.getJobPlace());
			RoleVo roleVo = new RoleVo();
			roleVo.setId(user.getTbRole().getId());
			userVo.setTbRole(roleVo);
			listUser.add(userVo);
		}
		return listUser;
	}
}
