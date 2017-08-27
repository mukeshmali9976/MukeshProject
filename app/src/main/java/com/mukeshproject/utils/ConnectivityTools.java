package com.mukeshproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectivityTools {


    /**
     * Is network available boolean.
     *
     * @param activity the activity
     * @return the boolean
     */
    public static boolean isNetworkAvailable(Context activity) {

        try {

            if (activity != null) {

                Context context = activity.getApplicationContext();
                ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    //noinspection deprecation
                    NetworkInfo[] info = connectivity.getAllNetworkInfo();
                    if (info != null) {
                        for (NetworkInfo anInfo : info) {
                            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
