package com.example.jithin.monitorapp.services;

import com.example.jithin.monitorapp.model.Patient;
import com.example.jithin.monitorapp.model.Patientdetails;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Master on 3/19/2018.
 */

public interface DoctorService {
    Patientdetails getPatForRec(ParseUser parseUser);
    Patient getDetails(ParseObject object);
}
