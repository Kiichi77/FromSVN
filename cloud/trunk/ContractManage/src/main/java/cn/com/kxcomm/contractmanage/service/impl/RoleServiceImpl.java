package cn.com.kxcomm.contractmanage.service.impl;

import org.apache.log4j.Logger;
import org.htmlparser.lexer.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.RightDAO;
import cn.com.kxcomm.contractmanage.dao.RightRoleDAO;
import cn.com.kxcomm.contractmanage.dao.RoleDAO;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbRightRoleId;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.service.IRoleService;

/**
 * 角色管理业务逻辑类
 * @author chenliang
 *
 */
@Service("roleService")
public class RoleServiceImpl extends CommonService<TbRole> implements IRoleService {
	
	public static Logger log = Logger.getLogger(RightServiceImpl.class);
	
	@Autowired(required = true)
	private RoleDAO roleDAO;
	
	@Autowired(required = true)
	private RightDAO rightDAO;
	
	@Autowired(required = true)
	private RightRoleDAO rightRoleDAO;
	
	@Override
	public CommonDAO<TbRole> getBindDao() {
		return roleDAO;
	}
	
	
	/**
	 * 重写分页方法
	 */
	@Override
	public PageInfo<TbRole> findByPage(TbRole entity, PageInfo<TbRole> pageInfo) {
		StringBuffer hql = new StringBuffer(" from TbRole tr where 1=1 ");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getRolename())){
				hql.append(" and tr.rolename like ? ");
				entity.setRolename("%"+entity.getRolename()+"%");
			}
			return this.roleDAO.findByPage(pageInfo, hql.toString(), entity.getRolename());
		}else{
			return this.roleDAO.findByPage(pageInfo, hql.toString());
		}
	}

	/**
	 * 插入角色与权限的关系
	 */
	@Override
	public void insertRoleRight(Long roleId, String[] rightIds) {
		log.debug("insert roleRight ,rightIds:"+rightIds.toString());
		if(!BlankUtil.isBlank(rightIds)){
			for (int i = 0; i < rightIds.length; i++) {
				TbRightRoleId rightRole = new TbRightRoleId();
				rightRole.setRoleId(roleId);
				rightRole.setRightId(Long.parseLong(rightIds[i]));
				TbRightRole rightRole2 = new TbRightRole();
				rightRole2.setId(rightRole);
				this.roleDAO.insertRoleRight(rightRole2);
				log.debug("insert roleRight successful.");
			}
		}else{
			log.debug("rightIds is null ,insert fail.");
			return;
		}
	}
	
	/**
	 * 更新角色和权限
	 */
	@Override
	public void updateRoleRight(Long roleId, String[] rightIds) {
		log.debug("update roleRight ,rightIds:"+rightIds.toString());
		if(!BlankUtil.isBlank(rightIds)){
			String hql = "delete TbRightRole tr where tr.id.roleId=? ";
			//删除该角色之前的所有权限
			int result = this.roleDAO.executeByHQL(hql, roleId);
			log.debug("delete tbRightRole by roleId success.insert rightRole.");
			//更新新的全新id
			for (int i = 0; i < rightIds.length; i++) {
				TbRightRoleId rightRole = new TbRightRoleId();
				rightRole.setRoleId(roleId);
				rightRole.setRightId(Long.parseLong(rightIds[i]));
				TbRightRole rightRole2 = new TbRightRole();
				rightRole2.setId(rightRole);
				this.roleDAO.updateRoleRight(rightRole2);
				log.debug("update roleRight successful.");
				
				//当前的权限
				TbRight right = this.rightDAO.findById(Integer.parseInt(rightIds[i]));
				if(right.getParid()!=null){
					//判断二级菜单的父级菜单是否保存关系
					TbRightRole query =rightRoleDAO.query(roleId, right.getParid());
					Long parId = Long.parseLong(right.getParid().toString());
					if(query==null){
						rightRole = new TbRightRoleId();
						rightRole.setRoleId(roleId);
						rightRole.setRightId(parId);
						rightRole2 = new TbRightRole();
						rightRole2.setId(rightRole);
						this.roleDAO.updateRoleRight(rightRole2);
					}
				}
			}	
		}else{
			String hql = "delete TbRightRole tr where tr.id.roleId=? ";
			//权限id为空，删除该角色之前的所有权限
			int result = this.roleDAO.executeByHQL(hql, roleId);
			if(result>0){
				log.debug("update tbRightRole by roleId success.");
			}else{
				log.debug("update tbRightRole by roleId fail.");
			}
		}
	}
	
}
