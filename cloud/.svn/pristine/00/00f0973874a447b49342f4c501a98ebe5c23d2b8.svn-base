package cn.com.kxcomm.common.util;

import org.apache.log4j.Logger;

import cn.com.kxcomm.contractmanage.InitBean;
import cn.com.kxcomm.contractmanage.entity.TbContractDocType;
import cn.com.kxcomm.contractmanage.entity.TbDataSourceType;
import cn.com.kxcomm.contractmanage.entity.TbJobPlace;
import cn.com.kxcomm.contractmanage.entity.TbJobduties;
import cn.com.kxcomm.contractmanage.entity.TbProductCategory;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbRightRoleId;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbTaskUrl;
import cn.com.kxcomm.contractmanage.entity.TbUser;

/**
 * 初始化系统数据
 * 
 * @author Administrator
 * 
 */
public class InitDatabase {
	private static InitDatabase instance = null;
	private static final Logger log = Logger.getLogger(InitDatabase.class);
	private InitBean initBean = InitBean.getInstance();


	public static InitDatabase getSingleInstance(){
		if(instance==null){
			instance = new InitDatabase();
		}
		return instance;
	}


	public void writeData(){
		String hql="";
		int total=0;
		/**
		 * 职务初始化
		 */
		hql = "select count(*) from TbJobduties tr ";
		total = initBean.productCategoryDAO.findTotalCount(hql, null);
		if(total<=0){
			initDuties();
		}
		
		/**
		 * 工作地点
		 */
		hql = "select count(*) from TbJobPlace tr ";
		total = initBean.productCategoryDAO.findTotalCount(hql, null);
		if(total<=0){
			initJobPlace();
		}
		
		hql = "select count(tr.id) from TbRight tr ";
		total = initBean.rightDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:"+total);
		if(total<=0){
			initRight(true);
		}
		
		/**
		 * 初始角色限数据
		 */
		hql = "select count(*) from TbRole tr ";
		total = initBean.roleDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:" + total);
		if (total <= 0) {
			initRole();
		}

		/**
		 * 初始化用户数据
		 */
		hql = "select count(*) from TbUser tr ";
		total = initBean.userDAO.findTotalCount(hql, null);
		System.out.println("rightDAO:" + total);
		if (total <= 0) {
			initUser(true);
		}
	
		/**
		 * 初始化角色权限关系表
		 */
		hql = "select count(*) from TbRightRole tr ";
		total = initBean.roleDAO.findTotalCount(hql, null);
		log.info("rightDAO:" + total);
		if (total <= 0) {
			initRightRole();
		}
		
		
		hql = "select count(*) from TbContractDocType tr ";
		total = initBean.contractDocTypeDAO.findTotalCount(hql, null);
		if(total<=0){
			TbContractDocType entity = new TbContractDocType();
			entity.setName("默认");
			initBean.contractDocTypeDAO.save(entity);
			entity = new TbContractDocType();
			entity.setName("服务器");
			initBean.contractDocTypeDAO.save(entity);
			entity = new TbContractDocType();
			entity.setName("存储");
			initBean.contractDocTypeDAO.save(entity);
			entity = new TbContractDocType();
			entity.setName("服务器和存储");
			initBean.contractDocTypeDAO.save(entity);
		}
		
		hql = "select count(*) from TbProductCategory tr ";
		total = initBean.productCategoryDAO.findTotalCount(hql, null);
		TbProductCategory category =null;
		if(total<=0){
			category = new TbProductCategory();
			category.setName("基本配置单元");
			category.setIsRepeat(0);
			initBean.productCategoryDAO.save(category);
			category = new TbProductCategory();
			category.setName("同系列通用单元");
			category.setIsRepeat(0);
			initBean.productCategoryDAO.save(category);
			category = new TbProductCategory();
			category.setName("全通用单元");
			category.setIsRepeat(1);
			initBean.productCategoryDAO.save(category);
		}
		
		hql = "select count(*) from TbDataSourceType tr ";
		total = initBean.dataSourceTypeDAO.findTotalCount(hql, null);
		TbDataSourceType tbDataSourceType =null;
		if(total<=0){
			tbDataSourceType = new TbDataSourceType();
			tbDataSourceType.setTypeName("存储");
			initBean.dataSourceTypeDAO.save(tbDataSourceType);
			tbDataSourceType = new TbDataSourceType();
			tbDataSourceType.setTypeName("硬件");
			initBean.dataSourceTypeDAO.save(tbDataSourceType);
			tbDataSourceType = new TbDataSourceType();
			tbDataSourceType.setTypeName("软件");
			initBean.dataSourceTypeDAO.save(tbDataSourceType);
			tbDataSourceType = new TbDataSourceType();
			tbDataSourceType.setTypeName("其他");
			initBean.dataSourceTypeDAO.save(tbDataSourceType);
		}
		
		
		hql = "select count(*) from TbPurchaseCompany tr ";
		total=initBean.purchaseCompanyDAO.findTotalCount(hql, null);
		TbPurchaseCompany purchaseCompany = null;
		if(total<=0){
			purchaseCompany = new TbPurchaseCompany();
			purchaseCompany.setCompanyNo("001");
			purchaseCompany.setCompanyName("北京市康讯通讯设备有限公司");
			purchaseCompany.setAccountName("北京市康讯通讯设备有限公司");
			purchaseCompany.setAccountNumber("137141516010015676");
			purchaseCompany.setAccountOpeningBank("广发银行北京分行方庄支行");
			purchaseCompany.setBankAddress("北京方庄");
			purchaseCompany.setZipCode("100078");
			initBean.purchaseCompanyDAO.save(purchaseCompany);
			purchaseCompany = new TbPurchaseCompany();
			purchaseCompany.setCompanyNo("002");
			purchaseCompany.setCompanyName("北京云上云天信息技术有限公司");
			purchaseCompany.setAccountName("北京市康讯通讯设备有限公司");
			purchaseCompany.setAccountNumber("137141516010015676");
			purchaseCompany.setAccountOpeningBank("广发银行北京分行方庄支行");
			purchaseCompany.setBankAddress("北京方庄");
			purchaseCompany.setZipCode("100078");
			initBean.purchaseCompanyDAO.save(purchaseCompany);
		}
		
		/**
		 * 初始化流程跳转菜单URL
		 */
		hql = "select count(*) from TbTaskUrl tr ";
		total=initBean.taskUrlDAO.findTotalCount(hql, null);
		TbTaskUrl taskUrl=null;
		if(total<=0){
			initTaskUrl();
		}
	}
	
