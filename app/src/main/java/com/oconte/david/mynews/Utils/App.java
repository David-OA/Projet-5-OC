package com.oconte.david.mynews.Utils;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.MODE_PRIVATE;

public class App extends Worker implements NYTCallsSearch.Callbacks {

    private SharedPreferences preferences;
    private String queryTerm;
    private String querySection;
    private Context context;

    private static final String EXTRA_NOTI_QUERY = "extra_noti_query";
    private static final String EXTRA_NOTI_ART = "extra_noti_art";
    private static final String EXTRA_NOTI_BUSINESS = "extra_noti_business";
    private static final String EXTRA_NOTI_ENTREPRENEURS = "extra_noti_entrepreneurs";
    private static final String EXTRA_NOTI_POLITICS = "extra_noti_politics";
    private static final String EXTRA_NOTI_SPORTS = "extra_noti_sports";
    private static final String EXTRA_NOTI_TRAVEL = "extra_noti_travel";

    public static final String CHANNEL_ID = "channel";
    private Result result;

    public App(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        executeHttpRequestWithRetrofit();

        return Result.success();
    }

    private void displayNotification(String title, String query) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(query)
                .setSmallIcon(R.drawable.new_york_time_icon);

        notificationManager.notify(1, notification.build());
    }

    private void getDataFromPreferences(){
        preferences = context.getSharedPreferences("EXTRA_NOTI_", MODE_PRIVATE);
        queryTerm = preferences.getString(EXTRA_NOTI_QUERY, null);
        querySection = preferences.getString(EXTRA_NOTI_ART, null);
        querySection = preferences.getString(EXTRA_NOTI_BUSINESS, null);
        querySection = preferences.getString(EXTRA_NOTI_ENTREPRENEURS, null);
        querySection = preferences.getString(EXTRA_NOTI_POLITICS, null);
        querySection = preferences.getString(EXTRA_NOTI_SPORTS, null);
        querySection = preferences.getString(EXTRA_NOTI_TRAVEL, null);
    }

    private void executeHttpRequestWithRetrofit() {
        getDataFromPreferences();

        NYTCallsSearch.getSearchSection(this, null, null, queryTerm, querySection, 0);
    }

    @Override
    public void onResponse(@Nullable SearchResult response) {
        if (response == null || response.getResponse().getDocs().size() == 0){
            //noMoreNew();
        } else if (response.getResponse().getDocs().size() != 0){
            //this.result = response;
            String taskDesc = getInputData().getString(CHANNEL_ID);
            displayNotification("My News", taskDesc + "Pages Founds");
        }
    }

    @Override
    public void onFailure() {

    }

    /*public void noMoreNew() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert ! ");
        myAlertDialogue.setMessage("No more News");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }*/

}
