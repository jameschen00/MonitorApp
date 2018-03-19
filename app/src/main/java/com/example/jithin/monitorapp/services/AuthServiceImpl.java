package com.example.jithin.monitorapp.services;

import android.content.Context;
import android.widget.Toast;

import com.example.jithin.monitorapp.repository.AuthRepository;
import com.example.jithin.monitorapp.repository.AuthRepositoryImpl;
import com.example.jithin.monitorapp.repository.ParseHelperRepositoryImpl;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by jithin on 18/3/18.
 */

public class AuthServiceImpl implements AuthServices {


    private AuthRepositoryImpl authRepository;
    private Context mContext;

    public AuthServiceImpl( Context mContext) {
        this.mContext = mContext;
        authRepository = new AuthRepositoryImpl(mContext);
    }


    @Override
    public void createUser(String username, String email, String password, String usertype) {
        authRepository.createUser(username,email,password,usertype);
    }

    @Override
    public void loginUser(String username, String password) {

        authRepository.loginUser(username, password);

    }

    @Override
    public void logOut() {

    }
}
