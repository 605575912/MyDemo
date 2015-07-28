package com.lzxmy.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.SpannableString;

/**
 * @author {XiaOt} 类型转换工具类
 */
public class DataTypeUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat shortDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat shortDateFormat2 = new SimpleDateFormat(
			"yyyy/MM/dd");

	public static Double getDouble(Object obj) {
		Double res = 0.0;
		if (obj != null) {
			String doubleString = obj.toString();
			try {
				if (doubleString.indexOf(",") != -1) {
					doubleString = doubleString.replaceAll(",", "");
				}
				res = Double.valueOf(doubleString);
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static String StringDateFormat(long time) {
		Date date = null;
		try {
			date = new Date(time);
		} catch (Exception e) {
			// TODO: handle exception
			date = new Date();
		}

		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * 语音时间戳
	 * 
	 * @param time
	 * @return
	 */
	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String Comparison_hour(long sdate) {
		Date time = null;
		try {
			time = new Date(sdate);
		} catch (Exception e) {
			// TODO: handle exception
			time = new Date();
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		// String curDate = dateFormater2.format(cal.getTime());
		// String paramDate = dateFormater2.format(time);
		// if(curDate.equals(paramDate)){
		int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
		if (hour <= 0)
			ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,
					1) + "分钟前";
		else {
			// int hour = (int) ((cal.getTimeInMillis() - time.getTime()) /
			// 3600000);
			ftime = hour + "小时前";
			if (hour >= 24) {

				ftime = hour / 24 + "天前";
			}
		}

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

	public static String getDoubleString(Object obj) {
		String res = "";
		if (obj != null) {
			String doubleString = obj.toString();
			try {
				if (doubleString.indexOf("E") != -1) {

					java.text.NumberFormat nf = java.text.NumberFormat
							.getInstance();
					nf.setGroupingUsed(false);
					doubleString = nf.format(obj);

				}
				res = doubleString;
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static Float getFloat(Object obj) {
		Float res = 0.0f;
		if (obj != null) {
			String floatString = obj.toString();
			if ("".equals(floatString)) {
				return res;
			}
			try {
				res = Float.parseFloat(floatString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Integer getInteger(Object obj) {
		Integer res = 0;
		if (obj != null) {
			try {
				String objStr = obj + "";
				if (objStr.indexOf(".") != -1) {
					objStr = objStr.subSequence(0, objStr.indexOf(".")) + "";
				}
				res = Integer.valueOf(objStr);
			} catch (Exception e) {
			}
		}

		return res;
	}

	public static Long getLong(Object obj) {
		Long res = 0L;
		if (obj != null) {
			try {
				res = Long.valueOf(obj + "");
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static String getString(Object obj) {
		String res = "";
		if (obj != null) {
			try {
				res = obj + "";
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static SpannableString getSpannableString(Object obj) {
		SpannableString res = null;
		if (obj != null) {
			try {
				res = (SpannableString) obj;
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static String getTimeString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = timeFormat.format(obj);
				} else {

					Date d = new Date(Date.parse(date));
					date = timeFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static String getShortDateString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = shortDateFormat.format(obj);
				} else {
					Date d = new Date(Date.parse(date));
					date = shortDateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static String getShortDateString2(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = shortDateFormat2.format(obj);
				} else {
					Date d = new Date(Date.parse(date));
					date = shortDateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static String getDateString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = dateFormat.format(obj);
				} else {
					date = date.replace('T', ' ');
					Date d = new Date(Date.parse(date));
					date = dateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static Date getDate1(Object obj) {
		Date res = new Date();
		if (obj != null) {
			try {
				if (obj instanceof Date)
					return (Date) obj;
				String dateString = getString(obj);
				dateString = dateString.replace('T', ' ');
				res = dateFormat.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Date getShortDate(Object obj) {
		Date res = new Date();
		if (obj != null) {
			try {
				if (obj instanceof Date)
					return (Date) obj;
				String dateString = getString(obj);
				dateString = dateString.replace('T', ' ');
				res = shortDateFormat.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Boolean getBoolean(Object obj) {
		Boolean res = false;
		if (obj != null) {
			try {
				res = Boolean.parseBoolean(obj.toString());
			} catch (Exception e) {

			}
		}
		return res;
	}

	public static Boolean IsEmpty(String v) {
		boolean flag = true;
		if (v != null && !"".equals(v)) {
			flag = false;
		}
		return flag;
	}
}
