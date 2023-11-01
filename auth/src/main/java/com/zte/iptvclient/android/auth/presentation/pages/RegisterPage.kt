package com.zte.iptvclient.android.auth.presentation.pages

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.components.ConfigLayoutDevice
import com.zte.iptvclient.android.auth.presentation.components.Screens
import com.zte.iptvclient.android.auth.presentation.components.TabForm
import com.zte.iptvclient.android.auth.presentation.components.TextFieldEmail
import com.zte.iptvclient.android.auth.presentation.components.TextFieldOTP
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPassword
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPhoneNumber
import com.zte.iptvclient.android.auth.presentation.components.TextSwitchAuth
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundForm
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextButtonDisable
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary

@Composable
internal fun RegisterScreen(
    clickBack: () -> Unit,
    navController: NavController
) {

    BackHandler {
        clickBack()
    }

    ConfigLayoutDevice(
        screenMobile = { RegisterContent(clickBack, navController) },
        screenTablet = { RegisterContent(clickBack, navController) })
}

@Composable
internal fun RegisterContent(
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

    Scaffold(
        containerColor = ColorBackround,
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.label_register),
                onBackClick = {
                    clickBack()
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
                    actionText = stringResource(id = R.string.label_login),
                    onTextClick = {
                        navController.navigate(Screens.Login.route)
                    }
                )
                TabForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(ColorBackgroundForm),
                    onTabClick = { value ->
                        positionTab = value
                        phoneNumber = ""
                        email = ""
                        passwordPhoneNumber = ""
                        passwordEmail = ""
                    },
                    tabPhone = {
                        PhoneRegister(
                            onPhoneNumberResult = { value ->
                                phoneNumber = value
                            },
                            onPasswordResult = { value ->
                                passwordPhoneNumber = value
                            },
                        )
                    },
                    tabEmail = {
                        EmailRegister(
                            onEmailResult = { value ->
                                email = value
                            },
                            onPasswordResult = { value ->
                                passwordEmail = value
                            },
                        )
                    })
            }
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
                    id = R.string.label_register
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
    )
}

@Composable
internal fun PhoneRegister(
    onPhoneNumberResult: (String) -> Unit,
    onPasswordResult: (String) -> Unit
) {

    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val otpValue = remember { mutableStateOf("") }
    var isOtpClicked by rememberSaveable { mutableStateOf(false) }

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Get OTP or Link Click",
                style = MaterialTheme.typography.bodyMedium,
                color = if (phoneNumber.isNotEmpty() && password.isNotEmpty()) ColorTextPrimary else ColorTextSecondary,
                textAlign = TextAlign.Start
            )
            val countdownString = buildAnnotatedString {
                append("Send after")
                withStyle(style = SpanStyle(ColorPrimary)) {
                    append(" ")
                }
            }
//                                         todo: check phone requirement. no phone requirement yet
            if (isOtpClicked) {
                Text(
                    text = countdownString,
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorTextPrimary,
                    textAlign = TextAlign.Start
                )
            } else {
                ClickableText(
                    text = AnnotatedString("Send OTP"),
                    style = TextStyle(
                        color = if (phoneNumber.isNotEmpty() && password.isNotEmpty()) ColorPrimary else ColorTextButtonDisable,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    ),
                    onClick = {
                        if (phoneNumber.isNotEmpty() && password.isNotEmpty()) isOtpClicked = true
                    }
                )
            }
        }

        TextFieldOTP(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = phoneNumber.isNotEmpty() && password.isNotEmpty(),
            otpText = otpValue.value,
            onOtpTextChange = { value, _ ->
                if (value.isDigitsOnly() &&
                    phoneNumber.isNotEmpty() &&
                    password.isNotEmpty()
                ) {
                    otpValue.value = value
                }
            })
    }
}

@Composable
internal fun EmailRegister(
    onEmailResult: (String) -> Unit,
    onPasswordResult: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isOtpClicked by rememberSaveable { mutableStateOf(false) }

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Get OTP or Link Click",
                style = MaterialTheme.typography.bodyMedium,
                color = if (email.isNotEmpty() && password.isNotEmpty()) ColorTextPrimary else ColorTextSecondary,
                textAlign = TextAlign.Start
            )
            val countdownString = buildAnnotatedString {
                append("Send after")
                withStyle(style = SpanStyle(ColorPrimary)) {
                    append(" ")
                }
            }
//                                         todo: check phone requirement. no phone requirement yet
            if (isOtpClicked) {
                Text(
                    text = countdownString,
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorTextPrimary,
                    textAlign = TextAlign.Start
                )
            } else {
                ClickableText(
                    text = AnnotatedString("Send OTP"),
                    style = TextStyle(
                        color = if (email.isNotEmpty() && password.isNotEmpty()) ColorPrimary else ColorTextButtonDisable,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    ),
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) isOtpClicked = true
                    }
                )
            }
        }

        TextFieldOTP(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = true,
            otpText = "",
            onOtpTextChange = { value, _ ->

            })
    }
}