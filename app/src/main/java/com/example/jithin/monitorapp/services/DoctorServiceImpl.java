package com.example.jithin.monitorapp.services;

import android.content.Context;

import com.example.jithin.monitorapp.model.Patientdetails;
import com.example.jithin.monitorapp.repository.DoctorRepositoryImpl;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Master on 3/19/2018.
 */

public class DoctorServiceImpl implements DoctorService {

    private Context mContext;
    private DoctorRepositoryImpl doctorRepository;

    public DoctorServiceImpl(Context mContext) {
        this.mContext = mContext;
        doctorRepository = new DoctorRepositoryImpl(mContext);
    }

    @Override
    public Patientdetails getPatForRec(ParseUser parseUser) {
        return doctorRepository.getPatForRec(parseUser);
    }
}
