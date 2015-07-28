package com.lzxmy.demo.Jumping;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lzxmy.demo.R;

/**
 * Created by apple on 15/7/17.
 */
public class JumMainActivity extends Activity{
    private JumpingBeans jumpingBeans1, jumpingBeans2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumping_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Here you can see that we don't duplicate trailing dots on the text (we reuse
        // them or, if it's an ellipsis character, replace it with three dots and animate
        // those instead)
        final TextView textView1 = (TextView) findViewById(R.id.textView);
        jumpingBeans1 = JumpingBeans.with(textView1)
                .appendJumpingDots()
                .build();

        // Note that, even though we access textView2's text when starting to work on
        // the animation builder, we don't alter it in any way, so we're ok
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        jumpingBeans2 = JumpingBeans.with(textView2)
                .makeTextJump(0, textView2.getText().toString().indexOf(' '))
                .setIsWave(false)
                .setLoopDuration(1000)
                .build();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jumpingBeans1.stopJumping();
        jumpingBeans2.stopJumping();
    }
}
