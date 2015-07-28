package com.lzxmy.demo.endcall;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class EndCallService extends Service {

	private Intent intent;
	private TelephonyManager telephonyManager;
	private AudioManager     audioManager;
	private ITelephony iTelephony;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	public void onCreate() {
		super.onCreate();
	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		this.intent = intent;

		telephonyManager = (TelephonyManager) this.getSystemService(this
				.getApplicationContext().TELEPHONY_SERVICE);
        audioManager=(AudioManager) this.getSystemService(this.getApplicationContext().AUDIO_SERVICE);
		telephonyManager.listen(new MyPhoneStateListener(),
				PhoneStateListener.LISTEN_CALL_STATE);

	}

	public void onDestroy() {
		super.onDestroy();
		this.startService(intent);
	}

	// 创建一个内部类
	private class MyPhoneStateListener extends PhoneStateListener {

		public void onCallStateChanged(int state, String incomingNumber) {

			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:

				try {
					// 挂断电话的核心代码
					Class<TelephonyManager> telephonyManagerClass = TelephonyManager.class;
					Method getITelephonyMethod;
					getITelephonyMethod = telephonyManagerClass
							.getDeclaredMethod("getITelephony", (Class[]) null);

					getITelephonyMethod.setAccessible(true);
					iTelephony = (ITelephony) getITelephonyMethod.invoke(
							telephonyManager, (Object[]) null);
					if (incomingNumber.equals("1234567890")) {
                        //设置为静音
						audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
						// 挂断电话
						iTelephony.endCall();
                        //弹出来一个提示 
						Toast.makeText(EndCallService.this, "拦截成功", Toast.LENGTH_LONG).show();
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}

	}

	// ------END-----------------------------------------------------------
}
