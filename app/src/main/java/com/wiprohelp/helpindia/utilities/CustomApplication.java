package com.wiprohelp.helpindia.utilities;

import android.app.Application;
import android.content.Context;

import com.wiprohelp.helpindia.model.DataManager;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class CustomApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }
}
