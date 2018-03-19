package com.example.jithin.monitorapp.repository;

import android.content.Context;

import com.example.jithin.monitorapp.model.Patientdetails;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Master on 3/19/2018.
 */

public class DoctorRepositoryImpl implements DoctorRepository {

    private static final String TAG = "DoctorRepositoryImpl";
    private Context mContext;

    public DoctorRepositoryImpl(Context mContext) {
        this.mContext = mContext;
        Parse.initialize(mContext);
    }

    @Override
    public Patientdetails getPatForRec(ParseUser parseUser) {

        Patientdetails patientdetails = null;

            String username = parseUser.getUsername();
            String status = parseUser.getString("status");

            patientdetails = new Patientdetails(username, status);

        return patientdetails;
    }
}
