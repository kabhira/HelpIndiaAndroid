package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.model.RequestCategoryArray;
import com.wiprohelp.helpindia.Network.VolleyRequest;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class RequestCategoryOperation extends VolleyRequest<RequestCategoryArray> {

    public RequestCategoryOperation(){
        super(Method.GET, "https://api.myjson.com/bins/447d7", RequestCategoryArray.class, null, null, null);
    }
}

