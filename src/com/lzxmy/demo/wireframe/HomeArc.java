package com.lzxmy.demo.wireframe;


import com.lzxmy.demo.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * 圆弧计分
 * @author Administrator
 *
 */
public class HomeArc extends TextView { 

	private Paint paint_black, paint_white;
	private RectF rectf;
	private float tb;
//	private int blackColor = 0x70000000; // 底黑色
	private int whiteColor = 0xddffffff; // 白色
	private int score;
	private float arc_y = 0f;
	private Bitmap bitmap_point;
	Matrix matrix = new Matrix();
	public HomeArc(Context context, int score) {
		super(context);
		init(score);
	}

	public void init(int score) {
		setWillNotDraw(false);
		this.score = score;
		Resources res = getResources();
		tb = res.getDimension(R.dimen.historyscore_tb);

		paint_black = new Paint();
		paint_black.setAntiAlias(true);
//		paint_black.setColor(blackColor);
		paint_black.setStrokeWidth(tb * 0.2f);
		paint_black.setStyle(Style.STROKE);

		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);
		paint_white.setTextSize(tb*6.0f);
		paint_white.setStrokeWidth(tb * 0.2f);
//		paint_white.setTextAlign(Align.CENTER);
		paint_white.setStyle(Style.STROKE);
	
		rectf = new RectF();
		rectf.set(tb * 0.5f, tb * 0.5f, tb * 18.5f, tb * 18.5f);
		bitmap_point = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_1);
		setLayoutParams(new LayoutParams((int) (tb * 19.5f), (int) (tb * 19.5f)));
//		this.getViewTreeObserver().addOnPreDrawListener(
//				new OnPreDrawListener() {
//					public boolean onPreDraw() {
//						new thread();
//						getViewTreeObserver().removeOnPreDrawListener(this);
//						return false;
//					}
//				});
	}

	@Override
	protected void onDraw(Canvas c) {

//		
		c.drawArc(rectf, -90, 360, false, paint_black);
		c.drawArc(rectf, -90, arc_y, false, paint_white);
		super.onDraw(c);
	}
@Override
protected void dispatchDraw(Canvas canvas) {
	// TODO Auto-generated method stub
	super.dispatchDraw(canvas);
	canvas.drawText("" + "90", tb * 9.7f, tb * 11.0f, paint_white);
	canvas.drawBitmap(bitmap_point, matrix, paint_white);
	
}
	class thread implements Runnable {
		private Thread thread;
		private int statek;
		int count;

		public thread() {
			thread = new Thread(this);
			thread.start();
		}

		@Override
		public void run() {
			while (true) {
				switch (statek) {
				case 0:
					try {
						Thread.sleep(200);
						statek = 1;
					} catch (InterruptedException e) {
					}
					break;
				case 1:
					try {
						Thread.sleep(15);
						arc_y += 3.6f;
						count++;
						postInvalidate();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				if (count >= score)
					break;
			}
		}
	}

}
