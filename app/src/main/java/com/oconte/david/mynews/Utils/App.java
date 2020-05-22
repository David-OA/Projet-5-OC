package com.oconte.david.mynews.Utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import com.oconte.david.mynews.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class App extends Worker {

    public static final String CHANNEL_ID = "channel";

    public App(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //getting the input data
        String taskDesc = getInputData().getString(CHANNEL_ID);

        displayNotification("My News", taskDesc);

        //setting output data
        /*Data data = new Data.Builder()
                .putString(CHANNEL_ID, "number of page for search")
                .build();*/


        return Result.success();
    }

    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.drawable.new_york_time_icon);

        notificationManager.notify(1, notification.build());
    }

    private void executeHttpRequestWithRetrofit() {
        //getSearchQuery();

        //NYTCallsSearch.getSearchSection(this, correctBeginDate, correctendDate, sectionTerm, query, 10);
    }
}
