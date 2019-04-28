package com.klal.www.techteamk;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private static int Splash_Timeout=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imageView=findViewById(R.id.Logo);
        Animation startAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        imageView.startAnimation(startAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent=new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(HomeIntent);
                finish();
            }
        },Splash_Timeout);
    }
}
