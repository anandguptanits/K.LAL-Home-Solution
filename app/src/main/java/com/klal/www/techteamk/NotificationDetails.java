package com.klal.www.techteamk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NotificationDetails extends AppCompatActivity
{
    private static final String TAG = "Notification_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
    }


    private void getIncomingIntent()
    {
        Log.d(TAG,"Checking for Intent ");
        if(getIntent().hasExtra("Massage")&&getIntent().hasExtra("Date")&&getIntent().hasExtra("Notification"))
        {
            String msg = getIntent().getStringExtra("Massage");
            Calendar calendar = Calendar.getInstance();

            String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

//            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            String notification = getIntent().getStringExtra("Notification");

            setNotification(msg,date,notification);

        }
    }
    private void setNotification(String msg, String date,String notification)
    {
        TextView masg = findViewById(R.id.headLine);
        masg.setText(msg);
        TextView Date = findViewById(R.id.notification_date);
        Date.setText(date);
        TextView Notifcation =findViewById(R.id.message);
        Notifcation.setText(notification);

    }
}
