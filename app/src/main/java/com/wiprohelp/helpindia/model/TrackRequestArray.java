package com.wiprohelp.helpindia.model;

import java.util.ArrayList;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class TrackRequestArray extends ServerBaseResponse {

    private ArrayList<TrackRequetElement> data;

    public ArrayList<TrackRequetElement> getData() {
        return data;
    }

    public void setData(ArrayList<TrackRequetElement> data) {
        this.data = data;
    }
}
