package com.lzxmy.demo.draglistview;

import java.util.ArrayList;

import com.lzxmy.demo.draglistview.CYDragListView.CYDragListener;


import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

public class DragListViewActivity extends Activity implements CYDragListener {
	private CYDragListView listView;
	MyAdapter adapter ;
	ArrayList<String> datas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new CYDragListView(this);
		listView.setDragListener(this);
		String aa[] = { "items1", "item2", "items1", "item4", "items5",
				"item6", "items7", "item1", "items9", "item10", "items11",
				"item12" };
		datas = new ArrayList<String>();
		for(int i = 0; i < aa.length; i ++){
			datas.add(aa[i]);
			
		}
		adapter = new MyAdapter(this, datas);
		listView.setAdapter(adapter);
//		listView.setSelector(null);
		setContentView(listView);

	}

	@Override
	public void onDragFinsh(int position) {
		Toast.makeText(DragListViewActivity.this,
				 "delete:" + position, Toast.LENGTH_SHORT)
				.show();
		datas.remove(position);
		adapter.notifyDataSetChanged();
		
	}

}
