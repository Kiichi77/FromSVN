package cn.com.kxcomm.base.constants;

import java.util.HashMap;

/**
 * 
* 功能描述:定义业务常量
* <p>版权所有：北京康讯通讯
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author libu 新增日期：2010-6-9
* @author 你的姓名 修改日期：2010-6-9
* @since ipmi_web
 */
public class BusinessConstants {
	
	//项目编码
	public static final String PROJECT_ENCODE = "UTF-8";
	//配置文件变量名
	public static final String totleMoneyNum = "totleMoneyNum"; //#总价格小写
	public static final String totleMoneyRmb = "totleMoneyRmb";  //#总价格大写
	
	public static final String PrelimipayRMBlower = "PrelimipayRMBlower";	//#初验付款百分比折算人民币小写
	public static final String PrelimipayRMBCapital = "PrelimipayRMBCapital";//#初验付款百分比折算人民币大写
	
	public static final String perceRateRMBlower = "perceRateRMBlower";  //#贷款百分比折算人民币小写
	public static final String perceRateRMBCaptial = "perceRateRMBCaptial";	//#贷款百分比折算人民币大写
	
	public static final String finalPayRMBlower = "finalPayRMBlower"; //#终验付款百分比折算人民币小写
	public static final String finalPayRMBCapital = "finalPayRMBCapital";//#终验付款百分比折算人民币大写
	
	public static final String PrelimiPay = "PrelimiPay";			//初验付款贷款百分比
	public static final String totalPriceLoans = "totalPriceLoans";//#本合同总价贷款百分比
	public static final String finalPay = "finalPay";//#终验付款贷款百分比
	
	public static final String publishProject = "0"; //发布模板审核流程
	public static final String templateProcess="1"; //发布项目流程
	public static final String caigouProcess="2"; //发布采购询价流程
	public static final String orderarriveProcess="3"; //发布订单到货流程
	public static final String shouhouProcess="4"; //发布售后流程
	//合同模板审核状态
	public static final int template_check_now = 0;//审核中
	public static final int template_check_unpass = 1;//未审核
	public static final int template_check_sellPass = 2;//销售审核通过
	public static final int template_check_sellUnpass = 3;//销售审核未通过
	public static final int template_check_financialPass = 4;//财务审核通过
	public static final int template_check_financialUnpass = 5;//财务审核未通过
	public static final int template_check_generalManagerPass = 6;//经理审核通过
	public static final int template_check_generalManagerUnpass = 7;//经理审核未通过
	
	//审核是否通过
	public static final String check_pass = "0";  //通过
	public static final String check_unpass = "1";  //不通过
	
	//角色
	public static final long role_admin = 1;//系统管理员
	public static final long role_ordinary_users = 2;//普通用户
	public static final long role_manager = 3;//经理
	public static final long role_seller = 4;//销售
	public static final long role_purchase = 5;//采购
	public static final long role_financial = 6;//财务
	public static final long role_aftermarket = 7;//售后
	
	//创建任务，任务跳转常量定义
	public static final int task_sale_templateCheck = 1;			//模板创建完,通知销售审核
	public static final int task_caiwu_templateCheck = 2;			//销售审核完合同模板,通知财务审核
	public static final int task_sale_templateCheck_Unpass = 3;     //销售审核不通过，通知系统管理员
	public static final int task_generalManager_templateCheck = 4;	//财务审核完合同模板,通知经理审核
	public static final int task_caiwu_templateCheck_Unpass = 5;    //财务审核不通过，通知销售
	public static final int task_templateCheck_finish = 6;			//经理模板审核完成，通知销售做报价表
	public static final int task_generalmanager_templateCheck_Unpass = 7;//经理审核不通过，通知财务
	
