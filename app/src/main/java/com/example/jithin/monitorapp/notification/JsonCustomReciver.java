package com.example.jithin.monitorapp.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.jithin.monitorapp.register.RegisterActivity;
import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by Master on 3/19/2018.
 */

public class JsonCustomReciver extends ParsePushBroadcastReceiver {
    private static final String TAG = "JsonCustomReciver";

    @Override
    protected Class<? extends Activity> getActivity(Context context, Intent intent) {


        return RegisterActivity.class;
    }


}