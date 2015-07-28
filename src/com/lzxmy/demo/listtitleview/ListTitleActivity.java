package com.lzxmy.demo.listtitleview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ListTitleActivity extends Activity {

	private ListView listView;
	private TextView titleText;
	private String[] strList = { "fewq", "vaerq", "irnd", "xcbfqw", "nrt",
			"ves", "eyu", "piyh", "ong", "ghje", "tenszb", "qwegmj", "sdvfw",
			"qwj", "tuyj", "fdh", "grthg", "hfdt", "ertsr", "bbxn", "cmbnx",
			"bnmd", "xzvbrfe", "refaq", "bsdfn", "gjtr", "mhfku", "bsfger",
			"cvzedw", "qwegn", "dfwe", "jkgm", "yet", "vadfga", "ryty" };
	private final static String[] TITLE = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", };
	private ArrayList<TestModel> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_title_layout);
		listView = (ListView) findViewById(R.id.listview);
		titleText = (TextView) findViewById(R.id.title_text);
		list = new ArrayList<TestModel>();
		for (String str : strList) {
			TestModel model = new TestModel();
			model.setInfo(str);
			list.add(model);
		}
		for (String str : TITLE) {
			TestModel model = new TestModel();
			model.setInfo(str);
			model.setTitle(true);
			list.add(model);
		}
		Collections.sort(list, new Comparator<TestModel>() {

			@Override
			public int compare(TestModel lhs, TestModel rhs) {
				// TODO Auto-generated method stub
				return lhs.getInfo().toLowerCase()
						.compareTo(rhs.getInfo().toLowerCase());
			}
		});
		ListViewAdapter adapter = new ListViewAdapter(list, this);
		listView.setAdapter(adapter);
		titleText.setText(list.get(0).getInfo());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position > parent.getFirstVisiblePosition()) {
//					infoText.setText(list.get(position).getInfo());
//					infoText.setVisibility(View.VISIBLE);
//					new Thread() {
//						public void run() {
//							try {
//								Thread.sleep(500);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							handler.sendEmptyMessage(0);
//						};
//					}.start();
				}
			}
		});
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem < list.size() && visibleItemCount > 0) {
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleText
							.getLayoutParams();
					View itemView = view.getChildAt(1);
					int top = 0;
					if (list.get(firstVisibleItem + 1).isTitle()) {
						top = itemView.getTop() - itemView.getHeight();
					}
					titleText.setText(list.get(firstVisibleItem).getInfo()
							.substring(0, 1).toUpperCase());
					params.setMargins(0, top, 0, 0);
					titleText.setLayoutParams(params);
				}
			}
		});
	}



}
