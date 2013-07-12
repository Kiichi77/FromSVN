package cn.com.kxcomm.contractmanage.status;

/**
 * 
 * 发票状态定义
 * 
 * @author lujia 新增日期：2013-3-8
 * @since ContractManage
 */
public class S_Invoice {
	/**
	 * 未寄送发票
	 */
	public static final int INVOICE_NOT_SEND = 0;
	/**
	 * 已寄送发票
	 */
	public static final int INVOICE_END_SEND  = 1;
	/**
	 * 已收到发票
	 */
	public static final int INVOICE_RECEIVE = 2;
}
