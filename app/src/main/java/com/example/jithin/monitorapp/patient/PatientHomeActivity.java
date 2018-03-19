package com.example.jithin.monitorapp.patient;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.notification.AlarmReciver;
import com.parse.ParseUser;

import java.util.Calendar;

public class PatientHomeActivity extends AppCompatActivity {

    private static final String TAG = "PatientHomeActivity";

    private Toolbar toolbar;
    private AlarmManager alarmManger;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        // set tool bar
        /*toolbar = findViewById(R.id.patientHomeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ParseUser.getCurrentUser().getUsername());*/


        //set up calender alarm
        setupAlarm(alarmManger);


    }

    /**
     * create alarm manager for periodic update
     * @param alarmManger
     */
    private void setupAlarm(AlarmManager alarmManger) {

        long t = System.currentTimeMillis();


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 7);


        Intent intent = new Intent(this, AlarmReciver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManger = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManger.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY
                , pendingIntent);

    }



    //enter profile details
    public void updateProfile(View view) {

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

        startActivity(intent);
    }

    //enter sensor reading
    public void sensorRead(View view) {

       /* Intent intent = new Intent(this, ThreadActivity.class);
        startActivity(intent);*/
        Toast.makeText(this, "pressed ", Toast.LENGTH_SHORT).show();


    }

    //daily survey
    public void dailySurvey(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }
}
