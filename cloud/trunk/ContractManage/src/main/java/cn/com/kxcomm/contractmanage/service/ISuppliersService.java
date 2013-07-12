package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;

public interface ISuppliersService extends ICommonService<TbSuppliers>{
	
	/**
	 * 
	* 方法用途和描述: 分页查询供应商
	* @param pageInfo
	* @return
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public PageInfo<TbSuppliers> pageQuerySuppliers(PageInfo<TbSuppliers> pageInfo,TbSuppliers suppliers);
	
	/**
	 * 
	* 方法用途和描述: 查询所有的供应商，只审核通过的
	* @param pageInfo
	* @return
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public PageInfo<TbSuppliers> listSuppliers(PageInfo<TbSuppliers> pageInfo);
	public TbSuppliers querySuppliersById(String id);
	
	/**
	 * 
	* 方法用途和描述: 提交审核
	* @param checkedVo
	* @return
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public String submitChecked(CheckedVo checkedVo) throws Exception;

	/**
	 * 
	* 方法用途和描述: 审核供应商
	* @param checkedVo
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public void checkSuppliers(CheckedVo checkedVo);

}
