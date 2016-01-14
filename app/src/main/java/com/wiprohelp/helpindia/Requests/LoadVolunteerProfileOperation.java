package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.VolunteerProfileResponse;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 1/13/2016.
 */
public class LoadVolunteerProfileOperation extends VolleyRequest<VolunteerProfileResponse> {

    public LoadVolunteerProfileOperation(String mobile){
        super(Method.GET, Constants.LOAD_VOLUNTEER_PROFILE_URL+"?mobileNumber="+mobile, VolunteerProfileResponse.class, null, null, null);
    }
}
