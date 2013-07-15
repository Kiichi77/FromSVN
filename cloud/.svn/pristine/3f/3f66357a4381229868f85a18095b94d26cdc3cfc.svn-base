package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractOrderDAO;
import cn.com.kxcomm.contractmanage.dao.ContractOrderRelationshipDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationship;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbModelData;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.status.S_ContractOrder;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderRelationshipVo;
import cn.com.kxcomm.contractmanage.vo.QuotationDataInfoBean;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("contractOrderRelationshipService")
public class ContractOrderRelationshipService extends
CommonService<TbContractOrderRelationship> implements IContractOrderRelationshipService{
	@Autowired(required=true)
	private ContractOrderRelationshipDAO contractOrderRelationshipDAO;
	@Autowired(required=true)
	private ITaskService taskService;
	
	@Autowired(required=true)
	private ContractOrderDAO contractOrderDAO;
	
	@Override
	public void saveRelationship(TbContractOrderRelationship entity) {
		contractOrderRelationshipDAO.save(entity);
	}

	@Override
	public CommonDAO<TbContractOrderRelationship> getBindDao() {
		return contractOrderRelationshipDAO;
	}
   
	/**
	 * 修改销售订单详情（询价）.
	 *
	 * @author lujia 新增日期：2013-3-15
	 * @param jsonBeanList
	 * @return
	 */
	@Override
	public String UpdateShip(List<ContractOrderRelationshipVo> jsonBeanList){
		String end = "ok";
		try {
			StringBuffer hql=new StringBuffer();
			hql.append(" UPDATE TbContractOrderRelationship v ");
			hql.append(" SET v.suppliers.id=?,v.purchasePrice=?,v.suppliersQuotations.superliersQuotationsId=? ");
			hql.append(" where v.id=? ");
			for(int i = 0;i < jsonBeanList.size(); i++) {
				if(!BlankUtil.isBlank(jsonBeanList.get(i).getId())) {
					//主键id
					int pkid = Integer.parseInt(jsonBeanList.get(i).getId());
					//询价价格
					String purchasePrice = jsonBeanList.get(i).getPurchasePrice();
					double price= purchasePrice!=null?Double.parseDouble(purchasePrice):0;
					//供应商报价表id
					int suppliersQuotationsId = Integer.parseInt(jsonBeanList.get(i).getSuppliersQuotations()); 
					//供应商id
					int suppliersId = Integer.parseInt(jsonBeanList.get(i).getSuppliers());
					contractOrderRelationshipDAO.executeByHQL(hql.toString(), suppliersId,price,suppliersQuotationsId,pkid);
				}
			}
		} catch (Exception e) {
			end = "error";
			e.printStackTrace();
		}
		return end;
	}

	
	@Override
	public String submitState(CheckedVo checkedVo) throws Exception {
		//提交询价单审核
		try {
			//更新销售订单状态
			String hql = " update TbContractOrder tb set tb.status=? where tb.id = ? ";
			int contractorderPkid = 0; 
			if(!BlankUtil.isBlank(checkedVo.getId())){
				contractorderPkid = Integer.parseInt(checkedVo.getId());
			}
			contractOrderDAO.executeByHQL(hql.toString(), S_ContractOrder.CONTRACTORDER_NAUDITED,contractorderPkid);
			//向任务表中添加任务
			int Id = 0; //合同id
			Long snpnorid = 0l; //创建人员
			Long personInChargeId = 0l; //负责人
			String param = "";//参数
			Integer projectId = null;
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
			}
//			else{
//				throw new NullPointerException("ProjectId is null");
//			}
			param="?quotationsId="+Id;
			taskService.startTask(null, snpnorid, TaskName.audit_inquire, projectId, personInChargeId,
					BusinessConstants.audit_inquiry, checkedVo.getDescribe(), param,BusinessConstants.askorders_pkid+contractorderPkid);
			
			//完成销售下发给采购的任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.sell_ask_order_pkid+contractorderPkid);
			taskService.finishTask(taskVo);
			
			//完成总经理打回的询价单审核的任务
//			taskVo = new TaskVo();
//			taskVo.setParamPkid(BusinessConstants.unAskorders_pkid+contractorderPkid);
//			taskService.finishTask(taskVo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "ok";
	}
}
