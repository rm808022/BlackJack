package com.example.user.blackjack;

import android.content.Intent;
import android.os.Handler;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingSplashScreen extends AppCompatActivity implements OnProgressBarListener {

  NumberProgressBar number_progress_bar;
    private static int Loading_Screen = 3000;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_splash_screen);
        number_progress_bar = findViewById(R.id.number_progress_bar);
        number_progress_bar.setOnProgressBarListener(LoadingSplashScreen.this);

  /*      new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoadingSplashScreen.this,MainActivity.class);
                startActivity(i);
                number_progress_bar.incrementProgressBy(10);
                finish();

            }
        },1000);*/

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        number_progress_bar.incrementProgressBy(5);
                        Log.i("TAG",""+number_progress_bar.getProgress());
                        if(number_progress_bar.getProgress() == 95){
                            Intent i = new Intent(LoadingSplashScreen.this,LoginActivity.class);
                            Log.i("TAG",""+"");
                            startActivity(i);
                            finish();
                        }else{

                        }



                    }
                });
            }
        },1000,100);


    }

    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
        // Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
            number_progress_bar.setProgress(0);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();


    }
}
