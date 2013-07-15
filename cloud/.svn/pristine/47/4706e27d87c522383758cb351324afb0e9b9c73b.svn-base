package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemporaryDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemporaryQuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.ProjectDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.dao.TemporaryDataToFormalDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporaryQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.entity.TbTemporaryDataToFormal;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IContractTemporaryService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.status.S_Contract;
import cn.com.kxcomm.contractmanage.status.S_Quotations;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemporaryVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.GenerateContractNo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("contractTemporaryService")
public class ContractTemporaryService extends CommonService<TbContractTemporary> implements IContractTemporaryService{
	@Autowired(required=true)
	private ContractTemporaryDAO contractTemporaryDAO;
	@Autowired(required=true)
	private ContractTemporaryQuotationsDAO contractTemporaryQuotationsDAO;
	@Autowired(required=true)
	private ContractTemporaryQuotationsService contractTemporaryQuotationsService;
	@Autowired(required=true)
	private QuotationsDAO quotationsDAO;
	@Autowired(required=true)
	private UserDAO userDAO;
	@Autowired(required=true)
	private ProjectDAO projectDAO;
	@Autowired(required=true)
	private QuotationsDataDAO quotationsDataDAO;
	@Autowired(required=true)
	private ConfigModelsService configModelsService;
	@Autowired(required=true)
	private TemporaryDataToFormalDAO temporaryDataToFormalDAO;
	@Autowired(required=true)
	private ContractQuotationsService contractQuotationsService; //报价总表的dao
	@Autowired(required=true)
	private ITaskService taskService;
	@Override
	public CommonDAO<TbContractTemporary> getBindDao() {
		return contractTemporaryDAO;
	}

	@Override
	public boolean addContractTemporary(ContractTemporaryVo vo,Long userId) {
	   if(vo==null){
		   return false;
	   }
	   String[] ids = vo.getQuotationsId().split(",");
	   for(int i = 0; i<ids.length;i++){
		   Integer id = Integer.parseInt(ids[i]);
		   Integer count = this.contractQuotationsService.getContractByQuotationId(id);
			if(count!=null &&count>0){
				return false;
			}
			count = this.contractTemporaryQuotationsService.getContractTemporaryByQuotationId(id);
			if(count!=null && count>0){
				return false;
			}
	   }
		
	   TbContractTemporary entity = new TbContractTemporary();
	   String generateNo = GenerateContractNo.generate();
	   entity.setContractNo(generateNo);
	   entity.setCreatetime(new Date());
	   TbUser u= new TbUser();
	   u.setId(userId);
	   entity.setCreator(u);
	   entity.setFileName(vo.getFileForPDF());
	   entity.setTitle(vo.getTitle());
	   entity.setStatus(S_Contract.CONTRACT_TO_REVIEW);
	   Integer temporaryId = (Integer) contractTemporaryDAO.save(entity);
	   if(temporaryId!=null){
		   LOGGER.info("addContractTemporary succes! temporaryId:"+temporaryId);
	   }
	  Double totalPrice = 0d;
	   for(int i = 0; i<ids.length;i++){
		   Integer id = Integer.parseInt(ids[i]);
		   TbContractTemporaryQuotations ct = new TbContractTemporaryQuotations();
		   ct.setContractTemporary(entity);
		   TbQuotations quotations = quotationsDAO.findById(id);
		   ct.setQuotations(quotations);
		   Iterator<TbQuotationsData> datas = quotations.getQuotationsMembers().iterator();
		   while(datas.hasNext()){
			   TbQuotationsData tmp = datas.next();
			   double price = tmp.getQuantity() * tmp.getConfigModels().getTotalPrice();
			   totalPrice+=price;
		   }
		   this.contractTemporaryQuotationsService.save(ct);
	   }
	   entity.setContractTotalPrice(totalPrice);
	   contractTemporaryDAO.saveOrUpdate(entity);
		return true;
	}

