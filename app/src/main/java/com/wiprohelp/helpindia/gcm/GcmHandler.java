package com.wiprohelp.helpindia.gcm;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;

/**
 * Created by akhare on 9/15/15.
 */
public class GcmHandler {

    public static final String TAG = "GcmHandler";

    public void gcmRegistration(Activity activity){
        if (checkPlayServices(activity)) {
            // Start IntentService to register this application with GCM.
            HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(activity);
            boolean flag = helpIndiaSharedPref.getGCMRegistrationStatus();
            if(!flag){
                Intent intent = new Intent(activity, RegistrationIntentService.class);
                activity.startService(intent);
                helpIndiaSharedPref.setGCMRegistrationStatus(true);
            }
        }
    }

    private boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
            }
            return false;
        }
        return true;
    }
}
