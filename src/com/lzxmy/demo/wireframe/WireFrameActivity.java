package com.lzxmy.demo.wireframe;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;


import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.app.Activity;
import android.content.Context;

public class WireFrameActivity extends Activity {
	PowerManager powerManager = null;
	WakeLock wakeLock = null;
	LinearLayout arc;
    RelativeLayout pillars,linear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wireframe_layout);

		arc = (LinearLayout) findViewById(R.id.arc);//圆弧计分
//		arc.addView(new HomeArc(this, 90)); 
		arc.addView(new ImageSurfaceView(this)); //反转
		List<Score> list = new ArrayList<Score>();;//柱状图  范围10-100
		for (int i = 0; i < 28; i++) {
			Score s = new Score();
			s.date = "2013-10-" + i;
			s.score = getRandom(10,100);
			list.add(s);
		}
		pillars= (RelativeLayout) findViewById(R.id.pillars);
		pillars.addView(new HomeColumnar(this,list));
		
		List<Integer> lists = new ArrayList<Integer>();//线性图  范围10-100
		for (int i = 0; i < 48; i++) {
			if (i < 8 || i == 28 || i == 12 || i == 18 || i == 20 || i == 30
					|| i == 34) {
				lists.add(0);
			} else {
				lists.add(getRandom(0, 500));
			}
		}
		linear= (RelativeLayout) findViewById(R.id.linear);
		linear.addView(new HomeDiagram(this,lists));
		this.powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		this.wakeLock = this.powerManager.newWakeLock(
				PowerManager.FULL_WAKE_LOCK, "My Lock");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try {
			this.wakeLock.acquire();
		} catch (Exception e) {
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			if (wakeLock != null) {
				this.wakeLock.release();
			}
		} catch (Exception e) {
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (this.wakeLock != null) {
			if (this.wakeLock.isHeld()) {
				this.wakeLock.release();
			}
		}
		super.onDestroy();
	}
	public int getRandom(int min,int max){
		return (int) Math.round(Math.random()*(max-min)+min);
	}

}
