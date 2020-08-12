package com.oconte.david.mynews.OptionMenu;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oconte.david.mynews.MainActivity;
import com.oconte.david.mynews.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    // For Design
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

    public Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!mNotiArt.isChecked() && !mNotiBusiness.isChecked() && !mNotiEntrepreneurs.isChecked() && !mNotiPolitics.isChecked() && !mNotiSport.isChecked() && !mNotiTravel.isChecked()) {
                        forgetCheckBox();
                    } else if (notificationQueryTerm.length() <= 0) {
                        errorQueryTerm();
                    } else {
                        getPreferencesNotificationsAndSave();
                        startAlarmForWorkManager();
                        toast();
                        becomeHomePage();
                    }
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

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This is for said at WorkManager you start at 12 h and for all Day you work at this time.
     */
    @SuppressLint("NewApi")
    private void startAlarmForWorkManager() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    /**
     * This is for save data in the sharedPreferences.
     */
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

    /**
     * It's for custom Toast.
     */
    private void toast() {

        Toast toast = Toast.makeText(getBaseContext(), "The notification is ready", Toast.LENGTH_LONG);
        View view = toast.getView();
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextSize(16);
        view.setBackgroundColor(Color.BLUE);
        toast.show();
    }

    /**
     * When you do a Notification after you return at home page.
     */
    private void becomeHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This is for error when you forget or use wrong argument.
     */
    public void errorQueryTerm() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert !");
        myAlertDialogue.setMessage("You forget something");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }

    public void forgetCheckBox() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert !");
        myAlertDialogue.setMessage("You need to choice one ore more categories");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }

}
