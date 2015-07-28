package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Boy {

	private int mScreenWidth;
	private int mScreenHeight;
	private int mWidth;
	private int mHeight;
	private int mCarWidth;
	private int mStreetHeight;
	private Bitmap mBoys;
	private int mCurX;

	public Boy(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		mBoys = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.boy_w380);
		mCarWidth = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car1).getWidth();
		mStreetHeight = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.street).getHeight();
	}

	public void draw(Canvas canvas) {
		mWidth = mBoys.getWidth() / 5;
		mHeight = mBoys.getHeight();
		Rect srcRect = null;
		int step = mScreenWidth / 10;
		if (mCurX < mScreenWidth * 1.5) {
			int curStep = mCurX / step;
			if (curStep % 2 == 0) {
				srcRect = new Rect(mWidth, 0, mWidth * 2, mHeight);// 第一个状态
			} else {
				srcRect = new Rect(0, 0, mWidth, mHeight);// 第二个状态
			}

		} else if (mCurX < mScreenWidth * 4.5 - mCarWidth / 3 * 2) {
			int curStep = (int) ((mCurX - mScreenWidth * 1.5) / step);
			if (curStep % 2 == 0) {
				srcRect = new Rect(mWidth * 3, 0, mWidth * 4, mHeight);// 第三个状态
			} else {
				srcRect = new Rect(mWidth * 2, 0, mWidth * 3, mHeight);// 第四个状态
			}
		} else if (mCurX < mScreenWidth * 4.5) {
			srcRect = new Rect(mWidth * 4, 0, mWidth * 5, mHeight);// 第五个状态
		} else {
			int curStep = (int) ((mCurX - mScreenWidth * 4.5) / step);
			if (curStep % 2 == 0) {
				srcRect = new Rect(mWidth, 0, mWidth * 2, mHeight);// 第一个状态
			} else {
				srcRect = new Rect(0, 0, mWidth, mHeight);// 第二个状态
			}
		}
		Rect dstRect = new Rect(mScreenWidth / 2 - mWidth / 2, mScreenHeight
				- mStreetHeight - mHeight, mScreenWidth / 2 + mWidth / 2,
				mScreenHeight - mStreetHeight);
		
		int alpha = 0;
		if (mCurX > mScreenWidth * 5.5) {
			alpha = (255 * (mCurX - (int) (mScreenWidth * 5.5)))
					/ (mScreenWidth / 2);
			alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
			alpha = 255 - alpha;
		} else {
			alpha = 255;
		}
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAlpha(alpha);
		canvas.drawBitmap(mBoys, srcRect, dstRect, paint);
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
