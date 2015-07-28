package com.lzxmy.demo.drawwelcome;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Build {

	private int mScreenWidth;
	private int mScreenHeight;
	private int mStreetHeight;
	private int mCarWidth;
	private int mCurX;
	private int[] mBuildIds = new int[] { R.drawable.build1, R.drawable.build2,
			R.drawable.build3, R.drawable.build4 };
	private List<Bitmap> mBuilds = new ArrayList<Bitmap>();

	public Build(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		for (int i = 0; i < mBuildIds.length; i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), mBuildIds[i]);
			mBuilds.add(bitmap);
		}
		mStreetHeight = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.street).getHeight();
		mCarWidth = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car1).getWidth();
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);
		for (int i = 0; i < mBuilds.size(); i++) {
			Bitmap bitmap = mBuilds.get(i);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Rect srcRect = new Rect(0, 0, width, height);
			int left = 0;
			int right = 0;
			switch (i) {
			case 0:
				left = 0;
				right = mScreenWidth - mCarWidth / 2;
				break;
			case 1:
				left = (int) (mScreenWidth * 1.5);
				right = (int) (mScreenWidth * 1.5 + width);
				break;

			case 2:
				left = mScreenWidth * 3;
				right = mScreenWidth * 3 + width;
				break;

			case 3:
				left = mScreenWidth * 5;
				right = mScreenWidth * 5 + width;
				break;
			}
			Rect dstRect = new Rect(left, mScreenHeight - mStreetHeight
					- height, right, mScreenHeight - mStreetHeight);
			canvas.drawBitmap(bitmap, srcRect, dstRect, null);
		}
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
