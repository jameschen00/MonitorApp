package com.example.jithin.monitorapp.repository;

/**
 * Created by jithin on 18/3/18.
 */

public interface AuthRepository {

    void createUser(String username, String email,String password, String usertype);
    void loginUser(String username, String password);
    void logOut();
}
