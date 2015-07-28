package com.lzxmy.demo.damp;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DampActivity extends Activity {
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dampview_layout);
		setupView();
	}

	public void setupView() {
		img = (ImageView) findViewById(R.id.img);
		DampView view = (DampView) findViewById(R.id.dampview);
		view.setImageView(img);
	}
	
	public void onImgClick(View view){
		Toast.makeText(this, "单击背景", Toast.LENGTH_SHORT).show();
	}
	
	public void onPhotoClick(View view){
		Toast.makeText(this, "单击图像", Toast.LENGTH_SHORT).show();
	}

}
