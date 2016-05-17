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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.call4it.kbsl.prasanga.call4it.R;
import com.call4it.kbsl.prasanga.call4it.application.Call4ItApplication;

public class Main_Activity extends Activity implements View.OnClickListener{



    Call4ItApplication application;

    // activity components
    RelativeLayout sltrainbowpageslayout;
    RelativeLayout mobileticketslayout;
    RelativeLayout accidentcoverlayout;
    RelativeLayout exidebatmobilelayout;
    //Button summaryButton;
    //Button settingsButton;
    RelativeLayout logout;
    
    TextView logoutText;
    TextView sltrainbowpagesText;
    //TextView sltrainbowpagesIcon;
    TextView mobileticketsText;
    //TextView mobileticketsIcon;
    TextView accidentcoverText;
    //TextView accidentcoverIcon;
    TextView exidebatmobileText;
    //TextView exidebatmobileIcon;
    TextView call4itIcon;

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
        mobileticketslayout = (RelativeLayout) findViewById(R.id.mobile_tickets_layout);
        accidentcoverlayout = (RelativeLayout) findViewById(R.id.accident_cover_layout);
        exidebatmobilelayout = (RelativeLayout) findViewById(R.id.exide_bat_mobile_layout);
        logout = (RelativeLayout) findViewById(R.id.logout_layout);

        // set custom font
        logoutText = (TextView) findViewById(R.id.logout_text);
        logoutText.setTypeface(typeface, Typeface.BOLD);

        //sltrainbowpagesIcon = (TextView) findViewById(R.id.slt_rainbow_pages_icon);
        sltrainbowpagesText = (TextView) findViewById(R.id.slt_rainbow_pages_text);
        //sltrainbowpagesIcon.setTypeface(typeface, Typeface.BOLD);
        sltrainbowpagesText.setTypeface(typeface, Typeface.BOLD);

        mobileticketsText = (TextView) findViewById(R.id.mobile_tickets_text);
        //mobileticketsIcon = (TextView) findViewById(R.id.mobile_tickets_text);
        //mobileticketsIcon.setTypeface(typeface, Typeface.BOLD);
        mobileticketsText.setTypeface(typeface, Typeface.BOLD);

        //accidentcoverIcon = (TextView) findViewById(R.id.accident_cover_icon);
        accidentcoverText = (TextView) findViewById(R.id.accident_cover_text);
        //accidentcoverIcon.setTypeface(typeface, Typeface.BOLD);
        accidentcoverText.setTypeface(typeface, Typeface.BOLD);

        //exidebatmobileIcon = (TextView) findViewById(R.id.exide_bat_mobile_icon);
        exidebatmobileText = (TextView) findViewById(R.id.exide_bat_mobile_text);
        //exidebatmobileIcon.setTypeface(typeface, Typeface.BOLD);
        exidebatmobileText.setTypeface(typeface, Typeface.BOLD);

        call4itIcon = (TextView) findViewById(R.id.call4it_icon);
        call4itIcon.setTypeface(typeface, Typeface.BOLD);

        sltrainbowpageslayout.setOnClickListener(Main_Activity.this);
        mobileticketslayout.setOnClickListener(Main_Activity.this);
        accidentcoverlayout.setOnClickListener(Main_Activity.this);
        exidebatmobilelayout.setOnClickListener(Main_Activity.this);
        logout.setOnClickListener(Main_Activity.this);


    }


    @Override
    public void onClick(View v) {

        if (v==sltrainbowpageslayout){
            //display rainbow pages activity
            startActivity(new Intent(Main_Activity.this, SltRainbowPages_Activity.class));
            Main_Activity.this.finish();
        }else if (v==mobileticketslayout){
            //display mobile tickets activity
            startActivity(new Intent(Main_Activity.this, MobileTickets_Activity.class));
            Main_Activity.this.finish();
        }else if (v==accidentcoverlayout){
            //display accident cover activity
            startActivity(new Intent(Main_Activity.this, AccidentCover_Activity.class));
            Main_Activity.this.finish();
        }else if (v==exidebatmobilelayout){
            //display exide bat mobile activity
            startActivity(new Intent(Main_Activity.this, ExideBatMobile_Activity.class));
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
