package com.wiprohelp.helpindia.events;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wiprohelp.helpindia.events.EventBusSingleton;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class DataVolleyError implements Response.ErrorListener{
    @Override
    public void onErrorResponse(VolleyError error) {
        EventBusSingleton.instance().postEvent(error);
    }
}
