package com.wiprohelp.helpindia.model;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.events.EventBusSingleton;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.Requests.RequestCategoryOperation;
import com.wiprohelp.helpindia.utilities.CustomApplication;

import java.util.HashMap;

/**
 * Created by AB335009 on 12/22/2015.
 */
public class DataManager {

    private static  DataManager instance = new DataManager();
    private HashMap<String, RequestCategoryElement> requestCategoryList;

    private DataManager(){
        requestCategoryList = new HashMap<String, RequestCategoryElement>();
        EventBusSingleton.instance().register(this);

        // need to load this on start of App.
        RequestCategoryOperation requestCategoryOperation = new RequestCategoryOperation();
        VolleyNetwork.getInstance(CustomApplication.getmContext()).addToRequestQueue(requestCategoryOperation);
    }

    public static DataManager instance()
    {
        return instance;
    }

    public synchronized HashMap<String, RequestCategoryElement> getRequestCategoryList(){
        return requestCategoryList;
    }

    @Subscribe
    public void requestCatagoryData(RequestCategoryArray data){
        requestCategoryList.clear();
        for (RequestCategoryElement element:data.getData()) {
            requestCategoryList.put(element.getCatId(), element);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBusSingleton.instance().unregister(this);
    }
}
