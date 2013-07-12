package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemporaryVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderVo;

public interface IContractTemporaryService  extends ICommonService<TbContractTemporary>{
	/**
	 * 
	 * 添加临时合同
	 * 
	 * @param vo
	 * @param userId
	 * @return
	 * @author zhangjh 新增日期：2013-4-15
	 * @since ContractManage
	 */
	public boolean addContractTemporary(ContractTemporaryVo vo,Long userId);
	
	/**
	 * 
	 * 查询所有临时合同
	 * 
	 * @param userId
	 * @return
	 * @author zhangjh 新增日期：2013-4-15
	 * @since ContractManage
	 */
	public PageInfo<ContractTemporaryVo> queryAll(PageInfo<ContractTemporaryVo> pageInfo,Long userId,ContractTemporaryVo  paramVo);
	
	/**
	 * 
	 * 查询临时合同的所有报价表的所有机型配置
	 * 
	 * @param ids,取值 1,2,3都可以
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	public List<ContractQuotationsVo> queryAllConfigModels(String  ids);
	
	/**
	 * 
	 * 查询临时合同信息
	 * 
	 * @param ctId
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	public ContractTemporaryVo getById(Integer ctId);
	
	/**
	 * 
	 * 修改临时合同信息
	 * 
	 * @param vo
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	public boolean editContractTemporary(ContractTemporaryVo vo);
	
	/**
	 * 
	 * 临时合同转正式合同
	 * 
	 * @param vo
	 * @return
	 * @author zhangjh 新增日期：2013-4-18
	 * @since ContractManage
	 */
	public boolean saveTemporaryDataToFormal(ContractTemporaryVo vo,Long userId);
	
	/**
	 * 
	 * 审核临时合同
	 * 
	 * @param vo
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public void checkContract(CheckedVo checkVo) throws Exception;
	
	/**
	 * 
	 * 提交临时合同审核请求
	 * 
	 * @param vo
	 * @author zhangjh 新增日期：2013-4-27
	 * @since ContractManage
	 */
	public void submitCheck(CheckedVo vo);
}
