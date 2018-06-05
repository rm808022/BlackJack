package com.example.user.blackjack;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout llSetting,llHeaderCenter,llDialogText,llProfile,llGeneral,llNotification,llSignin,llSupport,
            llMessage;
    RelativeLayout rlBodyCenterBody,rlBodyPlayNow,rlBodyLobby;

    HorizontalScrollView horizontalScrollView;

    SeekBar sbVoice, sbSound;
    private static final int REQUEST_TABBED_DIALOG = 42;
    TextView tvGeneral,tvNotification,tvProfile,tvPlayerOnline,tvFriendsPlayer,tvDialogHeader;
    public int general = 1;
    public int notification = 1;
    public boolean setting = false;
    public boolean support = false;
    public boolean message = false;
   public ImageView cancel,imgVSignCancel,imgVSupportCancel,profileImg, imgVUserProfile;
    Context context;
    public Drawable imgValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llSetting = findViewById(R.id.llSetting);
        llDialogText = findViewById(R.id.llDialogText);
        llHeaderCenter = findViewById(R.id.llHeaderCenter);
        llProfile = findViewById(R.id.llProfile);
       // linearLayout3 = findViewById(R.id.linearLayout3);
       // llSignin = findViewById(R.id.llSignin);
       // llMessage = findViewById(R.id.llMessage);
        llSupport = findViewById(R.id.llSupport);
        llProfile = findViewById(R.id.llProfile);

        tvProfile = findViewById(R.id.tvProfile);
        tvPlayerOnline = findViewById(R.id.tvPlayerOnline);
      //  tvFriendsPlayer = findViewById(R.id.tvFriendsPlayer);
        tvDialogHeader = findViewById(R.id.tvDialogHeader);

      //  horizontalScrollView= findViewById(R.id.horizontalScrollView);

        rlBodyCenterBody = findViewById(R.id.rlBodyCenterBody);
        rlBodyPlayNow = findViewById(R.id.rlBodyPlayNow);
        rlBodyLobby = findViewById(R.id.rlBodyLobby);

        imgVUserProfile=findViewById(R.id.imgVUserProfile);


        llSetting.setOnClickListener(this);
        llSignin.setOnClickListener(this);
        llSupport.setOnClickListener(this);
        llMessage.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        rlBodyPlayNow.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "not", Toast.LENGTH_SHORT).show();
            requestPermission();
        }

}


    public void requestPermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("This permission is required for camera and gallery")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==1) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Log.i("Bitmap",""+bitmap);
            imgVUserProfile.setImageBitmap(bitmap);
            imgValue=imgVUserProfile.getDrawable();
        }


        if(requestCode == 2)
        {
            Bitmap bitmap= null;
            if (resultCode==RESULT_OK)
            {
                Uri selectedimageuri=data.getData();
                String selectedimagepath=getPath(selectedimageuri);
                Toast.makeText(this, selectedimagepath, Toast.LENGTH_SHORT).show();
                imgVUserProfile.setImageURI(selectedimageuri);
                imgValue=imgVUserProfile.getDrawable();
            }
            }
    }

    private String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);


}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llSetting:
                displaySetting();
                break;
          /*  case R.id.llSignin:
                DisplaySignIn();
                break;*/
            case R.id.llSupport:
                DisplaySupport();
                break;
            /*case R.id.llMessage:
                displayMessage();
                break;*/
            case R.id.llProfile:
                imgValue=imgVUserProfile.getDrawable();
                displayProfile(imgValue);
                break;
            case R.id.rlBodyPlayNow:
                Intent i  = new Intent(MainActivity.this,TableActivity.class);
                startActivity(i);
                break;
        }

    }

    private void displayProfile(final Drawable imgValue) {

        final Dialog myAlertdisplayProfileDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertdisplayProfileDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertdisplayProfileDialog.setContentView(R.layout.profile_layout);
        myAlertdisplayProfileDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

       ImageView imgVSetImage = myAlertdisplayProfileDialog.findViewById(R.id.imgVSetImage);
        final ImageView profileImg =(ImageView) myAlertdisplayProfileDialog.findViewById(R.id.profileImg);

        if(imgValue !=null) {
            profileImg.setImageDrawable(imgValue);
        }

       imgVSetImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Dialog myAlertsetImageDialog = new Dialog(MainActivity.this,R.style.MyDialog_FullScreen);
               myAlertsetImageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               myAlertsetImageDialog.setContentView(R.layout.profile_picture_layout);
               myAlertsetImageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
               Toast.makeText(MainActivity.this, "profileImg ", Toast.LENGTH_SHORT).show();
              TextView tvTakePhoto = myAlertsetImageDialog.findViewById(R.id.tvTakePhoto);
               TextView tvChoosePhoto = myAlertsetImageDialog.findViewById(R.id.tvChoosePhoto);

               final ImageView avatar1 = myAlertsetImageDialog.findViewById(R.id.avatar1);
               final ImageView avatar2 = myAlertsetImageDialog.findViewById(R.id.avatar2);
               final ImageView avatar3 = myAlertsetImageDialog.findViewById(R.id.avatar3);
               final ImageView avatar4 = myAlertsetImageDialog.findViewById(R.id.avatar4);
               final ImageView avatar5 = myAlertsetImageDialog.findViewById(R.id.avatar5);
               final ImageView avatar6 = myAlertsetImageDialog.findViewById(R.id.avatar6);
               final ImageView avatarW1 = myAlertsetImageDialog.findViewById(R.id.avatar_w1);
               final ImageView avatarW2 = myAlertsetImageDialog.findViewById(R.id.avatar_w2);
               final ImageView avatarW3 = myAlertsetImageDialog.findViewById(R.id.avatar_w3);
               final ImageView avatarW4 = myAlertsetImageDialog.findViewById(R.id.avatar_w4);
               final ImageView avatarW5 = myAlertsetImageDialog.findViewById(R.id.avatar_w5);
               final ImageView avatarW6 = myAlertsetImageDialog.findViewById(R.id.avatar_w6);


               avatar1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Drawable img = avatar1.getDrawable();
                       imgVUserProfile.setImageDrawable(img);
                       profileImg.setImageDrawable(img);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatar2.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Drawable img2 = avatar2.getDrawable();
                       imgVUserProfile.setImageDrawable(img2);
                       profileImg.setImageDrawable(img2);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatar3.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable img3 = avatar3.getDrawable();
                       imgVUserProfile.setImageDrawable(img3);
                       profileImg.setImageDrawable(img3);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatar4.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable img4 = avatar4.getDrawable();
                       imgVUserProfile.setImageDrawable(img4);
                       profileImg.setImageDrawable(img4);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatar5.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable img5 = avatar5.getDrawable();
                       imgVUserProfile.setImageDrawable(img5);
                       profileImg.setImageDrawable(img5);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatar6.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable img6 = avatar6.getDrawable();
                       imgVUserProfile.setImageDrawable(img6);
                       profileImg.setImageDrawable(img6);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW1 = avatarW1.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW1);
                       profileImg.setImageDrawable(imgW1);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW2.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW2 = avatarW2.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW2);
                       profileImg.setImageDrawable(imgW2);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW3.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW3 = avatarW3.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW3);
                       profileImg.setImageDrawable(imgW3);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW4.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW4 = avatarW4.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW4);
                       profileImg.setImageDrawable(imgW4);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW5.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW5 = avatarW5.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW5);
                       profileImg.setImageDrawable(imgW5);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });

               avatarW6.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Drawable imgW6 = avatarW6.getDrawable();
                       imgVUserProfile.setImageDrawable(imgW6);
                       profileImg.setImageDrawable(imgW6);
                       Toast.makeText(MainActivity.this, "Profile pic set", Toast.LENGTH_SHORT).show();
                   }
               });



               tvChoosePhoto.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i,2);
                   }
               });

               tvTakePhoto.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                      startActivityForResult(intent,1);

                  }
              });

               myAlertsetImageDialog.show();

              ImageView imgVProfilePicCancel = myAlertsetImageDialog.findViewById(R.id.imgVProfilePicCancel);
               imgVProfilePicCancel.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       myAlertsetImageDialog.dismiss();
                   }
               });
           }
       });


