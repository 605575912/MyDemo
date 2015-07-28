package com.lzxmy.demo.utils;

/**
 * 验证常用基本信息是否合法工具类
 * 
 * 身份证、手机号码、邮箱
 * 
 * @author frank
 * 
 */
public class ValidateUtil {

	/**
	 * 判别手机是否为正确手机号码； 号码段分配如下：
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
	 * 
	 * "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"
	 */
	// public static final String PATTERN_MOBILE_PHONE =
	// "^\\d{11,15}|\\+\\d{13,19}$";
	public static final String PATTERN_MOBILE_PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 电子邮箱 E-mail
	 */
	// public static final String PATTERN_EMAIL =
	// "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	public static final String PATTERN_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 验证合法手机号码
	 * 
	 * @param number
	 *            输入手机号码
	 * @return
	 */
	public static boolean isValidMobilePhoneNumber(String number) {
		if (isNullOrEmpty(number)) {
			return false;
		}

		return number.matches(PATTERN_MOBILE_PHONE);
	}

	/**
	 * 验证合法E-mail账号
	 * 
	 * @param account
	 *            输入邮箱账号
	 * @return
	 */
	public static boolean isValidEmailAccount(String account) {
		if (isNullOrEmpty(account)) {
			return false;
		}

		return account.matches(PATTERN_EMAIL);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	private static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().length() < 1;
	}

}
