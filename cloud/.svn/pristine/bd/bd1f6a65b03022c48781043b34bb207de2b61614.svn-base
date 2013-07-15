package cn.com.kxcomm.contractmanage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.kxcomm.contractmanage.dao.ContractDocTypeDAO;
import cn.com.kxcomm.contractmanage.dao.DataRecordDAO;
import cn.com.kxcomm.contractmanage.dao.DataSourceDAO;
import cn.com.kxcomm.contractmanage.dao.DataSourceTypeDAO;
import cn.com.kxcomm.contractmanage.dao.JobPlaceDAO;
import cn.com.kxcomm.contractmanage.dao.JobdutiesDAO;
import cn.com.kxcomm.contractmanage.dao.ProductCategoryDAO;
import cn.com.kxcomm.contractmanage.dao.ProductTypeDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseCompanyDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.dao.RightDAO;
import cn.com.kxcomm.contractmanage.dao.RightRoleDAO;
import cn.com.kxcomm.contractmanage.dao.RoleDAO;
import cn.com.kxcomm.contractmanage.dao.TaskUrlDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.service.impl.ConfigModelsService;
import cn.com.kxcomm.contractmanage.service.impl.ContractDocService;
import cn.com.kxcomm.contractmanage.service.impl.ContractDocVariableService;
import cn.com.kxcomm.contractmanage.service.impl.ContractQuotationsService;
import cn.com.kxcomm.contractmanage.service.impl.ContractService;
import cn.com.kxcomm.contractmanage.service.impl.ContractTemplatesDocService;
import cn.com.kxcomm.contractmanage.service.impl.PurchaseOrderContractFileService;


public class InitBean {
	private static InitBean instance = null;
	private ApplicationContext ctx;
	public DataSourceDAO dataSourceDAO = null;
	public ProductCategoryDAO productCategoryDAO = null;
	public ProductTypeDAO productTypeDAO = null;
	public RightDAO rightDAO = null;
	public RoleDAO roleDAO  = null;
	public UserDAO userDAO  = null;
	public RightRoleDAO rightRoleDAO  = null;
	public ContractDocTypeDAO contractDocTypeDAO = null;
	public DataRecordDAO dataRecordDAO = null;
	public QuotationsDAO quotationsDAO = null;
	public QuotationsDataDAO quotationsDataDAO = null;
	public ConfigModelsService configModelsService;
	public ContractDocService contractDocService;
	public ContractService contractService;
	public ContractTemplatesDocService contractTemplatesDocService;
	public ContractDocVariableService contractDocVariableService;
	public ContractQuotationsService icontractQuotationsService;
	public DataSourceTypeDAO dataSourceTypeDAO = null;
	public TaskUrlDAO taskUrlDAO;
	public PurchaseCompanyDAO purchaseCompanyDAO;
	public PurchaseOrderContractFileService purchaseOrderContractFileService;
	public JobPlaceDAO jobPlaceDAO;
	public JobdutiesDAO jobdutiesDAO;
	
	private InitBean(){	
		ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		dataSourceDAO = (DataSourceDAO) ctx.getBean("dataSourceDAO");
		productCategoryDAO = (ProductCategoryDAO)ctx.getBean("productCategoryDAO");
		dataSourceTypeDAO = (DataSourceTypeDAO)ctx.getBean("dataSourceTypeDAO");
		productTypeDAO = (ProductTypeDAO)ctx.getBean("productTypeDAO");
		rightDAO = (RightDAO) ctx.getBean("rightDAO");
		roleDAO = (RoleDAO) ctx.getBean("roleDAO");
		userDAO = (UserDAO) ctx.getBean("userDAO");
		rightRoleDAO = (RightRoleDAO) ctx.getBean("rightRoleDAO");
		contractDocTypeDAO = (ContractDocTypeDAO) ctx.getBean("contractDocTypeDAO");
		dataRecordDAO = (DataRecordDAO) ctx.getBean("dataRecordDAO");
		quotationsDAO = (QuotationsDAO) ctx.getBean("quotationsDAO");
		quotationsDataDAO = (QuotationsDataDAO) ctx.getBean("quotationsDataDAO");
		configModelsService = (ConfigModelsService) ctx.getBean("configModelsService");
		contractDocService = (ContractDocService) ctx.getBean("contractDocService");
		contractService = (ContractService) ctx.getBean("contractService");
		contractTemplatesDocService = (ContractTemplatesDocService) ctx.getBean("contractTemplatesDocService");
		contractDocVariableService = (ContractDocVariableService) ctx.getBean("contractDocVariableService");
		icontractQuotationsService = (ContractQuotationsService) ctx.getBean("icontractQuotationsService");
		taskUrlDAO = (TaskUrlDAO) ctx.getBean("taskUrlDAO");
		purchaseCompanyDAO = (PurchaseCompanyDAO) ctx.getBean("purchaseCompanyDAO");
		purchaseOrderContractFileService = (PurchaseOrderContractFileService) ctx.getBean("purchaseOrderContractFileService");
		jobPlaceDAO = (JobPlaceDAO) ctx.getBean("jobPlaceDAO");
		jobdutiesDAO = (JobdutiesDAO) ctx.getBean("jobdutiesDAO");
	}
	
	public static InitBean getInstance(){
		if(instance == null)
			instance = new InitBean();
		return instance;
	}
}
