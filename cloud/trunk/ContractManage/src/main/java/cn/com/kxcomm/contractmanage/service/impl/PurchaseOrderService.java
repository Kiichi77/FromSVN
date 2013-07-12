package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractOrderDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderRelationshipDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderRelationshipDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationship;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderRelationship;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.status.S_ContractOrder;
import cn.com.kxcomm.contractmanage.vo.ContractOrderRelationshipVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderRelationshipVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderVo;
@Service("purchaseOrderService")
public class PurchaseOrderService extends CommonService<TbPurchaseOrder> implements IPurchaseOrderService{
	private static final Logger log = Logger.getLogger(PurchaseOrderService.class);
	@Autowired(required=true)
	private PurchaseOrderDAO purchaseOrderDAO;
	@Autowired(required=true)
	private PurchaseOrderRelationshipDAO purchaseOrderRelationshipDAO;
	@Autowired(required=true)
	private PurchaseOrderRelationshipDataDAO purchaseOrderRelationshipDataDAO;
	@Autowired(required=true)
	private ContractOrderDAO contractOrderDAO;
	@Autowired(required = true)
	private IactivitiService activitiService; // 流程控制服务
	
	@Override
	public CommonDAO<TbPurchaseOrder> getBindDao() {
		return purchaseOrderDAO;
	}
	