	/**
	 * 
	 * 初始化用户
	 * 
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initUser(boolean isWrite){
		if(!isWrite)
			return ;
		TbUser tbuser = new TbUser();
		tbuser.setAccouont("admin");
		tbuser.setEmail("zhangjianhua@kxcomm.com.cn");
		tbuser.setNote("初始化管理员");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("15817130480");
		TbJobduties jobDuties = new TbJobduties();
		jobDuties.setId(12);
		tbuser.setJobDuties(jobDuties);
		TbJobPlace jobPlace = new TbJobPlace();
		jobPlace.setId(204);
		tbuser.setJobPlace(jobPlace);
		TbRole tbrole=new TbRole();
		tbrole.setId(1l);
		tbuser.setTbRole(tbrole);
		tbuser.setUserName("张建华");
		tbuser.setIsInterface(0);
		initBean.userDAO.save(tbuser);

		tbuser = new TbUser();
		tbuser.setAccouont("chenl");
		tbuser.setEmail("chenliang@kxcomm.com.cn");
		tbuser.setNote("初始化管理员");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("15902021957");
		tbuser.setUserName("陈亮");
		//职务
		jobDuties = new TbJobduties();
		jobDuties.setId(12);
		tbuser.setJobDuties(jobDuties);
		//工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(204);
		tbuser.setJobPlace(jobPlace);
		//角色
		tbrole=new TbRole();
		tbrole.setId(1l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(0);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("zhaoqi");
		tbuser.setEmail("zhaoqi@kxcomm.com.cn");
		tbuser.setNote("初始化经理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18611105777/13581823810");
		tbuser.setUserName("赵琪");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(1);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(101);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(3l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(1);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("wangshan");
		tbuser.setEmail("wangshan@kxcomm.com.cn");
		tbuser.setNote("初始化管理助理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18611757373");
		tbuser.setUserName("王珊");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(2);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(101);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(6l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(1);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("sunlei");
		tbuser.setEmail("sunlei@kxcomm.com.cn");
		tbuser.setNote("初始化技术总监");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("13311080093");
		tbuser.setUserName("孙磊");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(3);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(101);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(7l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(1);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("liyingying");
		tbuser.setEmail("liyingying@kxcomm.com.cn");
		tbuser.setNote("初始化采购主管");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("13911506571");
		tbuser.setUserName("李莹莹");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(4);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(101);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(5l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(1);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("yangliu");
		tbuser.setEmail("yangliu@kxcomm.com.cn");
		tbuser.setNote("初始化采购助理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18810841868");
		tbuser.setUserName("杨柳");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(9);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(101);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(5l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(0);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("lijing");
		tbuser.setEmail("lijing@kxcomm.com.cn");
		tbuser.setNote("初始化采购助理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18688909299");
		tbuser.setUserName("李静");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(11);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(204);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(4l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(1);
		initBean.userDAO.save(tbuser);
		
		tbuser = new TbUser();
		tbuser.setAccouont("chenmengting");
		tbuser.setEmail("chenmengting@kxcomm.com.cn");
		tbuser.setNote("初始化销售助理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18688420094");
		tbuser.setUserName("陈梦婷");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(10);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(204);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(4l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(0);
		initBean.userDAO.save(tbuser);

		tbuser = new TbUser();
		tbuser.setAccouont("qiliangchuan");
		tbuser.setEmail("qiliangchuan@kxcomm.com.cn");
		tbuser.setNote("初始化销售助理");
		tbuser.setPassword(MD5.toMD5("123"));
		tbuser.setPhoneNumber("18631110925");
		tbuser.setUserName("齐良川");
		// 职务
		jobDuties = new TbJobduties();
		jobDuties.setId(8);
		tbuser.setJobDuties(jobDuties);
		// 工作地点
		jobPlace = new TbJobPlace();
		jobPlace.setId(217);
		tbuser.setJobPlace(jobPlace);
		// 角色
		tbrole = new TbRole();
		tbrole.setId(7l);
		tbuser.setTbRole(tbrole);
		tbuser.setIsInterface(0);
		initBean.userDAO.save(tbuser);
		log.info("初始化TbUser数据");
	}
	
	/**
	 * 
	 * 初始化权限
	 * 
	 * @param isWrite
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initRight(boolean isWrite){
		if(!isWrite)
			return ;
		//**********************第一级菜单*********************  
		//=======================1、销售管理======================
		TbRight tbRight = new TbRight();
		tbRight.setId(1);
		tbRight.setRightName("销售管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);
		//=======================2、采购管理======================
		tbRight = new TbRight();
		tbRight.setId(2);
		tbRight.setRightName("采购管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);
		//=======================3、售后管理======================
		tbRight = new TbRight();
		tbRight.setId(3);
		tbRight.setRightName("售后管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);
		//=======================4、财务管理======================
		tbRight = new TbRight();
		tbRight.setId(4);
		tbRight.setRightName("财务管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);
		//=======================5、审核管理======================
		tbRight = new TbRight();
		tbRight.setId(5);
		tbRight.setRightName("审核管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);
		//=======================6、系统管理======================
		tbRight = new TbRight();
		tbRight.setId(6);
		tbRight.setRightName("系统管理");
		tbRight.setRightLevel(1);
		initBean.rightDAO.save(tbRight);

		
		//**********************第二级菜单*********************  
		//========1、销售管理========
		tbRight = new TbRight();
		tbRight.setId(10);
		tbRight.setRightName("我的项目");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/project/projectManager.jsp");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(12);
		tbRight.setRightName("客户管理");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/client/client_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(13);
		tbRight.setRightName("我的机型配置");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/config/config.jsp");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(14);
		tbRight.setRightName("我的报价表");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/rapaport/rapaport.jsp");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(15);
		tbRight.setRightName("我的合同");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/contract/createcontract/createcontract.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(16);
		tbRight.setRightName("我的销售订单");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/salesOrderManager/salesOrderManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(17);
		tbRight.setRightName("我的销项发票");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/invoiceApplication/invoiceApplication.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(18);
		tbRight.setRightName("我的合同收款");
		tbRight.setParid(1);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/salesReceivables/salesReceivables.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========2、采购管理========
		tbRight = new TbRight();
		tbRight.setId(20);
		tbRight.setRightName("供应商管理");
		tbRight.setParid(2);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/supplier/supplier_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(21);
		tbRight.setRightName("询价单");
		tbRight.setParid(2);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/InquiryOrder/inquiryOrder.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(22);
		tbRight.setRightName("采购订单");
		tbRight.setParid(2);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/purchaseOrder/orders.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(23);
		tbRight.setRightName("采购合同管理");
		tbRight.setParid(2);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/purchaseContract/purchaseContract.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(24);
		tbRight.setRightName("供应商报价单");
		tbRight.setParid(2);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/suppliersQuotations/supplier_quotations_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========3、售后管理========
		tbRight = new TbRight();
		tbRight.setId(30);
		tbRight.setRightName("收货列表");
		tbRight.setParid(3);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/aftersale/aftersale_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(31);
		tbRight.setRightName("初验/终验");
		tbRight.setParid(3);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/aftersale/accept.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========4、财务管理========
		tbRight = new TbRight();
		tbRight.setId(40);
		tbRight.setRightName("采购合同付款");
		tbRight.setParid(4);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/finance/payment.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(41);
		tbRight.setRightName("客户合同收款");
		tbRight.setParid(4);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/financialProceedsReceived/financialProceedsReceived.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(42);
		tbRight.setRightName("客户销项发票");
		tbRight.setParid(4);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/invoiceManager/invoiceManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(43);
		tbRight.setRightName("采购进项发票");
		tbRight.setParid(4);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/purchaseContract/purchaseContractInfo.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========5、审核管理========
		tbRight = new TbRight();
		tbRight.setId(50);
		tbRight.setRightName("审核报价表");
		tbRight.setParid(5);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/check/checkRapaport_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
//		tbRight = new TbRight();
//		tbRight.setId(51);
//		tbRight.setRightName("审核发票");
//		tbRight.setParid(5);
//		tbRight.setRightLevel(2);
//		tbRight.setUrl("/auditInvoices/auditInvoices.jsp");
//		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(52);
		tbRight.setRightName("审核询价单");
		tbRight.setParid(5);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/InquiryOrder/orderChecked.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(53);
		tbRight.setRightName("审核合同模板");
		tbRight.setParid(5);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/templateCheck/templateCheck.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(54);
		tbRight.setRightName("审核合同");
		tbRight.setParid(5);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/checkContract/checkContract_manage.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(55);
		tbRight.setRightName("审核供应商");
		tbRight.setParid(5);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/checkSuppliers/checkSuppliers.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========6、系统管理========
		tbRight = new TbRight();
		tbRight.setId(60);
		tbRight.setRightName("可选配件源数据");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/quotation/datasource/uploaddata.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(61);
		tbRight.setRightName("合同模版管理");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/contract/contract.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(62);
		tbRight.setRightName("账户管理");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/systemUser/systemUser.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(63);
		tbRight.setRightName("角色管理");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/roleManager/roleManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(64);
		tbRight.setRightName("日志记录");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/logmanager/logManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(65);
		tbRight.setRightName("合同文件管理");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/contractFileManager/contractFileManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(66);
		tbRight.setRightName("权限管理");
		tbRight.setParid(6);
		tbRight.setRightLevel(2);
		tbRight.setUrl("/rightManager/rightManager.jsp");
		initBean.rightDAO.save(tbRight);
		
		//**********************第三级菜单权限配置*********************  
		//========1、========
		//*******1.0、项目管理*******
		tbRight = new TbRight();
		tbRight.setId(100);
		tbRight.setRightName("action");
		tbRight.setParid(10);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/projectManage1/projectManage1/project1");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(101);
		tbRight.setRightName("action");
		tbRight.setParid(10);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/customer111");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(102);
		tbRight.setRightName("action");
		tbRight.setParid(10);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(103);
		tbRight.setRightName("action");
		tbRight.setParid(10);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/project/projectProcess.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(104);
		tbRight.setRightName("action");
		tbRight.setParid(10);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/activiti/activiti/activiti");
		initBean.rightDAO.save(tbRight);
		
		//*******1.2、客户管理*******
		tbRight = new TbRight();
		tbRight.setId(120);
		tbRight.setRightName("action");
		tbRight.setParid(12);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/customer111");
		initBean.rightDAO.save(tbRight);
		
		//*******1.3、机型配置管理*******
		tbRight = new TbRight();
		tbRight.setId(130);
		tbRight.setRightName("page");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotation/config/optionaltype/optional.jsp");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(131);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/configModel/configModel/configModel");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(132);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataSourceType");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(133);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataSource");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(134);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/quotations");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(135);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/projectManage1/projectManage1/project1");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(136);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(137);
		tbRight.setRightName("action");
		tbRight.setParid(13);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataRecord");
		initBean.rightDAO.save(tbRight);
		
		//*******1.4、报价表*******
		tbRight = new TbRight();
		tbRight.setId(140);
		tbRight.setRightName("action");
		tbRight.setParid(14);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/quotations");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(141);
		tbRight.setRightName("downPage");
		tbRight.setParid(14);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/jquery-easyui-1.3.1/downLoadFile.jsp");
		initBean.rightDAO.save(tbRight);
		
		//*******1.5、生成合同******
		tbRight = new TbRight();
		tbRight.setId(150);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contract");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(151);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplates");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(152);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/contractQuotations");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(153);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/quotations");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(154);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contract/createcontract/file.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(155);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/variable/variable/contractDocVariable");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(156);
		tbRight.setRightName("downPage");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/jquery-easyui-1.3.1/downLoadFile.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(157);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(158);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/purchaseCompany");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(159);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/projectManage1/projectManage1/project1");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(1510);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(1511);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractPayment");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(1512);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/variable/variable/templatesDocVariable");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(1513);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractDoc");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(1514);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contract/createcontract/finish.jsp");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(1515);
		tbRight.setRightName("action");
		tbRight.setParid(15);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contract/createcontract/realcreatecontract.jsp");
		initBean.rightDAO.save(tbRight);
		
		//*******1.6销售订单管理*******
		tbRight = new TbRight();
		tbRight.setId(160);
		tbRight.setRightName("action");
		tbRight.setParid(16);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);
		
		//*******1.7 发票申请*******
		tbRight = new TbRight();
		tbRight.setId(170);
		tbRight.setRightName("action");
		tbRight.setParid(17);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/invoice/invoice/invoice");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(171);
		tbRight.setRightName("action");
		tbRight.setParid(17);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/customer111");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(172);
		tbRight.setRightName("action");
		tbRight.setParid(17);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contract");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(173);
		tbRight.setRightName("action");
		tbRight.setParid(17);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/purchaseCompany");
		initBean.rightDAO.save(tbRight);
		
		//*******1.8 合同收款*******
		tbRight = new TbRight();
		tbRight.setId(180);
		tbRight.setRightName("action");
		tbRight.setParid(18);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractPayment");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(181);
		tbRight.setRightName("action");
		tbRight.setParid(18);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		//========2、========
		//********2.0供应商管理*******
		tbRight = new TbRight();
		tbRight.setId(200);
		tbRight.setRightName("action");
		tbRight.setParid(20);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/suppliers/suppliers/suppliers");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(201);
		tbRight.setRightName("action");
		tbRight.setParid(20);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/customer111");
		initBean.rightDAO.save(tbRight);

		//********2.1询价单*******
		tbRight = new TbRight();
		tbRight.setId(210);
		tbRight.setRightName("询价");
		tbRight.setParid(21);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/InquiryOrder/addInquiryOrder.jsp");
		initBean.rightDAO.save(tbRight);	
		
//		tbRight = new TbRight();
//		tbRight.setId(211);
//		tbRight.setRightName("采购");
//		tbRight.setParid(21);
//		tbRight.setRightLevel(3);
//		tbRight.setUrl("/purchaseOrder/addOrders.jsp");
//		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(212);
		tbRight.setRightName("action");
		tbRight.setParid(21);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(213);
		tbRight.setRightName("action");
		tbRight.setParid(21);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/suppliers/suppliers/suppliers");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(214);
		tbRight.setRightName("action");
		tbRight.setParid(21);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/configModel/configModel/configModel");
		initBean.rightDAO.save(tbRight);
		
		//********2.2采购订单*******
		tbRight = new TbRight();
		tbRight.setId(220);
		tbRight.setRightName("查看订单详情");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseOrder/ordersDetail.jsp");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(221);
		tbRight.setRightName("添加");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseOrder/addOrders.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(222);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchasecontract/purchasecontract/purchasecontract");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(223);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataSource");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(224);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);

		tbRight = new TbRight();
		tbRight.setId(225);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/suppliers/suppliers/suppliers");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(226);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(227);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseorder/purchaseorder/purchaseorder");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(228);
		tbRight.setRightName("action");
		tbRight.setParid(22);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseorder/purchaseorder/relationship");
		initBean.rightDAO.save(tbRight);
		
		//********2.3采购合同管理*******
		tbRight = new TbRight();
		tbRight.setId(230);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchasecontract/purchasecontract/purchasecontract");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(231);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contract");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(232);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/suppliers/suppliers/suppliers");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(233);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/purchaseCompany");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(234);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(235);
		tbRight.setRightName("action");
		tbRight.setParid(23);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseContract/purchaseContractProcess.jsp");
		initBean.rightDAO.save(tbRight);
		
		//========3、========
		//********3.0 收货列表*******
		tbRight = new TbRight();
		tbRight.setId(300);
		tbRight.setRightName("action");
		tbRight.setParid(30);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseorder/purchaseorder/purchaseorder");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(301);
		tbRight.setRightName("查看订单");
		tbRight.setParid(30);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/aftersale/aftersale_detail_manage.jsp");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(302);
		tbRight.setRightName("action");
		tbRight.setParid(30);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchaseorder/purchaseorder/relationship");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(303);
		tbRight.setRightName("action");
		tbRight.setParid(30);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);	
		
		//********3.1 初验/终验*******
		tbRight = new TbRight();
		tbRight.setId(310);
		tbRight.setRightName("action");
		tbRight.setParid(31);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/arrivalProved");
		initBean.rightDAO.save(tbRight);	
		
		//========4、========
		//********4.0 采购付款项*******
		tbRight = new TbRight();
		tbRight.setId(400);
		tbRight.setRightName("action");
		tbRight.setParid(40);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchasecontract/purchasecontract/purchasecontract");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(401);
		tbRight.setRightName("付款");
		tbRight.setParid(40);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/finance/payment_detail.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(402);
		tbRight.setRightName("action");
		tbRight.setParid(40);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/purchasecontract/purchasecontract/purchaseOrderContractBill");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(403);
		tbRight.setRightName("action");
		tbRight.setParid(40);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/customer111/customer111/purchaseCompany");
		initBean.rightDAO.save(tbRight);
		
		//********4.1 销售收款项*******
		tbRight = new TbRight();
		tbRight.setId(410);
		tbRight.setRightName("action");
		tbRight.setParid(41);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractPayment");
		initBean.rightDAO.save(tbRight);
		
		//********4.2 发票管理*******
		tbRight = new TbRight();
		tbRight.setId(420);
		tbRight.setRightName("action");
		tbRight.setParid(42);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/invoice/invoice/invoice");
		initBean.rightDAO.save(tbRight);
		
		//========5、========
		//********5.0审核报价表*******
		tbRight = new TbRight();
		tbRight.setId(500);
		tbRight.setRightName("action");
		tbRight.setParid(50);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/quotations");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(501);
		tbRight.setRightName("action");
		tbRight.setParid(50);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/check/checkDetails.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(502);
		tbRight.setRightName("action");
		tbRight.setParid(50);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplates");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(503);
		tbRight.setRightName("action");
		tbRight.setParid(50);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(504);
		tbRight.setRightName("action");
		tbRight.setParid(50);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/activiti/activiti/activiti");
		initBean.rightDAO.save(tbRight);
		
		//********5.1审核发票*******
		tbRight = new TbRight();
		tbRight.setId(510);
		tbRight.setRightName("action");
		tbRight.setParid(51);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/invoice/invoice/invoice");
		initBean.rightDAO.save(tbRight);
		
		//********5.2审核订单*******
		tbRight = new TbRight();
		tbRight.setId(520);
		tbRight.setRightName("action");
		tbRight.setParid(52);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/order/order/order");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(521);
		tbRight.setRightName("action");
		tbRight.setParid(52);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/InquiryOrder/orderCheckedDetail.jsp");
		initBean.rightDAO.save(tbRight);
		
		//********5.3审核合同模板*******
		tbRight = new TbRight();
		tbRight.setId(531);
		tbRight.setRightName("action");
		tbRight.setParid(53);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplatesDoc");
		initBean.rightDAO.save(tbRight);  
		
		tbRight = new TbRight();
		tbRight.setId(532);
		tbRight.setRightName("action");
		tbRight.setParid(53);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(533);
		tbRight.setRightName("action");
		tbRight.setParid(53);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/activiti/activiti/activiti");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(534);
		tbRight.setRightName("action");
		tbRight.setParid(53);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/templateCheck/templateDoc.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(535);
		tbRight.setRightName("action");
		tbRight.setParid(53);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplates");
		initBean.rightDAO.save(tbRight);
		
		//========6、系统管理========
		//********6.0可选配件数据源*******
		tbRight = new TbRight();
		tbRight.setId(600);
		tbRight.setRightName("action");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataSource");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(601);
		tbRight.setRightName("action");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataSourceType");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(602);
		tbRight.setRightName("action");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/dataSource/dataSource/dataRecord");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(603);
		tbRight.setRightName("查看详情");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotation/datasource/data_details.jsp");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(604);
		tbRight.setRightName("page");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotation/datasource/standardConfiguration.jsp");
		initBean.rightDAO.save(tbRight);	
		
		tbRight = new TbRight();
		tbRight.setId(605);
		tbRight.setRightName("action");
		tbRight.setParid(60);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/quotations/quotations/quotations");
		initBean.rightDAO.save(tbRight);	
		
		//*******6.1、合同模板管理*******
		tbRight = new TbRight();
		tbRight.setId(610);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplates");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(611);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplatesDoc");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(612);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/variable/variable/templatesDocVariable");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(613);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractDocType");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(614);
		tbRight.setRightName("查看详情");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contract/addcontract.jsp");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(615);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(616);
		tbRight.setRightName("action");
		tbRight.setParid(61);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/activiti/activiti/activiti");
		initBean.rightDAO.save(tbRight);
		
		//*******6.2、账户管理*******
		tbRight = new TbRight();
		tbRight.setId(620);
		tbRight.setRightName("action");
		tbRight.setParid(62);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/user/user/user");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(621);
		tbRight.setRightName("action");
		tbRight.setParid(62);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/role/role/role");
		initBean.rightDAO.save(tbRight);
		
		//*******6.3、角色管理*******
		tbRight = new TbRight();
		tbRight.setId(630);
		tbRight.setRightName("action");
		tbRight.setParid(63);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/role/role/role");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(631);
		tbRight.setRightName("action");
		tbRight.setParid(63);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/right/right/right");
		initBean.rightDAO.save(tbRight);
		
		//*******6.5、合同文件管理*******
		tbRight = new TbRight();
		tbRight.setId(650);
		tbRight.setRightName("action");
		tbRight.setParid(65);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractTemplatesDoc");
		initBean.rightDAO.save(tbRight);
	
		tbRight = new TbRight();
		tbRight.setId(651);
		tbRight.setRightName("action");
		tbRight.setParid(65);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/variable/variable/templatesDocVariable");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(652);
		tbRight.setRightName("action");
		tbRight.setParid(65);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/contractTemplates/contractTemplates/contractDocType");
		initBean.rightDAO.save(tbRight);
		
		//*******6.6、权限管理*******
		tbRight = new TbRight();
		tbRight.setId(660);
		tbRight.setRightName("action");
		tbRight.setParid(66);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/role/role/role");
		initBean.rightDAO.save(tbRight);
		
		tbRight = new TbRight();
		tbRight.setId(661);
		tbRight.setRightName("action");
		tbRight.setParid(66);
		tbRight.setRightLevel(3);
		tbRight.setUrl("/right/right/right");
		initBean.rightDAO.save(tbRight);
	}
	
	/**
	 * 
	 * 初始化角色
	 * 
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initRole(){

		TbRole tbrole = new TbRole();
		tbrole.setRolename("系统管理员");
		tbrole.setNote("初始化管理员");
		tbrole.setId(1l);
		initBean.roleDAO.save(tbrole);
		log.info("初始化TbRole数据");
		
		tbrole = new TbRole();
		tbrole.setRolename("普通用户");
		tbrole.setNote("初始化普通用户");
		tbrole.setId(2l);
		initBean.roleDAO.save(tbrole);
		
		tbrole = new TbRole();
		tbrole.setRolename("经理");
		tbrole.setNote("初始化经理角色");
		tbrole.setId(3l);
		initBean.roleDAO.save(tbrole);
		
		tbrole = new TbRole();
		tbrole.setRolename("销售");
		tbrole.setNote("初始化销售角色");
		tbrole.setId(4l);
		initBean.roleDAO.save(tbrole);
		
		tbrole = new TbRole();
		tbrole.setRolename("采购");
		tbrole.setNote("初始化采购角色");
		tbrole.setId(5l);
		initBean.roleDAO.save(tbrole);
		
		tbrole = new TbRole();
		tbrole.setRolename("财务");
		tbrole.setId(6l);
		tbrole.setNote("初始化财务角色");
		initBean.roleDAO.save(tbrole);
		
		tbrole = new TbRole();
		tbrole.setRolename("售后");
		tbrole.setNote("初始化售后角色");
		tbrole.setId(7l);
		initBean.roleDAO.save(tbrole);
		log.info("初始化TbRole数据");
	
	}
	
	/**
	 * 
	 * 初始化角色权限
	 * 
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initRightRole(){

		//管理员-角色-配置所有权限
		//+++++++++++第一级菜单赋权限+++++++++++++++++++++
		TbRightRoleId tbRightRoleId=null;
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(1l);
		tbRightRoleId.setRoleId(1l);
		TbRightRole tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);

		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(2l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);

		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(3l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(4l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);	
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(5l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(6l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//++++++++++++第二级赋权限+++++++++++++++
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(10l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(11l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(12l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(13l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(14l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(15l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(16l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(17l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(18l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(20l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(21l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(22l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(23l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
				
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(30l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(31l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
				
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(40l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(41l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(42l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(50l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(51l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(52l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(53l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(54l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//--------------------------------------------------------//
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(60l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(61l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(62l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
				
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(63l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(64l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(65l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(66l);
		tbRightRoleId.setRoleId(1l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//初始化普通用户-角色-系统管理权限配置
		//系统管理第一级菜单权限
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(6l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		//系统管理第二级菜单权限
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(60l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(61l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(62l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(63l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(64l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(65l);
		tbRightRoleId.setRoleId(2l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//经理-角色-审核管理权限配置
		//审核管理第一级菜单权限配置
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(5l);
		tbRightRoleId.setRoleId(3l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//审核管理第二级菜单权限配置
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(50l);
		tbRightRoleId.setRoleId(3l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(51l);
		tbRightRoleId.setRoleId(3l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(52l);
		tbRightRoleId.setRoleId(3l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(53l);
		tbRightRoleId.setRoleId(3l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//销售-角色配置权限
		//销售管理配置第一级菜单权限
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(1l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//销售管理配置第二级菜单权限
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(10l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(11l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(12l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(13l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(14l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(15l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(16l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(17l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(18l);
		tbRightRoleId.setRoleId(4l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//采购-角色配置权限
		//采购管理配置第一级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(2l);
		tbRightRoleId.setRoleId(5l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//采购管理配置第二级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(20l);
		tbRightRoleId.setRoleId(5l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(21l);
		tbRightRoleId.setRoleId(5l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(22l);
		tbRightRoleId.setRoleId(5l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(23l);
		tbRightRoleId.setRoleId(5l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//财务-角色配置权限
		//财务管理配置第一级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(4l);
		tbRightRoleId.setRoleId(6l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//财务管理配置第二级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(40l);
		tbRightRoleId.setRoleId(6l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(41l);
		tbRightRoleId.setRoleId(6l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(42l);
		tbRightRoleId.setRoleId(6l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//售后-角色配置权限
		//售后管理配置第一级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(3l);
		tbRightRoleId.setRoleId(7l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		//售后管理配置第二级菜单
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(30l);
		tbRightRoleId.setRoleId(7l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
		tbRightRoleId=new TbRightRoleId();
		tbRightRoleId.setRightId(31l);
		tbRightRoleId.setRoleId(7l);
		tbRightRole = new TbRightRole();
		tbRightRole.setId(tbRightRoleId);
		initBean.rightRoleDAO.save(tbRightRole);
		
	
	}
	
	/**
	 * 
	 * 初始化任务地址
	 * 
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initTaskUrl(){

		TbTaskUrl taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(4l);
		taskUrl.setUrl("contract/contract.jsp?processid=");
		taskUrl.setUserName("createMode");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(6l);
		taskUrl.setUrl("templateCheck/templateCheck.jsp?processid=");
		taskUrl.setUserName("saleAudit");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(3l);
		taskUrl.setUrl("templateCheck/templateCheck.jsp?processid=");
		taskUrl.setUserName("financialAudit");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(null);
		taskUrl.setUrl("templateCheck/templateCheck.jsp?processid=");
		taskUrl.setUserName("managerAudit");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(4l);
		taskUrl.setUrl("project/projectManager.jsp?processid=");
		taskUrl.setUserName("processstart");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(3l);
		taskUrl.setUrl("config/config.jsp?processid=");
		taskUrl.setUserName("saleGenOffer");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(4l);
		taskUrl.setUrl("check/checkRapaport_manage.jsp?processid=");
		taskUrl.setUserName("managerApprovalOffer");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(5l);
		taskUrl.setUrl("/contract/createcontract/createcontract.jsp?processid=");
		taskUrl.setUserName("saleGenOrder");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(null);
		taskUrl.setUrl("contract/createcontract/realcreatecontract.jsp");
		taskUrl.setUserName("saleGenContract");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(3l);
		taskUrl.setUrl("InquiryOrder/inquiryOrderProcess.jsp");
		taskUrl.setUserName("purchasingFillInquirySheet");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(5l);
		taskUrl.setUrl("InquiryOrder/orderChecked.jsp");
		taskUrl.setUserName("managerApprovalFillInquirySheet");
		initBean.taskUrlDAO.save(taskUrl);

		taskUrl = new TbTaskUrl();
		taskUrl.setRole_id(6l);
		taskUrl.setUrl("purchaseContract/purchaseContractProcess.jsp");
		taskUrl.setUserName("purchasingContract");
		initBean.taskUrlDAO.save(taskUrl);

	}

	/**
	 * 
	 * 初始化职务
	 * 
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initDuties(){

		TbJobduties jobduties =new TbJobduties();
		jobduties.setId(1);
		jobduties.setJobDutiesrName("经理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(2);
		jobduties.setJobDutiesrName("管理助理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(3);
		jobduties.setJobDutiesrName("技术总监");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(4);
		jobduties.setJobDutiesrName("采购主管");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(5);
		jobduties.setJobDutiesrName("商务助理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(6);
		jobduties.setJobDutiesrName("客户经理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(7);
		jobduties.setJobDutiesrName("工程师");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(8);
		jobduties.setJobDutiesrName("系统工程师");
		initBean.jobdutiesDAO.save(jobduties);
	
		
		jobduties =new TbJobduties();
		jobduties.setId(9);
		jobduties.setJobDutiesrName("采购助理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(10);
		jobduties.setJobDutiesrName("销售助理");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(11);
		jobduties.setJobDutiesrName("销售总监");
		initBean.jobdutiesDAO.save(jobduties);
		
		jobduties =new TbJobduties();
		jobduties.setId(12);
		jobduties.setJobDutiesrName("开发工程师");
		initBean.jobdutiesDAO.save(jobduties);
	
	}

	/**
	 * 
	 * 初始化工作地点
	 * 四市(101-104)、廿三省（201-223）、五区（301-305），及两个特别行政区（401-402）
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	private void initJobPlace(){
		//四市
		TbJobPlace place = new TbJobPlace();
		place.setId(101);
		place.setJobPlaceName("北京市");
		Integer aa = (Integer) this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(102);
		place.setJobPlaceName("天津市");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(103);
		place.setJobPlaceName("上海市");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(104);
		place.setJobPlaceName("重庆市");
		this.initBean.jobPlaceDAO.save(place);
		//二十三省
		place = new TbJobPlace();
		place.setId(201);
		place.setJobPlaceName("黑龙江省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(202);
		place.setJobPlaceName("吉林省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(203);
		place.setJobPlaceName("山东省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(204);
		place.setJobPlaceName("广东省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(205);
		place.setJobPlaceName("江西省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(206);
		place.setJobPlaceName("河南省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(207);
		place.setJobPlaceName("海南省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(208);
		place.setJobPlaceName("湖南省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(209);
		place.setJobPlaceName("福建省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(210);
		place.setJobPlaceName("贵州省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(211);
		place.setJobPlaceName("辽宁省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(212);
		place.setJobPlaceName("台湾省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(213);
		place.setJobPlaceName("云南省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(214);
		place.setJobPlaceName("安徽省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(215);
		place.setJobPlaceName("山西省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(216);
		place.setJobPlaceName("江苏省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(217);
		place.setJobPlaceName("河北省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(218);
		place.setJobPlaceName("浙江省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(219);
		place.setJobPlaceName("湖北省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(220);
		place.setJobPlaceName("甘肃省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(221);
		place.setJobPlaceName("四川省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(222);
		place.setJobPlaceName("青海省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(223);
		place.setJobPlaceName("陕西省");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(401);
		place.setJobPlaceName("香港");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(402);
		place.setJobPlaceName("澳门");
		this.initBean.jobPlaceDAO.save(place);
		//五区
		place = new TbJobPlace();
		place.setId(301);
		place.setJobPlaceName("内蒙古自治区");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(302);
		place.setJobPlaceName("广西壮族自治区");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(303);
		place.setJobPlaceName("西藏自治区");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(304);
		place.setJobPlaceName("宁夏回族自治区");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(305);
		place.setJobPlaceName("新疆维吾尔族自治区");
		this.initBean.jobPlaceDAO.save(place);
		place = new TbJobPlace();
		place.setId(600);
		place.setJobPlaceName("未知");
		this.initBean.jobPlaceDAO.save(place);
	}
	public static void main(String args[]){
		InitDatabase aa = InitDatabase.getSingleInstance();
		aa.writeData();
	}
}
