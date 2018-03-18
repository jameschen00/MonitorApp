package com.example.jithin.monitorapp.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
}
