package com.wiprohelp.helpindia.model;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class LiveRequestElement {

    private String requestId;
    private String categoryId;
    private String categoryDesc;
    private String categoryImage;
    private String victimName;
    private String victimPhoneNumber;
    private String victimAddress;
    private String victimLatitude;
    private String victimLongitude;
    private String victimExpectedTime;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public String getVictimPhoneNumber() {
        return victimPhoneNumber;
    }

    public void setVictimPhoneNumber(String victimPhoneNumber) {
        this.victimPhoneNumber = victimPhoneNumber;
    }

    public String getVictimAddress() {
        return victimAddress;
    }

    public void setVictimAddress(String victimAddress) {
        this.victimAddress = victimAddress;
    }

    public String getVictimLatitude() {
        return victimLatitude;
    }

    public void setVictimLatitude(String victimLatitude) {
        this.victimLatitude = victimLatitude;
    }

    public String getVictimLongitude() {
        return victimLongitude;
    }

    public void setVictimLongitude(String victimLongitude) {
        this.victimLongitude = victimLongitude;
    }

    public String getVictimExpectedTime() {
        return victimExpectedTime;
    }

    public void setVictimExpectedTime(String victimExpectedTime) {
        this.victimExpectedTime = victimExpectedTime;
    }
}
