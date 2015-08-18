package com.lzxmy.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<ChooseItem> datas;
    Context context;
    private LayoutInflater listContainer;// 视图容器
    Typeface typeFace;

    public ListAdapter(Context context, List<ChooseItem> datas, Typeface typeFace) {
        this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
        this.datas = datas;
        this.context = context;
        this.typeFace = typeFace;
    }

    class VHolder {
        TextView tv_name;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return datas.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ChooseItem chooseItem = datas.get(position);
        VHolder vHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            convertView = listContainer.inflate(R.layout.listview, null);
            vHolder = new VHolder();
            vHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(vHolder);


        } else {
            vHolder = (VHolder) convertView.getTag();

        }
        SpannableStringBuilder style = new SpannableStringBuilder(
                chooseItem.getName());
        int len = 3 >= chooseItem.getName().length() ? chooseItem.getName().length() : 3;
        style.setSpan(new ForegroundColorSpan(Color.RED), 1,
                len,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        style.setSpan(new AbsoluteSizeSpan(23),1,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		style.setSpan(new StyleSpan(typeFace.)), 1,
//				chooseItem.getName().length() - 3,
//				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        vHolder.tv_name.setTypeface(typeFace);
        vHolder.tv_name.setText(style);

        return convertView;
    }

}
