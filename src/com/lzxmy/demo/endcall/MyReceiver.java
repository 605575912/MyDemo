package com.lzxmy.demo.endcall;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 从intent意图中提取行为action，判断是不是开机启动广播
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent dynamicRegisterServiceIntent = new Intent(context,
					DynamicRegisterService.class);
			context.startService(dynamicRegisterServiceIntent);
		} else {
			// 其它的行为算作有可能是呼叫行为，放在一个新的服务里面进行处理
			Intent endCallIntent = new Intent(context, EndCallService.class);
			context.startService(endCallIntent);
		}

	}

}
