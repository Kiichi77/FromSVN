package cn.com.kxcomm.contractmanage.service.impl;



import cn.com.kxcomm.contractmanage.InitBean;
import cn.com.kxcomm.contractmanage.entity.TbProductCategory;
import cn.com.kxcomm.contractmanage.entity.TbProductType;

/**
 * 
 * 初始化数据库
 * 
 * @author zhangjh 新增日期：2012-12-5
 * @since ContractManage
 */
public class InitDatabaseService {
	private static InitDatabaseService instance = null;
	private  InitBean initBean = InitBean.getInstance();
	private InitDatabaseService(){	}
	
	public InitDatabaseService getInstance(){
		if(instance == null)
			instance = new InitDatabaseService();
		return instance;
	}

	
	public void init(){
		//--------------------------产品总类-----------------------------------//
		int categorySize = initBean.productCategoryDAO.findAll().size();
		if(categorySize>0)
			return ; 
		int typeSize = initBean.productTypeDAO.findAll().size();
		if(typeSize>0)
			return ; 
		
		TbProductCategory category = new TbProductCategory();
		category.setName("基本配置单元");
		int oneCategory = (Integer) initBean.productCategoryDAO.save(category);
		category.setId(oneCategory);
		
		TbProductType type = new TbProductType();
		type.setCategory(category);
		type.setName("服务器");
		initBean.productTypeDAO.save(type);
		type.setName("硬盘框");
		initBean.productTypeDAO.save(type);
		
		category.setName("同系列通用单元");
		int secondCategory = (Integer) initBean.productCategoryDAO.save(category);
		category.setId(secondCategory);
		
		type.setCategory(category);
		type.setName("CPU");
		initBean.productTypeDAO.save(type);
		type.setName("内存");
		initBean.productTypeDAO.save(type);
		type.setName("硬盘");
		initBean.productTypeDAO.save(type);
		type.setName("网卡");
		initBean.productTypeDAO.save(type);
		type.setName("HBA卡");
		initBean.productTypeDAO.save(type);
		type.setName("RAID卡");
		initBean.productTypeDAO.save(type);
		type.setName("iDRAC卡");
		initBean.productTypeDAO.save(type);
		type.setName("电源");
		initBean.productTypeDAO.save(type);
		type.setName("光驱");
		initBean.productTypeDAO.save(type);
		type.setName("其他");
		initBean.productTypeDAO.save(type);
		type.setName("导轨");
		initBean.productTypeDAO.save(type);
		
		category.setName("全通用单元");
		int threeCategory = (Integer) initBean.productCategoryDAO.save(category);
		category.setId(threeCategory);
		type.setCategory(category);
		type.setName("其他");
		initBean.productTypeDAO.save(type);
		
//		TbWarrantyService service = new TbWarrantyService();
//		service.setFirstYear(1);
//		service.setName("默认");
//		service.setSecondYear(1);
//		service.setThirdYear(1);
//		initBean.warrantyServiceDAO.save(service);
	}
}
