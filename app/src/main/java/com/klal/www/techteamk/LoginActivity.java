package com.klal.www.techteamk;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText uemail;
    EditText upass;
    CheckBox cbk;
    private final int REQUEST_LOCATION_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ask permission
        askpermission(Manifest.permission.ACCESS_FINE_LOCATION,REQUEST_LOCATION_CODE);
        //Creting a login dialog progress circle...

        //Checking for internet and location...
        if (!isConnected(LoginActivity.this)) {
            buildDialog(LoginActivity.this).show();
        } else {
            uemail = (EditText) findViewById(R.id.login_emailid);
            upass = (EditText) findViewById(R.id.login_password);
            cbk = (CheckBox) findViewById(R.id.show_hide_password);
            cbk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        upass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    } else {
                        upass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }
            });
        }
    }

    //For Login purposes..........
    public void loginbtnclicked(View view) {
        String username = String.valueOf(R.id.login_emailid);
        String password = String.valueOf(R.id.login_password);
        ;
        LoginBackground bg = new LoginBackground(LoginActivity.this);
        bg.execute(uemail.getText().toString(), upass.getText().toString());
        //Change intent if needed...
    }

    public void askpermission(String permission ,int requestcode){
if(ContextCompat.checkSelfPermission(this,permission)!=PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this,new String[]{permission},requestcode);
}else {
    Toast.makeText(this,"permission already granted",Toast.LENGTH_SHORT).show();
}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_LOCATION_CODE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Location permission granted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Location permission denied",Toast.LENGTH_SHORT).show();
                }
        }
    }

    //For checking internet.......
    public boolean isConnected(Context context) {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cn.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Alert dialog builder...
    public AlertDialog.Builder buildDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Access!");
        builder.setMessage("Please switch on your internet and restart app...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        return builder;
    }
}
