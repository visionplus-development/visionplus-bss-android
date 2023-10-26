package com.zte.iptvclient.android.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.zte.iptvclient.android.auth.configs.ConfigManager
import com.zte.iptvclient.android.auth.utils.PageType
import com.zte.iptvclient.android.auth.utils.SupportedLanguages

object VPAuth {
    private var config: ConfigManager? = null

    /**
     * init VPAuth in Application for sdk configuration
     * @param context application context.
     */
    fun init(context: Context) {
        if (config == null) {
            config = ConfigManager(context)
        }
    }

    /**
     * Update page language preference.
     * ```
     * available languages:
     * en: English
     * id: Indonesia
     * ```
     *
     * @param lang language
     */
    fun updateLanguage(lang: String) {
        val appLang = SupportedLanguages.entries.find {
            it.name == lang
        }?.name ?: "en"

        config?.saveAppLang(appLang)
    }

    /**
     * Open login page.
     *
     * @param context activity context.
     * @param startActivityResult intent activity result.
     *
     * @return token for success or error message for failed .
     *
     */
    fun login(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.LOGIN.name)
        }
        startActivityResult.launch(intent)
    }

    /**
     * Open register page
     */
    fun register(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.REGISTER.name)
        }
        startActivityResult.launch(intent)
    }

    /**
     * Open change password page
     */
    fun changePassword(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.FORGOT_PASSWORD.name)
        }
        startActivityResult.launch(intent)
    }

    /**
     * Open reset password page
     */
    fun resetPassword(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.RESET_PASSWORD.name)
        }
        startActivityResult.launch(intent)
    }

    fun openPageOtp(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.OTP.name)
        }
        startActivityResult.launch(intent)
    }

    fun openPageEmailVerification(context: Context, startActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.PAGE_TYPE, PageType.EMAIL_VERIFICATION.name)
        }
        startActivityResult.launch(intent)
    }

    /**
     * Logout Google account.
     */
    fun logoutGoogle(activity: Activity) {
        // todo: refactor GoogleSignInOptions to singleton object.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_SIGN_TOKEN)
            .requestEmail()
            .build()
        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(activity, gso)
        val account = GoogleSignIn.getLastSignedInAccount(activity)
        if (account != null) {
            googleSignInClient.signOut().addOnCompleteListener(activity) { }
        }
    }

    /**
     * Logout Facebook account.
     */
    fun logoutFacebook() {
        LoginManager.getInstance().logOut()
    }
}