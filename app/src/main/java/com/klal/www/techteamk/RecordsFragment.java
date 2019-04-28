package com.klal.www.techteamk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RecordsFragment extends Fragment {

    //Universal arrays as outputs...
    String []data={"NA","NA","NA","NA","NA","NA","NA"};
    String []disAmount={"NA","NA","NA","NA","NA","NA","NA"};
    String []returned={"NA","NA","NA","NA","NA","NA","NA"};
    String []balance={"NA","NA","NA","NA","NA","NA","NA"};
    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_records,container,false);
        //copied from stackoverflow.....
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //Your code here onwards.....
            //calling methods...
            getRecordsDate();
            getRecordsDisimbersed();
            getRecordsReturned();
            getRecordsBalance();
            //changing the contents of table dynamically now...
            TextView date1=(TextView) v.findViewById(R.id.registerDate1);
            TextView date2=(TextView) v.findViewById(R.id.registerDate2);
            TextView date3=(TextView) v.findViewById(R.id.registerDate3);
            TextView date4=(TextView) v.findViewById(R.id.registerDate4);
            TextView date5=(TextView) v.findViewById(R.id.registerDate5);
            TextView date6=(TextView) v.findViewById(R.id.registerDate6);
            TextView date7=(TextView) v.findViewById(R.id.registerDate7);

            TextView disAmount1=(TextView) v.findViewById(R.id.disAmout1);
            TextView disAmount2=(TextView) v.findViewById(R.id.disAmout2);
            TextView disAmount3=(TextView) v.findViewById(R.id.disAmout3);
            TextView disAmount4=(TextView) v.findViewById(R.id.disAmout4);
            TextView disAmount5=(TextView) v.findViewById(R.id.disAmout5);
            TextView disAmount6=(TextView) v.findViewById(R.id.disAmout6);
            TextView disAmount7=(TextView) v.findViewById(R.id.disAmout7);

            TextView depositAmount1=(TextView) v.findViewById(R.id.dipositAmout1);
            TextView depositAmount2=(TextView) v.findViewById(R.id.dipositAmout2);
            TextView depositAmount3=(TextView) v.findViewById(R.id.dipositAmout3);
            TextView depositAmount4=(TextView) v.findViewById(R.id.dipositAmout4);
            TextView depositAmount5=(TextView) v.findViewById(R.id.dipositAmout5);
            TextView depositAmount6=(TextView) v.findViewById(R.id.dipositAmout6);
            TextView depositAmount7=(TextView) v.findViewById(R.id.dipositAmout7);

            TextView balance1=(TextView) v.findViewById(R.id.balance1);
            TextView balance2=(TextView) v.findViewById(R.id.balance2);
            TextView balance3=(TextView) v.findViewById(R.id.balance3);
            TextView balance4=(TextView) v.findViewById(R.id.balance4);
            TextView balance5=(TextView) v.findViewById(R.id.balance5);
            TextView balance6=(TextView) v.findViewById(R.id.balance6);
            TextView balance7=(TextView) v.findViewById(R.id.balance7);

            //Initiallizing the above declairations....
            date1.setText(data[0]);
            date2.setText(data[1]);
            date3.setText(data[2]);
            date4.setText(data[3]);
            date5.setText(data[4]);
            date6.setText(data[5]);
            date7.setText(data[6]);

            disAmount1.setText(disAmount[0]);
            disAmount2.setText(disAmount[1]);
            disAmount3.setText(disAmount[2]);
            disAmount4.setText(disAmount[3]);
            disAmount5.setText(disAmount[4]);
            disAmount6.setText(disAmount[5]);
            disAmount7.setText(disAmount[6]);

            depositAmount1.setText(returned[0]);
            depositAmount2.setText(returned[1]);
            depositAmount3.setText(returned[2]);
            depositAmount4.setText(returned[3]);
            depositAmount5.setText(returned[4]);
            depositAmount6.setText(returned[5]);
            depositAmount7.setText(returned[6]);

            balance1.setText(balance[0]);
            balance2.setText(balance[1]);
            balance3.setText(balance[2]);
            balance4.setText(balance[3]);
            balance5.setText(balance[4]);
            balance6.setText(balance[5]);
            balance7.setText(balance[6]);
        }
        //All widgets dynamically initialized....
        return v;
    }

    //Code for backend starts here....
    //This method is used to extract the DATE part of the Records tables......
    private void getRecordsDate(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getRecordsData.php";
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

            //data=new String[ja.length()];
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

    //This method is used to extract the Disimbersed amount part of the Records tables......
    private void getRecordsDisimbersed(){
        //Code starts from here...
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getRecordsData.php";
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

            //data=new String[ja.length()];
            if(ja.length()<7){
                for (int i = 0; i<ja.length();i++){
                    jo=ja.getJSONObject(i);
                    disAmount[i]=jo.getString("disimbersed");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    disAmount[i]=jo.getString("disimbersed");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //
    //This method is used to extract the returned amount part of the Records tables......
    private void getRecordsReturned(){
        //Code starts from here...
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getRecordsData.php";
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

            //data=new String[ja.length()];
            if(ja.length()<7){
                for (int i = 0; i<ja.length();i++){
                    jo=ja.getJSONObject(i);
                    returned[i]=jo.getString("returned");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    returned[i]=jo.getString("returned");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //
    //This method is used to extract the balance amount part of the Records tables......
    private void getRecordsBalance(){
        //Code starts from here...
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getRecordsData.php";
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

            //data=new String[ja.length()];
            if(ja.length()<7){
                for (int i = 0; i<ja.length();i++){
                    jo=ja.getJSONObject(i);
                    balance[i]=jo.getString("balance");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    balance[i]=jo.getString("balance");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //

}
