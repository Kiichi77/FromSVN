package cn.com.kxcomm.contractmanage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.DateFormatUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocVariableDAO;
import cn.com.kxcomm.contractmanage.dao.ContractQuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractQuotations;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocType;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesRelationship;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IContractService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.status.S_ConfigModels;
import cn.com.kxcomm.contractmanage.status.S_Contract;
import cn.com.kxcomm.contractmanage.status.S_Quotations;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractDetailVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.vo.ContractVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("contractService")
public class ContractService extends
CommonService<TbContract> implements IContractService{
	
	private static final Logger log = Logger.getLogger(ContractService.class);

	@Autowired(required=true)
	private ContractDAO contractDAO;
	@Autowired(required=true)
	private ContractDocDAO contractDocDAO;
	@Autowired(required=true)
	private QuotationsDAO quotationsDAO; //报价表dao
	@Autowired(required=true)
	private ContractQuotationsDAO contractQuotationsDAO; //报价总表的dao
	@Autowired(required=true)
	private ContractTemplatesDAO contractTemplatesDAO;//合同模板DAO
	@Autowired(required=true)
	private ContractDocVariableDAO contractDocVariableDAO; //合同模板变量DAO
	@Autowired(required=true)
	private ContractQuotationsService contractQuotationsService; //报价总表的dao
	@Autowired(required=true)
	private ContractTemporaryQuotationsService contractTemporaryQuotationsService; //报价总表的dao
	@Autowired(required=true)
	private ITaskService taskService;
	
	@Override
	public CommonDAO<TbContract> getBindDao() {
		return contractDAO;
	}

	//获得所有的合同
		public PageInfo<ContractVo> listContract(PageInfo<ContractVo> pageInfo,ContractVo conVo,Integer contractId,Long userId)
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT distinct con.id,tem.name,con.title,con.create_time,con.status,con.download_url,tpro.project_name,tpro.project_id,con.contract_number ");
			sql.append(",rp.contract_total_price,rp.contract_goods_percent,rp.contract_goods_price,rp.contract_begin_percent,rp.contract_begin_price,rp.contract_final_percent,rp.contract_final_price,con.contract_no");
			sql.append(" from tb_contract_templates tem ,tb_project tpro ,tb_contract con left join tb_contract_receive_payment rp on con.id=rp.contract_id ");
			sql.append(" WHERE con.contract_templates_id=tem.id and con.project_id=tpro.project_id ");
			if(!BlankUtil.isBlank(contractId)){
				sql.append(" and con.id=? ");
			}
			if(!BlankUtil.isBlank(userId))
			{
				sql.append(" and con.creator_id=? ");
			}
			if(!BlankUtil.isBlank(conVo.getCreateTime())){
				sql.append(" and date_format(con.create_time,'%Y-%m-%d') >= date_format(?,'%Y-%m-%d') ");
			}else{
				conVo.setCreateTime(null);
			}
			if(!BlankUtil.isBlank(conVo.getEndTime())){
				sql.append(" and date_format(con.create_time,'%Y-%m-%d') <= date_format(?,'%Y-%m-%d') ");
			}else{
				conVo.setEndTime(null);
			}
			sql.append(" group by con.id order by con.create_time desc ");
			StringBuffer countSql = new StringBuffer();
			countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
			int count = contractDAO.findCount(countSql.toString(),conVo.getCreateTime(),conVo.getEndTime(),contractId,userId);
			List list = contractDAO.findByPage(sql.toString(), pageInfo,conVo.getCreateTime(),conVo.getEndTime(),contractId,userId);
			List<ContractVo> listContractVo=new ArrayList<ContractVo>();
			Object[] obj = new Object[list.size()];	
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				ContractVo contractVo=new ContractVo();
				contractVo.setId(""+obj[0]);  //合同id
				contractVo.setContractTemplatesName(""+obj[1]); //合同模板名称
				contractVo.setTitle(""+obj[2]);   //合同内容
				contractVo.setCreateTime(DateFormatUtil.longFormatDateTime((Date) obj[3])); //创建时间
				contractVo.setStatus(""+obj[4]); //合同状态
				contractVo.setDownPath(obj[5]==null?"":obj[5].toString()); //合同下载地址
				contractVo.setProjectName(obj[6]==null?"":obj[6].toString()); //项目名称
				contractVo.setProjectId(""+obj[7]); //项目id
				contractVo.setContractNumber(""+obj[8]); //合同编号
				contractVo.setContractTotalPrice(obj[9]!=null?""+obj[9]:"-");
				contractVo.setContractGoodsPercent(obj[10]!=null?""+obj[10]:"-");
				contractVo.setContractGoodsPrice(obj[11]!=null?""+obj[11]:"-");
				contractVo.setContractBeginPercent(obj[12]!=null?""+obj[12]:"-");
				contractVo.setContractBeginPrice(obj[13]!=null?""+obj[13]:"-");
				contractVo.setContractFinalPercent(obj[14]!=null?""+obj[14]:"-");
				contractVo.setContractFinalPrice(obj[15]!=null?""+obj[15]:"-");
				contractVo.setContractNo(obj[16]!=null?obj[16]+"":"");
				listContractVo.add(contractVo);
			}
			PageInfo<ContractVo> page = new PageInfo<ContractVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractVo);
			return page;
		}

	@Override
	public List<ContractDetailVo> listContractDetail(int contractId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select td.name,tt.sourceId,tt.sourceName,tt.modelName,tt.modelId,tt.totalPrice ");
		hql.append(" from tb_quotations_data td,tb_contract_quotations tcq, ");
		hql.append(" (select e.id as sourceId,e.name sourceName,c.name modelName,c.id modelId, ");
		hql.append(" sum(a.total_price) as totalPrice ");
		hql.append(" from tb_configmodel_data a,tb_config_models c,tb_data_source e ");
		hql.append(" where a.config_model_id= c.id ");
		hql.append(" and c.data_source_id = e.id ");
		hql.append(" group by e.id,e.name,c.name,c.id ");
		hql.append(" ) tt ");
		hql.append(" where tcq.quotations_id = td.quotations_id ");
		hql.append(" and td.config_models_id = tt.modelId ");
		hql.append(" and tcq.contract_id= ? ");
		List list = contractDAO.findByPage(hql.toString(), contractId);
		Object[] obj = new Object[list.size()];
		List<ContractDetailVo> detailvoList = new ArrayList<ContractDetailVo>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractDetailVo detailVo = new ContractDetailVo();
			detailVo.setQuotationName(""+obj[0]);
			detailVo.setSourceId(""+obj[1]);
			detailVo.setSourceName(""+obj[2]);
			detailVo.setConfigName(""+obj[3]);
			detailVo.setConfigId(""+obj[4]);
			   BigDecimal bg = new BigDecimal(Double.parseDouble(obj[5].toString()));
			detailVo.setTotalPrice( bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			detailvoList.add(detailVo);
		}
		return detailvoList;
	}

	@Override
	public PageInfo<ContractTemplatesDocVo> listContractTemplates(int contractId) {
		List<ContractTemplatesDocVo> volist = new ArrayList<ContractTemplatesDocVo>();
		String hql = " from TbContractDoc t where contract.id = ? and doc.id = ?";
		//根据合同id查询合同
		TbContract contract = contractDAO.findById(contractId);
		//获取合同模板信息
	Set<TbContractTemplatesRelationship> set = contract.getContractTemplates().getContractTemplatesRelationship();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			ContractTemplatesDocVo docvo = new ContractTemplatesDocVo();
			TbContractTemplatesRelationship doccs = (TbContractTemplatesRelationship) iter.next();
			TbContractTemplatesDoc doc = doccs.getContractTemplatesDoc();
			docvo.setName(contract.getTitle());  //合同名称
			docvo.setTemplateDocName(doc.getDocname()); //合同模板文件名称
			docvo.setPath(doc.getPath());  //文件路径
			docvo.setbVariable(""+doc.getbVariable());  //是否变量
			Set<TbContractTemplatesDocType> docset = doc.getContractTemplatesDocTypeMembers();
			Iterator iterdoc = docset.iterator();
			String typeName = "";
			int num=0;
			while (iterdoc.hasNext()) {
				num++;
				TbContractTemplatesDocType tbdoc = (TbContractTemplatesDocType) iterdoc.next();
				typeName += tbdoc.getDocType().getName();
				if(num!=docset.size()){
					typeName+=",";
				}
			}
			docvo.setTypeName(typeName);  //分类
			//根据合同id与合同模板文件id查询合同选中的文件id
			List<TbContractDoc> contractDoc = contractDocDAO.find(hql, contract.getId(),doc.getId());
			if(!BlankUtil.isBlank(contractDoc) && contractDoc.size()>0){
				docvo.setContractTemplatesId(""+contractDoc.get(0).getId()); //合同选中的文件id
				docvo.setDocname(contractDoc.get(0).getFileName());
			}else{
				docvo.setContractTemplatesId("0"); //合同选中的文件id
			}
			docvo.setId(""+doc.getId()); //合同模板文件的id
			volist.add(docvo);
		}
		PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>();
		pageInfo.setRows(volist);
		pageInfo.setTotal(set.size());
		return pageInfo;
	}

	/**
	 * 
	* 方法用途和描述:根据ID查询合同信息
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	@Override
	public TbContract queryTbContractById(String id) {
		return this.contractDAO.findById(Integer.parseInt(id));
	}
	
	/**
	 * 
	* 方法用途和描述:修改合同信息
	* @return
	* @author lizl 新增日期：2012-12-24
	* @since ContractManage
	 */
	@Override
	public String updateTbContract(TbContract contract) {
		String end = "ok";
		try {
			this.contractDAO.merge(contract);
		} catch (Exception e) {
			end = "error";
		}
		return end;
	}
	
	/**
	* 
	* 方法用途和描述: 查询所有的合同
	* @return
	* @author luj 新增日期：2013-2-28
	* @since ContractManage
	*/
	@Override
	public List<ContractVo> queryAllContract() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT distinct con.id,quo.title as quoTitle,tem.name,con.title,con.create_time,con.status,con.download_url ");
		sql.append(" FROM tb_contract con,tb_quotations quo,tb_contract_templates tem ,tb_contract_quotations tq");
		sql.append(" WHERE  con.contract_templates_id=tem.id and con.id=tq.contract_id and tq.quotations_id=quo.id");
		List list = this.contractDAO.queryAllContract(sql.toString());
		List<ContractVo> listContractVo = new ArrayList<ContractVo>();
		if(!BlankUtil.isBlank(list)){
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractVo contractVo=new ContractVo();
			contractVo.setId(obj[0].toString());
			contractVo.setQuotationsTitle(obj[1].toString());
			contractVo.setContractTemplatesName(obj[2].toString());
			contractVo.setTitle(obj[3].toString());
			contractVo.setCreateTime(obj[4].toString());
			contractVo.setStatus(obj[5].toString());
			contractVo.setDownPath(obj[6]==null?"":obj[6].toString());
			listContractVo.add(contractVo);
		}
		}
		return listContractVo;
	}

	@Override
	public Integer saveContract(ContractVo contractVo) throws Exception {
		String quotationId = contractVo.getQuotationsTitle();
		log.debug("Parameters quotationId:"+quotationId);
		String[] quoIds = quotationId.split(",");
		   for(int i = 0; i<quoIds.length;i++){
			   Integer id = Integer.parseInt(quoIds[i]);
			   Integer count = this.contractQuotationsService.getContractByQuotationId(id);
				if(count!=null &&count>0){
					return -1;
				}
				count = this.contractTemporaryQuotationsService.getContractTemporaryByQuotationId(id);
				if(count!=null && count>0){
					return -1;
				}
		   }
		
		log.debug("contractVo:"+Json.toJson(contractVo));
		Integer contractId;
		//1.添加合同
		TbContract contract = convertEntity(contractVo);
		contract.setStatus(S_Contract.CONTRACT_TO_REVIEW);
		contract.setCreatetime(new Date());
		contractDAO.save(contract);
		contractId = contract.getId();
		log.debug("Save contract success.");
		//2.保存合同与报价表之间的关系
		contractQuotations(contractVo,contract);
		log.debug("Save contractQuotations success.");
		//3.保存合同选中的合同模板中的合同文件与合同之间的关系
		contractDocRelationShip(contract,contractVo);
		//4.保存合同文件的变量，根据合同选中的合同模板id查询合同文件，根据合同文件查询对应的变量保存到合同文件变量
		saveContractDocVaialbe(contract);
		log.info("SaveContract All Success.");
		return contractId;
	}
	
	/**
	 * 
	* 方法用途和描述: 把vo的值设置到entity中
	* @param contractVo
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	private TbContract convertEntity(ContractVo contractVo){
		TbContract contract=new TbContract();
		contract.setCreatetime(new Date()); //创建时间
		contract.setStatus(1);  //状态
		contract.setTitle(contractVo.getTitle()); //合同名称
		contract.setContractNo(contractVo.getContractNo());  //合同编号
		//设置模板
		if(!BlankUtil.isBlank(contractVo.getContractTemplatesId())){
			TbContractTemplates contractTemplates=new TbContractTemplates();
			contractTemplates.setId(Integer.parseInt(contractVo.getContractTemplatesId()));
			contract.setContractTemplates(contractTemplates);
		}else{
			log.info("ContractTemplatesid is null.");
		}
		//设置创建者
		if(!BlankUtil.isBlank(contractVo.getCreatorId())){
			TbUser creator = new TbUser();
			creator.setId(Long.parseLong(contractVo.getCreatorId()));
			contract.setCreator(creator);
		}else{
			log.info("creatorId is null.");
		}
		//设置项目
		if(!BlankUtil.isBlank(contractVo.getProjectId())){
			TbProject project = new TbProject();
			project.setProjectId(Integer.parseInt(contractVo.getProjectId()));
			contract.setProject(project);
		}else{
			log.info("projectId is null.");
		}
		//合同编号
		contract.setContractNumber(contractVo.getContractNumber());
		return contract;
	}
	
	/**
	 * 
	* 方法用途和描述:保存合同与报价表之间的关系
	* @param contractVo
	* @param contract
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	private void contractQuotations(ContractVo contractVo,TbContract contract){
		TbContractQuotations contractQuotations;
		TbQuotations quotations;
		//2.1保存合同与报价表之间的关系
		//2.1.1根据报价表id查询配置信息
		String quotationId = contractVo.getQuotationsTitle();
		log.debug("Parameters quotationId:"+quotationId);
		
		String[] quoIds = quotationId.split(",");
		int pkid;  //报价表id
		for (String id : quoIds) {
			quotations=new TbQuotations();
			pkid = Integer.parseInt(id);
			quotations.setId(pkid); 
			contractQuotations = new TbContractQuotations();
			contractQuotations.setQuotations(quotations);  //设置报价表
			contractQuotations.setContract(contract); //设置合同
			contractQuotationsDAO.save(contractQuotations);
			log.debug("Save contractQuotations success,parameter pkid:"+pkid);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据合同模板id查询该模板的合同文件,保存合同与文件之间的关系
	* @param templateId
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	private void contractDocRelationShip(TbContract contract,ContractVo contractVo){
		int pkid = 0;
		if(!BlankUtil.isBlank(contractVo) ){
			String templateId = contractVo.getContractTemplatesId();
			if(!BlankUtil.isBlank(templateId)){
				pkid = Integer.parseInt(templateId);
			}
		}
		TbContractTemplates contractTemplates = contractTemplatesDAO.findById(pkid);
		String docName ="";
		if(!BlankUtil.isBlank(contractTemplates)){
			Set set = contractTemplates.getContractTemplatesRelationship();
			if(!BlankUtil.isBlank(set)){
				Iterator iter = set.iterator();
				while (iter.hasNext()) {
					TbContractTemplatesRelationship ship = (TbContractTemplatesRelationship) iter.next();
					TbContractTemplatesDoc templateDoc = ship.getContractTemplatesDoc();
					
					TbContractDoc doc = new TbContractDoc();
					doc.setContract(contract);
					doc.setCreatetime(new Date());
					doc.setExportPath(templateDoc.getPath());//该字段的值目前先设置为模板文件的地址，在生成合同的时候将会要更新为生成的合同文件的地址
					docName = templateDoc.getDocname();
					if(!BlankUtil.isBlank(docName)){
						String exportPath = doc.getExportPath();
						String suffix = "";
						if(!BlankUtil.isBlank(exportPath)){
							suffix = exportPath.substring(exportPath.lastIndexOf("."), exportPath.length());
							docName+=suffix;
						}else{
							if(!docName.endsWith(".docx")){
								docName+=".docx";
							}
						}
					}
					doc.setFileName(docName);
					doc.setDoc(templateDoc);
					
					contractDocDAO.save(doc);
				}
				log.info("ContractDocRelationShip All Success.");
			}else{
				log.info("ContractTemplatesRelationship set is null.");
			}
		}else{
			log.info("ContractTemplates is null.");
		}
	}
	
	public static void main(String[] args) {
		String exportPath = "垃圾人.docx";
		exportPath  = exportPath.substring(exportPath.lastIndexOf("."), exportPath.length());
		System.out.println("exportPath:"+exportPath);
	}
	
	/**
	 * 
	* 方法用途和描述: 保存合同文件变量，根据合同选中的合同模板id查询合同文件，根据合同文件查询对应的变量保存到合同文件变量
	* @param contractVo
	* @author chenliang 新增日期：2013-3-7
	* @since ContractManage
	 */
	private void saveContractDocVaialbe(TbContract contract){
		int pkid = 0;
		if(!BlankUtil.isBlank(contract)){
			pkid = contract.getId();
		}
		String hql = " select a,b from TbContractDoc a,TbContractTemplatesDocVariable b "+
					 " where a.doc.id = b.templatesDoc.id "+
					 " and a.contract.id = ? ";
		List<Object[]> lists = contractDAO.query(hql, pkid);
		for (int i = 0; i < lists.size(); i++) {
			Object[] obj = lists.get(i);
			TbContractDoc contractDoc = (TbContractDoc) obj[0];
			TbContractTemplatesDocVariable templateDocVariable = (TbContractTemplatesDocVariable) obj[1];
			log.debug("Id:"+contractDoc.getId()+",variableName:"+templateDocVariable.getVariableName());
			//设置合同文件变量
			TbContractDocVariable docVariable = new TbContractDocVariable();
			TbContractDoc doc = new TbContractDoc();
			doc.setId(contractDoc.getId());
			docVariable.setContractDoc(doc);
			docVariable.setVariableName(templateDocVariable.getVariableName());
			contractDocVariableDAO.save(docVariable);
			log.info("saveContractDocVaialbe Success.");
		}
	}

	@Override
	public Integer updateContractByState(ContractVo contractVo) {
		int state = 0 ;
		if(!BlankUtil.isBlank(contractVo.getStatus()) && !"null".equals(contractVo.getStatus())){
			state = Integer.parseInt(contractVo.getStatus());
		}
		//审核不同意和待审核的状态，全部都得修改
		if(S_Contract.CONTRACT_TO_REVIEW == state || S_Contract.CONTRACT_UnApproved == state){
			updateContract(contractVo);
		}else{ //其它状态,则修改合同编号和合同名称
			updateContractContent(contractVo);
		}
		return null;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改合同编号和合同名称
	* @return
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	private Integer updateContractContent(ContractVo contractVo){
		String hql = "update TbContract tb set tb.title = ?,tb.contractNumber=? where tb.id=?";
		int pkid = 0;
		if(!BlankUtil.isBlank(contractVo.getId())){
			pkid = Integer.parseInt(contractVo.getId());
		}
		return contractDAO.executeByHQL(hql, contractVo.getTitle(),contractVo.getContractNumber(),pkid);
	}
	
	/**
	 * 
	* 方法用途和描述: 状态为2或者状态为4是才可以修改
	* @return
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	private Integer updateContract(ContractVo contractVo){
		Integer contractId;
		//1.修改合同
		TbContract contract = convertEntity(contractVo);
		if(!BlankUtil.isBlank(contractVo.getId())){
			contract.setId(Integer.parseInt(contractVo.getId()));
		}
		contractDAO.merge(contract);
		contractId = contract.getId();
		//2.修改报价表与合同的关系
		//2.1根据合同id删除报价表与合同之前的关系
		deleteRelationById(contractVo.getId());
		//2.2重新添加合同与报价表之间的关系
		contractQuotations(contractVo,contract);
		//3.保存合同选中的合同模板中的合同文件与合同之间的关系
		contractDocRelationShip(contract,contractVo);
		//4.保存合同文件的变量，根据合同选中的合同模板id查询合同文件，根据合同文件查询对应的变量保存到合同文件变量
		//4.1根据合同id删除合同文件的变量
		deleteContractDocVariableById(contractVo.getId());
		//4.2重新添加合同文件的变量
		saveContractDocVaialbe(contract);
		return contractId;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据合同id删除合同与报价表之间的关系
	* @param contractId
	* @author chenliang 新增日期：2013-3-5
	* @since ContractManage
	 */
	private void deleteRelationById(String contractId){
		int pkid = 0;
		if(!BlankUtil.isBlank(contractId)){
			pkid = Integer.parseInt(contractId);
		}
		String hql = "delete TbContractQuotations where contract.id=?";
		contractDAO.executeByHQL(hql, pkid);
	}
	
	/**
	 * 
	* 方法用途和描述: 根据合同id删除合同文件的变量值
	* @author chenliang 新增日期：2013-3-7
	* @since ContractManage
	 */
	private void deleteContractDocVariableById(String contractId){
		int pkid = 0;
		if(!BlankUtil.isBlank(contractId)){
			pkid = Integer.parseInt(contractId);
		}
		String hql = "delete TbContractDocVariable where contractDoc.id in (select id from TbContractDoc where contract.id = ? ) ";
		contractDAO.executeByHQL(hql, pkid);
	}
	
	/**
	 * 
	* 方法用途和描述: 生成正式合同编号
	* 规则：KX+日期+流水号
	* example:KXyyyyMMdd0001
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String officialContractNo(Integer number){
		//合同编号:KX+日期+流水号
		StringBuffer contractNO = new StringBuffer("KX");
		//合同编号：拼接日期-yyyyMMdd
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		contractNO.append(date);
		//合同编号:流水号---查询id最大的正式合同，获取合同编号，流水号追加
		if(BlankUtil.isBlank(number)){
			number = getMaxContractNo(date);
		}else{
			number++;
		}
		//判断流水号的长度是否是规定的4位数长度,不足4为则在前面追加0
		String strNum = ""+number;
		StringBuffer nn = new StringBuffer();
		if(nn.length()<4){
			for (int i = 0; i < (4-strNum.length()); i++) {
				nn.append("0");
			}
		}
		nn.append(strNum);
		contractNO.append(nn.toString());
		//判断生成的合同编号是否在数据库中已经存在，不存在就返回，存在就重新生成
		if(!queryByContractNo(contractNO.toString())){
			return contractNO.toString();
		}else{
			return officialContractNo(number);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 获取最大正式合同编号
	* @return
	* @author chenliang 新增日期：2013-4-16
	* @since ContractManage
	 */
	private int getMaxContractNo(String date){
		int number=1;
		StringBuffer sb = new StringBuffer();
		sb.append("select contract_no from tb_contract where id = (select max(id) from tb_contract) ");
		List lists = contractDAO.queryAllContract(sb.toString());
		if(!BlankUtil.isBlank(lists)){
			String contractNo = (String) lists.get(0);
			if(!BlankUtil.isBlank(contractNo)){
				if(contractNo.length()>10){
					String time = contractNo.substring(2,10);
					String num = contractNo.substring(10, contractNo.length());
					if(date.equals(time)){ //同一天合同编号流水号追加
						if(!BlankUtil.isBlank(num)){
							number = Integer.parseInt(num);
							number++;
						}
					}
				}
			}
		}
		return number;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据正式合同编号查询
	* @param contractNo
	* @return true 该编号已经存在
	* @return false 该编号不存在
	* @author chenliang 新增日期：2013-4-16
	* @since ContractManage
	 */
	private boolean queryByContractNo(String contractNo){
		String  hql = "from TbContract tb where contractNo=?";
		List<TbContract> lists = contractDAO.find(hql, contractNo);
		if(null!=lists && lists.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 生成临时合同编号
	* 规则：KX+日期+流水号+L
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String temporaryContractNo(Integer number){
		//合同编号:KX+日期+流水号
		StringBuffer contractNO = new StringBuffer("KX");
		//合同编号：拼接日期-yyyyMMdd
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		contractNO.append(date);
		//合同编号:流水号 ---查询id最大的正式合同，获取合同编号，流水号追加
		if(BlankUtil.isBlank(number)){
			number = getMaxTemporaryContractNo(date);
		}else{
			number++;
		}
		//判断流水号的长度是否是规定的8位数长度,不足8为则在前面追加0
		String strNum = ""+number;
		StringBuffer nn = new StringBuffer();
		if(nn.length()<4){
			for (int i = 0; i < (4-strNum.length()); i++) {
				nn.append("0");
			}
		}
		nn.append(strNum);
		contractNO.append(nn.toString());
		//合同编号：拼接L
		contractNO.append("L");
		if(!queryByTemporaryContractNo(contractNO.toString())){
			return contractNO.toString();
		}else{
			return temporaryContractNo(number);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 查询临时合同最大合同编号
	* @return
	* @author chenliang 新增日期：2013-4-16
	* @since ContractManage
	 */
	private int getMaxTemporaryContractNo(String date){
		int number=1;
		StringBuffer sb = new StringBuffer();
		sb.append("select contract_no from tb_contract_temporary where id = (select max(id) from tb_contract_temporary) ");
		List lists = contractDAO.queryAllContract(sb.toString());
		if(!BlankUtil.isBlank(lists)){
			String contractNo = (String) lists.get(0);
			if(!BlankUtil.isBlank(contractNo)){
				if(contractNo.length()>10){
					String time = contractNo.substring(2,10);
					String num = contractNo.substring(10, contractNo.length());
					if(date.equals(time)){
						if(!BlankUtil.isBlank(num)){
							number = Integer.parseInt(num);
							number++;
						}
					}
				}
			}
		}
		return number;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据临时合同编号查询
	* @param contractNo
	* @return true 该编号已经存在
	* @return false 该编号不存在
	* @author chenliang 新增日期：2013-4-16
	* @since ContractManage
	 */
	private boolean queryByTemporaryContractNo(String contractNo){
		String  hql = "from TbContractTemporary tb where contractNo=?";
		List<TbContract> lists = contractDAO.find(hql, contractNo);
		if(null!=lists && lists.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void checkContract( CheckedVo checkVo) throws Exception {
		Integer type = Integer.parseInt(checkVo.getType());
		Integer contractId =  Integer.parseInt(checkVo.getId());
		String describe = checkVo.getDescribe();
		String result = checkVo.getResult();
		String userId = checkVo.getUser_id();
		if(type==null || contractId==null ||describe==null ||result==null){
			return ; 
		}
		TbContract contract = this.getByPk(contractId);
		if("0".equals(result)){ //审核同意
			contract.setStatus(S_Contract.CONTRACT_Approved);
			//同意之后完成代办任务
		}else if("1".equals(result)){//审核不同意
			contract.setStatus(S_Contract.CONTRACT_UnApproved);
			String  param = "?contractIds="+ contract.getId() + "&backUrl=/view/main.jsp";
			taskService.startTask(null, Long.parseLong(userId), TaskName.audit_contract_result+"("+contract.getTitle()+")", 
					contract.getProject().getProjectId(), contract.getCreator().getId(), BusinessConstants.audit_contract_result, 
					checkVo.getDescribe(), param,BusinessConstants.unContract_pkid+contractId);
		}
		//完成待办任务
		TaskVo taskVo = new TaskVo();
		taskVo.setParamPkid(BusinessConstants.contract_pkid+contractId);
		taskService.finishTask(taskVo);
		this.update(contract);
	}

	@Override
	public void submitCheck(CheckedVo checkedVo) {

		int Id = 0; //合同id
		Long snpnorid = 0l; //创建人员
		Long personInChargeId = 0l; //负责人
		String param = "";//参数
		Integer projectId = null;
		try {
			if(!BlankUtil.isBlank(checkedVo.getId())){
				Id = Integer.parseInt(checkedVo.getId());
			}else{
				throw new NullPointerException("ContractId is null");
			}
			if(!BlankUtil.isBlank(checkedVo.getSnpnorId())){
				snpnorid = Long.parseLong(checkedVo.getSnpnorId());
			}else{
				throw new NullPointerException("SnpnorId is null");
			}
			if(!BlankUtil.isBlank(checkedVo.getUser_id())){
				personInChargeId = Long.parseLong(checkedVo.getUser_id());
			}
			if(!BlankUtil.isBlank(checkedVo.getProjectId())){
				projectId = Integer.parseInt(checkedVo.getProjectId());
			}else{
				throw new NullPointerException("ProjectId is null");
			}
			param="?id="+Id+"&type=1";
			
			TbContract contract = contractDAO.findById(Id);
			contract.setStatus(S_Contract.CONTRACT_TO_REVIEWING);
			contractDAO.saveOrUpdate(contract);
			//给审核人提交任务
			taskService.startTask(null, snpnorid, TaskName.audit_contract+"("+contract.getTitle()+")", projectId, 
					personInChargeId, BusinessConstants.audit_contract, checkedVo.getDescribe(), param,BusinessConstants.contract_pkid+Id);

			//完成打回的合同审核任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.unContract_pkid+Id);
			taskService.finishTask(taskVo);
			LOGGER.info("Submit Success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
