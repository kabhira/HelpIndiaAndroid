package com.wiprohelp.helpindia.model;

/**
 * Created by AB335009 on 1/13/2016.
 */
public class VolunteerProfileResponse extends ServerBaseResponse {

    private VolunteerProfileElement data;

    public VolunteerProfileElement getData() {
        return data;
    }

    public void setData(VolunteerProfileElement data) {
        this.data = data;
    }
}
