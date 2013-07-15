package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderRelationshipDAO;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderRelationship;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderRelationshipService;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderRelationshipVo;
@Service("purchaseOrderRelationshipService")
public class PurchaseOrderRelationshipService extends CommonService<TbPurchaseOrderRelationship> implements IPurchaseOrderRelationshipService{
	private static final Logger LOG = Logger.getLogger(PurchaseOrderRelationshipService.class);
   
	@Autowired(required=true)
	private PurchaseOrderRelationshipDAO purchaseOrderRelationshipDAO;
	@Override
	public CommonDAO<TbPurchaseOrderRelationship> getBindDao() {
		return purchaseOrderRelationshipDAO;
	}

	/**
	* 
	* 方法用途和描述: 根据采购订单Id查询配置详情
	* @return
	* @author luj 新增日期：2013-3-18
	* @since ContractManage
	*/
	@Override
	public List<PurchaseOrderRelationshipVo> queryAllConfig(String purchaseOrderId)
	{
		Integer orderId=null;
		if(!BlankUtil.isBlank(purchaseOrderId)){
			orderId=Integer.parseInt(purchaseOrderId);
		}
		StringBuffer sql=new StringBuffer();
		sql.append(" select porId,poNo,cmName,porQuantity,cmId from( ");
		sql.append(" SELECT por.id porId,po.order_no poNo,cm.name cmName,por.purchase_quantity porQuantity,cm.id cmId");
		sql.append(" from tb_purchase_order_relationship por,tb_contract_order_relationship cor,tb_purchase_order po,tb_config_models cm ");
		sql.append(" WHERE por.purchase_order_id=po.id AND por.contractorderrelationship_id=cor.id ");
		sql.append(" AND cor.config_model_id=cm.id AND po.id=? ) tt");
		List list=this.purchaseOrderRelationshipDAO.findByPage(sql.toString(), purchaseOrderId);
		Object[] obj = new Object[list.size()];
		List<PurchaseOrderRelationshipVo> listRelationship = new ArrayList<PurchaseOrderRelationshipVo>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[])list.get(i);
			PurchaseOrderRelationshipVo purchaseOrderRelationshipVo=new PurchaseOrderRelationshipVo();
			purchaseOrderRelationshipVo.setId(obj[0].toString());
			purchaseOrderRelationshipVo.setPurchaseOrderNo(obj[1].toString());
			purchaseOrderRelationshipVo.setConfigModels(obj[2].toString());
			purchaseOrderRelationshipVo.setPurchaseQuantity(obj[3].toString());
			purchaseOrderRelationshipVo.setConfigModelsId(obj[4].toString());
			listRelationship.add(purchaseOrderRelationshipVo);
		}
		return listRelationship;
	}

	/**
	 * 根据采购订单id查询采购订单详情
	 */
	@Override
	public List<PurchaseOrderRelationshipVo> findDetailByOrderPkid(String purchaseOrderId) {
		int pkid = 0;
		if(!BlankUtil.isBlank(purchaseOrderId)){
			pkid = Integer.parseInt(purchaseOrderId);
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select name,did,purchase_quantity,company_name,aid from( ");
		sql.append(" select d.name,d.id did,a.purchase_quantity,c.company_name,a.id aid ");
		sql.append(" from tb_purchase_order_relationship a,tb_contract_order_relationship b,tb_suppliers c,tb_config_models d ");
		sql.append(" where a.contractorderrelationship_id = b.id ");
		sql.append(" and b.suppliers_id= c.id ");
		sql.append(" and b.config_model_id = d.id ");
		sql.append(" and a.purchase_order_id = ? ");
		sql.append(" ) tt ");
		List lists = purchaseOrderRelationshipDAO.findByPage(sql.toString(),pkid);
		Object[] obj = new Object[lists.size()];
		List<PurchaseOrderRelationshipVo> shipVolist = new ArrayList<PurchaseOrderRelationshipVo>();
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			PurchaseOrderRelationshipVo vo = new PurchaseOrderRelationshipVo();
			vo.setConfigModels(""+obj[0]);  //机型配置名称
			vo.setConfigModelsId(""+obj[1]); //机型配置id
			vo.setPurchaseQuantity(""+obj[2]);  //采购数量
			vo.setCompanyName(""+obj[3]);  //供应商
			vo.setPurchaseOrderId(""+obj[4]);  //主键id
			shipVolist.add(vo);
		}
		return shipVolist;
	}

	/**
	 * 根据采购订单详情id查询采购订单详情相关联的全通用单元数据
	 */
	@Override
	public List<CurrentUnitVo> findUnitDetailByOrderDetailPkid(String purchaseOrderDetailId) {
		int detailId = 0;
		if(!BlankUtil.isBlank(purchaseOrderDetailId)){
			detailId = Integer.parseInt(purchaseOrderDetailId);
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select order_quantity ,model_data_id,contractorderrelationship_id,ename,fname,productDesc from ( ");
		sql.append(" select b.order_quantity ,b.model_data_id,a.contractorderrelationship_id,e.name ename,f.name fname,d.productDesc ");
		sql.append(" from tb_purchase_order_relationship a,tb_purchase_order_relationship_data b ");
		sql.append(" ,tb_configmodel_data c,tb_data_record  d,tb_product_type e,tb_product_category f ");
		sql.append(" where a.id = b.purchase_order_relationship_id ");
		sql.append(" and b.model_data_id = c.id ");
		sql.append(" and c.data_record_id = d.id ");
		sql.append(" and d.product_type_id = e.id ");
		sql.append(" and e.category_id=f.id ");
		sql.append(" and a.id = ? ");
		sql.append(" group by b.model_data_id,b.purchase_order_relationship_id ");
		sql.append(" ) tt ");
		List volist = purchaseOrderRelationshipDAO.findByPage(sql.toString(), detailId);
		List<CurrentUnitVo> listVo = new ArrayList<CurrentUnitVo>();
		Object[] obj = new Object[volist.size()];
		for (int i = 0; i < volist.size(); i++) {
			obj = (Object[]) volist.get(i);
			CurrentUnitVo vo = new CurrentUnitVo();
			vo.setOrdered(""+obj[0]); //已采购数
			vo.setConfigModelDataId(""+obj[1]); //机型配置数据id
			vo.setId(""+obj[2]);  //销售订单详情id
			vo.setTypeName(""+obj[3]); //类型名称
			vo.setCategoryName(""+obj[4]);//总类型名称
			vo.setProductDesc(""+obj[5]); //产品描述
			listVo.add(vo);
		}
		return listVo;
	}
}
