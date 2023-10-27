package com.zte.iptvclient.android.auth.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldOTP(
    modifier: Modifier,
    onSendOTP: () -> Unit,
    label: String,
    countdown: String,
    isEnabled: Boolean,
    value1: String,
    value2: String,
    value3: String,
    value4: String,
    onValueChange1: (value: String) -> Unit,
    onValueChange2: (value: String) -> Unit,
    onValueChange3: (value: String) -> Unit,
    onValueChange4: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                textAlign = TextAlign.Start,
                color = if (isEnabled) Color(0xFFF5F5F5) else Color(0xFF919999),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
            val countdownString = buildAnnotatedString {
                append("Send after")
                withStyle(style = SpanStyle(Color(0xFF07E3D0))) {
                    append(" $countdown")
                }
            }
            Log.e("log_","countdown $countdown")
            if (countdown.isNotEmpty()) {
                Text(
                    text = countdownString,
                    textAlign = TextAlign.Start,
                    color = if (isEnabled) Color(0xFFF5F5F5) else Color(0xFF919999),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500
                )
            } else {
                ClickableText(
                    text = AnnotatedString("Send OTP"),
                    style = TextStyle(
                        color = if (isEnabled) Color(0xFF07E3D0) else Color(0x5507E3D0),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    ),
                    onClick = {
                        onSendOTP()
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextOTP(
                value = value1,
                onValueChange = onValueChange1,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            TextOTP(
                value = value2,
                onValueChange = onValueChange2,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            TextOTP(
                value = value3,
                onValueChange = onValueChange3,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            TextOTP(
                value = value4,
                onValueChange = onValueChange4,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextOTP(
    value: String,
    onValueChange: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        modifier = Modifier.width(60.dp),
        value = value,
        onValueChange = { num ->
            if (num.length <= 1 && num.matches(Regex("[0-9]"))) {
                onValueChange(num)
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF202020),
            cursorColor = Color(0xFFF5F5F5),
            focusedTextColor = Color(0xFFF5F5F5),
            unfocusedTextColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            color = Color(0xFFFFFFFF),
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "-",
                color = Color(0xFF3A3D3D),
                fontSize = 22.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldOTPPreview() {
    TextFieldOTP(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 12.dp
            ),
        onSendOTP = {},
        label = "Get OTP or Click Link",
        countdown = "02:50",
        isEnabled = true,
        value1 = "1",
        value2 = "3",
        value3 = "",
        value4 = "",
        onValueChange1 = {},
        onValueChange2 = {},
        onValueChange3 = {},
        onValueChange4 = {},
    )
}