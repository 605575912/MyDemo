package com.lzxmy.demo.settview;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;
import com.lzxmy.demo.settview.SettingView.onSettingViewItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class SettingActivity extends Activity {

	private SettingView mSettingView = null;

	private SettingData mItemData = null;
	private SettingViewItemData mItemViewData = null;
	private List<SettingViewItemData> mListData = new ArrayList<SettingViewItemData>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingview_layout);

		mSettingView = (SettingView) findViewById(R.id.main_setting_view);
		mSettingView.setOnSettingViewItemClickListener(new onSettingViewItemClickListener() {

			@Override
			public void onItemClick(int index) {
				// TODO Auto-generated method stub
				switch (index) {
				case 0:
					startActivity(new Intent(SettingActivity.this, QQStyleActivity.class));
					break;
				case 1:
					startActivity(new Intent(SettingActivity.this, IosStyleActivity.class));

					break;
				case 2:
					startActivity(new Intent(SettingActivity.this, XMLLayoutActivity.class));

					break;

				default:
					break;
				}
			}
		});

		findViewById(R.id.qr_code).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://github.com/SkillCollege"));
				startActivity(intent);
			}
		});

		initView();
	}

	private void initView() {
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("仿QQ界面效果");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(SettingActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("仿iOS设置界面");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(SettingActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("单个Item布局效果");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(SettingActivity.this));
		mListData.add(mItemViewData);

		mSettingView.setAdapter(mListData);
	}
}
