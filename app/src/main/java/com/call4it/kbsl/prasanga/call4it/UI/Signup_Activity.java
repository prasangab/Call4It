package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.call4it.kbsl.prasanga.call4it.R;

import butterknife.InjectView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/16/16.
 */
public class Signup_Activity extends AppCompatActivity {

    private static final String TAG = "Signup_Activity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_mobile_number) EditText _mobileNoText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }


    public void signup(){
        Log.d(TAG, "Signup");

        if (!validate()){
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup_Activity.this, R.style.Base_Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String mobileNo = _mobileNoText.getText().toString();
        String password = _passwordText.getText().toString();

        //TODO: Implement signup login here

        Intent x = new Intent(this,Main_Activity.class);
        startActivity(x);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //on complete call either onSignupSuccess or onSignupFailed
                        //depending on success
                        onSignupSuccess();
                        //onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess(){
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String name = _nameText.getText().toString();
        String mobileNo = _mobileNoText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3 ){
            _nameText.setError("at least 3 characters");
            valid = false;
        }else {
            _nameText.setError(null);
        }
        if (mobileNo.isEmpty() || !Patterns.PHONE.matcher(mobileNo).matches()){
            _mobileNoText.setError("Enter valid mobile number");
            valid = false;
        }else {
            _mobileNoText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10 ){
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }else {
            _passwordText.setError(null);
        }
        return valid;
    }


}
