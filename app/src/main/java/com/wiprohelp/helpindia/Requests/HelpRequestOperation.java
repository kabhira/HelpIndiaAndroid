package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.ServerResponseElement;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 1/10/2016.
 */
public class HelpRequestOperation extends VolleyRequest<ServerResponseElement> {

    public HelpRequestOperation(String request){
        super(Method.GET, Constants.NEW_HELP_REQUEST_URL+"?newRequest="+request, ServerResponseElement.class, null, null, null);
    }
}
