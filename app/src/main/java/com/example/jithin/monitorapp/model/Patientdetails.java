package com.example.jithin.monitorapp.model;

/**
 * Created by Master on 1/22/2018.
 */

public class Patientdetails {

    private String username;
    private String status;


    public Patientdetails() {
    }

    public Patientdetails(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patientdetails{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
