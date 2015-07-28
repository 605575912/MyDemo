package com.lzxmy.demo.waterdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class DropCover extends SurfaceView implements SurfaceHolder.Callback {

	private static final int EXPLOSION_SIZE = 200;
	private int mMaxDistance = 100;

	private ExplosionUpdateThread mThread;
	private Explosion mExplosion;

	private float mBaseX;
	private float mBaseY;

	private float mTargetX;
	private float mTargetY;

	private Bitmap mDest;
	private Paint mPaint = new Paint();

	private float targetWidth;
	private float targetHeight;

	private float mStrokeWidth = 20;
	private boolean isDraw = true;
	private float mStatusBarHeight = 0;
	private OnDragCompeteListener mOnDragCompeteListener;

	public interface OnDragCompeteListener {
		void onDrag();
	}

	public DropCover(Context context) {
		super(context);
		this.setBackgroundColor(Color.TRANSPARENT);
		this.setZOrderOnTop(true);
		getHolder().setFormat(PixelFormat.TRANSPARENT);
		getHolder().addCallback(this);
		setFocusable(false);
		setClickable(false);
		mPaint.setAntiAlias(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	/**
	 * draw drop and line
	 */
	private void drawDrop() {
		Canvas canvas = getHolder().lockCanvas();
		if (canvas != null) {
			canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);

			if (isDraw) {
				double distance = Math.sqrt(Math.pow(mBaseX - mTargetX, 2)
						+ Math.pow(mBaseY - mTargetY, 2));
				mPaint.setColor(0xffff0000);
				canvas.drawCircle(mBaseX, mBaseY, mStrokeWidth, mPaint);
				if (distance < mMaxDistance) {
					mStrokeWidth = (float) ((1 - distance / mMaxDistance) * 25);
					mPaint.setStrokeWidth(mStrokeWidth);
					// TODO t
					// The line is not smooth.
					// maybe change it to B��zier curve
					canvas.drawLine(mBaseX, mBaseY, mTargetX + targetWidth / 2,
							mTargetY + targetHeight / 2, mPaint);
				}
				canvas.drawBitmap(mDest, mTargetX, mTargetY, mPaint);
			}
			getHolder().unlockCanvasAndPost(canvas);
		}
	}

	public void setTarget(Bitmap dest) {
		mDest = dest;
		targetWidth = dest.getWidth();
		targetHeight = dest.getHeight();
	}

	public void init(float x, float y) {
		mBaseX = x + mDest.getWidth() / 2;
		mBaseY = y - mDest.getWidth() / 2;
		mTargetX = x;
		mTargetY = y - mStatusBarHeight;

		isDraw = true;
		drawDrop();
	}

	/**
	 * move the drop
	 * 
	 * @param x
	 * @param y
	 */
	public void update(float x, float y) {

		mTargetX = x;
		mTargetY = y - mStatusBarHeight;
		drawDrop();
	}

	/**
	 * reset datas
	 */
	public void clearDatas() {
		mBaseX = -1;
		mBaseY = -1;
		mTargetX = -1;
		mTargetY = -1;
		mDest = null;
	}

	/**
	 * remove DropCover
	 */
	public void clearViews() {
		if (getParent() != null) {
			CoverManager.getInstance().getWindowManager().removeView(this);
		}
	}

	/**
	 * finish drag event and start explosion
	 * 
	 * @param target
	 * @param x
	 * @param y
	 */
	public void finish(View target, float x, float y) {
		double distance = Math.sqrt(Math.pow(mBaseX - mTargetX, 2)
				+ Math.pow(mBaseY - mTargetY, 2));

		clearDatas();
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		getHolder().unlockCanvasAndPost(canvas);

		if (distance > mMaxDistance) {
			if (mOnDragCompeteListener != null)
				mOnDragCompeteListener.onDrag();

			initExplosion(x, y);

			mThread = new ExplosionUpdateThread(getHolder(), this);
			mThread.setRunning(true);
			mThread.start();
		} else {
			clearViews();
			target.setVisibility(View.VISIBLE);
		}

		isDraw = false;
	}

	public void setStatusBarHeight(int statusBarHeight) {
		mStatusBarHeight = statusBarHeight;
	}

	public void setOnDragCompeteListener(
			OnDragCompeteListener onDragCompeteListener) {
		mOnDragCompeteListener = onDragCompeteListener;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawDrop();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mThread != null) {
			mThread.setRunning(false);
			mThread = null;
		}
	}

	/**
	 * init the explosion whit start position
	 * 
	 * @param x
	 * @param y
	 */
	public void initExplosion(float x, float y) {
		if (mExplosion == null || mExplosion.getState() == Explosion.STATE_DEAD) {
			mExplosion = new Explosion(EXPLOSION_SIZE, (int) x, (int) y);
		}
	}

	/**
	 * call it to draw explosion
	 * 
	 * @param canvas
	 * @return isAlive
	 */
	public boolean render(Canvas canvas) {
		boolean isAlive = false;
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		canvas.drawColor(Color.argb(0, 0, 0, 0)); // To make canvas transparent
		// render explosions
		if (mExplosion != null) {
			isAlive = mExplosion.draw(canvas);
		}
		return isAlive;
	}

	/**
	 * update explosion
	 */
	public void update() {
		// update explosions
		if (mExplosion != null && mExplosion.isAlive()) {
			mExplosion.update(getHolder().getSurfaceFrame());
		}
	}

	// private void displayFps(Canvas canvas, String fps) {
	// if (canvas != null && fps != null) {
	// Paint paint = new Paint();
	// paint.setARGB(255, 255, 255, 255);
	// canvas.drawText(fps, this.getWidth() - 50, 20, paint);
	// }
	// }

	/**
	 * please call it before animation start
	 * 
	 * @param maxDistance
	 */
	public void setMaxDragDistance(int maxDistance) {
		mMaxDistance = maxDistance;
	}
}
