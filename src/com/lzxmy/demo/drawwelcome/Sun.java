package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Sun {

	private Bitmap mSun;
	private int mWidth;
	private int mHeight;
	private int mMargin;
	private int mXPoint;
	private int mYPoint;
	private int mDegrees;

	public Sun(Context context, int width) {
		mSun = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.sun);
		mWidth = mSun.getWidth();
		mHeight = mSun.getHeight();
		mMargin = context.getResources().getDimensionPixelSize(
				R.dimen.sun_margin);
		mXPoint = width - mWidth - mMargin;
		mYPoint = mMargin;
	}

	public void draw(Canvas canvas) {
		canvas.translate(mXPoint, mYPoint);
		canvas.rotate(-((mDegrees += 4) % 360), mWidth / 2, mHeight / 2);
		canvas.drawBitmap(mSun, 0, 0, null);
	}
}
