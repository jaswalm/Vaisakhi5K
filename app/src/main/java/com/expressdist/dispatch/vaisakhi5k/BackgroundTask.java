package com.expressdist.dispatch.vaisakhi5k;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Manvir Jaswal on 2/3/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;

    private static final String TAG = "LoginActivity";

    String link = "http://localhost/5k/php/login.php";
    URL url;
    HttpURLConnection conn;
    BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        final ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }



    @Override
    protected String doInBackground(String... params) {
        String result="";

        try {
            String email = params[0];

            String password = params[1];
            url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty( "charset", "utf-8");
            OutputStream out = conn.getOutputStream();
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            String data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                    URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            buffer.write(data);
            buffer.flush();
            buffer.close();
            out.close();

            InputStream input = conn.getInputStream();
            BufferedReader buffer_read = new BufferedReader(new InputStreamReader(input,"iso-8859-1"));
            String line="";
            while((line=buffer_read.readLine())!=null)
            {
                result+=line;
            }

            buffer_read.close();
            input.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.equals("Success")){
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
        }

        return null;
    }
}
