package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Cloud {

	private int mScreenWidht;
	private int mScreenHeight;
	private Bitmap mCloud;
	private int mWidth;
	private int mHeight;
	private int mTopMargin;
	private int mMargin;
	private int mCurX;

	public Cloud(Context context, int width, int height) {
		mScreenWidht = width;
		mScreenHeight = height;
		mCloud = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.cloud);
		mWidth = mCloud.getWidth();
		mHeight = mCloud.getHeight();
		mTopMargin = context.getResources().getDimensionPixelOffset(
				R.dimen.cloud_margin_top);
		mMargin = context.getResources().getDimensionPixelOffset(
				R.dimen.sun_margin) / 2;
	}

	public void draw(Canvas canvas) {
		canvas.translate(-(mCurX / 12), 0);
		Rect srcRect = new Rect(0, 0, mWidth, mHeight);
		Rect dstOneRect = new Rect(50, mScreenHeight / 3, mWidth + 50,
				mScreenHeight / 3 + mHeight);
		canvas.drawBitmap(mCloud, srcRect, dstOneRect, null);

		int width = (int) (0.7 * mWidth);
		int height = (int) (0.7 * mHeight);
		Rect dstTwoRect = new Rect(mScreenWidht - width - mMargin, mTopMargin,
				mScreenWidht - 20, height + mTopMargin);
		canvas.drawBitmap(mCloud, srcRect, dstTwoRect, null);
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
