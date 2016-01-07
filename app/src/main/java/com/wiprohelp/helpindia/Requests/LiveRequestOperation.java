package com.wiprohelp.helpindia.Requests;

import com.wiprohelp.helpindia.Network.VolleyRequest;
import com.wiprohelp.helpindia.model.LiveRequestArray;
import com.wiprohelp.helpindia.model.TrackRequestArray;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class LiveRequestOperation  extends VolleyRequest<LiveRequestArray> {

    public LiveRequestOperation(){
        super(Method.GET, "https://api.myjson.com/bins/33btt", LiveRequestArray.class, null, null, null);
    }
}
