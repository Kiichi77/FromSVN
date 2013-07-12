package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractDetailVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.vo.ContractVo;

public interface IContractService extends ICommonService<TbContract> {
	public PageInfo<ContractVo> listContract(PageInfo<ContractVo> pageInfo,ContractVo contractVo,Integer contractId,Long userId);
	
	//查询合同详情
	public List<ContractDetailVo> listContractDetail(int contractId);
	//根据合同id查询合同模板文件内容
	public PageInfo<ContractTemplatesDocVo> listContractTemplates(int contractId);
	//根据合同id查询合同
	public TbContract queryTbContractById(String id);
	//修改合同
	public String updateTbContract(TbContract contract);
	public List<ContractVo> queryAllContract();
	
	/**
	 * 
	* 方法用途和描述: 保存合同
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	public Integer saveContract(ContractVo contractVo) throws Exception;

	/**
	 * 
	* 方法用途和描述: 修改合同
	* @param contractVo
	* @return
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	public Integer updateContractByState(ContractVo contractVo);
	
	/**
	 * 
	* 方法用途和描述: 生成正式合同编号
	* 规则：KX+日期+流水号
	* example:KXyyyyMMdd0001
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String officialContractNo(Integer number);
	
	/**
	 * 
	* 方法用途和描述: 生成临时合同编号
	* 规则：KX+日期+流水号+L
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String temporaryContractNo(Integer number);
	
	/**
	 * 
	 * 审核合同
	 * 
	 * @param vo
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public void checkContract(CheckedVo vo) throws Exception ;
	
	/**
	 * 
	 * 提交审核请求
	 * 
	 * @param vo
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public void submitCheck(CheckedVo vo);
	
}
