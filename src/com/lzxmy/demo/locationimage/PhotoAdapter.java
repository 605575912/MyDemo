package com.lzxmy.demo.locationimage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lzxmy.demo.R;
import com.nostra13.universalimageloader.ServiceLoader;
import com.support.serviceloader.packet.ImageDownPacket;
import com.support.serviceloader.packet.ImageOptions;

import android.R.id;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PhotoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<String> list;
	// private ViewHolder viewHolder;
	boolean start = true;
	ImageOptions imageOptions;
Context context;
	public PhotoAdapter(Context context, List<String> list) {
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
		imageOptions = new ImageOptions(context, R.drawable.h_defaultpic);
		imageOptions.setImageOnFail(R.drawable.h_defaultpic);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		String path = list.get(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.grid_item, null);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.gridimg);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ServiceLoader.getInstance().displayImage(imageOptions, path, viewHolder.image);
		// Bitmap headBitmap = ServiceLoader.getInstance().getBitmap(path);
		// if (headBitmap != null) {
		// viewHolder.image.setImageBitmap(headBitmap);
		// } else {
		// viewHolder.image.setImageBitmap(bitmapdefaut);
		// viewHolder.image.setTag(path);
		// ServiceLoader.getInstance().sendPacket(
		// new ImageDownPacket(viewHolder.image, path, 400, 400));
		//
		// }
		// convertView.setVisibility(View.GONE);
		// an(convertView);
		return convertView;
	}

	HashMap<Integer, View> sfHashMap = new HashMap<Integer, View>();

	void an(final View convertView) {

		AlphaAnimation alphaAnimation0 = new AlphaAnimation(0, 1);
		alphaAnimation0.setDuration(100);
		alphaAnimation0.setFillAfter(true);
		alphaAnimation0.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				// View view = sfHashMap.get(convertView.hashCode());
				// if (view != null) {
				Integer i = 0;
				for (Integer integer : sfHashMap.keySet()) {
					i = integer;
					View view = sfHashMap.get(integer);
					AlphaAnimation animation = (AlphaAnimation) view
							.getTag(R.id.arc);
					// AlphaAnimation animation = sfHashMap.get(integer);
					Log.i("TAG", "===" + integer);
					// view.setVisibility(View.VISIBLE);
					view.setAnimation(animation);
					break;
				}
				sfHashMap.remove(i);
				// }

			}
		});
		convertView.setTag(R.id.arc, alphaAnimation0);
		sfHashMap.put(convertView.hashCode(), convertView);
		if (start) {
			convertView.setVisibility(View.VISIBLE);
			start = false;
			convertView.setAnimation(alphaAnimation0);

		}
	}

	public class ViewHolder {
		public ImageView image;
	}
}
