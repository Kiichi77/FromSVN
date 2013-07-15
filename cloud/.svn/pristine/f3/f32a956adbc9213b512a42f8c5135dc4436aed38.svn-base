package cn.com.kxcomm.common.util;

import java.util.Locale;

/**
 * 
* 功能描述:本地化工具类
* <p>版权所有：康讯通讯
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author lizl 新增日期：2010-7-7
* @author 你的姓名 修改日期：2010-7-7
* @since zte_crbt_bi
 */
public class LocalUtil {
	/**
	 * 
	* 方法用途和描述: 获取本地语言标记   1:中文   0:默认为英文
	* @return
	* @author lizl 新增日期：2010-7-7
	* @author 你的姓名 修改日期：2010-7-7
	* @since zte_crbt_bi
	 */
	public static String  getCountryCode(){
		String countryCode = Locale.getDefault().getCountry();
		if("CN".equals(countryCode.trim()) || "TW".equals(countryCode.trim()) || "HK".equals(countryCode.trim()) || "SG".equals(countryCode.trim()) || "MO".equals(countryCode.trim())){
			countryCode = "1";//表示中文
		}
		else
			countryCode = "0";//表示英文
		return countryCode;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取需要引用的资源文件名
	* @return
	* @author lizl 新增日期：2010-7-7
	* @author 你的姓名 修改日期：2010-7-7
	* @since zte_crbt_bi
	 */
	public static String getResourceFileName(String url){
		String name =  "";
		int end = url.lastIndexOf("/");
		if(end == -1)
			end =url.lastIndexOf("\\");
		String subStr = url.substring(0,end);
		int secondEnd = subStr.lastIndexOf("/");
		if(secondEnd == -1)
			secondEnd = subStr.lastIndexOf("\\");
		if("1".equals(getCountryCode())){//中文
			name=  subStr.substring(secondEnd+1,subStr.length())+"_localeCN.js";
		}else{//英文
			name =  subStr.substring(secondEnd+1,subStr.length())+"_locale.js";
		}
		return name;
	}

}
