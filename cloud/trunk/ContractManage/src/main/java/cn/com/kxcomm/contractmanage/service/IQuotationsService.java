package cn.com.kxcomm.contractmanage.service;

import java.util.HashMap;
import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;

/**
 * 
 * 报价表
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IQuotationsService extends  ICommonService<TbQuotations> {
	public PageInfo<QuotationsVo> listQuotations(PageInfo<QuotationsVo> pageInfo,Long loginUserId) ;
	public List<QuotationsVo> queryAllQuotations();
	public List<QuotationsVo> queryAllQuotationseByContract(int id); 
	/**
	 * 
	* 方法用途和描述: 查询所有没有被合同选中的报价表
	* @return
	* @author chenliang 新增日期：2013-2-28
	* @since ContractManage
	 */
	public List<QuotationsVo> listAll(Long loginUserId,String contractId);
	
	/**
	 * 
	* 方法用途和描述: 根据合同id查询
	* @param contractId
	* @return
	* @author chenliang 新增日期：2013-2-28
	* @since ContractManage
	 */
	public List<QuotationsVo> listByContractId(String contractId,Integer projectId);
	
	//public void merge(TbQuotations quotations);
	
	/**
	 * 
	* 方法用途和描述: 审核报价表
	* @author chenliang 新增日期：2013-4-8
	* @since ContractManage
	 */
	public void checkQuotation(String quotationsId,CheckedVo checkedVo);
	
	/**
	 * 
	* 方法用途和描述: 生成报价表
	* @param idJson 报价表与机型配置关系json字符串
	* @param baojiaTable  报价总表json字符串
	* @param projectId 项目id
	* @author chenliang 新增日期：2013-4-9
	* @since ContractManage
	 */
	public void saveAuotions(HashMap<String, String> map,TbQuotations model) throws Exception;
	
	/**
	 * 
	* 方法用途和描述: 提交审核
	* @param checkedVo
	* @author chenliang 新增日期：2013-4-9
	* @since ContractManage
	 */
	public void submitCheck(CheckedVo checkedVo) throws Exception;
}
