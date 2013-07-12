package cn.com.kxcomm.contractmanage.status;

/**
 * 
 * 销售订单状态定义
 * 
 * @author lujia 新增日期：2013-3-8
 * @since ContractManage
 */
public class S_ContractOrder {
	/**
	 * 询价中
	 */
	public static final int CONTRACTORDER_ENQUIRY  = 0;
	/**
	 * 待审核
	 */
	public static final int CONTRACTORDER_NAUDITED  = 1;
	/**
	 *审核通过 
	 */
	public static final int CONTRACTORDER_PASSED  = 2;
	/**
	 * 审核不通过
	 */
	public static final int CONTRACTORDER_FAIL  = 3;
	/**
	 * 采购中
	 */
	public static final int CONTRACTORDER_PURCHASE = 4;
	/**
	 * 到货
	 */
	public static final int CONTRACTORDER_ARRIVAL = 5;
	/**
	 * 完成
	 */
	public static final int CONTRACTORDER_FINISH = 6;
	/**
	 * 采购完成
	 */
	public static final int PURCHASE_FINISH = 7;
}
