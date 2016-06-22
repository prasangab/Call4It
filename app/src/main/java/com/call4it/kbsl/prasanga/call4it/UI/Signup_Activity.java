package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.InjectView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/16/16.
 */
public class Signup_Activity extends AppCompatActivity {

    private static final String TAG = "Signup_Activity";

    InputStream is=null;
    String result=null;
    String line=null;
    int code;

    String name = "";
    String mobileNo = "";
    String password = "";

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
                Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
                Signup_Activity.this.finish();
            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);


        // signup login...............

        String name = _nameText.getText().toString();
        String mobileNo = _mobileNoText.getText().toString();
        String password = _passwordText.getText().toString();

        insertToDatabase(mobileNo, password, name);
    }


    private void insertToDatabase(String mobileNo, String password, String name) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String mobileno = params[0];
                String password = params[1];
                String name = params[2];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("mobileno", mobileno));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("name", name));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://10.2.3.87/insert.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();
                    Log.e("pass 1", "connection success ");


                } catch (IOException e) {

                    Log.e("Fail 1", e.toString());
                    Toast.makeText(getApplicationContext(), "Invalid IP Address",
                            Toast.LENGTH_LONG).show();

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                final ProgressDialog progressDialog = new ProgressDialog(Signup_Activity.this, R.style.Animation_AppCompat_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Creating Account...");
                progressDialog.show();

                if (result.equals("success")){
                    Toast.makeText(getBaseContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();

                    Intent x = new Intent(Signup_Activity.this,Splash_Activity.class);
                    startActivity(x);

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    onSignupSuccess();
                                    //onSignupFailed();
                                    progressDialog.dismiss();

                                }
                            }, 3000);
                }else
                {
                    Toast.makeText(getBaseContext(), "Sorry, Try Again", Toast.LENGTH_LONG).show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    //onSignupSuccess();
                                    onSignupFailed();
                                    progressDialog.dismiss();

                                }
                            }, 3000);
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(mobileNo,password, name);
    }


    public void onSignupSuccess(){
        Toast.makeText(getBaseContext(), "Signup success", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(){
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

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
        if (mobileNo.isEmpty() || mobileNo.length()>10 || !Patterns.PHONE.matcher(mobileNo).matches()){
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
