package cn.com.kxcomm.contractmanage.status;

/**
 * 
 * 供应商状态
 * 
 * @author lujia 新增日期：2013-3-8
 * @since ContractManage
 */
public class S_Suppliers {
	/**
	 * 待审核
	 */
	public static final int SUPPLIERSCODE_PENDING  = 0;
	
	/**
	 * 审核中
	 */
	public static final int SUPPLIERSCODE_CHECKING = 1;
	
	/**
	 * 审核通过 
	 */
	public static final int SUPPLIERSCODE_PASS  = 2;
	/**
	 * 审核不通过
	 */
	public static final int SUPPLIERSCODE_FAIL  = 3;

}
