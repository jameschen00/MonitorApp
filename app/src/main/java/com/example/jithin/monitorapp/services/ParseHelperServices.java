package com.example.jithin.monitorapp.services;

import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by jithin on 18/3/18.
 */

public interface ParseHelperServices {

    String getUserType(ParseUser object, ParseException e);
    void updatingUserProfileDetails(String objid, final String age, final String weight, final String height, final String totalchol, final String hdlChol, final String symbp, final String havesmoker, final String havetreatment);
   /* void createUserDetails(ParseUser user);
    void createTDEE(ParseUser user);*/


}
