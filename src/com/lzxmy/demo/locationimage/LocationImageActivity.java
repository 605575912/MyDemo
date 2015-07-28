package com.lzxmy.demo.locationimage;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.widget.GridView;
import android.widget.Toast;

import com.lzxmy.demo.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LocationImageActivity extends Activity {
	private List<String> img_path = new ArrayList<String>();
	GridView mgGridView;
	PhotoAdapter adapter;
	private HashSet<String> mDirPaths = new HashSet<String>();// 临时的辅助类，用于防止同一个文件夹的多次扫描
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationimage_layout);
		mgGridView = (GridView) findViewById(R.id.main_grid);
//		ServiceLoader.getInstance().displayImage(null, "http://g.hiphotos.baidu.com/image/pic/item/ac4bd11373f082029c2883f649fbfbedaa641b44.jpg", new ImageView(LocationImageActivity.this));
		getImages();
	}

	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
	 */
	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}

		// 显示进度条
		// mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		new Thread(new Runnable() {

			@Override
			public void run() {
				String firstImage = null;
				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = LocationImageActivity.this
						.getContentResolver();
				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaColumns.MIME_TYPE + "=? or "
								+ MediaColumns.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaColumns.DATE_MODIFIED);

				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaColumns.DATA));
					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = path;
					// 获取该图片的父路径名
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					// 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
					if (mDirPaths.contains(dirPath)){
						continue;
					} else{
						mDirPaths.add(dirPath);
						// 初始化imageFloder
//						imageFloder = new ImageFloder();
//						imageFloder.setDir(dirPath);
//						imageFloder.setFirstImagePath(path);
					}
					img_path.add(path);
					if(parentFile.list()==null)continue ;
					int picSize = parentFile.list(new FilenameFilter(){
						@Override
						public boolean accept(File dir, String filename){
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;

				}

//				img_path.add("http://h.hiphotos.baidu.com/image/pic/item/bba1cd11728b471026c5ee05c1cec3fdfd032314.jpg");
//				img_path.add("http://g.hiphotos.baidu.com/image/pic/item/ac4bd11373f082029c2883f649fbfbedaa641b44.jpg");
//				img_path.add("http://img0.bdstatic.com/img/image/1%E8%A5%BF%E6%B8%B8%E8%AE%B0%E2%80%94%E2%80%94%E8%A5%BF%E7%8F%AD%E7%89%99.jpg");
//				img_path.add("http://img0.bdstatic.com/img/image/%E5%B7%B4%E8%A5%BF.jpg");
//				img_path.add("http://img0.bdstatic.com/img/image/%E7%91%9E%E5%A3%AB-%E5%BE%B7%E5%9B%BD%E8%87%AA%E7%94%B1%E8%A1%8C.jpg");
//				img_path.add("http://img0.bdstatic.com/img/image/%E7%BE%8E%E5%9B%BD%E5%90%8D%E6%A0%A1.jpg");
//				img_path.add("http://img0.bdstatic.com/img/image/%E8%8B%B1%E5%9B%BD%E5%9F%8E%E5%A0%A1%E5%A4%A7%E5%AD%A6.jpg");
//				img_path.add("http://b.hiphotos.baidu.com/image/w%3D310/sign=5ae31504fadcd100cd9cfe20428b47be/c995d143ad4bd11312d4083b58afa40f4bfb0599.jpg");
//				img_path.add("http://b.hiphotos.baidu.com/image/w%3D310/sign=ddc3d9511e950a7b753548c53ad0625c/6a600c338744ebf88b87e658daf9d72a6159a7e7.jpg");
//				img_path.add("http://h.hiphotos.baidu.com/image/w%3D310/sign=b2e220bfb0b7d0a27bc9029cfbee760d/2cf5e0fe9925bc3143b64d955cdf8db1ca1370d7.jpg");
//				img_path.add("http://b.hiphotos.baidu.com/image/w%3D310/sign=1588dc341bd8bc3ec60800cbb28ba6c8/0ff41bd5ad6eddc428d3ddf53bdbb6fd52663383.jpg");
//				img_path.add("http://d.hiphotos.baidu.com/image/w%3D310/sign=0dda470faa773912c4268360c8188675/a1ec08fa513d2697a23709ef56fbb2fb4216d898.jpg");
//				img_path.add("http://d.hiphotos.baidu.com/image/w%3D310/sign=a82ebc1ecdfc1e17fdbf8a307a91f67c/10dfa9ec8a1363278957c93b938fa0ec08fac704.jpg");
//				img_path.add("http://c.hiphotos.baidu.com/image/w%3D310/sign=0094081cca95d143da76e22243f08296/b21c8701a18b87d6654c4f00050828381f30fda7.jpg");
//				img_path.add("http://h.hiphotos.baidu.com/image/w%3D310/sign=38032f0f99504fc2a25fb604d5dde7f0/18d8bc3eb13533fa95a63029aad3fd1f41345ba0.jpg");
//				img_path.add("http://imgs.meimeinice.com/imgs/db19f5c199009f8475f46c0d781f208d.jpg!small");
//				img_path.add("http://a.hiphotos.baidu.com/image/w%3D310/sign=ffa827ff7f1ed21b79c928e49d6eddae/0b55b319ebc4b7453b95011bcdfc1e178a8215bd.jpg");
//				img_path.add("http://d.hiphotos.baidu.com/image/w%3D310/sign=028afd7ad42a60595210e71b1835342d/2fdda3cc7cd98d10526c1d82223fb80e7aec90cc.jpg");
//				img_path.add("http://h.hiphotos.baidu.com/image/w%3D310/sign=8a9320779058d109c4e3afb3e159ccd0/fd039245d688d43fd053d2fb7f1ed21b0ff43bc4.jpg");
//				img_path.add("http://f.hiphotos.baidu.com/image/pic/item/023b5bb5c9ea15ced382a404b4003af33a87b24b.jpg");
//				img_path.add("http://a.hiphotos.baidu.com/image/pic/item/50da81cb39dbb6fdeab1f0020a24ab18972b372f.jpg");
//				img_path.add("http://a.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc4e187a5483adbb6fd52663324.jpg");
//				img_path.add("http://d.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81037f3d5bbf3eb13533fa403c.jpg");
//				img_path.add("http://h.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3ee049fb6173cf3bc79f3d5658.jpg");
				handler.sendEmptyMessage(0);
				mDirPaths = null;
					mCursor.close();
				// 通知Handler扫描图片完成

				
			}
		}).start();
	}



	Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				adapter = new PhotoAdapter(getApplicationContext(), img_path);
				mgGridView.setAdapter(adapter);
			} else if (msg.what == 1) {
				adapter.notifyDataSetChanged();
			}

		};
	};


}
