package com.lzxmy.demo.swithbutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.lzxmy.demo.R;

/**
 * Created by apple on 15/7/10.
 */
public class SwithActivity extends Activity{
    SwitchButton bt_switch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swithbutton);
        bt_switch = (SwitchButton) findViewById(R.id.bt_switch);
        bt_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }
}
