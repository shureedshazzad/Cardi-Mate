package com.example.cardimate.Class;

public class Cardmodel {
    private String date;
    private String diastolicPressure;
    private String heartRate;
    private String systolicPressure;
    private String time;
    private String comment;

    // Constructor
    public Cardmodel()
    {

    }
    public Cardmodel(String date, String diastolicPressure, String heartRate, String systolicPressure, String time, String comment) {
        this.date = date;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.systolicPressure = systolicPressure;
        this.time = time;
        this.comment=comment;
    }

    // Getters and Setters

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(String systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
