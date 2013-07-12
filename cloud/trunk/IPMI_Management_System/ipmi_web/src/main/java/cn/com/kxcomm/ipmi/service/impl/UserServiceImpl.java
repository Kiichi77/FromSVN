package cn.com.kxcomm.ipmi.service.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.MD5;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.UserDAO;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.UserService;
import cn.com.kxcomm.ipmi.vo.MenuEntity;
import cn.com.kxcomm.ipmi.vo.RightEntity;

/**
 * 系统用户管理业务逻辑处理类
 * @author chenliang
 *
 */
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<TbUser> implements UserService {
	public static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired(required = true)
	public UserDAO userDAO;
	
	
	/**
	 * 重写分页方法
	 */
	@Override
	public Page<TbUser> findByPage(TbUser entity, Page<TbUser> pageInfo) {
		log.debug("tbUser entity:"+entity.toString());
		StringBuffer hql =  new StringBuffer(" from TbUser tu where 1=1 ");
		if(!BlankUtil.isBlank(entity.getAccouont())){
			hql.append(" and tu.accouont like ? ");
			entity.setAccouont("%"+entity.getAccouont()+"%");
		}
		if(!BlankUtil.isBlank(entity.getUserName())){
			hql.append(" and tu.userName like ? ");
			entity.setUserName("%"+entity.getUserName()+"%");
		}
		if(!BlankUtil.isBlank(entity.getTbRole())){
			if(!BlankUtil.isBlank(entity.getTbRole().getId())){
				hql.append("and tu.tbRole.id= "+entity.getTbRole().getId());
			}
		}
		if(entity.getAccouont().equals("")&&entity.getAccouont()!=null)
			entity.setAccouont(null);
		if(entity.getUserName().equals("")&&entity.getUserName()!=null)
			entity.setUserName(null);
		return userDAO.findByPage(pageInfo, hql.toString(), entity.getAccouont(),entity.getUserName());
	}
	/**
	 * 旧密码验证
	 * 
	 */
	@Override
	public boolean checkPasswd(Long userID, String oldPasswd) {
		log.debug("oldPasswd check!");
		TbUser userEntity = this.getByPk(userID);
		if(BlankUtil.isBlank(userEntity))return false;
		String md5 = MD5.toMD5(oldPasswd);
		if(userEntity.getPassword().trim().equals(md5)){
			return true;
		}
		return false;
	}
	/**
	 * 修改密码
	 */
	@Override
	public void updatePasswd(Long userID, String newPasswd) {
		log.debug("update Passwd.");
		if(BlankUtil.isBlank(userID)){
			log.debug("consumerId is null.");
			return;
		}
		TbUser userEntity = this.getByPk(userID);
		if(BlankUtil.isBlank(userEntity)){
			log.debug("Query data by consumerId ,the result userEntity is null.");
			return;
		}
		String md5 = MD5.toMD5(newPasswd);
		userEntity.setPassword(md5);
		this.update(userEntity);
		log.debug("update passwd successful.");
	}
	
	/**
	 * 初始化用户密码，初始化密码为123456
	 */
	@Override
	public void initPasswd(Long userId) {
		log.debug("initpasswd to 123456.");
		updatePasswd(userId, "123456");
		log.debug("initpasswd successful.");
	}
	
	/**
	 * 检查账号是否被使用
	 * @return false 该账号不存在
	 * 		   true 账号已经存在
	 */
	@Override
	public boolean checkAccount(String account) {
		log.debug("check account.");
		TbUser userEntity = checkLogin(account);
		if(BlankUtil.isBlank(userEntity)){
			return false;
		}
		return true;
	}
	
	/**
	 * 登陆检查
	 * @param userentity
	 * @return
	 */
	@Override
	public TbUser checkLogin(String account) {
		log.debug("checkLogin account is :"+account);
		return this.findByField("accouont", account);
	}
	
	/**
	 * 查询用户角色
	 * params:consumerId 用户id
	 * return: 角色对象
	 */
	@Override
	public TbRole queryConsumerRole(Long consumerId) {
		log.info("queryConsumerRole by consumerId.");
		if(!BlankUtil.isBlank(consumerId)){
			TbUser userEntity = userDAO.findById(consumerId);
			if(!BlankUtil.isBlank(userEntity)){
				log.info("query successful.");
				return userEntity.getTbRole();
			}
		}
		log.info("consumerid is null.");
		return new TbRole();
	}
	
	/**
	 * 根据角色id查询角色权限集合
	 */
	@Override
	public List<RightEntity> queryRoleRightListByRoleId(Long roleId) {
		if(!BlankUtil.isBlank(roleId)){
//			String hql = "select tri.id,tri.right_name,tri.parid,tri.right_level,tri.url " +
//					" from tb_right tri,tb_right_role trr where tri.id = trr.right_id and trr.role_id = ?";
			String hql = " select distinct(t3.id) as right_id,t3.right_name, t3.parid,t3.right_level,t3.url "+
						" from tb_right t3 "+
						" where t3.id in "+
						" ( "+
						"  select t2.id "+
						"   from tb_right_role t1,tb_right t2 "+
						"   where t2.id=t1.right_id "+
						"   and t1.role_id=? "+
						"   ) "+
						"  or t3.parid in "+
						"  ( "+
						"     select t2.id "+
						"   from tb_right_role t1,tb_right t2 "+
						"   where t2.id=t1.right_id "+
						"   and t1.role_id=? "+
						"   ) "+
						"  or t3.id in "+
						"   ( "+
						"   select t2.parid "+
						"   from tb_right_role t1,tb_right t2 "+
						"   where t2.id=t1.right_id "+
						"   and t1.role_id=? "+
						"  ) ";		
			List list = userDAO.executeHql(hql, roleId,roleId,roleId);
			Object[] obj = new Object[list.size()];
			List<RightEntity> rightList = new ArrayList<RightEntity>();
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				RightEntity entity = new RightEntity();
				entity.setRightId(parseInteger((BigInteger) obj[0]));
				entity.setRightName(BlankUtil.isNull(obj[1]));
				entity.setParentId((Integer) obj[2]);
				entity.setLevel(parseShort((Integer)obj[3]));
				entity.setUrl(BlankUtil.isNull(obj[4]));
				rightList.add(entity);
			}
			log.debug("rightList size:"+rightList.size());
			return rightList;
		}
		return new ArrayList<RightEntity>();
	}
	
	private Integer parseInteger(BigInteger params){
		Integer result = 0;
		if(!BlankUtil.isBlank(params)){
			result = Integer.parseInt(params.toString());
		}
		return result;
	}
	
	private Short parseShort(Integer params){
		Short result = 0;
		if(!BlankUtil.isBlank(params)){
			result = Short.parseShort(params.toString());
		}
		return result;
	}
	
	@Override
	public TbUser queryConsumerEntityFromSession(HttpSession session) {
		return null;
	}
	
	
	@Override
	public CommonDAO<TbUser> getBindDao() {
		return userDAO;
	}
	
	/**
	 * 根据权限id查找对应的菜单
	 */
	@Override
	public List<MenuEntity> queryMenuEntityByRoleId(Long roleId) {
		
//		long startTime = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsMenu = null;
		ResultSet rsRight = null;
		ArrayList<MenuEntity> list = null;
		ArrayList<RightEntity> rightEListInMenu = null;
////		HashMap<Integer,MenuEntity> tempMenuMap = null;
//		
		try {
////          方案1:经测试，查询时间大概范围为：15ms--47ms			
			list = new ArrayList<MenuEntity>();
			conn = SessionFactoryUtils.getDataSource(this.userDAO.getSessionFactory()).getConnection();;// 获得连接
//            String sql=" select distinct(tr.id),tr.url,tr.right_level,tr.parid,tr.right_name from tb_right tr " +
//"where "+
//"tr.id in(select t2.id from tb_right_role t1,tb_right t2 where t1.right_id=t2.id and t1.role_id=?) " +
//"or "+
//"tr.parid in (select id from tb_right t4 where t4.id in (select t2.id from tb_right_role t1,tb_right t2 where t2.id =t1.right_id and t1.role_id=?)) " +
//"or "+
//"tr.parid in (select t2.id from tb_right_role t1,tb_right t2 where t2.id=t1.right_id  and t1.role_id=?) " +
//"or "+
//"tr.parid in (select t2.id from tb_right_role t1,tb_right t2 where t2.id = t1.right_id and t1.role_id=?);";
            //2级菜单的SQL简单一点
			String sql="select distinct(tr.id),tr.url,tr.right_level,tr.parid,tr.right_name from tb_right tr where tr.id in(select t2.id from tb_right_role t1,tb_right t2 where t1.right_id=t2.id and t1.role_id=?)";
			log.debug("menusql:"+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, roleId);
//			pstmt.setLong(2, roleId);
//			pstmt.setLong(3, roleId);
//			pstmt.setLong(4, roleId);
			rsRight = pstmt.executeQuery();
			
			ArrayList<RightEntity> rightEntityList =  new ArrayList<RightEntity>();
			RightEntity rightEntity;
			RightEntity tempRightEntity;
			MenuEntity menuEntity;
			int tempid; // 一级菜单ID
			while (rsRight.next()) {					
				rightEntity = new RightEntity();
				//rightEntity.setCreateTime(rsRight.getTimestamp("create_time"));
				rightEntity.setLevel(rsRight.getShort("right_level"));
				rightEntity.setParentId(rsRight.getInt("parid"));
				rightEntity.setRightId(rsRight.getInt("id"));
				rightEntity.setRightName(rsRight.getString("right_name"));
				rightEntity.setUrl(rsRight.getString("url"));
				rightEntityList.add(rightEntity);
			}
			if(null!=rightEntityList&&rightEntityList.size()>0){
				ArrayList<RightEntity> tempREL = new ArrayList<RightEntity>();
			    tempREL.addAll(rightEntityList);	 // 对象拷贝，用来比较
				for(int i=0;i<rightEntityList.size();i++){
					rightEntity = rightEntityList.get(i);					
					if(1==(short)rightEntity.getLevel()){	
						menuEntity = new MenuEntity();
						menuEntity.setCreateTime(rightEntity.getCreateTime());
						menuEntity.setLevel(rightEntity.getLevel());
						menuEntity.setMenuId(rightEntity.getRightId());
						menuEntity.setMenuName(rightEntity.getRightName());
						menuEntity.setUrl(rightEntity.getUrl());
						tempid = rightEntity.getRightId();
						rightEListInMenu = new ArrayList<RightEntity>();
						for(int j=0;j<tempREL.size();j++){
							tempRightEntity = tempREL.get(j);
							if(tempid==tempRightEntity.getParentId()&&2==(short)tempRightEntity.getLevel()){ //如果是二级菜单，并且父菜单ID对应外层一级菜单的ID
								rightEListInMenu.add(tempRightEntity);
							}
						} // end for 内层
						menuEntity.setList(rightEListInMenu);
						list.add(menuEntity);
					}					    
				} // end for 外层				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsMenu != null)
					rsMenu.close();
				if (rsRight != null)
					rsRight.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
		return list;
	}
}
