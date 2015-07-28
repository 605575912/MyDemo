package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Street {

	private int mScreenWidth;
	private int mScreenHeight;
	private Bitmap mStreet;
	private int mWidth;
	private int mHeight;
	private int mCurX;

	public Street(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		mStreet = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.street);
		mWidth = mStreet.getWidth();
		mHeight = mStreet.getHeight();
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);
		int count = (mScreenWidth * 7) % mWidth == 0 ? (mScreenWidth * 7)
				/ mWidth : (mScreenWidth * 7) / mWidth + 1;
		for (int i = 0; i < count; i++) {
			Rect rect = new Rect(i * mWidth, mScreenHeight - mHeight, (i + 1)
					* mWidth, mScreenHeight);
			canvas.drawBitmap(mStreet, rect.left, rect.top, null);
		}
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
