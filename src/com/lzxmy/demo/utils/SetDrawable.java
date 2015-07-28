package com.lzxmy.demo.utils;
//package com.ivg.around.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import com.ivg.around.R;
//
//import android.content.Context;
//import android.content.res.AssetManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.StateListDrawable;
//import android.view.View;
//
//public class SetDrawable {
//	 Drawable sBitmap = null;
//	private static SetDrawable mInstance = null;
//	 AssetManager assetManager;
//	 String dir = "lbssource/images/";
//
//	public static SetDrawable getInstance(Context context) {
//		if (mInstance == null) {
//			mInstance = new SetDrawable(context);
//		}
//		return mInstance;
//	}
//
//	private SetDrawable(Context context) {
//		assetManager = context.getAssets();
//		sBitmap = context.getResources().getDrawable(
//				R.drawable.bt_back_selector);
//	}
//
//	/**
//	 * 获取资源文件图片
//	 * 
//	 * @param path
//	 * @return
//	 */
//	public  void getDrawableByPath(View view, String normalpath,
//			String presspath) {
//		Drawable normaldrawable = null;
//		Drawable pressdrawable = null;
//		try {
//			StateListDrawable bg = new StateListDrawable();
//
//			InputStream pressis = assetManager.open(dir + presspath);
//			pressdrawable = Drawable.createFromStream(pressis, null);
//			bg.addState(new int[] { android.R.attr.state_pressed },
//					pressdrawable);
//
//			InputStream normalis = assetManager.open(dir + normalpath);
//			normaldrawable = Drawable.createFromStream(normalis, null);
//			bg.addState(new int[] {}, normaldrawable);
//			view.setBackgroundDrawable(bg);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 获取资源文件图片
//	 * 
//	 * @param path
//	 * @return
//	 */
//	public  Bitmap getBitmapByPath(String normalpath) {
//		try {
//			InputStream pressis = assetManager.open(normalpath);
//			Bitmap bitmap = BitmapFactory.decodeStream(pressis);
//			return bitmap;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
