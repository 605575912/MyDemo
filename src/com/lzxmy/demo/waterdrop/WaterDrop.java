package com.lzxmy.demo.waterdrop;


import com.lzxmy.demo.waterdrop.DropCover.OnDragCompeteListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WaterDrop extends RelativeLayout {
	private Paint mPaint;
	private TextView mTextView;
	private DropCover.OnDragCompeteListener mOnDragCompeteListener;
	private boolean mHolderEventFlag;

	public WaterDrop(Context context) {
		super(context);
		init();
	}

	public WaterDrop(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void setText(String str) {
		mTextView.setText(str);
	}

	public void setTextSize(int size) {
		mTextView.setTextSize(size);
	}

	private void init() {
		mTextView = new TextView(getContext());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mTextView.setTextSize(13);
		mTextView.setTextColor(0xffffffff);
		mTextView.setLayoutParams(params);
		addView(mTextView);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {

		if (mPaint == null) {
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}

		mPaint.setColor(0xffff0000);
		canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2,
				mPaint);
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		ViewGroup parent = getScrollableParent();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mHolderEventFlag = !CoverManager.getInstance().isRunning();
			if (mHolderEventFlag) {
				parent.requestDisallowInterceptTouchEvent(true);
				CoverManager.getInstance().start(this, event.getRawX(),
						event.getRawY(), mOnDragCompeteListener);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (mHolderEventFlag) {
				CoverManager.getInstance().update(event.getRawX(),
						event.getRawY());
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			if (mHolderEventFlag) {
				parent.requestDisallowInterceptTouchEvent(false);
				CoverManager.getInstance().finish(this, event.getRawX(),
						event.getRawY());
			}
			break;
		}

		return true;
	}

	private ViewGroup getScrollableParent() {
		View target = this;
		while (true) {
			View parent = (View) target.getParent();
			if (parent == null)
				return null;
			if (parent instanceof ListView || parent instanceof ScrollView) {
				return (ViewGroup) parent;
			}
			target = parent;
		}

	}

	public void setOnDragCompeteListener(
			OnDragCompeteListener onDragCompeteListener) {
		mOnDragCompeteListener = onDragCompeteListener;
	}
}
