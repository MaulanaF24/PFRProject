package com.catatankeuangan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jmarkstar on 25/05/2017.
 */
public class SharedPreferencesUtil {

    private static final String PREF_NAME = "GPS_TRACKER_PREF";
    private static final String PREF_JOB_FLAG = "job_flag";
    private static final String PREF_USERNAME = "username";

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesUtil(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setFlagJob(Boolean isworking){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(PREF_JOB_FLAG, isworking);
        editor.apply();
    }

    public boolean getFlagJob(){
        return mSharedPreferences.getBoolean(PREF_JOB_FLAG, false);
    }

    public void setUsername(String username){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREF_USERNAME, username);
        editor.apply();
    }

    public String getUsername(){
        return mSharedPreferences.getString(PREF_USERNAME, "");
    }
}
