package cn.com.kxcomm.common.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * 功能描述:日期格式处理、转换工具类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public final class DateFormatUtil {

	/** 注意格里历和儒略历交接时的日期差别 */
	private static transient int gregorianCutoverYear = 1582;

	/** 闰年中每月天数 */
	private static final int[] DAYS_P_MONTH_LY = { 31, 29, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	/** 平年中每月天数 */
	private static final int[] DAYS_P_MONTH_CY = { 31, 28, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	/** 代表数组里的年、月、日 */
	private static final int Y = 0, M = 1, D = 2, HH = 3, mm = 4, ss = 5;

	/**
	 * 日期类型枚举对象
	 */
	enum DateStyle {
		/**
		 * 全日期型
		 */
		FULL(DateFormat.FULL),
		/**
		 * 长日期型
		 */
		LONG(DateFormat.LONG),
		/**
		 * 中日期型
		 */
		MEDIUM(DateFormat.MEDIUM),
		/**
		 * 短日期型
		 */
		SHORT(DateFormat.SHORT);
		private int value;

		private DateStyle(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 方法用途和描述: 按照给定的日期类型将指定的日期转换成字符串。
	 * 
	 * @param date
	 *            需要格式化的日期对象
	 * @param style
	 *            日期类型枚举对象
	 * @param loc
	 *            字符定义对象
	 * @return 格式化后的日期时间字符串
	 * @author 邓成东 新增日期：2008-2-21
	 * @author 你的姓名 修改日期：Mar 7, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String formatDate(Date date, DateStyle style, Locale loc)
			throws Exception {
		if (style == null) {
			throw new Exception("Datestyle is null");// 没有指定日期格式
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			DateFormat df = DateFormat.getDateInstance(style.getValue(), loc);
			newDate = df.format(date);
		}
		return newDate;
	}

	/**
	 * 按照给定的格式模板将指定的日期值转换成字符串。
	 * 
	 * @param date
	 *            : 待转换的日期
	 * @param pattern
	 *            : 指定转化格式字符串,例如：yyyy-MM-dd
	 * @param loc
	 *            : 字符定义对象
	 * @return 格式化后的日期字符串
	 * @author 邓成东 新增日期：2008-2-21
	 * @since CE-Common version(1.0)
	 */
	public static String formatDate(Date date, String pattern, Locale loc) {

		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
			newDate = sdf.format(date);
		}
		return newDate;
	}

	/**
	 * 按照不同的日期格式和时间格式，将指定的日期时间值转换成字符串。如：2008年4月18日 上午11时26分30秒
	 * 
	 * @param date
	 *            : 待转换的日期
	 * @param dateStyle
	 *            日期类型枚举对象
	 * @param timeStyle
	 *            ：时间类型枚举对象
	 * @param loc
	 *            ：字符定义对象
	 * @return 格式化后的日期时间字符串
	 * @author 邓成东 新增日期：2008-2-21
	 * @author 你的姓名 修改日期：Mar 7, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String formatDateTime(Date date, DateStyle dateStyle,
			DateStyle timeStyle, Locale loc) {
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			DateFormat df = DateFormat.getDateTimeInstance(
					dateStyle.getValue(), timeStyle.getValue(), loc);
			newDate = df.format(date);
		}
		return newDate;
	}

	/**
	 * 
	 * 方法用途和描述: 获得当前日期，时间位置为00:00:00
	 * 
	 * @return Date实例
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 
	 * 方法用途和描述: 获得当前系统的时间戳
	 * 
	 * @return 从1970-1-1到现在的毫秒数
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 方法用途和描述: 取得当前时间的字符串 例如：20080221050416
	 * 
	 * @return
	 * @author 邓成东 新增日期：2008-2-21
	 * @since CE-Common version(1.0)
	 */
	public static String getCurrentTimeStr() {
		String str = longFormatDateTime(new Date());
		str = str.trim();
		str = str.replaceAll("-", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll(" ", "");
		return str;
	}

	/**
	 * 
	 * 方法用途和描述:获得当前去掉时分秒的时间
	 * 
	 * @return 返回处理后的当前时间，如：Fri Apr 18 00:00:00 CST 2008
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date getFormatDate() throws ParseException {

		Date now = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = bartDateFormat.format(now);
		now = bartDateFormat.parse(strDate);
		return now;
	}

	/**
	 * 
	 * 方法用途和描述:把指定日期的时分秒去掉
	 * 
	 * @return 返回处理后的当前时间，如：Fri Apr 18 00:00:00 CST 2008
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date getFormatDate(Date date) throws ParseException {

		Date now = date;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = bartDateFormat.format(now);
		now = bartDateFormat.parse(strDate);
		return now;
	}

	/**
	 * 
	 * 方法用途和描述: 根据传入的日期格式获得当前时间。 格式如:yyyy-MM-dd : HH:mm:ss(24小时制)，yyyy-MM-dd :
	 * hh:mm:ss(12小时制)等
	 * 
	 * @param pattern
	 * @return
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date getFormatTime(String pattern) throws ParseException {

		Date now = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(pattern);
		String strDate = bartDateFormat.format(now);
		now = bartDateFormat.parse(strDate);
		return now;
	}

	/**
	 * 
	 * 方法用途和描述:获得该天的结束临界时间，如：Fri Apr 18 23:59:59 CST 2008
	 * 
	 * @return 返回处理后的当前时间，如：Fri Apr 18 23:59:59 CST 2008
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date getEndOfDate(Date date) throws Exception {

		Date end = new Date(getFormatDate(date).getTime() + 24 * 3600 * 1000
				- 1);

		return end;
	}

	/**
	 * 按照不同的格式模板将指定的日期时间值转换成字符串。
	 * 
	 * @param date
	 *            : 待转换的日期
	 * @param dateStr
	 *            : 指定日期转化格式字符串模板,例如:yyyy-MM-dd
	 * @param timeStr
	 *            : 指定时间转化格式字符串模板,例如:hh:mm:ss
	 * @param loc
	 *            ：字符定义对象
	 * @return 格式化后的日期时间字符串
	 * @throws Exception
	 *             : pattern模板不符合格式时报异常
	 * @author 邓成东 新增日期：2008-2-21
	 * @since CE-Common version(1.0)
	 */
	public static String formatDateTime(Date date, String dateStr,
			String timeStr, Locale loc) throws Exception {
		if (BlankUtil.isBlank(date)) {
			return "";
		}
		if (BlankUtil.isBlank(dateStr)) {
			throw new Exception("dateStr is null");
		}
		if (BlankUtil.isBlank(timeStr)) {
			throw new Exception("timeStr is null");
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		String pattern = dateStr.concat(" ").concat(timeStr);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
		newDate = sdf.format(date);
		return newDate;
	}

	/**
	 * 
	 * 方法用途和描述: 把日期时间格式化为yyyy-MM-dd HH:mm:ss格式
	 * 方法的实现逻辑描述（如果是接口方法可以不写）：XXXXXXXXX（可以分多行编写）
	 * 
	 * @param dt
	 *            java.util.Date
	 * @return yyyy-MM-dd HH:mm:ss格式的日期字符串
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String longFormatDateTime(Date dt) {
		String newDate = "";
		if (dt != null) {
			Locale locale = Locale.CHINESE;
			SimpleDateFormat dateStyle = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", locale);
			newDate = dateStyle.format(dt);
		}
		return newDate;
	}

	/**
	 * 
	 * 方法用途和描述: 把日期时间格式化为yyyy-MM-dd格式
	 * 
	 * @param dt
	 *            java.util.Date
	 * @return yyyy-MM-dd格式的日期字符串
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String middleFormatDateTime(Date dt) {

		String newDate = "";
		if (dt != null) {
			Locale locale = Locale.CHINESE;
			SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd",
					locale);
			newDate = dateStyle.format(dt);
		}
		return newDate;
	}

	/**
	 * 
	 * 方法用途和描述: 把日期时间格式化为HH:mm:ss格式
	 * 
	 * @param dt
	 *            java.util.Date
	 * @return HH:mm:ss格式的日期字符串
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String sortFormatDateTime(Date dt) {

		String newDate = "";
		if (dt != null) {
			Locale locale = Locale.CHINESE;
			SimpleDateFormat dateStyle = new SimpleDateFormat("HH:mm:ss",
					locale);
			newDate = dateStyle.format(dt);
		}
		return newDate;
	}

	/**
	 * 
	 * 方法用途和描述: 将指定的字符串转换成日期
	 * 
	 * @param dateStr
	 *            : 待转换的日期符串,以yyyy-MM-dd模板进行转换
	 * @return 返回标准的日期格式yyyy-MM-dd,与字符串dateStr对应的date对象
	 * @throws Exception
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date parseStrToDate(String dateStr) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.parse(dateStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * 方法用途和描述: 按照不同的格式模板将指定的字符串转换成日期。
	 * 
	 * @param date
	 *            : 待转换的日期符串
	 * @param pattern
	 *            : 字符串的格式模板,例如:yyyy-MM-dd hh:mm:ss
	 * @return 与字符串dateStr对应的date对象
	 * @throws Exception
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date parseStrToDate(String date, String pattern)
			throws Exception {
		if (BlankUtil.isBlank(date)) {
			throw new Exception("date is null");
		}
		if (BlankUtil.isBlank(pattern)) {
			throw new Exception("pattern is null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	/**
	 * 
	 * 方法用途和描述: 在给定的日期点上加入指定的天数
	 * 
	 * @param date
	 *            给定的日期点
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addDate(Date date, int days) {

		if (date == null)
			return date;
		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day + days);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在当前的日期点上加入指定的天数
	 * 
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addDate(int days) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day + days);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在当前的日期点上减去指定的天数
	 * 
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date reduceDate(int days) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day - days);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在指定的日期点上减去指定的天数
	 * 
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date reduceDate(Date date, int days) {
		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day - days);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在当前的日期点上加入指定的年数
	 * 
	 * @param years
	 *            年数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addYear(int years) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year + years, month, day);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在指定的日期点上加入指定的年数
	 * 
	 * @param date
	 *            指定的时间
	 * @param years
	 *            年数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addYear(Date date, int years) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year + years, month, day);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在当前的日期点上加入指定的月数
	 * 
	 * @param months
	 *            年数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addMonth(int months) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		if (month + months > 11) {
			year += (month + months) / 12;
			month = (month + months) % 12;
		} else if (month + months < 1) {
			year += (month + months) / 12 - 1;
			month = 12 + ((month + months) % 12);
		} else {
			month += months;
		}
		cal.set(year, month, day);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 在指定的日期点上加入指定的月数
	 * 
	 * @param date
	 *            指定的时间
	 * @param months
	 *            年数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static Date addMonth(Date date, int months) {

		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		if (month + months > 11) {
			year += (month + months) / 12;
			month = (month + months) % 12;
		} else if (month + months < 1) {
			year += (month + months) / 12 - 1;
			month = 12 + ((month + months) % 12);
		} else {
			month += months;
		}
		cal.set(year, month, day);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法用途和描述: 获得两个时间点之间相差的天数
	 * 
	 * @param beginDate
	 *            开始时间点
	 * @param endDate
	 *            结束时间点
	 * @return 返回天数差值
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static int getDaysBetween(Date beginDate, Date endDate) {

		if (beginDate == null || endDate == null)
			return 0;
		return (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 
	 * 方法用途和描述: 计算两个日期之间的相隔天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @author 邓成东 新增日期：Apr 21, 2008
	 * @author 你的姓名 修改日期：Apr 21, 2008
	 * @since CE-Common version(1.0)
	 */
	public static int getDaysBetween(java.util.Calendar beginDate,
			java.util.Calendar endDate) {
		if (beginDate == null || endDate == null) {
			return 0;
		}
		if (beginDate.after(endDate)) {
			java.util.Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int days = endDate.get(java.util.Calendar.DAY_OF_YEAR)
				- beginDate.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = endDate.get(java.util.Calendar.YEAR);
		if (beginDate.get(java.util.Calendar.YEAR) != y2) {
			beginDate = (java.util.Calendar) beginDate.clone();
			do {
				days += beginDate
						.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				beginDate.add(java.util.Calendar.YEAR, 1);
			} while (beginDate.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 
	 * 方法用途和描述: 检查传入的参数是否合法的日期
	 * 
	 * @param date
	 *            需要验证的日期
	 * @throws Exception
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static void validate(String date) throws Exception {

		int[] ymd = splitYMD(date);

		if (ymd[M] == 0 || ymd[M] > 12) {
			throw new Exception("Moth is error");
		}

		if (true == isLeapYear(ymd[0])) {
			if (ymd[D] == 0 || ymd[D] > DAYS_P_MONTH_LY[ymd[M] - 1]) {
				throw new Exception("Day is error");
			}
		} else {
			if (ymd[D] == 0 || ymd[D] > DAYS_P_MONTH_CY[ymd[M] - 1]) {
				throw new Exception("Day is error");
			}
		}
	}

	/**
	 * 
	 * 方法用途和描述: 将代表日期的字符串分割为代表年月日的整形数组
	 * 
	 * @param date
	 *            格式如:2007-12-25:12-25-33
	 * @return
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static int[] splitYMD(String date) {

		int[] ymd = { 0, 0, 0, 0, 0, 0 };
		ymd[Y] = Integer.parseInt(date.substring(0, 4));
		ymd[M] = Integer.parseInt(date.substring(5, 7));
		ymd[D] = Integer.parseInt(date.substring(8, 10));
		ymd[HH] = Integer.parseInt(date.substring(11, 13));
		ymd[mm] = Integer.parseInt(date.substring(14, 16));
		ymd[ss] = Integer.parseInt(date.substring(17, 19));
		return ymd;
	}

	/**
	 * 
	 * 方法用途和描述:检查传入的参数代表的年份是否为闰年
	 * 
	 * @param year
	 *            年份
	 * @return true-闰年/false-平年
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static boolean isLeapYear(int year) {

		return year >= gregorianCutoverYear ? ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
				: // Gregorian
				(year % 4 == 0); // Julian
	}

	/**
	 * 
	 * 方法用途和描述: 将不足两位的月份或日期补足为两位,如：2变为02
	 * 
	 * @param decimal
	 *            月份或天数
	 * @return
	 * @author 邓成东 新增日期：Apr 18, 2008
	 * @author 你的姓名 修改日期：Apr 18, 2008
	 * @since CE-Common version(1.0)
	 */
	public static String formatMonthOrDay(int decimal) {

		DecimalFormat df = new DecimalFormat("00");
		return df.format(decimal);
	}

	/**
	 * 
	 * 方法用途和描述: 获取指定时间段内工作的天数(五天工作制)
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @author 邓成东 新增日期：Apr 21, 2008
	 * @author 你的姓名 修改日期：Apr 21, 2008
	 * @since CE-Common version(1.0)
	 */
	public static int getWorkingDay(java.util.Calendar beginDate,
			java.util.Calendar endDate) {
		if (beginDate == null || endDate == null) {
			return 0;
		}
		int result = -1;
		if (beginDate.after(endDate)) { // swap dates so that d1 is start and d2
			// is end
			java.util.Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int charge_start_date = 0;// 开始日期的日期偏移量
		int charge_end_date = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - beginDate.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - endDate.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
			charge_end_date = etmp - 1;
		}
		result = (getDaysBetween(getNextMonday(beginDate),
				getNextMonday(endDate)) / 7)
				* 5 + charge_start_date - charge_end_date;
		return result;
	}

	/**
	 * 
	 * 方法用途和描述: 获得日期的下一个星期一的日期
	 * 
	 * @param date
	 *            指定日期
	 * @return 下一个星期一的日期
	 * @author 邓成东 新增日期：Apr 21, 2008
	 * @author 你的姓名 修改日期：Apr 21, 2008
	 * @since wapportal_manager version(2.0)
	 */
	public static Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 
	 * 方法用途和描述: 计算两个指定日期间存在的休息日的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return 休息日的天数
	 * @author 邓成东 新增日期：Apr 21, 2008
	 * @author 你的姓名 修改日期：Apr 21, 2008
	 * @since wapportal_manager version(2.0)
	 */
	public static int getHolidays(Calendar beginDate, Calendar endDate) {
		return getDaysBetween(beginDate, endDate)
				- getWorkingDay(beginDate, endDate);

	}

	/**
	 * 
	 * 方法用途和描述: 获取当前日期的前一天的结束时间
	 * 
	 * @return （example:20100701 23:59:59.999999）
	 * @author chenhui 新增日期：2010-7-5
	 * @author 你的姓名 修改日期：2010-7-5
	 * @since zte_crbt_bi
	 */
	public static String getLastDateEndString() {
		String timeStr = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date lastDate = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		timeStr = df.format(lastDate);
		timeStr = timeStr + " 23:59:59.999999";
		return timeStr;
	}

	/**
	 * 
	 * 方法用途和描述: 获取当前日期的前一天的开始时间
	 * 
	 * @return （example:20100701 00:00:00.000000）
	 * @author chenhui 新增日期：2010-7-5
	 * @author 你的姓名 修改日期：2010-7-5
	 * @since zte_crbt_bi
	 */
	public static String getLastDateStartString() {
		String timeStr = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date lastDate = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		timeStr = df.format(lastDate);
		timeStr = timeStr + " 00:00:00.000000";
		return timeStr;
	}
	
	/**
	 * 
	* 方法用途和描述: 返回结束时间sybase datetime格式的后缀字符串
	* @return  23:59:59.999999
	* @author chenhui 新增日期：2010-7-12
	* @author 你的姓名 修改日期：2010-7-12
	* @since zte_crbt_bi
	 */
	public static String getEndTimeSuffix(){
		
		return " 23:59:59.999999";
		
	}
	
	/**
	 * 
	* 方法用途和描述: 返回开始时间sybase datetime格式的后缀字符串
	* @return  00:00:00.000000
	* @author chenhui 新增日期：2010-7-12
	* @author 你的姓名 修改日期：2010-7-12
	* @since zte_crbt_bi
	 */
	public static String getStartTimePrefix(){
		return " 00:00:00.000000";
	}

	public static void main(String[] args) {
		try {
			//		
			System.out.println("start:"
					+ DateFormatUtil.getLastDateStartString());
			System.out.println("end:" + DateFormatUtil.getLastDateEndString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/***
	 * 
	 * @param date
	 * @param s
	 * @return
	 */
	public static String formatDate(String date, String s) {
		String now = null;
		Date myDate = null;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			myDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				s);
		now = dateFormat.format(myDate);
		return now;
	}

	public static Date stringToDate(String dateStr, String formt) {
		java.util.Date re = null;

		try {

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					formt);

			re = sdf.parse(dateStr);

		} catch (ParseException e) {

			e.printStackTrace();

		}
		return re;
	}

	/***
	 * java.util.date转java.sql.Date
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date getSqlDateTime(java.util.Date date) {
		long time = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(time);
		return sqlDate;
	}
	
	/**
	* 时间字符串转换Date
	* @param dateStr
	* @param yyyyMmDd2
	* @return
	* @author panlei 新增日期：2011-9-22
	* @author 你的姓名 修改日期：2011-9-22
	 * @throws ParseException 
	* @since gdunicom_crbt_bi
	*/
	public static Date strToDate(String dateStr, String patter)  {
		SimpleDateFormat format = new SimpleDateFormat(patter);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
