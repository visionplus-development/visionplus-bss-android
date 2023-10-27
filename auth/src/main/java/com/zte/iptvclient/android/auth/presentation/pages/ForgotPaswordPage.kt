package com.zte.iptvclient.android.auth.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.components.TabForm
import com.zte.iptvclient.android.auth.presentation.components.TextFieldEmail
import com.zte.iptvclient.android.auth.presentation.components.TextFieldOTP
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain

@Composable
fun ForgotPasswordPage() {
    var email by remember { mutableStateOf("") }
    var otpNum1 by remember { mutableStateOf("") }
    var otpNum2 by remember { mutableStateOf("") }
    var otpNum3 by remember { mutableStateOf("") }
    var otpNum4 by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = "Create New Password"
            ) {
            }
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
                            .background(Color(0xFF141414)),
                        onTabClick = {
                            email = ""
                            otpNum1 = ""
                            otpNum2 = ""
                            otpNum3 = ""
                            otpNum4 = ""
                        },
                        tabPhone = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 16.dp
                                    )
                            ) {
                                TextFieldOTP(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    onSendOTP = {
                                    },
                                    label = "Get OTP or Link Click",
                                    countdown = "",
                                    isEnabled = true,
                                    value1 = otpNum1,
                                    value2 = otpNum2,
                                    value3 = otpNum3,
                                    value4 = otpNum4,
                                    onValueChange1 = {
                                        otpNum1 = it
                                    },
                                    onValueChange2 = {
                                        otpNum2 = it
                                    },
                                    onValueChange3 = {
                                        otpNum3 = it
                                    },
                                    onValueChange4 = {
                                        otpNum4 = it
                                    },
                                    keyboardOptions = remember {
                                        KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next
                                        )
                                    },
                                    keyboardActions = KeyboardActions(
                                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                                    )
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
                                    value = email,
                                    onValueChange = { value ->
                                        email = value
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
                                TextFieldOTP(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    onSendOTP = {
                                    },
                                    label = "Get OTP or Link Click",
                                    countdown = "",
                                    isEnabled = true,
                                    value1 = otpNum1,
                                    value2 = otpNum2,
                                    value3 = otpNum3,
                                    value4 = otpNum4,
                                    onValueChange1 = {
                                        otpNum1 = it
                                    },
                                    onValueChange2 = {
                                        otpNum2 = it
                                    },
                                    onValueChange3 = {
                                        otpNum3 = it
                                    },
                                    onValueChange4 = {
                                        otpNum4 = it
                                    },
                                    keyboardOptions = remember {
                                        KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next
                                        )
                                    },
                                    keyboardActions = KeyboardActions(
                                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                                    )
                                )
                            }
                        }
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 42.dp),
                        text = "We will send the verification code to your email address.",
                        color = Color(0xFF919999),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
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
                isEnabled = false
            ) {
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    ForgotPasswordPage()
}