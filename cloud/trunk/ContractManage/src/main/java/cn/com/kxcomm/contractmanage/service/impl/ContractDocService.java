package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.service.IContractDocService;

@Service("contractDocService")
public class ContractDocService extends
CommonService<TbContractDoc> implements IContractDocService{
	
	private static final Logger LOG = Logger.getLogger(ContractDocService.class);

	@Autowired(required=true)
	private ContractDocDAO contractDocDAO;
	
	@Override
	public CommonDAO<TbContractDoc> getBindDao() {
		return contractDocDAO;
	}

	/**
	 * 
	* 方法用途和描述:根据ID查询合同文件
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	@Override
	public TbContractDoc queryTbContractDocById(String id) {
		return getByPk(Integer.parseInt(id));
	}
	
	/**
	 * 
	* 方法用途和描述:修改合同文件
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	@Override
	public void updateContractDoc(TbContractDoc contractDoc) {
		this.update(contractDoc);
	}
	
	/**
	 * 
	* 方法用途和描述:根据合同ID查询所有的合同文件
	* @param contractId 合同id
	* @return
	* @author lizl 新增日期：2012-12-27
	* @since ContractManage
	 */
	@Override
	public List<TbContractDoc> queryConFileById(int contractId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from TbContractDoc t where contract.id = ? order by t.createtime desc");
		List<TbContractDoc> contractDocList = contractDocDAO.find(hql.toString(),contractId);
		return contractDocList;
	}

	/**
	 * 根据合同id删除合同选中文件
	 */
	@Override
	public void deleteByContractId(int contractId) {
		String hql = "delete TbContractDoc t where t.contract.id = ? ";
		int result = contractDocDAO.executeByHQL(hql, contractId);
		LOGGER.info("result:"+result);
	}

	/**
	 * 根据合同选中文件id修改合同选中文件的名称
	 */
	@Override
	public boolean updateContractField(TbContractDoc doc) {
		String hql = "update TbContractDoc d set d.fileName=? where id=? ";
		int result = contractDocDAO.executeByHQL(hql, doc.getFileName(),doc.getId());
		return result>=0?true:false;
	}

	@Override
	public TbContractDoc getContractDocById(int contractId, int conId) {
		String hql = " from TbContractDoc t where contract.id = ? and doc.id = ?";
		List<TbContractDoc> contractList = contractDocDAO.find(hql, contractId,conId);
		LOG.info("contractList:"+contractList);
		if(!BlankUtil.isBlank(contractList)){
			return contractList.get(0);
		}
		return null;
	}
}
