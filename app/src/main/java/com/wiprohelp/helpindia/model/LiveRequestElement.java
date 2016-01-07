package com.wiprohelp.helpindia.model;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class LiveRequestElement {
    private String catagoryId;
    private String seekerName;
    private String seekerMobile;
    private String latitude;
    private String longitude;
    private String address;
    private String expectedTime;

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getSeekerName() {
        return seekerName;
    }

    public void setSeekerName(String seekerName) {
        this.seekerName = seekerName;
    }

    public String getSeekerMobile() {
        return seekerMobile;
    }

    public void setSeekerMobile(String seekerMobile) {
        this.seekerMobile = seekerMobile;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }
}
