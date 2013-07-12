package cn.com.kxcomm.contractmanage.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVariableVo;

/**
 * 
 * 合同模版文件变量设置
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IContractTemplatesDocVariableService extends ICommonService<TbContractTemplatesDocVariable>{
	public List<ContractTemplatesDocVariableVo> listContractTemplatesDocVariable(int id);
	public String saveAndUpdateVariable(String docPath,List<ContractTemplatesDocVariableVo> jsonBeanList);
	public void deleteVariable(int id) throws SQLException;
}
