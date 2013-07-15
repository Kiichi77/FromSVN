package cn.com.kxcomm.contractmanage.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderContractBillDAO;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractBill;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractBillService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractBillVo;

@Service("purchaseOrderContractBillService")
public class PurchaseOrderContractBillService extends CommonService<TbPurchaseOrderContractBill> implements IPurchaseOrderContractBillService{
	private static final Logger LOGGER = Logger.getLogger(PurchaseOrderContractBillService.class);
	@Autowired(required=true)
	private PurchaseOrderContractBillDAO purchaseOrderContractBillDAO;
	@Override
	public CommonDAO<TbPurchaseOrderContractBill> getBindDao() {
		return purchaseOrderContractBillDAO;
	}
	
	/**
	 * 分页查询付款明细
	 *
	 * @author lujia 新增日期：2013-3-14
	 * @param 
	 * @return
	 */
	@Override
	public PageInfo<PurchaseOrderContractBillVo> listBill(PageInfo<PurchaseOrderContractBillVo> pageInfo,int id)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT id,project_name,ct,pct,contract_money,scn,payment,use_overpay_money,user_name,create_time,tpcn,use_overpay,pbno,pbtime from ");
		sql.append(" (SELECT DISTINCT pb.id,p.project_name,c.title ct,pc.title pct,pc.contract_money,s.company_name scn,pb.payment,pb.use_overpay_money,u.user_name,pb.create_time,tpc.company_name tpcn,pb.use_overpay,pb.check_no pbno,pb.payment_time pbtime");
		sql.append(" FROM tb_purchase_order_contract_bill pb,tb_purchase_order_contractfile pc,tb_contract c,tb_project p,tb_suppliers s,tb_purchase_company tpc,tb_user u ");
		sql.append(" WHERE pb.purchase_order_contractfile_id=pc.id ");
		sql.append(" AND pc.contract_id=c.id ");
		sql.append(" AND c.project_id=p.project_id ");
		sql.append(" AND pc.suppliers_id=s.id ");
		sql.append(" AND pb.purchase_company_id=tpc.id ");
		sql.append(" AND pb.payer=u.id ");
		sql.append(" AND pb.purchase_order_contractfile_id=? ");
		sql.append(" )tt ");
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(*) as count from tb_purchase_order_contract_bill ");
		int count = purchaseOrderContractBillDAO.findCount(countSql.toString());
	    List list = purchaseOrderContractBillDAO.findByPage(sql.toString(), pageInfo,id);
	    List<PurchaseOrderContractBillVo> listBill=new ArrayList<PurchaseOrderContractBillVo>();
		Object[] obj = new Object[list.size()];	
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			PurchaseOrderContractBillVo purchaseOrderContractBillVo=new PurchaseOrderContractBillVo();
			purchaseOrderContractBillVo.setId(obj[0].toString());
			purchaseOrderContractBillVo.setProjectName(obj[1].toString());
			purchaseOrderContractBillVo.setContract(obj[2].toString());
			purchaseOrderContractBillVo.setTitle(obj[3].toString());
			NumberFormat nf = NumberFormat.getNumberInstance();
	        nf.setMaximumFractionDigits(2);
			purchaseOrderContractBillVo.setContractMoney(nf.format(obj[4]));
			purchaseOrderContractBillVo.setSuppliers(obj[5].toString());
			purchaseOrderContractBillVo.setPayment(obj[6]==null?"":obj[6].toString());
			if(null!=obj[7]){
				purchaseOrderContractBillVo.setUserOverPayMoney(obj[7].toString());
			}
			purchaseOrderContractBillVo.setPayer(obj[8].toString());
			purchaseOrderContractBillVo.setCreateTime(obj[9].toString());
			purchaseOrderContractBillVo.setPurchaseCompany(obj[10].toString());
			purchaseOrderContractBillVo.setUseOverPay(obj[11].toString());
			purchaseOrderContractBillVo.setCheckNo(obj[12]==null?"":obj[12].toString());
			purchaseOrderContractBillVo.setPaymentTime(obj[13]==null?"":obj[13].toString());
			listBill.add(purchaseOrderContractBillVo);
		}
		PageInfo<PurchaseOrderContractBillVo> page = new PageInfo<PurchaseOrderContractBillVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listBill);
		return page;
	}
}
