package cn.com.kxcomm.contractmanage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.base.exception.OperationException;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.DateFormatUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.dao.ContractOrderDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemporaryDAO;
import cn.com.kxcomm.contractmanage.dao.ModelDataDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationship;
import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationshipData;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.entity.TbCustomerAddressInfo;
import cn.com.kxcomm.contractmanage.entity.TbModelData;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipDataService;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipService;
import cn.com.kxcomm.contractmanage.service.IContractOrderService;
import cn.com.kxcomm.contractmanage.service.IQuotationsDataService;
import cn.com.kxcomm.contractmanage.status.S_Contract;
import cn.com.kxcomm.contractmanage.status.S_ContractOrder;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.CheckinquiryOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.vo.UserVo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;
@Service("contractOrderService")
public class ContractOrderService extends CommonService<TbContractOrder> implements IContractOrderService{
  
	private static final Logger LOG = Logger.getLogger(ContractOrderService.class);
	@Autowired(required=true)
	private ContractOrderDAO contractOrderDAO;
	@Autowired(required=true)
	private UserDAO userDAO;
	@Autowired(required=true)
	private ContractDAO contractDAO;
	@Autowired(required=true)
	private ContractTemporaryDAO  contractTemporaryDAO;
	@Autowired(required=true)
	private PurchaseOrderService purchaseOrderService;
	@Autowired(required=true)
	private IContractOrderRelationshipDataService contractOrderRelationshipDataService;
	@Autowired(required = true)
	private IContractOrderRelationshipService contractOrderRelationshipService;
	@Autowired(required = true)
	private IQuotationsDataService quotationsDataService;
	@Autowired(required = true)
	private QuotationsDataDAO quotationsDataDAO;
	@Autowired(required = true)
	private ModelDataDAO modelDataDAO;
	@Autowired(required = true)
	private TaskService taskService;
	@Autowired(required = true)
	private UserServiceImpl userService;
	@Autowired(required = true)
	private CustomerAddressInfoService customerAddressInfoService;
	
	@Override
	public CommonDAO<TbContractOrder> getBindDao() {
		return contractOrderDAO;
	}

