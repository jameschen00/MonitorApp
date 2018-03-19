package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.LoginActivity;
import com.example.jithin.monitorapp.admin.AdminHomeActivity;
import com.example.jithin.monitorapp.doctor.DoctorHomeActivity;
import com.example.jithin.monitorapp.patient.PatientHomeActivity;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by jithin on 18/3/18.
 */

public class AuthRepositoryImpl implements AuthRepository {

    private static final String TAG = "AuthRepositoryImpl";

    private Parse parse;
    private Context mContext;

    ParseHelperRepositoryImpl parseHelperRepository;

    public AuthRepositoryImpl(Context mContext) {

        this.mContext = mContext;
        Parse.initialize(mContext);
        parseHelperRepository = new ParseHelperRepositoryImpl(mContext);
    }

    @Override
    public void createUser(String username, String email,String password, String usertype) {

        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(username);
        parseUser.setEmail(email);
        parseUser.setPassword(password);

        if (usertype == null) {
            Toast.makeText(mContext, "Please select any user", Toast.LENGTH_SHORT).show();
        }else {

            parseUser.put("usertype", usertype);
            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Toast.makeText(mContext, "user created successfully", Toast.LENGTH_SHORT).show();

                        // TODO: 3/1/2018 locally taken user may need to change later
                        ParseUser user = ParseUser.getCurrentUser();

                        Log.i(TAG, "done: " + user);
                        parseHelperRepository.createUserDetails(user);
                        parseHelperRepository.createTDEE(user);

                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);

                    }else {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }



                }

            });

        }


    }

    @Override
    public void loginUser(String username, String password) {

        ParseUser
                .logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (e == null){

                            String username1 = ParseUser.getCurrentUser().getUsername();
                            checkUserType(username1);

                        }else {
                            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    // check usertype
    private void checkUserType(String username1) {

        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();

        userParseQuery.whereEqualTo("username", username1);

        userParseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {

                String usertype =parseHelperRepository.getUserType(object,e);



                if (object.getString("usertype").equals("patient")) {

                    Intent intent = new Intent(mContext, PatientHomeActivity.class);
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, "patient", Toast.LENGTH_SHORT).show();


                } else if (object.getString("usertype").equals("admin")) {
                   Intent intent = new Intent(mContext, AdminHomeActivity.class);
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, "admin", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(mContext, DoctorHomeActivity.class);
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, "doctor", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void logOut() {

    }
}
