package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorDivider
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary

@Composable
fun TextFieldOTP(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    isEnabled: Boolean = true,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        enabled = isEnabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        },
        visualTransformation = VisualTransformation.None
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val char = when {
        index >= text.length -> "-"
        else -> text[index].toString()
    }

    Text(
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .background(
                ColorBackgroundTextField,
                RoundedCornerShape(8.dp)
            )
            .wrapContentSize(),
        text = char,
        fontSize = 22.sp,
        fontWeight = FontWeight.W400,
        color = if (char == "-") ColorDivider else ColorTextPrimary,
    )
}

@Preview(showBackground = true)
@Composable
fun OtpTextFieldPreview() {
    TextFieldOTP(
        otpText = "21",
        onOtpTextChange = { value, otpInpitFilled ->
        }
    )
}