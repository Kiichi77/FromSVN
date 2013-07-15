package cn.com.kxcomm.common.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * 
 * 功能描述:判断对象是否为空
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public class BlankUtil {

	/**
	 * 
	 * 方法用途和描述: 判断字符串是否为空 
	 * 
	 * @param str
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final String str) {
		if("null".equals(str)){
			return true;
		}
		return (str == null) || (str.trim().length() <= 0);
	}

	/**
	 * 
	 * 方法用途和描述: 判断字符是否为空 
	 * 
	 * @param cha
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Character cha) {
		return (cha == null) || cha.equals(' ');
	}

	/**
	 * 
	 * 方法用途和描述: 判断对象是否为空 
	 * 
	 * @param obj
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Object obj) {
		return (obj == null);
	}

	/**
	 * 
	 * 方法用途和描述: 判断数组是否为空
	 * 
	 * @param objs
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	/**
	 * 
	 * 方法用途和描述: 判断Collection是否为空 
	 * 
	 * @param obj
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Collection<Object> obj) {
		return (obj == null) || (obj.size() <= 0);
	}
	

	/**
	 * 
	 * 方法用途和描述: 判断Set是否为空 
	 * 
	 * @param obj
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Set<Object> obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	/**
	 * 
	 * 方法用途和描述: 判断Serializable是否为空
	 * 
	 * @param obj
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Serializable obj) {
		return obj == null;
	}

	/**
	 * 
	 * 方法用途和描述:判断Map是否为空 
	 * 
	 * @param obj
	 * @return 为空返回true
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public static boolean isBlank(final Map<Object, Object> obj) {
		return (obj == null) || (obj.size() <= 0);
	}
	
	/**
	 * 
	* 方法用途和描述: 将double型数据转换成保留小数点后两位
	* @param num
	* @param precision
	* @return
	* @author chenhui 新增日期：2010-12-23
	* @author 你的姓名 修改日期：2010-12-23
	* @since zte_crbt_bi
	 */
	public static double doubleFormatByDoubleNum(double num,int precision){
		double   factor   =   Math.pow(10,precision);
		return  Math.floor(num * factor + 0.5) /factor; 
	}
	/**
	 * 
	 * 方法用途和描述:给数字型数据加千位分隔符
	 * 
	 * @return
	 * @throws Exception
	 * @author 李智林 新增日期：2012-03-03
	 * @author 你的姓名 修改日期：
	 * @throws SQLException 
	 * @since smg_bi
	 */
	private static String changeNunFun(Long num) {
		if(null == num || 0 == num) {
			return "0";
		}
		String ss = num+"";
	         StringBuffer ret = new StringBuffer();
	        for( int i = ss.length()-1; i>=0; i-- ){
	            ret.append( ss.charAt(i) );
	            if( (ss.length()-i)%3==0 ){
	                ret.append(",");
	            }
	        }
	    String end = ret.reverse().toString();
	    if(",".equals(end.substring(0,1)) || "0".equals(end.substring(0,1))) {
	    	end = end.substring(1,end.length());
	    }
	    return end;
	}
	
	/**
	 * 
	* 方法用途和描述: 判断是否为空
	* @param obj
	* @return
	* @author chenl 新增日期：2012-8-16
	* @since ipmi_web
	 */
	public static String isNull(Object obj){
		String str ="";
		if(!BlankUtil.isBlank(obj) && !"".equals(obj)){
			str = obj.toString();
		}
		return str;
	}
	
}