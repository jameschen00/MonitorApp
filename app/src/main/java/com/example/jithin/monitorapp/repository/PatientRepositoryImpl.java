package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.patient.PatientHomeActivity;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Master on 3/19/2018.
 */

public class PatientRepositoryImpl implements PatientRepository {

    private static final String TAG = "PatientRepositoryImpl";
    private Context mContext;
    private ParseHelperRepositoryImpl parseHelperRepository;

    public PatientRepositoryImpl(Context mContext) {
        this.mContext = mContext;
        Parse.initialize(mContext);
        parseHelperRepository=new ParseHelperRepositoryImpl(mContext);
    }

    @Override
    public void updatingUserProfileDetails(String objid, final String age, final String weight, final String height, final String totalchol, final String hdlChol, final String symbp, final String havesmoker, final String havetreatment) {
        final ParseQuery<ParseObject> userdetatil = ParseQuery.getQuery("UserDetails");

        userdetatil.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject updateprofile, ParseException e) {


                if (e == null) {

                    updateprofile.put("age", Integer.parseInt(age));
                    updateprofile.put("weight", Integer.parseInt(weight));
                    updateprofile.put("height", Integer.parseInt(height));
                    updateprofile.put("totalchol", Integer.parseInt(totalchol));
                    updateprofile.put("hdlChol", Integer.parseInt(hdlChol));
                    updateprofile.put("symbp", Integer.parseInt(symbp));

                    updateprofile.put("smoker", havesmoker);
                    updateprofile.put("have_treatment", havetreatment);

                    updateprofile.saveInBackground();



                    // calling another class
                    parseHelperRepository.CalculateRiskFactor();


                    // TODO: 3/1/2018 navigate to home
                    Intent intent = new Intent(mContext, PatientHomeActivity.class);
                    mContext.startActivity(intent);


                } else {
                    Toast.makeText(mContext, "please select", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * upload survey data
     * @param objid
     * @param headPoint
     * @param nauseaPoint
     * @param fatiguePoint
     * @param faintingPoint
     * @param dizzPoint
     * @param pressurePoint
     * @param spinnerData
     */
    @Override
    public void uploadSurveyDataSecond(String objid, final String headPoint, final String nauseaPoint, final String fatiguePoint, final String faintingPoint, final String dizzPoint, final String pressurePoint, final String spinnerData) {

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Survey");

        parseQuery.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject surveyobj, ParseException e) {

                if (e == null) {


                    surveyobj.put("checkHead", headPoint);
                    surveyobj.put("checkNausea", nauseaPoint);
                    surveyobj.put("checkDizz", dizzPoint);
                    surveyobj.put("checkFaint", faintingPoint);
                    surveyobj.put("checkFatigue", fatiguePoint);
                    surveyobj.put("checkPressure", pressurePoint);
                    surveyobj.put("work", spinnerData);
                    surveyobj.put("owner", ParseUser.getCurrentUser().getUsername());

                    surveyobj.saveInBackground();

                    Log.i(TAG, "done: data saved");

                    // set up thread


                    Intent intent = new Intent(mContext, PatientHomeActivity.class);
                    mContext.startActivity(intent);

                } else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
