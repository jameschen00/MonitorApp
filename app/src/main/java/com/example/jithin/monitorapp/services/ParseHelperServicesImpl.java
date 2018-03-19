package com.example.jithin.monitorapp.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jithin.monitorapp.repository.ParseHelperRepository;
import com.example.jithin.monitorapp.repository.ParseHelperRepositoryImpl;
import com.example.jithin.monitorapp.repository.PatientRepositoryImpl;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by jithin on 18/3/18.
 */

public class ParseHelperServicesImpl implements ParseHelperServices{

    private ParseHelperRepositoryImpl parseHelperRepository;

    private Context mContext;

    public ParseHelperServicesImpl(Context mContext) {
        this.mContext = mContext;
        parseHelperRepository = new ParseHelperRepositoryImpl(mContext);

    }

    @Override
    public String getUserType(ParseUser object, ParseException e) {
        return parseHelperRepository.getUserType(object,e);
    }


}
