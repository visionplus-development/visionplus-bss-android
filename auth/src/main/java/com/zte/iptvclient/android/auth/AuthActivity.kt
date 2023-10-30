package com.zte.iptvclient.android.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.zte.iptvclient.android.auth.presentation.pages.EmailVerificationPage
import com.zte.iptvclient.android.auth.presentation.components.Screens
import com.zte.iptvclient.android.auth.presentation.pages.ForgotPasswordPage
import com.zte.iptvclient.android.auth.presentation.pages.LoginScreen
import com.zte.iptvclient.android.auth.presentation.pages.RegisterScreen
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
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
                Surface(modifier = Modifier.fillMaxSize(), color = ColorBackround) {
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Register.route,
                    ) {
                        composable(
                            route = Screens.Register.route,
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                    animationSpec = tween(100)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                    animationSpec = tween(100)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                    animationSpec = tween(100)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                    animationSpec = tween(100)
                                )
                            }) {
                            RegisterScreen(
                                clickBack = {
                                    finish()
                                },
                                navController = navController
                            )
                        }
                        composable(
                            route = Screens.Login.route,
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                    animationSpec = tween(100)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                    animationSpec = tween(100)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                    animationSpec = tween(100)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                    animationSpec = tween(100)
                                )
                            }) {
                            LoginScreen(
                                clickGoogle = {
                                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestIdToken(BuildConfig.GOOGLE_SIGN_TOKEN)
                                        .requestEmail()
                                        .build()
                                    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)
                                    val account = GoogleSignIn.getLastSignedInAccount(context)
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
                                },
                                navController = navController
                            )
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