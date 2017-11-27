package com.wiprohelp.helpindia.model;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.events.EventBusSingleton;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.Requests.RequestCategoryOperation;
import com.wiprohelp.helpindia.utilities.CustomApplication;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AB335009 on 12/22/2015.
 */
public class DataManager {

    private static  DataManager instance = new DataManager();
    private ArrayList<RequestCategoryElement> requestCategoryList;
    private HashMap<String, RequestCategoryElement> requestCategoryMap;

    private DataManager(){
        requestCategoryList = new ArrayList<RequestCategoryElement>();
        requestCategoryMap = new HashMap<String, RequestCategoryElement>();
        EventBusSingleton.instance().register(this);
    }

    public static DataManager instance()
    {
        return instance;
    }

    public synchronized HashMap<String, RequestCategoryElement> getRequestCategoryMap(){
        return requestCategoryMap;
    }

    public synchronized ArrayList<RequestCategoryElement> getRequestCategoryArray(){
        return requestCategoryList;
    }

    @Subscribe
    public void requestCatagoryData(RequestCategoryArray data){
        requestCategoryList = data.getData();
        requestCategoryMap.clear();
        for (RequestCategoryElement element:data.getData()) {
            requestCategoryMap.put(element.getCategoryId(), element);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBusSingleton.instance().unregister(this);
    }
}
