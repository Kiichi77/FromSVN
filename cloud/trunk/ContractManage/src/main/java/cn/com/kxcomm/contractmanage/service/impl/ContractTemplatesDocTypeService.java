package cn.com.kxcomm.contractmanage.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocTypeDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractDocType;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocType;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocTypeService;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.vo.DataSourceVo;

@Service("contractTemplatesDocTypeService")
public class ContractTemplatesDocTypeService extends CommonService<TbContractTemplatesDocType> implements IContractTemplatesDocTypeService{

	@Autowired(required=true)
	private ContractTemplatesDocTypeDAO contractTemplatesDocTypeDAO;
	
	@Override
	public CommonDAO<TbContractTemplatesDocType> getBindDao() {
		return contractTemplatesDocTypeDAO;
	}
	
	//查询一个模版下所有的文件
	public PageInfo<ContractTemplatesDocVo> listConfigModels(PageInfo<ContractTemplatesDocVo> pageInfo)
	{
		      List<ContractTemplatesDocVo> listContractTemplatesDoc=new ArrayList<ContractTemplatesDocVo>(); 
                  List<TbContractTemplatesDocType> list=contractTemplatesDocTypeDAO.findAll();
                  int count = contractTemplatesDocTypeDAO.findTotalCount("select count(*) from TbContractTemplatesDocType");
                  for(TbContractTemplatesDocType contractTemplatesDocType:list)
                  {
                	  ContractTemplatesDocVo contractTemplatesDocVo=new ContractTemplatesDocVo();
                	  contractTemplatesDocVo.setId(contractTemplatesDocType.getDoc().getId().toString());
                	  contractTemplatesDocVo.setDocname(contractTemplatesDocType.getDoc().getDocname());
                	  contractTemplatesDocVo.setPath(contractTemplatesDocType.getDoc().getPath());
                	  contractTemplatesDocVo.setbVariable(contractTemplatesDocType.getDoc().getbVariable().toString());
                	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
                	  contractTemplatesDocVo.setCreatetime(sdf.format(contractTemplatesDocType.getDoc().getCreatetime()));
//                	  contractTemplatesDocVo.setContractTemplatesId(contractTemplatesDocType.getDoc();//.getContractTemplates().getId().toString());
                      contractTemplatesDocVo.setTypeName(contractTemplatesDocType.getDocType().getName());
                      listContractTemplatesDoc.add(contractTemplatesDocVo);
                  }
                  PageInfo<ContractTemplatesDocVo> page = new PageInfo<ContractTemplatesDocVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractTemplatesDoc);
         		 return page;
	}

	//修改合同模板与合同文件分类的关系
	@Override
	public void updateTemplateDocType(ContractTemplatesDocVo docVO)
			throws Exception {
		String stringid = docVO.getId();
		int docvoid = 0;
		if(!BlankUtil.isBlank(stringid)){
			docvoid = Integer.parseInt(stringid);
		}
		StringBuffer hql = new StringBuffer();
		//1先删除合同模板与合同文件的关系
		hql.append("delete TbContractTemplatesDocType where doc.id = ? ");
		int result = contractTemplatesDocTypeDAO.executeByHQL(hql.toString(), docvoid);
		LOGGER.info("删除合同模板与合同文件关系成功！");
		//2.2保存合同模板与合同文件的关系
		String docTypeId = docVO.getTypeName();
		if(!BlankUtil.isBlank(docTypeId)){
			String[] typeIds = docTypeId.split(",");
			for (String string : typeIds) {
				int id = Integer.parseInt(string);
				TbContractTemplatesDocType templateDoc = new TbContractTemplatesDocType();
				TbContractTemplatesDoc doc = new TbContractTemplatesDoc();
				doc.setId(docvoid);
				templateDoc.setDoc(doc);
				TbContractDocType docType = new TbContractDocType();
				docType.setId(id);
				templateDoc.setDocType(docType);
				contractTemplatesDocTypeDAO.save(templateDoc);
			}
			LOGGER.info("修改合同模板与合同文件关系成功!");
		}		
	}
}
