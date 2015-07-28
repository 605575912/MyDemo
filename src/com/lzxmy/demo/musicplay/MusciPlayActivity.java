package com.lzxmy.demo.musicplay;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MusciPlayActivity extends Activity {
	TextView textview;
	PowerManager powerManager = null;
	WakeLock wakeLock = null;
	ImageView imageView1;
	String string = "[以下文字是我对你的心声]猪莉华师大附近善良劫匪阿拉[此处是错别字]斯加东方丽景阿萨德路附近阿里山的减肥操蛋";
static	int[] im = new int[] { R.drawable.m01, R.drawable.m01, R.drawable.m02,
			R.drawable.icon01, R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3 };
	Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			// bt_locateAnimation(textview);
			if (msg.what == 0) {
//				int sd = im[msg.arg1];
//				ret.setBackgroundDrawable(getResources().getDrawable(sd));
//				Message msg0 = handler.obtainMessage();
//				int i = getRandom(0, im.length - 1);
//				Log.i("TAG", i+"=======");
//				msg0.arg1 = i;
//				msg0.what = 0;
//				handler.sendMessageDelayed(msg0, 5000);
			} else {
				textview.setText((CharSequence) msg.obj);
			}
		};
	};

	public int getRandom(int min, int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicplay);
		textview = (TextView) findViewById(R.id.textview);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		 imageView1.setImageResource(R.anim.h_loading);
		 AnimationDrawable drawable = (AnimationDrawable) imageView1
		 .getDrawable();
		 drawable.start();
//		 AnimUtils.setAnimation(getApplicationContext(), imageView1,
//		 R.anim.slide_down_out);
		
		this.powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		this.wakeLock = this.powerManager.newWakeLock(
				PowerManager.FULL_WAKE_LOCK, "My Lock");
//		Message msg = handler.obtainMessage();
//		msg.arg1 = 0;
//		msg.what = 0;
//		handler.sendMessageDelayed(msg, 2000);
		new Thread(new Startplay(getApplicationContext())).start();
		new Thread() {
			@Override
			public void run() {
				StringBuffer stringBuffer = new StringBuffer();
				int start = -1;
				int end = -1;
				int isadd = 0;
				for (int i = 0; i < string.length();) {

					end = stringBuffer.lastIndexOf("]");
					if (end >= 0) {
						start = stringBuffer.lastIndexOf("[");
					}
					if (start >= 0) {
						int del = stringBuffer.length() - 1;
						if (del >= start) {
							stringBuffer.deleteCharAt(del);
						} else {
							start = -1;
						}

					} else {

						if (isadd == 0) {
							stringBuffer.append(string.charAt(i));
							i++;
						}

					}
					Message msg = handler.obtainMessage();
					msg.what = 1;
					String sore = stringBuffer.toString();
					if (isadd == 2) {
						isadd = 0;
					} else {
						if (isadd == 1) {
							sore = sore + "|";
						}
						isadd++;
					}
					sore = sore.replaceAll("\\[", "");
					sore = sore.replaceAll("\\]", "");
					msg.obj = sore;
					handler.sendMessage(msg);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			};
		}.start();

	}

	/**
	 * ���򶯻�
	 */
	// void bt_locateAnimation(View view) {
	// Animation animation = AnimationUtils.loadAnimation(view.getContext(),
	// R.anim.slide_down_out);
	// view.startAnimation(animation);
	// animation.setAnimationListener(new AnimationListener() {
	//
	// @Override
	// public void onAnimationStart(Animation arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onAnimationRepeat(Animation arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onAnimationEnd(Animation arg0) {
	// // TODO Auto-generated method stub
	// handler.sendEmptyMessageDelayed(0, 0);
	// }
	// });
	// // RotateAnimation
	// // }
	//
	// }
	/**
	 * ���¼����Ϸ��ذ�ť�˳�����
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// ����
			finish();
			System.exit(0);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
