package com.lzxmy.demo.drawwelcome;
import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {

	private int mScreenWidth;
	private int mScreenHeight;
	private Bitmap mBackground;
	private int mWidth;
	private int mHeight;
	private int mCurX;
	Rect srcRect ;
	public Background(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		mBackground = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.background);
		mWidth = mBackground.getWidth();
		mHeight = mBackground.getHeight();
		 srcRect = new Rect(0, 0, mWidth, mHeight);
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);
		
		for (int i = 0; i < 2; i++) {
			Rect dstRect = new Rect(i * mScreenWidth, mScreenHeight - mHeight,
					mScreenWidth * (i + 1), mScreenHeight);
			canvas.drawBitmap(mBackground, srcRect, dstRect, null);
		}
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
