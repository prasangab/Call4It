package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.call4it.kbsl.prasanga.call4it.R;
import com.call4it.kbsl.prasanga.call4it.application.Call4ItApplication;

public class Main_Activity extends Activity implements View.OnClickListener{



    Call4ItApplication application;

    // activity components
    RelativeLayout sltrainbowpageslayout;
    RelativeLayout busticketslayout;
    RelativeLayout trainticketslayout;
    RelativeLayout slcricketticketslayout;
    RelativeLayout logout;
    
    TextView logoutText;
    TextView sltrainbowpagesText;
    //TextView sltrainbowpagesIcon;
    TextView busticketsText;
    //TextView mobileticketsIcon;
    TextView trainticketsText;
    //TextView accidentcoverIcon;
    TextView slcricketticketsText;
    //TextView exidebatmobileIcon;
    TextView call4itIcon;

    //ImageView trainticketIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        init();

    }


    //initialize activity components....

    public void init(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/vegur_2.otf");
        application = (Call4ItApplication) Main_Activity.this.getApplication();

        sltrainbowpageslayout = (RelativeLayout) findViewById(R.id.slt_rainbow_pages_layout);
        busticketslayout = (RelativeLayout) findViewById(R.id.bus_tickets_layout);
        trainticketslayout = (RelativeLayout) findViewById(R.id.train_tickets_layout);
        slcricketticketslayout = (RelativeLayout) findViewById(R.id.slcricket_tickets_layout);
        logout = (RelativeLayout) findViewById(R.id.logout_layout);

        // set custom font
        logoutText = (TextView) findViewById(R.id.logout_text);
        logoutText.setTypeface(typeface, Typeface.BOLD);

        //sltrainbowpagesIcon = (TextView) findViewById(R.id.slt_rainbow_pages_icon);
        sltrainbowpagesText = (TextView) findViewById(R.id.slt_rainbow_pages_text);
        //sltrainbowpagesIcon.setTypeface(typeface, Typeface.BOLD);
        sltrainbowpagesText.setTypeface(typeface, Typeface.BOLD);

        busticketsText = (TextView) findViewById(R.id.bus_tickets_text);
        //mobileticketsIcon = (TextView) findViewById(R.id.mobile_tickets_text);
        //mobileticketsIcon.setTypeface(typeface, Typeface.BOLD);
        busticketsText.setTypeface(typeface, Typeface.BOLD);

        //trainticketIcon = (ImageView) findViewById(R.id.train_tickets_icon);
        trainticketsText = (TextView) findViewById(R.id.train_tickets_text);
        //accidentcoverIcon.setTypeface(typeface, Typeface.BOLD);
        trainticketsText.setTypeface(typeface, Typeface.BOLD);

        //exidebatmobileIcon = (TextView) findViewById(R.id.exide_bat_mobile_icon);
        slcricketticketsText = (TextView) findViewById(R.id.slcricket_tickets_text);
        //exidebatmobileIcon.setTypeface(typeface, Typeface.BOLD);
        slcricketticketsText.setTypeface(typeface, Typeface.BOLD);

        call4itIcon = (TextView) findViewById(R.id.call4it_icon);
        call4itIcon.setTypeface(typeface, Typeface.BOLD);

        sltrainbowpageslayout.setOnClickListener(Main_Activity.this);
        busticketslayout.setOnClickListener(Main_Activity.this);
        trainticketslayout.setOnClickListener(Main_Activity.this);
        slcricketticketslayout.setOnClickListener(Main_Activity.this);
        logout.setOnClickListener(Main_Activity.this);

        //trainticketIcon.setImageResource(R.drawable.AWT_Bus);


    }


    @Override
    public void onClick(View v) {

    if (v==sltrainbowpageslayout){
            //display rainbow pages activity
            startActivity(new Intent(Main_Activity.this, SltRainbowPages_Activity.class));
            Main_Activity.this.finish();
        }else if (v==busticketslayout){
            //display mobile tickets activity
            startActivity(new Intent(Main_Activity.this, BusTickets_Activity.class));
            Main_Activity.this.finish();
        }else if (v==trainticketslayout){
            //display accident cover activity
            startActivity(new Intent(Main_Activity.this, TrainTickets_Activity.class));
            Main_Activity.this.finish();
        }else if (v==slcricketticketslayout){
            //display exide bat mobile activity
            startActivity(new Intent(Main_Activity.this, SLCricketTickets_Activity.class));
            Main_Activity.this.finish();
        }else if (v==logout){
            displayInformationMessageDialog("Are you sure want to logout?");
        }
    }

    // display message dialog when user going to logout

    public void displayInformationMessageDialog(String message){
        final Dialog dialog = new Dialog(Main_Activity.this);

        //set layout for dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.shareconfirmmessagedialog_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        //set dialog texts
        TextView messageHeaderTextView = (TextView) dialog.findViewById(R.id.information_message_dialog_layout_message_header_text);
        TextView messageTextView = (TextView) dialog.findViewById(R.id.information_message_dialog_layout_message_text);
        messageTextView.setText(message);

        //set custom font
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/vegur_2.otf");
        messageHeaderTextView.setTypeface(face);
        messageHeaderTextView.setTypeface(null, Typeface.BOLD);
        messageTextView.setTypeface(face);

        //set ok button
        Button okButton = (Button) dialog.findViewById(R.id.information_message_dialog_layout_ok_button);
        okButton.setTypeface(face);
        okButton.setTypeface(null, Typeface.BOLD);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // back to login activity
                //startActivity(new Intent(MobileBankActivity.this, LoginActivity.class));
                Main_Activity.this.finish();
                dialog.cancel();
            }
        });

        //set cancel button
        Button cancelButton = (Button) dialog.findViewById(R.id.information_message_dialog_layout_cancel_button);
        cancelButton.setTypeface(face);
        cancelButton.setTypeface(null, Typeface.BOLD);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //display toast message

    public void displayToast(String message){
        Toast.makeText(Main_Activity.this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        displayInformationMessageDialog("Are you sure want to logout?");
    }
}
