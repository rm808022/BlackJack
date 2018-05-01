package com.example.user.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingDialog extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_setting_layout);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvGeneral:

                break;

            case R.id.tvNotification:

                break;

        }

    }
}
