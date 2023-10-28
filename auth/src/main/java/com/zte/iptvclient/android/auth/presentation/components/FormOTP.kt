package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextOTP(
    modifier: Modifier,
    label: String,
    countdown: String,
    isEnabled: Boolean,
    num1: String,
    num2: String,
    num3: String,
    num4: String
) {
    VisionplusbssandroidTheme {
        Column(
            modifier = modifier,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isEnabled) ColorTextPrimary else ColorTextSecondary,
                    textAlign = TextAlign.Start
                )
                val countdownString = buildAnnotatedString {
                    append("Send after")
                    withStyle(style = SpanStyle(ColorPrimary)) {
                        append(" $countdown")
                    }
                }
                if (isEnabled) {
                    Text(
                        text = countdownString,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isEnabled) ColorTextPrimary else ColorTextSecondary,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.width(60.dp),
                    value = num1,
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = ColorBackgroundTextField,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = ColorTextPrimary,
                        unfocusedTextColor = ColorTextPrimary
                    ),
                    textStyle = TextStyle(
                        color = ColorTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "-",
                            color = ColorTextSecondary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center
                        )
                    },
                )
                TextField(
                    modifier = Modifier.width(60.dp),
                    value = num2,
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = ColorBackgroundTextField,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = ColorTextPrimary,
                        unfocusedTextColor = ColorTextPrimary
                    ),
                    textStyle = TextStyle(
                        color = ColorTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "-",
                            color = ColorTextSecondary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center
                        )
                    },
                )
                TextField(
                    modifier = Modifier.width(60.dp),
                    value = num3,
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = ColorBackgroundTextField,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = ColorTextPrimary,
                        unfocusedTextColor = ColorTextPrimary
                    ),
                    textStyle = TextStyle(
                        color = ColorTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "-",
                            color = ColorTextSecondary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center
                        )
                    },
                )
                TextField(
                    modifier = Modifier.width(60.dp),
                    value = num4,
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = ColorBackgroundTextField,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = ColorTextPrimary,
                        unfocusedTextColor = ColorTextPrimary
                    ),
                    textStyle = TextStyle(
                        color = ColorTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "-",
                            color = ColorTextSecondary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextOTPPreview() {
    TextOTP(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 12.dp
            ),
        label = "Get OTP or Click Link",
        countdown = "02:50",
        true,
        "1",
        "3",
        "",
        ""
    )
}