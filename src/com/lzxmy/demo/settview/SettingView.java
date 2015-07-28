package com.lzxmy.demo.settview;

import java.util.ArrayList;
import java.util.List;

import com.lzxmy.demo.R;
import com.lzxmy.demo.settview.SwitchItemView.onSwitchItemChangedListener;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;


public class SettingView extends LinearLayout {

	private Context mContext;
	private ImageView mTopDivider;
	private ImageView mBottomDivider;

	private boolean iOSStyleable = true;
	private List<SettingViewItemData> mItemViews = null;

	private onSettingViewItemClickListener mItemClickListener;
	private onSettingViewItemSwitchListener mItemSwitchListener;

	public SettingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mItemViews = new ArrayList<SettingViewItemData>();
		setOrientation(LinearLayout.VERTICAL);

		mTopDivider = new ImageView(context);
		mTopDivider.setBackgroundResource(R.drawable.divider);

		mBottomDivider = new ImageView(context);
		mBottomDivider.setBackgroundResource(R.drawable.divider);

		readAttrs(attrs);
	}

	private void readAttrs(AttributeSet attrs) {
		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.SettingView);

		if (a.hasValue(R.styleable.SettingView_iOSStyle)) {
			iOSStyleable = a.getBoolean(R.styleable.SettingView_iOSStyle, false);
		}

		a.recycle();
	}

	public void setAdapter(List<SettingViewItemData> listData) {
		if (!listData.isEmpty()) {
			mItemViews = listData;

			int size = mItemViews.size();

			LinearLayout.LayoutParams dividerLps = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			// Add Top Divider
			addView(mTopDivider, dividerLps);
			// Add Content
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					SettingViewItemData item = mItemViews.get(i);
					initItemView(item, i);
					if (i != (size - 1)) {
						addDivider(iOSStyleable);
					}
				}
			}
			// Add Bottom Divider
			addView(mBottomDivider, dividerLps);
		}
	}

	private void addDivider(boolean iOSStylable) {
		ImageView divider = new ImageView(mContext);
		divider.setScaleType(ScaleType.FIT_XY);
		divider.setBackgroundColor(getResources().getColor(R.color.setting_view_item_bg_normal));
		divider.setImageResource(R.drawable.divider);

		LayoutParams lps = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lps.gravity = Gravity.RIGHT;

		if (iOSStyleable) {
			int paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.setting_view_min_height), getResources()
					.getDisplayMetrics())
					+ (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.setting_view_lr_padding), getResources()
							.getDisplayMetrics());
			divider.setPadding(paddingLeft, 0, 0, 0);
		}

		addView(divider, lps);
	}

	private void initItemView(SettingViewItemData data, final int index) {
		FrameLayout itemView = data.getItemView();

		if (itemView instanceof SwitchItemView) {
			((SwitchItemView) itemView).fillData(data.getData());
			((SwitchItemView) itemView).setOnSwitchItemChangedListener(new onSwitchItemChangedListener() {

				@Override
				public void onSwitchItemChanged(boolean isChecked) {
					// TODO Auto-generated method stub
					if (null != mItemSwitchListener) {
						mItemSwitchListener.onSwitchChanged(index, isChecked);
					}
				}
			});
			itemView.setClickable(false);
		} else {
			itemView.setClickable(data.isClickable());
			itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (null != mItemClickListener) {
						mItemClickListener.onItemClick(index);
					}
				}
			});

			if (itemView instanceof BasicItemViewH) {
				((BasicItemViewH) itemView).fillData(data.getData());
			} else if (itemView instanceof BasicItemViewV) {
				((BasicItemViewV) itemView).fillData(data.getData());
			} else if (itemView instanceof ImageItemView) {
				((ImageItemView) itemView).fillData(data.getData());
			} else if (itemView instanceof CheckItemViewH) {
				((CheckItemViewH) itemView).fillData(data.getData());
			} else if (itemView instanceof CheckItemViewV) {
				((CheckItemViewV) itemView).fillData(data.getData());
			}
		}

		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.setting_view_min_height), getResources()
				.getDisplayMetrics());
		LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, height);

		addView(itemView, lps);
	}

	public interface onSettingViewItemClickListener {
		void onItemClick(int index);
	}

	public void setOnSettingViewItemClickListener(onSettingViewItemClickListener listener) {
		this.mItemClickListener = listener;
	}

	public interface onSettingViewItemSwitchListener {
		public void onSwitchChanged(int index, boolean isChecked);
	}

	public void setOnSettingViewItemSwitchListener(onSettingViewItemSwitchListener listener) {
		this.mItemSwitchListener = listener;
	}
}
