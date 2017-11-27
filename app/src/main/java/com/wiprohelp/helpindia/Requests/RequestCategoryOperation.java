package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.model.RequestCategoryArray;
import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class RequestCategoryOperation extends VolleyRequest<RequestCategoryArray> {

    public RequestCategoryOperation(){
        super(Method.GET, Constants.ALL_GATEGORY_URL, RequestCategoryArray.class, null, null, null);
    }
}

