package com.assignedpro.nj.steam.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by logimetrix on 29/7/16.
 */
public class InternetConnection {
    public static boolean isConnected(Context context) {
        ConnectivityManager connect = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connect.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnected())
            return true;
        else
            return false;
    }
}
