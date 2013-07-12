package cn.com.kxcomm.contractmanage.status;

/**
 * 
 * 合同模版状态定义
 * 
 * @author lujia 新增日期：2013-3-8
 * @since ContractManage
 */
public class S_ContractTemplates {
	/**
	 * 无效
	 */
	public static final int TMPLATES_VAINU  = 0;
	/**
	 * 有效
	 */
	public static final int TMPLATES_VALID  = 1;
	/**
	 * 待审核
	 */
	public static final int TMPLATES_NAUDITED  = 2;
	/**
	 *审核通过 
	 */
	public static final int TMPLATES_PASSED  = 3;
	/**
	 * 审核不通过
	 */
	public static final int TMPLATES_FAIL  = 4;
}
