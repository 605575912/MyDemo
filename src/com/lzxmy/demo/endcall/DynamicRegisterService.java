package com.lzxmy.demo.endcall;


import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

//动态注册服务
public class DynamicRegisterService extends Service {
    private Intent intent;
	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	public void onCreate() {
		super.onCreate();

	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		//广播接收器的类的对象
		MyReceiver myReceiver = new MyReceiver();
		//意图过滤的对象，并且设置属性
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.setPriority(2147483647);
		intentFilter.addAction("android.intent.action.PHONE_STATE");
        //动态注册广播接收器
		this.registerReceiver(myReceiver, intentFilter);
		//给全局的intent变量赋值
		this.intent=intent;
	}

	public void onDestroy() {
		super.onDestroy();
        //当流氓霸道的软件想要杀死我们的服务时，我们就重新的启动服务
		
		this.startService(intent);
	}
}
