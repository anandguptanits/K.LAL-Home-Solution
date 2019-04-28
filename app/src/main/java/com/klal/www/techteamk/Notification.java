package com.klal.www.techteamk;

import android.content.res.Resources;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Notification extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> notification = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Log.d(TAG,"OnCreate : Started");
        Resources res = getResources();
        Collections.addAll(notification, res.getStringArray(R.array.Avengers));
        initRecyclerView();

    }
    private  void initRecyclerView() {
        Log.d(TAG,"Initiating Recycler View");

        RecyclerView recyclerView= findViewById(R.id.recyclerview);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,notification);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);

        //dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));

        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
