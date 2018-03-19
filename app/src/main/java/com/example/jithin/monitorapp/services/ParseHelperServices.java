package com.example.jithin.monitorapp.services;

import com.example.jithin.monitorapp.model.Patient;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by jithin on 18/3/18.
 */

public interface ParseHelperServices {

    String getUserType(ParseUser object, ParseException e);

    Patient getPatientDetails(ParseObject object, ParseException e);


   /* void createUserDetails(ParseUser user);
    void createTDEE(ParseUser user);*/


}
