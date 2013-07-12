package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbContractDoc;

public interface IContractDocService  extends ICommonService<TbContractDoc> {
	//根据ID查询合同文件
	public TbContractDoc queryTbContractDocById(String id);
	//修改合同文件
	public void updateContractDoc(TbContractDoc contractDoc);
	//根据合同ID查询所有的合同文件
	public List<TbContractDoc> queryConFileById(int contractId);
	//根据合同id删除合同选中的文件
	public void deleteByContractId(int contractId);
	//根据选中合同文件id修改选中合同文件名称
	public boolean updateContractField(TbContractDoc doc);
	//根据合同id和合同选中文件id查询合同选中文件
	public TbContractDoc getContractDocById(int contractId,int conId);
}
