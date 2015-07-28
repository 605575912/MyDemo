package com.lzxmy.demo.waterdrop;

import com.lzxmy.demo.R;
import com.lzxmy.demo.waterdrop.DropCover.OnDragCompeteListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class WaterDropActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waterdrop_layout);
		CoverManager.getInstance().init(this);

		ListView mList = (ListView) findViewById(R.id.list);
		mList.setAdapter(new DemoAdapter());

		CoverManager.getInstance().setMaxDragDistance(150);
		CoverManager.getInstance().setExplosionTime(150);
	}

	class DemoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 99;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			convertView = LayoutInflater.from(WaterDropActivity.this).inflate(
					R.layout.waterdrop_list_item, null);
			WaterDrop drop = (WaterDrop) convertView.findViewById(R.id.drop);
			drop.setText(String.valueOf(position));

			drop.setOnDragCompeteListener(new OnDragCompeteListener() {

				@Override
				public void onDrag() {
					Toast.makeText(WaterDropActivity.this, "remove:" + position,
							Toast.LENGTH_SHORT).show();
				}
			});

			return convertView;
		}
	}
}
