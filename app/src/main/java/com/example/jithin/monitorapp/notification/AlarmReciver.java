package com.example.jithin.monitorapp.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.jithin.monitorapp.LoginActivity;
import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.patient.SurveyActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Master on 3/19/2018.
 */

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.push_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000});

        int notificationid = 001;

        Intent resultintent = new Intent(context, SurveyActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultintent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);


        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notificationid, mBuilder.build());


    }


}

