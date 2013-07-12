package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;

public interface IContractDocVariableService extends ICommonService<TbContractDocVariable> {
	public List<ContractDocVariableVo> queryAllContractDocVariable(int id,boolean isAll);
	/**
	 * 
	 * 方法用途和描述:修改变量内容
	 * 
	 * @return
	 * @author lizl 新增日期：2012-12-21
	 * @since ContractManage
	 */
	public String saveUpdataContractDocVariable(List<ContractDocVariableVo> contractDocVariables,List<ContractQuotationsVo> contractQuotationlist,int contractId) throws Exception;
	public boolean checkVariable(int templatesDocId,int contractId);
	/**
	 * 
	* 方法用途和描述: 保存合同变量值设置
	* @param templatesId  合同模板id
	* @author chenliang 新增日期：2013-1-5
	* @since ContractManage
	 */
	public void saveContractDocVariable(int templatesId);
	
	/**
	 * 
	* 方法用途和描述: 查询合同选中文件的价格
	* @author chenliang 新增日期：2013-1-17
	* @since ContractManage
	 */
	public List<ContractDocVariableVo> queryPrice(int contractDocId);
	/**
	 * 根据合同ID查询合同金额
	 * @param contractDocId 合同选中文件id
	 */
	public String totleMoneyByContractId(Integer contractId);
}
