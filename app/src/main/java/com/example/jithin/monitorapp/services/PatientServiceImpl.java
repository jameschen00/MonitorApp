package com.example.jithin.monitorapp.services;

import android.content.Context;

import com.example.jithin.monitorapp.repository.PatientRepositoryImpl;

/**
 * Created by Master on 3/19/2018.
 */

public class PatientServiceImpl implements PatientService {

    private PatientRepositoryImpl patientRepository;
    private Context mContext;

    public PatientServiceImpl(Context mContext) {
        this.mContext = mContext;
        patientRepository = new PatientRepositoryImpl(mContext);
    }

    @Override
    public void updatingUserProfileDetails(String objid, String age, String weight, String height, String totalchol, String hdlChol, String symbp, String havesmoker, String havetreatment) {
        patientRepository.updatingUserProfileDetails(objid, age,  weight,  height,  totalchol, hdlChol,  symbp,  havesmoker, havetreatment);
    }

    @Override
    public void uploadSurveyDataSecond(String objid, String headPoint, String nauseaPoint, String fatiguePoint, String faintingPoint, String dizzPoint, String pressurePoint, String spinnerData) {
        patientRepository.uploadSurveyDataSecond(objid,headPoint,
                nauseaPoint,fatiguePoint,faintingPoint,dizzPoint,pressurePoint,spinnerData);
    }

}