//visibilty gone from main page
        llHeaderCenter.setVisibility(GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(GONE);
        tvProfile.setVisibility(GONE);
        tvPlayerOnline.setVisibility(GONE);
        llProfile.setVisibility(GONE);
      //  linearLayout3.setVisibility(GONE);
     //   tvFriendsPlayer.setVisibility(GONE);
        rlBodyPlayNow.setVisibility(GONE);
        rlBodyLobby.setVisibility(GONE);
        horizontalScrollView.setVisibility(GONE);
        tvDialogHeader.setText("Profile");

        myAlertdisplayProfileDialog.show();

        ImageView imgVProfileCancel= myAlertdisplayProfileDialog.findViewById(R.id.imgVProfileCancel);

        imgVProfileCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertdisplayProfileDialog.dismiss();

                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                llProfile.setVisibility(View.VISIBLE);
               // linearLayout3.setVisibility(View.VISIBLE);
              //  tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });
    }

   /* private void displayMessage() {
        final Dialog myAlertdisplayMessageDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertdisplayMessageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertdisplayMessageDialog.setContentView(R.layout.message_layout);
        myAlertdisplayMessageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final TextView tvMessage = myAlertdisplayMessageDialog.findViewById(R.id.tvMessage);
        final TextView tvNews = myAlertdisplayMessageDialog.findViewById(R.id.tvNews);
        final TextView tvSupport = myAlertdisplayMessageDialog.findViewById(R.id.tvSupport);
       final RelativeLayout rlBodyMessageLayout = myAlertdisplayMessageDialog.findViewById(R.id.rlBodyMessageLayout);
       final RelativeLayout rlBodyNewsLayout = myAlertdisplayMessageDialog.findViewById(R.id.rlBodyNewsLayout);
        final RelativeLayout rlBodyMessageSupportLayout = myAlertdisplayMessageDialog.findViewById(R.id.rlBodyMessageSupportLayout);

        //Message Support
        final LinearLayout llMessageAccountRelatedQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageAccountRelatedQuestion);
        final LinearLayout  llMessageAccountRelatedQuestionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageAccountRelatedQuestionLayout);
        final LinearLayout llMessageSupportMainFirstLayout  = myAlertdisplayMessageDialog.findViewById(R.id.llMessageSupportMainFirstLayout);
        final LinearLayout llMessageFinancialQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageFinancialQuestion);
        final LinearLayout  llMessageFinancialQuestionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageFinancialQuestionLayout);
        final LinearLayout llMessageRulesQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageRulesQuestion);
        final LinearLayout  llMessageRulesQuestionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageRulesQuestionLayout);
        final LinearLayout llMessageTechnicalQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageTechnicalQuestion);
        final LinearLayout  llMessageTechnicalQuestionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageTechnicalQuestionLayout);
        final LinearLayout llMessageGamesQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageGamesQuestion);
        final LinearLayout  llMessageGamesQuestionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageGamesQuestionLayout);
        final LinearLayout llMessageFriendsQuestion = myAlertdisplayMessageDialog.findViewById(R.id.llMessageFriendsQuestion);
        final LinearLayout  llMessageFriendsLayout = myAlertdisplayMessageDialog.findViewById(R.id.llMessageFriendsLayout);

        //News
        final LinearLayout  llSplitBetPoker = myAlertdisplayMessageDialog.findViewById(R.id.llSplitBetPoker);
        final LinearLayout  llSplitBetPokerLayout = myAlertdisplayMessageDialog.findViewById(R.id.llSplitBetPokerLayout);
        final LinearLayout  llWelcomeBonus = myAlertdisplayMessageDialog.findViewById(R.id.llWelcomeBonus);
        final LinearLayout  llWelcomeBonusLayout = myAlertdisplayMessageDialog.findViewById(R.id.llWelcomeBonusLayout);
        final LinearLayout  llSale = myAlertdisplayMessageDialog.findViewById(R.id.llSale);
      // final LinearLayout  llSaleLayout = myAlertDialog.findViewById(R.id.llSaleLayout);
        final LinearLayout  llTournament = myAlertdisplayMessageDialog.findViewById(R.id.llTournament);
       // final LinearLayout  llTournamentLayout = myAlertDialog.findViewById(R.id.llTournamentLayout);
        final LinearLayout  llDailyQuest = myAlertdisplayMessageDialog.findViewById(R.id.llDailyQuest);
        final ScrollView scDailyQuestLayout = myAlertdisplayMessageDialog.findViewById(R.id.scDailyQuestLayout);
        final LinearLayout  llNewsFriends = myAlertdisplayMessageDialog.findViewById(R.id.llNewsFriends);
        final RelativeLayout  rlBodyNewsFriendsLayout = myAlertdisplayMessageDialog.findViewById(R.id.rlBodyNewsFriendsLayout);
        final LinearLayout  llAchievement = myAlertdisplayMessageDialog.findViewById(R.id.llAchievement);
       // final LinearLayout  llAchievementLayout = myAlertDialog.findViewById(R.id.llAchievementLayout);
        final LinearLayout  llCollection = myAlertdisplayMessageDialog.findViewById(R.id.llCollection);
        final LinearLayout  llCollectionLayout = myAlertdisplayMessageDialog.findViewById(R.id.llCollectionLayout);
        final LinearLayout  llLevel = myAlertdisplayMessageDialog.findViewById(R.id.llLevel);
        final ScrollView  scLevelLayout = myAlertdisplayMessageDialog.findViewById(R.id.scLevelLayout);


        llSplitBetPoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.VISIBLE);
                llWelcomeBonusLayout.setVisibility(View.GONE);
                scDailyQuestLayout.setVisibility(View.GONE);
                rlBodyNewsFriendsLayout.setVisibility(View.GONE);
                llCollectionLayout.setVisibility(View.GONE);
                scLevelLayout.setVisibility(View.GONE);
                }
        });

        llWelcomeBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.GONE);
                llWelcomeBonusLayout.setVisibility(View.VISIBLE);
                scDailyQuestLayout.setVisibility(View.GONE);
                rlBodyNewsFriendsLayout.setVisibility(View.GONE);
                llCollectionLayout.setVisibility(View.GONE);
                scLevelLayout.setVisibility(View.GONE);
            }
        });

        llDailyQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.GONE);
                llWelcomeBonusLayout.setVisibility(View.GONE);
                scDailyQuestLayout.setVisibility(View.VISIBLE);
                rlBodyNewsFriendsLayout.setVisibility(View.GONE);
                llCollectionLayout.setVisibility(View.GONE);
                scLevelLayout.setVisibility(View.GONE);
            }
        });

        llNewsFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.GONE);
                llWelcomeBonusLayout.setVisibility(View.GONE);
                scDailyQuestLayout.setVisibility(View.GONE);
                rlBodyNewsFriendsLayout.setVisibility(View.VISIBLE);
                llCollectionLayout.setVisibility(View.GONE);
                scLevelLayout.setVisibility(View.GONE);
            }
        });

        llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.GONE);
                llWelcomeBonusLayout.setVisibility(View.GONE);
                scDailyQuestLayout.setVisibility(View.GONE);
                rlBodyNewsFriendsLayout.setVisibility(View.GONE);
                llCollectionLayout.setVisibility(View.VISIBLE);
                scLevelLayout.setVisibility(View.GONE);
            }
        });

        llLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSplitBetPokerLayout.setVisibility(View.GONE);
                llWelcomeBonusLayout.setVisibility(View.GONE);
                scDailyQuestLayout.setVisibility(View.GONE);
                rlBodyNewsFriendsLayout.setVisibility(View.GONE);
                llCollectionLayout.setVisibility(View.GONE);
                scLevelLayout.setVisibility(View.VISIBLE);
            }
        });




//visibilty gone from main page
        llHeaderCenter.setVisibility(GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(GONE);
        tvProfile.setVisibility(GONE);
        tvPlayerOnline.setVisibility(GONE);
        llProfile.setVisibility(GONE);
       // linearLayout3.setVisibility(GONE);
       // tvFriendsPlayer.setVisibility(GONE);
        rlBodyPlayNow.setVisibility(GONE);
        rlBodyLobby.setVisibility(GONE);
        horizontalScrollView.setVisibility(GONE);
        tvDialogHeader.setText("Message");

        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodyMessageLayout.setVisibility(View.VISIBLE);
                rlBodyNewsLayout.setVisibility(GONE);
                rlBodyMessageSupportLayout.setVisibility(View.GONE);
                message= true;
                tvMessage.setBackgroundResource(R.drawable.bg_dialog_header);
                tvNews.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
            }
        });

        tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodyMessageLayout.setVisibility(View.GONE);
                rlBodyNewsLayout.setVisibility(View.VISIBLE);
                rlBodyMessageSupportLayout.setVisibility(View.GONE);
                message= true;
               tvMessage.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvNews.setBackgroundResource(R.drawable.bg_dialog_header);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
            }
        });

        tvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodyMessageSupportLayout.setVisibility(View.VISIBLE);
                rlBodyMessageLayout.setVisibility(View.GONE);
                rlBodyNewsLayout.setVisibility(View.GONE);
                message= true;
                tvMessage.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvNews.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header);
            }
        });


        llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
        llMessageSupportMainFirstLayout.setVisibility(View.VISIBLE);
        llMessageFinancialQuestionLayout.setVisibility(GONE);
        llMessageRulesQuestionLayout.setVisibility(GONE);
        llMessageTechnicalQuestionLayout.setVisibility(GONE);
        llMessageGamesQuestionLayout.setVisibility(GONE);
        llMessageFriendsLayout.setVisibility(GONE);



        llMessageAccountRelatedQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageAccountRelatedQuestionLayout.setVisibility(View.VISIBLE);
                llMessageFinancialQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
                llMessageRulesQuestionLayout.setVisibility(GONE);
                llMessageTechnicalQuestionLayout.setVisibility(GONE);
                llMessageGamesQuestionLayout.setVisibility(GONE);
                llMessageFriendsLayout.setVisibility(GONE);

            }
        });

        llMessageFinancialQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageFinancialQuestionLayout.setVisibility(View.VISIBLE);
                llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
                llMessageRulesQuestionLayout.setVisibility(GONE);
                llMessageTechnicalQuestionLayout.setVisibility(GONE);
                llMessageGamesQuestionLayout.setVisibility(GONE);
                llMessageFriendsLayout.setVisibility(GONE);

            }
        });

        llMessageRulesQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageRulesQuestionLayout.setVisibility(View.VISIBLE);
                llMessageFinancialQuestionLayout.setVisibility(GONE);
                llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
                llMessageTechnicalQuestionLayout.setVisibility(GONE);
                llMessageGamesQuestionLayout.setVisibility(GONE);
                llMessageFriendsLayout.setVisibility(GONE);


            }
        });

        llMessageTechnicalQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageTechnicalQuestionLayout.setVisibility(View.VISIBLE);
                llMessageRulesQuestionLayout.setVisibility(GONE);
                llMessageFinancialQuestionLayout.setVisibility(GONE);
                llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
                llMessageGamesQuestionLayout.setVisibility(GONE);
                llMessageFriendsLayout.setVisibility(GONE);

            }
        });

        llMessageGamesQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageGamesQuestionLayout.setVisibility(View.VISIBLE);
                llMessageTechnicalQuestionLayout.setVisibility(GONE);
                llMessageRulesQuestionLayout.setVisibility(GONE);
                llMessageFinancialQuestionLayout.setVisibility(GONE);
                llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
                llMessageFriendsLayout.setVisibility(GONE);
            }
        });

        llMessageFriendsQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMessageFriendsLayout.setVisibility(View.VISIBLE);
                llMessageGamesQuestionLayout.setVisibility(GONE);
                llMessageTechnicalQuestionLayout.setVisibility(GONE);
                llMessageRulesQuestionLayout.setVisibility(GONE);
                llMessageFinancialQuestionLayout.setVisibility(GONE);
                llMessageAccountRelatedQuestionLayout.setVisibility(GONE);
                llMessageSupportMainFirstLayout.setVisibility(GONE);
            }
        });

        myAlertdisplayMessageDialog.show();

       ImageView imgVMessageCancel= myAlertdisplayMessageDialog.findViewById(R.id.imgVMessageCancel);

        imgVMessageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertdisplayMessageDialog.dismiss();

                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                llProfile.setVisibility(View.VISIBLE);
              //  linearLayout3.setVisibility(View.VISIBLE);
               // tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });

    }*/

    private void DisplaySupport() {

        final Dialog myAlertDisplaySupportDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertDisplaySupportDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertDisplaySupportDialog.setContentView(R.layout.support_layout);
        myAlertDisplaySupportDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

     //  final TextView tvSupport = myAlertDisplaySupportDialog.findViewById(R.id.tvSupport);
        final TextView tvRules = myAlertDisplaySupportDialog.findViewById(R.id.tvRules);
        final LinearLayout rlBodySupport = myAlertDisplaySupportDialog.findViewById(R.id.rlBodySupport);
        final RelativeLayout rlBodyRules = myAlertDisplaySupportDialog.findViewById(R.id.rlBodyRules);
       /* final LinearLayout llAccountRelatedQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llAccountRelatedQuestion);
        final LinearLayout  llAccountRelatedQuestionLayout = myAlertDisplaySupportDialog.findViewById(R.id.llAccountRelatedQuestionLayout);
        final LinearLayout llSupportMainFirstLayout  = myAlertDisplaySupportDialog.findViewById(R.id.llSupportMainFirstLayout);
        final LinearLayout llFinancialQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llFinancialQuestion);
        final LinearLayout  llFinancialQuestionLayout = myAlertDisplaySupportDialog.findViewById(R.id.llFinancialQuestionLayout);
        final LinearLayout llRulesQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llRulesQuestion);
        final LinearLayout  llRulesQuestionLayout = myAlertDisplaySupportDialog.findViewById(R.id.llRulesQuestionLayout);
        final LinearLayout llTechnicalQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llTechnicalQuestion);
        final LinearLayout  llTechnicalQuestionLayout = myAlertDisplaySupportDialog.findViewById(R.id.llTechnicalQuestionLayout);
        final LinearLayout llGamesQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llGamesQuestion);
        final LinearLayout  llGamesQuestionLayout = myAlertDisplaySupportDialog.findViewById(R.id.llGamesQuestionLayout);
        final LinearLayout llFriendsQuestion = myAlertDisplaySupportDialog.findViewById(R.id.llFriendsQuestion);
        final LinearLayout  llFriendsLayout = myAlertDisplaySupportDialog.findViewById(R.id.llFriendsLayout);*/





//visibilty gone from main page
        llHeaderCenter.setVisibility(GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(GONE);
        tvProfile.setVisibility(GONE);
        tvPlayerOnline.setVisibility(GONE);
        llProfile.setVisibility(GONE);
      //  linearLayout3.setVisibility(GONE);
       // tvFriendsPlayer.setVisibility(GONE);
        rlBodyPlayNow.setVisibility(GONE);
        rlBodyLobby.setVisibility(GONE);
        horizontalScrollView.setVisibility(GONE);
        tvDialogHeader.setText("Support");


      /*  llAccountRelatedQuestionLayout.setVisibility(GONE);
        llSupportMainFirstLayout.setVisibility(View.VISIBLE);
        llFinancialQuestionLayout.setVisibility(GONE);
        llRulesQuestionLayout.setVisibility(GONE);
        llTechnicalQuestionLayout.setVisibility(GONE);
        llGamesQuestionLayout.setVisibility(GONE);
        llFriendsLayout.setVisibility(GONE);*/


/*
        llAccountRelatedQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llAccountRelatedQuestionLayout.setVisibility(View.VISIBLE);
                llFinancialQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
                llRulesQuestionLayout.setVisibility(GONE);
                llTechnicalQuestionLayout.setVisibility(GONE);
                llGamesQuestionLayout.setVisibility(GONE);
                llFriendsLayout.setVisibility(GONE);

            }
        });

        llFinancialQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFinancialQuestionLayout.setVisibility(View.VISIBLE);
                llAccountRelatedQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
                llRulesQuestionLayout.setVisibility(GONE);
                llTechnicalQuestionLayout.setVisibility(GONE);
                llGamesQuestionLayout.setVisibility(GONE);
                llFriendsLayout.setVisibility(GONE);

            }
        });

        llRulesQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llRulesQuestionLayout.setVisibility(View.VISIBLE);
                llFinancialQuestionLayout.setVisibility(GONE);
                llAccountRelatedQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
                llTechnicalQuestionLayout.setVisibility(GONE);
                llGamesQuestionLayout.setVisibility(GONE);
                llFriendsLayout.setVisibility(GONE);


            }
        });

        llTechnicalQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llTechnicalQuestionLayout.setVisibility(View.VISIBLE);
                llRulesQuestionLayout.setVisibility(GONE);
                llFinancialQuestionLayout.setVisibility(GONE);
                llAccountRelatedQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
                llGamesQuestionLayout.setVisibility(GONE);
                llFriendsLayout.setVisibility(GONE);

            }
        });

        llGamesQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGamesQuestionLayout.setVisibility(View.VISIBLE);
                llTechnicalQuestionLayout.setVisibility(GONE);
                llRulesQuestionLayout.setVisibility(GONE);
                llFinancialQuestionLayout.setVisibility(GONE);
                llAccountRelatedQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
                llFriendsLayout.setVisibility(GONE);
            }
        });

        llFriendsQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFriendsLayout.setVisibility(View.VISIBLE);
                llGamesQuestionLayout.setVisibility(GONE);
                llTechnicalQuestionLayout.setVisibility(GONE);
                llRulesQuestionLayout.setVisibility(GONE);
                llFinancialQuestionLayout.setVisibility(GONE);
                llAccountRelatedQuestionLayout.setVisibility(GONE);
                llSupportMainFirstLayout.setVisibility(GONE);
            }
        });

        tvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBodySupport.setVisibility(View.VISIBLE);
              //  rlBodySupportFAQ.setVisibility(View.GONE);
                rlBodyRules.setVisibility(GONE);
                tvSupport.setBackgroundResource(R.drawable.bg_dialog_header);
               // tvFAQ.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                tvRules.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                support = true;
            }
        });*/




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
                rlBodySupport.setVisibility(GONE);
             //   rlBodySupportFAQ.setVisibility(View.GONE);
                rlBodyRules.setVisibility(View.VISIBLE);
                tvRules.setBackgroundResource(R.drawable.bg_dialog_header);
               // tvFAQ.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
              //  tvSupport.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                support = true;
            }
        });


        myAlertDisplaySupportDialog.show();

        imgVSupportCancel= myAlertDisplaySupportDialog.findViewById(R.id.imgVSupportCancel);
        imgVSupportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDisplaySupportDialog.dismiss();

                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                llProfile.setVisibility(View.VISIBLE);
              //  linearLayout3.setVisibility(View.VISIBLE);
             //   tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });
    }

   /* private void DisplaySignIn() {

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
    }*/

    private void displaySetting() {

        final Dialog myAlertdisplaySettingDialog = new Dialog(this,R.style.MyDialog_FullScreen);
        myAlertdisplaySettingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertdisplaySettingDialog.setContentView(R.layout.tab_setting_layout);
        myAlertdisplaySettingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        tvGeneral = myAlertdisplaySettingDialog.findViewById(R.id.tvGeneral);
       // tvNotification = myAlertdisplaySettingDialog.findViewById(R.id.tvNotification);
        cancel = myAlertdisplaySettingDialog.findViewById(R.id.cancel);

        llGeneral = myAlertdisplaySettingDialog.findViewById(R.id.llGeneral);
       // llNotification = myAlertdisplaySettingDialog.findViewById(R.id.llNotification);





        //visibility gone from main page
        llHeaderCenter.setVisibility(GONE);
        llDialogText.setVisibility(View.VISIBLE);
        rlBodyCenterBody.setVisibility(GONE);
        tvProfile.setVisibility(GONE);
        tvPlayerOnline.setVisibility(GONE);
        llProfile.setVisibility(GONE);
      //  linearLayout3.setVisibility(GONE);
//        tvFriendsPlayer.setVisibility(GONE);
        rlBodyPlayNow.setVisibility(GONE);
        rlBodyLobby.setVisibility(GONE);
        horizontalScrollView.setVisibility(GONE);

   /*     tvGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // llNotification.setVisibility(GONE);
                llGeneral.setVisibility(View.VISIBLE);
                tvGeneral.setBackgroundResource(R.drawable.bg_dialog_header);
              //  tvNotification.setBackgroundResource(R.drawable.bg_dialog_header_unselected);
                    setting=true;
            }
        });*/
  /*      tvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llNotification.setVisibility(View.VISIBLE);
                tvNotification.setBackgroundResource(R.drawable.bg_dialog_header);
                tvGeneral.setBackgroundResource(R.drawable.bg_dialog_header_unselected);

                         llGeneral.setVisibility(GONE);
                        setting=true;
            }
        });*/


        myAlertdisplaySettingDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertdisplaySettingDialog.dismiss();
                llHeaderCenter.setVisibility(View.VISIBLE);
                llDialogText.setVisibility(GONE);
                rlBodyCenterBody.setVisibility(View.VISIBLE);
                tvProfile.setVisibility(View.VISIBLE);
                tvPlayerOnline.setVisibility(View.VISIBLE);
                llProfile.setVisibility(View.VISIBLE);
               // linearLayout3.setVisibility(View.VISIBLE);
              //  tvFriendsPlayer.setVisibility(View.VISIBLE);
                rlBodyPlayNow.setVisibility(View.VISIBLE);
                rlBodyLobby.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });


    }


    }

