package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSwitchAuth(
    modifier: Modifier,
    questionText: String,
    actionText: String,
    onTextClick: () -> Unit
) {
    Row(modifier = modifier) {
        Text(
            text = questionText,
            color = Color(0xFF7D7D7D),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
        ClickableText(
            text = AnnotatedString(actionText),
            style = TextStyle(
                color = Color(0xFF07E3D0),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            ),
            onClick = {
                onTextClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextSwitchAuthPreview() {
    TextSwitchAuth(
        modifier = Modifier.padding(all = 16.dp),
        questionText = "Have not created an account? ",
        actionText = "Register") {
    }
}