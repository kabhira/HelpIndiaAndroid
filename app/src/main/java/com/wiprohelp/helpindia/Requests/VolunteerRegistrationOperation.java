package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.ServerResponseElement;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 1/11/2016.
 */
public class VolunteerRegistrationOperation extends VolleyRequest<ServerResponseElement> {

    public VolunteerRegistrationOperation(String request){
        super(Method.GET, Constants.VOLUNTEER_REGISTRATION_URL+"?newUser="+request, ServerResponseElement.class, null, null, null);
    }
}
