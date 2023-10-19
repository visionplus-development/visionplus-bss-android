package com.zte.iptvclient.android.auth.configs

import android.content.Context

internal class ConfigManager(context: Context) {

    companion object {
        private const val APP_LANG = "app_lang"
        private const val SDK_KEY = "sdk_key"
    }

    private val packageName = context.packageName
    private val packageManager = context.packageManager


    private val prefs by lazy {
        context.getSharedPreferences("AuthConfig", Context.MODE_PRIVATE)
    }

    val appPackage: String
        get() = try {
            packageManager.getPackageInfo(packageName, 0).packageName
        } catch (e: Exception) {
            ""
        }

    val appVersion: String
        get() = try {
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: Exception) {
            ""
        }

    val appBuildNumber: Int
        get() = try {
            packageManager.getPackageInfo(packageName, 0).versionCode
        } catch (e: Exception) {
            0
        }

    val appLang: String
        get() = prefs.getString(APP_LANG, "").orEmpty()

    fun saveAppLang(appLang: String) {
        prefs.edit().putString(APP_LANG, appLang).apply()
    }

    val sdkKey: String
        get() = prefs.getString(SDK_KEY, "").orEmpty()

    fun saveSdkKey(sdkKey: String) {
        prefs.edit().putString(SDK_KEY, sdkKey).apply()
    }
}