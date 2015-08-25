package com.lzxmy.demo.progressbutton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lzxmy.demo.R;

/**
 * State Change Sample
 */
public class Sample3Activity extends Activity {

    public static void startThisActivity(Activity activity) {
        activity.startActivity(new Intent(activity, Sample3Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sample_3);



        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
                    circularButton1.setProgress(100);
                } else {
                    circularButton1.setProgress(0);
                }
            }
        });

        final CircularProgressButton circularButton2 = (CircularProgressButton) findViewById(R.id.circularButton2);
        circularButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton2.getProgress() == 0) {
                    circularButton2.setProgress(-1);
                } else {
                    circularButton2.setProgress(0);
                }
            }
        });
    }
}
