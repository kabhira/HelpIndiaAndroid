package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.ServerResponseElement;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 1/12/2016.
 */
public class HelpRequestUpdateOperation extends VolleyRequest<ServerResponseElement> {

    public HelpRequestUpdateOperation(String request){
        super(Method.GET, Constants.HELP_REQUEST_UPDATE_URL+"?requestUpdateData="+request, ServerResponseElement.class, null, null, null);
    }
}
