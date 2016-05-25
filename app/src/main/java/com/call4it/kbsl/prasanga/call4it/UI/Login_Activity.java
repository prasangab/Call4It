package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.call4it.kbsl.prasanga.call4it.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import butterknife.InjectView;
import butterknife.ButterKnife;
/**
 * Created by root on 5/16/16.
 */
public class Login_Activity extends AppCompatActivity{

    private static final String TAG = "Login_Activity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_mobile_number) EditText _mobileNumberText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    Signin_Activity sa = new Signin_Activity(Login_Activity.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // start the signup activity
                Intent intent = new Intent(getApplicationContext(), Signup_Activity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login(){
        Log.d(TAG, "Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this, R.style.Animation_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        String mobileNo = _mobileNumberText.getText().toString();
        String password = _passwordText.getText().toString();

        String result= "";

        // TODO: Implement authentication logic here


        sa.execute(mobileNo, password);

        try {
            result = sa.get();
            Log.w(result, "result");

            if (result.equals(mobileNo)){

                //Log.w("jhfjsdgfk","gdsgads");
                Intent x = new Intent(this,Main_Activity.class);
                startActivity(x);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                onLoginSuccess();
                                //onLoginFailed();
                                progressDialog.dismiss();

                            }
                        }, 3000);
            }
            else {
                //Log.w(result,"result");
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                //onLoginSuccess();
                                onLoginFailed();
                                progressDialog.dismiss();

                            }
                        }, 3000);

                //setContentView(R.layout.login_layout);
                //ButterKnife.inject(this);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){

                //TODO: implement successfull signup logic here
                //by default i just finish the Activity and log them in automtically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the Main_Activity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(){
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed(){
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String mobileNo = _mobileNumberText.getText().toString();
        String password = _passwordText.getText().toString();

        if(mobileNo.isEmpty() || !Patterns.PHONE.matcher(mobileNo).matches()){
            _mobileNumberText.setError("Enter a valid mobile number");
            valid = false;
        }else {
            _mobileNumberText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10){
            _passwordText.setError("between 4 and 10 alphanumeric charaters");
            valid = false;
        }else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
