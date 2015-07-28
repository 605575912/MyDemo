package com.lzxmy.demo.utils;

import java.util.Stack;


import android.app.Activity;
import android.content.Context;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * 
 * @author lzx
 * @version 1.0
 * @created 2012-6-21
 */
public class AppManager {

	public static Stack<Activity> activityStack;
	public static Stack<Activity> baseactivityStack;
	private static AppManager instance;

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
			if (activityStack == null) {
				activityStack = new Stack<Activity>();
			}
			if (baseactivityStack == null) {
				baseactivityStack = new Stack<Activity>();
			}
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 添加BaseActivity到堆栈
	 */
	public void addbaseActivity(Activity activity) {
		if (baseactivityStack == null) {
			baseactivityStack = new Stack<Activity>();
		}
		baseactivityStack.add(activity);
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		if (activity != null) {
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public boolean indexOf(Activity activity) {
		if (activity != null) {
			if (activityStack.indexOf(activity) > -1) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			baseactivityStack.remove(activity);
			activity.finish();
//			activity.overridePendingTransition(R.anim.zoom_nor, R.anim.zoom_in);
			activity = null;
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if (activityStack!=null) {
			if (activityStack.size() > 0) {
				for (Activity activity : activityStack) {
					if (null != activity) {
						activity.finish();
//						activity.overridePendingTransition(R.anim.zoom_nor, R.anim.zoom_in);
					}
				}
				activityStack.clear();
			}
		}
	

	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllBaseActivity() {
		if(baseactivityStack!=null)
		if (baseactivityStack.size() > 0) {
			for (Activity activity : baseactivityStack) {
				if (null != activity) {
					finishActivity(activity);
				}
			}
			baseactivityStack.clear();
		}

	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(final Context context) {
		try {
			new Thread() {
				@Override
				public void run() {
//					if (context != null) {
//						NotificationManager mNotificationManager = (NotificationManager) context
//								.getSystemService(Context.NOTIFICATION_SERVICE);
//						mNotificationManager.cancelAll();
//						Intent intent = new Intent();
//						intent.setClass(context, XMPPServer.class);
//						context.stopService(intent);
//					}
					finishAllActivity();
					finishAllBaseActivity();
					
				};
			}.start();

		} catch (Exception e) {
		}
	}
}