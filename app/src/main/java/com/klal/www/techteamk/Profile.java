package com.klal.www.techteamk;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity{

    TabLayout mTabLayout;
    ViewPager mViewPager;
    PageAdapter mPageAdapter;
    TabItem mattendance;
    TabItem mrecords;
    TabItem maccounts;
    DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        String username=intent.getStringExtra(LoginBackground.EXTRA_USERNAME);
        String password=intent.getStringExtra(LoginBackground.EXTRA_PASSWORD);

        //Pdf download for attendance....
        //

        TextView employeeName=(TextView)findViewById(R.id.empName);
        employeeName.setText(username);
        //Listening For logout...
        ImageButton logout=(ImageButton)findViewById(R.id.logOutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Profile.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Listening for notifications....
        ImageButton notify=(ImageButton)findViewById(R.id.notificationButton);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Profile.this,Notification.class);
                startActivity(i);
            }
        });

        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mTabLayout=(TabLayout)findViewById(R.id.tabProfile);
        mattendance=(TabItem)findViewById(R.id.tabItemAttendance);
        mrecords=(TabItem)findViewById(R.id.tabItemRecords);
        maccounts=(TabItem)findViewById(R.id.tabItemAccounts);


        mPageAdapter =new PageAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }

}
