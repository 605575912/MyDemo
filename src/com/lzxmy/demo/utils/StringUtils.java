package com.lzxmy.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;



/**
 * 字符串操作工具包
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	public static SimpleDateFormat stime = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分");

	static DecimalFormat onedf = new DecimalFormat("#0.0");
	static DecimalFormat twodf = new DecimalFormat("#0.00");
	static DecimalFormat threefd = new DecimalFormat("#0.000");

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.parse(sdate);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String toDateString(String sdate) {
		return dateFormater2.format(toDatere(sdate));
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDatere(String sdate) {
		try {
			return dateFormater.parse(sdate);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 截取小数1~3位
	 * 
	 * @param sdate
	 * @return
	 */
	public static String toDecimal(String strs, int i) {
		switch (i) {
		case 1:
			return onedf.format(Double.valueOf(strs));
		case 2:
			return twodf.format(Double.valueOf(strs));
		case 3:
			return threefd.format(Double.valueOf(strs));
		default:
			return twodf.format(Double.valueOf(strs));
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String toString(String date) {
		return stime.format(toDate(date));
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date tocounDate(String sdate) {
		try {
			return dateFormater2.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getDate(String date) {
		Date localDate = null;
		try {
			localDate = new Date(sdf.parse(date).getTime() + 8 * 60 * 60 * 1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localDate;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static boolean Comparison_day(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return false;
		}
		// String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		// String curDate = dateFormater2.format(cal.getTime());
		// String paramDate = dateFormater2.format(time);
		// if(curDate.equals(paramDate)){
		// int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
		// if(hour == 0)
		// ftime = Math.max((cal.getTimeInMillis() - time.getTime()) /
		// 60000,1)+"分钟前";
		// else
		// ftime = hour+"小时前";
		// return ftime;
		// }

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		// if(days == 0){
		// int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
		// if(hour == 0)
		// ftime = Math.max((cal.getTimeInMillis() - time.getTime()) /
		// 60000,1)+"分钟前";
		// else
		// ftime = hour+"小时前";
		// }
		// else if(days == 1){
		// ftime = "昨天";
		// }
		// else if(days == 2){
		// ftime = "前天";
		// }
		// else if(days > 2 && days <= 10){
		// ftime = days+"天前";
		// }
		// else if(days > 10){
		// ftime = dateFormater2.format(time);
		// }
		if (days <= 3) {
			return true;
		}
		return false;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String Comparison_hour(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		// String curDate = dateFormater2.format(cal.getTime());
		// String paramDate = dateFormater2.format(time);
		// if(curDate.equals(paramDate)){
		int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
		if (hour == 0)
			ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,
					1) + "分钟前";
		else
			ftime = hour + "小时前";
		return ftime;
		// }

		// long lt = time.getTime()/86400000;
		// long ct = cal.getTimeInMillis()/86400000;
		// int days = (int)(ct - lt);
		// if(days == 0){
		// int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
		// if(hour == 0)
		// ftime = Math.max((cal.getTimeInMillis() - time.getTime()) /
		// 60000,1)+"分钟前";
		// else
		// ftime = hour+"小时前";
		// }
		// else if(days == 1){
		// ftime = "昨天";
		// }
		// else if(days == 2){
		// ftime = "前天";
		// }
		// else if(days > 2 && days <= 10){
		// ftime = days+"天前";
		// }
		// else if(days > 10){
		// ftime = dateFormater2.format(time);
		// }
		// return "";
	}






	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}
	/**
	 * 过滤HTML标签，取出文本内容
	 * 
	 * @param inputString
	 * @return
	 */
	public static String filterHtmlTag(String inputString) {
		String htmlStr = inputString; // 含HTML标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{�?script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{�?style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤HTML标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
	
}