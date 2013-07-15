package cn.com.kxcomm.contractmanage.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractQuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IConfigModelsService;
import cn.com.kxcomm.contractmanage.service.IQuotationsService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.status.S_ConfigModels;
import cn.com.kxcomm.contractmanage.status.S_Quotations;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.ExportQuotations;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("quotationsService")
public class QuotationsService  extends
CommonService<TbQuotations> implements IQuotationsService {
	@Autowired(required=true)
	private QuotationsDAO quotationsDAO;
	@Autowired(required=true)
	private ITaskService taskService;
	@Autowired(required=true)
	private QuotationsDataDAO quotationsDataDAO;
	@Autowired(required=true)
	private IConfigModelsService configModelsService;//配置单总表
	@Autowired(required=true)
	private ContractQuotationsDAO contractQuotationsDAO; 
	
	private static final Logger LOG = Logger.getLogger(QuotationsService.class);
	
	private Parameters para = Parameters.getInstance();

	@Override
	public CommonDAO<TbQuotations> getBindDao() {
		return quotationsDAO;
	}
	//分页查询所有的报价表
	public PageInfo<QuotationsVo> listQuotations(PageInfo<QuotationsVo> pageInfo,Long loginUserId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.create_time,tu.user_name,a.down_url,a.id,a.status,a.title, ");
		sql.append(" tt.after_discount_now_price,tt.after_discount_price,tt.catalog_total_price,tt.total_price ");
	    sql.append(" from tb_user tu,tb_quotations a, ");
	    sql.append(" ( select t1.quotations_id, ");
	    sql.append(" sum(t2.after_discount_now_price*t1.quantity) after_discount_now_price, ");
	    sql.append(" sum(t2.after_discount_price*t1.quantity) after_discount_price, ");
	    sql.append(" sum(t2.catalog_total_price*t1.quantity) catalog_total_price, ");
	    sql.append(" sum(t2.total_price*t1.quantity) total_price ");
	    sql.append(" from tb_quotations_data t1,tb_config_models t2 ");
	    sql.append(" where t1.config_models_id=t2.id group by t1.quotations_id ");
	    sql.append(" ) tt ");
	    sql.append(" where  tu.id = a.creator_id and a.id = tt.quotations_id  ");
	    if(loginUserId!=null){
			sql.append(" and a.creator_id = "+loginUserId);
		}
	    sql.append(" order by a.create_time desc");
	    StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(*) as count from ("+sql.toString()+") tt1 ");
		int count = 0;
		List list = null;
		count = quotationsDAO.findCount(countSql.toString());
		list = quotationsDAO.findByPage(sql.toString(), pageInfo);
		
		//钱转换
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		
		List<QuotationsVo> listQuotationsVo=new ArrayList<QuotationsVo>();
		Object[] obj = new Object[list.size()];	
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			QuotationsVo quotationsVo=new QuotationsVo();
			quotationsVo.setCreatetime(obj[0].toString());
			quotationsVo.setCreatorName(obj[1].toString());
			quotationsVo.setDownUrl(obj[2].toString());
			quotationsVo.setId(obj[3].toString());
		//	quotationsVo.setProjectName(obj[4].toString());
			quotationsVo.setStatus(obj[4].toString());
			quotationsVo.setTitle(obj[5].toString());
	//		quotationsVo.setProjectId(obj[7]!=null?obj[7].toString():"0");
			quotationsVo.setAfterDiscountNowPrice(nf.format(obj[6]!=null?obj[6]:0));
			quotationsVo.setAfterDiscountPrice(nf.format(obj[7]!=null?obj[7]:0));
			quotationsVo.setCatalogTotalPrice(nf.format(obj[8]!=null?obj[8]:0));
			quotationsVo.setTotalPrice(nf.format(obj[9]!=null?obj[9]:0));
			listQuotationsVo.add(quotationsVo);
		}
		PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listQuotationsVo);
		return page;
	}
	
	//查询所有的报价单
	public List<QuotationsVo> queryAllQuotations() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT id,status,title,create_time,down_url from tb_quotations ");
		List list = quotationsDAO.queryAllQuotations(sql.toString());
		List<QuotationsVo> listQuotationsVo = new ArrayList<QuotationsVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			QuotationsVo quotationsVo=new QuotationsVo();
			quotationsVo.setId(obj[0].toString());
			quotationsVo.setStatus(obj[1].toString());
			quotationsVo.setTitle(obj[2].toString());
			quotationsVo.setCreatetime(obj[3].toString());
			quotationsVo.setDownUrl(obj[4].toString());
	//		quotationsVo.setDataSourceName(obj[4].toString());
			listQuotationsVo.add(quotationsVo);
		}
		return listQuotationsVo;
	}
	
	//根据合同查询所有的报价单
	public List<QuotationsVo> queryAllQuotationseByContract(int id) 
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT q.id,q.status,q.title,q.create_time,q.down_url ");
		sql.append("  from tb_quotations q,tb_contract_quotations cq,tb_contract c  ");
		sql.append(" WHERE q.id=cq.quotations_id AND c.id=cq.contract_id AND c.id=? ");
		List list = this.quotationsDAO.queryAllQuotations(sql.toString(),id);
		List<QuotationsVo> listQuotationsVo = new ArrayList<QuotationsVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			QuotationsVo quotationsVo=new QuotationsVo();
			quotationsVo.setId(obj[0].toString());
			quotationsVo.setStatus(obj[1].toString());
			quotationsVo.setTitle(obj[2].toString());
			quotationsVo.setCreatetime(obj[3].toString());
			quotationsVo.setDownUrl(obj[4].toString());
			listQuotationsVo.add(quotationsVo);
		}
		return listQuotationsVo;
	}
	
	@Override
	public List<QuotationsVo> listAll(Long loginUserId,String contractId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select tb1.create_time,tb1.down_url,tb1.id,tb1.title,b.after_discount_now_price,b.after_discount_price,b.catalog_total_price,b.total_price"); //tp.project_name,tp.project_id,
		sql.append(" from  ");
		sql.append(" (select * from tb_quotations a where a.id not in (select quotations_id from tb_contract_quotations)) tb1  ");
		sql.append(" inner join ");
		sql.append(" (select * from tb_quotations a where a.id not in (select quotations_id from tb_contract_temporary_quotations)) tb2 ");
		sql.append(" on tb1.id=tb2.id, ");
		sql.append(" (select t1.quotations_id,sum(t2.after_discount_now_price*t1.quantity) after_discount_now_price,sum(t2.after_discount_price*t1.quantity) after_discount_price, " );
		sql.append(" sum(t2.catalog_total_price*t1.quantity) catalog_total_price,sum(t2.total_price*t1.quantity) total_price from tb_quotations_data t1,tb_config_models t2 ");
		sql.append(" where t1.config_models_id=t2.id group by t1.quotations_id ) b "); //, tb_project tp
		sql.append(" where tb1.id = b.quotations_id and tb1.creator_id =?  and tb1.status = ?"); //and tp.project_id= tb1.project_id
		sql.append( " order by tb1.create_time desc" );
		List list = quotationsDAO.findByPage(sql.toString(), loginUserId,S_Quotations.QUOTATIONS_PASSED);
		List<QuotationsVo> volist = new ArrayList<QuotationsVo>(); 
		Object[] obj = new Object[list.size()];	
		//钱转换
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			QuotationsVo quotationsVo = new QuotationsVo();
			quotationsVo.setCreatetime(obj[0].toString());
			quotationsVo.setDownUrl(obj[1].toString());
			quotationsVo.setId(obj[2].toString());
//			quotationsVo.setProjectName(obj[3].toString());
			quotationsVo.setTitle(obj[3].toString());
//			quotationsVo.setProjectId(""+obj[5]);
			quotationsVo.setAfterDiscountNowPrice(nf.format(obj[4]!=null?obj[4]:0));
			quotationsVo.setAfterDiscountPrice(nf.format(obj[5]!=null?obj[5]:0));
			quotationsVo.setCatalogTotalPrice(nf.format(obj[6]!=null?obj[6]:0));
			quotationsVo.setTotalPrice(nf.format(obj[7]!=null?obj[7]:0));
			volist.add(quotationsVo);
		}
		//根据合同id查询该合同下的
		if(!BlankUtil.isBlank(contractId) && !"null".equals(contractId)){
			volist = queryContractQuotationsById(contractId,volist);
		}
		return volist;
	}
	
	private List<QuotationsVo> queryContractQuotationsById(String contractId,List<QuotationsVo> volist){
		Long contId = Long.parseLong(contractId);
		StringBuilder sql = new StringBuilder();
		sql.append("select tb1.create_time,tb1.down_url,tb1.id,tp.project_name,tb1.title,tp.project_id,b.after_discount_now_price,b.after_discount_price,b.catalog_total_price,b.total_price");
		sql.append(" from  ");
		sql.append(" (select * from tb_quotations a where a.id in (select tc.quotations_id from tb_contract_quotations tc where tc.contract_id = ?)) tb1,  ");
		sql.append(" (select t1.quotations_id,sum(t2.after_discount_now_price*t1.quantity) after_discount_now_price,sum(t2.after_discount_price*t1.quantity) after_discount_price, " );
		sql.append(" sum(t2.catalog_total_price*t1.quantity) catalog_total_price,sum(t2.total_price*t1.quantity) total_price from tb_quotations_data t1,tb_config_models t2 ");
		sql.append(" where t1.config_models_id=t2.id group by t1.quotations_id ) b, tb_project tp ");
		sql.append(" where tb1.id = b.quotations_id and tp.project_id= tb1.project_id ");
		sql.append( " order by tb1.create_time desc" );
		List lists = contractQuotationsDAO.findByPage(sql.toString(), contId);
		Object[] obj = new Object[lists.size()];	
		//钱转换
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			QuotationsVo quotationsVo = new QuotationsVo();
			quotationsVo.setCreatetime(obj[0].toString());
			quotationsVo.setDownUrl(obj[1].toString());
			quotationsVo.setId(obj[2].toString());
			quotationsVo.setProjectName(obj[3].toString());
			quotationsVo.setTitle(obj[4].toString());
			quotationsVo.setProjectId(""+obj[5]);
			quotationsVo.setAfterDiscountNowPrice(nf.format(obj[6]!=null?obj[6]:0));
			quotationsVo.setAfterDiscountPrice(nf.format(obj[7]!=null?obj[7]:0));
			quotationsVo.setCatalogTotalPrice(nf.format(obj[8]!=null?obj[8]:0));
			quotationsVo.setTotalPrice(nf.format(obj[9]!=null?obj[9]:0));
			volist.add(quotationsVo);
		}
		return volist;
		
	}
	
	@Override
	public List<QuotationsVo> listByContractId(String contractId,Integer projectId) {
		Integer cid = null;
		if(!BlankUtil.isBlank(contractId)){
			cid = Integer.parseInt(contractId);
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" select distinct a.create_time,tu.user_name,a.down_url,a.id aid,tp.project_name,a.status,a.title,b.id bid ");
		hql.append(" from tb_user tu,tb_project tp,tb_quotations a left join tb_contract_quotations b ");
		hql.append(" on a.id = b.quotations_id ");
		if(null!=cid){
			hql.append(" and b.contract_id = ? ");
		}
		hql.append(" where  tu.id = a.creator_id and tp.project_id= a.project_id and a.status=4 ");
		if(null!=projectId){
			hql.append(" and tp.project_id = ? ");
		}
		hql.append(" and a.id not in (select c.quotations_id from tb_contract_quotations c where 1=1 ");
		if(null!=cid){
			hql.append(" and c.contract_id != ? ");
		}
		hql.append(" ) ");
		List<QuotationsVo> list = quotationsDAO.query(hql.toString(), cid,projectId,cid);
		return list;
	}
	
	/**
	* 
	* 方法用途和描述: 修改报价表状态
	* @return
	* @author luj 新增日期：2013-3-26
	* @since ContractManage
	*/
	//@Override
	public void merge(TbQuotations quotations) {
		quotationsDAO.merge(quotations);
	}
	
	@Override
	public void checkQuotation(String quotationsId,CheckedVo checkedVo) {
		int quoId=0;//报价表id
		Long uid = 0L;  //创建人员
		String param = ""; //请求参数
		Integer projectId = null; //项目id
		Long personInChangeId=0L;//负责人
		if(!BlankUtil.isBlank(quotationsId) ){
			quoId=Integer.parseInt(quotationsId);
		}
		if(!BlankUtil.isBlank(checkedVo.getSnpnorId())){
			uid = Long.parseLong(checkedVo.getSnpnorId());
		}
		if(!BlankUtil.isBlank(checkedVo.getProjectId()) && !"null".equals(checkedVo.getProjectId())){
			projectId = Integer.parseInt(checkedVo.getProjectId());
		}
		TbQuotations quotations=quotationsDAO.findById(quoId);
		try {
			if(!BlankUtil.isBlank(checkedVo)){
				if(BusinessConstants.check_pass.equals(checkedVo.getResult())){  //审核通过
					quotations.setStatus(S_Quotations.QUOTATIONS_PASSED);
				}else if(BusinessConstants.check_unpass.equals(checkedVo.getResult())){ //审核不通过,驳回
					//根据报价表id查询报价表的创建人
					personInChangeId = quotations.getCreator().getId();
					//不通过，需要通知销售人员，重新制作报价表
					taskService.startTask(null, uid, TaskName.sale_make_quatitions+"("+quotations.getTitle()+")", 
							projectId, personInChangeId, BusinessConstants.task_quotationCheck_Unpass, checkedVo.getDescribe(), param,BusinessConstants.unQuotation_pkid+quotations.getId());
					//修改报价表的状态
					quotations.setStatus(S_Quotations.QUOTATIONS_FAIL);
				}
				//完成待办任务
				LOG.info("BusinessConstants.quotation_pkid+quotations.getId():"+BusinessConstants.quotation_pkid+quotations.getId());
				TaskVo taskVo = new TaskVo();
				taskVo.setParamPkid(BusinessConstants.quotation_pkid+quotations.getId());
				taskService.finishTask(taskVo);
				//修改
				quotationsDAO.merge(quotations);
			}
		} catch (Exception e) {
			LOGGER.error("checkRapaport error.",e);
		}
	}
	
	@Override
	public void saveAuotions(HashMap<String, String> map,TbQuotations model) throws Exception{
		try {
			String baojiaTable = map.get("baoJiaTable"); //报价总表的json
			Long userId = map.get("userId")!=null?Long.parseLong(map.get("userId")):0L; //当前用户id
			//保存报价表与机型配置的关系
			if(!BlankUtil.isBlank(baojiaTable)){
				JSONArray jsonArray = JSONArray.fromObject(baojiaTable);
				List<ContractQuotationsVo> volist = JSONArray.toList(jsonArray,ContractQuotationsVo.class);

				String fileName = System.currentTimeMillis()+".xlsx";
				String exportDoc = para.exportExcelPath+fileName;

				//1.保存报价表
				saveQuotations(model,userId,fileName);
				LOGGER.debug("model:"+model.toString());

				//2.保存报价总表，即保存报价表与配置单的关系
				saveQuotationsData(volist,model);

				//3.生成报价总表的excel到指定目录
				//3.1、查询需要生成文件的数据
				String[] idd = new String[volist.size()];
				for (int i = 0; i < volist.size(); i++) {
					ContractQuotationsVo vo = volist.get(i);
					idd[i] = vo.getConfigModelId();
				}
				List<List<DataRecordVo>> dataRecordList = configModelsService.listConfigModelByIds(idd);
				LOGGER.info("volist:"+volist.size());
				//3.2、生成excel文件
				ExportQuotations ex = new ExportQuotations();
				String templatesDoc = para.templatesExcelPath+"quotations_templates.xlsx";
				ex.export(templatesDoc, exportDoc, dataRecordList);
				LOGGER.info("生成报价表成功.");

			}else{
				throw new NullPointerException("SaveAuotions baojiaTable is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("saveAuotions error.",e);
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 保存报价表
	* @param model
	* @param userId
	* @param fileName
	* @author chenliang 新增日期：2013-4-9
	* @since ContractManage
	 */
	private void saveQuotations(TbQuotations model,Long userId,String fileName) throws Exception{
		try {
			TbUser user=new TbUser();
			user.setId(userId);
			model.setCreator(user); //创建者
			model.setCreatetime(new Date()); //创建时间
			model.setStatus(S_Quotations.QUOTATIONS_VALID); //状态
			model.setDownUrl(fileName); //下载文件名
			if(model.getProject()!=null){
				TbProject project=new TbProject();
				project.setProjectId(model.getProject().getProjectId());
				model.setProject(project); //项目
			}
			quotationsDAO.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 保存报价总表，即保存报价表与配置单的关系
	* @author chenliang 新增日期：2013-4-9
	* @since ContractManage
	 */
	private void saveQuotationsData(List<ContractQuotationsVo> volist,TbQuotations model) throws Exception{
		try {
			if(!BlankUtil.isBlank(volist)){
				for (ContractQuotationsVo vo : volist) {
					int quantity = 0; //数量
					int configModelId=0; //配置单id
					if(!BlankUtil.isBlank(vo.getConfigModelId())){
						configModelId = Integer.parseInt(vo.getConfigModelId());
					}else{
						throw new NullPointerException("SaveQuotationsData ConfigModelId is null.");
					}
					if(!BlankUtil.isBlank(vo.getQuantity())){
						quantity = Integer.parseInt(vo.getQuantity());
					}
					
					TbQuotationsData data = new TbQuotationsData();
					TbConfigModels configModels = new TbConfigModels();
					configModels.setId(configModelId);
					data.setConfigModels(configModels);
			
					TbQuotations quotations = new TbQuotations();
					quotations.setId(model.getId());
					data.setQuotations(quotations);  //报价表
					data.setName(vo.getQuotationName()); //报价总表名称 
					data.setProjectName(vo.getProjectName()); //项目
					data.setOrdered(0);// 已下单数默认设置为0
					data.setQuantity(quantity); //数量\
					//普通报价表
					quotations.setQuotationType(1);
					quotationsDataDAO.save(data);
				}
				LOGGER.info("SaveQuotationsData success.");
			}else{
				throw new NullPointerException("SaveQuotationsData volist is null.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public void submitCheck(CheckedVo checkedVo) throws Exception{
		int quotationsId = 0; //报价表id
		Long snpnorid = 0l; //创建人员
		Long personInChargeId = 0l; //负责人
		String param = "";//参数
		Integer projectId = null;
		try {
			if(!BlankUtil.isBlank(checkedVo.getId())){
				quotationsId = Integer.parseInt(checkedVo.getId());
			}else{
				throw new NullPointerException("QuotationsId is null");
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
			}
//			else{
//				//throw new NullPointerException("ProjectId is null");
//			}
			param="qoutaionId="+quotationsId+"&projectId="+projectId+"&status="+S_Quotations.QUOTATIONS_NAUDITED;
			
			//修改报价表的状态
			TbQuotations quotations = quotationsDAO.findById(quotationsId);
			quotations.setStatus(S_Quotations.QUOTATIONS_NAUDITED);
			quotationsDAO.merge(quotations);
			LOG.info("BusinessConstants.quotation_pkid+quotations.getId():"+BusinessConstants.quotation_pkid+quotations.getId());
			//给审核人提交任务
			taskService.startTask(null, snpnorid, TaskName.checked_quotations+"("+quotations.getTitle()+")", 
					projectId, personInChargeId, BusinessConstants.task_quotationCheck, checkedVo.getDescribe(), param,BusinessConstants.quotation_pkid+quotations.getId());
			//修改配置单的状态
			Set set = quotations.getQuotationsMembers();
			if(!BlankUtil.isBlank(set)){
				Iterator iter = set.iterator();
				while (iter.hasNext()) {
					TbQuotationsData data = (TbQuotationsData) iter.next();
					TbConfigModels configModels = data.getConfigModels();
					TbConfigModels tbconfigModels = configModelsService.getByPk(configModels.getId());
					tbconfigModels.setStatus(S_ConfigModels.CONFIG_CHECKED);
					configModelsService.update(tbconfigModels);
				}
			}
			
			//完成总经理不通过报价表审核的任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.unQuotation_pkid+quotations.getId());
			taskService.finishTask(taskVo);
			LOG.info("Submit Success.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
