package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@Composable
fun TextSwitchAuth(
    modifier: Modifier,
    questionText: String,
    actionText: String,
    onTextClick: () -> Unit
) {
    VisionplusbssandroidTheme {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = questionText,
                style = MaterialTheme.typography.labelSmall,
                color = ColorTextSecondary,
            )
            ClickableText(
                text = AnnotatedString(actionText),
                style = TextStyle(
                    color = ColorPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 15.sp,
                    letterSpacing = 0.5.sp
                ),
                onClick = {
                    onTextClick()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextSwitchAuthPreview() {
    TextSwitchAuth(
        modifier = Modifier,
        questionText = "Have not created an account? ",
        actionText = "Register"
    ) {
    }
}