	// 查询所有的销售订单
		@Override
		public PageInfo<ContractOrderVo> listContractOrder(PageInfo<ContractOrderVo> pageInfo,Long userId,ContractOrderVo cOrder)
		{
			List<ContractOrderVo> listContractOrderVo=new ArrayList<ContractOrderVo>();
	         StringBuffer hql= new StringBuffer(" select co from TbContractOrder co where 1=1 ");
	         StringBuffer countSql = new StringBuffer(" select count(*) from TbContractOrder co where 1=1 ");
	         StringBuffer where = new StringBuffer();
//	         if(userId!=null){
//	        	 where.append(" and co.creator.id=?");
//	         }
	         if(!BlankUtil.isBlank(cOrder.getStartTime())){
	        	 where.append(" and date_format(co.createtime,'%Y-%m-%d') >= date_format('"+cOrder.getStartTime()+"','%Y-%m-%d') ");
	         }
	         if(!BlankUtil.isBlank(cOrder.getEndTime())){
	        	 where.append(" and date_format(co.createtime,'%Y-%m-%d') <= date_format('"+cOrder.getEndTime()+"','%Y-%m-%d') ");
	         }
	         if(!BlankUtil.isBlank(cOrder.getKeyword())){
	        	where.append(" and (");
	        	where.append("  co.orderNo like '%"+cOrder.getKeyword()+"%'");
	        	where.append(" or co.receiveInfo like '%"+cOrder.getKeyword()+"%'");
	        	where.append(" )");
	         }
	       
	         where.append(" order by co.createtime desc");
	         hql.append(where);
	         countSql.append(where);
	         int count = 0;
	 		count = contractOrderDAO.findTotalCount(countSql.toString());
	 		PageInfo<TbContractOrder> p =new PageInfo<TbContractOrder>();
	 		p.setPageSize(pageInfo.getPageSize());
	 		p.setCurrentPage(pageInfo.getCurrentPage());
	 		List<TbContractOrder> list  = contractOrderDAO.pageInfoQuery(hql.toString(), p);
	         
			for (TbContractOrder contractOrder:list) {
				ContractOrderVo contractOrderVo=new ContractOrderVo();
				contractOrderVo.setContractOrderId(""+contractOrder.getId());
//				contractOrderVo.setCaudit(contractOrder.getCaudit().getUserName());
				if(contractOrder.getContractType()==1){
					contractOrderVo.setContractName(contractOrder.getContract().getTitle());
					contractOrderVo.setContractNo(contractOrder.getContract().getContractNo());
					contractOrderVo.setProjectName(contractOrder.getContract().getProject().getProjectName());
				}else {
					contractOrderVo.setContractName(contractOrder.getContractTemporary().getTitle());
					contractOrderVo.setContractNo(contractOrder.getContractTemporary().getContractNo());
				}
				  /**
		          * 内部合同编号
		          */
				 if(!BlankUtil.isBlank(cOrder.getContractNo())){
					 if(!cOrder.getContractNo().equals(contractOrderVo.getContractNo())){
						 continue;
					 }
				 }
				contractOrderVo.setContractType(""+contractOrder.getContractType());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
				contractOrderVo.setCreatetime(sdf.format(contractOrder.getCreatetime()));
				 contractOrderVo.setCreator(contractOrder.getCreator().getUserName());
				 contractOrderVo.setNote(contractOrder.getNote());
				 contractOrderVo.setOrderNo(contractOrder.getOrderNo());
				 contractOrderVo.setPurchaseCompany("未设置");
				 if(contractOrder.getPurchaseCompany()!=null){
					 contractOrderVo.setPurchaseCompany(contractOrder.getPurchaseCompany().getCompanyName());
				 }
				 contractOrderVo.setPurchase("未分配");
				 if(contractOrder.getPurchase()!=null){
					 contractOrderVo.setPurchase(contractOrder.getPurchase().getUserName());
				 }
				 contractOrderVo.setStatus(""+contractOrder.getStatus());
				 contractOrderVo.setReceiveInfo(contractOrder.getReceiveInfo());
				listContractOrderVo.add(contractOrderVo);
			}
			PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractOrderVo);
			 return page;
		}
	
		
		// 售后查询所有状态为待收货和已收货的销售订单
		@Override
		public PageInfo<ContractOrderVo> listContractOrderByStauts(PageInfo<ContractOrderVo> pageInfo,ContractOrderVo cOrder,String stauts){
			String tiaojian = "";
			Integer ids = null;
			if(!BlankUtil.isBlank(stauts) && !"null".equals(stauts)) {
				tiaojian = "and co.status = ?";
				ids = Integer.parseInt(stauts);
			}
			StringBuffer hql= new StringBuffer(" select co from TbContractOrder co where 1=1 "+tiaojian+" order by co.createtime desc");
			int count = contractOrderDAO.findTotalCount("select count(*) from TbContractOrder co where 1=1 "+tiaojian,ids);
			  List<TbContractOrder> list=contractOrderDAO.query(hql.toString(),pageInfo,ids);
			  List<ContractOrderVo> rs = new ArrayList();
				for(TbContractOrder contractOrder : list){
					ContractOrderVo contractOrderVo=new ContractOrderVo();
					contractOrderVo.setContractOrderId(""+contractOrder.getId());
//					contractOrderVo.setCaudit(contractOrder.getCaudit().getUserName());
					if(contractOrder.getContractType()==1){
						contractOrderVo.setContractName(contractOrder.getContract().getTitle());
					}else {
						contractOrderVo.setContractName(contractOrder.getContractTemporary().getTitle());
					}
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
					contractOrderVo.setCreatetime(sdf.format(contractOrder.getCreatetime()));
					 contractOrderVo.setCreator(contractOrder.getCreator().getUserName());
					 contractOrderVo.setNote(contractOrder.getNote());
					 LOG.info("Contract:"+contractOrder.getContract()+",ContractTemporary:"+contractOrder.getContractTemporary());
					 if(!BlankUtil.isBlank(contractOrder.getContract())){
						 contractOrderVo.setProjectId(""+contractOrder.getContract().getProject().getProjectId());
						 contractOrderVo.setProjectName(contractOrder.getContract().getProject().getProjectName());
					 }else{
						 contractOrderVo.setProjectId("");
						 contractOrderVo.setProjectName("");
					 }
					 contractOrderVo.setOrderNo(contractOrder.getOrderNo());
					 if(!BlankUtil.isBlank(contractOrder.getPurchaseCompany())){
						 contractOrderVo.setPurchaseCompany(contractOrder.getPurchaseCompany().getCompanyName());
					 }
					 if(!BlankUtil.isBlank(contractOrder.getPurchase())){
						 contractOrderVo.setPurchase(contractOrder.getPurchase().getUserName());
					 }
					 contractOrderVo.setStatus(""+contractOrder.getStatus());
					 contractOrderVo.setReceiveInfo(contractOrder.getReceiveInfo());
					rs.add(contractOrderVo);
				}
				PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),rs);
				 return page;
		}

		
	//添加合同订单
	@Override
	public void save(Integer contractId,String note,String orderNo,Long creatorId,Long cauditId,Long purchaseId) throws OperationException{
		try{
			TbContractOrder entity = new TbContractOrder();
			TbContract contract = contractDAO.findById(contractId);
			entity.setContract(contract);
			if(note!=null&&!"".equals(note)){
				entity.setNote(note);
			}
			if(orderNo!=null&&!"".equals(orderNo)){
				entity.setOrderNo(orderNo);
			}
			TbUser creator = userDAO.findById(creatorId);
			entity.setCreator(creator);
			entity.setStatus(0);
			TbUser caudit = userDAO.findById(cauditId);
			entity.setCaudit(caudit);
			entity.setCreatetime(new Date());
			TbUser purchase = userDAO.findById(purchaseId);
			entity.setPurchase(purchase);
			entity.setContractType(1);
			contractOrderDAO.save(entity);
		}catch(Exception e){
			e.printStackTrace();
			throw new OperationException("数据库保存失败，错误信息:"+e.getMessage());
		}
	}
	//修改合同订单
	@Override
	public void edit(Integer contractOrderId, String note, String orderNo,Long purchaseId) throws OperationException{
		TbContractOrder entity = contractOrderDAO.findById(contractOrderId);
		if(note!=null&&!"".equals(note)){
			entity.setNote(note);
		}
		if(orderNo!=null&&!"".equals(orderNo)){
			entity.setOrderNo(orderNo);
		}
		if(purchaseId!=null){
			TbUser purchase = userDAO.findById(purchaseId);
			entity.setPurchase(purchase);
		}
		contractOrderDAO.saveOrUpdate(entity);
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个订单信息
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
	@Override
	public ContractOrderVo queryOrderById(int id) {
		TbContractOrder contractOrder = this.contractOrderDAO.findById(id);
		ContractOrderVo contractOrderVo = new ContractOrderVo();
		contractOrderVo.setContractOrderId(""+contractOrder.getId());
		contractOrderVo.setNote(contractOrder.getNote());
		contractOrderVo.setOrderNo(contractOrder.getOrderNo());
		if(contractOrder.getContractType()==1){
			contractOrderVo.setContractName(contractOrder.getContract().getTitle());
		}else {
			contractOrderVo.setContractName(contractOrder.getContractTemporary().getTitle());
		}
//		contractOrderVo.setPurchase(contractOrder.getPurchase().getUserName());
		if(null != contractOrder.getContract()) {
			contractOrderVo.setProjectName(contractOrder.getContract().getProject().getProjectName());
		}
		contractOrderVo.setCreator(contractOrder.getCreator().getUserName());
		contractOrderVo.setCreatetime(DateFormatUtil.longFormatDateTime(contractOrder.getCreatetime()));
		return contractOrderVo;
	}
	
	@Override
	public List<ContractQuotationsVo> findOrderDetailByContractId(int contractOrderId,String suppliersId) {
		Integer suppliersPkid = null;
		if(!BlankUtil.isBlank(suppliersId)){
			suppliersPkid = Integer.parseInt(suppliersId);
		}
		List<ContractQuotationsVo> volist = new ArrayList<ContractQuotationsVo>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select cname,cid,order_quantity,sum(totalPrice),aid from( ");
		sql.append(" select c.name cname,a.config_model_id cid,b.id did,a.order_quantity,a.id aid, ");
		sql.append(" sum(b.total_price)*a.order_quantity totalPrice ");
		sql.append(" from tb_configmodel_data b,tb_contract_order_relationship a,tb_config_models c,tb_data_record d,tb_product_type e,tb_product_category f ");
		sql.append(" where a.config_model_id = c.id and b.config_model_id = c.id ");
		sql.append(" and b.data_record_id = d.id ");
		sql.append(" and d.product_type_id = e.id ");
		sql.append(" and e.category_id = f.id ");
		sql.append(" and f.is_repeat = 0 ");
		sql.append(" and a.contract_order_id = ? ");
		if(!BlankUtil.isBlank(suppliersPkid)){
			sql.append(" and a.suppliers_id = ? ");
		}
		sql.append(" group by did ");
		sql.append(" ) tt ");
		sql.append(" group by cid ");
		List list = contractOrderDAO.findByPage(sql.toString(), contractOrderId,suppliersPkid);
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractQuotationsVo vo = new ContractQuotationsVo();
			vo.setConfigModelName(""+obj[0]);
			vo.setConfigModelId(""+obj[1]);
			vo.setQuantity(""+(obj[2]!=null?obj[2]:0));
			vo.setTotalPrice(""+(obj[3]!=null?obj[3]:0));
			vo.setId(""+obj[4]);
			volist.add(vo);
		}
		return volist;
	}
	@Override
	public List<ContractQuotationsVo> findOrderByContractId(String contractOrderId) {
		List<ContractQuotationsVo> volist = new ArrayList<ContractQuotationsVo>();
		String hql=" from TbContractOrderRelationship co where co.contractOrder.id=?";
		Integer pkid = null;
		if(!BlankUtil.isBlank(contractOrderId))
		{
			pkid = Integer.parseInt(contractOrderId);
		}
		List<TbContractOrderRelationship> list=contractOrderDAO.find(hql.toString(), pkid);
		for (TbContractOrderRelationship contractOrderRelationship:list) {
			ContractQuotationsVo vo=new ContractQuotationsVo();
			vo.setId(""+contractOrderRelationship.getId());
			vo.setConfigModelId(""+contractOrderRelationship.getConfigModels().getId());
			vo.setConfigModelName(""+contractOrderRelationship.getConfigModels().getName());
			vo.setSupplierId(""+contractOrderRelationship.getSuppliers().getCompanyName());
			vo.setPurchasePrice(""+contractOrderRelationship.getPurchasePrice());
			vo.setQuantity(""+contractOrderRelationship.getOrderQuantity());
			vo.setSuppliersQuotationsId(""+contractOrderRelationship.getSuppliersQuotations().getSuppliersQuotationsName());
			volist.add(vo);
		}
		return volist;
	}
	@Override
	public PageInfo<CurrentUnitVo> findCurrentUnit(Integer contractId, String configModelId) {
		Integer pkid = null;
		if(!BlankUtil.isBlank(configModelId)){
			pkid = Integer.parseInt(configModelId);
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.quantity,t1.unitPrice,t1.cname,t1.dname,t1.productDesc,t1.productOrderNo,t2.ordered,t1.id from ");
		sql.append(" (select a.id,a.quantity,a.total_price/a.quantity unitPrice,c.name cname,d.name dname,b.productDesc,b.productOrderNo ");
		sql.append(" from tb_configmodel_data a,tb_data_record b,tb_product_type c,tb_product_category d ");
		sql.append(" where a.data_record_id = b.id and b.product_type_id = c.id and c.category_id = d.id "); 
		sql.append(" and a.config_model_id = ? and d.is_repeat = 1  ");
		sql.append(" ) t1 left join");
		sql.append(" ( select g.model_data_id,sum(g.order_quantity) ordered  ");
		sql.append("  from tb_contract_order e,tb_contract_order_relationship f,tb_contract_order_relationship_data g ");
		sql.append("  where e.id = f.contract_order_id and f.id = g.contract_order_relationship_id ");
		sql.append(" and e.contract_id = ?");
		sql.append(" group by g.model_data_id ");
		sql.append("  ) t2 ");
		sql.append(" on t1.id = t2.model_data_id ");
		List lists = contractOrderDAO.findByPage(sql.toString(), pkid,contractId);
		Object[] obj = new Object[lists.size()];
		List<CurrentUnitVo> unitVolist = new ArrayList<CurrentUnitVo>();
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			CurrentUnitVo unitvo = new CurrentUnitVo();
			unitvo.setQuantity(""+(obj[0]!=null?obj[0]:0));
			unitvo.setUnitPrice(""+(obj[1]!=null?obj[1]:0));
			unitvo.setTypeName(""+obj[2]);
			unitvo.setCategoryName(""+obj[3]);
			unitvo.setProductDesc(""+obj[4]);
			unitvo.setProductOrderNo(""+obj[5]);
			unitvo.setOrdered(""+(obj[6]!=null?obj[6]:0));
			unitvo.setConfigModelDataId(""+obj[7]);
			unitVolist.add(unitvo);
		}
		PageInfo<CurrentUnitVo> pageInfo = new PageInfo<CurrentUnitVo>();
		pageInfo.setRows(unitVolist);
		return pageInfo;
	}
	
	@Override
	public List<CurrentUnitVo> findCurrentUnitDetail(String orderShipId){
		Integer pkid = null;
		if(!BlankUtil.isBlank(orderShipId)){
			pkid = Integer.parseInt(orderShipId);
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select aid,order_quantity,quantity,totlePrice,name,productDesc,productOrderNo,unitPrice,bid from ( ");
		sql.append(" select a.id aid,a.order_quantity,b.quantity, ");
		sql.append(" a.order_quantity*(b.total_price/b.quantity) totlePrice,d.name,c.productDesc,c.productOrderNo,(b.total_price/b.quantity) unitPrice,b.id bid ");
		sql.append(" from tb_contract_order_relationship_data a, ");
		sql.append(" tb_configmodel_data b, ");
		sql.append(" tb_data_record c, ");
		sql.append(" tb_product_type d, ");
		sql.append(" tb_product_category e ");
		sql.append(" where a.model_data_id = b.id ");
		sql.append(" and b.data_record_id = c.id ");
		sql.append(" and c.product_type_id = d.id ");
		sql.append(" and d.category_id = e.id ");
		sql.append(" and a.contract_order_relationship_id = ? ");
		sql.append(" ) tt ");
		List lists = contractOrderDAO.findByPage(sql.toString(), pkid);
		Object[] obj  = new Object[lists.size()];
		List<CurrentUnitVo> volist = new ArrayList<CurrentUnitVo>();
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			CurrentUnitVo unitvo = new CurrentUnitVo();
			unitvo.setId(""+obj[0]);  //全通用单元Id
			unitvo.setOrdered(""+(obj[1]!=null?obj[1]:0));  //下单数
			unitvo.setQuantity(""+(obj[2]!=null?obj[2]:0));  //总数
			BigDecimal bg = new BigDecimal(obj[3].toString());
			unitvo.setTotalPrice(""+(obj[3]!=null?bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue():0)); //单价
			unitvo.setTypeName(""+obj[4]);//类型名称
			unitvo.setProductDesc(""+obj[5]);  //产品描述
			unitvo.setProductOrderNo(""+obj[6]);  //产品编号
			BigDecimal bg1 = new BigDecimal(obj[7].toString());
			unitvo.setUnitPrice(""+(obj[7]!=null?bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue():0)); //总价
			unitvo.setConfigModelDataId(""+obj[8]); //机型配置数据id
			volist.add(unitvo);
		}
		return volist;
	}

	/**
	 * 查询审核询价单
	 */
	@Override
	public PageInfo<CheckinquiryOrderVo> checkInquiryOrder(PageInfo<CheckinquiryOrderVo> pageInfo,Integer contractOrder) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.id,c.status,c.order_no,c.caudit_time,sum(t2mm.totalPrice) totalPrice,sum(t2mm.catalogPrice) catalogPrice,sum(t2mm.purchasePrice) purchasePrice from tb_contract_order c,");
		sql.append(" ( "+checkOrderSqlJoin(contractOrder)+" )  t2mm ");
		sql.append(" where c.id = t2mm.orderid ");
		if(!BlankUtil.isBlank(contractOrder)){
			sql.append(" and c.id = ? ");
		}
		sql.append(" group by c.id ");
		List list = contractOrderDAO.queryPage(sql.toString(),pageInfo,contractOrder);
		Object[] obj = new Object[list.size()];
		List<CheckinquiryOrderVo> orderlist = new ArrayList<CheckinquiryOrderVo>();
		double totalPrice; //销售总价
		double purchasePrice; //采购总价
		double inquiryerPrice; //目录合价
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			CheckinquiryOrderVo vo = new CheckinquiryOrderVo();
			vo.setId(""+obj[0]); //销售订单id
			vo.setStatus(""+obj[1]);  //状态
			vo.setOrderNo(""+obj[2]); //订单号 
			vo.setCauditTime(""+DateFormatUtil.longFormatDateTime(obj[1]!=null?(Date)obj[3]:null));//销售订单时间
			totalPrice = Double.parseDouble(""+(obj[4]!=null?obj[4]:0));
			vo.setTotalPrice(""+(obj[4]!=null?obj[4]:0)); //销售总价
			vo.setCatalogPrice(""+(obj[5]!=null?obj[5]:0)); //目录合价
			purchasePrice = Double.parseDouble(""+(obj[6]!=null?obj[6]:0));
			vo.setPurchasePrice(""+(obj[6]!=null?obj[6]:0)); //采购总价
			inquiryerPrice = (totalPrice-purchasePrice)/totalPrice*100;
			vo.setInquiryer(inquiryerPrice+"%");  //毛利率
			orderlist.add(vo);
		}
		PageInfo<CheckinquiryOrderVo> page = new PageInfo<CheckinquiryOrderVo>(pageInfo.getCurrentPage(),orderlist.size(),pageInfo.getPageSize(),orderlist);
		return page;
	}
	
	/**
	 * 
	* 方法用途和描述:询价单审核查询sql
	* @return
	* @author chenliang 新增日期：2013-3-15
	* @since ContractManage
	 */
	private String checkOrderSqlJoin(Integer contractOrderid){
		StringBuffer sql = new StringBuffer();
		sql.append("  select t2.aid shipid,t2.contract_order_id orderid, ");
		sql.append("  case when mm.totalPrice1 is null then t2.totalPrice else (t2.totalPrice+mm.totalPrice1) end totalPrice, ");
		sql.append("  case when mm.catalogPrice1 is null then t2.catalogPrice else (t2.catalogPrice+mm.catalogPrice1) end catalogPrice, ");
		sql.append("  t2.purchasePrice, ");
		sql.append("  t2.order_quantity,t2.modelName,t2.modelid,t2.zid,t2.tbsqUrl ");
		sql.append("  from ( ");
		sql.append("  select a.id aid,a.contract_order_id, ");
		sql.append("  a.order_quantity*tt.totalPrice totalPrice, ");
		sql.append("  sum(a.purchase_price) purchasePrice, ");
		sql.append("  a.order_quantity*sum(tt.catalogPrice) catalogPrice, ");
		sql.append("  a.order_quantity,tt.modelName,tt.modelid,tt.id zid,tbsq.url tbsqUrl");
		sql.append("  from  ");
		sql.append("  ( ");
		sql.append("  select  z.id, z.name modelName, z.id modelid, ");
		sql.append("  sum(b.total_price) totalPrice, ");
		sql.append("  sum(b.catalog_price) catalogPrice ");
		sql.append("  from tb_configmodel_data b,tb_data_record k, tb_product_type s, tb_product_category j,tb_config_models z ");
		sql.append("  where  z.id = b.config_model_id ");
		sql.append("  and b.data_record_id=k.id ");
		sql.append("  and k.product_type_id = s.id ");
		sql.append("  and s.category_id = j.id ");
		sql.append("  and j.is_repeat=0 ");
		sql.append("  group by  z.id ");
		sql.append("  ) tt,tb_contract_order_relationship a left join tb_suppliers_quotations tbsq on a.suppliers_quotations_id=tbsq.superliers_quotations_id  where a.config_model_id = tt.modelid group by aid ");
		sql.append("  ) t2 ");
		sql.append("  left join ");
		sql.append("  ( select g.contract_order_relationship_id, ");
		sql.append("  sum(g.order_quantity*(h.total_price/h.quantity)) totalPrice1, ");
		sql.append("  sum(g.order_quantity*(h.catalog_price/h.quantity)) catalogPrice1 ");
		sql.append("  from tb_contract_order_relationship_data g,tb_configmodel_data h ");
		sql.append("  where g.model_data_id=h.id ");
		sql.append("  group by g.contract_order_relationship_id ");
		sql.append("  ) mm  on t2.aid = mm.contract_order_relationship_id ");
		return sql.toString();
	}

	/**
	 * 查询审核询价单详情
	 */
	@Override
	public List<CheckinquiryOrderVo> checkInquiryOrderDetail(String contractOrderId) {
		Integer orderDetailpkid = null;
		if(!BlankUtil.isBlank(contractOrderId)){
			orderDetailpkid = Integer.parseInt(contractOrderId);
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.id,sum(t2mm.totalPrice) totalPrice,sum(t2mm.catalogPrice) catalogPrice,sum(t2mm.purchasePrice) purchasePrice,");
		sql.append(" t2mm.modelName,t2mm.modelid,t2mm.order_quantity,t2mm.shipid,t2mm.tbsqUrl from tb_contract_order c,"); //c.status,c.order_no,c.caudit_time,
		sql.append(" ( "+checkOrderSqlJoin(orderDetailpkid)+" ) t2mm ");
		sql.append(" where c.id = t2mm.orderid ");
		if(!BlankUtil.isBlank(orderDetailpkid)){
			sql.append(" and c.id = ? ");
		}
		sql.append(" group by shipid ");
		List list = contractOrderDAO.findByPage(sql.toString(),orderDetailpkid);
		Object[] obj = new Object[list.size()];
		List<CheckinquiryOrderVo> orderlist = new ArrayList<CheckinquiryOrderVo>();
		double totalPrice; //销售总价
		double purchasePrice; //采购总价
		double inquiryerPrice; //目录合价
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			CheckinquiryOrderVo vo = new CheckinquiryOrderVo();
			vo.setId(""+obj[0]); //销售订单id
			totalPrice = Double.parseDouble(""+(obj[1]!=null?obj[1]:0));
			vo.setTotalPrice(""+totalPrice);  
			vo.setCatalogPrice(""+(obj[2]!=null?obj[2]:0));
			purchasePrice = Double.parseDouble(""+(obj[3]!=null?obj[3]:0));
			vo.setPurchasePrice(""+purchasePrice);
			vo.setConfigModelName(""+obj[4]);
			vo.setConfigModelId(""+obj[5]);
			vo.setQuantity(""+(obj[6]!=null?obj[6]:0));
			inquiryerPrice = (totalPrice-purchasePrice)/totalPrice*100;
			vo.setInquiryer(inquiryerPrice+"%"); //毛利率
			vo.setSuppliersQuotationUrl(""+obj[8]); //供应商报价表下载地址
			orderlist.add(vo);
		}
		return orderlist;
	}

	/**
	 * 审核询价单
	 */
	@Override
	public boolean checkedInquiriy(CheckedVo checkedVo) {
		int status = 0; //状态
		int cauditId = 0 ;// 审核id
		int pkid = 0;
		if(!BlankUtil.isBlank(checkedVo.getStatus())){
			status = Integer.parseInt(checkedVo.getStatus());
			if(1==status){//前台传过来的1为同意,设置到数据库中用2表示
				status = S_ContractOrder.CONTRACTORDER_PASSED; 
			}else if(0==status){ //前台传过来的0为不同意,设置到数据库中用3表示
				status = S_ContractOrder.CONTRACTORDER_FAIL;
			}
		}
		if(!BlankUtil.isBlank(checkedVo.getUser_id())){
			cauditId = Integer.parseInt(checkedVo.getUser_id());
		}
		if(!BlankUtil.isBlank(checkedVo.getId())){
			pkid = Integer.parseInt(checkedVo.getId());
		}
		String hql = "update TbContractOrder a set status=?,caudittime=NOW(),caudit.id=? where id=? ";
		contractOrderDAO.executeByHQL(hql, status,cauditId,pkid);
		
		//完成审核询价单的待办任务
		TaskVo taskVo = new TaskVo();
		taskVo.setParamPkid(BusinessConstants.askorders_pkid+pkid);
		taskService.finishTask(taskVo);
		return true;
	}

	@Override
	public boolean confirmAllReceipt(Integer contractOrderId) {
		TbContractOrder order = contractOrderDAO.findById(contractOrderId);
		if(order==null){
			return false;
		}
		purchaseOrderService.confirmAllReceipt(contractOrderId);
		order.setStatus(S_ContractOrder.CONTRACTORDER_ARRIVAL);
		contractOrderDAO.saveOrUpdate(order);
		return true;
	}

	@Override
	public List<ContractQuotationsVo> querySaleOrder(Integer contractOrderId,Integer suppliersId) {
		if(contractOrderId==null)
			return null;
		return contractOrderDAO.querySaleOrder(contractOrderId,suppliersId);
	}

	@Override
	public boolean saveSalOrder(ContractOrderVo contractOrderVo,String contractOrderId,Long createUserId) {
		return saveContractOrder(contractOrderVo,contractOrderId,createUserId,1);
	}

	@Override
	public boolean saveSalTemporaryOrder(ContractOrderVo contractOrderVo,
			String contractOrderId, Long createUserId) {
		return saveContractOrder(contractOrderVo,contractOrderId,createUserId,2);
	}
	
	/**
	 * 
	 * 保存正式合同订单和临时合同订单
	 * 
	 * @param contractOrderVo
	 * @param contractOrderId
	 * @param createUserId
	 * @param contractType 1：正式合同，2：临时合同
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	private boolean saveContractOrder(ContractOrderVo contractOrderVo,String contractOrderId,Long createUserId,Integer contractType){
		if(contractOrderVo==null || contractOrderId==null){
			return false;
		}
		//机型配置IDs
		String[] confid = contractOrderVo.getConfigModelId().split(",");
		//报价表IDs
		String[] quotid = contractOrderVo.getQuotionIds().split(",");
		//下单数
	    String[] nums = contractOrderVo.getRoderNums().split(",");
	    Integer projectId = null;
	    Integer totalOrder = 0;
	    for(int i = 0; i < quotid.length; i++) {
	    	String quotationsId =quotid[i];
			String configModelId = confid[i];
			//修改报价表机型配置中间表中的已采购数量
			Integer newOrder = Integer.parseInt(nums[i]);
			String count = "select d from TbQuotationsData d  where d.configModels.id=? and d.quotations.id = ?";
			List orderedList = quotationsDataDAO.find(count, Integer.parseInt(configModelId),Integer.parseInt(quotationsId));
			if(orderedList.size()<=0){
				return false; 
			}
			TbQuotationsData  d =(TbQuotationsData) orderedList.get(0);
			totalOrder = d.getOrdered() + newOrder;
			if(totalOrder > d.getQuantity()){
				return false;
			}
	    }
		
		//一、保存销售订单总表数据
		TbContractOrder contractOrder = new TbContractOrder();
		//1、设置创建时间
		contractOrder.setCreatetime(new Date());
		//2、设置合同
		if(contractType==1){
			TbContract contract = contractDAO.findById(Integer.parseInt(contractOrderId));
			contractOrder.setContract(contract);
			projectId = contract.getProject().getProjectId();
			contract.setStatus(S_Contract.CONTRACT_RUNING);
			contractDAO.save(contract);
		}else if(contractType==2){
			TbContractTemporary contract =contractTemporaryDAO.findById(Integer.parseInt(contractOrderId));
			contractOrder.setContractTemporary(contract);
			contract.setStatus(S_Contract.CONTRACT_RUNING);
			contractTemporaryDAO.save(contract);
		}
		contractOrder.setContractType(contractType);
		
		//3、设置订单编号
		contractOrder.setOrderNo(contractOrderVo.getOrderNo());
		//4、设置订单创建者
		TbUser createUser = new TbUser();
		createUser.setId(createUserId);
		contractOrder.setCreator(createUser);
		//5、设置订单备注
		contractOrder.setNote(contractOrderVo.getNote());
		//6、设置订单状态
		contractOrder.setStatus(S_ContractOrder.CONTRACTORDER_ENQUIRY);
		//7、收款信息
		contractOrder.setReceiveInfo(contractOrderVo.getReceiveInfo());
		//8、设置收货人
		contractOrder.setConsignee(contractOrderVo.getConsignee());
		//9、设置收货人联系号码
		contractOrder.setConsigneePhone(contractOrderVo.getConsigneePhone());
		//判断地址是否有记录，无记录则保存
		TbCustomerAddressInfo customerAddressInfo = customerAddressInfoService.findByField("receiveInfo", contractOrderVo.getReceiveInfo());
		if(customerAddressInfo ==null){
			customerAddressInfo = new TbCustomerAddressInfo();
			customerAddressInfo.setReceiveInfo(contractOrderVo.getReceiveInfo());
			customerAddressInfoService.save(customerAddressInfo);
		}
		try {
			int conorderId = (Integer) this.save(contractOrder);//合同订单ID
			TbContractOrderRelationship orderRelationship = null;
			for(int i = 0; i < quotid.length; i++) {
				String quotationsId =quotid[i];
				String configModelId = confid[i];
				//修改报价表机型配置中间表中的已采购数量
				Integer newOrder = Integer.parseInt(nums[i]);
				orderRelationship = new TbContractOrderRelationship();
				//1、设置报价表
				TbQuotations quotations = new TbQuotations();
				quotations.setId(Integer.parseInt(quotationsId));
				orderRelationship.setQuotations(quotations);
				//2、设置机型配置
				TbConfigModels configModels = new TbConfigModels();
				configModels.setId(Integer.parseInt(configModelId));
				orderRelationship.setConfigModels(configModels);
				//3、设置销售订单
				TbContractOrder contrOrd = new TbContractOrder();
				contrOrd.setId(conorderId);
				orderRelationship.setContractOrder(contrOrd);
				//4、创建时间
				orderRelationship.setCreatetime(new Date());
				//5、采购数量
				orderRelationship.setOrderQuantity(newOrder);
				//二、保存销售订单详情数据
				contractOrderRelationshipService.saveRelationship(orderRelationship);
				//三、修改报价表中的已下单数
				this.quotationsDataService.updateQuotationsData(configModelId,quotationsId,newOrder);
				List<TbModelData> modelDataList = (List<TbModelData> ) this.modelDataDAO.find("select a from TbModelData a where a.configModel.id = ?", Integer.parseInt(configModelId));
				//四、保存数据单元的数据
				if(modelDataList.size()>0){
					for (TbModelData mData : modelDataList) {
						TbContractOrderRelationshipData shipData =  new TbContractOrderRelationshipData();
						shipData.setContractOrderRelationship(orderRelationship);
						shipData.setModelData(mData);
						shipData.setOrderQuantity(newOrder);
						contractOrderRelationshipDataService.save(shipData);
					}
				}else{
					LOGGER.info("modelDataList is null.");
				}
			}
			//TODO 提交任务到采购接口人,param为连接地址的入参，根据临时订单和正式订单的跳转url可能不一样
			String param = "";
			List<UserVo>  IpUser = userService.queryIpUserByRole(5l);
			//销售下发任务到采购，让采购进行询价
			for(UserVo personInChargeId : IpUser){
				taskService.startTask(null, createUserId, TaskName.ask_Price+"_"+contractOrder.getOrderNo(), 
						projectId, personInChargeId.getId(), BusinessConstants.task_saleOrderFinish, contractOrderVo.getNote(), 
						param,
						BusinessConstants.sell_ask_order_pkid+contractOrder.getId()); //拼接询价单表主键id
			}
			
			//判断报价表是否已经完成下单
//			select * from  tb_quotations_data a where a.quotations_id in (1,2,3,4) and ordered < quantity;
			String quotations_Ids = contractOrderVo.getQuotionIds();
			if(contractOrderVo.getQuotionIds().endsWith(",")){
				quotations_Ids = contractOrderVo.getQuotionIds().substring(0, contractOrderVo.getQuotionIds().length()-1);
			}
			String hql = "select d from TbQuotationsData d  where d.ordered < d.quantity and d.quotations.id in ("+quotations_Ids+")";
			List<TbQuotationsData> unOrder =quotationsDataDAO.find(hql);
			if(unOrder.size()==0){
				if(contractType==1){
					TbContract contract = contractDAO.findById(Integer.parseInt(contractOrderId));
					contract.setStatus(S_Contract.CONTRACT_FINISHED);
					contractDAO.save(contract);
				}else if(contractType==2){
					TbContractTemporary contract =contractTemporaryDAO.findById(Integer.parseInt(contractOrderId));
					contract.setStatus(S_Contract.CONTRACT_FINISHED);
					contractTemporaryDAO.save(contract);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	
	}
	
	@Override
	public String createOrderNO(Integer contractId,Integer typeId) throws Exception{
		//1、生成销售订单号
		String orderNumber = generateSalOrder(contractId, typeId);
		System.out.println("+++++orderNumber:"+orderNumber);
		//判断生成的订单号是否已经存在
		orderNumber = isExcexitOrderNO(orderNumber,false);
		return orderNumber;
	}

	/**
	 * 
	* 方法用途和描述: 生成订单号
	* @param contractId
	* @param typeId
	* @return
	* @throws Exception
	* @author chenliang 新增日期：2013-4-24
	* @since ContractManage
	 */
	private String generateSalOrder(Integer contractId,Integer typeId) throws Exception {
		String hql = "";
		if(BlankUtil.isBlank(contractId)){
			LOGGER.info("GenerateSalOrder contnractId is null.");
			throw new NullArgumentException("GenerateSalOrder contnractId is null:");
		}
		String orderNumber = "";
		if(1==typeId){ //正式合同
			//正式合同订单编号
			//格式：合同编号-00001
			//1、先根据合同id查询该合同下最大合同订单号
			hql = "select orderNo from TbContractOrder a where a.id = (select max(id) from TbContractOrder b where b.contract.id = ? )";
			List orderList = contractOrderDAO.find(hql, contractId);
			if(orderList.size()>0){
				String contractNo = (String) orderList.get(0);
				if(!BlankUtil.isBlank(contractNo)){
					if(contractNo.length()>15 && contractNo.contains("-")){ //订单编号必须包含14位合同编号和‘-’
						String prevStr = contractNo.substring(0,contractNo.lastIndexOf("-")+1);
						String num = contractNo.substring(contractNo.lastIndexOf("-")+1,contractNo.length());
						int nextOrderNum = Integer.parseInt(num)+1;
						orderNumber = prevStr+nextOrderNum;
					}
				}
			}
			if(BlankUtil.isBlank(orderNumber)){
				hql = "select contractNo from TbContract where id = ? ";
				List contractList = contractDAO.find(hql, contractId);
				if(contractList.size()>0){
					String contractNO = (String) contractList.get(0);
					orderNumber = contractNO+"-00001";
				}
			}
		}else if(2==typeId){ //临时合同
			//1、查询该临时合同下最大的订单号追加
			hql = "select orderNo from TbContractOrder a where a.id = (select max(id) from TbContractOrder a where a.contractTemporary.id = ?  )";
			List tempoOrderList = contractOrderDAO.find(hql, contractId);
			if(tempoOrderList.size()>0){
				String contractNo = (String) tempoOrderList.get(0);
				if(!BlankUtil.isBlank(contractNo)){
					if(contractNo.length()>16 && contractNo.contains("-")){ //订单编号必须包含15位临时合同编号和‘-’
						String prevStr = contractNo.substring(0,contractNo.lastIndexOf("-")+1);
						String num = contractNo.substring(contractNo.lastIndexOf("-")+1,contractNo.length());
						int nextOrderNum = Integer.parseInt(num)+1;
						orderNumber = prevStr+nextOrderNum;
					}
				}
			}
			//2、查询该临时合同拼接第一个临时订单号
			if(BlankUtil.isBlank(orderNumber)){
				hql = "select contractNo from TbContractTemporary where id = ? ";
				List contractList = contractDAO.find(hql, contractId);
				if(contractList.size()>0){
					String contractNO = (String) contractList.get(0);
					orderNumber = contractNO+"-00001";
				}
			}
		}else{
			LOGGER.error("GenerateSalOrder typeId is error.");
			throw new NullArgumentException("GenerateSalOrder typeId is error");
		}
		return orderNumber;
	}
	
	private String isExcexitOrderNO(String orderNumber,boolean isExceit){
		String hql = "";
		if(!BlankUtil.isBlank(orderNumber)){
			if(isExceit){ //判断是否需要递归
				if(orderNumber.length()>16 && orderNumber.contains("-")){ //订单编号必须包含15位临时合同编号和‘-’
					String prevStr = orderNumber.substring(0,orderNumber.lastIndexOf("-")+1);
					String num = orderNumber.substring(orderNumber.lastIndexOf("-")+1,orderNumber.length());
					int nextOrderNum = Integer.parseInt(num)+1;
					orderNumber = prevStr+nextOrderNum;
				}
			}
			hql = "from TbContractOrder a where a.orderNo=?";
			List list = contractOrderDAO.find(hql, orderNumber);
			if(list.size()>0){
				return isExcexitOrderNO(orderNumber,true);//递归调用
			}else{
				return orderNumber;
			}
		}else{
			LOGGER.error("isExcexitOrderNO orderNumber is error.");
			throw new NullArgumentException("isExcexitOrderNO orderNumber is null:");
		}
	}
}
