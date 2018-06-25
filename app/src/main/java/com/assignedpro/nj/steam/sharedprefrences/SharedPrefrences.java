package com.assignedpro.nj.steam.sharedprefrences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by logimetrix on 8/7/16.
 */
public class SharedPrefrences {

    private static SharedPrefrences gardenSharedPrfs;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String sharedprfstag="sag";
    private static final String prf_login_status="loginstatus";
    private static final String prf_loginid="loginid";
    private static final String prf_imei_no="imei";
    private static final String prf_user_detail="userdetail";
    private static final String offline_timestamp="2016-09-22 07:00:00";



    private SharedPrefrences(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(sharedprfstag, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public static SharedPrefrences getsharedprefInstance(Context con) {
        if (gardenSharedPrfs == null)
            gardenSharedPrfs = new SharedPrefrences(con);
        return gardenSharedPrfs;
    }

    public SharedPreferences getAppSharedPrefs() {
        return appSharedPrefs;
    }

    public void setAppSharedPrefs(SharedPreferences appSharedPrefs) {
        this.appSharedPrefs = appSharedPrefs;
    }

    public SharedPreferences.Editor getPrefsEditor() {
        return prefsEditor;
    }

    public void Commit() {
        prefsEditor.commit();
    }

    public void setBaseURL(String url) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("base_url", url);
        prefsEditor.commit();
    }

    public String getBaseURL() {
        return appSharedPrefs.getString("base_url", "http://pmms.logimetrix.me/api");
    }
    public void setAppId(String appid) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("appid", appid);
        prefsEditor.commit();
    }

    public String getAppId() {
        return appSharedPrefs.getString("appid", "");
    }


    public void setIMEINo(String imeiNo) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(prf_imei_no, imeiNo);
        prefsEditor.commit();
    }
}
