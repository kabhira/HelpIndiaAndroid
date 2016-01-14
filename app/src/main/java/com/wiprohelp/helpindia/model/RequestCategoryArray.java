package com.wiprohelp.helpindia.model;

import java.util.ArrayList;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class RequestCategoryArray extends ServerBaseResponse {
    private ArrayList<RequestCategoryElement> data;

    public ArrayList<RequestCategoryElement> getData() {
        return data;
    }

    public void setData(ArrayList<RequestCategoryElement> data) {
        this.data = data;
    }
}
