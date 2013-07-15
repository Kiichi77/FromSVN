package cn.com.kxcomm.contractmanage.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.dao.InvoiceDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.entity.TbInvoice;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IInvoiceService;
import cn.com.kxcomm.contractmanage.status.S_Invoice;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.InvoiceVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.vo.UserVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("invoiceService")
public class InvoiceService extends CommonService<TbInvoice> implements IInvoiceService{
	private static final Logger LOGGER = Logger.getLogger(InvoiceService.class);
    
	@Autowired(required=true)
	private InvoiceDAO invoiceDAO;
	@Autowired(required = true)
	private UserServiceImpl userService;
	@Autowired(required = true)
	private TaskService taskService;
	@Autowired(required=true)
	private ContractDAO contractDAO;
	
	@Override
	public CommonDAO<TbInvoice> getBindDao() {
		return invoiceDAO;
	}
	
	/**
	* 
	* 方法用途和描述: 分页查询所有的发票
	* @return
	* @author luj 新增日期：2013-3-6
	* @since ContractManage
	*/
	@Override
	public PageInfo<InvoiceVo> listInvoice(PageInfo<InvoiceVo> pageInfo,String processId,Long userId,InvoiceVo paramVo) {
		StringBuilder hql=new StringBuilder();
		StringBuilder countHql = new StringBuilder();
		hql.append("select invoice from TbInvoice invoice where 1=1");
		countHql.append("select count(invoice.id) from TbInvoice invoice where 1=1");
		if(!BlankUtil.isBlank(userId) && !"".equals(userId)){
			hql.append("  and invoice.create.id=? ");
			countHql.append("  and invoice.create.id=? ");
		}
		if(!BlankUtil.isBlank(processId) && !"".equals(processId)){
			if(!"null".equals(processId)){
				hql.append(" and invoice.processId=? ");
				countHql.append("  and invoice.processId=? ");
			}else{
				processId = null;
			}
		}
		if(!BlankUtil.isBlank(paramVo.getCreatetime())){
			hql.append(" and date_format(invoice.createtime,'%Y-%m-%d') >= date_format('"+paramVo.getCreatetime()+"','%Y-%m-%d') ");
			countHql.append(" and date_format(invoice.createtime,'%Y-%m-%d') >= date_format('"+paramVo.getCreatetime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(paramVo.getEndtime())){
			hql.append(" and date_format(invoice.createtime,'%Y-%m-%d') <= date_format('"+paramVo.getEndtime()+"','%Y-%m-%d') ");
			countHql.append(" and date_format(invoice.createtime,'%Y-%m-%d') <= date_format('"+paramVo.getEndtime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(paramVo.getCompanyName())){
			hql.append(" and purchaseCompany.companyName like '"+paramVo.getCompanyName()+"' ");
			countHql.append(" and purchaseCompany.companyName like '%"+paramVo.getCompanyName()+"%'  ");
		}
		PageInfo<TbInvoice> p =new PageInfo<TbInvoice>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List<TbInvoice> list=invoiceDAO.pageInfoQuery(hql.toString(),p,userId,processId);
		int count = invoiceDAO.findTotalCount(countHql.toString(),userId,processId);
		List<InvoiceVo> listInvoiceVo=new ArrayList<InvoiceVo>();
		for(TbInvoice invoice:list)
		{
			InvoiceVo invoiceVo=new InvoiceVo();
			invoiceVo.setId(invoice.getId().toString());
			NumberFormat nf = NumberFormat.getNumberInstance();
	        nf.setMaximumFractionDigits(2);
			invoiceVo.setAmount(nf.format(invoice.getAmount()));
			invoiceVo.setCompanyName(invoice.getPurchaseCompany().getCompanyName());
			invoiceVo.setContractTitle(invoice.getContract().getTitle());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
			invoiceVo.setCreatetime(sdf.format(invoice.getCreatetime()));
			invoiceVo.setCustomer(invoice.getCustomer().getCompanyName());
			invoiceVo.setStatus(invoice.getStatus().toString());
			invoiceVo.setTaxType(invoice.getTaxType().toString());
			invoiceVo.setUserName(invoice.getCreate().getUserName());
			invoiceVo.setContractDownPath(invoice.getContract().getDownloadUrl());
			listInvoiceVo.add(invoiceVo);
		}
		PageInfo<InvoiceVo> page = new PageInfo<InvoiceVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listInvoiceVo);
		return page;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个发票信息
	* @return
	* @author luj 新增日期：2013-3-7
	* @since ContractManage
	*/
	@Override
	public InvoiceVo queryInvoiceById(String id) {
		TbInvoice invoice = this.invoiceDAO.findById(Integer.parseInt(id));
		InvoiceVo invoiceVo = new InvoiceVo();
		invoiceVo.setId(""+invoice.getId());
		invoiceVo.setAmount(""+invoice.getAmount());
		invoiceVo.setCompanyName(invoice.getPurchaseCompany().getCompanyName());
		invoiceVo.setContractTitle(invoice.getContract().getTitle());
		invoiceVo.setCreatetime(""+invoice.getCreate());
		invoiceVo.setCustomer(invoice.getCustomer().getCompanyName());
		invoiceVo.setStatus(""+invoice.getStatus());
		invoiceVo.setTaxType(""+invoice.getTaxType());
		invoiceVo.setUserName(""+invoice.getCreate().getUserName());
		return invoiceVo;
	}
	
	@Override
	public void merge(TbInvoice invoice) {
		invoiceDAO.merge(invoice);
	}

	/**
	 * 审核发票 
	 */
	@Override
	public void checked(CheckedVo checkedVo) {
		int pkid = 0;
		if(!BlankUtil.isBlank(checkedVo.getId())){
			pkid = Integer.parseInt(checkedVo.getId());
		}
		String hql = "update TbInvoice tb set status=2,remark=? where id=? ";
		invoiceDAO.executeByHQL(hql, checkedVo.getDescribe(),pkid);
		
		//完成发票申请的待办任务
		TaskVo taskVo = new TaskVo();
		taskVo.setParamPkid(BusinessConstants.invoic_pkid+pkid);
		taskService.finishTask(taskVo);
	}

	@Override
	public void addInvoice(TbInvoice invoice, Long createUserId,double amountdd) {
		invoice.setCreatetime(new Date());
		invoice.setStatus(S_Invoice.INVOICE_NOT_SEND);
		TbUser user=new TbUser();
		user.setId(createUserId);
        invoice.setCreate(user);
       	TbCustomer customer=new TbCustomer();
       	customer.setCustomerId(invoice.getCustomer().getCustomerId());
       	invoice.setCustomer(customer);
       	invoice.setAmount(amountdd);
       	TbContract contract = contractDAO.findById(invoice.getContract().getId());
       	invoice.setContract(contract);
		this.save(invoice);
		//TODO 告诉财务发送发票,param为连接地址的入参
//		String param = "";
//		List<UserVo>  IpUser = userService.queryIpUserByRole(6l);
//		try{
//			for(UserVo personInChargeId : IpUser){
//				//TODO 审核未完成
//				taskService.startTask(null, createUserId, TaskName.task_get_the_invoice+"_"+invoice.getId(), 
//						contract.getProject().getProjectId(), personInChargeId.getId(), 
//						BusinessConstants.task_get_the_invoice,
//						"发票金额:"+invoice.getAmount(), param,BusinessConstants.invoic_pkid+invoice.getId());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

}
