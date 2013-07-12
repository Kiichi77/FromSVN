package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesVo;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;

@Service("contractTemplatesService")
public class ContractTemplatesService extends CommonService<TbContractTemplates> implements IContractTemplatesService{
	@Autowired(required=true)
	private ContractTemplatesDAO contractTemplatesDAO;
	
	@Override
	public CommonDAO<TbContractTemplates> getBindDao() {
		return contractTemplatesDAO;
	}

	//分页查询所有的合同模版
	public PageInfo<ContractTemplatesVo> listContractTemplates(PageInfo<ContractTemplatesVo> pageInfo){
		StringBuffer sql = new StringBuffer();
		sql.append("select id,create_time,name,status from tb_contract_templates where 1=1 order by create_time desc");
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
		int count = contractTemplatesDAO.findCount(countSql.toString());
		List list = contractTemplatesDAO.findByPage(sql.toString(), pageInfo);
		List<ContractTemplatesVo> listContractTemplatesVo=new ArrayList<ContractTemplatesVo>();
		Object[] obj = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			obj = (Object[]) list.get(i);
			ContractTemplatesVo contractTemplatesVo=new ContractTemplatesVo();
			contractTemplatesVo.setId(obj[0].toString());
			contractTemplatesVo.setCreatetime(obj[1].toString());
			contractTemplatesVo.setName(obj[2].toString());
			contractTemplatesVo.setStatus(obj[3].toString());
			listContractTemplatesVo.add(contractTemplatesVo);
		}
		PageInfo<ContractTemplatesVo> page = new PageInfo<ContractTemplatesVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractTemplatesVo);
		 return page;
	}
	
	//查询所有的合同模版
	public List<ContractTemplatesVo> queryAllContractTemplates() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,create_time,name,status from tb_contract_templates where status = "+BusinessConstants.template_check_generalManagerPass);
		List list = contractTemplatesDAO.queryAllContractTemplates(sql.toString());
		List<ContractTemplatesVo> listContractTemplatesVo = new ArrayList<ContractTemplatesVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractTemplatesVo contractTemplatesVo=new ContractTemplatesVo();
			contractTemplatesVo.setId(obj[0].toString());
			contractTemplatesVo.setCreatetime(obj[1].toString());
			contractTemplatesVo.setName(obj[2].toString());
			contractTemplatesVo.setStatus(obj[3].toString());
			listContractTemplatesVo.add(contractTemplatesVo);
		}
		return listContractTemplatesVo;
	}

	/**
	* 
	* 方法用途和描述: 根据ID查询某个合同模板信息
	* @return
	* @author luj 新增日期：2012-12-14
	* @since ContractManage
	*/
	//@Override
	public ContractTemplatesVo queryTemplatesById(String id) {
		TbContractTemplates contractTemplates = this.contractTemplatesDAO.findById(Integer.parseInt(id));
		ContractTemplatesVo contractTemplatesVo = new ContractTemplatesVo();
		contractTemplatesVo.setId(""+contractTemplates.getId());
		contractTemplatesVo.setCreatetime((new Date()).toString());
		contractTemplatesVo.setName(contractTemplates.getName());
		contractTemplatesVo.setStatus(contractTemplates.getStatus().toString());
		return contractTemplatesVo;
	}
}
