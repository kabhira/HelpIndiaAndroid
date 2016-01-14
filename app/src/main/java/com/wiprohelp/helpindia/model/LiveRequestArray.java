package com.wiprohelp.helpindia.model;

import java.util.ArrayList;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class LiveRequestArray extends ServerBaseResponse {

    private ArrayList<LiveRequestElement> data;

    public ArrayList<LiveRequestElement> getData() {
        return data;
    }

    public void setData(ArrayList<LiveRequestElement> data) {
        this.data = data;
    }
}
