package com.oconte.david.mynews.OptionMenu;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.notification_query_term)
    EditText notificationQueryTerm;
    @BindView(R.id.notification_switch)
    SwitchCompat switchNotification;

    // CheckBox
    @BindView(R.id.notification_item_art)
    CheckBox mNotiArt;
    @BindView(R.id.notification_item_business) CheckBox mNotiBusiness;
    @BindView(R.id.notification_item_entrepreneurs) CheckBox mNotiEntrepreneurs;
    @BindView(R.id.notification_item_politics) CheckBox mNotiPolitics;
    @BindView(R.id.notification_item_sport) CheckBox mNotiSport;
    @BindView(R.id.notification_item_travel) CheckBox mNotiTravel;

    private NotificationManagerCompat notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        notificationManager = NotificationManagerCompat.from(this);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sendOnChannel();
                }
            }
        });

        this.configureToolbar();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        //afficher le bouton retour

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void  sendOnChannel() {
        String title = notificationQueryTerm.getText().toString();
        String checkbox = mNotiArt.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_york_time_icon)
                .setContentTitle(title)
                .setContentText(checkbox)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}
