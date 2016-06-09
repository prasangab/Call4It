package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.call4it.kbsl.prasanga.call4it.R;

/**
 * Created by root on 6/9/16.
 */
public class MovieTickets_Activity extends Activity implements View.OnClickListener {

    RelativeLayout back;
    RelativeLayout done;
    TextView lineText;

    private static final String TAG = MovieTickets_Activity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movietickets_layout);
        init();
    }

    @Override
    public void onClick(View v) {
        if (v==back){
            startActivity(new Intent(MovieTickets_Activity.this, Main_Activity.class));
            MovieTickets_Activity.this.finish();
        }else if (v==done){
            // implement what will happen after done...

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MovieTickets_Activity.this, Main_Activity.class));
        MovieTickets_Activity.this.finish();
    }

    public void init(){
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/vegur_2.otf");

        back = (RelativeLayout) findViewById(R.id.balance_query_layout_back);
        done = (RelativeLayout) findViewById(R.id.balance_query_layout_get_balance);

        back.setOnClickListener(MovieTickets_Activity.this);
        done.setOnClickListener(MovieTickets_Activity.this);

        // set custom font for text
        lineText = (TextView) findViewById(R.id.balance_query_account_no);
        lineText.setTypeface(face);

    }
}