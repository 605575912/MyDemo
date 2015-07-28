package com.lzxmy.demo.drawwelcome;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Car {

	private int mScreenWidth;
	private int mScreenHeight;
	private int[] mCarIds = new int[] { R.drawable.car1, R.drawable.car2,
			R.drawable.car3, R.drawable.car4, R.drawable.car1 };
	private List<Bitmap> mCars = new ArrayList<Bitmap>();
	private int mStreetHeight;
	private int mCurX;

	public Car(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;
		for (int i = 0; i < mCarIds.length; i++) {
			mCars.add(BitmapFactory.decodeResource(context.getResources(),
					mCarIds[i]));
		}
		mStreetHeight = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.street).getHeight();
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);
		for (int i = 0; i < mCars.size(); i++) {
			Bitmap bitmap = mCars.get(i);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Rect srcRect = new Rect(0, 0, width, height);
			int left = 0;
			int right = 0;
			switch (i) {
			case 0:
				left = mScreenWidth - width / 2;
				right = mScreenWidth + width / 2;
				break;

			case 1:
				left = mScreenWidth * 2 - width / 2;
				right = mScreenWidth * 2 + width / 2;
				break;

			case 2:
				left = (int) (mScreenWidth * 3.5 - width);
				right = (int) (mScreenWidth * 3.5);
				break;

			case 3:
				left = mScreenWidth * 4;
				right = mScreenWidth * 4 + width;
				break;

			case 4:
				left = mScreenWidth * 5 - width / 2;
				right = mScreenWidth * 5 + width / 2;
				break;
			}
			Rect dstRect = new Rect(left, mScreenHeight - mStreetHeight / 2
					- height, right, mScreenHeight - mStreetHeight / 2);
			canvas.drawBitmap(bitmap, srcRect, dstRect, null);
		}
	}

	public void setCurX(int x) {
		mCurX = x;
	}
}
