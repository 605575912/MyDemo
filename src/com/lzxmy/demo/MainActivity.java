package com.lzxmy.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lzxmy.demo.Jumping.JumMainActivity;
import com.lzxmy.demo.aidl.AidlActivity;
import com.lzxmy.demo.cirelist.CirleListActivity;
import com.lzxmy.demo.crop.CropMainActivity;
import com.lzxmy.demo.damp.DampActivity;
import com.lzxmy.demo.draglistview.DragListViewActivity;
import com.lzxmy.demo.drawArc.ArcActivity;
import com.lzxmy.demo.drawstart.StartActivity;
import com.lzxmy.demo.drawwelcome.MainActivity58;
import com.lzxmy.demo.foldingmenu.FoldingMenuActivity;
import com.lzxmy.demo.imgheadlistview.IMGHeaderActivity;
import com.lzxmy.demo.iosswitch.IosSwitchActivity;
import com.lzxmy.demo.layer.LayerDrawableActivity;
import com.lzxmy.demo.listtitleview.ListTitleActivity;
import com.lzxmy.demo.locationimage.LocationImageActivity;
import com.lzxmy.demo.matrix.MatrixAcitivity;
import com.lzxmy.demo.musicplay.MusciPlayActivity;
import com.lzxmy.demo.progress.ProgressActivity;
import com.lzxmy.demo.settview.SettingActivity;
import com.lzxmy.demo.slidingTab.SlidingTabActivity;
import com.lzxmy.demo.swithbutton.SwithActivity;
import com.lzxmy.demo.swithbuttongif.SwithButtonActivity;
import com.lzxmy.demo.waterdrop.WaterDropActivity;
import com.lzxmy.demo.wechat.WeChatActivity;
import com.lzxmy.demo.wireframe.WireFrameActivity;
import com.nostra13.universalimageloader.ServiceLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView lv_list;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐去电池等图标和一切修饰部分（状态栏部分）
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐去标题栏（程序的名字）
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // CatchHandler.getInstance().init(getApplicationContext());
        lv_list = (ListView) findViewById(R.id.lv_list);
        ServiceLoader.getInstance().Init(getApplicationContext());
        List<ChooseItem> datas = new ArrayList<ChooseItem>();
        datas.add(new ChooseItem("侧滑移动Item删除", 0));
        datas.add(new ChooseItem("仿QQ5.1侧滑底部菜单", 1));
        datas.add(new ChooseItem("折叠菜单菜单", 2));
        datas.add(new ChooseItem("圆形ListView", 3));
        datas.add(new ChooseItem("仿IOS设置页面", 4));
        datas.add(new ChooseItem("拖曳删除Item", 5));
        datas.add(new ChooseItem("微信左右滑动页面", 6));
        datas.add(new ChooseItem("自定义View", 7));
        datas.add(new ChooseItem("mp3播放", 8));
        datas.add(new ChooseItem("扫描本地图片", 9));
        datas.add(new ChooseItem("ListView 头部图片拉大", 10));
        datas.add(new ChooseItem("Martix 应用", 11));
        datas.add(new ChooseItem("AIDL应用", 12));
        datas.add(new ChooseItem("ListView仿QQ好友列表分段滑动", 13));
        datas.add(new ChooseItem("下拉放大图片", 14));
        datas.add(new ChooseItem("图片叠层", 15));
        datas.add(new ChooseItem("画五角星", 16));
        datas.add(new ChooseItem("MainActivity58", 17));
        datas.add(new ChooseItem("ArcActivity", 18));
        datas.add(new ChooseItem("按钮SwithActivity", 19));
        datas.add(new ChooseItem("跳动的文字", 20));
        datas.add(new ChooseItem("按钮SwithActivity", 21));
        datas.add(new ChooseItem("IOS设置按钮SwithActivity", 22));
        datas.add(new ChooseItem("文本部分变色可点击", 23));
        datas.add(new ChooseItem("滚动条", 24));
        datas.add(new ChooseItem("滚动条", 25));
        datas.add(new ChooseItem("截图", 26));
        Typeface typeFace = Typeface.createFromAsset(getAssets(),
                "fonts/SchmottoPlotto.ttf");
        adapter = new ListAdapter(MainActivity.this, datas, typeFace);
        lv_list.setAdapter(adapter);
        lv_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                ChooseItem chooseItem = (ChooseItem) arg0.getAdapter().getItem(
                        arg2);
                Intent intent = new Intent();
                switch (chooseItem.getTag()) {
                    case 0: {// 侧滑移动Item删除
                        intent.setClass(MainActivity.this,
                                DragListViewActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 1: {// 仿QQ5.1侧滑底部菜单
                        intent.setClass(
                                MainActivity.this,
                                com.lzxmy.demo.slidingpane.SlidingPaneActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 2: {// 折叠菜单菜单
                        intent.setClass(MainActivity.this,
                                FoldingMenuActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 3: {// 圆形ListView
                        intent.setClass(MainActivity.this, CirleListActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 4: {// 仿IOS设置页面
                        intent.setClass(MainActivity.this, SettingActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 5: {// 拖曳删除Item
                        intent.setClass(MainActivity.this, WaterDropActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 6: {// 微信左右滑动页面
                        intent.setClass(MainActivity.this, WeChatActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 7: {// 自定义View
                        intent.setClass(MainActivity.this, WireFrameActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 8: {// mp3播放
                        intent.setClass(MainActivity.this, MusciPlayActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 9: {// 扫描本地图片
                        intent.setClass(MainActivity.this,
                                LocationImageActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 10: {// ListView 头部图片拉大

                        intent.setClass(MainActivity.this, IMGHeaderActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 11: {// Martix 应用

                        intent.setClass(MainActivity.this, MatrixAcitivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 12: {// AIDL 应用

                        intent.setClass(MainActivity.this, AidlActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 13: {// ListView仿QQ好友列表分段滑动

                        intent.setClass(MainActivity.this, ListTitleActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 14: {// 下拉放大图片 应用

                        intent.setClass(MainActivity.this, DampActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 15: {// 图片叠层

                        intent.setClass(MainActivity.this, LayerDrawableActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 16: {// 图片叠层

                        intent.setClass(MainActivity.this, StartActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 17: {// MainActivity58

                        intent.setClass(MainActivity.this, MainActivity58.class);
                        startActivity(intent);

                    }
                    break;
                    case 18: {// 图片叠层

                        intent.setClass(MainActivity.this, ArcActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 19: {// 图片叠层

                        intent.setClass(MainActivity.this, SwithActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 20: {// 图片叠层

                        intent.setClass(MainActivity.this, JumMainActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 21: {// 图片叠层

                        intent.setClass(MainActivity.this, SwithButtonActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 22: {// 图片叠层

                        intent.setClass(MainActivity.this, IosSwitchActivity.class);
                        startActivity(intent);

                    }
                    break;

                    case 24: {// 图片叠层

                        intent.setClass(MainActivity.this, ProgressActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 25: {// 图片叠层

                        intent.setClass(MainActivity.this, SlidingTabActivity.class);
                        startActivity(intent);

                    }
                    break;
                    case 26: {// 截图

                        intent.setClass(MainActivity.this, CropMainActivity.class);
                        startActivity(intent);

                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }
}
