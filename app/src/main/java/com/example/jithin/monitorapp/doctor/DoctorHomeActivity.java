package com.example.jithin.monitorapp.doctor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.adapters.PatientListAdapter;
import com.example.jithin.monitorapp.model.Patientdetails;
import com.example.jithin.monitorapp.services.DoctorServiceImpl;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DoctorHomeActivity extends AppCompatActivity {

    private static final String TAG = "DoctorHomeActivity";

    RecyclerView recyclerView;
    /*private LinearLayoutManager mlayoutManager;*/
    GridLayoutManager gridLayoutManager;
    private PatientListAdapter adapter;
    /*initialize widgets*/
    private ArrayList<Patientdetails> userlist;
    private Context mContext;

    private DoctorServiceImpl doctorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        mContext = DoctorHomeActivity.this;

        doctorService = new DoctorServiceImpl(this);

        setupRecyclerView();


    }

    /**
     * set up recyclerview
     */
    private void setupRecyclerView() {
        userlist = new ArrayList<>();
        recyclerView = findViewById(R.id.patientListRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new PatientListAdapter(this, userlist);

        recyclerView.setAdapter(adapter);
        parseUserList();
    }

    /**
     * data for recycler view
     */
    private void parseUserList() {

        ParseQuery<ParseUser> userquery = ParseUser.getQuery();
        userquery.whereEqualTo("usertype", "patient");

        userquery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                for (ParseUser parseUser:objects){

                    if (objects.size()>0){
                        userlist.add(doctorService.getPatForRec(parseUser));
                        Log.i(TAG, "done: "+userlist.toString());

                    }else {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();

            }
        });

    }
}
