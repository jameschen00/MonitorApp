package com.example.jithin.monitorapp.model;

/**
 * Created by Master on 3/1/2018.
 */

public class Patient {

    private int patient_age;
    private int patient_weight;
    private int patient_height;
    private int patient_total_chol;
    private int patient_hdl_chol;
    private String patient_smoker;
    private String patient_have_treatment;
    private String patient_objid;
    private int patient_symbp;
    private int riskScore;


    public Patient() {
    }

    public Patient(int patient_age, int patient_weight, int patient_height, int patient_total_chol, int patient_hdl_chol, String patient_smoker, String patient_have_treatment, String patient_objid, int patient_symbp, int riskScore) {
        this.patient_age = patient_age;
        this.patient_weight = patient_weight;
        this.patient_height = patient_height;
        this.patient_total_chol = patient_total_chol;
        this.patient_hdl_chol = patient_hdl_chol;
        this.patient_smoker = patient_smoker;
        this.patient_have_treatment = patient_have_treatment;
        this.patient_objid = patient_objid;
        this.patient_symbp = patient_symbp;
        this.riskScore = riskScore;
    }

    public Patient(int patient_age, int patient_weight, int patient_height, int patient_total_chol, int patient_hdl_chol, String patient_smoker, String patient_have_treatment, int patient_symbp, int riskScore) {
        this.patient_age = patient_age;
        this.patient_weight = patient_weight;
        this.patient_height = patient_height;
        this.patient_total_chol = patient_total_chol;
        this.patient_hdl_chol = patient_hdl_chol;
        this.patient_smoker = patient_smoker;
        this.patient_have_treatment = patient_have_treatment;
        this.patient_symbp = patient_symbp;
        this.riskScore = riskScore;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public int getPatient_weight() {
        return patient_weight;
    }

    public void setPatient_weight(int patient_weight) {
        this.patient_weight = patient_weight;
    }

    public int getpatient_height() {
        return patient_height;
    }

    public void setpatient_height(int patient_height) {
        this.patient_height = patient_height;
    }

    public int getPatient_total_chol() {
        return patient_total_chol;
    }

    public void setPatient_total_chol(int patient_total_chol) {
        this.patient_total_chol = patient_total_chol;
    }

    public int getPatient_hdl_chol() {
        return patient_hdl_chol;
    }

    public void setPatient_hdl_chol(int patient_hdl_chol) {
        this.patient_hdl_chol = patient_hdl_chol;
    }

    public String getPatient_smoker() {
        return patient_smoker;
    }

    public void setPatient_smoker(String patient_smoker) {
        this.patient_smoker = patient_smoker;
    }

    public String getPatient_have_treatment() {
        return patient_have_treatment;
    }

    public void setPatient_have_treatment(String patient_have_treatment) {
        this.patient_have_treatment = patient_have_treatment;
    }

    public String getPatient_objid() {
        return patient_objid;
    }

    public void setPatient_objid(String patient_objid) {
        this.patient_objid = patient_objid;
    }

    public int getPatient_symbp() {
        return patient_symbp;
    }

    public void setPatient_symbp(int patient_symbp) {
        this.patient_symbp = patient_symbp;
    }

    public int getPatient_height() {
        return patient_height;
    }

    public void setPatient_height(int patient_height) {
        this.patient_height = patient_height;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_age=" + patient_age +
                ", patient_weight=" + patient_weight +
                ", patient_height=" + patient_height +
                ", patient_total_chol=" + patient_total_chol +
                ", patient_hdl_chol=" + patient_hdl_chol +
                ", patient_smoker='" + patient_smoker + '\'' +
                ", patient_have_treatment='" + patient_have_treatment + '\'' +
                ", patient_objid='" + patient_objid + '\'' +
                ", patient_symbp=" + patient_symbp +
                ", riskScore=" + riskScore +
                '}';
    }
}
