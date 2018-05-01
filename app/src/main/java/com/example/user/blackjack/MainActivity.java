package com.example.user.blackjack;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.design.widget.TabLayout;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,View.OnClickListener {


    LinearLayout llSetting,llHeaderCenter,llDialogText,linearLayout2,linearLayout3,llGeneral,llNotification,llSignin,llSupport;
    RelativeLayout rlBodyCenterBody,rlBodyPlayNow,rlBodyLobby;

    HorizontalScrollView horizontalScrollView;

    SeekBar sbVoice, sbSounds;
    private static final int REQUEST_TABBED_DIALOG = 42;
    TextView tvGeneral,tvNotification,tvProfile,tvPlayerOnline,tvFriendsPlayer,tvDialogHeader;
    public int general = 1;
    public int notification = 1;
    public boolean setting = false;
    public boolean support = false;
    ImageView cancel,imgVSignCancel,imgVSupportCancel;
    Context context;


//    public String[] pageTitle = {"General", "Notifications"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llSetting = findViewById(R.id.llSetting);
        llDialogText = findViewById(R.id.llDialogText);
        llHeaderCenter = findViewById(R.id.llHeaderCenter);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        llSignin = findViewById(R.id.llSignin);
        llSupport = findViewById(R.id.llSupport);

        tvProfile = findViewById(R.id.tvProfile);
        tvPlayerOnline = findViewById(R.id.tvPlayerOnline);
        tvFriendsPlayer = findViewById(R.id.tvFriendsPlayer);
        tvDialogHeader = findViewById(R.id.tvDialogHeader);

        horizontalScrollView= findViewById(R.id.horizontalScrollView);

        rlBodyCenterBody = findViewById(R.id.rlBodyCenterBody);
        rlBodyPlayNow = findViewById(R.id.rlBodyPlayNow);
        rlBodyLobby = findViewById(R.id.rlBodyLobby);

        llSetting.setOnClickListener(this);
        llSignin.setOnClickListener(this);
        llSupport.setOnClickListener(this);
      //  sbVoice = findViewById(R.id.sbVoice);
//        sbVoice.setOnSeekBarChangeListener(this);
//        sbVoice.setProgress(sbVoice.getMax()/2);

      //  sbSounds = findViewById(R.id.sbSounds);
        //  sbSounds.setOnSeekBarChangeListener(this);
        //       sbSounds.setProgress(sbSounds.getMax()/2);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llSetting:
                displaySetting();
                break;
            case R.id.llSignin:
                DisplaySignIn();
                break;
            case R.id.llSupport:
                DisplaySupport();

        }

    }

    private void DisplaySupport() {

        final Dialog myAlertDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertDialog.setContentView(R.layout.support_layout);
        myAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

       final TextView tvSupport = myAlertDialog.findViewById(R.id.tvSupport);
       // final TextView tvFAQ = myAlertDialog.findViewById(R.id.tvFAQ);
        final TextView tvRules = myAlertDialog.findViewById(R.id.tvRules);
        final RelativeLayout rlBodySupport = myAlertDialog.findViewById(R.id.rlBodySupport);
        final  RelativeLayout rlBodySupportFAQ = myAlertDialog.findViewById(R.id.rlBodySupportFAQ);
        final RelativeLayout rlBodyRules = myAlertDialog.findViewById(R.id.rlBodyRules);

//visibilty gone from main page
        llHeaderCenter.setVisibility(View.GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(View.GONE);
        tvProfile.setVisibility(View.GONE);
        tvPlayerOnline.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);
        tvFriendsPlayer.setVisibility(View.GONE);
        rlBodyPlayNow.setVisibility(View.GONE);
        rlBodyLobby.setVisibility(View.GONE);
        horizontalScrollView.setVisibility(View.GONE);
        tvDialogHeader.setText("Support");

        tvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodySupport.setVisibility(View.VISIBLE);
                rlBodySupportFAQ.setVisibility(View.GONE);
                rlBodyRules.setVisibility(View.GONE);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header);
               // tvFAQ.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvRules.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                support = true;
            }
        });




     /*   tvFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodySupport.setVisibility(View.GONE);
                rlBodySupportFAQ.setVisibility(View.VISIBLE);
                rlBodyRules.setVisibility(View.GONE);
                tvFAQ.setBackgroundResource(R.drawable.bg_dialog_header);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvRules.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                support = true;
            }
        });*/


        tvRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodySupport.setVisibility(View.GONE);
                rlBodySupportFAQ.setVisibility(View.GONE);
                rlBodyRules.setVisibility(View.VISIBLE);
                tvRules.setBackgroundResource(R.drawable.bg_dialog_header);
               // tvFAQ.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                support = true;
            }
        });


        myAlertDialog.show();

        imgVSupportCancel= myAlertDialog.findViewById(R.id.imgVSupportCancel);
        imgVSupportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();

                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(View.GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void DisplaySignIn() {

        final Dialog myAlertDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertDialog.setContentView(R.layout.sign_in_layout);
        myAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        myAlertDialog.show();

        imgVSignCancel = myAlertDialog.findViewById(R.id.imgVSignCancel);
        imgVSignCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
            }
        });
    }

    private void displaySetting() {

       /* TextView alert_title, alertMessage;
        final ImageView close_alert;
        Button ok_alert, cancel_alert;*/


        final Dialog myAlertDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertDialog.setContentView(R.layout.tab_setting_layout);
        myAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        tvGeneral = myAlertDialog.findViewById(R.id.tvGeneral);
        tvNotification = myAlertDialog.findViewById(R.id.tvNotification);
        cancel = myAlertDialog.findViewById(R.id.cancel);

        llGeneral = myAlertDialog.findViewById(R.id.llGeneral);
        llNotification = myAlertDialog.findViewById(R.id.llNotification);


        //visibility gone from main page
        llHeaderCenter.setVisibility(View.GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(View.GONE);
        tvProfile.setVisibility(View.GONE);
        tvPlayerOnline.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);
        tvFriendsPlayer.setVisibility(View.GONE);
        rlBodyPlayNow.setVisibility(View.GONE);
        rlBodyLobby.setVisibility(View.GONE);
        horizontalScrollView.setVisibility(View.GONE);

        tvGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNotification.setVisibility(View.GONE);
                llGeneral.setVisibility(View.VISIBLE);
                tvGeneral.setBackgroundResource(R.drawable.bg_dialog_header);
                tvNotification.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                    setting=true;
            }
        });
        tvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llNotification.setVisibility(View.VISIBLE);
                tvNotification.setBackgroundResource(R.drawable.bg_dialog_header);
                tvGeneral.setBackgroundResource(R.drawable.bg_dialog_header_unselected);

                         llGeneral.setVisibility(View.GONE);
                        setting=true;
            }
        });


        myAlertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(View.GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    }

