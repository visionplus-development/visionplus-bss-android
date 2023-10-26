package com.zte.iptvclient.android.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.zte.iptvclient.android.auth.presentation.pages.EmailVerificationScreen
import com.zte.iptvclient.android.auth.presentation.pages.ForgotPasswordPage
import com.zte.iptvclient.android.auth.presentation.pages.LoginScreen
import com.zte.iptvclient.android.auth.presentation.pages.OtpScreen
import com.zte.iptvclient.android.auth.presentation.pages.RegisterScreen
import com.zte.iptvclient.android.auth.presentation.pages.ResetPasswordScreen
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.PageType
import com.zte.iptvclient.android.auth.utils.Result

internal class AuthActivity : ComponentActivity() {

    companion object {
        const val PAGE_TYPE = "page_type"
    }

    private val callbackManager = CallbackManager.Factory.create()
    private val signInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val intent = Intent().apply {
                    putExtra("token", account.result.idToken)
                }
                setResult(Result.SUCCESS, intent)
                finish()
            } else {
                val intent = Intent().apply {
                    putExtra("failed", "google login failed")
                }
                setResult(Result.FAILED, intent)
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        }
        val pageType = intent.getStringExtra(PAGE_TYPE)
        setContent {
            VisionplusbssandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    when (pageType) {
                        PageType.LOGIN.name -> {
                            LoginScreen(
                                clickGoogle = {
                                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestIdToken(BuildConfig.GOOGLE_SIGN_TOKEN)
                                        .requestEmail()
                                        .build()
                                    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
                                    val account = GoogleSignIn.getLastSignedInAccount(this)
                                    if (account == null) {
                                        val signInIntent = googleSignInClient.signInIntent
                                        signInLauncher.launch(signInIntent)
                                    }
                                },
                                callbackManager = callbackManager,
                                onLoginResult = { loginResult ->
                                    if (loginResult != null) {
                                        val intent = Intent().apply {
                                            putExtra("token", loginResult.accessToken.token)
                                        }
                                        setResult(Result.SUCCESS, intent)
                                        finish()
                                    } else {
                                        val intent = Intent().apply {
                                            putExtra("failed", "facebook login failed")
                                        }
                                        setResult(Result.FAILED, intent)
                                    }
                                },
                                clickBack = {
                                    finish()
                                }
                            )
                        }

                        PageType.REGISTER.name -> {
                            RegisterScreen()
                        }

                        PageType.FORGOT_PASSWORD.name -> {
                            ForgotPasswordPage()
                        }

                        PageType.RESET_PASSWORD.name -> {
                            ResetPasswordScreen()
                        }

                        PageType.OTP.name -> {
                            OtpScreen()
                        }

                        PageType.EMAIL_VERIFICATION.name -> {
                            EmailVerificationScreen()
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Result.CANCELLED)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}