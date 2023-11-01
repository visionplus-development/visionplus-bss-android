package com.zte.iptvclient.android.auth.utils

import android.content.Context
import android.widget.Toast

object ViewUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}