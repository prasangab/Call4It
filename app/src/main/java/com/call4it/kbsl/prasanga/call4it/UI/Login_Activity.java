package com.call4it.kbsl.prasanga.call4it.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.InjectView;
import butterknife.ButterKnife;
/**
 * Created by root on 5/16/16.
 */
public class Login_Activity extends AppCompatActivity {

    InputStream is=null;
    String result=null;
    String line=null;
    int code;

    private static final String TAG = "Login_Activity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_mobile_number)
    EditText _mobileNumberText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;

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

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start the signup activity
                Intent intent = new Intent(getApplicationContext(), Signup_Activity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                Login_Activity.this.finish();
            }
        });
    }

    // login ................
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }


        _loginButton.setEnabled(false);


        // signup login...............


        String mobileNo = _mobileNumberText.getText().toString();
        String password = _passwordText.getText().toString();

        insertToDatabase(mobileNo, password);
    }


    private void insertToDatabase(String mobileNo, String password) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String mobileno = params[0];
                String password = params[1];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("mobileno", mobileno));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.2.3.181/check.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    Log.e("pass 1", "connection success ");

                } catch (Exception e) {
                    Log.e("Fail 1", e.toString());
                    Toast.makeText(getApplicationContext(), "Invalid IP Address",
                            Toast.LENGTH_LONG).show();
                }

                try {
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                    Log.e("pass 2", "connection success ");
                    //return result;
                    JSONObject json_data = new JSONObject(result);
                    return json_data.getString("result");
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                final ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this, R.layout.progress_dialog_layout);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                String password = _passwordText.getText().toString();

                if (result.equals(password)) {
                    Toast.makeText(getBaseContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent x = new Intent(Login_Activity.this, Splash_Activity.class);
                    startActivity(x);

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    onLoginSuccess();
                                    //onSignupFailed();
                                    progressDialog.dismiss();

                                }
                            }, 3000);
                } else {
                    Toast.makeText(getBaseContext(), "Sorry, Try Again", Toast.LENGTH_LONG).show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    //onSignupSuccess();
                                    onLoginFailed();
                                    progressDialog.dismiss();

                                }
                            }, 3000);
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(mobileNo, password);
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
        //Toast.makeText(getBaseContext(), "Welcome to home page", Toast.LENGTH_LONG).show();
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
