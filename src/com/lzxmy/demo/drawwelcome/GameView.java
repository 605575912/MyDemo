package com.lzxmy.demo.drawwelcome;

import com.lzxmy.demo.drawwelcome.Arc.OnGoListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Scroller;



public class GameView extends SurfaceView implements SurfaceHolder.Callback,
		OnGestureListener {

	private SurfaceHolder mHolder;
	private PlayThread mPlayThread;
	private boolean mPlayed = true;

	private int mWidth;
	private int mHeight;

	private Arrow mArrow;
	private Sun mSun;
	private Cloud mCloud;
	private Background mBackground;
	private Street mStreet;
	private Build mBuild;
	private Text mText;
	private Boy mBoy;
	private Car mCar;
	private Arc mArc;

	private int mDownX = 0;
	private int mCurX = 0;
	private boolean mIsAllowSilde = true;

	private GestureDetectorCompat mDetectorCompat;
	private Scroller mScroller;

	public GameView(Context context) {
		this(context, null);
	}

	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setFormat(PixelFormat.TRANSLUCENT);
		setZOrderOnTop(true);
		setFocusable(true);
		mDetectorCompat = new GestureDetectorCompat(context, this);
		mScroller = new Scroller(context);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mWidth = getWidth();
		mHeight = getHeight();
		mSun = new Sun(getContext(), mWidth);
		mCloud = new Cloud(getContext(), mWidth, mHeight);
		mBackground = new Background(getContext(), mWidth, mHeight);
		mArrow = new Arrow(getContext(), mWidth, mHeight);
		mStreet = new Street(getContext(), mWidth, mHeight);
		mBuild = new Build(getContext(), mWidth, mHeight);
		mText = new Text(getContext(), mWidth, mHeight);
		mBoy = new Boy(getContext(), mWidth, mHeight);
		mCar = new Car(getContext(), mWidth, mHeight);
		mArc = new Arc(getContext(), mWidth, mHeight);

		setOnGoListener(new OnGoListener() {

			@Override
			public void go() {
				post(new Runnable() {

					@Override
					public void run() {
						mPlayed = false;
						mPlayThread.interrupt();
						((Activity) getContext()).finish();
					}
				});

			}
		});
		play();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mPlayed = false;
		mPlayThread.interrupt();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mArrow.dismiss();
			mDownX = (int) event.getX();
			mIsAllowSilde = !mArc.isInThumb(mCurX + (int) event.getX(),
					(int) event.getY());
			break;

		case MotionEvent.ACTION_MOVE:
			if (mIsAllowSilde) {
				int x = (int) event.getX() - mDownX;
				int curX = mCurX - x;
				if (curX <= 0) {
					curX = 0;
				}
				if (curX > mWidth * 6) {
					curX = mWidth * 6;
				}
				change(curX);
			} else {
				mArc.moveThumb(mCurX + (int) event.getX(), (int) event.getY());
			}
			break;

		case MotionEvent.ACTION_UP:
			if (mIsAllowSilde) {
				mCurX -= (int) event.getX() - mDownX;
				if (mCurX <= 0) {
					mCurX = 0;
				}
				if (mCurX > mWidth * 6) {
					mCurX = mWidth * 6;
				}
			} else {
				mArc.upThumb(mCurX + (int) event.getX(), (int) event.getY());
			}
			break;
		}
		return mDetectorCompat.onTouchEvent(event);
	}

	public void setOnGoListener(OnGoListener listener) {
		mArc.setOnGoListener(listener);
	}

	private void change(int x) {
		mCloud.setCurX(x);
		mBackground.setCurX(x);
		mStreet.setCurX(x);
		mBuild.setCurX(x);
		mText.setCurX(x);
		mBoy.setCurX(x);
		mCar.setCurX(x);
		mArc.setCurX(x);
	}

	private void play() {
		mPlayThread = new PlayThread();
		mPlayThread.start();
	}

	private class PlayThread extends Thread {

		@Override
		public void run() {
			while (mPlayed) {
				Canvas canvas = mHolder.lockCanvas();
				if (canvas==null) {
					mPlayed = false;
				}
				
				
				canvas.drawColor(Color.BLACK);
				canvas.save();
				int[] colors = new int[2];
				colors[0] = -2757121;
				colors[1] = -6433281;
				LinearGradient linearGradient = new LinearGradient(0f, 0f,  0f,
						0f, colors, null, Shader.TileMode.REPEAT);
				Paint mPaint = new Paint();
				mPaint.setShader(linearGradient);
				canvas.drawRect(new RectF(0, 0, mWidth, mHeight), mPaint);
				canvas.restore();
////
				canvas.save();
				mSun.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mCloud.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mBackground.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mStreet.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mBuild.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mArrow.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mBoy.draw(canvas);
				canvas.restore();
//
				canvas.save();
				mCar.draw(canvas);
				canvas.restore();

				canvas.save();
				mArc.draw(canvas);
				canvas.restore();

				canvas.save();
				mText.draw(canvas);
				canvas.restore();

				if (mIsAllowSilde) {
					try {
						Thread.sleep(5);
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
				mHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (velocityX > 3000) {
			velocityX = 3000;
		}
		if (velocityX < -3000) {
			velocityX = -3000;
		}

		if (velocityX > 0) {
			mScroller.fling(mCurX, 0, -(int) velocityX, 0,
					Math.max(0, mCurX - mWidth), mCurX, 0, 0);
		} else {
			mScroller.fling(mCurX, 0, -(int) velocityX, 0, 0,
					Math.min(mCurX + mWidth, mWidth * 6), 0, 0);
		}
		invalidate();
		return true;
	}

	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			mCurX = mScroller.getCurrX();
			change(mCurX);
			invalidate();
		}

	}
}
