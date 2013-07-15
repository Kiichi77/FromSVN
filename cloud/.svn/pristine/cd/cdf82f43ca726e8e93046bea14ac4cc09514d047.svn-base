package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocTypeDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractDocType;
import cn.com.kxcomm.contractmanage.service.IContractDocTypeService;
import cn.com.kxcomm.contractmanage.vo.ContractDocTypeVo;

@Service("contractDocTypeService")
public class ContractDocTypeService extends
CommonService<TbContractDocType> implements IContractDocTypeService{

	@Autowired(required=true)
	private ContractDocTypeDAO contractDocTypeDAO;
	
	@Override
	public CommonDAO<TbContractDocType> getBindDao() {
		return contractDocTypeDAO;
	}
	
	//分页查询所有的类型
	public PageInfo<ContractDocTypeVo> listContractDocType(PageInfo<ContractDocTypeVo> pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name from tb_contract_doc_type ");
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
		int count = contractDocTypeDAO.findCount(countSql.toString());
		List list = contractDocTypeDAO.findByPage(sql.toString(), pageInfo);
		List<ContractDocTypeVo> listContractDocType=new ArrayList<ContractDocTypeVo>();
		Object[] obj = new Object[list.size()];
		for(int i=0;i<list.size();i++)
		{
			obj = (Object[]) list.get(i);
			ContractDocTypeVo contractDocTypeVo=new ContractDocTypeVo();
			contractDocTypeVo.setId(obj[0].toString());
			contractDocTypeVo.setName(obj[1].toString());
			listContractDocType.add(contractDocTypeVo);
		}
		PageInfo<ContractDocTypeVo> page = new PageInfo<ContractDocTypeVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractDocType);
		return page;
	}
	
	
}
