package com.wiprohelp.helpindia.events;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wiprohelp.helpindia.events.EventBusSingleton;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class DataVolleyError implements Response.ErrorListener{

    private static String TAG = "DataVolleyError";

    @Override
    public void onErrorResponse(VolleyError error) {
        //Log.e(TAG, error.toString()+"");
        EventBusSingleton.instance().postEvent(error);
    }
}
