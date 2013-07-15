package cn.com.kxcomm.contractmanage.service.impl;

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

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.base.vo.RightEntity;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.MD5;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IUserService;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;
import cn.com.kxcomm.contractmanage.vo.RoleVo;
import cn.com.kxcomm.contractmanage.vo.UserVo;

/**
 * 系统用户管理业务逻辑处理类
 * @author chenliang
 *
 */
@Service("userService")
public class UserServiceImpl extends CommonService<TbUser> implements IUserService {
	public static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired(required = true)
	public UserDAO userDAO;
	
	
	/**
	 * 重写分页方法
	 */
	@Override
	public PageInfo<UserVo> queryPage(TbUser entity, PageInfo<TbUser> pageInfo) {
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
		if("".equals(entity.getAccouont())||null==entity.getAccouont())
			entity.setAccouont(null);
		if("".equals(entity.getUserName())||null==entity.getUserName())
			entity.setUserName(null);
		PageInfo<TbUser> userInfo =  userDAO.findByPage(pageInfo, hql.toString(), entity.getAccouont(),entity.getUserName());
		PageInfo<UserVo> uservoinfo = new PageInfo<UserVo>();
		if(null!=userInfo){
			List<TbUser> userList = userInfo.getRows();
			List<UserVo> uservoList = new ArrayList<UserVo>();
			for (int i = 0; i < userList.size(); i++) {
				TbUser user = userList.get(i);
				UserVo vo = new UserVo();
				vo.setAccouont(user.getAccouont());
				vo.setEmail(user.getEmail());
				vo.setId(user.getId());
				vo.setNote(user.getNote());
				vo.setPassword(user.getPassword());
				vo.setPhoneNumber(user.getPhoneNumber());
				vo.setUserName(user.getUserName());
				vo.setJobDuties(user.getJobDuties());
				vo.setJobPlace(user.getJobPlace());
				RoleVo role = new RoleVo();
				role.setId(user.getTbRole().getId());
				role.setNote(user.getTbRole().getNote());
				role.setRolename(user.getTbRole().getRolename());
				vo.setTbRole(role);
				vo.setIsInterface(""+user.getIsInterface());
				uservoList.add(vo);
			}
			uservoinfo.setTotal(userInfo.getTotal());
			uservoinfo.setRows(uservoList);  
		}
		return uservoinfo;
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
			String hql = " select distinct(t3.id),t3.right_name, t3.parid,t3.right_level,t3.url "+
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
				entity.setRightId(Integer.parseInt(obj[0].toString()));
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
	 * 根据角色id查找对应的菜单
	 */
	@Override
	public List<MenuEntity> queryMenuEntityByRoleId(Long roleId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsMenu = null;
		ResultSet rsRight = null;
		ArrayList<MenuEntity> list = null;
		ArrayList<RightEntity> rightEListInMenu = null;
		try {
//          方案1:经测试，查询时间大概范围为：15ms--47ms			
			list = new ArrayList<MenuEntity>();
			conn = SessionFactoryUtils.getDataSource(this.userDAO.getSessionFactory()).getConnection();;// 获得连接
            //2级菜单的SQL简单一点
			String sql="select distinct(tr.id),tr.url,tr.right_level,tr.parid,tr.right_name from tb_right tr where tr.id in(select t2.id from tb_right_role t1,tb_right t2 where t1.right_id=t2.id and t1.role_id=?)";
			log.debug("menusql:"+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, roleId);
			rsRight = pstmt.executeQuery();
			
			ArrayList<RightEntity> rightEntityList =  new ArrayList<RightEntity>();
			RightEntity rightEntity;
			RightEntity tempRightEntity;
			MenuEntity menuEntity;
			int tempid; // 一级菜单ID
			while (rsRight.next()) {					
				rightEntity = new RightEntity();
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
	
	@Override
	public  List<UserVo> queryUserByRole(long roleId) {
		 List<UserVo> ll =userDAO.queryUserByRole(roleId);
		return ll;
	}
	
	/**
	 * 根据角色ID查询该角色下的接口人
	 * @param roleId
	 * @return
	 */
	@Override
	public List<UserVo> queryIpUserByRole(long roleId) {
		List<UserVo> ll =this.userDAO.queryIpUserByRole(roleId);
		return ll;
	}
	
	
	@Override
	public List<UserVo> templateCheckQueryUser(String status, String checkResult) {
		List<UserVo> list = new ArrayList<UserVo>();
		if(!BlankUtil.isBlank(status) && !BlankUtil.isBlank(checkResult)){
			int st = Integer.parseInt(status);
			if("0".equals(checkResult)){  //审核通过，查询下一个接口人
				if(BusinessConstants.template_check_now==st || BusinessConstants.template_check_financialUnpass==st){  //审核中，则查询所有财务接口人，销售审核
					list = queryIpUserByRole(BusinessConstants.role_financial);
				}else if(BusinessConstants.template_check_sellPass==st || BusinessConstants.template_check_generalManagerUnpass==st){ //销售审核通过，查询所有经理接口人,财务审核
					list = queryIpUserByRole(BusinessConstants.role_manager);
				}else if(BusinessConstants.template_check_financialPass==st){//通知销售生成报价表
					//list = queryIpUserByRole(BusinessConstants.role_seller);
				}
			}else if("1".equals(checkResult)){ //审核不通过，查询上一个接口人
				if(BusinessConstants.template_check_now == st || BusinessConstants.template_check_financialUnpass==st){ //销售审核
					//系统管理员
					list = queryIpUserByRole(BusinessConstants.role_admin); 
				}else if(BusinessConstants.template_check_financialPass == st ){//经理审核
					//查询所有财务
					list = queryIpUserByRole(BusinessConstants.role_financial); 
				}else if(BusinessConstants.template_check_sellPass == st || BusinessConstants.template_check_generalManagerUnpass==st){  //财务审核
					//查询所有销售
					list = queryIpUserByRole(BusinessConstants.role_seller);  
				}
			}
		}
		return list;
	}
}
