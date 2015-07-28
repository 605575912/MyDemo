package com.lzxmy.demo.draglistview;

import java.util.ArrayList;

import com.lzxmy.demo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private ArrayList<String> datas;
	private Context mContext;

	public MyAdapter(Context mContext, ArrayList<String> datas) {
		this.mContext = mContext;
		this.datas = datas;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	class VHolder {
		TextView tv_name;
	}

	class DHolder {
		TextView tv_name;
		Button bt_dele;
		RelativeLayout relative;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		String string = (String) getItem(position);
		if (string.indexOf("1") > -1) {
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		VHolder vHolder = null;
		DHolder dHolder = null;
		String str = datas.get(position);
		int type = getItemViewType(position);
		if (convertView == null) {
			switch (type) {
			case 0: {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.drag_main_layout, null);
				vHolder = new VHolder();
				vHolder.tv_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				convertView.setTag(vHolder);
			}
				break;
			case 1:
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.drag_item, null);
				dHolder = new DHolder();
				dHolder.tv_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				dHolder.bt_dele = (Button) convertView
						.findViewById(R.id.bt_dele);
				dHolder.relative = (RelativeLayout) convertView
						.findViewById(R.id.relative);
				convertView.setTag(dHolder);
				break;

			default:
				break;
			}

		} else {
			switch (type) {
			case 0:
				vHolder = (VHolder) convertView.getTag();
				break;
			case 1:
				dHolder = (DHolder) convertView.getTag();
				break;

			default:
				break;
			}

		}
		switch (type) {
		case 0: {
			vHolder.tv_name.setText(str);

		}
			break;
		case 1: {
			dHolder.tv_name.setText(str);
		}
			break;

		default:
			break;
		}
		return convertView;
	}

}
