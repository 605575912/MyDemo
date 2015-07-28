package com.lzxmy.demo.drawwelcome;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity58 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this));
	}
}