	@Override
	public PageInfo<ContractTemporaryVo> queryAll(PageInfo<ContractTemporaryVo> pageInfo,Long userId,ContractTemporaryVo  paramVo) {
		StringBuilder hql= new StringBuilder();
		hql.append(" from TbContractTemporary po where 1=1 ");
		StringBuilder where= new StringBuilder();
		if(!BlankUtil.isBlank(paramVo.getCreateTime())){
			where.append( " and date_format(po.createtime,'%Y-%m-%d') >= date_format('"+paramVo.getCreateTime()+"','%Y-%m-%d') ");
		}else{
			paramVo.setCreateTime(null);
		}
		if(!BlankUtil.isBlank(paramVo.getEndTime())){
			where.append(" and date_format(po.createtime,'%Y-%m-%d') <= date_format('"+paramVo.getEndTime()+"','%Y-%m-%d') ");
		}else{
			paramVo.setEndTime(null);
		}
		if(!BlankUtil.isBlank(paramVo.getId())){
			where.append(" and po.id = "+paramVo.getId());
		}
		if(!BlankUtil.isBlank(userId)){
			where.append(" and po.creator.id = "+userId);
		}
		hql.append(where);
		hql.append(" order by po.createtime desc ");
		PageInfo<TbContractTemporary> p =new PageInfo<TbContractTemporary>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		if(pageInfo.getPageSize()<=0){
			pageInfo.setPageSize(10);
		}
		p.setPageSize(pageInfo.getPageSize());
		LOGGER.info("pageInfo.getCurrentPage():"+pageInfo.getCurrentPage()+",pageInfo.getPageSize():"+pageInfo.getPageSize());
		List<TbContractTemporary> list=contractTemporaryDAO.pageInfoQuery(hql.toString(), p);
		int count = contractTemporaryDAO.findTotalCount("select count(*) from TbContractTemporary po where 1=1 " +where.toString());
		List<ContractTemporaryVo> rs=new ArrayList<ContractTemporaryVo>();
		for(TbContractTemporary tmp:list){
			ContractTemporaryVo vo=new ContractTemporaryVo(); 
			vo.setContractNo(tmp.getContractNo());
			vo.setCreateTime(""+tmp.getCreatetime());
			vo.setCreatorId(""+tmp.getCreator().getId());
			vo.setCreatorName(tmp.getCreator().getUserName());
			vo.setFileForPDF(tmp.getFileName());
			vo.setId(""+tmp.getId());
			vo.setStatus(""+tmp.getStatus());
			vo.setTitle(tmp.getTitle());
			vo.setPrice(tmp.getContractTotalPrice()+"");
		    rs.add(vo);
		}
		PageInfo<ContractTemporaryVo> page = new PageInfo<ContractTemporaryVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),rs);
		return page;
	}

	@Override
	public List<ContractQuotationsVo> queryAllConfigModels(String ids) {
		boolean is = ids.endsWith(",");
		if(is){
			ids = ids.substring(0, ids.length()-1);
		}
		return queryConfigModelsList(ids);
	}

	@Override
	public ContractTemporaryVo getById(Integer ctId) {
		TbContractTemporary t = this.contractTemporaryDAO.findById(ctId);
		ContractTemporaryVo v = new ContractTemporaryVo();
		v.setContractNo(t.getContractNo());
		v.setCreateTime(""+t.getCreatetime());
		v.setCreatorId(""+t.getCreator().getId());
		v.setCreatorName(t.getCreator().getUserName());
		v.setFileForPDF(t.getFileName());
		v.setId(""+t.getId());
		v.setStatus(""+t.getStatus());
		v.setTitle(t.getTitle());
		return v;
	}

	@Override
	public boolean editContractTemporary(ContractTemporaryVo vo) {
		TbContractTemporary entity = this.contractTemporaryDAO.findById(Integer.parseInt(vo.getId()));
		entity.setTitle(vo.getTitle());
		this.contractTemporaryDAO.saveOrUpdate(entity);
		return true;
	}


	/**
	 * 
	 * 根据ids查询多个临时合同的报价表下的机型配置信息
	 * 
	 * @param ids
	 * @return
	 * @author zhangjh 新增日期：2013-4-18
	 * @since ContractManage
	 */
	public List<ContractQuotationsVo> queryConfigModelsList(String ids){
		StringBuffer sql =  new StringBuffer();		
		sql.append("select b.projectName,a.quotations.title,b.quantity,c.totalPrice*b.quantity,c.name,a.quotations.id,c.id,b.ordered,c.totalPrice,b.id,b.toUsed,a.contractTemporary.id " +
				"from TbContractTemporaryQuotations a,TbQuotationsData b,TbConfigModels c " +
				"where a.quotations.id = b.quotations.id and b.configModels.id = c.id and a.contractTemporary.id in("+ids+")");
		PageInfo<TbContractTemporaryQuotations> page = new PageInfo<TbContractTemporaryQuotations>();
		List list = contractTemporaryQuotationsDAO.pageInfoQuery(sql.toString(),page);
		Object[] obj = new Object[list.size()];
		List<ContractQuotationsVo> detailvoList = new ArrayList<ContractQuotationsVo>();
		int ordered=0,quantity=0,remainingQuantity=0;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractQuotationsVo detailVo = new ContractQuotationsVo();
			detailVo.setProjectName(""+obj[0]);
			detailVo.setQuotationName(""+obj[1]);
			detailVo.setQuantity(""+obj[2]);
			detailVo.setTotalPrice(""+obj[3]);
			detailVo.setConfigModelName(""+obj[4]);
			detailVo.setQuotationsId(""+obj[5]);
			detailVo.setConfigModelId(""+obj[6]);
			detailVo.setOrdered(""+obj[7]);
			if(!BlankUtil.isBlank(detailVo.getQuantity())){
				quantity = Integer.parseInt(detailVo.getQuantity());//总数
			}
			if(!BlankUtil.isBlank(detailVo.getOrdered())){
				ordered = Integer.parseInt(detailVo.getOrdered()); //下单数
			}
			remainingQuantity = quantity-ordered;
			detailVo.setRemainingQuantity(""+remainingQuantity); //采购数
			detailVo.setUnitPrice(""+(obj[8]!=null?obj[8]:0)); //单价
			detailVo.setUnit("台");
			detailVo.setId(""+(obj[9]!=null?obj[9]:0));
			detailVo.setUsedNum(""+(obj[10]!=null?obj[10]:0));
			detailVo.setContractId(""+(obj[11]!=null?obj[11]:0));
			detailvoList.add(detailVo);
		}
		return detailvoList;
	}

	@Override
	public boolean saveTemporaryDataToFormal(ContractTemporaryVo vo,Long userId) {
		//检查入的使用数大于剩余使用数量
		String[] quotationsDataIds = vo.getQuotationsId().split(",");
		String[] usedNums = vo.getUsedNum().split(",");
		String[] names = vo.getName().split(",");
		String[] projectNames = vo.getProjectName().split(",");
		String[] quantitys = vo.getQuantity().split(",");
		String[] contractIds = vo.getId().split(",");
		for(int i=0;i<quotationsDataIds.length;i++){
			Integer quotationsDataId = Integer.parseInt(quotationsDataIds[i].toString());
			Integer usedNum = Integer.parseInt(usedNums[i].toString());
			TbQuotationsData data = quotationsDataDAO.findById(quotationsDataId);
			if(data==null)
				return false;
			
			Integer tUsed = 0;
			if(null!=data.getToUsed()){
				tUsed = data.getToUsed();
			}
			Integer remain = data.getQuantity() - tUsed;
			if(usedNum>remain){
				LOGGER.info("输入的使用数大于剩余使用数量.");
				return false;
			}
		}
		
		//新建报价表
		TbQuotations  newQuotations = new TbQuotations();
		newQuotations.setCreatetime(new Date());
		newQuotations.setCreator(userDAO.findById(userId));
		String fileName = System.currentTimeMillis()+".xlsx";
		newQuotations.setDownUrl(fileName);
		newQuotations.setStatus(S_Quotations.QUOTATIONS_PASSED);
		newQuotations.setTitle(vo.getTitle()+"(临转正)");
		Integer newQuotationsId = (Integer) quotationsDAO.save(newQuotations);
		newQuotations.setId(newQuotationsId);
		
		if(newQuotationsId!=null){
			for(int i=0;i<quotationsDataIds.length;i++){
				Integer quotationsDataId = Integer.parseInt(quotationsDataIds[i].toString());
				String name = names[i];
				String projectName = projectNames[i];
				Integer quantity =  Integer.parseInt(quantitys[i].toString());
				Integer usedNum =  Integer.parseInt(usedNums[i].toString());
				Integer contractId = Integer.parseInt(contractIds[i].toString());
				TbQuotationsData data = quotationsDataDAO.findById(quotationsDataId);
				Integer confId = 0;
				if(data!=null){
							confId = data.getConfigModels().getId();
							//保存新报价表与临时合同报价表的机型配置关系
							TbTemporaryDataToFormal temporaryDataToFormal = new TbTemporaryDataToFormal();
							TbContractTemporary  contractTemporary = contractTemporaryDAO.findById(contractId);
							temporaryDataToFormal.setContractTemporary(contractTemporary);
							temporaryDataToFormal.setQuotations(newQuotations);
							temporaryDataToFormal.setQuotationsData(data);
							temporaryDataToFormal.setUsedNum(usedNum);
							this.temporaryDataToFormalDAO.save(temporaryDataToFormal);
							//更新使用数量的值
							Integer toUsed = 0;
							if(data.getToUsed()!=null){
								toUsed = data.getToUsed() + usedNum;
							}else{
								toUsed = usedNum;
							}
							data.setToUsed(toUsed);
							quotationsDataDAO.saveOrUpdate(data);
				}
				//保存新报价表的机型配置关系
				TbQuotationsData quotationsData = new TbQuotationsData();
				quotationsData.setConfigModels(configModelsService.getByPk(confId));
				quotationsData.setName(name);
				quotationsData.setOrdered(usedNum);
				quotationsData.setQuantity(usedNum);
				quotationsData.setToUsed(0);
				quotationsData.setProjectName(projectName);
				quotationsData.setQuotations(quotationsDAO.findById(newQuotationsId));
				Integer newDataId = (Integer) this.quotationsDataDAO.save(quotationsData);
				newQuotations.setQuotationType(2);
				quotationsDAO.saveOrUpdate(newQuotations);
			}
		}
		return true;
	}

	@Override
	public void checkContract( CheckedVo checkVo) throws Exception {
		Integer contractId =  Integer.parseInt(checkVo.getId());
		String describe = checkVo.getDescribe();
		String result = checkVo.getResult();
		String userId = checkVo.getUser_id();
		if( contractId==null ||describe==null ||result==null){
			return ; 
		}
		TbContractTemporary contract = this.getByPk(contractId);
		if("0".equals(result)){
			contract.setStatus(S_Contract.CONTRACT_Approved);
		}else if("1".equals(result)){
			contract.setStatus(S_Contract.CONTRACT_UnApproved);
			String  param = "?contractIds="+ contract.getId() + "&backUrl=/view/main.jsp";
			//TODO 未审核
			taskService.startTask(null, Long.parseLong(userId), TaskName.audit_contractL_result+"("+contract.getTitle()+")", 
					null, contract.getCreator().getId(), BusinessConstants.audit_contractL_result, 
					checkVo.getDescribe(), param,BusinessConstants.unTemp_contract_pkid+contractId);
		}
		
		//完成待办任务
		TaskVo taskVo = new TaskVo();
		taskVo.setParamPkid(BusinessConstants.temp_contract_pkid+contractId);
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
			
			param="?id="+Id+"&type=2";
			
			TbContractTemporary contract = contractTemporaryDAO.findById(Id);
			contract.setStatus(S_Contract.CONTRACT_TO_REVIEWING);
			contractTemporaryDAO.saveOrUpdate(contract);
			//给审核人提交任务
			taskService.startTask(null, snpnorid, TaskName.audit_contract+"("+contract.getTitle()+")", 
					projectId, personInChargeId, BusinessConstants.audit_contract,
					checkedVo.getDescribe(), param,BusinessConstants.temp_contract_pkid+Id);
			
			//完成审核不通过的待办任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.unTemp_contract_pkid+Id);
			taskService.finishTask(taskVo);
			
			LOGGER.info("Submit Success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
