package cn.com.kxcomm.ipmitool.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import cn.com.kxcomm.ipmitool.entity.Errors;
import cn.com.kxcomm.ipmitool.exception.SeriousError;

public class Util {
	/**
	 * 
	 * 十进制转换为十六进制
	 * 
	 * @param tmp
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String int2Hex(int tmp) {
		return Integer.toHexString(tmp);
	}

	/**
	 * 
	 * 十六进制取第一低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String firstByteInt(int OneWord) {
		char byte4 = (char) ((OneWord >> (0)) & 0x000000ff);
		return Integer.toHexString(byte4);
	}

	/**
	 * 
	 * 十六进制取第二低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String twoByteInt(int OneWord) {
		char byte3 = (char) ((OneWord >> (8)) & 0x000000ff);
		return Integer.toHexString(byte3);
	}

	/**
	 * 
	 * 十六进制取第三低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String threeByteInt(int OneWord) {
		char byte2 = (char) ((OneWord >> (16)) & 0x000000ff);
		return Integer.toHexString(byte2);
	}

	/**
	 * 
	 * 十六进制取第三低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String fourByteInt(int OneWord) {
		char byte1 = (char) ((OneWord >> (24)) & 0x000000ff);
		return Integer.toHexString(byte1);
	}

	/**
	 * 
	 * Integer 十进制转换为Integer 十六进制
	 * 
	 * @param n
	 * @return Integer
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static Integer hex2Int(Integer n) {
		String s = Integer.toHexString(n);
		Integer i = Integer.parseInt(s, 16);// 转为十六进制
		return i;
	}

	/**
	 * 
	 * Long 十进制转换为Long 十六进制
	 * 
	 * @param n
	 * @return Long
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static Long hex2Long(Long n) {
		String s = Long.toHexString(n);
		Long i = Long.parseLong(s, 16);// 转为十六进制
		return i;
	}

	/**
	 * 
	 * 十六进制取第一低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String firstByteLong(long OneWord) {
		char byte4 = (char) ((OneWord >> (0)) & 0x000000ff);
		return Long.toHexString(byte4);
	}

	/**
	 * 
	 * 十六进制取第二低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String twoByteLong(long OneWord) {
		char byte3 = (char) ((OneWord >> (8)) & 0x000000ff);
		return Long.toHexString(byte3);
	}

	/**
	 * 
	 * 十六进制取第三低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String threeByteLong(long OneWord) {
		char byte2 = (char) ((OneWord >> (16)) & 0x000000ff);
		return Long.toHexString(byte2);
	}

	/**
	 * 
	 * 十六进制取第四低位
	 * 
	 * @param OneWord
	 * @return String
	 * @author zhangjh 新增日期：2012-8-20
	 * @since ipmi_sdk
	 */
	public static String fourByteLong(long OneWord) {
		char byte1 = (char) ((OneWord >> (24)) & 0x000000ff);
		return Long.toHexString(byte1);
	}

	/**
	 * 
	 * 域返回
	 * 
	 * @param component
	 * @return String
	 * @author zhangjh 新增日期：2012-8-21
	 * @since ipmi_sdk
	 */
	public static String getDomain(int component) {
		String domain;
		switch (component) {
		case 0x00:
			domain = "0x00";
			break;
		case 0x01:
			domain = "0x01";
			break;
		case 0x02:
			domain = "0x02";
			break;
		default:
			domain = "0x00";
		}
		return domain;
	}

	/**
	 * 
	 * 日期(LONG->String)
	 * 
	 * @param tt
	 * @return
	 * @author zhangjh 新增日期：2012-8-21
	 * @since ipmi_sdk
	 */
	public static String long2DateStr(long tt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date(tt);
		String sDate = sdf.format(d);
		return sDate;
	}

	/**
	 * 
	 * 日期(String->LONG)
	 * 
	 * @param tt
	 * @return
	 * @author zhangjh 新增日期：2012-8-21
	 * @throws ParseException
	 * @since ipmi_sdk
	 */
	public static Long str2Long(String tt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(tt);
		long sDate = d.getTime();
		return sDate;
	}

	/**
	 * 
	 * 检测网络是否通
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-8-23
	 * @throws IOException
	 * @throws UnknownHostException
	 * @since ipmi_sdk
	 */
	public static void checkNetwork(String host) {
		Errors errors = new Errors(ErrorCode.HOST_NOT_EXIST,
				"Host Unreachable,Please check your network.Error host:" + host);
		try {
			int timeOut = 3000; // 超时应该在3钞以上
			boolean status = InetAddress.getByName(host).isReachable(timeOut);
			if (!status)
				throw new SeriousError(errors);
		} catch (Exception e) {
			throw new SeriousError(errors);
		}
	}
	
	/**
	 * 
	 * 分隔
	 * 
	 * @param str
	 * @param symbol
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public static String[] split(String str,String symbol){
		Errors errors = new Errors(ErrorCode.PARAMETER_ERROR,
				"Parameter error,Can not be converted! string:" + str+",symbol:"+symbol);
		try{
			String[] tmp = StringUtils.split(str,symbol);
			return tmp;
		}catch(Exception e){
			System.out.println("erorr..........");
			throw new SeriousError(errors);
		}
	}
}
