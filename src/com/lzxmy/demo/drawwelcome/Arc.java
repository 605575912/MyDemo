package com.lzxmy.demo.drawwelcome;


import com.lzxmy.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class Arc {

	private int mScreenWidth;
	private int mScreenHeight;
	private int mCurX;

	private Bitmap mThumb;
	private Bitmap mSeekBg;
	private Bitmap mProgressBg;
	private Bitmap mEye;
	private Bitmap mFoot;

	private int mStreetHeight;

	private int mArcRadius;
	private int mArcOffset;
	private int mFootToMouthOffset;

	private float mOverlayStartAngle = 115.0F;

	private RectF mRountRectF;
	private Rect mFaceRect;
	private Rect mThumbRect;
	private float mAngle = -150.0F;
	private float mAnglePerX;

	private int mThumbStartX;
	private int mThumbEndX;

	private boolean mIsBack = false;
	private boolean mIsGo = false;

	private OnGoListener mOnGoListener;

	public Arc(Context context, int width, int height) {
		mScreenWidth = width;
		mScreenHeight = height;

		mThumb = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.thumb);
		mSeekBg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.seekbg);
		mProgressBg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.progressbg);
		mEye = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.eye);
		mFoot = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.foot);

		mStreetHeight = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.street).getHeight();

		mFootToMouthOffset = context.getResources().getDimensionPixelSize(
				R.dimen.foot_to_mouth_offset);
		mArcRadius = context.getResources().getDimensionPixelSize(
				R.dimen.arc_radius);
		mArcOffset = context.getResources().getDimensionPixelOffset(
				R.dimen.arc_offset);

		mRountRectF = getRoundRectF();
		mFaceRect = getFaceRect();

		mAnglePerX = 132.0F / (mFaceRect.right - mFaceRect.left);

		Rect startRect = getThumbRect(-150.0F);
		mThumbStartX = (startRect.left + startRect.right) / 2;
		Rect endRect = getThumbRect(-18.0F);
		mThumbEndX = (endRect.left + endRect.right) / 2;
	}

	public void draw(Canvas canvas) {
		canvas.translate(-mCurX, 0);

		// 脚
		canvas.drawBitmap(mFoot, null, getFootRect(), null);

		// 笑脸
//		canvas.drawBitmap(mSeekBg, null, mFaceRect, null);

		// 眼睛
		canvas.drawBitmap(mEye, null, getEyesRect(), null);

		// 遮盖
		drawOverlay(canvas);

		// 橘黄笑脸

		drawOrangeOverlay(canvas);

		// 按钮
		mThumbRect = getThumbRect(mAngle);
		canvas.drawBitmap(mThumb, null, mThumbRect, null);

		if (mIsGo && mOnGoListener != null) {
			mOnGoListener.go();
			mOnGoListener = null;
		}

	}

	public void setCurX(int x) {
		mCurX = x;
	}

	public boolean isInThumb(int x, int y) {
		if (mIsBack) {
			return false;
		}
		if (mIsGo) {
			return true;
		}
		if (mThumbRect == null) {
			return false;
		}
		return mThumbRect.contains(x, y);
	}

	public void moveThumb(int x, int y) {
		if (mIsBack || mIsGo) {
			return;
		}
		int xPos = (int) ((mRountRectF.left + mRountRectF.right) / 2 - x);
		int yPos = (int) ((mRountRectF.top + mRountRectF.bottom) / 2 - y);
		double r = Math.sqrt(xPos * xPos + yPos * yPos);
		if (mArcRadius + mArcOffset * 2 > r && mArcRadius - mArcOffset * 2 < r) {
			if (x >= mThumbStartX && x <= mThumbEndX) {
				mAngle = -150.0F + mAnglePerX * (x - mThumbStartX);
				if (mAngle <= -150.0F) {
					mAngle = -150.0F;
				}
				if (mAngle >= -18.0F) {
					mAngle = -18.0F;
				}
				if (mAngle > -28.0F) {
					mIsGo = true;
				}
			}
		} else {
			back();
		}

	}

	public void upThumb(int x, int y) {
		if (mIsGo) {
			return;
		}
		back();
	}

	public void setOnGoListener(OnGoListener listener) {
		mOnGoListener = listener;
	}

	private void back() {
		if (mIsGo) {
			return;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				mIsBack = true;
				while (mAngle > -150.0F) {
					if (mAngle - 1 < -150.0F) {
						mAngle = -150.0F;
					} else {
						mAngle--;
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				mIsBack = false;
			}
		}).start();
	}
	
	private void drawOverlay(Canvas canvas) {

		int count = canvas.saveLayer(mRountRectF.left, mRountRectF.top,
				mRountRectF.right, mRountRectF.bottom + mArcOffset, null,
				Canvas.ALL_SAVE_FLAG);
		canvas.drawBitmap(mSeekBg, null, mFaceRect, null);

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2.0F * mArcOffset);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

		// SweepGradient sweepGradient = new SweepGradient(
		// (roundRectF.left + roundRectF.right) / 2,
		// (roundRectF.top + roundRectF.bottom) / 2, new int[] {
		// Color.WHITE, Color.TRANSPARENT }, new float[] { 0.5f,
		// 1.0f });
		// paint.setShader(sweepGradient);
		paint.setColor(Color.BLACK);
		canvas.drawArc(new RectF(mRountRectF.left, mRountRectF.top,
				mRountRectF.right, mRountRectF.bottom), getOverlayStartAngle(),
				20.0F, false, paint);
		paint.setXfermode(null);
		canvas.restoreToCount(count);
	}

	private void drawOrangeOverlay(Canvas canvas) {
		int count = canvas.saveLayer(mFaceRect.left, mFaceRect.top,
				mFaceRect.right, mFaceRect.bottom, null, Canvas.ALL_SAVE_FLAG);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(false);
		canvas.drawBitmap(mProgressBg, null, mFaceRect, paint);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(10.0F * mArcOffset);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		canvas.drawArc(mRountRectF, 0.0F, -mAngle, false, paint);
		paint.setXfermode(null);
		canvas.restoreToCount(count);
	}

	private Rect getFootRect() {
		int left = mScreenWidth * 6 + (mScreenWidth - mFoot.getWidth()) / 2;
		int top = mScreenHeight - mStreetHeight - mFoot.getHeight();
		int right = left + mFoot.getWidth();
		int bottom = top + mFoot.getHeight();
		return new Rect(left, top, right, bottom);
	}

	private Rect getFaceRect() {
		int left = mScreenWidth * 6 + (mScreenWidth - mSeekBg.getWidth()) / 2;
		int top = mScreenHeight - mStreetHeight - mFoot.getHeight()
				- mSeekBg.getHeight() + mFootToMouthOffset;
		int right = left + mSeekBg.getWidth();
		int bottom = top + mSeekBg.getHeight();
		return new Rect(left, top, right, bottom);
	}

	private Rect getEyesRect() {
		int left = mScreenWidth * 6 + (mScreenWidth - mEye.getWidth()) / 2;
		int top = mScreenHeight - mStreetHeight - mFoot.getHeight()
				- mSeekBg.getHeight() - mEye.getHeight();
		int right = left + mEye.getWidth();
		int bottom = top + mEye.getHeight();
		return new Rect(left, top, right, bottom);
	}

	private Rect getThumbRect(float angle) {
		int[] xy = getThumbXY(angle);
		int left = mScreenWidth * 6 + xy[0] - mThumb.getWidth() / 2;
		int top = mScreenHeight - mStreetHeight - mFoot.getHeight()
				- mArcRadius - mArcOffset + mFootToMouthOffset - xy[1]
				- mThumb.getHeight() / 2;

		int right = left + mThumb.getWidth();
		int bottom = top + mThumb.getHeight();
		return new Rect(left, top, right, bottom);
	}

	private RectF getRoundRectF() {
		// 圆所在的矩形
		int left = mScreenWidth * 6 + mScreenWidth / 2 - mArcRadius;
		int top = mScreenHeight - mStreetHeight - mFoot.getHeight()
				- mArcRadius * 2 - mArcOffset + mFootToMouthOffset;
		int right = left + mArcRadius * 2;
		int bottom = top + mArcRadius * 2;
		return new RectF(left, top, right, bottom);
	}

	private int[] getThumbXY(float angle) {
		int mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(angle)));
		mThumbXPos += mScreenWidth / 2;
		int mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(angle)));
		return new int[] { mThumbXPos, mThumbYPos };
	}

	private float getOverlayStartAngle() {
		mOverlayStartAngle -= 1;
		if (mOverlayStartAngle <= 40.0F) {
			mOverlayStartAngle = 115.0F;
		}
		return mOverlayStartAngle;
	}

	public interface OnGoListener {
		public abstract void go();
	}
}
