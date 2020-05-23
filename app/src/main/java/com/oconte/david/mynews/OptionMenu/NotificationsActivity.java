package com.oconte.david.mynews.OptionMenu;

import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.R;
import com.oconte.david.mynews.Utils.App;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.oconte.david.mynews.Utils.App.CHANNEL_ID;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.notification_query_term) EditText notificationQueryTerm;
    @BindView(R.id.notification_switch) SwitchCompat switchNotification;

    // CheckBox
    @BindView(R.id.notification_item_art) CheckBox mNotiArt;
    @BindView(R.id.notification_item_business) CheckBox mNotiBusiness;
    @BindView(R.id.notification_item_entrepreneurs) CheckBox mNotiEntrepreneurs;
    @BindView(R.id.notification_item_politics) CheckBox mNotiPolitics;
    @BindView(R.id.notification_item_sport) CheckBox mNotiSport;
    @BindView(R.id.notification_item_travel) CheckBox mNotiTravel;

    private SharedPreferences preferences;
    private static final String EXTRA_NOTI_QUERY = "extra_noti_query";
    private static final String EXTRA_NOTI_ART = "extra_noti_art";
    private static final String EXTRA_NOTI_BUSINESS = "extra_noti_business";
    private static final String EXTRA_NOTI_ENTREPRENEURS = "extra_noti_entrepreneurs";
    private static final String EXTRA_NOTI_POLITICS = "extra_noti_politics";
    private static final String EXTRA_NOTI_SPORTS = "extra_noti_sports";
    private static final String EXTRA_NOTI_TRAVEL = "extra_noti_travel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    getPreferencesNotificationsAndSave();
                    getWorkManager();
                }
            }
        });

        this.configureToolbar();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * This for the ToolBar
     */
    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        //afficher le bouton retour

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getWorkManager(){
        Data data = new Data.Builder()
                .putString(App.CHANNEL_ID, "There are number of page for the result searchNotification.")
                .build();

        //This is the subclass of our WorkRequest
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(App.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().enqueue(workRequest);
    }

    private void getPreferencesNotificationsAndSave() {

        String query = notificationQueryTerm.getText().toString();

        String art = null;
        if (mNotiArt.isChecked()) {
            art = mNotiArt.getText().toString();
        }

        String business = null;
        if (mNotiBusiness.isChecked()) {
            business = mNotiBusiness.getText().toString();
        }

        String entrepreneurs = null;
        if (mNotiEntrepreneurs.isChecked()) {
            entrepreneurs = mNotiEntrepreneurs.getText().toString();
        }

        String politics = null;
        if (mNotiPolitics.isChecked()) {
            politics = mNotiPolitics.getText().toString();
        }

        String sports = null;
        if (mNotiSport.isChecked()) {
            sports = mNotiSport.getText().toString();
        }

        String travel = null;
        if (mNotiTravel.isChecked()) {
            travel = mNotiTravel.getText().toString();
        }

        SharedPreferences preferences = getSharedPreferences("EXTRA_NOTI_", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EXTRA_NOTI_QUERY, query);
        editor.putString(EXTRA_NOTI_ART, art);
        editor.putString(EXTRA_NOTI_BUSINESS, business);
        editor.putString(EXTRA_NOTI_ENTREPRENEURS, entrepreneurs);
        editor.putString(EXTRA_NOTI_POLITICS, politics);
        editor.putString(EXTRA_NOTI_SPORTS, sports);
        editor.putString(EXTRA_NOTI_TRAVEL, travel);
        editor.apply();



    }

    public void getPreferencesNotificationsFromSave() {
        preferences = getBaseContext().getSharedPreferences("EXTRA_NOTI_", MODE_PRIVATE);
        preferences.getString(EXTRA_NOTI_QUERY, null);
        preferences.getString(EXTRA_NOTI_ART, null);
        preferences.getString(EXTRA_NOTI_BUSINESS, null);
        preferences.getString(EXTRA_NOTI_ENTREPRENEURS, null);
        preferences.getString(EXTRA_NOTI_POLITICS, null);
        preferences.getString(EXTRA_NOTI_SPORTS, null);
        preferences.getString(EXTRA_NOTI_TRAVEL, null);

    }

}
