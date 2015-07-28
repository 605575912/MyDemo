package com.lzxmy.demo.drawwelcome;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Text {

	private int mScreenWidth;
	private int mTopMargin;
	private int[] mTextIds = new int[] { R.drawable.leading_text_1,
			R.drawable.leading_text_2, R.drawable.leading_text_3,
			R.drawable.leading_text_4, R.drawable.leading_text_5 };
	private List<Bitmap> mTexts = new ArrayList<Bitmap>();
	private int mCurX;

	public Text(Context context, int width, int height) {
		mScreenWidth = width;
		mTopMargin = context.getResources().getDimensionPixelOffset(
				R.dimen.cloud_margin_top);
		for (int i = 0; i < mTextIds.length; i++) {
			mTexts.add(BitmapFactory.decodeResource(context.getResources(),
					mTextIds[i]));
		}
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);
		for (int i = 0; i < mTexts.size(); i++) {
			Bitmap bitmap = mTexts.get(i);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Rect srcRect = new Rect(0, 0, width, height);
			int left = 0;
			int right = 0;
			int alpha = 0;
			switch (i) {
			case 0:
				left = mScreenWidth - width / 2;
				right = mScreenWidth - width / 2 + width;
				alpha = (255 * mCurX) / (mScreenWidth / 2);
				alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
				break;

			case 1:
				left = mScreenWidth * 2 - width / 2;
				right = mScreenWidth * 2 - width / 2 + width;
				if (mCurX < mScreenWidth) {
					alpha = 0;
				} else {
					alpha = (255 * (mCurX - mScreenWidth)) / (mScreenWidth / 2);
					alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
				}
				break;

			case 2:
				left = (int) (mScreenWidth * 3.5 - width / 2);
				right = (int) (mScreenWidth * 3.5 - width / 2 + width);
				if (mCurX < mScreenWidth * 2.5) {
					alpha = 0;
				} else {
					alpha = (255 * (mCurX - (int) (mScreenWidth * 2.5)))
							/ (mScreenWidth / 2);
					alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
				}
				break;

			case 3:
				left = (int) (mScreenWidth * 4.5);
				right = (int) (mScreenWidth * 4.5 + width);
				if (mCurX < mScreenWidth * 4) {
					alpha = 0;
				} else {
					alpha = (255 * (mCurX - (int) (mScreenWidth * 4)))
							/ (mScreenWidth / 2);
					alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
				}
				break;

			case 4:
				left = mScreenWidth * 6 + (mScreenWidth - width) / 2;
				right = mScreenWidth * 6 + (mScreenWidth - width) / 2 + width;
				if (mCurX < mScreenWidth * 5.5) {
					alpha = 0;
				} else {
					alpha = (255 * (mCurX - (int) (mScreenWidth * 5.5)))
							/ (mScreenWidth / 2);
					alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
				}
				break;
			}
			Rect dstRect = new Rect(left, mTopMargin, right, mTopMargin
					+ height);
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setAlpha(alpha);
			canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
		}
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
