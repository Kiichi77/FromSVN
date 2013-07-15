package cn.com.kxcomm.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import org.apache.log4j.Logger;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

/**
 * 
* 功能描述:计算公式
* @author chenliang 新增日期：2012-12-14
* @since ContractManage
 */
public class FormulaUtil {
	
	private static final Logger log = Logger.getLogger(FormulaUtil.class);
	/**
	 * 
	* 方法用途和描述: 计算目录合价
	* @param vo  详情vo
	* @return
	* @author chenliang 新增日期：2012-12-13
	* @since ContractManage
	 */
	public static double planTotalPrice(DataRecordVo vo){
		double quantity = vo.getQuantity();  //数量
		double unitPrice = vo.getUnitPrice();  //单价
		 BigDecimal bg = new BigDecimal(quantity*unitPrice);
		 double sum =  bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //目录单价
		log.debug("sum:"+sum);
		return sum;
	} 
	
	/**
	 * 
	* 方法用途和描述: 计算折扣后价格
	* @param discountRate
	* @return
	* @author chenliang 新增日期：2012-12-13
	* @since ContractManage
	 */
	public static double planRabateprice(DataRecordVo vo){
		double sum = planTotalPrice(vo);   //目录单价
		double discountRate = vo.getDiscountRate();  //折扣率
		BigDecimal bg = new BigDecimal(sum*(1-discountRate));
		double price=bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//折扣后价格
		log.info("price:"+price);
		return price;
	}
	
	/**
	 * 
	* 方法用途和描述: 计算折扣后现场价
	* @param otheRates 
	* @return
	* @author chenliang 新增日期：2012-12-13
	* @since ContractManage
	 */
	public static double planRateafter(DataRecordVo vo){
		double rebatePrice = planRabateprice(vo);  //折扣后价格
		double otherRate = vo.getOtherRates();     //运保及其他费率
		BigDecimal bg = new BigDecimal(rebatePrice*(1+otherRate));
		double reteafter=bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //折扣后现场价
		log.info("reteafter:"+reteafter);
		return reteafter;
	}
	
	/**
	 * 
	* 方法用途和描述: 计算合计
	* @param vo
	* @return
	* @author chenliang 新增日期：2012-12-13
	* @since ContractManage
	 */
	public static double planTotal(DataRecordVo vo){
		double reteafter = planRateafter(vo); //折扣后现场价
		double installServicerCost = vo.getInstallServiceCharge();
		double warrantyCosts = vo.getFirstYear()+vo.getSecondYear()+vo.getThirdYear();
		BigDecimal bg = new BigDecimal(reteafter+installServicerCost+warrantyCosts);
		double total=bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		log.info("total:"+total);
		return total;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取配置文件中的变量
	* @return map集合
	* @author chenliang 新增日期：2013-1-16
	* @since ContractManage
	 */
	public static HashMap<String, String> getConfigTemplatesParam(){
		HashMap<String, String> map = new HashMap<String, String>();
		AppConfig config = AppConfig.getInstance();
		//#总价格小写
		String totleMoneyNum = config.getString("totleMoneyNum");
		map.put(BusinessConstants.totleMoneyNum, totleMoneyNum);
		//#总价格大写
		String totleMoneyRmb = config.getString("totleMoneyRmb");
		map.put(BusinessConstants.totleMoneyRmb, totleMoneyRmb);
		//#贷款百分比折算人民币小写
		String perceRateRMBlower = config.getString("perceRateRMBlower");
		map.put(BusinessConstants.perceRateRMBlower, perceRateRMBlower);
		//#贷款百分比折算人民币大写
		String perceRateRMBCaptial = config.getString("perceRateRMBCaptial");
		map.put(BusinessConstants.perceRateRMBCaptial, perceRateRMBCaptial);
		//#初验付款百分比折算人民币小写
		String PrelimipayRMBlower = config.getString("PrelimipayRMBlower");
		map.put(BusinessConstants.PrelimipayRMBlower, PrelimipayRMBlower);
		//#初验付款百分比折算人民币大写
		String PrelimipayRMBCapital = config.getString("PrelimipayRMBCapital");
		map.put(BusinessConstants.PrelimipayRMBCapital, PrelimipayRMBCapital);
		//#终验付款百分比折算人民币小写
		String finalPayRMBlower = config.getString("finalPayRMBlower");
		map.put(BusinessConstants.finalPayRMBlower, finalPayRMBlower);
		//#终验付款百分比折算人民币大写
		String finalPayRMBCapital = config.getString("finalPayRMBCapital");
		map.put(BusinessConstants.finalPayRMBCapital, finalPayRMBCapital);
		//初验付款贷款百分比
		String PrelimiPay = config.getString("PrelimiPay");
		map.put(BusinessConstants.PrelimiPay, PrelimiPay);
		//#本合同总价贷款百分比
		String totalPriceLoans = config.getString("totalPriceLoans");
		map.put(BusinessConstants.totalPriceLoans, totalPriceLoans);
		//#终验付款贷款百分比
		String finalPay = config.getString("finalPay");
		map.put(BusinessConstants.finalPay, finalPay);
		return map;
	}
	
	/**
	 * 
	* 方法用途和描述: 判断变量名是否和配置文件中名字一样
	* @param variableName
	* @return
	* @author chenliang 新增日期：2013-1-17
	* @since ContractManage
	 */
	public static boolean checkVariableName(String variableName){
		HashMap<String, String> map = getConfigTemplatesParam();
		if(!BlankUtil.isBlank(variableName)){
			if(variableName.equals(map.get(BusinessConstants.totleMoneyNum))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.totleMoneyRmb))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.perceRateRMBlower))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.perceRateRMBCaptial))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.PrelimipayRMBlower))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.PrelimipayRMBCapital))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.finalPayRMBlower))){
				return true;
			}else if(variableName.equals(map.get(BusinessConstants.finalPayRMBCapital))){
				return true;
			}else{
				return false;
			}
		}else{
			log.info("variablename is null.");
			return false;
		}
	}
	
}
