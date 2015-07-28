package com.lzxmy.demo.wireframe;


import com.lzxmy.demo.R;
import com.lzxmy.demo.wireframe.RotateAnimation.InterpolatedTimeListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2012-6-27 上午07:32:00
 */
public class ActRotate extends Activity implements OnClickListener, InterpolatedTimeListener {
	private Button btnIncrease, btnDecrease;
	private TextView txtNumber;
	private int number;
	/** TextNumber是否允许显示最新的数字。 */
	private boolean enableRefresh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roate_layout);

		btnIncrease = (Button) findViewById(R.id.button1);
		btnDecrease = (Button) findViewById(R.id.button2);
		txtNumber = (TextView) findViewById(R.id.textView1);

		btnIncrease.setOnClickListener(this);
		btnDecrease.setOnClickListener(this);

		number = 3;
		txtNumber = (TextView) findViewById(R.id.textView1);
		txtNumber.setText(Integer.toString(number));
	}

	@Override
	public void onClick(View v) {
		enableRefresh = true;
		RotateAnimation rotateAnim = null;
		float cX = txtNumber.getWidth() / 2.0f;
		float cY = txtNumber.getHeight() / 2.0f;
//		if (v == btnDecrease) {
//			number--;
//			rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
//		} else if (v == btnIncrease) {
//			number++;
			rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_INCREASE);
//		}
		if (rotateAnim != null) {
//			rotateAnim.setInterpolatedTimeListener(this);
//			rotateAnim.setFillAfter(true);
			txtNumber.startAnimation(rotateAnim);
		}
	}

	@Override
	public void interpolatedTime(float interpolatedTime) {
		// 监听到翻转进度过半时，更新txtNumber显示内容。
//		if (enableRefresh && interpolatedTime > 0.5f) {
//			txtNumber.setText(Integer.toString(number));
//			Log.d("ANDROID_LAB", "setNumber:" + number);
//			enableRefresh = false;
//		}
	}
}