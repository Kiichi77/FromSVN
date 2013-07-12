package cn.com.kxcomm.ipmi.service;

import cn.com.kxcomm.ipmi.entity.TbRole;

/**
 * 角色业务逻辑处理接口
 * @author chenliang
 *
 */
public interface RoleService extends CommonService<TbRole> {


	/**
	 * 
	 * 新增角色与权限的关联
	 * 
	 * @param entity
	 * @author chenl 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public void insertRoleRight(Long roleId,Long[] rightIds) ;

	/**
	 * 
	 * 更新角色与权限的关联
	 * 
	 * @param entity
	 * @author chenl 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public void updateRoleRight(Long roleId,Long[] rightIds) ;

}
