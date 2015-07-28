package com.lzxmy.demo;


import com.nostra13.universalimageloader.ServiceLoader;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class XswApplication extends Application {

	public static XswApplication app;
	static String phone = "";
	static String password = "";
	// static String phone = "17003073073";
	// static String password = "123456";

	public static String versionName = "";

	public XswApplication() {
		super();
		app = this;
	}

	public static XswApplication getInstance() {
		if (app == null) {
			app = new XswApplication();
		}
		return app;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		ServiceLoader.getInstance().Init(app);
		// CatchHandler.getInstance().init(app);

	}



	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
