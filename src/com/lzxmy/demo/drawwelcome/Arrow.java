package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Arrow {
	private int mScreenWidth;
	private int mScreenHeight;
	private Bitmap[] mArrows;

	private boolean mIsDissmiss = false;

	private int mMargin;
	private int mPosition = 0;
	private int mCount;

	public Arrow(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		mArrows = new Bitmap[] {
				BitmapFactory.decodeResource(context.getResources(),
						R.drawable.arrow1),
				BitmapFactory.decodeResource(context.getResources(),
						R.drawable.arrow2) };
		mMargin = context.getResources().getDimensionPixelSize(
				R.dimen.arrow_margin_right);
	}

	public void draw(Canvas canvas) {
		if (mIsDissmiss) {
			return;
		}
		mCount++;
		if (mCount == 12) {
			mCount = 0;
			mPosition = mPosition == 0 ? 1 : 0;
		}
		Bitmap arrow = mArrows[mPosition];
		int left = mScreenWidth - arrow.getWidth() / 4 - mMargin;
		int top = mScreenHeight / 3 * 2 - arrow.getHeight() / 4;
		int right = left + arrow.getWidth() / 2;
		int bottom = top + arrow.getHeight() / 2;
		canvas.drawBitmap(arrow,
				new Rect(0, 0, arrow.getWidth(), arrow.getHeight()), new Rect(
						left, top, right, bottom), null);
	}

	public void dismiss() {
		mIsDissmiss = true;
	}
}
