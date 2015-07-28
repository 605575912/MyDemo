package com.lzxmy.demo.layer;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LayerDrawableActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView imageView = new ImageView(LayerDrawableActivity.this);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.image);
		Drawable[] array = new Drawable[3];
		array[0] = new PaintDrawable(Color.DKGRAY); // 黑色
		array[1] = new PaintDrawable(Color.CYAN); // 白色
		array[2] = new BitmapDrawable(bm); // 位图资源
		LayerDrawable ld = new LayerDrawable(array); // 参数为上面的Drawable数组
		ld.setLayerInset(1, 10, 14, 14, 1); // 第一个参数1代表数组的第二个元素，为白色
		ld.setLayerInset(2, 22, 21, -22, -21); // 第一个参数2代表数组的第三个元素，为位图资源
		imageView.setImageDrawable(ld);
		setContentView(imageView);
	}

}
