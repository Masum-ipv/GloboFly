package com.learning.globofly.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.Toast;

public class Helper {

    public static final String BASE_URL = "http://10.0.3.2:9000/";

    public static boolean isNetworkAvailable(Context context) {
        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // If the network is available, connected
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;
        else {
            Toast.makeText(context, "Network connection error!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean notEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        return false;
    }
}
