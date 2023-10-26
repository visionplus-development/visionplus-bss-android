package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
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
            if (isEnabled) {
                Text(
                    text = countdownString,
                    textAlign = TextAlign.Start,
                    color = if (isEnabled) Color(0xFFF5F5F5) else Color(0xFF919999),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                modifier = Modifier.width(60.dp),
                value = num1,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF202020),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
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
            )
            TextField(
                modifier = Modifier.width(60.dp),
                value = num2,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF202020),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
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
            )
            TextField(
                modifier = Modifier.width(60.dp),
                value = num3,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF202020),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
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
            )
            TextField(
                modifier = Modifier.width(60.dp),
                value = num4,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF202020),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
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
            )
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