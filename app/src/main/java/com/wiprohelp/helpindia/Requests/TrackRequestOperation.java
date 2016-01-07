package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.TrackRequestArray;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class TrackRequestOperation  extends VolleyRequest<TrackRequestArray> {

    public TrackRequestOperation(){
        super(Method.GET, "https://api.myjson.com/bins/2uv1b", TrackRequestArray.class, null, null, null);
    }
}
