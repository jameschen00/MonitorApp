package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
    public void createUser() {

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

                    /*Intent intent = new Intent(mContext, PatientHomeActivity.class);
                    mContext.startActivity(intent);*/
                    Toast.makeText(mContext, "patient", Toast.LENGTH_SHORT).show();


                } else if (object.getString("usertype").equals("admin")) {
                   /* Intent intent = new Intent(mContext, AdminHomeActivity.class);
                    mContext.startActivity(intent);*/
                    Toast.makeText(mContext, "admin", Toast.LENGTH_SHORT).show();

                } else {
                    /*Intent intent = new Intent(mContext, DoctorHomeActivity.class);
                    mContext.startActivity(intent);*/
                    Toast.makeText(mContext, "doctor", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void logOut() {

    }
}
