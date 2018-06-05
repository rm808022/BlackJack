package com.example.user.blackjack;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llPayTable;
    ImageView imgVInfo;
    TextView tvMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        llPayTable = findViewById(R.id.llPayTable);
        imgVInfo = findViewById(R.id.imgVInfo);

        tvMenu = findViewById(R.id.tvMenu);
        tvMenu.setOnClickListener(this);

        imgVInfo.setOnClickListener(this);
        llPayTable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.llPayTable:
                displayPayTable();
                break;

            case R.id.imgVInfo:
                displayPayTableInfo();
                break;
            case R.id.tvMenu:
                displayPopup();
        }

    }

    private void displayPopup() {

        PopupMenu popupMenu = new PopupMenu(TableActivity.this,tvMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(TableActivity.this, " "+item.getTitle()
                , Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        popupMenu.show();
    }

    private void displayPayTable() {

        final Dialog myAlertdisplayPayTableDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertdisplayPayTableDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertdisplayPayTableDialog.setContentView(R.layout.pay_table_layout);
        myAlertdisplayPayTableDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView imgVPayTableCancel = myAlertdisplayPayTableDialog.findViewById(R.id.imgVPayTableCancel);
        imgVPayTableCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertdisplayPayTableDialog.dismiss();
            }
        });

        myAlertdisplayPayTableDialog.show();
    }

    private void displayPayTableInfo() {

        final Dialog myAlertdisplayPayTableInfoDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertdisplayPayTableInfoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertdisplayPayTableInfoDialog.setContentView(R.layout.pay_table_layout);
        myAlertdisplayPayTableInfoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView imgVPayTableCancel = myAlertdisplayPayTableInfoDialog.findViewById(R.id.imgVPayTableCancel);
        imgVPayTableCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertdisplayPayTableInfoDialog.dismiss();
            }
        });
        myAlertdisplayPayTableInfoDialog.show();
    }
}
