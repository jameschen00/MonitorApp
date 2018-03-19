package com.example.jithin.monitorapp.repository;

import com.example.jithin.monitorapp.model.Patient;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by jithin on 18/3/18.
 */

public interface ParseHelperRepository {

    String getUserType(ParseUser object, ParseException e);
    void createUserDetails(ParseUser user);
    void createTDEE(ParseUser user);
    void calculateRiskFactor();
    /*void calculateTotalEnergyExp(Patient patient);*/
    void updateRiskScore(int riskscore, String objid);




}
