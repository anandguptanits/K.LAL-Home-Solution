package com.klal.www.techteamk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginBackground extends AsyncTask<String,Void,String> {
    static final String EXTRA_USERNAME="com.klal.www.techteamk.EXTRA_USERNAME";
    static final String EXTRA_PASSWORD="com.klal.www.techteamk.EXTRA_PASSWORD";
    static String userNameSend="";
    static String passwordSend="";

    public int entryPermissionGranted=0;
    public int bgrunning=-1;
    AlertDialog dialog;
    AlertDialog circle;
    Context context;
    public LoginBackground(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        dialog=new AlertDialog.Builder(context).create();
        dialog.setTitle("Verifying...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setTitle(s);
        if(s.equals("User VERIFIED!")){
            Intent i=new Intent(context,Profile.class);
            i.putExtra(EXTRA_USERNAME,userNameSend);
            i.putExtra(EXTRA_PASSWORD,passwordSend);
            context.startActivity(i);
            AppCompatActivity x=(AppCompatActivity) context;
            x.finish();
        }
            dialog.setMessage(s);
            dialog.show();
    }

    @Override
    protected String doInBackground(String... voids) {

        String result="";
        String user=voids[0];
        String pass=voids[1];
        userNameSend=user;
        passwordSend=pass;


        String connect="http://monoclinous-beaches.000webhostapp.com/connectiontest/getdata.php";

        try {
            URL url=new URL(connect);
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops=http.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips=http.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"UTF-8"));//Chances of error are here....
            String line="";
            while((line=reader.readLine())!=null){
                result+=line;
            }
            reader.close();
            ips.close();
            http.disconnect();

            return result;

        } catch (MalformedURLException e) {
            result=e.getMessage();
        }
        catch (IOException e){
            result=e.getMessage();
        }

        return result;
    }
}
