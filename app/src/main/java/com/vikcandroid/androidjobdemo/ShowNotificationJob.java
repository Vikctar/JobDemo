package com.vikcandroid.androidjobdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by android-dev on 10/3/17.
 */

public class ShowNotificationJob extends Job {

    static final String TAG = "show_notification_job_tag";

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(), MainActivity.class), 0);

//        Notification notification = new NotificationCompat.Builder(getContext())
//                .setContentTitle("Android Job Demo")
//                .setContentText("Notification from Android Job Demo.")
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setShowWhen(true)
//                .setColor(Color.RED)
//                .setLocalOnly(true)
//                .build();
//
//        NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);
        showPopupNotification();

        return Result.SUCCESS;
    }

    static void schedulePeriodicJob() {
        new JobRequest.Builder(ShowNotificationJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setUpdateCurrent(true)
                .setPersisted(true)
                .build()
                .schedule();
    }

    private void showPopupNotification() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://fundilistapp.com/api/v1/client_bids/12",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
                                new Intent(getContext(), MainActivity.class), 0);

                        Notification notification = new NotificationCompat.Builder(getContext())
                                .setContentTitle("Android Job Demo")
                                .setContentText("Notification from Android Job Demo.")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                                .setDefaults(Notification.DEFAULT_SOUND)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setShowWhen(true)
                                .setColor(Color.RED)
                                .setLocalOnly(true)
                                .build();

                        NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        App.getInstance().addToRequestQueue(request);
    }
}
