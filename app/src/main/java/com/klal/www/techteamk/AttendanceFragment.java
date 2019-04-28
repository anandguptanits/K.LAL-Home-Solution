package com.klal.www.techteamk;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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


public class AttendanceFragment extends Fragment {

    String []data={"NA","NA","NA","NA","NA","NA","NA"};
    String []stat={"NA","NA","NA","NA","NA","NA","NA"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_attendance,container,false);

        //copied from stackoverflow...
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            //Marking the dates of attendance...
            getAttendanceDate();
            TextView date1=(TextView) v.findViewById(R.id.date1);
            TextView date2=(TextView) v.findViewById(R.id.date2);
            TextView date3=(TextView) v.findViewById(R.id.date3);
            TextView date4=(TextView) v.findViewById(R.id.date4);
            TextView date5=(TextView) v.findViewById(R.id.date5);
            TextView date6=(TextView) v.findViewById(R.id.date6);
            TextView date7=(TextView) v.findViewById(R.id.date7);
            date1.setText(data[0]);
            date2.setText(data[1]);
            date3.setText(data[2]);
            date4.setText(data[3]);
            date5.setText(data[4]);
            date6.setText(data[5]);
            date7.setText(data[6]);
            //Marking the status on the above dates....
            stat=new String[7];
            getStatus();
            TextView stat1=(TextView) v.findViewById(R.id.stat1);
            TextView stat2=(TextView) v.findViewById(R.id.stat2);
            TextView stat3=(TextView) v.findViewById(R.id.stat3);
            TextView stat4=(TextView) v.findViewById(R.id.stat4);
            TextView stat5=(TextView) v.findViewById(R.id.stat5);
            TextView stat6=(TextView) v.findViewById(R.id.stat6);
            TextView stat7=(TextView) v.findViewById(R.id.stat7);
            stat1.setText(stat[0]);
            stat2.setText(stat[1]);
            stat3.setText(stat[2]);
            stat4.setText(stat[3]);
            stat5.setText(stat[4]);
            stat6.setText(stat[5]);
            stat7.setText(stat[6]);
        }
        //
        //PDF printer...
        Button attendance=(Button)v.findViewById(R.id.sayPresent);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),GetLocation.class);
                startActivity(i);
            }
        });

        return v;
    }
    //This method is used to extract the DATE part of the attendance tables......
    private void getAttendanceDate(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAttendanceData.php";
        String result="";

        try {
            URL url=new URL(connect);
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);


            InputStream ips=new BufferedInputStream(http.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"UTF-8"));//Chances of error are here....
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null){
                sb.append(line+"\n");
            }
            reader.close();
            ips.close();
            http.disconnect();

            result=sb.toString();

        } catch (MalformedURLException e) {
            result=e.getMessage();
        }
        catch (IOException e){
            result=e.getMessage();
        }

        //Parsing the JSON data...

        try {
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;

            data=new String[ja.length()];
            if(ja.length()<7){
                for (int i = 0; i<ja.length();i++){
                    jo=ja.getJSONObject(i);
                    data[i]=jo.getString("date");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    data[i]=jo.getString("date");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //This method is used to extract the STATUS part of the attendance tables......
    private void getStatus(){
        //Code starts from here...
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAttendanceData.php";
        String result="";

        try {
            URL url=new URL(connect);
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);


            InputStream ips=new BufferedInputStream(http.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"UTF-8"));//Chances of error are here....
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null){
                sb.append(line+"\n");
            }
            reader.close();
            ips.close();
            http.disconnect();

            result=sb.toString();

        } catch (MalformedURLException e) {
            result=e.getMessage();
        }
        catch (IOException e){
            result=e.getMessage();
        }

        //Parsing the JSON data...

        try {
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;

            data=new String[ja.length()];
            if(ja.length()<7){
                for (int i = 0; i<ja.length();i++){
                    jo=ja.getJSONObject(i);
                    stat[i]=jo.getString("status");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    stat[i]=jo.getString("status");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //
    }
}
