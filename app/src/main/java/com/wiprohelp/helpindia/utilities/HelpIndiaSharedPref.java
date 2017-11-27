package com.wiprohelp.helpindia.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AB335009 on 1/11/2016.
 */
public class HelpIndiaSharedPref {

    private SharedPreferences mSharedPreferences;

    public HelpIndiaSharedPref(Context context){
        mSharedPreferences = context.getSharedPreferences("HelpIndia", Context.MODE_PRIVATE);
    }

    public void setVictimMobile(String mobile){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("VictimMobile", mobile+"");
        editor.commit();
    }

    public String getVictimMobile(){
        return  mSharedPreferences.getString("VictimMobile", "");
    }

    public void setVolunteerMobile(String mobile){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("VolunteerMobile", mobile+"");
        editor.commit();
    }

    public String getVolunteerMobile(){
        return  mSharedPreferences.getString("VolunteerMobile", "");
    }

    public void setGCMRegistrationStatus(boolean flag){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("GCMRegistrationStatus", flag);
        editor.commit();
    }

    public boolean getGCMRegistrationStatus(){
        return  mSharedPreferences.getBoolean("GCMRegistrationStatus", false);
    }

    public void setGCMRegistrationToken(String token){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("GCMRegistrationToken", token);
        editor.commit();
    }

    public String getGCMRegistrationToken(){
        return  mSharedPreferences.getString("GCMRegistrationToken", "");
    }
}
