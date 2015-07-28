package com.lzxmy.demo.musicplay;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;


public class AnimUtils {
     public static void setAnimation(Context context,View view,int style) {
    	 Animation loadingAnim = AnimationUtils.loadAnimation(context,
    			 style);
			LinearInterpolator lin = new LinearInterpolator();
		loadingAnim.setInterpolator(lin);
		view.startAnimation(loadingAnim);
	}
}
