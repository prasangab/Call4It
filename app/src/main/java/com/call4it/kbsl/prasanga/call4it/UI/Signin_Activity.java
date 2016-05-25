package com.call4it.kbsl.prasanga.call4it.UI;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by root on 5/25/16.
 */
public class Signin_Activity extends AsyncTask<String,Void,String>{

    private Context context;


    //flag 0 means get and 1 means post.(By default it is get.)
    public Signin_Activity(Context context) {
        this.context = context;

    }

    @Override
    protected String doInBackground(String... arg0) {
     //means by Get Method

            try{
                String mobileno = (String)arg0[0];
                String password = (String)arg0[1];
                Log.w(mobileno,"mobileno");
                Log.w(password,"password");
                String link = "http://10.2.3.87/mysql.php?password="+password;

                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                //System.out.print(sb.toString());


                return sb.toString();
            }

            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }


    }

}
