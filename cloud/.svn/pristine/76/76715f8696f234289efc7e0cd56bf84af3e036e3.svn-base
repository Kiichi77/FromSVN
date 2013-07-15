package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesRelationShipDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesRelationship;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesRelationShipService;

@Service("contractTemplatesRelationShipService")
public class ContractTemplatesRelationShipService extends CommonService<TbContractTemplatesRelationship> implements IContractTemplatesRelationShipService{

	private static final Logger LOGGER = Logger.getLogger(ContractTemplatesRelationShipService.class);
	
	@Autowired(required=true)
	private ContractTemplatesRelationShipDAO contractTemplatesRelationShipDAO;
	
	@Override
	public CommonDAO<TbContractTemplatesRelationship> getBindDao() {
		return contractTemplatesRelationShipDAO;
	}

	@Override
	public void saveRelationShip(String contractTemplateId,
			String contractTemplateDocIds) {
		LOGGER.info("contractTemplateId:"+contractTemplateId+",contractTemplateDocIds:"+contractTemplateDocIds);
		if(!BlankUtil.isBlank(contractTemplateDocIds) && !BlankUtil.isBlank(contractTemplateId)){
			//先根据合同模板id删除合同模板与合同模板文件之间的关系
			int tempId = Integer.parseInt(contractTemplateId);
			deleteByTemplateId(tempId);
			//保存新关系
			int docId;
			String[] docIds = contractTemplateDocIds.split(",");
			for (String string : docIds) {
				docId = Integer.parseInt(string);
				TbContractTemplatesRelationship ship = new TbContractTemplatesRelationship();
				
				TbContractTemplates template = new TbContractTemplates();
				template.setId(tempId);
				ship.setContractTemplates(template);
				
				TbContractTemplatesDoc doc = new TbContractTemplatesDoc();
				doc.setId(docId);
				ship.setContractTemplatesDoc(doc);
				contractTemplatesRelationShipDAO.save(ship);
			}
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据模板id删除合同模板与合同模板文件之间的关系
	* @param templateId
	* @author chenliang 新增日期：2013-2-27
	* @since ContractManage
	 */
	private void deleteByTemplateId(int templateId){
		String hql = "delete TbContractTemplatesRelationship a where a.contractTemplates.id = ? ";
		contractTemplatesRelationShipDAO.executeByHQL(hql, templateId);
	}

	@Override
	public List<TbContractTemplatesRelationship> listByDocId(int docId) {
		String hql = "from TbContractTemplatesRelationship a where a.contractTemplatesDoc.id = ?";
		List<TbContractTemplatesRelationship> relationShiplist = contractTemplatesRelationShipDAO.find(hql, docId);
		return relationShiplist;
	}

}
