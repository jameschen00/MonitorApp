package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.model.Patient;
import com.example.jithin.monitorapp.model.Patientdetails;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
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

    /**
     * recycler view patient details
     * @param parseUser
     * @return
     */
    @Override
    public Patientdetails getPatForRec(ParseUser parseUser) {

        Patientdetails patientdetails = null;

            String username = parseUser.getUsername();
            String status = parseUser.getString("status");

            patientdetails = new Patientdetails(username, status);

        return patientdetails;
    }

    /**
     * get patient details
     * @param object
     * @parame
     * @return
     */
    @Override
    public Patient getDetails(ParseObject object) {

            int age = object.getInt("age");
            int total_cholestrol = object.getInt("totalchol");
            int hdl_cholestrol = object.getInt("hdlChol");
            int symbolicbp = object.getInt("symbp");
            int riskScore = object.getInt("riskScore");
            int weight = object.getInt("weight");
            int height = object.getInt("height");
            String smoker = object.getString("smoker");
            String have_treatment = object.getString("have_treatment");

            Patient patient = new Patient(age,weight,height,total_cholestrol,hdl_cholestrol,smoker
                    ,have_treatment, symbolicbp,riskScore);

        Log.i(TAG, "getDetails: "+patient.toString());



            /*setUserDetails(username, age, riskScore, smoker, have_treatment);
            setGraph(username, weight, height, total_cholestrol, hdl_cholestrol, symbolicbp);*/




        return patient;
    }
}
