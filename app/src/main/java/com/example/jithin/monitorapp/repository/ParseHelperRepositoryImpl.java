package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.model.Patient;
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

    /*private Parse parse;*/
    private Context mContext;

    public ParseHelperRepositoryImpl(Context mContext) {
        this.mContext = mContext;
        Parse.initialize(mContext);
    }

    /**
     * get user type
     * @param object
     * @param e
     * @return
     */
    @Override
    public String getUserType(ParseUser object, ParseException e) {



        String usertype = null;


                if (e == null) {

                    Log.i(TAG, "usertype: " + object.getString("usertype"));

                    usertype = object.getString("usertype");

                } else {

                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

        return usertype;
    }

    /**
     * create user database
     * @param user
     */
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

    /**
     * create total energy exp database
     * @param user
     */
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

    /**
     * calculate risk factor
     */
    @Override
    public void calculateRiskFactor() {
       // parseHelper = new ParseHelper();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

        // TODO: 3/1/2018 user may cause some problem you should check later
        query.whereEqualTo("user", ParseUser.getCurrentUser());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e == null) {
                    Patient patient = getPatientDetails(object, e);
                    calculateTotalRiskFactor(patient);

                } else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    /**
     * calculating total energy exp
     * @param patient
     */
   /* @Override*/
    public void calculateTotalEnergyExp(Patient patient) {

        int num = patient.getPatient_age();
        int height = patient.getPatient_height();
         int weight = patient.getPatient_weight();

        final float BMR;
        BMR = (float) (66 + (1.37 * weight) + (5 * height) - (6.8 * num));

        Log.i(TAG, "totalEnergyExp: " + BMR);

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Survey");
        parseQuery.whereEqualTo("owner", ParseUser.getCurrentUser().getUsername());
        parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    String userWork = object.getString("work");
                    String objid = object.getString("objid");

                    tDEEMATH(objid, BMR, userWork);

                } else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * calculation goes on here
     * @param objid
     * @param BMR
     * @param userWork
     */
    private void tDEEMATH(String objid, float BMR, String userWork) {

        double tDEE = 0;


        if (userWork.equals("Sedantary")) {

            tDEE = BMR * 1.2;


        } else if (userWork.equals("Lightly Active")) {

            tDEE = BMR * 1.375;
        } else if (userWork.equals("Moderatively Active")) {

            tDEE = BMR * 1.55;
        } else if (userWork.equals("Very Active")) {

            tDEE = BMR * 1.725;
        } else if (userWork.equals("Extremely Active")) {

            tDEE = BMR * 1.79;
        } else {
            Toast.makeText(mContext, ".....", Toast.LENGTH_SHORT).show();
        }

        Log.i(TAG, "tDEEMATH: " + tDEE);


        saveTdee(objid, tDEE);

    }


    /**
     * saving this into server
     * @param objid
     * @param tDEE
     */
    private void saveTdee(String objid, final double tDEE) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Survey");
        query.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e == null) {

                    object.put("TDEE", tDEE);
                    object.saveInBackground();
                    Log.i(TAG, "survey data saved sucessfully"+object.getObjectId());


                } else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    /**
     * save data to server
     * @param riskscore
     * @param objid
     */
    @Override
    public void updateRiskScore(final int riskscore, String objid) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(mContext.getString(R.string.db_userDetails));

        query.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e == null) {

                    object.put("riskScore", riskscore);
                    object.saveInBackground();

                    Log.i(TAG, "done: " + riskscore);


                } else {

                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * getting details of patient
     * @param object
     * @param e
     * @return
     */
    public Patient getPatientDetails(ParseObject object, ParseException e) {
        Patient patient = new Patient();
        Log.i(TAG, "done: " + object.getString("age"));

        int num = object.getInt("age");
        int height = object.getInt("height");
        int weight = object.getInt("weight");
        int tchget = object.getInt("totalchol");
        int hdl = object.getInt("hdlChol");
        String smoke = object.getString("smoker");
        String treatment = object.getString("have_treatment");
        int symbp = object.getInt("symbp");
        String objid = object.getString("objid");


        patient.setPatient_age(num);
        patient.setPatient_weight(weight);
        patient.setpatient_height(height);
        patient.setPatient_total_chol(tchget);
        patient.setPatient_hdl_chol(hdl);
        patient.setPatient_smoker(smoke);
        patient.setPatient_objid(objid);
        patient.setPatient_have_treatment(treatment);
        patient.setPatient_symbp(symbp);

        Log.i(TAG, "getPatientDetails: " + patient.toString());

        return patient;
    }

    /**
     * calculate rsik factor from patient details
     * @param patient
     */
    private void calculateTotalRiskFactor(Patient patient) {

        int num = patient.getPatient_age();
        int hdl = patient.getPatient_hdl_chol();
        int tchget = patient.getPatient_total_chol();
        String smoke = patient.getPatient_smoker();
        String treatment = patient.getPatient_have_treatment();
        String objid = patient.getPatient_objid();

        final int riskscore = checkAgePoints(num) + checkHdlPointns(hdl) + checkTchPoints(num, tchget) + checkSmoker(smoke) + checkTreatment(treatment);

        // TODO: 3/2/2018 update riskscore
        updateRiskScore(riskscore,objid);

        Log.i(TAG, "totalriskFactor: " + riskscore);
    }



    /**
     * checking age point
     * @param num
     * @return
     */
    private int checkAgePoints(int num) {
        int agepoints = -1;

        int age = num;

        if (age >= 20 && age <= 34) {

            agepoints = -9;

        } else if (age >= 35 && age <= 39) {

            agepoints = -4;
        } else if (age >= 40 && age <= 44) {

            agepoints = 0;
        } else if (age >= 45 && age <= 49) {
            agepoints = 3;
        } else if (age >= 50 && age <= 54) {

            agepoints = 6;
        } else if (age >= 55 && age <= 59) {
            agepoints = 8;

        } else if (age >= 60 && age <= 64) {
            agepoints = 10;

        } else if (age >= 65 && age <= 69) {
            agepoints = 12;

        } else if (age >= 70 && age <= 74) {
            agepoints = 14;

        } else if (age >= 74 && age <= 79) {
            agepoints = 16;

        } else {
            agepoints = 0;
        }


        Log.i(TAG, "checkAgePoints: " + agepoints);
        return agepoints;
    }

    /**
     * checkin hdl point for risk score
     * @param hdl
     * @return
     */
    private int checkHdlPointns(int hdl) {

        int hdlpoints = -1;
        int testchole = hdl;

        if (testchole < 40) {
            hdlpoints = 2;
        } else if (testchole >= 40 && testchole <= 49) {
            hdlpoints = 1;
        } else if (testchole >= 50 && testchole <= 59) {
            hdlpoints = 0;
        } else if (testchole >= 60) {
            hdlpoints = -1;
        } else {
            hdlpoints =0;
            Log.i(TAG, "checkHdlPointns: invalid option");
        }

        Log.i(TAG, "checkHdlPointns: " + testchole);

        Log.i(TAG, "checkHdlPointns: " + hdlpoints);

        return hdlpoints;
    }

    /**
     * check total cholerstrol and age point for total risk factor
     * @param num
     * @param tchget
     * @return
     */
    private int checkTchPoints(int num, int tchget) {
        int tchpoints = 1;
        int age = num;
        int tch = tchget;

        if (age >= 20 && age <= 39) {

            if (tch < 160) {
                tchpoints = 0;
            } else if (tch >= 169 && tch <= 199) {
                tchpoints = 4;
            } else if (tch >= 200 && tch < 239) {
                tchpoints = 7;
            } else if (tch >= 240 && tch <= 279) {
                tchpoints = 9;
            } else if (tch >= 280) {
                tchpoints = 11;
            }

        } else if (age >= 40 && age <= 49) {

            if (tch < 160) {
                tchpoints = 0;
            } else if (tch >= 169 && tch <= 199) {
                tchpoints = 3;
            } else if (tch >= 200 && tch < 239) {
                tchpoints = 5;
            } else if (tch >= 240 && tch <= 279) {
                tchpoints = 6;
            } else if (tch >= 280) {
                tchpoints = 8;
            }
        } else if (age >= 50 && age <= 59) {

            if (tch < 160) {
                tchpoints = 0;
            } else if (tch >= 169 && tch <= 199) {
                tchpoints = 2;
            } else if (tch >= 200 && tch < 239) {
                tchpoints = 3;
            } else if (tch >= 240 && tch <= 279) {
                tchpoints = 4;
            } else if (tch >= 280) {
                tchpoints = 5;
            }
        } else if (age >= 60 && age <= 69) {
            if (tch < 160) {
                tchpoints = 0;
            } else if (tch >= 169 && tch <= 199) {
                tchpoints = 1;
            } else if (tch >= 200 && tch < 239) {
                tchpoints = 1;
            } else if (tch >= 240 && tch <= 279) {
                tchpoints = 2;
            } else if (tch >= 280) {
                tchpoints = 3;
            }
        } else if (age >= 70 && age <= 79) {

            if (tch < 160) {
                tchpoints = 0;
            } else if (tch >= 169 && tch <= 199) {
                tchpoints = 0;
            } else if (tch >= 200 && tch < 239) {
                tchpoints = 0;
            } else if (tch >= 240 && tch <= 279) {
                tchpoints = 1;
            } else if (tch >= 280) {
                tchpoints = 1;
            }
        } else {
            tchpoints = 0;
        }


        Log.i(TAG, "checkTchPoints: " + tchpoints);

        return tchpoints;
    }

    /**
     * calculating smoker point for total risk factor
     * @param smoke
     * @return
     */
    private int checkSmoker(String smoke) {
        int smokepoint;
        if (smoke.equals("yes")) {
            smokepoint = 1;
        } else {
            smokepoint = 0;
        }

        Log.i(TAG, "checkSmoker: " + smokepoint);

        return smokepoint;
    }

    /**
     * check have treatment before
     * @param treatment
     * @return
     */
    private int checkTreatment(String treatment) {
        int tretPoint;
        if (treatment.equals("yes")) {

            tretPoint = 1;
        } else {
            tretPoint = 0;
        }
        Log.i(TAG, "checkTreatment: " + tretPoint);
        return tretPoint;
    }
}
