package com.example.cardimate.Class;

import com.example.cardimate.R;

import java.io.Serializable;

/**
 * This is the java class which is used to fetch data from firebase
 */
public class Cardmodel implements Serializable {
    private String date;
    private String diastolicPressure;
    private String heartRate;
    private String systolicPressure;
    private String time;
    private String comment;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;


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

    /**
     * This function adds "mmhg" after the inserted systolic pressure
     * @return systolic pressure with values with mmhg added 
     */
    public String getFormattedSys(){
        return systolicPressure+" mmHg";
    }

    /**
     * This function adds "mmhg" after the inserted diastolic pressure
     * @return diastolic pressure with values with "mmhg" added
     */
    public String getFormattedDys(){
        return diastolicPressure+" mmHg";
    }

    /**
     * This function adds "bpm" after the inserted heart-rate
     * @return diastolic pressure with values with "bpm" added
     */
    public String getFormattedHeartRate(){
        return heartRate+" bpm";
    }

    /**
     * This function return color based on the inserted comment
     * @return red if comment="high|low" else return yellow
     */
    public int getBackColor(){
        if(comment.equalsIgnoreCase("high") || comment.equalsIgnoreCase("low")){
            return R.color.red;
        }
        else{
            return R.color.orange;
        }
    }

}
