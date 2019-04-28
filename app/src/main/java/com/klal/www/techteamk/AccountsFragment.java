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


public class AccountsFragment extends Fragment {

    //Universal arrays as outputs...
    String []data={"NA","NA","NA","NA","NA","NA","NA"};
    String []salary={"NA","NA","NA","NA","NA","NA","NA"};
    String []petrol={"NA","NA","NA","NA","NA","NA","NA"};
    String []site={"NA","NA","NA","NA","NA","NA","NA"};
    String []others={"NA","NA","NA","NA","NA","NA","NA"};
    //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_accounts,container,false);
        //copied from stackoverflow.....
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //Your code here onwards.....
            //calling methods...
            getAccountsDate();
            getAccountsSalary();
            getAccountsPetrol();
            getAccountsSite();
            getAccountsOthers();
            //
            //changing the contents of table dynamically now...

            TextView date1=(TextView) v.findViewById(R.id.accountsDate1);
            TextView date2=(TextView) v.findViewById(R.id.accountsDate2);
            TextView date3=(TextView) v.findViewById(R.id.accountsDate3);
            TextView date4=(TextView) v.findViewById(R.id.accountsDate4);
            TextView date5=(TextView) v.findViewById(R.id.accountsDate5);
            TextView date6=(TextView) v.findViewById(R.id.accountsDate6);
            TextView date7=(TextView) v.findViewById(R.id.accountsDate7);

            TextView salary1=(TextView) v.findViewById(R.id.accountsSalary1);
            TextView salary2=(TextView) v.findViewById(R.id.accountsSalary2);
            TextView salary3=(TextView) v.findViewById(R.id.accountsSalary3);
            TextView salary4=(TextView) v.findViewById(R.id.accountsSalary4);
            TextView salary5=(TextView) v.findViewById(R.id.accountsSalary5);
            TextView salary6=(TextView) v.findViewById(R.id.accountsSalary6);
            TextView salary7=(TextView) v.findViewById(R.id.accountsSalary7);

            TextView petrol1=(TextView) v.findViewById(R.id.accountsPetrol1);
            TextView petrol2=(TextView) v.findViewById(R.id.accountsPetrol2);
            TextView petrol3=(TextView) v.findViewById(R.id.accountsPetrol3);
            TextView petrol4=(TextView) v.findViewById(R.id.accountsPetrol4);
            TextView petrol5=(TextView) v.findViewById(R.id.accountsPetrol5);
            TextView petrol6=(TextView) v.findViewById(R.id.accountsPetrol6);
            TextView petrol7=(TextView) v.findViewById(R.id.accountsPetrol7);

            TextView site1=(TextView) v.findViewById(R.id.accountsSite1);
            TextView site2=(TextView) v.findViewById(R.id.accountsSite2);
            TextView site3=(TextView) v.findViewById(R.id.accountsSite3);
            TextView site4=(TextView) v.findViewById(R.id.accountsSite4);
            TextView site5=(TextView) v.findViewById(R.id.accountsSite5);
            TextView site6=(TextView) v.findViewById(R.id.accountsSite6);
            TextView site7=(TextView) v.findViewById(R.id.accountsSite7);

            TextView others1=(TextView) v.findViewById(R.id.accountsOthers1);
            TextView others2=(TextView) v.findViewById(R.id.accountsOthers2);
            TextView others3=(TextView) v.findViewById(R.id.accountsOthers3);
            TextView others4=(TextView) v.findViewById(R.id.accountsOthers4);
            TextView others5=(TextView) v.findViewById(R.id.accountsOthers5);
            TextView others6=(TextView) v.findViewById(R.id.accountsOthers6);
            TextView others7=(TextView) v.findViewById(R.id.accountsOthers7);
            //
            //Initiallizing the above declairations....

            date1.setText(data[0]);
            date2.setText(data[1]);
            date3.setText(data[2]);
            date4.setText(data[3]);
            date5.setText(data[4]);
            date6.setText(data[5]);
            date7.setText(data[6]);

            salary1.setText(salary[0]);
            salary2.setText(salary[1]);
            salary3.setText(salary[2]);
            salary4.setText(salary[3]);
            salary5.setText(salary[4]);
            salary6.setText(salary[5]);
            salary7.setText(salary[6]);

            petrol1.setText(petrol[0]);
            petrol2.setText(petrol[1]);
            petrol3.setText(petrol[2]);
            petrol4.setText(petrol[3]);
            petrol5.setText(petrol[4]);
            petrol6.setText(petrol[5]);
            petrol7.setText(petrol[6]);

            site1.setText(site[0]);
            site2.setText(site[1]);
            site3.setText(site[2]);
            site4.setText(site[3]);
            site5.setText(site[4]);
            site6.setText(site[5]);
            site7.setText(site[6]);

            others1.setText(others[0]);
            others2.setText(others[1]);
            others3.setText(others[2]);
            others4.setText(others[3]);
            others5.setText(others[4]);
            others6.setText(others[5]);
            others7.setText(others[6]);
            //
        }
        //All widgets dynamically initialized....
        return v;
    }

    //Code for backend starts here....
    //This method is used to extract the DATE part of the Accounts tables......
    private void getAccountsDate(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAccountsData.php";
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

    //
    //This method is used to extract the Salary part of the Accounts tables......

    private void getAccountsSalary(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAccountsData.php";
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
                    salary[i]=jo.getString("salary");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    salary[i]=jo.getString("salary");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //
    //This method is used to extract the Petrol part of the Accounts tables......

    private void getAccountsPetrol(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAccountsData.php";
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
                    petrol[i]=jo.getString("petrol");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    petrol[i]=jo.getString("petrol");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //
    //This method is used to extract the Site part of the Accounts tables......
    private void getAccountsSite(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAccountsData.php";
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
                    site[i]=jo.getString("site");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    site[i]=jo.getString("site");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //
    //This method is used to extract the Others part of the Accounts tables......
    private void getAccountsOthers(){
        String connect="https://monoclinous-beaches.000webhostapp.com/connectiontest/getAccountsData.php";
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
                    others[i]=jo.getString("others");
                }
            }
            else {
                for (int i = 0; i<7;i++){
                    jo=ja.getJSONObject(i);
                    others[i]=jo.getString("others");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //
}
