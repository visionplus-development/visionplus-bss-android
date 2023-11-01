package com.zte.iptvclient.android.auth.presentation.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.components.ConfigLayoutDevice
import com.zte.iptvclient.android.auth.presentation.components.TabForm
import com.zte.iptvclient.android.auth.presentation.components.TextFieldEmail
import com.zte.iptvclient.android.auth.presentation.components.TextFieldOTP
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPhoneNumber
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundForm
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextButtonDisable
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.NetworkUtils
import com.zte.iptvclient.android.auth.utils.ViewUtils

@Composable
fun ForgotPasswordPage(navController: NavController) {


    BackHandler {
        navController.popBackStack()
    }
    VisionplusbssandroidTheme {
        ConfigLayoutDevice(
            screenMobile = { ForgotPasswordContent(navController) },
            screenTablet = { ForgotPasswordContent(navController)})
    }
}
@Composable
fun ForgotPasswordContent(navController: NavController){

    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val isOtpClicked = rememberSaveable { mutableStateOf(false) }
    val otpValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context= LocalContext.current

    Scaffold(
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = "Forgot Password",
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TabForm(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(ColorBackgroundForm),
                        onTabClick = {
                            email.value = ""
                            phone.value = ""
                            otpValue.value = ""
                            isOtpClicked.value = false
                        },
                        tabPhone = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 16.dp
                                    ),
                            ) {


                                TextFieldPhoneNumber(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 32.dp),
                                    label = "Enter Phone Number",
                                    inputWrapper = InputWrapper(phone.value, null),
                                    isEnabled = true,
                                    onPhoneNumberChange = { value ->
                                        phone.value = value
                                    }
                                )



                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Get OTP or Link Click",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = if (phone.value.isNotEmpty()) ColorTextPrimary else ColorTextSecondary,
                                        textAlign = TextAlign.Start
                                    )
                                    val countdownString = buildAnnotatedString {
                                        append("Send after")
                                        withStyle(style = SpanStyle(ColorPrimary)) {
                                            append(" ")
                                        }
                                    }
//                                         todo: check phone requirement. no phone requirement yet
                                        if (isOtpClicked.value) {
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
                                                    color = if (phone.value.isNotEmpty()) ColorPrimary else ColorTextButtonDisable,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.W600
                                                ),
                                                onClick = {
                                                    if (phone.value.isNotEmpty()) isOtpClicked.value = true
                                                }
                                            )
                                        }
                                    }
                                    TextFieldOTP(
                                        modifier = Modifier.fillMaxWidth(),
                                        otpText = otpValue.value,
                                        // todo: requirement TBD, need BE integration
                                        isEnabled = phone.value.isNotEmpty(),
                                        onOtpTextChange = { value, _ ->
                                            if (value.isDigitsOnly() && phone.value.isNotEmpty()) {
                                                otpValue.value = value
                                            }
                                        }
                                    )
                                }
                            },
                            tabEmail = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 16.dp,
                                            vertical = 16.dp
                                        )
                                ) {
                                    TextFieldEmail(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 32.dp),
                                        label = "Email",
                                        inputWrapper = InputWrapper(email.value, null),
                                        onValueChange = { value ->
                                            email.value = value
                                        },
                                        keyboardOptions = remember {
                                            KeyboardOptions(
                                                keyboardType = KeyboardType.Email,
                                                imeAction = ImeAction.Next
                                            )
                                        },
                                        keyboardActions = KeyboardActions(
                                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                                        )
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Get OTP or Link Click",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (email.value.isNotEmpty()) ColorTextPrimary else ColorTextSecondary,
                                            textAlign = TextAlign.Start
                                        )
                                        val countdownString = buildAnnotatedString {
                                            append("Send after")
                                            withStyle(style = SpanStyle(ColorPrimary)) {
                                                append(" ")
                                            }
                                        }
                                        // todo: check email requirement. no email requirement yet
                                        if (isOtpClicked.value) {
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
                                                    color = if (email.value.isNotEmpty()) ColorPrimary else ColorTextButtonDisable,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.W600
                                                ),
                                                onClick = {
                                                    if (email.value.isNotEmpty()) isOtpClicked.value = true
                                                }
                                            )
                                        }
                                    }
                                    TextFieldOTP(
                                        modifier = Modifier.fillMaxWidth(),
                                        otpText = otpValue.value,
                                        // todo: requirement TBD, need BE integration
                                        isEnabled = email.value.isNotEmpty(),
                                        onOtpTextChange = { value, _ ->
                                            if (value.isDigitsOnly() && email.value.isNotEmpty()) {
                                                otpValue.value = value
                                            }
                                        }
                                    )
                                }
                            }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 42.dp),
                            text = "We will send the verification code to your email address.",
                            style = MaterialTheme.typography.bodySmall,
                            color = ColorTextSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            bottomBar = {
                ButtonMain(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "Next",
                    isEnabled = otpValue.value.length == 4
                ) {
                    // todo: check connection in retrofit interceptor
                    if (NetworkUtils.isConnect(context)) {
                        // todo: request BE
                    } else {
                        ViewUtils.showToast(context, context.getString(R.string.error_no_internet))
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    ForgotPasswordPage(navController = NavController(LocalContext.current))
}