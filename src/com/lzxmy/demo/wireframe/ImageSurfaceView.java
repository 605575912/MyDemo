package com.lzxmy.demo.wireframe;

import com.lzxmy.demo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ImageSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	public Bitmap image_bitmap;
	private int whiteColor = 0xddffffff; // 白色
	private int blackColor = 0x700aaeee; // 底黑色
	private Paint paint_white, paint_black;
	Matrix m;
	private RectF rectf;
	private float tb;
	Mythread mythread;

	public ImageSurfaceView(Context context) {
		super(context);
		// 添加回调
		Resources res = getResources();
		tb = res.getDimension(R.dimen.historyscore_tb);
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
		initBitmap(context);
		mythread = new Mythread(holder);
		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);
		paint_white.setStrokeWidth(tb * 0.2f);
		paint_white.setTextAlign(Align.CENTER);
		paint_white.setStyle(Style.STROKE);
		paint_white.setTextSize(65f);

		paint_black = new Paint();
		paint_black.setAntiAlias(true);
		paint_black.setColor(blackColor);
		paint_black.setStrokeWidth(tb * 0.2f);
		paint_black.setStyle(Style.STROKE);

	
		m = new Matrix();
		m.setScale(-1, 1);

	}

	public void initBitmap(Context context) {
		Resources r = context.getResources();
		image_bitmap = BitmapFactory.decodeResource(r, R.drawable.icon_1);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@SuppressLint("WrongCall")
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mythread == null) {
			mythread = new Mythread(holder);
		}
		mythread.start();

	}

	class Mythread extends Thread {
		SurfaceHolder holder;
		boolean isrun = true;

		Mythread(SurfaceHolder holder) {
			this.holder = holder;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Canvas canvas = holder.lockCanvas(); // 调用渲染界面的方法 渲染出界面
			toDraw(canvas);
			holder.unlockCanvasAndPost(canvas);
		}

		// 界面渲染
		public void toDraw(Canvas canvas) {
			if (rectf==null) {
				rectf = new RectF();
				rectf.set(getWidth()/4, 0, getWidth()*3/4, getWidth()/2);
			}
			
			Log.i("TAG", getWidth()+"");
			
			m.postTranslate(getWidth(), 0); // 向右平移两个图片宽度的位置
			canvas.setMatrix(m);
			canvas.drawBitmap(image_bitmap, 0, 0, null); // 输出第一张图片
			canvas.drawText("颠覆文字图片View", 310, 65, paint_white);
			canvas.drawArc(rectf, 80, 360, false, paint_black);
			canvas.drawArc(rectf, 0, 80, false, paint_white);

		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mythread = null;
	}
}