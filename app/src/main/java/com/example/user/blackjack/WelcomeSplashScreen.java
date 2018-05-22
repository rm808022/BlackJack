package com.example.user.blackjack;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeSplashScreen extends AppCompatActivity {
    private static int Welcome_Screen = 3000;
    ImageView imgVDtsLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_splash_screen);
        imgVDtsLogo = findViewById(R.id.imgVDtsLogo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
        imgVDtsLogo.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                startActivity(new Intent(getApplicationContext(),LoadingSplashScreen.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Welcome Screen
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeSplashScreen.this,LoadingSplashScreen.class);
                startActivity(i);
                finish();

            }
        },Welcome_Screen);*/
    }
}
