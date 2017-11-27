package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.LiveRequestArray;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class LiveRequestOperation  extends VolleyRequest<LiveRequestArray> {

    public LiveRequestOperation(String mobile){
        super(Method.GET, Constants.ALL_REQUEST_TO_VOLUNTEER_URL+"?mobileNumber="+mobile, LiveRequestArray.class, null, null, null);
    }
}
