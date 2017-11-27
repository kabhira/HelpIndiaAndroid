package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.TrackRequestArray;
import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class TrackRequestOperation extends VolleyRequest<TrackRequestArray> {

    public TrackRequestOperation(String mobile){
        super(Method.GET, Constants.TRACK_REQUEST_URL+"?mobileNumber="+mobile, TrackRequestArray.class, null, null, null);
    }
}
