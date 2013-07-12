package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesVo;

/**
 * 
 * 合同模版
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IContractTemplatesService extends ICommonService<TbContractTemplates>{
	public PageInfo<ContractTemplatesVo> listContractTemplates(PageInfo<ContractTemplatesVo> pageInfo);
	public List<ContractTemplatesVo> queryAllContractTemplates();
	public ContractTemplatesVo queryTemplatesById(String id) ;
}
