package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.base.vo.RightEntity;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.vo.UserVo;

/**
 * 系统用户管理业务逻辑处理接口
 * @author chenliang
 *
 */
public interface IUserService extends ICommonService<TbUser> {

	
	public PageInfo<UserVo> queryPage(TbUser entity, PageInfo<TbUser> pageInfo);
	
	/**
	 * 
	 * 旧密码 的验证
	 * 
	 * @param consumerID
	 * @param oldPasswd
	 * @return true 旧密码验证成功，false旧密码验证失败
	 * @author zhangjh 新增日期：2010-6-25
	 * @author 你的姓名 修改日期：2010-6-25
	 * @since zte_crbt_bi
	 */
	public boolean checkPasswd(Long userID, String oldPasswd);

	/**
	 * 
	 * 修改用户密码(含验证旧密码)
	 * 
	 * @param consumerID
	 * @param oldPasswd
	 * @param newPasswd
	 * @return
	 * @author zhangjh 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public void updatePasswd(Long userId, String newPasswd);

	/**
	 * 
	 * 初始化用户密码,初始值为123456
	 * 
	 * @param consumerID
	 * @author zhangjh 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public void initPasswd(Long userID);

	/**
	 * 
	 * 检查帐号是否被使用
	 * 
	 * @param account
	 * @return false 该账号不存在
	 * 		   true 账号已经存在
	 * @author zhangjh 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public boolean checkAccount(String account);

	/**
	 * 检查登陆
	 * @param account
	 * @return
	 */
	public TbUser checkLogin(String account) ;


	/**
	 * 
	 * 获取用户角色列表
	 * 
	 * @param consumerId
	 * @return
	 * @author zhangjh 新增日期：2010-6-21
	 * @author 你的姓名 修改日期：2010-6-21
	 * @since zte_crbt_bi
	 */
	public TbRole queryConsumerRole(Long consumerId) ;

	/**
	 * 根据角色id查找对应的权限与角色的关系
	 * @param roleId
	 * @return
	 */
	public List<RightEntity> queryRoleRightListByRoleId(Long roleId) ;
	
	/**
	 * 根据角色id查找对应的权限菜单
	 * @param roleId
	 * @return
	 */
	public List<MenuEntity> queryMenuEntityByRoleId(Long roleId);
	
	/**
	 * 
	* 方法用途和描述: 从用户登录session中获取用户账号信息
	* @param session
	* @return
	* @author chenhui 新增日期：2010-7-27
	* @author 你的姓名 修改日期：2010-7-27
	* @since zte_crbt_bi
	 */
	public TbUser queryConsumerEntityFromSession(HttpSession session);
	
	/**
	 * 查询角色下的用户
	 * @param roleId
	 * @return
	 */
	public List<UserVo> queryUserByRole(long roleId);
	
	/**
	 * 根据角色ID查询该角色下的接口人
	 * @param roleId
	 * @return
	 */
	public  List<UserVo> queryIpUserByRole(long roleId);

	/**
	 * 
	* 方法用途和描述: 根据状态和审核结果查询处理人信息列表
	* @param status  状态 <code>BusinessConstants</code>
	* @param checkResult 处理结果:0通过，1不通过
	* @author chenliang 新增日期：2013-4-7
	* @since ContractManage
	 */
	public List<UserVo> templateCheckQueryUser(String status, String checkResult);
}
