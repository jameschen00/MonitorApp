package com.example.jithin.monitorapp.services;

/**
 * Created by jithin on 18/3/18.
 */

public interface AuthServices {

    void createUser(String username, String email,String password, String usertype);
    void loginUser(String username, String password);
    void logOut();
}
