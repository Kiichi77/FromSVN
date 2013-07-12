package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractQuotations;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;

/**
 * 
 * 合同报价总表
 * 
 * @author chenl 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IContractQuotationsService extends  ICommonService<TbContractQuotations> {
	//根据合同id查询合同报价总表内容
	public List<ContractQuotationsVo> queryAllQuotations(int templatesId);
	//保存加个总表表单数据
	public String saveAndUpdateQuotation(List<ContractQuotationsVo> volist);

	/**
	 * 
	 * 导出合同报价表总价excel
	 * 
	 * @param contract
	 *            合同对象
	 * @author zhangjh 新增日期：2012-12-28
	 * @since ContractManage
	 */
	public TbContract exportContractTotalPrice(TbContract contract);
	
	/**
	 * 
	* 方法用途和描述: 执行合同是查询配置单
	* @param conId
	* @return
	* @author chenliang 新增日期：2013-3-14
	* @since ContractManage
	 */
	public List<ContractQuotationsVo> queryAllConfigModels(int conId);
	
	/**
	 * 
	 * 生成合同
	 * 
	 * @param contractId
	 * @return
	 * @author zhangjh 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public boolean shengchengContractFile(Integer contractId);

	/**
	 * 
	 * 根据报价表ID，获取合同对象
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-4-19
	 * @since ContractManage
	 */
	public Integer getContractByQuotationId(Integer quotationId);
	
}
