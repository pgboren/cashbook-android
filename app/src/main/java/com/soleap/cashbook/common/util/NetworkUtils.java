package com.soleap.cashbook.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class NetworkUtils {

    private static NetworkUtils instance;
    private Context context;

    private NetworkUtils(Context context) {
        this.context = context;
    }

    public static synchronized NetworkUtils getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkUtils(context.getApplicationContext());
        }
        return instance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

                return networkCapabilities != null &&
                        (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
            } else {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }

        return false;
    }

    public void checkInternetConnection() {
        if (isNetworkAvailable()) {
            // Internet connection is available
            Toast.makeText(context, "Internet connection is available", Toast.LENGTH_SHORT).show();
        } else {
            // No internet connection
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
