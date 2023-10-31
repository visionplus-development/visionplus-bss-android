package com.zte.iptvclient.android.auth.presentation.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.components.Screens
import com.zte.iptvclient.android.auth.presentation.components.TabForm
import com.zte.iptvclient.android.auth.presentation.components.TextDivider
import com.zte.iptvclient.android.auth.presentation.components.TextFieldEmail
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPassword
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPhoneNumber
import com.zte.iptvclient.android.auth.presentation.components.TextSwitchAuth
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundForm
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    clickGoogle: () -> Unit,
    callbackManager: CallbackManager,
    onLoginResult: (loginResult: LoginResult?) -> Unit,
    clickBack: () -> Unit,
    navController: NavController
) {

    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passwordPhoneNumber by remember { mutableStateOf("") }
    var passwordEmail by remember { mutableStateOf("") }
    var positionTab by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    val isAuthenticationValid = (phoneNumber.isNotEmpty() || email.isNotEmpty()) &&
            (passwordPhoneNumber.isNotEmpty() || passwordEmail.isNotEmpty())

    BackHandler {
        clickBack()
    }

    Scaffold(
        containerColor = ColorBackround,
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.label_login),
                onBackClick = {
                    clickBack()
                }
            )
        },
        bottomBar = {
            ButtonMain(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .background(ColorBackround),
                text = stringResource(
                    id = R.string.label_login
                ),
                isEnabled = isAuthenticationValid,
                onButtonClick = {
                    if (positionTab == 0) {
                        Toast.makeText(context, "$phoneNumber,$passwordPhoneNumber", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "$email,$passwordEmail", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                TextSwitchAuth(
                    modifier = Modifier.padding(all = 16.dp),
                    questionText = stringResource(id = R.string.label_created_account) + " ",
                    actionText = stringResource(id = R.string.label_register),
                    onTextClick = {
                        navController.navigate(Screens.Register.route)
                    }
                )
                TabForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    onTabClick = { value ->
                        positionTab = value
                        phoneNumber = ""
                        email = ""
                        passwordPhoneNumber = ""
                        passwordEmail = ""
                    },
                    tabPhone = {
                        PhoneLogin(
                            onPhoneNumberResult = { value ->
                                phoneNumber = value
                            },
                            onPasswordResult = { value ->
                                passwordPhoneNumber = value
                            },
                            navController = navController
                        )
                    },
                    tabEmail = {
                        EmailLogin(
                            onEmailResult = { value ->
                                email = value
                            },
                            onPasswordResult = { value ->
                                passwordEmail = value
                            },
                            navController = navController
                        )
                    })
                TextDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    text = stringResource(id = R.string.label_divider)
                )
                GridSocialLoginScreen(clickGoogle, callbackManager, onLoginResult)
            }
        }
    )
}


@Composable
internal fun PhoneLogin(
    onPhoneNumberResult: (String) -> Unit,
    onPasswordResult: (String) -> Unit,
    navController: NavController
) {

    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorBackgroundForm)
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {

        TextFieldPhoneNumber(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            label = stringResource(id = R.string.label_enter_phone_number),
            isEnabled = true,
            inputWrapper = InputWrapper(phoneNumber, null),
            onPhoneNumberChange = { value ->
                phoneNumber = value
                onPhoneNumberResult(phoneNumber)
            }
        )

        TextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            label = stringResource(id = R.string.label_enter_password),
            isEnabled = phoneNumber.isNotEmpty(),
            inputWrapper = InputWrapper(password, null),
            placeHolder = "e.g placeholder",
            onPasswordChange = { value ->
                password = if (phoneNumber.isEmpty()) {
                    ""
                } else {
                    value
                }
                onPasswordResult(password)
            }
        )

        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = AnnotatedString(stringResource(id = R.string.label_forgot_password)),
            style = TextStyle(
                color = ColorTextSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.End
            ),
            onClick = {
                navController.navigate(Screens.Forgot.route)
            }
        )
    }
}

@Composable
internal fun EmailLogin(
    onEmailResult: (String) -> Unit,
    onPasswordResult: (String) -> Unit,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorBackgroundForm)
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {

        TextFieldEmail(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            label = stringResource(id = R.string.label_enter_email),
            inputWrapper = InputWrapper(email, null),
            onValueChange = { value ->
                email = value
                onEmailResult(email)
            })

        TextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            label = stringResource(id = R.string.label_enter_password),
            isEnabled = email.isNotEmpty(),
            inputWrapper = InputWrapper(password, null),
            placeHolder = "e.g placeholder",
            onPasswordChange = { value ->
                password = if (email.isEmpty()) {
                    ""
                } else {
                    value
                }
                onPasswordResult(password)
            }
        )

        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = AnnotatedString(stringResource(id = R.string.label_forgot_password)),
            style = TextStyle(
                color = ColorTextSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.End
            ),
            onClick = {
                navController.navigate(Screens.Forgot.route)
            }
        )
    }
}


@Composable
internal fun GridSocialLoginScreen(
    clickGoogle: () -> Unit,
    callbackManager: CallbackManager,
    onLoginResult: (loginResult: LoginResult?) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            com.zte.iptvclient.android.auth.presentation.components.IconButton(
                painter = painterResource(
                    id = R.drawable.ic_gmail
                ),
                text = stringResource(id = R.string.label_google),
                modifier = Modifier.weight(1f),
                onButtonClick = {
                    clickGoogle()
                })
            com.zte.iptvclient.android.auth.presentation.components.IconButton(
                painter = painterResource(
                    id = R.drawable.ic_facebook
                ),
                text = stringResource(id = R.string.label_facebook),
                modifier = Modifier.weight(1f),
                onButtonClick = {
                    val loginButton = LoginButton(context)
                    loginButton.setPermissions(listOf("email", "public_profile"))
                    loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            onLoginResult(loginResult)
                        }

                        override fun onCancel() {
                        }

                        override fun onError(error: FacebookException) {
                            onLoginResult(null)
                        }
                    })

                    loginButton.performClick()
                })
        }
    }
}