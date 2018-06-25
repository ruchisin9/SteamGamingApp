package com.assignedpro.nj.steam.constants;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by logimetrix on 30/7/16.
 */
public class URLConstants {
    public static String domain="http://store.steampowered.com/api/appdetails/?appids=";


    public static String getDeviceId(Context context){
        String device_id=((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (device_id!=null)
            return device_id;
        else
           return Build.SERIAL;
    }

    public static String getImeiNo(Context con){
        String str=null;
        try {
            String deviceId = ((TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            if (deviceId != null) {
                str=deviceId;
            } else {
                String android_id = Settings.Secure.getString(con.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                if(android_id!=null)
                    str=android_id;
            }
        }catch(Exception e){
            e.printStackTrace();
            str="2365489563";
        }

        return str;
    }
}
