package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.call4it.kbsl.prasanga.call4it.R;

/**
 * Created by root on 6/9/16.
 */
public class Splash_Activity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private static final String TAG = Splash_Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        navigateToHome();
    }


    private void navigateToHome(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Activity.this, Main_Activity.class);
                Splash_Activity.this.startActivity(intent);
                Splash_Activity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
