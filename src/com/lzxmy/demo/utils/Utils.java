package com.lzxmy.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static final int LISTVIEW_DATA_MORE = 0x001;
	public static final int LISTVIEW_ACTION_INIT = 0x002;
	public static final int LISTVIEW_ACTION_REFRESH = 0x003;
	public static final int LISTVIEW_ACTION_CHANGE_CATALOG = 0x004;
	public static final int LISTVIEW_ACTION_SCROLL = 0x005;
	public static final int LISTVIEW_DATA_EMPTY = 0x006;
	public static final int LISTVIEW_DATA_FULL = 0x007;
	public static int HANDLERCODE = -10;

	// 获取ApiKey
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {

		}
		return apiKey;
	}

	// 用share preference来实现是否绑定的开关。在ionBind且成功时设置true，unBind且成功时设置false
	public static boolean hasBind(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		String flag = sp.getString("bind_flag", "");
		if ("ok".equalsIgnoreCase(flag)) {
			return true;
		}
		return false;
	}

	public static void setBind(Context context, boolean flag) {
		String flagStr = "not";
		if (flag) {
			flagStr = "ok";
		}
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("bind_flag", flagStr);
		editor.commit();
	}

	public static List<String> getTagsList(String originalText) {
		if (originalText == null || originalText.equals("")) {
			return null;
		}
		List<String> tags = new ArrayList<String>();
		int indexOfComma = originalText.indexOf(',');
		String tag;
		while (indexOfComma != -1) {
			tag = originalText.substring(0, indexOfComma);
			tags.add(tag);

			originalText = originalText.substring(indexOfComma + 1);
			indexOfComma = originalText.indexOf(',');
		}

		tags.add(originalText);
		return tags;
	}

	public static String getLogText(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString("log_text", "");
	}

	/**
	 * 带动画的页面跳转
	 * 
	 * @param intent
	 * @param activity
	 * @param cl
	 */
	public static void StartAnimActivity(Intent intent, Activity activity,
			Class<? extends Activity> cl) {
		intent.setClass(activity, cl);
		activity.startActivity(intent);
	}

	/**
	 * 带动画的页面跳转
	 * 
	 * @param intent
	 * @param activity
	 * @param cl
	 */
	public static void StartAnimActivity(Intent intent, Activity activity,
			Class<? extends Activity> cl, int requestCode) {
		intent.setClass(activity, cl);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void setLogText(Context context, String text) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("log_text", text);
		editor.commit();
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
				return true;
			}
		}
		return false;
	}

	/**
	 * 计算字宽
	 */

	public static float GetTextWidth(String text, float Size) {
		TextPaint FontPaint = new TextPaint();
		FontPaint.setTextSize(Size);
		return FontPaint.measureText(text);
	}

	/**
	 * 检测字符串中是否包含汉字
	 * 
	 * @param sequence
	 * @return
	 */
	public static boolean checkChinese(String sequence) {
		final String format = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
		boolean result = false;
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(sequence);
		result = matcher.find();
		return result;
	}

	/**
	 * 检测字符串中只能包含：中文、数字、下划线(_)、横线(-)
	 */
	public static boolean checkNickname(String sequence) {
		final String format = "[^\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-_]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(sequence);
		return !matcher.find();
	}

	/**
	 * 验证合法E-mail账号
	 * 
	 * @param account
	 *            输入邮箱账号
	 * @return
	 */
	public static boolean isValidEmailAccount(String account) {
		String PATTERN_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return account.matches(PATTERN_EMAIL);
	}

	/**
	 * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytes2kb(long bytes) {
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		return (returnValue + "KB");
	}

	/**
	 * 使用TransitionDrawable实现渐变效果
	 * 
	 * @param imageView
	 * @param bitmap
	 */
	private void setImageBitmap(Context context, ImageView imageView,
			Bitmap bitmap) {
		// Use TransitionDrawable to fade in.
		final TransitionDrawable td = new TransitionDrawable(new Drawable[] {
				new ColorDrawable(android.R.color.transparent),
				new BitmapDrawable(context.getResources(), bitmap) });
		// noinspection deprecation
		imageView.setBackgroundDrawable(imageView.getDrawable());
		imageView.setImageDrawable(td);
		td.startTransition(200);
	}

	/**
	 * 解压一个压缩文档 到指定位置
	 * 
	 * @param zipFileString
	 *            压缩包的名字
	 * @param outPathString
	 *            指定的路径 [url=home.php?mod=space&uid=2643633]@throws[/url]
	 *            Exception
	 */
	public static void UnZipFolder(String zipFileString, String outPathString)
			throws Exception {
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(
				new java.io.FileInputStream(zipFileString));
		java.util.zip.ZipEntry zipEntry;
		String szName = "";

		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();

			if (zipEntry.isDirectory()) {

				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				java.io.File folder = new java.io.File(outPathString
						+ java.io.File.separator + szName);
				folder.mkdirs();

			} else {

				java.io.File file = new java.io.File(outPathString
						+ java.io.File.separator + szName);
				file.createNewFile();
				// get the output stream of the file
				java.io.FileOutputStream out = new java.io.FileOutputStream(
						file);
				int len;
				byte[] buffer = new byte[1024];
				// read (len) bytes into buffer
				while ((len = inZip.read(buffer)) != -1) {
					// write (len) byte from buffer at the position 0
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}// end of while

		inZip.close();

	}// end of func

	/**
	 * 展开、收起状态栏
	 * 
	 * @param ctx
	 */

	public static final void collapseStatusBar(Context ctx) {
		Object sbservice = ctx.getSystemService("statusbar");
		try {
			Class<?> statusBarManager = Class
					.forName("android.app.StatusBarManager");
			Method collapse;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
				collapse = statusBarManager.getMethod("collapsePanels");
			} else {
				collapse = statusBarManager.getMethod("collapse");
			}
			collapse.invoke(sbservice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 展开、收起状态栏
	 * 
	 * @param ctx
	 */
	public static final void expandStatusBar(Context ctx) {
		Object sbservice = ctx.getSystemService("statusbar");
		try {
			Class<?> statusBarManager = Class
					.forName("android.app.StatusBarManager");
			Method expand;
			if (Build.VERSION.SDK_INT >= 17) {
				expand = statusBarManager.getMethod("expandNotificationsPanel");
			} else {
				expand = statusBarManager.getMethod("expand");
			}
			expand.invoke(sbservice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ListView使用ViewHolder极简写法
	 * 
	 * @Override public View getView(int position, View convertView, ViewGroup
	 *           parent) { if (convertView == null) { convertView =
	 *           LayoutInflater
	 *           .from(getActivity()).inflate(R.layout.fragment_feed_item,
	 *           parent, false); }
	 * 
	 *           ImageView thumnailView = getAdapterView(convertView,
	 *           R.id.video_thumbnail); ImageView avatarView =
	 *           getAdapterView(convertView, R.id.user_avatar); ImageView
	 *           appIconView = getAdapterView(convertView, R.id.app_icon);
	 * @param convertView
	 * @param id
	 * @return
	 */
	public static <T extends View> T getAdapterView(View convertView, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			convertView.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = convertView.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}

	/**
	 * 调用开发者选项中显示触摸位置功能
	 * android.provider.Settings.System.putInt(getContentResolver(),
	 * "show_touches", 1); 设置1显示，设置0不显示。
	 */




//	获取相册照片的路径地址之前是这么写的

//	String getPath(Intent picdata) {
//		Cursor cursor = getContentResolver().query(uri, proj, null, null,
//				null);
//		int column_index = cursor
//				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//		cursor.moveToFirst();
//		String temp = cursor.getString(column_index);
//		if (cursor != null) {
//			cursor.close();
//			cursor = null;
//		}
//		return temp;
//	}

//	但是这个方法在4.2.2系统之后的小米手机上不能用 报空指针，改进方法如下：
//	本人亲测
	String getPath(Intent picdata,Context context) {
		Uri uri = picdata.getData();
		String[] proj = { MediaStore.Images.Media.DATA };
		if ("content".equals(uri.getScheme())) {
			Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
					null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String temp = cursor.getString(column_index);
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
			return temp;
		} else {
			return uri.getPath();
		}
	}
}