	public static final int task_quotationCheck = 8;				//销售制作完报价表，通知经理审核
	public static final int task_quotationCheck_Unpass=9;			//经理审核报价表，不通过
//	public static final int task_quotationCheck_finish = 9;	//经理审核完报价表，通知销售生成合同
	public static final int task_saleOrderFinish = 10;				//销售制作完销售订单，通知采购
	public static final int task_askOrderCheck = 11; 				//询价单制作完成，通知经理审核审核
	public static final int task_askOrderCheck_finish=12;			//经理审核完询价，通知采购签订厂商合同
	public static final int task_purchaseOrder=13;					//采购下单，通知财务付款
	public static final int task_get_the_invoice =14;             //申请客户收款发票
	public static final int audit_contract = 15;//提交审核合同
	public static final int audit_contract_result = 16;//审核正式合同结果
	public static final int audit_contractL_result = 17;//审核正式合同结果
	public static final int audit_inquiry = 18; //提交询价单审核
	public static final int audit_suppliers = 19; //提交供应商审核
	
	public static final HashMap<Integer,String> task_url=new HashMap<Integer,String>(){
		{
			put(task_sale_templateCheck,"/templateCheck/templateDoc.jsp?");
			put(task_caiwu_templateCheck,"/templateCheck/templateDoc.jsp?");
			put(task_sale_templateCheck_Unpass,"/contract/addcontract.jsp?");
			put(task_generalManager_templateCheck,"/templateCheck/templateDoc.jsp?");
			put(task_caiwu_templateCheck_Unpass,"/templateCheck/templateDoc.jsp?");
			put(task_templateCheck_finish,"/config/config.jsp");
			put(task_generalmanager_templateCheck_Unpass,"/templateCheck/templateDoc.jsp?");
			
			put(task_quotationCheck,"/check/checkDetails.jsp?");
			put(task_quotationCheck_Unpass,"/rapaport/rapaport.jsp?");

			put(task_saleOrderFinish,"/InquiryOrder/inquiryOrder.jsp");
			put(task_askOrderCheck,"/InquiryOrder/orderChecked.jsp");
			put(task_askOrderCheck_finish,"/purchaseContract/purchaseContract.jsp");
			put(task_get_the_invoice,"/invoiceManager/invoiceManager.jsp");
			put(task_purchaseOrder,"");
			put(audit_contract,"checkContract/checkContract_manage.jsp");
			put(audit_contract_result,"/contract/createcontract/file.jsp");
			put(audit_contractL_result,"/contractTemporary/contractTemporaryInfo.jsp");
			
			put(audit_inquiry,"/InquiryOrder/orderChecked.jsp");
			put(audit_suppliers,"/checkSuppliers/checkSuppliers.jsp");
		}
	};
	
	//该常量用于拼接任务表中对应个外键的主键id如：订单表：订单表常量_主键id（order_1）
	public static final String quotation_pkid = "quotation_"; //报价表主键id拼接，提交审核和通过 
	public static final String unQuotation_pkid = "unQuotation_"; //报价表主键id拼接，审核不通过
	public static final String askorders_pkid = "askorder_"; //询价单审核主键id拼接,提交审核和通过
	public static final String unAskorders_pkid = "unAskorder_"; //询价单审核主键id拼接，审核不通过
	public static final String contract_pkid = "contract_"; //审核正式合同主键id拼接,提交审核和通过
	public static final String unContract_pkid = "unContract_"; //审核正式合同主键id拼接，审核不通过
	public static final String sell_ask_order_pkid = "sellAsk_"; //销售人员转给采购人员进行采购，询价单接受任务id拼接
	public static final String temp_contract_pkid = "tempContract_";//审核临时合同主键id拼接，审核通过交审核
	public static final String unTemp_contract_pkid = "unTempContract_";//审核临时合同主键id拼接，审核不通过
	public static final String invoic_pkid = "invoic_"; //发票表主键id拼接
	public static final String suppliers_pkid = "suppliers_"; //供应商主键id拼接，提交审核和通过
	public static final String unSuppliers_pkid = "unSuppliers_"; //供应商主键id拼接，提交审核和通过
	public static final String template_pkid = "template_"; //合同模板主键id拼接，提交审核和通过
	public static final String unTemplate_pkid = "unTemplate_"; //合同模板主键id拼接，提交审核和通过
}
