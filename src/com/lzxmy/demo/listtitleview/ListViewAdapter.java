/**   
 * Copyright © 2014 All rights reserved.
 * 
 * @Title: ListViewAdapter.java 
 * @Prject: ListViewTitle
 * @Package: com.example.listviewtitle 
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014年9月16日 上午10:46:00 
 * @version: V1.0   
 */
package com.lzxmy.demo.listtitleview;

import java.util.ArrayList;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @ClassName: ListViewAdapter
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月16日 上午10:46:00
 */
public class ListViewAdapter extends BaseAdapter {

	private ArrayList<TestModel> list;
	private Context activity;

	public ListViewAdapter(ArrayList<TestModel> list, Context activity) {
		super();
		this.list = list;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (list.get(position).isTitle()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView;
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(
					R.layout.item_text_view, null);
			textView = (TextView) convertView.findViewById(R.id.item_info);
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}
		textView.setText(list.get(position).getInfo());
		if (list.get(position).isTitle()) {
			textView.setBackgroundColor(activity.getResources().getColor(
					android.R.color.darker_gray));
			textView.setTextColor(Color.WHITE);
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(0, textView.getPaddingTop(), 0,
					textView.getPaddingBottom());
		} else {
			textView.setBackgroundColor(Color.WHITE);
			textView.setTextColor(Color.BLACK);
			textView.setGravity(Gravity.LEFT);
			textView.setPadding(40, textView.getPaddingTop(), 0,
					textView.getPaddingBottom());
		}
		return convertView;
	}

}
