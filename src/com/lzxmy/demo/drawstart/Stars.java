package com.lzxmy.demo.drawstart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 
 *
 */
public class Stars extends View {
	private float radius = 40;
	private int color = 0xFF0000;
	private final static float DEGREE = 36; // 锟斤拷锟斤拷墙嵌锟�
	Paint paint;
	Paint graypaint;
	Path path;
	float radian;
	float radius_in;
	int sum = 5;
	double score = 0.15;

	public Stars(Context context) {
		super(context);
		init();
	}

	public Stars(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		// 锟斤拷取锟皆讹拷锟斤拷锟斤拷锟斤拷
		init();
	}

	public Stars(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setScore(int score) {
		if (score <= sum) {
			this.score = score;
		}

	}

	void init() {
		this.color = Color.YELLOW;
		paint = new Paint();
		paint.setColor(this.color);
		paint.setAntiAlias(true);
		graypaint = new Paint();
		graypaint.setColor(Color.LTGRAY);
		graypaint.setAntiAlias(true);

		path = new Path();
		// LayoutParams params = super.getLayoutParams();
		// 锟叫硷拷锟斤拷锟斤拷蔚陌刖�
		radian = degree2Radian(DEGREE);
		setBackgroundColor(Color.RED);
	}

	/**
	 * 锟角讹拷转锟斤拷锟斤拷
	 * 
	 * @param degree
	 * @return
	 */
	private float degree2Radian(float degree) {
		return (float) (Math.PI * degree / 180);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int measuredHeight = measure(heightMeasureSpec);
		int measuredWidth = measure(widthMeasureSpec);
		measuredHeight = measuredWidth / sum;
		super.setMeasuredDimension(measuredWidth, measuredHeight);

	}

	private int measure(int measureSpec) {

		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// Default size if no limits are specified.

		int result = measureSpec;
		if (specMode == MeasureSpec.AT_MOST) {

			// Calculate the ideal size of your
			// control within this maximum size.
			// If your control fills the available
			// space return the outer bound.
			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY) {

			// If your control can fit within these bounds return that
			// value.
			result = specSize;
		}
		return result;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int with = getWidth();
		float x = with % (sum * 2);
		if (x == 0) {
			this.radius = with / sum / 2;
		} else {
			this.radius = (with - x) / sum / 2;
		}
		radius_in = (float) (radius * Math.sin(radian / 2) / Math.cos(radian));
		float dx = (float) (radius * Math.cos(radian / 2)) * 2;

		for (int i = 1; i < sum + 1; i++) {

			if (i < score) {
				path.reset();
				path.moveTo((float) (radius * Math.cos(radian / 2)) + x, 0);
				//
				path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
						* Math.sin(radian))
						+ x, (float) (radius - radius * Math.sin(radian / 2)));
				path.lineTo((float) (radius * Math.cos(radian / 2) * 2) + x,
						(float) (radius - radius * Math.sin(radian / 2)));
				path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
						* Math.cos(radian / 2))
						+ x,
						(float) (radius + radius_in * Math.sin(radian / 2)));
				path.lineTo((float) (radius * Math.cos(radian / 2) + radius
						* Math.sin(radian))
						+ x, (float) (radius + radius * Math.cos(radian)));
				//
				path.lineTo((float) (radius * Math.cos(radian / 2)) + x,
						(float) (radius + radius_in));
				path.lineTo((float) (radius * Math.cos(radian / 2) - radius
						* Math.sin(radian))
						+ x, (float) (radius + radius * Math.cos(radian)));
				path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
						* Math.cos(radian / 2))
						+ x,
						(float) (radius + radius_in * Math.sin(radian / 2)));
				path.lineTo(0 + x,
						(float) (radius - radius * Math.sin(radian / 2)));
				path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
						* Math.sin(radian))
						+ x, (float) (radius - radius * Math.sin(radian / 2)));

				path.close();
				canvas.drawPath(path, paint);

			} else {
				if (i - (score) < 1) {
					{
						//
						path.reset();
						path.moveTo(
								(float) (radius * Math.cos(radian / 2)) + x, 0);
						path.lineTo(
								(float) (radius * Math.cos(radian / 2) + radius_in
										* Math.sin(radian))
										+ x,
								(float) (radius - radius * Math.sin(radian / 2)));
						path.lineTo(
								(float) (radius * Math.cos(radian / 2) * 2) + x,
								(float) (radius - radius * Math.sin(radian / 2)));
						path.lineTo(
								(float) (radius * Math.cos(radian / 2) + radius_in
										* Math.cos(radian / 2))
										+ x,
								(float) (radius + radius_in
										* Math.sin(radian / 2)));
						path.lineTo(
								(float) (radius * Math.cos(radian / 2) + radius
										* Math.sin(radian))
										+ x,
								(float) (radius + radius * Math.cos(radian)));
						path.lineTo(
								(float) (radius * Math.cos(radian / 2)) + x,
								(float) (radius + radius_in));
						path.close();
						canvas.drawPath(path, graypaint);
					}
					path.reset();
					path.moveTo((float) (radius * Math.cos(radian / 2)) + x, 0);
					path.lineTo((float) (radius * Math.cos(radian / 2)) + x,
							(float) (radius + radius_in));
					path.lineTo((float) (radius * Math.cos(radian / 2) - radius
							* Math.sin(radian))
							+ x, (float) (radius + radius * Math.cos(radian)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) - radius_in
									* Math.cos(radian / 2))
									+ x,
							(float) (radius + radius_in * Math.sin(radian / 2)));
					path.lineTo(0 + x,
							(float) (radius - radius * Math.sin(radian / 2)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) - radius_in
									* Math.sin(radian))
									+ x,
							(float) (radius - radius * Math.sin(radian / 2)));

					path.close();
					canvas.drawPath(path, paint);
				} else {

					path.reset();
					path.moveTo((float) (radius * Math.cos(radian / 2)) + x, 0);
					//
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) + radius_in
									* Math.sin(radian))
									+ x,
							(float) (radius - radius * Math.sin(radian / 2)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) * 2) + x,
							(float) (radius - radius * Math.sin(radian / 2)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) + radius_in
									* Math.cos(radian / 2))
									+ x,
							(float) (radius + radius_in * Math.sin(radian / 2)));
					path.lineTo((float) (radius * Math.cos(radian / 2) + radius
							* Math.sin(radian))
							+ x, (float) (radius + radius * Math.cos(radian)));
					//
					path.lineTo((float) (radius * Math.cos(radian / 2)) + x,
							(float) (radius + radius_in));
					path.lineTo((float) (radius * Math.cos(radian / 2) - radius
							* Math.sin(radian))
							+ x, (float) (radius + radius * Math.cos(radian)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) - radius_in
									* Math.cos(radian / 2))
									+ x,
							(float) (radius + radius_in * Math.sin(radian / 2)));
					path.lineTo(0 + x,
							(float) (radius - radius * Math.sin(radian / 2)));
					path.lineTo(
							(float) (radius * Math.cos(radian / 2) - radius_in
									* Math.sin(radian))
									+ x,
							(float) (radius - radius * Math.sin(radian / 2)));

					path.close();
					canvas.drawPath(path, graypaint);
				}
			}

			x = dx + x;
		}

		// path.reset();
		// // canvas.save();
		// path.moveTo((float) (radius * Math.cos(radian / 2)), 0);
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) + radius_in
		// * Math.sin(radian)),
		// (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
		// (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) + radius_in
		// * Math.cos(radian / 2)), (float) (radius + radius_in
		// * Math.sin(radian / 2)));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) + radius
		// * Math.sin(radian)),
		// (float) (radius + radius * Math.cos(radian)));
		// path.lineTo((float) (radius * Math.cos(radian / 2)),
		// (float) (radius + radius_in));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) - radius
		// * Math.sin(radian)),
		// (float) (radius + radius * Math.cos(radian)));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) - radius_in
		// * Math.cos(radian / 2)), (float) (radius + radius_in
		// * Math.sin(radian / 2)));
		// path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) - radius_in
		// * Math.sin(radian)),
		// (float) (radius - radius * Math.sin(radian / 2)));
		//
		// path.close();
		// canvas.drawPath(path, paint);
		// // canvas.restore();
		//
		// canvas.save();
		//
		// canvas.restore();
		// Bitmap bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
		// canvas.drawBitmap(bitmap, 10, 10, paint);
	}

}
