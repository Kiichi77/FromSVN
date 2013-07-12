package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationship;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderRelationshipVo;

public interface IContractOrderRelationshipService extends ICommonService<TbContractOrderRelationship>{
	/**
	 * 保存关联关系
	 * @param entity
	 */
	public void saveRelationship(TbContractOrderRelationship entity);
	
	/**
	* 
	* 方法用途和描述: 更新询价单的价格和供应商
	* @return
	* @author luj 新增日期：2013-3-15
	* @since ContractManage
	*/
	public String UpdateShip(List<ContractOrderRelationshipVo> jsonBeanList);
	
	/**
	 * 
	* 方法用途和描述: 提交审核
	* @param contractOrderId
	* @return
	* @author chenliang 新增日期：2013-5-6
	* @since ContractManage
	 */
	public String submitState(CheckedVo checkedVo) throws Exception;
}
