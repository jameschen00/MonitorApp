package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by jithin on 18/3/18.
 */

public class ParseHelperRepositoryImpl implements ParseHelperRepository{

    private static final String TAG = "ParseHelperRepositoryIm";

    private Parse parse;
    private Context mContext;

    public ParseHelperRepositoryImpl(Context mContext) {
        this.mContext = mContext;
        Parse.initialize(mContext);
    }

    @Override
    public String getUserType(ParseUser object, ParseException e) {



        String usertype = null;


                if (e == null) {

                    Log.i(TAG, "usertype: " + object.getString("usertype"));

                    usertype = object.getString("usertype");

                  /*  if (object.getString("usertype").equals("patient")) {

                        Intent intent = new Intent(mContext, PatientHomeActivity.class);
                        mContext.startActivity(intent);


                    } else if (object.getString("usertype").equals("admin")) {
                        Intent intent = new Intent(mContext, AdminHomeActivity.class);
                        mContext.startActivity(intent);

                    } else {
                        Intent intent = new Intent(mContext, DoctorHomeActivity.class);
                        mContext.startActivity(intent);
                    }*/

                } else {

                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

        return usertype;
    }

    @Override
    public void createUserDetails(ParseUser user) {
        final ParseObject userdetatil = new ParseObject(mContext.getString(R.string.db_userDetails));
        String username = ParseUser.getCurrentUser().getUsername();
        user = ParseUser.getCurrentUser();


        userdetatil.put(mContext.getString(R.string.c_age), 25);
        userdetatil.put(mContext.getString(R.string.c_weight), 55);
        userdetatil.put(mContext.getString(R.string.c_height), 160);
        userdetatil.put(mContext.getString(R.string.c_totaChol), 85);
        userdetatil.put(mContext.getString(R.string.c_hdlChol), 52);
        userdetatil.put(mContext.getString(R.string.c_symbp), 50);
        userdetatil.put(mContext.getString(R.string.c_owner), username);
        userdetatil.put(mContext.getString(R.string.c_user), user);
        userdetatil.put(mContext.getString(R.string.c_smoker), "no");
        userdetatil.put(mContext.getString(R.string.c_treatment), "yes");
        userdetatil.put(mContext.getString(R.string.c_riskScore), 7);


        userdetatil.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e != null) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Log.i(TAG, "done: user details updated" + userdetatil);
                saveObjid("UserDetails", userdetatil.getObjectId());
            }
        });
    }

    /**
     * save object id for update database
     * @param userDetails
     * @param objectId
     */
    private void saveObjid(String userDetails, final String objectId) {
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>(userDetails);

        parseQuery.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e != null) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                object.put("objid", objectId);
                object.saveInBackground();
            }
        });

    }

    @Override
    public void createTDEE(ParseUser user) {

        if (user == null) {
            Toast.makeText(mContext, "no user present", Toast.LENGTH_SHORT).show();
        }

        final ParseObject surveyobj = new ParseObject(mContext.getString(R.string.db_survey));

        surveyobj.put(mContext.getString(R.string.c_checkHead), "no");
        surveyobj.put(mContext.getString(R.string.c_nausea), "no");
        surveyobj.put(mContext.getString(R.string.c_dizz), "no");
        surveyobj.put(mContext.getString(R.string.c_faint), "no");
        surveyobj.put(mContext.getString(R.string.c_fatigu), "no");
        surveyobj.put(mContext.getString(R.string.c_pressure), "no");
        surveyobj.put(mContext.getString(R.string.c_work), "no");
        surveyobj.put(mContext.getString(R.string.c_tdee), 1.56);
        surveyobj.put(mContext.getString(R.string.c_owner_survey), ParseUser.getCurrentUser().getUsername());

        surveyobj.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    Toast.makeText(mContext, "Data saved successfully", Toast.LENGTH_SHORT).show();

                    String surveyid = surveyobj.getObjectId();
                    saveObjid("Survey", surveyid);

                } else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
