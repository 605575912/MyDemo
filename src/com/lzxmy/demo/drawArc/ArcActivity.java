package com.lzxmy.demo.drawArc;

import com.lzxmy.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArcActivity extends Activity {
	SeekCircle seekCircle;
	TextView textProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arc_main);
		textProgress = (TextView) findViewById(R.id.textProgress);
		seekCircle = (SeekCircle) findViewById(R.id.seekCircle);
		seekCircle
				.setOnSeekCircleChangeListener(new SeekCircle.OnSeekCircleChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekCircle seekCircle) {
					}

					@Override
					public void onStartTrackingTouch(SeekCircle seekCircle) {
					}

					@Override
					public void onProgressChanged(SeekCircle seekCircle,
							int progress, boolean fromUser) {
						updateText();
					}
				});

		updateText();
	}

	private void updateText() {

		if (textProgress != null && seekCircle != null) {
			int progress = seekCircle.getProgress();
			textProgress.setText(Integer.toString(progress) + "%");
		}
	}
}
