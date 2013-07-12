package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbSuppliersQuotations;
import cn.com.kxcomm.contractmanage.vo.SuppliersQuotationsVo;

public interface ISuppliersQuotationsService extends ICommonService<TbSuppliersQuotations> {

	/**
	 * 
	* 方法用途和描述: 分页查询供应商报价表
	* @param page
	* @param model
	* @return
	* @author chenliang 新增日期：2013-4-20
	* @since ContractManage
	 */
	public PageInfo<SuppliersQuotationsVo> queryPage(PageInfo<TbSuppliersQuotations> page, TbSuppliersQuotations model);

	/**
	 * 
	* 方法用途和描述: 保存供应商报价表
	* @param model
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	public void saveSuppliersQuotations(TbSuppliersQuotations model);

	/**
	 * 
	* 方法用途和描述: 根据主键id查询供应商报价表
	* @param id
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public SuppliersQuotationsVo querySuppliersById(String id);

	/**
	 * 
	* 方法用途和描述: 修改供应商报价表
	* @param model
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public void updateSupplierQuotations(TbSuppliersQuotations model);

	/**
	 * 
	* 方法用途和描述: 根据供应商id查询供应商下的所有报价单
	* @param id
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public  List<SuppliersQuotationsVo> querySuppliersQuotationsBySuppId(String id);


}
