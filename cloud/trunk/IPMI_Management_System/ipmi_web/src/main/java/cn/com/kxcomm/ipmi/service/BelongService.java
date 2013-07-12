package cn.com.kxcomm.ipmi.service;

import java.io.Serializable;
import java.util.List;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBelong;
import cn.com.kxcomm.ipmi.vo.BelongEntity;

/**
 * 
* 功能描述:服务器所属集合表业务处理逻辑接口
* @author chenl 新增日期：2012-8-10
* @since ipmi_web
 */
public interface BelongService extends CommonService<TbBelong> {
	
	/**
	 * 
	* 方法用途和描述: 根据菜单级别查询上一级菜单的对象
	* @param level
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public List<TbBelong> findParentBylevel(short level);
	
	/**
	 * 
	* 方法用途和描述: 查询第一级菜单，数据中心这级别数据
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public List<BelongEntity> findMenu(Long parentId);
	
	/**
	 * 
	* 方法用途和描述:查询最大Id
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	public Long findMaxId();
	
	/**
	 * 
	* 方法用途和描述: 根据等级，查询该等级下所有的集合列表
	* @return
	* @author chenl 新增日期：2012-8-16
	* @since ipmi_web
	 */
	public Page<BelongEntity> findListByLevel(TbBelong belong,Page<TbBelong> pageInfo);
	

	public List<BelongEntity> findMenuWithServer(Long parentId);
	
	/**
	 * 
	* 方法用途和描述:获取所有位置的层级关系
	* @param belongId
	* @return
	* @author chenl 新增日期：2012-8-28
	* @since ipmi_web
	 */
	public List<BelongEntity> getAllBelongMenu(Long belongId);
	
	/**
	 * 
	* 方法用途和描述: 重写分页方法
	* @return
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	public boolean deleteAlls(Serializable[] pks);
	
//	/**
//	 * 
//	* 方法用途和描述: 测试树形结构
//	* @param id
//	* @param checkedList
//	* @return
//	* @author chenl 新增日期：2012-9-7
//	* @since ipmi_web
//	 */
//	public List<BelongEntity> test(Long id);
}
