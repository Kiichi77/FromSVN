package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationship;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesRelationship;

public interface IContractTemplatesRelationShipService  extends ICommonService<TbContractTemplatesRelationship> {
	
	/**
	 * 
	* 方法用途和描述: 批量添加合同模板月合同模板文件之间的关系
	* @param contractTemplateId 合同模板id
	* @param contractTemplateDocIds 合同模板文件ids
	* @author chenliang 新增日期：2013-2-26
	* @since ContractManage
	 */
	public void saveRelationShip(String contractTemplateId,String contractTemplateDocIds);
	
	/**
	 * 
	* 方法用途和描述: 根据合同模板文件id查询合同模板与合同模板文件之间的关系
	* @param docId
	* @return
	* @author chenliang 新增日期：2013-2-27
	* @since ContractManage
	 */
	public List<TbContractTemplatesRelationship> listByDocId(int docId);
	

}
