package com.zte.iptvclient.android.auth.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isConnect(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}