	/**
	* 
	* 方法用途和描述: 分页查询所有的收货项
	* @return
	* @author luj 新增日期：2013-3-7
	* @since ContractManage
	*/
	@Override
	public PageInfo<PurchaseOrderVo> listPurchaseOrder(PageInfo<PurchaseOrderVo> pageInfo,PurchaseOrderVo paramVo) {
		String hql=" from TbPurchaseOrder po where 1=1 ";
		String where = "";
		if(!BlankUtil.isBlank(paramVo.getCreateTime())){
			where += " and date_format(po.createtime,'%Y-%m-%d') >= date_format('"+paramVo.getCreateTime()+"','%Y-%m-%d') ";
		}else{
			paramVo.setCreateTime(null);
		}
		if(!BlankUtil.isBlank(paramVo.getEndTime())){
			where += "and date_format(po.createtime,'%Y-%m-%d') <= date_format('"+paramVo.getEndTime()+"','%Y-%m-%d') ";
		}else{
			paramVo.setEndTime(null);
		}
		hql += where;
		PageInfo<TbPurchaseOrder> p =new PageInfo<TbPurchaseOrder>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List<TbPurchaseOrder> list=purchaseOrderDAO.pageInfoQuery(hql, p);
		int count = purchaseOrderDAO.findTotalCount("select count(*) from TbPurchaseOrder po where 1=1 " +where);
		List<PurchaseOrderVo> listPurchaseOrder=new ArrayList<PurchaseOrderVo>();
		for(TbPurchaseOrder purchaseOrder:list){
			PurchaseOrderVo purchaseOrderVo=new PurchaseOrderVo(); 
			purchaseOrderVo.setId(""+purchaseOrder.getId()); 
			purchaseOrderVo.setContractTitle(purchaseOrder.getContractOrder().getContract().getTitle()); //采购订购合同名称
		    purchaseOrderVo.setCreate(purchaseOrder.getCreate().getUserName());  //创建者
		    purchaseOrderVo.setOrderNo(purchaseOrder.getOrderNo());  //采购订单号
		    purchaseOrderVo.setQuoteNo(purchaseOrder.getQuoteNo());  //报价单号
		    purchaseOrderVo.setStatus(""+purchaseOrder.getStatus());  //状态
		    purchaseOrderVo.setCreateTime(""+purchaseOrder.getCreatetime()); //创建时间
 		    purchaseOrderVo.setSuppliers(purchaseOrder.getSuppliers().getCompanyName()); //供应商
		    purchaseOrderVo.setContractOrderNo(""+purchaseOrder.getContractOrder().getOrderNo()); //销售订单号
		    listPurchaseOrder.add(purchaseOrderVo);
		}
		PageInfo<PurchaseOrderVo> page = new PageInfo<PurchaseOrderVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listPurchaseOrder);
		return page;
	}
	
	/**
	* 
	* 方法用途和描述: 根据合同订单Id获取采购订单的所有数据
	* @return
	* @author luj 新增日期：2013-3-11
	* @since ContractManage
	*/
	@Override
	public List<PurchaseOrderVo> listByOrderId(int contractOrderId) {
		String hql=" from TbPurchaseOrderRelationship po where po.purchaseOrder.contractOrder.id=? group by po.purchaseOrder.id";
		List<TbPurchaseOrderRelationship> list=purchaseOrderRelationshipDAO.find(hql.toString(), contractOrderId);
		int count = purchaseOrderRelationshipDAO.findTotalCount("select count(*) from TbPurchaseOrderRelationship");
		List<PurchaseOrderVo> listPurchaseOrder=new ArrayList<PurchaseOrderVo>();
		for(TbPurchaseOrderRelationship purchaseOrder:list)
		{
			PurchaseOrderVo purchaseOrderVo=new PurchaseOrderVo();
			purchaseOrderVo.setId(""+purchaseOrder.getPurchaseOrder().getId());
			purchaseOrderVo.setOrderNo(purchaseOrder.getPurchaseOrder().getOrderNo());
			purchaseOrderVo.setQuoteNo(purchaseOrder.getPurchaseOrder().getQuoteNo());
			purchaseOrderVo.setStatus(""+purchaseOrder.getPurchaseOrder().getStatus());
			purchaseOrderVo.setAfterSalesId(purchaseOrder.getPurchaseOrder().getAfterSales().getUserName());
			purchaseOrderVo.setPurchaseQuantity(""+purchaseOrder.getPurchaseQuantity());
			purchaseOrderVo.setConfigModelsName(purchaseOrder.getContractOrderRelationship().getConfigModels().getName());
		   purchaseOrderVo.setRemark(purchaseOrder.getPurchaseOrder().getRemark());
		   purchaseOrderVo.setReceivertime(""+purchaseOrder.getPurchaseOrder().getReceivertime());
		   purchaseOrderVo.setCreate(purchaseOrder.getPurchaseOrder().getCreate().getUserName());
		   purchaseOrderVo.setSuppliers(purchaseOrder.getPurchaseOrder().getSuppliers().getCompanyName());
		   purchaseOrderVo.setContractOrderNo(purchaseOrder.getPurchaseOrder().getContractOrder().getOrderNo());
		   purchaseOrderVo.setCreateTime(""+purchaseOrder.getPurchaseOrder().getCreatetime());
		   purchaseOrderVo.setConfigId(""+purchaseOrder.getContractOrderRelationship().getConfigModels().getId());
			listPurchaseOrder.add(purchaseOrderVo);
		}
      return listPurchaseOrder;
	}
	/**
	* 
	* 方法用途和描述: 根据合同订单Id获取采购订单的所有数据
	* @return
	* @author luj 新增日期：2013-3-11
	* @since ContractManage
	*/
	@Override
	public List<PurchaseOrderVo> listByContractOrderId(int contractOrderId) {
		String hql=" from TbPurchaseOrder po where po.contractOrder.id=?";
		List<TbPurchaseOrder> list=purchaseOrderDAO.find(hql.toString(), contractOrderId);
		int count = purchaseOrderDAO.findTotalCount("select count(*) from TbPurchaseOrder");
		List<PurchaseOrderVo> listPurchaseOrder=new ArrayList<PurchaseOrderVo>();
		for(TbPurchaseOrder purchaseOrder:list)
		{
			PurchaseOrderVo purchaseOrderVo=new PurchaseOrderVo();
			purchaseOrderVo.setId(""+purchaseOrder.getId());
			String title = "";
			if(null!=purchaseOrder.getContractOrder()){
				if(null!=purchaseOrder.getContractOrder().getContract()){
					title = purchaseOrder.getContractOrder().getContract().getTitle();
				}else{
					log.debug("title is null.");
				}
			}else{
				log.debug("contract is null.");
			}
			purchaseOrderVo.setContractTitle(title);
		    purchaseOrderVo.setCreate(purchaseOrder.getCreate().getUserName());
		    purchaseOrderVo.setOrderNo(purchaseOrder.getOrderNo());
		    purchaseOrderVo.setQuoteNo(purchaseOrder.getQuoteNo());
		    purchaseOrderVo.setStatus(""+purchaseOrder.getStatus());
		    purchaseOrderVo.setCreateTime(""+purchaseOrder.getCreatetime());
		    purchaseOrderVo.setSuppliers(purchaseOrder.getSuppliers().getCompanyName());
		    purchaseOrderVo.setContractOrderNo(purchaseOrder.getContractOrder().getOrderNo());
		    purchaseOrderVo.setReceivertime(""+purchaseOrder.getReceivertime());
		    purchaseOrderVo.setAfterSalesId(""+purchaseOrder.getAfterSales().getUserName());
		    purchaseOrderVo.setRemark(purchaseOrder.getRemark());
		    listPurchaseOrder.add(purchaseOrderVo);
		}
      return listPurchaseOrder;
	}
	
	/**
	* 
	* 方法用途和描述: 修改采购订单状态
	* @return
	* @author luj 新增日期：2013-3-14
	* @since ContractManage
	*/
	@Override
	public void merge(TbPurchaseOrder purchaseOrder) {
		purchaseOrderDAO.merge(purchaseOrder);
	}	
	

	/**
	 * 保存采购订单
	 */
	@Override
	public Map savePurchaseOrder(PurchaseOrderVo  purchaseOrderVo) {
		Map map = new HashMap();
		//1、保存采购订单总表
		TbPurchaseOrder purchaseOrder = saveOrUpdatePKOrder(purchaseOrderVo);
		log.info("Save TbPurchaseOrder success. ");
		//2、保存采购订单详情
		saveOrUpdatePKOrderDetail(purchaseOrderVo, purchaseOrder);
		log.info("SavePurchaseOrder complted.");
		Integer contractOrderId = purchaseOrder.getContractOrder().getId();
		//判断销售订单是否已经完全下单采购，若有剩余未采购则新建工作流，直到销售订单完全采购。
		Long purchaseCount = contractOrderDAO.countContractOrderPurchaseOrder(contractOrderId);
		Long contractCount = contractOrderDAO.countContractOrderTotal(contractOrderId);
		boolean endbo = false;
		TbContractOrder contractOrder= this.contractOrderDAO.findById(contractOrderId);
	//	System.out.println(contractOrder.toString());
//		if(purchaseCount<contractCount) {
//			endbo = true;
			contractOrder.setStatus(S_ContractOrder.CONTRACTORDER_PURCHASE);
//		} else {
//			endbo = false;
//			contractOrder.setStatus(S_ContractOrder.CONTRACTORDER_FINISH);
//		}
		this.contractOrderDAO.save(contractOrder);
		map.put("isAddTask",endbo);
		map.put("purchaseOrderId",purchaseOrder.getId());
		int orderId=0;
			if(!BlankUtil.isBlank(purchaseOrderVo.getContractOrderId()))
			{
				orderId=Integer.parseInt(purchaseOrderVo.getContractOrderId());
			}
		 List<ContractQuotationsVo> list=contractOrderDAO.querySaleOrder(orderId,null);
		         int quantity=0;
				 int ordered=0;
		 for(int i=0;i<list.size();i++)
		 {
			   
			 quantity+=Integer.parseInt(list.get(i).getQuantity());
			 ordered+=Integer.parseInt(list.get(i).getOrdered());
		 }
		 if(quantity==ordered)
		 {
			 TbContractOrder contractOrder2=contractOrderDAO.findById(orderId);
			 contractOrder2.setStatus(S_ContractOrder.PURCHASE_FINISH);
			 contractOrderDAO.merge(contractOrder2);
		 }
		return map;
	}
	
	/**
	 * 
	* 方法用途和描述: 保存采购订单主表信息
	* @param purchaseOrderVo
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	private TbPurchaseOrder saveOrUpdatePKOrder(PurchaseOrderVo purchaseOrderVo){
		TbPurchaseOrder purchaseOrder = new TbPurchaseOrder();
		if(!BlankUtil.isBlank(purchaseOrderVo.getContractOrderId())){ //设置销售订单
			Integer contractOrderPkid = Integer.parseInt(purchaseOrderVo.getContractOrderId());
			TbContractOrder contractOrder = new TbContractOrder();
			contractOrder.setId(contractOrderPkid);
			purchaseOrder.setContractOrder(contractOrder);
		}
		if(!BlankUtil.isBlank(purchaseOrderVo.getCreaterId())){ //设置创建者
			Long createrPkid = Long.parseLong(purchaseOrderVo.getCreaterId());
			TbUser user = new TbUser();
			user.setId(createrPkid);
			purchaseOrder.setCreate(user);
		}
		if(!BlankUtil.isBlank(purchaseOrderVo.getSuppliersId())){ //设置供应商
			Integer suppliersPkid = Integer.parseInt(purchaseOrderVo.getSuppliersId());
			TbSuppliers suppliers = new TbSuppliers();
			suppliers.setId(suppliersPkid);
			purchaseOrder.setSuppliers(suppliers);
		}
		if(!BlankUtil.isBlank(purchaseOrderVo.getContractId())){ //订购合同文件
			Integer orderContractPkid = Integer.parseInt(purchaseOrderVo.getContractId());
			TbPurchaseOrderContractFile contractFile = new TbPurchaseOrderContractFile();
			contractFile.setId(orderContractPkid);
			purchaseOrder.setPurchaseOrderContractFile(contractFile);
		}
		purchaseOrder.setStatus(1);  //状态
		purchaseOrder.setOrderNo(purchaseOrderVo.getOrderNo());//采购订单
		purchaseOrder.setQuoteNo(purchaseOrderVo.getQuoteNo());//报价单号
		purchaseOrder.setCreatetime(new Date()); //设置创建时间
		if(!BlankUtil.isBlank(purchaseOrderVo.getAfterSalesId())){ //设置售后人员
			long salesPkid = Long.parseLong(purchaseOrderVo.getAfterSalesId());
			TbUser afterSales = new TbUser();
			afterSales.setId(salesPkid);
			purchaseOrder.setAfterSales(afterSales);//设置售后人员
		}
		purchaseOrder.setRemark(purchaseOrderVo.getRemark()); //设置备注
		purchaseOrderDAO.save(purchaseOrder);
		return purchaseOrder;
	}
	
	/**
	 * 
	* 方法用途和描述: 保存采购订单详情以及保存采购订单相关联的全通用单元
	* @param purchaseOrderVo
	* @param purchaseOrder
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	private void saveOrUpdatePKOrderDetail(PurchaseOrderVo purchaseOrderVo,TbPurchaseOrder purchaseOrder){
		if(!BlankUtil.isBlank(purchaseOrderVo.getShipVos())){
			PurchaseOrderRelationshipVo[] vos = purchaseOrderVo.getShipVos();
			for (PurchaseOrderRelationshipVo purchaseOrderRelationshipVo : vos) {
				TbPurchaseOrderRelationship purchaseRelationShip = new TbPurchaseOrderRelationship();
				purchaseRelationShip.setPurchaseOrder(purchaseOrder);  //设置采购订单
				String purchaseQuantity = purchaseOrderRelationshipVo.getPurchaseQuantity(); 
				int quantity = 0;
				if(!BlankUtil.isBlank(purchaseQuantity)){
					quantity = Integer.parseInt(purchaseQuantity);
				}
				purchaseRelationShip.setPurchaseQuantity(quantity); //设置数量
				if(!BlankUtil.isBlank(purchaseOrderRelationshipVo.getContractOrderRelationshipId())){ //设置采购详情与销售详情的关系
					int contractShipid = Integer.parseInt(purchaseOrderRelationshipVo.getContractOrderRelationshipId());
					TbContractOrderRelationship contractShip = new TbContractOrderRelationship();
					contractShip.setId(contractShipid);
					purchaseRelationShip.setContractOrderRelationship(contractShip);
				}
				purchaseOrderRelationshipDAO.save(purchaseRelationShip);
				log.debug("Save TbPurchaseOrderRelationship success.");
				//保存全通用单元
//				PurchaseOrderRelationshipDataVo[] dataVo = purchaseOrderRelationshipVo.getDataVo();
//				for (PurchaseOrderRelationshipDataVo purchaseOrderRelationshipDataVo : dataVo) {
//					int orderQuantity=0,modelDataPkid=0;
//					if(!BlankUtil.isBlank(purchaseOrderRelationshipDataVo.getQuantity())){
//						orderQuantity = Integer.parseInt(purchaseOrderRelationshipDataVo.getQuantity());
//					}
//					TbPurchaseOrderRelationshipData shipData = new TbPurchaseOrderRelationshipData();
//					shipData.setOrderQuantity(orderQuantity); //设置下单数量
//					if(!BlankUtil.isBlank(purchaseOrderRelationshipDataVo.getModelDataId())){
//						modelDataPkid = Integer.parseInt(purchaseOrderRelationshipDataVo.getModelDataId());
//					}
//					TbModelData modelData = new TbModelData();
//					modelData.setId(modelDataPkid);
//					shipData.setModelData(modelData); //设置机型配置数据
//					shipData.setPurchaseOrderRelationship(purchaseRelationShip); //设置采购订单详情
//					//3、保存某个配置的全通用单元
//					purchaseOrderRelationshipDataDAO.save(shipData);
//					log.debug("Save TbPurchaseOrderRelationshipData success.");
//				}
			}
			log.info("SaveOrderDetail success.");
		}else{
			log.info("PurchaseOrderVo ShipVos is null.SaveOrderDetail Failure.");
		}
	}

	/**
	 * 修改采购订单
	 */
	@Override
	public void updatePurchaseOrder(PurchaseOrderVo  purchaseOrderVo) {
		Integer pkid = 0; //采购订单id
		if(!BlankUtil.isBlank(purchaseOrderVo.getId())){
			pkid = Integer.parseInt(purchaseOrderVo.getId());
		}
		//1、修改采购订单总表
		String updateHql = "update TbPurchaseOrder set orderNo=?,quoteNo=?,remark=? where id=? ";
		purchaseOrderDAO.executeByHQL(updateHql, purchaseOrderVo.getOrderNo(),purchaseOrderVo.getQuoteNo(),purchaseOrderVo.getRemark(),pkid);
		//2、修改采购订单详情
		//2.1、根据采购订单详情删除订单详情关联的全通用单元
		StringBuffer deleteHql =  new StringBuffer();
		deleteHql.append(" delete b from TbPurchaseOrderRelationshipData b where b.purchaseOrderRelationship.id in ( ");
		deleteHql.append(" select a.id from TbPurchaseOrderRelationship a ");
		deleteHql.append(" where a.purchaseOrder.id= ? ) ");
		purchaseOrderRelationshipDataDAO.executeByHQL(deleteHql.toString(), pkid);
		//2.2、根据采购订单删除采购订单详情
		deleteHql.delete(0, deleteHql.length());
		deleteHql.append("delete a  from tb_purchase_order_relationship a where a.purchaseOrder.id = ?");
		purchaseOrderRelationshipDAO.executeByHQL(deleteHql.toString(), pkid);
		//2.4、保存采购订单详情相关联的全通用单元
		//2.4.1、根据采购订单id查询采购订单内容
		TbPurchaseOrder purchaseOrder = purchaseOrderDAO.findById(pkid);
		//2.4.2、保存采购订单详情和采购订单全通单元
		saveOrUpdatePKOrderDetail(purchaseOrderVo, purchaseOrder);
	}
	@Override
	public boolean confirmAllReceipt(Integer contractOrderId) {
		if(contractOrderId==null)
			return false;
		
		purchaseOrderDAO.confirmAllReceipt(contractOrderId);
		return true;
	}
	
	/**
	 * 根据销售订单判断该销售订单是否已经全部采购完成
	 */
	@Override
	public boolean checkContractOrderNum(Integer contractOrderId) {
		boolean end = false;
		//判断销售订单是否已经完全下单采购，若有剩余未采购则新建工作流，直到销售订单完全采购。
		Long purchaseCount = contractOrderDAO.countContractOrderPurchaseOrder(contractOrderId);
		Long contractCount = contractOrderDAO.countContractOrderTotal(contractOrderId);
		if(purchaseCount<contractCount) {
			end = true;
		} else{
			end = false;
		}		
		return end;
	}

	/**
	 * 根据进程id查询
	 */
	@Override
	public PurchaseOrderVo findByProcessId(String processId) {
		String hql = "	select id,contract_order_id,create_id,create_time,suppliers_id,purchaseordercontractfile_id,order_no," +
				"	quote_no,status,aftersales_id,remark from tb_purchase_order where process_id = ?";
		List lists = purchaseOrderDAO.findByPage(hql,processId);
		PurchaseOrderVo purchaseOrderVo = new PurchaseOrderVo();
		if(!BlankUtil.isBlank(lists)){
			Object[] obj = new Object[lists.size()];
			obj = (Object[]) lists.get(0);
			purchaseOrderVo.setId(""+obj[0]);
			purchaseOrderVo.setContractOrderId(""+obj[1]);
			purchaseOrderVo.setCreaterId(""+obj[2]);
			purchaseOrderVo.setCreateTime(""+obj[3]);
			purchaseOrderVo.setSuppliersId(""+obj[4]);
			purchaseOrderVo.setContractId(""+obj[5]); //订购合同文件id
			purchaseOrderVo.setOrderNo(""+obj[6]);
			purchaseOrderVo.setQuoteNo(""+obj[7]);
			purchaseOrderVo.setStatus(""+obj[8]);
			purchaseOrderVo.setAfterSalesId(""+obj[9]);
			purchaseOrderVo.setRemark(""+obj[10]);
		}
		return purchaseOrderVo;
	}
	
	/**
	 * 修改采购订单的报价单号和采购订单编号.
	 *
	 * @author lujia 新增日期：2013-3-15
	 * @param jsonBeanList
	 * @return
	 */
	@Override
	public String UpdateOrder(PurchaseOrderVo jsonBeanList){
		String end = "ok";
		try {
			StringBuffer hql=new StringBuffer();
			hql.append(" UPDATE TbPurchaseOrder p ");
			hql.append(" SET p.orderNo=?,p.quoteNo=? ");
			hql.append(" where p.id=? ");
			if(!BlankUtil.isBlank(jsonBeanList.getId())) {
				//采购订单ID
				int pkid = Integer.parseInt(jsonBeanList.getId());
				//采购订单号
				String orderNo = jsonBeanList.getOrderNo();
				//报价单号
				String quoteNo = jsonBeanList.getQuoteNo();
				//供应商id
				this.purchaseOrderDAO.executeByHQL(hql.toString(), orderNo,quoteNo,pkid);
			}
		} catch (Exception e) {
			end = "error";
			e.printStackTrace();
		}
		return end;
	}
	
	/**
	* 
	* 方法用途和描述: 根据合同订单Id和订单ID获取采购订单的所有数据
	* @return
	* @author lizl 新增日期：2013-6-21
	* @since ContractManage
	*/
	@Override
	public List<PurchaseOrderVo> listByOrderInfos(int contractOrderId,int parasid) {
		String hql=" from TbPurchaseOrderRelationship po where po.purchaseOrder.contractOrder.id=? and po.purchaseOrder.id=?";
		List<TbPurchaseOrderRelationship> list = this.purchaseOrderRelationshipDAO.find(hql.toString(), contractOrderId,parasid);
		List<PurchaseOrderVo> listPurchaseOrder=new ArrayList<PurchaseOrderVo>();
		for(TbPurchaseOrderRelationship purchaseOrder:list)
		{
			PurchaseOrderVo purchaseOrderVo=new PurchaseOrderVo();
			purchaseOrderVo.setId(""+purchaseOrder.getPurchaseOrder().getId());
			purchaseOrderVo.setOrderNo(purchaseOrder.getPurchaseOrder().getOrderNo());
			purchaseOrderVo.setQuoteNo(purchaseOrder.getPurchaseOrder().getQuoteNo());
			purchaseOrderVo.setStatus(""+purchaseOrder.getPurchaseOrder().getStatus());
			purchaseOrderVo.setAfterSalesId(purchaseOrder.getPurchaseOrder().getAfterSales().getUserName());
			purchaseOrderVo.setPurchaseQuantity(""+purchaseOrder.getPurchaseQuantity());
			purchaseOrderVo.setConfigModelsName(purchaseOrder.getContractOrderRelationship().getConfigModels().getName());
		   purchaseOrderVo.setRemark(purchaseOrder.getPurchaseOrder().getRemark());
		   purchaseOrderVo.setReceivertime(""+purchaseOrder.getPurchaseOrder().getReceivertime());
		   purchaseOrderVo.setCreate(purchaseOrder.getPurchaseOrder().getCreate().getUserName());
		   purchaseOrderVo.setSuppliers(purchaseOrder.getPurchaseOrder().getSuppliers().getCompanyName());
		   purchaseOrderVo.setContractOrderNo(purchaseOrder.getPurchaseOrder().getContractOrder().getOrderNo());
		   purchaseOrderVo.setCreateTime(""+purchaseOrder.getPurchaseOrder().getCreatetime());
		   purchaseOrderVo.setConfigId(""+purchaseOrder.getContractOrderRelationship().getConfigModels().getId());
			listPurchaseOrder.add(purchaseOrderVo);
		}
      return listPurchaseOrder;
	}
}
