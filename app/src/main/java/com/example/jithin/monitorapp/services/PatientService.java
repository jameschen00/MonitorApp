package com.example.jithin.monitorapp.services;

import com.parse.ParseUser;

/**
 * Created by Master on 3/19/2018.
 */

public interface PatientService {

    void updatingUserProfileDetails(String objid, final String age, final String weight, final String height, final String totalchol, final String hdlChol, final String symbp, final String havesmoker, final String havetreatment);
    void uploadSurveyDataSecond(String objid,String headPoint,String nauseaPoint,String fatiguePoint,String faintingPoint,String dizzPoint,String pressurePoint,String spinnerData);
    void calculateTDEE(ParseUser user);
}
