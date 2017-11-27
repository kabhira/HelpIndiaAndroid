package com.wiprohelp.helpindia.model;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class TrackRequetElement {

    private String requestId;
    private String categoryDesc;
    private String categoryId;
    private String categoryImage;
    private String requestStatus;
    private String volunteerName;
    private String requestAcceptedDate;
    private String volunteerExpectedTime;
    private String volunteerSpeciality;
    private String volenteerMobileNumber;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getRequestAcceptedDate() {
        return requestAcceptedDate;
    }

    public void setRequestAcceptedDate(String requestAcceptedDate) {
        this.requestAcceptedDate = requestAcceptedDate;
    }

    public String getVolunteerExpectedTime() {
        return volunteerExpectedTime;
    }

    public void setVolunteerExpectedTime(String volunteerExpectedTime) {
        this.volunteerExpectedTime = volunteerExpectedTime;
    }

    public String getVolunteerSpeciality() {
        return volunteerSpeciality;
    }

    public void setVolunteerSpeciality(String volunteerSpeciality) {
        this.volunteerSpeciality = volunteerSpeciality;
    }

    public String getVolenteerMobileNumber() {
        return volenteerMobileNumber;
    }

    public void setVolenteerMobileNumber(String volenteerMobileNumber) {
        this.volenteerMobileNumber = volenteerMobileNumber;
    }
}
